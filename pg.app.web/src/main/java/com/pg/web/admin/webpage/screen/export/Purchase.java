package com.pg.web.admin.webpage.screen.export;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.google.common.collect.Lists;
import com.pg.biz.manager.CustomerManager;
import com.pg.biz.manager.TransactionManager;
import com.pg.dal.model.CustomerDO;
import com.pg.dal.model.OrderDO;
import com.pg.dal.model.PurchaseDO;
import com.pg.dal.query.PurchaseQueryCondition;
import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.Paging;

public class Purchase {
	
	@Autowired
	private BufferedRequestContext buffered;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private TransactionManager transactionManager;
	
	@Autowired
	private CustomerManager customerManager;
	
	public void execute(@Params PurchaseQueryCondition queryCondition,
						Context context) throws Exception {
		buffered.setBuffering(false);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter(); 
		
		List<NameValuePair> pairs = Lists.newArrayList();
		pairs.add(new NameValuePair("","3%"));
		pairs.add(new NameValuePair("创建时间","10%"));
		pairs.add(new NameValuePair("名称","5%"));
		pairs.add(new NameValuePair("标题","5%"));
		pairs.add(new NameValuePair("编号","5%"));
		pairs.add(new NameValuePair("客户","12%"));
		pairs.add(new NameValuePair("单价","5%"));
		pairs.add(new NameValuePair("数量","5%"));
		pairs.add(new NameValuePair("总价","5%"));
		pairs.add(new NameValuePair("运费","5%"));
		pairs.add(new NameValuePair("发货地址","7%"));
		pairs.add(new NameValuePair("收货地址","8%"));
		pairs.add(new NameValuePair("收货人","10%"));
		pairs.add(new NameValuePair("备注","10%"));
		printTableHead(out,pairs);
		
		int count = 0;
		
		Paging<PurchaseDO> pageList = Paging.emptyPage();
		queryCondition.setPage(1);
		pageList = transactionManager.getPurchaseDOPage(queryCondition);
		for(PurchaseDO purchaseDO : pageList.getData()){
			count++;
			printTableRow(out,count,purchaseDO);
		}
		
		for(int page=2;page<=pageList.getTotalPage();page++){
			queryCondition.setPage(page);
			pageList = transactionManager.getPurchaseDOPage(queryCondition);
			for(PurchaseDO purchaseDO : pageList.getData()){
				count++;
				printTableRow(out,count,purchaseDO);
			}
		}
		
		printTableFoot(out,count);
	}
	
	private void printTableHead(PrintWriter out,List<NameValuePair> pairs) throws Exception{
		out.write("<div><a href='#' onclick='javascript:history.go(-2)'>返回</a>&nbsp;<a href='#' onclick='javascript: window.print();'>打印</a></div>");
		out.write("<table>");
		out.write("<tr class='head'>");
		for(NameValuePair head : pairs){
			out.write("<td style='width:"+head.getValue()+"'>"+head.getName()+"</td>");
		}
		out.write("</tr>");
	}
	
	private void printTableRow(PrintWriter out, int count, PurchaseDO purchaseDO) throws Exception{
		if(count % 2 == 0){
			out.write("<tr class='row'>");
		} else {
			out.write("<tr class='row odd'>");
		}
		
		out.write("<td style='text-align:center'>"+count+"</td>");
		out.write("<td>"+DateTools.DateToString(purchaseDO.getGmtCreate())+"</td>");
		out.write("<td>"+purchaseDO.getName()+"</td>");
		out.write("<td>"+purchaseDO.getTitle()+"</td>");
//		out.write("<td>"+purchaseDO.getExtendCode()+"</td>");
//		out.write("<td>"+getCustomer(purchaseDO)+"</td>");
//		out.write("<td>"+purchaseDO.getPrice()+"元/"+purchaseDO.getUnit()+"</td>");
//		out.write("<td>"+purchaseDO.getQuantity()+purchaseDO.getUnit()+"</td>");
		out.write("<td>"+getTotal(purchaseDO)+"</td>");
		out.write("<td>"+getTransport(purchaseDO)+"</td>");
		out.write("<td>"+purchaseDO.getAddressFrom()+"</td>");
		out.write("<td>"+purchaseDO.getAddressTo()+"</td>");
		out.write("<td>"+getContact(purchaseDO)+"</td>");
		out.write("<td>"+purchaseDO.getComment()+"</td>");
		out.write("</tr>");
	}
	
	private void printTableFoot(PrintWriter out,int count) throws Exception{
		out.write("</table><div>共"+count+"条数据</div>");
	}
	
	private String getContact(PurchaseDO purchaseDO){
		return purchaseDO.getKeeper() + "("+purchaseDO.getMobile()+" "+purchaseDO.getPhone()+")";
	}
	
	private String getTransport(PurchaseDO purchaseDO){
		return purchaseDO.getTransportFee() + "("+purchaseDO.getTransportCode()+")";
	}
	
	private String getCustomer(PurchaseDO purchaseDO){
		if(purchaseDO == null) {
			return "";
		}
		OrderDO orderDO = transactionManager.getOrderDOById(purchaseDO.getOrderId());
		if(orderDO == null){
			return "";
		}
		CustomerDO customerDO = customerManager.getById(orderDO.getCustomerId());
		if(customerDO == null) {
			return "";
		}
		return customerDO.getName() + "("+customerDO.getMobile()+")";
	}
	
	private String getTotal(PurchaseDO purchaseDO){
		if(purchaseDO == null){
			return "";
		}
//		Double price = purchaseDO.getPrice();
//		Integer quantity = purchaseDO.getQuantity();
//		if(price == null || quantity == null){
//			return "";
//		}
//		return (price*quantity)+"";
		return "";
	}
}
