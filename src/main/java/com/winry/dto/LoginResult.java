package com.winry.dto;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResult {

	private String status;

	private ErrorMessage message;

	private String ts;

	private LoginData data;

	public boolean success() {
		return StringUtils.equals(status, "true");
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ErrorMessage getMessage() {
		return message;
	}

	public void setMessage(ErrorMessage message) {
		this.message = message;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public LoginData getData() {
		return data;
	}

	public void setData(LoginData data) {
		this.data = data;
	}

	public static class LoginData {

		private String code;

		private String crossDomain;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getCrossDomain() {
			return crossDomain;
		}

		public void setCrossDomain(String crossDomain) {
			this.crossDomain = crossDomain;
		}

	}

	public static class ErrorMessage {

		private String code;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}

}
