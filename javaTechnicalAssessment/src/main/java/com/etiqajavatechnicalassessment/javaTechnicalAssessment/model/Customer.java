package com.etiqajavatechnicalassessment.javaTechnicalAssessment.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")

public class Customer {
	@Id 
		private String id;
	    private String firstName;
	    private String lastName;
	    private String emailOffice;
	    private String emailPersonal;
	    private List<String> familyMembers;

	    // Getters and Setters
	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getEmailOffice() {
	        return emailOffice;
	    }

	    public void setEmailOffice(String emailOffice) {
	        this.emailOffice = emailOffice;
	    }

	    public String getEmailPersonal() {
	        return emailPersonal;
	    }

	    public void setEmailPersonal(String emailPersonal) {
	        this.emailPersonal = emailPersonal;
	    }

	    public List<String> getFamilyMembers() {
	        return familyMembers;
	    }

	    public void setFamilyMembers(List<String> familyMembers) {
	        this.familyMembers = familyMembers;
	    }
}
