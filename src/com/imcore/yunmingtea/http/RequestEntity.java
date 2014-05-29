package com.imcore.yunmingtea.http;

import java.util.Map;

/**
 * Http�����������壬���������ַurl,   �Լ����������form����
 * 
 * @author Li Bin
 */
public class RequestEntity {
	private String url;
	private int method = HttpMethod.POST;
	private Map<String, Object> textFields;

	/**
	 * ���캯��
	 */
	public RequestEntity() {
	}

	/**
	 * ���캯��
	 * 
	 * @param url
	 *            ָ�����������ӵ�ַ
	 */
	public RequestEntity(String url) {
		this.url = url;
	}

	/**
	 * ���캯��
	 * 
	 * @param url
	 *            ָ�����������ӵ�ַ
	 * @param textFields
	 *            ������������ı����������ļ���
	 */
	public RequestEntity(String url, Map<String, Object> textFields) {
		this.url = url;
		this.textFields = textFields;
	}

	/**
	 * ���캯��
	 * 
	 * @param url
	 *            ָ�����������ӵ�ַ
	 * @param method
	 *            ָ����HTTP���󷽷�
	 * @param textFields
	 *            ������������ı����������ļ���
	 */
	public RequestEntity(String url, int method, Map<String, Object> textFields) {
		this.url = url;
		this.method = method;
		this.textFields = textFields;
	}

	/**
	 * ��ȡ�����ַurl
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * ���������ַurl
	 * 
	 * @return
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ������󷽷�
	 * 
	 * @return
	 */
	public int getMethod() {
		return method;
	}

	/**
	 * �������󷽷�
	 * 
	 * @param method
	 */
	public void setMethod(int method) {
		this.method = method;
	}

	/**
	 * ��ȡ�������
	 * 
	 * @return
	 */
	public Map<String, Object> getTextFields() {
		return textFields;
	}

	/**
	 * �����������
	 * 
	 * @param textFields
	 */
	public void setTextFields(Map<String, Object> textFields) {
		this.textFields = textFields;
	}

}
