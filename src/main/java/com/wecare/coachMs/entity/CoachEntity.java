package com.wecare.coachMs.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Coach")
public class CoachEntity {

	@Id
	@GeneratedValue(generator = "coachId")
	@GenericGenerator(name = "coachId", strategy = "com.wecare.coachMs.entity.idgenerator.CoachIdGenerator")
	private String coachId;

	private String name;
	private String password;
	private char gender;
	private LocalDate dateOfBirth;

	private Long mobileNumber;
	private String speciality;

	public String getCoachId() {
		return coachId;
	}

	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "CoachEntity [coachId=" + coachId + ", name=" + name + ", password=" + password + ", gender=" + gender
				+ ", dateOfbirth=" + dateOfBirth + ", mobileNumber=" + mobileNumber + ", speciality=" + speciality
				+ "]";
	}

}
