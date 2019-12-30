package com.example.eventcapturing.beans;

public class Properties {
	private String bank;
	private int merchantid;
	private float value;
	private String mode;
	private String text;

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(int merchantid) {
		this.merchantid = merchantid;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Properties(String bank, int merchantid, float value, String mode, String text) {
		super();
		this.bank = bank;
		this.merchantid = merchantid;
		this.value = value;
		this.mode = mode;
		this.text = text;
	}

	public Properties() {
		super();
	}

	@Override
	public String toString() {
		return "Properties [bank=" + bank + ", merchantid=" + merchantid + ", value=" + value + ", mode=" + mode
				+ ", text=" + text + "]";
	}

}
