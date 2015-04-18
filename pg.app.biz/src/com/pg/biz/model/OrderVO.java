package com.pg.biz.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.pg.dal.model.CustomerDO;
import com.pg.dal.model.OrderDO;
import com.pg.dal.model.OpLogDO;
import com.pg.dal.model.PurchaseDO;
import com.pg.dal.model.PurchaseItemDO;

public class OrderVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8722238630359313606L;
	
	private OrderDO orderDO;
	private CustomerDO customerDO;
	private List<OpLogDO> opLogList;
	private Map<PurchaseDO,List<PurchaseItemDO>> purchaseMap;
	
	public OrderDO getOrderDO() {
		return orderDO;
	}
	public void setOrderDO(OrderDO orderDO) {
		this.orderDO = orderDO;
	}
	public CustomerDO getCustomerDO() {
		return customerDO;
	}
	public void setCustomerDO(CustomerDO customerDO) {
		this.customerDO = customerDO;
	}
	public List<OpLogDO> getOpLogList() {
		return opLogList;
	}
	public void setOpLogList(List<OpLogDO> opLogList) {
		this.opLogList = opLogList;
	}
	public Map<PurchaseDO, List<PurchaseItemDO>> getPurchaseMap() {
		return purchaseMap;
	}
	public void setPurchaseMap(Map<PurchaseDO, List<PurchaseItemDO>> purchaseMap) {
		this.purchaseMap = purchaseMap;
	}
}
