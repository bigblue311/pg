package com.pg.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pg.biz.manager.TransactionManager;
import com.pg.biz.model.OrderVO;
import com.pg.dal.dao.CustomerDAO;
import com.pg.dal.dao.EmployeeDAO;
import com.pg.dal.dao.OpLogDAO;
import com.pg.dal.dao.OrderDAO;
import com.pg.dal.dao.PackageDAO;
import com.pg.dal.dao.ProductDAO;
import com.pg.dal.dao.PurchaseDAO;
import com.pg.dal.enumerate.OrderStatusEnum;
import com.pg.dal.model.CustomerDO;
import com.pg.dal.model.EmployeeDO;
import com.pg.dal.model.OpLogDO;
import com.pg.dal.model.OrderDO;
import com.pg.dal.model.PurchaseDO;
import com.pg.dal.query.OpLogQueryCondition;
import com.pg.dal.query.OrderQueryCondition;
import com.pg.dal.query.PurchaseQueryCondition;
import com.victor.framework.common.tools.StringTools;
import com.victor.framework.dal.basic.Paging;

public class TransactionManagerImpl implements TransactionManager{

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private OpLogDAO opLogDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private PackageDAO packageDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private PurchaseDAO purchaseDAO;
	
	@Override
	public void createOrder(OrderDO orderDO) {
		orderDAO.insert(orderDO);
	}
	
	@Override
	public void createPurchase(PurchaseDO purchaseDO) {
		purchaseDAO.insert(purchaseDO);
	}
	
	@Override
	public void createOrder(OrderDO orderDO, Long employeeId) {
		if(orderDO != null){
			Long orderId = orderDAO.insert(orderDO);
			
			OpLogDO opLogDO = new OpLogDO();
			opLogDO.setEmployeeId(employeeId);
			opLogDO.setOrderId(orderId);
			CustomerDO customerDO = customerDAO.getById(orderDO.getCustomerId());
			if(customerDO == null){
				return;
			}
			String msg = getEmployeeName(employeeId)+"为客户["+customerDO.getMobile()+"]提交了订单";
			opLogDO.setAction(msg);
			opLogDAO.insert(opLogDO);
		}
	}
	
	@Override
	public void createPurchase(PurchaseDO purchaseDO, Long employeeId) {
		purchaseDAO.insert(purchaseDO);
		Long orderId = purchaseDO.getOrderId();
		
		OpLogDO opLogDO = new OpLogDO();
		opLogDO.setEmployeeId(employeeId);
		opLogDO.setOrderId(orderId);
		CustomerDO customerDO = customerDAO.getById(purchaseDO.getCustomerId());
		if(customerDO == null){
			return;
		}
		String msg = getEmployeeName(employeeId)+"为客户["+customerDO.getMobile()+"]提交了购买"+purchaseDO.getName()+"["+purchaseDO.getExtendCode()+"]";
		opLogDO.setAction(msg);
		opLogDAO.insert(opLogDO);
	}

	@Override
	public void updateOrder(OrderDO orderDO,Long employeeId) {
		if(orderDO != null){
			OpLogDO opLogDO = new OpLogDO();
			opLogDO.setEmployeeId(employeeId);
			opLogDO.setOrderId(orderDO.getId());
			opLogDO.setAction(getOpLogAction(orderDO,employeeId));
			opLogDAO.insert(opLogDO);
			orderDAO.update(orderDO);
		}
	}
	
	@Override
	public void updatePurchase(PurchaseDO purchaseDO, Long employeeId) {
		if(purchaseDO != null){
			OpLogDO opLogDO = new OpLogDO();
			opLogDO.setEmployeeId(employeeId);
			opLogDO.setOrderId(purchaseDO.getId());
			opLogDO.setAction(getOpLogAction(purchaseDO,employeeId));
			opLogDAO.insert(opLogDO);
			purchaseDAO.update(purchaseDO);
		}
	}
	
	@Override
	public OrderDO getOrderDOById(Long id) {
		return orderDAO.getById(id);
	}
	
	@Override
	public PurchaseDO getPurchaseDOById(Long id) {
		return purchaseDAO.getById(id);
	}
	
	@Override
	public OrderVO getOrderVOById(Long id) {
		return DO2VO(getOrderDOById(id));
	}

	@Override
	public Paging<OrderDO> getOrderDOPage(OrderQueryCondition queryCondition) {
		int totalSize = orderDAO.getCount(queryCondition);
		@SuppressWarnings("unchecked")
		Paging<OrderDO> page = queryCondition.getPaging(totalSize, 5);
		List<OrderDO> list = orderDAO.getPage(queryCondition);
		page.setData(list);
		return page;
	}
	
	@Override
	public Paging<PurchaseDO> getPurchaseDOPage(PurchaseQueryCondition queryCondition) {
		int totalSize = purchaseDAO.getCount(queryCondition);
		@SuppressWarnings("unchecked")
		Paging<PurchaseDO> page = queryCondition.getPaging(totalSize, 5);
		List<PurchaseDO> list = purchaseDAO.getPage(queryCondition);
		page.setData(list);
		return page;
	}
	
