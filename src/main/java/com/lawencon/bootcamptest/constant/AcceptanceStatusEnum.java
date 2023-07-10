package com.lawencon.bootcamptest.constant;

public enum AcceptanceStatusEnum {
	NEEDREVIEW("Need Review", "NR"), REJECTED("Rejected", "REJ"), CONSIDERED("Considered", "CND"), RECOMMENDED("Recommeded", "RCD");
	
	final public String status;
	final public String acceptanceCode;
	
	AcceptanceStatusEnum(String status, String acceptanceCode){
		this.status = status;
		this.acceptanceCode = acceptanceCode;
	}
}
