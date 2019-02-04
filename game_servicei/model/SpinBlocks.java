package com.bailiwick.game_servicei.model;

public class SpinBlocks {
	
	private	Integer id;
	private String imgUrl;
	private Integer price;
	private String bgColor;
	private String text;
	private Integer nextAction;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getNextAction() {
		return nextAction;
	}
	public void setNextAction(Integer nextAction) {
		this.nextAction = nextAction;
	}
	
	@Override
	public String toString() {
		return "ResSpinGameDetails [id=" + id + ", imgUrl=" + imgUrl + ", price=" + price + ", nextAction=" + nextAction
				+ ", bgColor=" + bgColor + ", text=" + text + "]";
	}
}
