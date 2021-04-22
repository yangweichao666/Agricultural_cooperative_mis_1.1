package com.ywc.agric.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * table name:  t_market
 * author name: ywc
 * create time: 2021-04-21 16:24:20
 */ 
public class Market implements Serializable {

	private Integer id;//
	private String title;//标题
	private String outline;//内容概要
	private Date date;//时间
	private String img;//封面
	private Integer itemId;//种类
	private String context;//页面
	private  Integer messageNumber;//消息数
    private List<Message> messages;


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
	public void setOutline(String outline){
		this.outline=outline;
	}
	public String getOutline(){
		return outline;
	}
	public void setContext(String context){
		this.context=context;
	}
	public String getContext(){
		return context;
	}
	public void setDate(Date date){
		this.date=date;
	}
	public Date getDate(){
		return date;
	}
	public void setImg(String img){
		this.img=img;
	}
	public String getImg(){
		return img;
	}
	public void setItemId(Integer itemId){
		this.itemId=itemId;
	}
	public Integer getItemId(){
		return itemId;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Integer getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(Integer messageNumber) {
		this.messageNumber = messageNumber;
	}
}

