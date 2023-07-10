package com.lawencon.bootcamptest.constant;

public enum QuestionTypeEnum {
	ESSAY("Essay", "ESSA"), MULTIOPTION("Pilihan Ganda", "PG");
	
	final public String typeName;
	final public String typeCode;
	
	QuestionTypeEnum(String typeName, String typeCode){
		this.typeName = typeName;
		this.typeCode = typeCode;
	}
}
