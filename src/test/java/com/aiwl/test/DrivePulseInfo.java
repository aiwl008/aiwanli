package com.aiwl.test;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/***
 * 808Э��900ָ���е�������
 * @author 1
 *
 */
public class DrivePulseInfo implements Serializable{

	private static final long serialVersionUID = -7829629241650146492L;
	/***
	 * Kֵ
	 */
	private int KValue;

	/***
	 * �����
	 */
	private double Mileage;
	/***
	 * ȼ���ۼ�ʹ����
	 */
	private double OilCost;
	/***
	 * CAN�����
	 */
	private double CanMileage;
	/***
	 * GPS�����
	 */
	private double GPSMileage;

	/***
	 * ���������
	 */
	private double PulseMileage;

	/**
	 * ��ʻ����
	 */
	private String driveReport;
	/**
	 * ������Ϣid
	 */
	private String vehicle_id;
	/***
	 * ��������ʱ��
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
		return builder.append(this.getVehicle_id()).append("|") // ����ID
				.append(dateFormat.format(date)).append("|")// ʱ��
				.append(this.getMileage()).append("|")// �����
				.append(this.getOilCost()).append("|")// ȼ���ۼ�ʹ����
				.append(this.getCanMileage()).append("|")// CAN�����
				.append(this.getGPSMileage()).append("|")//GPS�����
				.append(this.getPulseMileage()).append("|").toString();// ���������
	
		
	}
	
	@Override
	public String toString() {
		return "DrivePulseInfo [vehicle_id = "+vehicle_id+", Mileage=" + Mileage
				+ ", OilCost=" + OilCost + ", CanMileage=" + CanMileage
				+ ", GPSMileage=" + GPSMileage + ", PulseMileage=" + PulseMileage + ", driveReport=" + driveReport +", reciveDate=" + reciveDate+ ", reciveDate="+reciveDate+"]";
	}

}
