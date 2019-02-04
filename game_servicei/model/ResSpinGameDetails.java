package com.bailiwick.game_servicei.model;

import java.util.List;

import com.bailiwick.game_service.enums.CostType;

public class ResSpinGameDetails {
	private List<SpinBlocks> spinBlocks;
	private CostType costType;
	private List<ResPacksDetails> resPacksDetails;

	public List<SpinBlocks> getSpinBlocks() {
		return spinBlocks;
	}
	public void setSpinBlocks(List<SpinBlocks> spinBlocks) {
		this.spinBlocks = spinBlocks;
	}
	
	public CostType getCostType() {
		return costType;
	}
	public void setCostType(CostType costType) {
		this.costType = costType;
	}
	public List<ResPacksDetails> getResPacksDetails() {
		return resPacksDetails;
	}
	public void setResPacksDetails(List<ResPacksDetails> resPacksDetails) {
		this.resPacksDetails = resPacksDetails;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResSpinGameDetails [spinBlocks=");
		builder.append(spinBlocks);
		builder.append(", costType=");
		builder.append(costType);
		builder.append(", resPacksDetails=");
		builder.append(resPacksDetails);
		builder.append("]");
		return builder.toString();
	}
}
