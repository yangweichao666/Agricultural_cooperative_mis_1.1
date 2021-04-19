package com.ywc.agric.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * table name:  news
 * author name: ywc
 * create time: 2021-04-16 15:56:47
 */ 
public class News implements Serializable {

	private Integer id;//id
	private String title;//标题
	private String context;//内容
	private String img;//封面
	private Date date;//时间

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setContext(String context){
		this.context=context;
	}
	public String getContext(){
		return context;
	}
	public void setImg(String img){
		this.img=img;
	}
	public String getImg(){
		return img;
	}
	public void setDate(Date date){
		this.date=date;
	}
	public Date getDate(){
		return date;
	}
}

