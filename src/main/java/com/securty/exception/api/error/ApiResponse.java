package com.securty.exception.api.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ApiResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Boolean success;

	public ApiResponse() {
	}

	public ApiResponse(boolean success) {
		this.success = success;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
