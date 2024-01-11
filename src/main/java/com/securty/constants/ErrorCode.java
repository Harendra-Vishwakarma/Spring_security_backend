package com.securty.constants;

public class ErrorCode {

	private int code;
	private String description;

	public ErrorCode(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	};

}
