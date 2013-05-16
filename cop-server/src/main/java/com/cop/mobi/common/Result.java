package com.cop.mobi.common;

/**
 * 
 * @author chris.liu
 * 
 */
public class Result {
	private ResultStatus status;
	private byte[] data;

	public Result(ResultStatus status, Object data) {
		try {
			this.status = status;
			this.data = data.toString().getBytes();
		} catch (Exception e) {
			this.status = status;
			this.data = new Message("error", "server data format error")
					.toString().getBytes();
		}
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("{\"status\":%d,\"data\":%s}", status.getStatus(),
				new String(data));

	}

	public static enum ResultStatus {
		RS_OK(200), RS_FAIL(400), RS_EXPIRED(401), RS_ERROR(500);

		private int status;

		ResultStatus(int status) {
			this.status = status;
		}

		public int getStatus() {
			return status;
		}
	}
}
