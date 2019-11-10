package com.kohinoor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileMessage {
	private String fileName;
	private String inputDirectory;
	private String tempDirectory;
	private String signedFileName;
	private String encryptedFileName;

	private String fileDirection;
	private long timestamp;
	private long signedTimestamp;
	private long uploadedTimestamp;
	private long encryptedTimestamp;
	private long decryptedTimestamp;
	private long verifiedTimestamp;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignedFileName() {
		return signedFileName;
	}

	public void setSignedFileName(String signedFileName) {
		this.signedFileName = signedFileName;
	}

	public String getEncryptedFileName() {
		return encryptedFileName;
	}

	public void setEncryptedFileName(String encryptedFileName) {
		this.encryptedFileName = encryptedFileName;
	}

	public String getFileDirection() {
		return fileDirection;
	}

	public void setFileDirection(String fileDirection) {
		this.fileDirection = fileDirection;
	}

	public long getSignedTimestamp() {
		return signedTimestamp;
	}

	public void setSignedTimestamp(long signedTimestamp) {
		this.signedTimestamp = signedTimestamp;
	}

	public long getEncryptedTimestamp() {
		return encryptedTimestamp;
	}

	public void setEncryptedTimestamp(long encryptedTimestamp) {
		this.encryptedTimestamp = encryptedTimestamp;
	}

	public long getDecryptedTimestamp() {
		return decryptedTimestamp;
	}

	public void setDecryptedTimestamp(long decryptedTimestamp) {
		this.decryptedTimestamp = decryptedTimestamp;
	}

	public long getVerifiedTimestamp() {
		return verifiedTimestamp;
	}

	public void setVerifiedTimestamp(long verifiedTimestamp) {
		this.verifiedTimestamp = verifiedTimestamp;
	}

	public String getInputDirectory() {
		return inputDirectory;
	}

	public void setInputDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
	}

	public String getTempDirectory() {
		return tempDirectory;
	}

	public void setTempDirectory(String tempDirectory) {
		this.tempDirectory = tempDirectory;
	}

	public long getUploadedTimestamp() {
		return uploadedTimestamp;
	}

	public void setUploadedTimestamp(long uploadedTimestamp) {
		this.uploadedTimestamp = uploadedTimestamp;
	}

	@Override
	public String toString() {
		return "FileMessage [" + (fileName != null ? "fileName=" + fileName + ", " : "")
				+ (inputDirectory != null ? "inputDirectory=" + inputDirectory + ", " : "")
				+ (tempDirectory != null ? "tempDirectory=" + tempDirectory + ", " : "")
				+ (signedFileName != null ? "signedFileName=" + signedFileName + ", " : "")
				+ (encryptedFileName != null ? "encryptedFileName=" + encryptedFileName + ", " : "")
				+ (fileDirection != null ? "fileDirection=" + fileDirection + ", " : "") + "timestamp=" + timestamp
				+ ", signedTimestamp=" + signedTimestamp + ", uploadedTimestamp=" + uploadedTimestamp
				+ ", encryptedTimestamp=" + encryptedTimestamp + ", decryptedTimestamp=" + decryptedTimestamp
				+ ", verifiedTimestamp=" + verifiedTimestamp + "]";
	}

}
