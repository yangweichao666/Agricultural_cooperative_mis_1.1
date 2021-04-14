package com.ywc.agric.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * table name:  t_contract
 * author name: ywc
 * create time: 2021-04-14 10:06:54
 */ 
public class Contract implements Serializable {

	private Integer id;//id
	private String name;//合同名字
	private String location;//位置
	private float size;//大小
	private String img;//合同照片
	//一对多的关系
	private Integer memberId;//会员id
	private String remark;//说明
	private String memberName; //名字

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setLocation(String location){
		this.location=location;
	}
	public String getLocation(){
		return location;
	}
	public void setSize(float size){
		this.size=size;
	}
	public float getSize(){
		return size;
	}
	public void setImg(String img){
		this.img=img;
	}
	public String getImg(){
		return img;
	}
	public void setMemberId(Integer memberId){
		this.memberId=memberId;
	}
	public Integer getMemberId(){
		return memberId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}

