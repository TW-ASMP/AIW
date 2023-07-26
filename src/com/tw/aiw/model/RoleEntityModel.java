package com.tw.aiw.model;

public class RoleEntityModel {
	private String EntityNumber;//col3
	private String EntityParent;//30
	private String EntityName;//4
	private String PhysicalAssetStatus;//R17
	private String PlannedChangesToRole;//21
	private String InProjectScope;//5
	
	
	public RoleEntityModel() {
		EntityNumber = "";
		EntityParent = "";
		EntityName = "";
		PhysicalAssetStatus = "";
		PlannedChangesToRole = "";
		InProjectScope = "";
		
	}
	
	public RoleEntityModel(String entityNumber, String entityParent, String entityName, String physicalAssetStatus,
			String plannedChangesToRole, String inProjectScope) {
		super();
		EntityNumber = entityNumber;
		EntityParent = entityParent;
		EntityName = entityName;
		PhysicalAssetStatus = physicalAssetStatus;
		PlannedChangesToRole = plannedChangesToRole;
		InProjectScope = inProjectScope;
	}

	public String[] getByColumns() {
		String [] columns = new String[6];
		columns[0] = this.EntityNumber;
		columns[1] = this.EntityName;
		columns[2] = this.PhysicalAssetStatus;
		columns[3] = this.PlannedChangesToRole;
		columns[4] = this.InProjectScope;
		columns[5] = this.EntityParent;
		
		return columns;
	}
	//setters and getters
	public String getEntityNumber() {
		return EntityNumber;
	}
	public void setEntityNumber(String entityNumber) {
		EntityNumber = entityNumber;
	}
	public String getEntityParent() {
		return EntityParent;
	}
	public void setEntityParent(String entityParent) {
		EntityParent = entityParent;
	}
	public String getEntityName() {
		return EntityName;
	}
	public void setEntityName(String entityName) {
		EntityName = entityName;
	}
	public String getPhysicalAssetStatus() {
		return PhysicalAssetStatus;
	}
	public void setPhysicalAssetStatus(String physicalAssetStatus) {
		PhysicalAssetStatus = physicalAssetStatus;
	}
	public String getPlannedChangesToRole() {
		return PlannedChangesToRole;
	}
	public void setPlannedChangesToRole(String plannedChangesToRole) {
		PlannedChangesToRole = plannedChangesToRole;
	}
	public String getInProjectScope() {
		return InProjectScope;
	}
	public void setInProjectScope(String inProjectScope) {
		InProjectScope = inProjectScope;
	}

}
