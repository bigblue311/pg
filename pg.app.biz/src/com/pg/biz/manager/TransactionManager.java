package com.pg.biz.manager;

import com.pg.biz.model.OrderVO;
import com.pg.dal.model.OrderDO;
import com.pg.dal.query.OrderQueryCondition;
import com.victor.framework.dal.basic.Paging;

public interface TransactionManager {
	
	/**
	 * 创建订单
	 * @param orderDO
	 */
	void create(OrderDO orderDO);
	
	/**
	 * 更新订单
	 * @param orderDO
	 * @param employeeId
	 */
	void update(OrderDO orderDO,Long employeeId);
	
	/**
	 * 根据订单ID获取
	 * @param id
	 * @return
	 */
	OrderDO getDOById(Long id);
	OrderVO getVOById(Long id);
	
	/**
	 * 根据查询条件获取分页数据
	 * @param queryCondition
	 * @return
	 */
	Paging<OrderDO> getDOPage(OrderQueryCondition queryCondition);
	Paging<OrderVO> getVOPage(OrderQueryCondition queryCondition);
}
