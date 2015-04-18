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
import com.pg.biz.manager.ProductManager;
import com.pg.biz.manager.WarehouseManager;
import com.pg.dal.enumerate.EnableEnum;
import com.pg.dal.model.PublishDO;
import com.pg.dal.model.WarehouseDO;
import com.pg.dal.query.PublishQueryCondition;
import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.Paging;

public class Publish {
	
	@Autowired
	private BufferedRequestContext buffered;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private WarehouseManager warehouseManager;
	
	public void execute(@Params PublishQueryCondition queryCondition,
						Context context) throws Exception{
		buffered.setBuffering(false);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter(); 
		
		List<NameValuePair> pairs = Lists.newArrayList();
		pairs.add(new NameValuePair("","3%"));
		pairs.add(new NameValuePair("名称","10%"));
		pairs.add(new NameValuePair("标题","10%"));
		pairs.add(new NameValuePair("编号","5%"));
		pairs.add(new NameValuePair("单价","5%"));
		pairs.add(new NameValuePair("库存","5%"));
		pairs.add(new NameValuePair("仓库","12%"));
		pairs.add(new NameValuePair("起订量","5%"));
		pairs.add(new NameValuePair("销售时间","15%"));
		pairs.add(new NameValuePair("状态","5%"));
		pairs.add(new NameValuePair("描述","20%"));
		printTableHead(out,pairs);
		
		int count = 0;
		
		Paging<PublishDO> pageList = Paging.emptyPage();
		queryCondition.setPage(1);
		pageList = productManager.getPublishPage(queryCondition);
		for(PublishDO publishDO : pageList.getData()){
			count++;
			printTableRow(out,count,publishDO);
		}
		
		for(int page=2;page<=pageList.getTotalPage();page++){
			queryCondition.setPage(page);
			pageList = productManager.getPublishPage(queryCondition);
			for(PublishDO publishDO : pageList.getData()){
				count++;
				printTableRow(out,count,publishDO);
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
	
	private void printTableRow(PrintWriter out, int count, PublishDO publishDO) throws Exception{
		if(count % 2 == 0){
			out.write("<tr class='row'>");
		} else {
			out.write("<tr class='row odd'>");
		}
		
		out.write("<td style='text-align:center'>"+count+"</td>");
//		out.write("<td>"+publishDO.getName()+"</td>");
//		out.write("<td>"+publishDO.getTitle()+"</td>");
//		out.write("<td>"+publishDO.getExtendCode()+"</td>");
//		out.write("<td>"+publishDO.getPrice()+"元/"+publishDO.getUnit()+"</td>");
//		out.write("<td>"+publishDO.getBalance()+publishDO.getUnit()+"</td>");
//		out.write("<td>"+getWarehouse(publishDO)+"</td>");
//		out.write("<td>"+publishDO.getLimitBuy()+"</td>");
		out.write("<td>"+DateTools.DateToString(publishDO.getValidFrom()) +" - "+ DateTools.DateToString(publishDO.getValidTo()) +"</td>");
		out.write("<td>"+EnableEnum.getByCode(publishDO.getEnable())+"</td>");
		out.write("<td>"+publishDO.getDescription()+"</td>");
		out.write("</tr>");
	}
	
	private void printTableFoot(PrintWriter out,int count) throws Exception{
		out.write("</table><div>共"+count+"条数据</div>");
	}
	
	private String getWarehouse(PublishDO publishDO){
		if(publishDO == null){
			return "";
		}
		WarehouseDO warehouseDO = warehouseManager.getById(publishDO.getWarehouseId());
		if(warehouseDO == null){
			return "";
		}
		return warehouseDO.getName();
	}
}
