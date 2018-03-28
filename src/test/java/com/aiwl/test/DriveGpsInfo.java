package com.aiwl.test;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 808Э��900ָ����gps��Ϣ����
 * @author aiwl
 * @version V1.0
 * @Date 2016��9��30��
 */
public class DriveGpsInfo implements Serializable{
	private static final long serialVersionUID = -7828629241650146492L;
	/**
	 * ������Ϣid
	 */
	private String vehicle_id;
	/***
	 * GPS�ٶ�
	 */
	private double GPSSpeed; 
	/****
	 * ����
	 */
	private double Longitude; 
	/****
	 * γ��
	 */
	private double Latitude ;;
	/****
	 * ˾��ID
	 */
	private int iDriverID; ;
	/****
	 * ����
	 */
	private float iAD ;
	/**
	 * ��ʻ����
	 */
	private String driveReport;
	/***
	 * �ϱ�ʱ��
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
		return builder.append(this.getVehicle_id()).append("|") // ����ID
				.append(this.getGPSSpeed()).append("|")// �ٶ�
				.append(decFormat.format(this.getLongitude())).append("|")// ���ܺ󾭶�
				.append(decFormat.format(this.getLatitude())).append("|")// ���ܺ�γ��
				.append(this.getiDriverID()).append("|")// ˾��ID
				.append(this.getiAD()).append("|")// ����
				.append(dateFormat.format(date)).append("|").toString();// ʱ��
	}

	@Override
	public String toString() {
		return "DriveGpsInfo [vehicle_id = "+vehicle_id+", GPSSpeed=" + GPSSpeed
				+ ", Longitude=" + Longitude + ", Latitude=" + Latitude
				+ ", iDriverID=" + iDriverID + ", iAD=" + iAD + ", driveReport=" + driveReport +", reciveDate=" + reciveDate+ "]";
	}
}