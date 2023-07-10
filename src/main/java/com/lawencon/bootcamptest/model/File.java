package com.lawencon.bootcamptest.model;

public class File extends BaseModel{
	private String ext;
	private String file;
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
}
