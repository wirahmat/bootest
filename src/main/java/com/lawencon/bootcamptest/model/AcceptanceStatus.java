package com.lawencon.bootcamptest.model;

public class AcceptanceStatus extends BaseModel{
	private String status;
	private String acceptanceCode;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAcceptanceCode() {
		return acceptanceCode;
	}
	public void setAcceptanceCode(String acceptanceCode) {
		this.acceptanceCode = acceptanceCode;
	}
}
