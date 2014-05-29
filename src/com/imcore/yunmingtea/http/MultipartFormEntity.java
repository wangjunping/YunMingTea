package com.imcore.yunmingtea.http;

import java.io.File;

/**
 * ���ϱ�ʵ��
 * 
 * @author Li Bin
 */
public class MultipartFormEntity extends RequestEntity {

	private String fileFieldName;
	private File fileField;

	/**
	 * ���캯��
	 */
	public MultipartFormEntity() {
	}

	/**
	 * ���캯��
	 * 
	 * @param url ָ���������ַ 
	 */
	public MultipartFormEntity(String url) {
		super(url);
	}

	/**
	 * ����ļ��������
	 * 
	 * @return
	 */
	public String getFileFieldName() {
		return fileFieldName;
	}

	/**
	 * �����ļ��������
	 * 
	 * @param fileFieldName
	 */
	public void setFileFieldName(String fileFieldName) {
		this.fileFieldName = fileFieldName;
	}

	/**
	 * ����ļ���
	 * 
	 * @return
	 */
	public File getFileField() {
		return fileField;
	}

	/**
	 * �����ļ���
	 * 
	 * @param fileField
	 */
	public void setFileField(File fileField) {
		this.fileField = fileField;
	}

}
