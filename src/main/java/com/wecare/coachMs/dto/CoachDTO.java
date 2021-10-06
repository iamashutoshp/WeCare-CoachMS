package com.wecare.coachMs.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CoachDTO {

	private String coachId;
	@NotEmpty(message="{password.empty}")
	@Size(min = 5, max = 10,message="{password.length.invalid}")
	private String password;
	
	@NotEmpty(message="{name.empty}")
	@Size(min = 3, max = 50,message="{name.length.invalid}")
	private String name;

	private LocalDate dateOfBirth;

	private char gender;
	
	@NotEmpty(message="{phoneNo.empty}")
	@Size(min = 10, max = 10,message="{phoneNo.length.invalid}")
	private Long mobileNumber;
	
	@NotEmpty(message="{speciality.empty}")
	@Size(min = 3, max = 50,message="{speciality.length.invalid}")
	private String speciality;

	public String getCoachId() {
		return coachId;
	}

	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
}
