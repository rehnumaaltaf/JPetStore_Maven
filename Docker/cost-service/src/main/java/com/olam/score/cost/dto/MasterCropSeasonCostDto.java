package com.olam.score.cost.dto;

import java.util.List;

public class MasterCropSeasonCostDto {
	public Integer pkCropSeasonCostId;
	public Integer fkOriginId;
	public String fkOriginName;
	public Integer fkBaseCropSeasonId;
	public String fkBaseCropSeasonName;
	public List<MasterCropSeasonCostPartyAssocDto> masterCropSeasonCostPartyList;
	public int isApplicableToAllPartyInd;
	public int fkProdId;
	public String fkProdName;
	public List<MasterCropSeasonCostUnitAssocDto> masterCropSeasonCostUnitList;
	public int isApplicableToAllUnitInd;
	
}
