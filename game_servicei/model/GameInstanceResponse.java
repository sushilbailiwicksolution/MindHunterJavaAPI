/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bailiwick.game_servicei.model;

import java.util.List;

/**
 *
 * @author Mathur
 */
public class GameInstanceResponse  extends GenericResponse{
    
  //  private List<GameInstanceUsers> res;
    private int instanceId;

//    public List<GameInstanceUsers> getRes() {
//        return res;
//    }
//
//    public void setRes(List<GameInstanceUsers> res) {
//        this.res = res;
//    }

    public int getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return "GameInstanceResponse{" + "instanceId=" + instanceId + '}';
    }

    

    
    
}
