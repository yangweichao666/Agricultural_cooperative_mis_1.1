package com.ywc.agric.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * table name:  message
 * author name: ywc
 * create time: 2021-04-21 16:30:06
 */ 
public class Message implements Serializable {

	private Integer id;
	private String title;//标题
	private String name;//名字
	private String content;//内容
	private String phone;//手机号
	private String weixin;//微信号
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
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setWeixin(String weixin){
		this.weixin=weixin;
	}
	public String getWeixin(){
		return weixin;
	}
	public void setDate(Date date){
		this.date=date;
	}
	public Date getDate(){
		return date;
	}
}

