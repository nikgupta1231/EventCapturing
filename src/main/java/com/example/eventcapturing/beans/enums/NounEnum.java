package com.example.eventcapturing.beans.enums;

public enum NounEnum {
	BILL("bill"), FDBK("fdbk");

	private String value;

	private NounEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
