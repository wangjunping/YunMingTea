package com.imcore.yunmingtea.data;


//实体类注意事项：
//1.访问修饰符必须是public
//2.字段名称得跟JSON字符串中相应的key一致，包括大小写
//3。   注意JSON字符串中的Value的类型
public class User {
	
  public String userId;
  public String token;
}
