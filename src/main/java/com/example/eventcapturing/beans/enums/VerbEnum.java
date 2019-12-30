package com.example.eventcapturing.beans.enums;

public enum VerbEnum {
	POST("post"), PAY("pay");

	private String value;

	private VerbEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
