package com.bailiwick.game_servicei.model;

public class ResGameDetails {
	
	private	Integer id;
	private String title;
	private Integer status;
	private Integer gtype;
	private String gIcon;
	private String startDate;
	private String endDate;
	private String season;
	private Integer gMode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getGtype() {
		return gtype;
	}
	public void setGtype(Integer gtype) {
		this.gtype = gtype;
	}
	public String getgIcon() {
		return gIcon;
	}
	public void setgIcon(String gIcon) {
		this.gIcon = gIcon;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public Integer getgMode() {
		return gMode;
	}
	public void setgMode(Integer gMode) {
		this.gMode = gMode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameDetails [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", status=");
		builder.append(status);
		builder.append(", gtype=");
		builder.append(gtype);
		builder.append(", gIcon=");
		builder.append(gIcon);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", season=");
		builder.append(season);
		builder.append(", gMode=");
		builder.append(gMode);
		builder.append("]");
		return builder.toString();
	}
	
	
}
