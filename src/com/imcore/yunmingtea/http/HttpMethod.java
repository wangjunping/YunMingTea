package com.imcore.yunmingtea.http;

public interface HttpMethod {
	
	/**
	 * ��ʾGET����
	 */
    public static final int GET = 0x00;
    
    /**
	 * ��ʾPOST����
	 */
    public static final int POST = 0x01;
    
    /**
	 * ��ʾPUT����
	 */
    public static final int PUT = 0x02;
    
    /**
     * ��ʾDELETE����
     */
    public static final int DELETE = 0x03;
    
    /**
     * ��ʾHEAD����
     */
    public static final int HEAD = 0x04;
}
