package com.pg.web.admin.model.form;

import java.io.Serializable;

public class NewsFO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 559486951700328388L;
	private Long id;
	private String news;
	private String type;
	private String top;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
}
