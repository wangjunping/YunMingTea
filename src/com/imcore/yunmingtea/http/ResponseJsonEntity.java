package com.imcore.yunmingtea.http;

import com.imcore.yunmingtea.util.JsonUtil;

/**
 * JSON��ʽhttp��Ӧʵ��ṹ���壬���պ�̨�ӿ�˵�������ֶ���Ϣ��װΪ�����ʵ��
 * 
 * @author Li Bin
 */
public class ResponseJsonEntity {
	// ��Ӧ״̬��200��ʾ�ɹ�
	private int status;

	// ��Ӧ���ʵ���json�ַ���ʽ
	private String data;

	// ��status��Ϊ200ʱ����������
	private String message;

	// ˽�й��캯����⿪����Աֱ�ӹ���ʵ��
	private ResponseJsonEntity() {
	}

	/**
	 * ��ݸ��json�ַ������ʵ���json�е���ݽṹӳ�䵽��ʵ����
	 * 
	 * @param json
	 * @return
	 */
	public static ResponseJsonEntity fromJSON(String json) {
		ResponseJsonEntity entity = new ResponseJsonEntity();
		entity.status = Integer.parseInt(JsonUtil.getJsonValueByKey(json,
				"status"));
		if (entity.status == 200) {
			entity.data = JsonUtil.getJsonValueByKey(json, "data");
		} else {
			entity.message = JsonUtil.getJsonValueByKey(json, "message");
		}

		return entity;
	}

	public int getStatus() {
		return this.status;
	}

	public String getData() {
		return this.data;
	}

	public String getMessage() {
		return this.message;
	}
}
