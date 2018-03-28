package com.aiwl.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Distancebylonlat {
	
	  private static double EARTH_RADIUS = 6378.137;  
	  
	    private static double rad(double d) {  
	        return d * Math.PI / 180.0;  
	    }  
	  
	    /** 
	     * ͨ����γ�Ȼ�ȡ����(��λ������) 
	     * @param lat1 ���γ��
	     * @param lng1 ��㾭��
	     * @param lat2 �յ�γ��
	     * @param lng2 �յ㾭��
	     * @return 
	     */  
	    public static double getDistance(String strlat1, String strlng1, String strlat2,  
	    		String strlng2) {  
	    	Double lat1 = Double.parseDouble(strlat1);
	    	Double lng1= Double.parseDouble(strlng1);
	    	Double lat2 = Double.parseDouble(strlat2);
	    	Double lng2 = Double.parseDouble(strlng2);
	    	
	        double radLat1 = rad(lat1);  
	        double radLat2 = rad(lat2);  
	        double a = radLat1 - radLat2;  
	        double b = rad(lng1) - rad(lng2);  
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
	                + Math.cos(radLat1) * Math.cos(radLat2)  
	                * Math.pow(Math.sin(b / 2), 2)));  
	        s = s * EARTH_RADIUS;  
	        s = Math.round(s * 10000d) / 10000d;  
//	        s = s*1000;  
	        return s;  
	    }  
	    
	    /**
	     * ��������
	     */
	    private static String getDatetime(String time){
	    	String date = time.substring(0,4)+time.substring(5,7)+time.substring(8,10);
	    	String strtime = time.substring(11,13)+time.substring(14,16)+time.substring(17);
	    	return date+strtime;
	    }
	    
	    
	    /** 
	     * ͨ����γ�Ȼ�ȡ����(��λ������) 
	     * @param lat1 ���γ��
	     * @param lng1 ��㾭��
	     * @param lat2 �յ�γ��
	     * @param lng2 �յ㾭��
	     * @return 
	     */  
	    public static double getDistance(double lat1, double lng1, double lat2,  
	    		double lng2) {  

	        double radLat1 = rad(lat1);  
	        double radLat2 = rad(lat2);  
	        double a = radLat1 - radLat2;  
	        double b = rad(lng1) - rad(lng2);  
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
	                + Math.cos(radLat1) * Math.cos(radLat2)  
	                * Math.pow(Math.sin(b / 2), 2)));  
	        s = s * EARTH_RADIUS;  
	        s = Math.round(s * 10000d) / 10000d;  
