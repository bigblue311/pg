package com.pg.dal.enumerate;

import java.util.List;

import com.google.common.collect.Lists;

public enum OrderStatusEnum {
	提交("0","订单已提交","待确认订单"),
	确认("1","订单已确认","待确认发货"),
	发货("2","卖家确认发货","待确认收货"),
	收货("3","买家确认收货","待尾款结算"),
	结算("4","订单尾款已结算","完成"),
	取消("999","订单已取消","取消");
	
	private String code;
	private String desc;
	private String next;
	
	private OrderStatusEnum(String code,String desc, String next){
		this.code = code;
		this.desc = desc;
		this.next = next;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public String getNext() {
		return next;
	}

	public static List<OrderStatusEnum> getAll(){
		return Lists.newArrayList(OrderStatusEnum.values());
	}
	
	public static OrderStatusEnum getByCode(String code){
		for(OrderStatusEnum status : getAll()){
			if(status.code.equals(code)){
				return status;
			}
		}
		return null;
	}
	
	public static OrderStatusEnum getByDesc(String desc){
		for(OrderStatusEnum status : getAll()){
			if(status.desc.equals(desc)){
				return status;
			}
		}
		return null;
	}
	
	public static OrderStatusEnum getByText(String text){
		if(getByCode(text) == null) {
			return getByDesc(text);
		}
		return getByCode(text);
	}
}
