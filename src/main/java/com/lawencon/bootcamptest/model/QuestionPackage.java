package com.lawencon.bootcamptest.model;

public class QuestionPackage extends BaseModel {
	private String packageName;
	private String packageCode;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
}
