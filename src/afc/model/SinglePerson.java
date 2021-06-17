/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc.model;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author FAISAL
 */
public class SinglePerson {
    
    String name;
    String father;
    String phone;
    String alternateNumber;
    String adhaarNumber;
    String DOB;
    FileInputStream photo;
    String address;
    String plan;
    String dateOfJoining;
    String dateWhenPlanExpired;
    boolean isPT;
    int fees;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SinglePerson(String name, String father, String phone, String alternateNumber, String adhaarNumber, String DOB, String address, String plan, String dateOfJoining, String dateWhenPlanExpired, boolean isPT, int fees, String email) {
        this.name = name;
        this.father = father;
        this.phone = phone;
        this.alternateNumber = alternateNumber;
        this.adhaarNumber = adhaarNumber;
        this.DOB = DOB;
        this.address = address;
        this.plan = plan;
        this.dateOfJoining = dateOfJoining;
        this.dateWhenPlanExpired = dateWhenPlanExpired;
        this.isPT = isPT;
        this.fees = fees;
        this.email = email;
    }

    
    
    public SinglePerson(String name, String father, String phone, String alternateNumber, String adhaarNumber, String DOB, FileInputStream photo, String address,
            String plan, String dateOfJoining, String dateWhenPlanExpired, boolean isPT, int fees, String email) {
        this.name = name;
        this.father = father;
        this.phone = phone;
        this.alternateNumber = alternateNumber;
        this.adhaarNumber = adhaarNumber;
        this.DOB = DOB;
        this.photo = photo;
        this.address = address;
        this.plan = plan;
        this.dateOfJoining = dateOfJoining;
        this.dateWhenPlanExpired = dateWhenPlanExpired;
        this.isPT = isPT;
        this.fees = fees;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public String getAdhaarNumber() {
        return adhaarNumber;
    }

    public void setAdhaarNumber(String adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public FileInputStream getPhoto() {
        return photo;
    }

    public void setPhoto(FileInputStream photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getDateWhenPlanExpired() {
        return dateWhenPlanExpired;
    }

    public void setDateWhenPlanExpired(String dateWhenPlanExpired) {
        this.dateWhenPlanExpired = dateWhenPlanExpired;
    }

    public boolean isIsPT() {
        return isPT;
    }

    public void setIsPT(boolean isPT) {
        this.isPT = isPT;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }
    
    
    
}
