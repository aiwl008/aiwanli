package com.aiwl.test;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 808协议900指令中gps信息对象
 * @author aiwl
 * @version V1.0
 * @Date 2016年9月30日
 */
public class DriveGpsInfo implements Serializable{
	private static final long serialVersionUID = -7828629241650146492L;
	/**
	 * 车辆信息id
	 */
	private String vehicle_id;
	/***
	 * GPS速度
	 */
	private double GPSSpeed; 
	/****
	 * 经度
	 */
	private double Longitude; 
	/****
	 * 纬度
	 */
	private double Latitude ;;
	/****
	 * 司机ID
	 */
	private int iDriverID; ;
	/****
	 * 油量
	 */
	private float iAD ;
	/**
	 * 行驶报告
	 */
	private String driveReport;
	/***
	 * 上报时间
	 */
	private String reciveDate;



	public double getGPSSpeed() {
		return GPSSpeed;
	}



	public void setGPSSpeed(double gPSSpeed) {
		GPSSpeed = gPSSpeed;
	}



	public double getLongitude() {
		return Longitude;
	}



	public void setLongitude(double longitude) {
		Longitude = longitude;
	}



	public double getLatitude() {
		return Latitude;
	}



	public void setLatitude(double latitude) {
		Latitude = latitude;
	}



	public int getiDriverID() {
		return iDriverID;
	}



	public void setiDriverID(int iDriverID) {
		this.iDriverID = iDriverID;
	}



	public float getiAD() {
		return iAD;
	}



	public void setiAD(float iAD) {
		this.iAD = iAD;
	}



	public String getDriveReport() {
		return driveReport;
	}



	public void setDriveReport(String driveReport) {
		this.driveReport = driveReport;
	}



	public String getReciveDate() {
		return reciveDate;
	}



	public void setReciveDate(String reciveDate) {
		this.reciveDate = reciveDate;
	}



	public String getVehicle_id() {
		return vehicle_id;
	}



	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String toHBaseString() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat decFormat = new DecimalFormat("#0.000000");
		StringBuilder builder = new StringBuilder();
		Date date =dateFormat.parse(this.getReciveDate());
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return builder.append(this.getVehicle_id()).append("|") // 车辆ID
				.append(this.getGPSSpeed()).append("|")// 速度
				.append(decFormat.format(this.getLongitude())).append("|")// 加密后经度
				.append(decFormat.format(this.getLatitude())).append("|")// 加密后纬度
				.append(this.getiDriverID()).append("|")// 司机ID
				.append(this.getiAD()).append("|")// 油量
				.append(dateFormat.format(date)).append("|").toString();// 时间
	}

	@Override
	public String toString() {
		return "DriveGpsInfo [vehicle_id = "+vehicle_id+", GPSSpeed=" + GPSSpeed
				+ ", Longitude=" + Longitude + ", Latitude=" + Latitude
				+ ", iDriverID=" + iDriverID + ", iAD=" + iAD + ", driveReport=" + driveReport +", reciveDate=" + reciveDate+ "]";
	}
}