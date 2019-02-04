package com.bailiwick.game_servicei.model;

import java.util.ArrayList;
import java.util.List;

public class WinnerResponse {
	private  String packageAmount;
	  private  String priceAmount;
	  private List<WinnerDetail> lstPlayerDetail = new ArrayList();
	public String getPackageAmount() {
		return packageAmount;
	}
	public void setPackageAmount(String packageAmount) {
		this.packageAmount = packageAmount;
	}
	public String getPriceAmount() {
		return priceAmount;
	}
	public void setPriceAmount(String priceAmount) {
		this.priceAmount = priceAmount;
	}
	public List<WinnerDetail> getLstPlayerDetail() {
		return lstPlayerDetail;
	}
	public void setLstPlayerDetail(List<WinnerDetail> lstPlayerDetail) {
		this.lstPlayerDetail = lstPlayerDetail;
	}
	  
	  
	  
}
