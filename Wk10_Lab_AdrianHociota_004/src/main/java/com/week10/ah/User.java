
package com.week10.ah;

import java.math.BigDecimal;
import java.time.LocalDate;

public class User {

    private String fullName;
    private String currentAddress;
    private String contactNumber;
    private String emailAddress;
    private String highestEducation;
    private String gender;
    private LocalDate dateAvailable;
    private String desiredPosition;
    private BigDecimal desiredSalary;
    private String authorizedToWork;
    private String relativesWorking;
    private String relativesExplanation;
    private String applicantSignature;

    public User(String fullName, String currentAddress,
                String contactNumber, String emailAddress,
                String highestEducation, String gender,
                LocalDate dateAvailable, String desiredPosition,
                BigDecimal desiredSalary, String authorizedToWork,
                String relativesWorking, String relativesExplanation,
                String applicantSignature) {

        this.fullName = fullName;
        this.currentAddress = currentAddress;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.highestEducation = highestEducation;
        this.gender = gender;
        this.dateAvailable = dateAvailable;
        this.desiredPosition = desiredPosition;
        this.desiredSalary = desiredSalary;
        this.authorizedToWork = authorizedToWork;
        this.relativesWorking = relativesWorking;
        this.relativesExplanation = relativesExplanation;
        this.applicantSignature = applicantSignature;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateAvailable() {
        return dateAvailable;
    }

    public String getDesiredPosition() {
        return desiredPosition;
    }

    public BigDecimal getDesiredSalary() {
        return desiredSalary;
    }

    public String getAuthorizedToWork() {
        return authorizedToWork;
    }

    public String getRelativesWorking() {
        return relativesWorking;
    }

    public String getRelativesExplanation() {
        return relativesExplanation;
    }

    public String getApplicantSignature() {
        return applicantSignature;
    }
}
