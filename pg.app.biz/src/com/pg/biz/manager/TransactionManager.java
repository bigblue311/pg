package com.pg.biz.manager;

import java.util.List;

import com.pg.biz.model.OrderStatisticVO;
import com.pg.biz.model.OrderVO;
import com.pg.dal.model.OrderDO;
import com.pg.dal.model.PurchaseDO;
import com.pg.dal.query.OrderQueryCondition;
import com.pg.dal.query.PurchaseQueryCondition;
import com.victor.framework.dal.basic.Paging;

public interface TransactionManager {
	
	/**
	 * 创建订单
	 * @param orderDO
	 */
	void createOrder(OrderDO orderDO);
	void createPurchase(PurchaseDO purchaseDO);
	
	/**
	 * 创建订单
	 * @param orderDO
	 * @param employeeId
	 */
	void createOrder(OrderDO orderDO, Long employeeId);
	void createPurchase(PurchaseDO purchaseDO, Long employeeId);
	
	/**
	 * 更新订单
	 * @param orderDO
	 * @param employeeId
	 */
	void updateOrder(OrderDO orderDO,Long employeeId);
	void updatePurchase(PurchaseDO purchaseDO, Long employeeId);
	
	/**
	 * 删除一个购买
	 * @param id
	 * @param employeeId
	 */
	void deletePurchase(Long id,Long employeeId);
	
	/**
	 * 根据订单ID获取
	 * @param id
	 * @return
	 */
	OrderDO getOrderDOById(Long id);
	PurchaseDO getPurchaseDOById(Long id);
	OrderVO getOrderVOById(Long id);
	
	/**
	 * 根据查询条件获取分页数据
	 * @param queryCondition
	 * @return
	 */
	Paging<OrderDO> getOrderDOPage(OrderQueryCondition queryCondition);
	List<PurchaseDO> getPurchaseDOByCondition(PurchaseQueryCondition queryCondition);
	Paging<PurchaseDO> getPurchaseDOPage(PurchaseQueryCondition queryCondition);
	Paging<OrderVO> getOrderVOPage(OrderQueryCondition queryCondition);
	
	List<OrderDO> getOrderDOList(OrderQueryCondition queryCondition);
	OrderStatisticVO getMonthOrderStatistic();
}
