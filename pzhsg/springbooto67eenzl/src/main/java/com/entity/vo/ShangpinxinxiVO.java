package com.entity.vo;

import com.entity.ShangpinxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 水果信息
 * @author 
 * @email 
 * @date 2024-03-07 14:29:29
 */
public class ShangpinxinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 水果类型
	 */
	
	private String shangpinleixing;
		
	/**
	 * 品牌
	 */
	
	private String pinpai;
		
	/**
	 * 规格
	 */
	
	private String guige;
		
	/**
	 * 生产厂家
	 */
	
	private String shengchanchangjia;
		
	/**
	 * 生产日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date shengchanriqi;
		
	/**
	 * 水果介绍
	 */
	
	private String shangpinjieshao;
		
	/**
	 * 水果图片
	 */
	
	private String shangpintupian;
		
	/**
	 * 店铺名称
	 */
	
	private String dianpumingcheng;
		
	/**
	 * 单限
	 */
	
	private Integer onelimittimes;
		
	/**
	 * 库存
	 */
	
	private Integer alllimittimes;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 价格
	 */
	
	private Double price;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：水果类型
	 */
	 
	public void setShangpinleixing(String shangpinleixing) {
		this.shangpinleixing = shangpinleixing;
	}
	
	/**
	 * 获取：水果类型
	 */
	public String getShangpinleixing() {
		return shangpinleixing;
	}
				
	
	/**
	 * 设置：品牌
	 */
	 
	public void setPinpai(String pinpai) {
		this.pinpai = pinpai;
	}
	
	/**
	 * 获取：品牌
	 */
	public String getPinpai() {
		return pinpai;
	}
				
	
	/**
	 * 设置：规格
	 */
	 
	public void setGuige(String guige) {
		this.guige = guige;
	}
	
	/**
	 * 获取：规格
	 */
	public String getGuige() {
		return guige;
	}
				
	
	/**
	 * 设置：生产厂家
	 */
	 
	public void setShengchanchangjia(String shengchanchangjia) {
		this.shengchanchangjia = shengchanchangjia;
	}
	
	/**
	 * 获取：生产厂家
	 */
	public String getShengchanchangjia() {
		return shengchanchangjia;
	}
				
	
	/**
	 * 设置：生产日期
	 */
	 
	public void setShengchanriqi(Date shengchanriqi) {
		this.shengchanriqi = shengchanriqi;
	}
	
	/**
	 * 获取：生产日期
	 */
	public Date getShengchanriqi() {
		return shengchanriqi;
	}
				
	
	/**
	 * 设置：水果介绍
	 */
	 
	public void setShangpinjieshao(String shangpinjieshao) {
		this.shangpinjieshao = shangpinjieshao;
	}
	
	/**
	 * 获取：水果介绍
	 */
	public String getShangpinjieshao() {
		return shangpinjieshao;
	}
				
	
	/**
	 * 设置：水果图片
	 */
	 
	public void setShangpintupian(String shangpintupian) {
		this.shangpintupian = shangpintupian;
	}
	
	/**
	 * 获取：水果图片
	 */
	public String getShangpintupian() {
		return shangpintupian;
	}
				
	
	/**
	 * 设置：店铺名称
	 */
	 
	public void setDianpumingcheng(String dianpumingcheng) {
		this.dianpumingcheng = dianpumingcheng;
	}
	
	/**
	 * 获取：店铺名称
	 */
	public String getDianpumingcheng() {
		return dianpumingcheng;
	}
				
	
	/**
	 * 设置：单限
	 */
	 
	public void setOnelimittimes(Integer onelimittimes) {
		this.onelimittimes = onelimittimes;
	}
	
	/**
	 * 获取：单限
	 */
	public Integer getOnelimittimes() {
		return onelimittimes;
	}
				
	
	/**
	 * 设置：库存
	 */
	 
	public void setAlllimittimes(Integer alllimittimes) {
		this.alllimittimes = alllimittimes;
	}
	
	/**
	 * 获取：库存
	 */
	public Integer getAlllimittimes() {
		return alllimittimes;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：价格
	 */
	 
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 获取：价格
	 */
	public Double getPrice() {
		return price;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
