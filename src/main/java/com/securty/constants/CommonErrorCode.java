package com.securty.constants;

public class CommonErrorCode {

	public static final ErrorCode REFRESH_TOKEN_CAN_NOT_BE_EMPTY=new ErrorCode(404, "Refresh token can not be empty");
	public static final ErrorCode INVALID_REFRESH_TOKEN=new ErrorCode(404, "Invalid refresh token");
	public static final ErrorCode INVALID_USER=new ErrorCode(404, "Invalid user");


}
