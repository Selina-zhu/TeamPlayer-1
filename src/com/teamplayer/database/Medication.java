package com.teamplayer.database;

import java.sql.Time;
import java.util.Date;

import  com.selina.teamplayer.MedicationDBAdapter;



public class Medication {
	
	private int Id;
	private String medName;
	private String medInstrction;
	private String remainder;//sqllite wont support boolean 1- true, 0- false
	private String startDate;
	private String alertTime;
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getAlertTime() {
		return alertTime;
	}
	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}
	public String getMedName() {
		return medName;
	}
	public void setMedName(String medName) {
		this.medName = medName;
	}
	public String getMedInstrction() {
		return medInstrction;
	}
	public void setMedInstrction(String medInstrction) {
		this.medInstrction = medInstrction;
	}
	public String getRemainder() {
		return remainder;
	}
	public void setRemainder(String remaind) {
		this.remainder = remaind;
	}
	
}
