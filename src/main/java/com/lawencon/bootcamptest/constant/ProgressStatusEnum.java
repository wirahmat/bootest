package com.lawencon.bootcamptest.constant;

public enum ProgressStatusEnum {
	ONPROGRESS("On Progress", "ONP"), SUBMITTED("Submitted", "SUBM");
	
	final public String status;
	final public String progressCode;
	
	ProgressStatusEnum(String status, String progressCode){
		this.status = status;
		this.progressCode = progressCode;
	}
}
