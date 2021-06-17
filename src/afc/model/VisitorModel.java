/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc.model;

import javafx.scene.control.Button;

/**
 *
 * @author LENOVO
 */
public class VisitorModel {
    
    String name;
    String phone;
    String comments;

    public VisitorModel(String name, String phone, String comments) {
        this.name = name;
        this.phone = phone;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
    
}
