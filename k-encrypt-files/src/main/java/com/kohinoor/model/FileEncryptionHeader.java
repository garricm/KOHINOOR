package com.kohinoor.model;

public class FileEncryptionHeader {
	private String algo;
	private String symKey;
	private String iv;

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public String getSymKey() {
		return symKey;
	}

	public void setSymKey(String symKey) {
		this.symKey = symKey;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

}
