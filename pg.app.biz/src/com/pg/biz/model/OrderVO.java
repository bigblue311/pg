package com.pg.biz.model;

import java.io.Serializable;
import java.util.List;

import com.pg.dal.model.CustomerDO;
import com.pg.dal.model.OrderDO;
import com.pg.dal.model.OpLogDO;

public class OrderVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8722238630359313606L;
	
	private OrderDO orderDO;
	private CustomerDO customerDO;
	private List<OpLogDO> opLogList;
	private String name;
	private String title;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
