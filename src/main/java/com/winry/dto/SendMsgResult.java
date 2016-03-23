package com.winry.dto;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by cong on 2016/3/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMsgResult implements Result {

    private String code;

    private String msg;

	@SuppressWarnings("rawtypes")
	private List data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	@SuppressWarnings("rawtypes")
	public List getData() {
        return data;
    }

	@SuppressWarnings("rawtypes")
	public void setData(List data) {
        this.data = data;
    }

	@Override
	public boolean success() {
		return StringUtils.equals(code, "0");
	}
}
