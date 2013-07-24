package com.cop.argus.demo.common.entity;

public class Result {
	private ResultStatus status;
	private String token;
	private Object data;

	public Result(ResultStatus status, String token, Object data) {
		this.status = status;
		this.token = token;
		this.data = data;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public enum ResultStatus {
		RS_OK(200), RS_FAIL(400), RS_EXPIRED(401), RS_ERROR(500);

		private int statusCode;

		ResultStatus(int statusCode) {
			this.statusCode = statusCode;
		}

		public int statusCode() {
			return statusCode;
		}
	}
}