	@Override
	public Paging<OrderVO> getOrderVOPage(OrderQueryCondition queryCondition) {
		int totalSize = orderDAO.getCount(queryCondition);
		@SuppressWarnings("unchecked")
		Paging<OrderVO> page = queryCondition.getPaging(totalSize, 5);
		List<OrderDO> doList = orderDAO.getPage(queryCondition);
		List<OrderVO> voList = Lists.transform(doList, new Function<OrderDO,OrderVO>(){

			@Override
			public OrderVO apply(OrderDO orderDO) {
				return DO2VO(orderDO);
			}
			
		});
		page.setData(voList);
		return page;
	}
	
	private OrderVO DO2VO(OrderDO orderDO){
		if(orderDO == null){
			return null;
		}
		OrderVO orderVO = new OrderVO();
		orderVO.setOrderDO(orderDO);
		CustomerDO customerDO = customerDAO.getById(orderDO.getCustomerId());
		if(customerDO == null){
			return null;
		}
		orderVO.setCustomerDO(customerDO);
		OpLogQueryCondition query = new OpLogQueryCondition();
		query.setOrderId(orderDO.getId());
		List<OpLogDO> opList = opLogDAO.getByCondition(query);
		orderVO.setOpLogList(opList);
		
		PurchaseQueryCondition queryCondition = new PurchaseQueryCondition();
		queryCondition.setOrderId(orderDO.getId());
		List<PurchaseDO> purchaseList = purchaseDAO.getByCondition(queryCondition);
		orderVO.setPurchaseList(purchaseList);
		return orderVO;
	}

	private String getEmployeeName(Long employeeId){
		EmployeeDO employee = employeeDAO.getById(employeeId);
		if(employee == null){
			return "[神秘人]";
		} else {
			return employee.getName();
		}
	}
	
	private String getOpLogAction(PurchaseDO purchaseDO, Long employeeId){
		if(purchaseDO == null){
			return "";
		}
		Long id = purchaseDO.getId();
		PurchaseDO originDO = purchaseDAO.getById(id);
		String msg = getEmployeeName(employeeId)+"操作了";
		if(purchaseDO.getQuantity() != null){
			msg += "数量["+originDO.getQuantity()+"]:"+purchaseDO.getQuantity()+";";
		}
		if(purchaseDO.getPrice() != null){
			msg += "单价["+originDO.getPrice()+"]:"+purchaseDO.getPrice()+";";
		}
		return msg;
	}
	
	private String getOpLogAction(OrderDO orderDO, Long employeeId){
		if(orderDO == null){
			return "";
		}
		Long id = orderDO.getId();
		OrderDO originDO = orderDAO.getById(id); 
		String msg = getEmployeeName(employeeId)+"操作了";
		if(StringTools.isNotEmpty(orderDO.getAddressTo())){
			msg += "收货仓库地址["+originDO.getAddressTo()+"]:"+orderDO.getAddressTo()+";";
		}
		if(StringTools.isNotEmpty(orderDO.getKeeper())){
			msg += "收货仓库联系人["+originDO.getKeeper()+"]:"+orderDO.getKeeper()+";";
		}
		if(StringTools.isNotEmpty(orderDO.getPhone())){
			msg += "收货仓库联系人电话["+originDO.getPhone()+"]:"+orderDO.getPhone()+";";
		}
		if(StringTools.isNotEmpty(orderDO.getMobile())){
			msg += "收货仓库联系人手机["+originDO.getMobile()+"]:"+orderDO.getMobile()+";";
		}
		if(orderDO.getDeposit()!=null){
			msg += "定金["+originDO.getDeposit()+"]:"+orderDO.getDeposit()+";";
		}
		if(orderDO.getTotalPrice()!=null){
			msg += "单价["+originDO.getTotalPrice()+"]:"+orderDO.getTotalPrice()+";";
		}
		if(orderDO.getTransportFee()!=null){
			msg += "物流费["+originDO.getTransportFee()+"]:"+orderDO.getTransportFee()+";";
		}
		if(StringTools.isNotEmpty(orderDO.getTransportCode())){
			msg += "物流编号["+originDO.getTransportCode()+"]:"+orderDO.getTransportCode()+";";
		}
		if(StringTools.isNotEmpty(orderDO.getStatus())){
			try {
				String old = OrderStatusEnum.getByCode(originDO.getStatus()).getDesc();
				String cur = OrderStatusEnum.getByCode(orderDO.getStatus()).getDesc();
				msg += "状态["+old+"]:"+cur+";";
			} catch (Exception e) {
				msg += "状态异常:;";
			}
		}
		if(StringTools.isNotEmpty(orderDO.getComment())){
			msg += "备注["+originDO.getComment()+"]:"+orderDO.getComment()+";";
		}
		return msg;
	}
}