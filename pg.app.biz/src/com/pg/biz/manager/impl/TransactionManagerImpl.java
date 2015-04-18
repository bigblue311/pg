package com.pg.biz.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pg.biz.manager.TransactionManager;
import com.pg.biz.model.OrderAlertVO;
import com.pg.biz.model.OrderStatisticVO;
import com.pg.biz.model.OrderVO;
import com.pg.dal.dao.CustomerDAO;
import com.pg.dal.dao.EmployeeDAO;
import com.pg.dal.dao.OpLogDAO;
import com.pg.dal.dao.OrderDAO;
import com.pg.dal.dao.PurchaseDAO;
import com.pg.dal.dao.PurchaseItemDAO;
import com.pg.dal.enumerate.OrderStatusEnum;
import com.pg.dal.model.CustomerDO;
import com.pg.dal.model.EmployeeDO;
import com.pg.dal.model.OpLogDO;
import com.pg.dal.model.OrderDO;
import com.pg.dal.model.PurchaseDO;
import com.pg.dal.model.PurchaseItemDO;
import com.pg.dal.query.OpLogQueryCondition;
import com.pg.dal.query.OrderQueryCondition;
import com.pg.dal.query.PurchaseItemQueryCondition;
import com.pg.dal.query.PurchaseQueryCondition;
import com.victor.framework.common.tools.DateTools;
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
	private PurchaseDAO purchaseDAO;
	
	@Autowired
	private PurchaseItemDAO purchaseItemDAO;
	
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
			String msg = getEmployeeName(employeeId)+"为客户"+customerDO.getName()+"["+customerDO.getMobile()+"]提交了订单";
			opLogDO.setAction(msg);
			opLogDAO.insert(opLogDO);
		}
	}
	
	@Override
	public void createPurchase(PurchaseDO purchaseDO, Long employeeId, Long customerId) {
		purchaseDAO.insert(purchaseDO);
		Long orderId = purchaseDO.getOrderId();
		recalculate(orderId);
		
		OpLogDO opLogDO = new OpLogDO();
		opLogDO.setEmployeeId(employeeId);
		opLogDO.setOrderId(orderId);
		CustomerDO customerDO = customerDAO.getById(customerId);
		if(customerDO == null){
			return;
		}
		String msg = getEmployeeName(employeeId)+"为客户"+customerDO.getName()+"["+customerDO.getMobile()+"]购买了"+purchaseDO.getName();
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
	public void updatePurchase(PurchaseDO purchaseDO, Long employeeId, Long customerId) {
		if(purchaseDO != null){
			OpLogDO opLogDO = new OpLogDO();
			opLogDO.setEmployeeId(employeeId);
			opLogDO.setOrderId(purchaseDO.getId());
			opLogDO.setAction(getOpLogAction(purchaseDO,employeeId,customerId));
			opLogDAO.insert(opLogDO);
			purchaseDAO.update(purchaseDO);
			recalculate(purchaseDO.getOrderId());
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
		Map<PurchaseDO, List<PurchaseItemDO>> purchaseMap = Maps.newHashMap();
		for(PurchaseDO purchaseDO : purchaseList){
			PurchaseItemQueryCondition purchaseItemQueryCondition = new PurchaseItemQueryCondition();
			purchaseItemQueryCondition.setPurchaseId(purchaseDO.getId());
			List<PurchaseItemDO> purchaseItemlist = purchaseItemDAO.getByCondition(purchaseItemQueryCondition);
			purchaseMap.put(purchaseDO, purchaseItemlist);
		}
		orderVO.setPurchaseMap(purchaseMap);
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
	
	private String getOpLogAction(PurchaseDO purchaseDO, Long employeeId, Long customerId){
		if(purchaseDO == null){
			return "";
		}
		Long id = purchaseDO.getId();
		CustomerDO customerDO = customerDAO.getById(customerId);
		if(customerDO == null){
			return "";
		}
		PurchaseDO originDO = purchaseDAO.getById(id);
		String msg = getEmployeeName(employeeId)+"为客户"+customerDO.getName()+"["+customerDO.getMobile()+"]操作了"+purchaseDO.getName();
		if(StringTools.isNotEmpty(purchaseDO.getAddressTo())){
			msg += "收货仓库地址["+originDO.getAddressTo()+"]:"+purchaseDO.getAddressTo()+";";
		}
		if(StringTools.isNotEmpty(purchaseDO.getKeeper())){
			msg += "收货仓库联系人["+originDO.getKeeper()+"]:"+purchaseDO.getKeeper()+";";
		}
		if(StringTools.isNotEmpty(purchaseDO.getPhone())){
			msg += "收货仓库联系人电话["+originDO.getPhone()+"]:"+purchaseDO.getPhone()+";";
		}
		if(StringTools.isNotEmpty(purchaseDO.getMobile())){
			msg += "收货仓库联系人手机["+originDO.getMobile()+"]:"+purchaseDO.getMobile()+";";
		}
		if(purchaseDO.getTransportFee()!=null){
			msg += "物流费["+originDO.getTransportFee()+"]:"+purchaseDO.getTransportFee()+";";
		}
		if(StringTools.isNotEmpty(purchaseDO.getTransportCode())){
			msg += "物流编号["+originDO.getTransportCode()+"]:"+purchaseDO.getTransportCode()+";";
		}
		return msg;
	}
	
	private String getOpLogAction(OrderDO orderDO, Long employeeId){
		if(orderDO == null){
			return "";
		}
		Long id = orderDO.getId();
		OrderDO originDO = orderDAO.getById(id);
		CustomerDO customerDO = customerDAO.getById(orderDO.getCustomerId());
		if(customerDO == null){
			return "";
		}
		String msg = getEmployeeName(employeeId)+"为客户"+customerDO.getName()+"["+customerDO.getMobile()+"]操作了";
		if(orderDO.getDeposit()!=null){
			msg += "定金["+originDO.getDeposit()+"]:"+orderDO.getDeposit()+";";
		}
		if(orderDO.getTotalPrice()!=null){
			msg += "单价["+originDO.getTotalPrice()+"]:"+orderDO.getTotalPrice()+";";
		}
		if(orderDO.getTransportFee()!=null){
			msg += "物流费["+originDO.getTransportFee()+"]:"+orderDO.getTransportFee()+";";
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

	@Override
	public void deletePurchase(Long id,Long employeeId,Long customerId) {
		PurchaseDO purchaseDO = purchaseDAO.getById(id);
		recalculate(purchaseDO.getOrderId());
		OpLogDO opLogDO = new OpLogDO();
		opLogDO.setEmployeeId(employeeId);
		opLogDO.setOrderId(purchaseDO.getOrderId());
		CustomerDO customerDO = customerDAO.getById(customerId);
		if(customerDO == null){
			return;
		}
		String msg = getEmployeeName(employeeId)+"为客户["+customerDO.getMobile()+"]删除了"+purchaseDO.getName();
		opLogDO.setAction(msg);
		opLogDAO.insert(opLogDO);
		purchaseDAO.delete(id);
	}
	
	private void recalculate(Long orderId){
		OrderDO order = orderDAO.getById(orderId);
		if(order == null){
			return;
		}
		PurchaseQueryCondition queryCondition = new PurchaseQueryCondition();
		queryCondition.setOrderId(orderId);
		List<PurchaseDO> list = purchaseDAO.getByCondition(queryCondition);
		Double total = 0.0d;
		Double totalTransportFee = 0.0d;
		for(PurchaseDO purchaseDO : list){
			if(purchaseDO == null){
				continue;
			}
			PurchaseItemQueryCondition purchaseItemQueryCondition = new PurchaseItemQueryCondition();
			purchaseItemQueryCondition.setPurchaseId(purchaseDO.getId());
			List<PurchaseItemDO> purchaseItemlist = purchaseItemDAO.getByCondition(purchaseItemQueryCondition);
			for(PurchaseItemDO purchaseItemDO : purchaseItemlist){
				if(purchaseItemDO!=null && purchaseItemDO.getPrice()!=null && purchaseItemDO.getQuantity()!=null){
					total += purchaseItemDO.getPrice() * purchaseItemDO.getQuantity();
				}
			}
			if(purchaseDO.getTransportFee()!=null){
				totalTransportFee += purchaseDO.getTransportFee();
			}
		}
		order.setTotalPrice(total);
		order.setTransportFee(totalTransportFee);
		orderDAO.update(order);
	}

	@Override
	public OrderStatisticVO getMonthOrderStatistic() {
		OrderStatisticVO orderStatisticVO = new OrderStatisticVO();
		List<OrderAlertVO> alertList = Lists.newArrayList();
		OrderQueryCondition query = new OrderQueryCondition();
		String[] statusValid = new String[]{OrderStatusEnum.确认.getCode(),
											OrderStatusEnum.发货.getCode(),
											OrderStatusEnum.收货.getCode(),
											OrderStatusEnum.结算.getCode()};
		
		query.setGmtCreateStart(DateTools.monthStart()).setGmtCreateEnd(DateTools.monthEnd());
		query.status(statusValid);
		
		Integer totalCount = orderDAO.getCount(query);
		Double totalSale = orderDAO.getTotalSale(query);
		Double totalDeposit = orderDAO.getTotalDeposit(query);
		
		orderStatisticVO.setTotalCount(totalCount);
		orderStatisticVO.setTotalSale(totalSale);
		orderStatisticVO.setTotalDeposit(totalDeposit);
		
		OrderQueryCondition queryMonth = new OrderQueryCondition();
		queryMonth.setGmtCreateStart(DateTools.monthStart()).setGmtCreateEnd(DateTools.monthEnd());
		for(OrderStatusEnum status : OrderStatusEnum.getAll()){
			queryMonth.setStatus(status.getCode());
			Integer statusCount = orderDAO.getCount(queryMonth);
			OrderAlertVO orderAlert = new OrderAlertVO();
			if(statusCount == null){
				orderAlert.setSuccess(false);
				orderAlert.setMsg("系统错误,请速联系管理员!");
			} else {
				orderAlert.setSuccess(true);
				orderAlert.setMsg("查询成功");
			}
			orderAlert.setCount(statusCount == null?0:statusCount.intValue());
			orderAlert.setColor(status.getColor());
			orderAlert.setStatusCode(status.getCode());
			orderAlert.setStatusDesc(status.getDesc());
			alertList.add(orderAlert);
		}
		orderStatisticVO.setStatusList(alertList);
		return orderStatisticVO;
	}

	@Override
	public List<OrderDO> getOrderDOList(OrderQueryCondition queryCondition) {
		return orderDAO.getByCondition(queryCondition);
	}

	@Override
	public List<PurchaseDO> getPurchaseDOByCondition(PurchaseQueryCondition queryCondition) {
		return purchaseDAO.getByCondition(queryCondition);
	}
}