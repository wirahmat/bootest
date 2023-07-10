package com.lawencon.bootcamptest.constant;

public enum RoleEnum {
	CANDIDATE("Candidate", "CAN"), REVIEWER("Reviewer", "REV"), HR("Human Resource", "HR"), SUPERADMIN("Super Admin", "SPA");
	
	final public String roleName;
	final public String roleCode;
	
	RoleEnum(String roleName, String roleCode){
		this.roleName = roleName;
		this.roleCode = roleCode;
	}
}
