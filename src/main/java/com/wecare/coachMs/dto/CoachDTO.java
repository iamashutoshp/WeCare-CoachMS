package com.wecare.coachMs.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wecare.coachMs.entity.CoachEntity;

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
	
	@NotNull(message="{phoneNo.empty}")
	@Min(1000000000L)
	@Max(9999999999L)
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
	
	@Override
	public String toString() {
		return "CoachDTO [coachId=" + coachId + ", password=" + password + ", name=" + name + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", speciality=" + speciality
				+ "]";
	}
	
	public static CoachEntity prepareCoachEntity(CoachDTO coachDTO) {
		CoachEntity coachEntity=new CoachEntity();
		coachEntity.setCoachId(coachDTO.getCoachId());
		coachEntity.setGender(coachDTO.getGender());
		coachEntity.setDateOfBirth(coachDTO.getDateOfBirth());
		coachEntity.setMobileNumber(coachDTO.getMobileNumber());
		coachEntity.setName(coachDTO.getName());
		coachEntity.setPassword(coachDTO.getPassword());
		coachEntity.setSpeciality(coachDTO.getSpeciality());
		return coachEntity;
	}
	
	public static CoachDTO prepareCoachDTO(CoachEntity coachEntity) {
		CoachDTO coachDTO=new CoachDTO();
		coachDTO.setCoachId(coachEntity.getCoachId());
		coachDTO.setGender(coachEntity.getGender());
		coachDTO.setDateOfBirth(coachEntity.getDateOfBirth());
		coachDTO.setMobileNumber(coachEntity.getMobileNumber());
		coachDTO.setName(coachEntity.getName());
		coachDTO.setPassword(coachEntity.getPassword());
		coachDTO.setSpeciality(coachEntity.getSpeciality());
		return coachDTO;
	}
}
