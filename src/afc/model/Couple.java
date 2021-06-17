/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc.model;

import java.io.FileInputStream;

/**
 *
 * @author LENOVO
 */
public class Couple {

    String maleName;
    String femaleName;
    String father;
    String phone;
    String alternativePhone;
    String maleAdhaar;
    String femaleAdhaar;
    String address;
    String maleDOB;
    String femaleDOB;
    double fees;
    String plan;
    String email;
    String dateOfJoining;
    String dateWhenPlanExpire;
    FileInputStream photo;
    boolean isPT;

    public Couple(String maleName, String femaleName, String father, String phone, String alternativePhone, String maleAdhaar, String femaleAdhaar, String address, String maleDOB, String femaleDOB, double fees, String plan, String email, String dateOfJoining, String dateWhenPlanExpire, FileInputStream photo, boolean isPT) {
        this.maleName = maleName;
        this.femaleName = femaleName;
        this.father = father;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.maleAdhaar = maleAdhaar;
        this.femaleAdhaar = femaleAdhaar;
        this.address = address;
        this.maleDOB = maleDOB;
        this.femaleDOB = femaleDOB;
        this.fees = fees;
        this.plan = plan;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.dateWhenPlanExpire = dateWhenPlanExpire;
        this.photo = photo;
        this.isPT = isPT;
    }

    public Couple(String maleName, String femaleName, String father, String phone, String alternativePhone, String maleAdhaar, String femaleAdhaar, String address, String maleDOB, String femaleDOB, double fees, String plan, String email, String dateOfJoining, String dateWhenPlanExpire, boolean isPT) {
        this.maleName = maleName;
        this.femaleName = femaleName;
        this.father = father;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.maleAdhaar = maleAdhaar;
        this.femaleAdhaar = femaleAdhaar;
        this.address = address;
        this.maleDOB = maleDOB;
        this.femaleDOB = femaleDOB;
        this.fees = fees;
        this.plan = plan;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.dateWhenPlanExpire = dateWhenPlanExpire;
        this.isPT = isPT;
    }

    public String getMaleName() {
        return maleName;
    }

    public void setMaleName(String maleName) {
        this.maleName = maleName;
    }

    public String getFemaleName() {
        return femaleName;
    }

    public void setFemaleName(String femaleName) {
        this.femaleName = femaleName;
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

    public String getAlternativePhone() {
        return alternativePhone;
    }

    public void setAlternativePhone(String alternativePhone) {
        this.alternativePhone = alternativePhone;
    }

    public String getMaleAdhaar() {
        return maleAdhaar;
    }

    public void setMaleAdhaar(String maleAdhaar) {
        this.maleAdhaar = maleAdhaar;
    }

    public String getFemaleAdhaar() {
        return femaleAdhaar;
    }

    public void setFemaleAdhaar(String femaleAdhaar) {
        this.femaleAdhaar = femaleAdhaar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaleDOB() {
        return maleDOB;
    }

    public void setMaleDOB(String maleDOB) {
        this.maleDOB = maleDOB;
    }

    public String getFemaleDOB() {
        return femaleDOB;
    }

    public void setFemaleDOB(String femaleDOB) {
        this.femaleDOB = femaleDOB;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getDateWhenPlanExpire() {
        return dateWhenPlanExpire;
    }

    public void setDateWhenPlanExpire(String dateWhenPlanExpire) {
        this.dateWhenPlanExpire = dateWhenPlanExpire;
    }

    public FileInputStream getPhoto() {
        return photo;
    }

    public void setPhoto(FileInputStream photo) {
        this.photo = photo;
    }

    public boolean isIsPT() {
        return isPT;
    }

    public void setIsPT(boolean isPT) {
        this.isPT = isPT;
    }
    
    

}
