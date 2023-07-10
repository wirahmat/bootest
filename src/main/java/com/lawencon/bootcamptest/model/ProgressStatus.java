package com.lawencon.bootcamptest.model;

public class ProgressStatus extends BaseModel{
	private String status;
	private String progressCode;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProgressCode() {
		return progressCode;
	}
	public void setProgressCode(String progressCode) {
		this.progressCode = progressCode;
	}
}
