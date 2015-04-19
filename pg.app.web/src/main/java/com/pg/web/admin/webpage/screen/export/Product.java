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
import com.pg.biz.manager.BrandManager;
import com.pg.biz.manager.CategoryManager;
import com.pg.biz.manager.ProductManager;
import com.pg.dal.model.BrandDO;
import com.pg.dal.model.CategoryDO;
import com.pg.dal.model.ProductDO;
import com.pg.dal.query.ProductQueryCondition;
import com.victor.framework.common.tools.DateTools;
import com.victor.framework.dal.basic.Paging;

public class Product {
	
	@Autowired
	private BufferedRequestContext buffered;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private BrandManager brandManager;
	
	@Autowired
	private CategoryManager categoryManager;
	
	public void execute(@Params ProductQueryCondition queryCondition,
						Context context) throws Exception{
		buffered.setBuffering(false);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter(); 

		List<NameValuePair> pairs = Lists.newArrayList();
		pairs.add(new NameValuePair("","50px"));
		pairs.add(new NameValuePair("创建时间","150px"));
		pairs.add(new NameValuePair("名称","150px"));
		pairs.add(new NameValuePair("标题","150px"));
		pairs.add(new NameValuePair("品牌","150px"));
		pairs.add(new NameValuePair("品类","150px"));
		pairs.add(new NameValuePair("编号","150px"));
		pairs.add(new NameValuePair("条形码","150px"));
		pairs.add(new NameValuePair("箱码","150px"));
		pairs.add(new NameValuePair("规则","150px"));
		pairs.add(new NameValuePair("包装系数","150px"));
		pairs.add(new NameValuePair("MSU","150px"));
		pairs.add(new NameValuePair("3500箱价格","150px"));
		pairs.add(new NameValuePair("2000箱价格","150px"));
		pairs.add(new NameValuePair("800箱价格","150px"));
		pairs.add(new NameValuePair("200箱价格","150px"));
		pairs.add(new NameValuePair("100箱价格","150px"));
		pairs.add(new NameValuePair("建议售价","150px"));
		pairs.add(new NameValuePair("体积","150px"));
		pairs.add(new NameValuePair("重量(公斤/箱)","150px"));
		pairs.add(new NameValuePair("生效日期","150px"));
		pairs.add(new NameValuePair("保质期","150px"));
		pairs.add(new NameValuePair("描述","300px"));
		printTableHead(out,pairs);
		
		int count = 0;
		
		Paging<ProductDO> pageList = Paging.emptyPage();
		queryCondition.setPage(1);
		pageList = productManager.getProductPage(queryCondition);
		for(ProductDO productDO : pageList.getData()){
			count++;
			printTableRow(out,count,productDO);
		}
		
		for(int page=2;page<=pageList.getTotalPage();page++){
			queryCondition.setPage(page);
			pageList = productManager.getProductPage(queryCondition);
			for(ProductDO productDO : pageList.getData()){
				count++;
				printTableRow(out,count,productDO);
			}
		}
		
		printTableFoot(out,count);
	}
	
	private void printTableHead(PrintWriter out,List<NameValuePair> pairs) throws Exception{
		out.write("<div><a href='#' onclick='javascript:history.go(-2)'>返回</a>&nbsp;<a href='#' onclick='javascript: window.print();'>打印</a></div>");
		out.write("<table style='width:3500px;'>");
		out.write("<tr class='head'>");
		for(NameValuePair head : pairs){
			out.write("<td style='width:"+head.getValue()+"'>"+head.getName()+"</td>");
		}
		out.write("</tr>");
	}
	
	private void printTableRow(PrintWriter out, int count, ProductDO productDO) throws Exception{
		if(count % 2 == 0){
			out.write("<tr class='row'>");
		} else {
			out.write("<tr class='row odd'>");
		}
		
		out.write("<td style='text-align:center'>"+count+"</td>");
		out.write("<td>"+DateTools.DateToString(productDO.getGmtCreate())+"</td>");
		out.write("<td>"+productDO.getName()+"</td>");
		out.write("<td>"+productDO.getTitle()+"</td>");
		out.write("<td>"+getBrandName(productDO)+"</td>");
		out.write("<td>"+getCategoryName(productDO)+"</td>");
		out.write("<td>"+productDO.getCode()+"</td>");
		out.write("<td>"+productDO.getBarcode()+"</td>");
		out.write("<td>"+productDO.getBoxcode()+"</td>");
		out.write("<td>"+productDO.getSpec()+"</td>");
		out.write("<td>"+productDO.getPackageSpec()+"</td>");
		out.write("<td>"+productDO.getMsu()+"</td>");
		out.write("<td>"+productDO.getPrice3500()+"</td>");
		out.write("<td>"+productDO.getPrice2000()+"</td>");
		out.write("<td>"+productDO.getPrice800()+"</td>");
		out.write("<td>"+productDO.getPrice200()+"</td>");
		out.write("<td>"+productDO.getPrice100()+"</td>");
		out.write("<td>"+productDO.getPriceSugg()+"</td>");
		out.write("<td>"+productDO.getCubage()+"</td>");
		out.write("<td>"+productDO.getWeight()+"</td>");
		out.write("<td>"+DateTools.DateToString(productDO.getValidFrom())+"</td>");
		out.write("<td>"+DateTools.DateToString(productDO.getExpTo())+"</td>");
		out.write("<td>"+productDO.getDescription()+"</td>");
		out.write("</tr>");
	}
	
	private void printTableFoot(PrintWriter out,int count) throws Exception{
		out.write("</table><div>共"+count+"条数据</div>");
	}
	
	private String getBrandName(ProductDO productDO){
		if(productDO == null){
			return "";
		}
		BrandDO brand = brandManager.getById(productDO.getBrandId());
		if(brand == null){
			return "";
		}
		return brand.getName();
	}
	
	private String getCategoryName(ProductDO productDO){
		if(productDO == null){
			return "";
		}
		CategoryDO categoryDO = categoryManager.getById(productDO.getCategoryId());
		if(categoryDO == null){
			return "";
		}
		return categoryDO.getName();
	}
}