//	        s = s*1000;  
	        return s;  
	    }  
	

	    /**
	     * ���ݾ�γ�ȼ���GPS���
	     * @param List<DriveGpsInfo>
	     * @return String distance   vid|datetime|distance
	     */
	    public static String calGPSdistance(List<DriveGpsInfo> drivegpsInfo){
	    	
	    	DriveGpsInfo dgibase = null;
	    	DriveGpsInfo dgisecond = null;
	    	String datetime = getDatetime(drivegpsInfo.get(0).getReciveDate()); //ȡ�������ĵ�ʱ�� ԭʱ��Ϊ  yyyy-MM-dd HH:mm:ss
	    	String vid = drivegpsInfo.get(0).getVehicle_id();
	    	BigDecimal base = new BigDecimal(0.0);
	    	
	    	
	    	for(int dgicnt=0; dgicnt < drivegpsInfo.size()-1; dgicnt++){    //ѭ��ȡgps��Ϣ
	    		dgibase = drivegpsInfo.get(dgicnt);                //����GPS���ݵľ�γ������ȡ��������
	    		dgisecond = drivegpsInfo.get(dgicnt+1);      //����GPS���ݵľ�γ������ȡ��������
	    		
	    		double baselat = dgibase.getLatitude();
	    		double baselon = dgibase.getLongitude();
//	    		System.out.println("��γ��1��"+baselon+"|"+baselat);
	    		double sndlat = dgisecond.getLatitude();
	    		double sndlon = dgisecond.getLongitude();
//	    		System.out.println("��γ��2��"+sndlon+"|"+sndlat);
	    		
	    		//��ȡ��������
	    		if(baselat == sndlat && baselon == sndlon){
//	    			System.out.println("��ͬ");
	    			continue;
	    		}else{
	    		   double dis = getDistance(dgibase.getLatitude(), dgibase.getLongitude(), dgisecond.getLatitude(), dgisecond.getLongitude());    		
//	    		  System.out.println("��������:"+dis);
	    		   BigDecimal bdis = new BigDecimal(dis);
	    		   //�ۼ��������
	    		   base = base.add(bdis);	    
	    		   base = base.setScale(2, BigDecimal.ROUND_HALF_UP);
//	    		   System.out.println("�ۼӾ���:"+base);
	    		}
	    	}
	    	
	    	
	    	StringBuffer sbuffer = new StringBuffer();
	    	sbuffer.append(vid).append("|");
	    	sbuffer.append(datetime).append("|");
	    	sbuffer.append(base);

	    	return sbuffer.toString();
	    }
	    
	    /**
	     * ����FF10�е��������е������
	     * @param List<DrivePulseInfo>
	     * @return String distance   vid|datetime|distance|oil
	     */
	    public static String calPulsedistance(List<DrivePulseInfo> drivePulseInfo){
	    	
	    	DrivePulseInfo dpl1 = drivePulseInfo.get(0); //ȡ��һ������
	    	DrivePulseInfo dpl2 = drivePulseInfo.get(drivePulseInfo.size()-1); //ȡ�ڶ�������
	    	
	    	String vid = dpl1.getVehicle_id();
	    	String datetime = getDatetime(dpl1.getReciveDate());
	    	
	    	BigDecimal dis1 = new BigDecimal(dpl1.getMileage());
	    	BigDecimal oil1 = new BigDecimal(dpl1.getOilCost());
	    	BigDecimal dis2 = new BigDecimal(dpl2.getMileage());
	    	BigDecimal oil2 = new BigDecimal(dpl2.getOilCost());
	        	
	        double dis =  dis1.subtract(dis2).abs().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	        double oil =  oil1.subtract(oil2).abs().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	        
	        StringBuffer sbuffer = new StringBuffer();
	        sbuffer.append(vid).append("|");
	        sbuffer.append(datetime).append("|");
	        sbuffer.append(dis).append("|");
	        sbuffer.append(oil);
	        
	    	return sbuffer.toString();
	    }
	    
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		Distancebylonlat dis = new Distancebylonlat();
		String testtime = "2016-09-30 09:45:23";
		String vid = "421";
		double lat = 40.054401;
		double lon = 118.725014;
		List lt = new ArrayList();
		
		for(int i=0; i < 4; i++){
		  DrivePulseInfo dgi = new DrivePulseInfo();
		  dgi.setVehicle_id(vid);
		  dgi.setReciveDate(testtime);
		  int n = 1+i;
		  System.out.println("���룺"+n);
		  int t = 2+i;
		  System.out.println("�ͺģ�"+t);
          dgi.setMileage(n);
          dgi.setOilCost(t);
		  lt.add(dgi);
		}
		String str = dis.calPulsedistance(lt);
		System.out.println("�����ͺģ�"+str);
		
//		  DriveGpsInfo dgi2 = new DriveGpsInfo();
//		  dgi2.setVehicle_id(vid);
//		  dgi2.setReciveDate(testtime);
//		  lat = 90.054401;
//		  lon = 138.725014;
//		  dgi2.setLatitude(lat);
//		  dgi2.setLongitude(lon);
//		  lt.add(dgi2);
//		  
//		  DriveGpsInfo dgi3 = new DriveGpsInfo();
//		  dgi3.setVehicle_id(vid);
//		  dgi3.setReciveDate(testtime);
//		  lat = 90.054401;
//		  lon = 148.725014;
//		  dgi3.setLatitude(lat);
//		  dgi3.setLongitude(lon);
//		  lt.add(dgi3);
//		
//		String s = dis.calGPSdistance(lt);
//		
//		System.out.println("����ֵ��"+s);
//		System.out.println(dis.getDatetime(testtime));
//		BigDecimal base = new BigDecimal(1.0);
//		BigDecimal bases = new BigDecimal(2.0);
//		
//		Double result = new Double(base.add(bases).doubleValue());
//		
//		System.out.println(result);
//		

//		System.out.println(dis.getDistance("41.054401","119.725014","40.054401","118.725014"));		
		
		
	}

}
