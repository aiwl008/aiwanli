package com.aiwl.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class UuidUtil {

	  private static long num=0; 
	    
	  /**
	   * 随机生成UUID
	   * @return
	   */
	  public static synchronized String getUUID(){
	    UUID uuid=UUID.randomUUID();
	    String str = uuid.toString(); 
	    String uuidStr=str.replaceAll("-", "");
	    return uuidStr;
	  }
	  /**
	   * 根据字符串生成固定UUID
	   * @param name
	   * @return
	   */
	  public static synchronized String getUUID(String name){
	    UUID uuid=UUID.nameUUIDFromBytes(name.getBytes());
	    String str = uuid.toString(); 
	    String uuidStr=str.replace("-", "");
	    return uuidStr;
	  }
	  /**
	   * 根据日期生成长整型id
	   * @param args
	   */
	  public static synchronized long getLongId(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
		Date today = new Date();
	    String date=format.format(today);
	    
	    if(num>=99) num=0l;
	    ++num;
	    if(num<10) {
	      date=date+00+num;
	    }else{
	      date+=num;
	    }
	    return Long.valueOf(date);
	  }

	public static void main(String[] args) {
		System.out.println(UuidUtil.getUUID());
	}
}