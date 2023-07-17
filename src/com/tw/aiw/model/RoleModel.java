package com.tw.aiw.model;

public class RoleModel {
	private String EntityNumber;//col3
	private String EntityParent;//30
	private String EntityName;//4
	private String PhysicalAssetStatus;//R17
	private String PlannedChangesToRole;//21
	private String InProjectScope;//5
	
	
	public RoleModel() {
		EntityNumber = "";
		EntityParent = "";
		EntityName = "";
		PhysicalAssetStatus = "";
		PlannedChangesToRole = "";
		InProjectScope = "";
		
	}
	
	public RoleModel(String entityNumber, String entityParent, String entityName, String physicalAssetStatus,
			String plannedChangesToRole, String inProjectScope) {
		super();
		EntityNumber = entityNumber;
		EntityParent = entityParent;
		EntityName = entityName;
		PhysicalAssetStatus = physicalAssetStatus;
		PlannedChangesToRole = plannedChangesToRole;
		InProjectScope = inProjectScope;
	}
	
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
