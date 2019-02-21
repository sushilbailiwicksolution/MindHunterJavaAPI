/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bailiwick.game_servicei.model;

/**
 *
 * @author Admin
 */
public class SupportQueryDetails {
    
    private String name;
    private String mobileNo;
    private String email;
    private String contestName;
    private String query;
    private String userId;
    

    @Override
    public String toString() {
        return "SupportQueryDetails{" + "name=" + name + ", mobileNo=" + mobileNo + ", email=" + email + ", contestName=" + contestName + ", query=" + query + '}';
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    
    
}
