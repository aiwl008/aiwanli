package com.aiwl.test;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/***
 * 808协议900指令中的脉冲量
 * @author 1
 *
 */
public class DrivePulseInfo implements Serializable{

	private static final long serialVersionUID = -7829629241650146492L;
	/***
	 * K值
	 */
	private int KValue;

	/***
	 * 总里程
	 */
	private double Mileage;
	/***
	 * 燃油累计使用量
	 */
	private double OilCost;
	/***
	 * CAN总里程
	 */
	private double CanMileage;
	/***
	 * GPS总里程
	 */
	private double GPSMileage;

	/***
	 * 脉冲总里程
	 */
	private double PulseMileage;

	/**
	 * 行驶报告
	 */
	private String driveReport;
	/**
	 * 车辆信息id
	 */
	private String vehicle_id;
	/***
	 * 接收数据时间
	 */
	private String reciveDate;

	public String getDriveReport() {
		return driveReport;
	}

	public void setDriveReport(String driveReport) {
		this.driveReport = driveReport;
	}


	public int getKValue() {
		return KValue;
	}

	public void setKValue(int kValue) {
		KValue = kValue;
	}

	public double getMileage() {
		return Mileage;
	}

	public void setMileage(double mileage) {
		Mileage = mileage;
	}

	public double getOilCost() {
		return OilCost;
	}

	public void setOilCost(double oilCost) {
		OilCost = oilCost;
	}

	public double getCanMileage() {
		return CanMileage;
	}

	public void setCanMileage(double canMileage) {
		CanMileage = canMileage;
	}

	public double getGPSMileage() {
		return GPSMileage;
	}

	public void setGPSMileage(double gPSMileage) {
		GPSMileage = gPSMileage;
	}

	public double getPulseMileage() {
		return PulseMileage;
	}

	public void setPulseMileage(double pulseMileage) {
		PulseMileage = pulseMileage;
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
		StringBuilder builder = new StringBuilder();
		Date date =dateFormat.parse(this.getReciveDate());
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return builder.append(this.getVehicle_id()).append("|") // 车辆ID
				.append(dateFormat.format(date)).append("|")// 时间
				.append(this.getMileage()).append("|")// 总里程
				.append(this.getOilCost()).append("|")// 燃油累计使用量
				.append(this.getCanMileage()).append("|")// CAN总里程
				.append(this.getGPSMileage()).append("|")//GPS总里程
				.append(this.getPulseMileage()).append("|").toString();// 脉冲总里程
	
		
	}
	
	@Override
	public String toString() {
		return "DrivePulseInfo [vehicle_id = "+vehicle_id+", Mileage=" + Mileage
				+ ", OilCost=" + OilCost + ", CanMileage=" + CanMileage
				+ ", GPSMileage=" + GPSMileage + ", PulseMileage=" + PulseMileage + ", driveReport=" + driveReport +", reciveDate=" + reciveDate+ ", reciveDate="+reciveDate+"]";
	}

}
