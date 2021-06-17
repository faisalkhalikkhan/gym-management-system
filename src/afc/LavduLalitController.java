/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import afc.helper.Bill;
import afc.model.SinglePerson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LavduLalitController implements Initializable {
    @FXML
    private Label heroName;
    @FXML
    private DatePicker jDate;
    @FXML
    private Label eDateLabel;
    @FXML
    private TextField discountLabel;
    @FXML
    private Label amountHolder;

    /**
     * Initializes the controller class.
     */
    
    SinglePerson person;
    boolean isPt = false;
    String plan ;
    long months;
    int discount;
    int amount;
    String joinD;
    String eD;
    String email;
    String namea;
    String adhaarCard;
    boolean a,b,c;
    @FXML
    private AnchorPane slide1;
    @FXML
    private AnchorPane slide2;
    @FXML
    private AnchorPane slide3;
    
    public void node1(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slide1);
        slider.setDuration(Duration.millis(2000));
        slider.setToX(0);
        slider.setToY(20);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        if (a){
           slider.play(); 
        }else {
            slider.stop();
        }
        
    }
    public void node2(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slide2);
        slider.setDuration(Duration.millis(2000));
        slider.setToX(0);
        slider.setToY(20);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        if (b){
           slider.play(); 
        }else {
            slider.stop();
        }
    }
    public void node3(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slide3);
        slider.setDuration(Duration.millis(2000));
        slider.setToX(0);
        slider.setToY(20);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        if (c){
           slider.play(); 
        }else {
            slider.stop();
        }
    }
    
    

    public void setAdhaarCard(String adhaarCard) {
        this.adhaarCard = adhaarCard;
    }
    
    
    public void setNamea(String namea) {
        this.namea = namea;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public void setPerson(SinglePerson person) {
        this.person = person;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //heroName.setText("Hello "+namea);   
    }    

    @FXML
    private void set3Months(MouseEvent event) {
        plan = "3 Months";
        months = 3;
        amount = 3999;
        a =true;
        c = b =false;
        node1();
        node2();
        node3();
    }

    @FXML
    private void set6Months(MouseEvent event) {
        plan = "6 Months";
        months = 6;
        amount = 6999;
        b = true;
        a=c=false;
        node1();
        node2();
        node3();
    }

    @FXML
    private void set1Year(MouseEvent event) {
        plan = "1 Year";
        months = 12;
        amount = 11999;
        c = true;
        a = b = false;
        node1();
        node2();
        node3();
    }

    @FXML
    private void showExpire(ActionEvent event) {
        LocalDate date = jDate.getValue();
        joinD = date.toString();
        eD = date.plusMonths(months).toString();
        eDateLabel.setText(eD);
        
    }

    @FXML
    private void showDiscount(ActionEvent event) {
        discount =Integer.parseInt(discountLabel.getText());
        if (discount > 20 && plan.equalsIgnoreCase("3 Months") || discount >20 && plan.equalsIgnoreCase("6 Months")){
            discountLabel.setText("00.00");
            amountHolder.setText("Amount :"+ "00.00");
        }else if (discount > 25 && plan.equalsIgnoreCase("1 Year")){
            discountLabel.setText("00.00");
            amountHolder.setText("Amount :"+ "00.00");
        }else {
            amount =(int) amount - (amount/100 * discount);
            amountHolder.setText("Amount :"+amount);
        }
    }

    @FXML
    private void makeABill(ActionEvent event) {
        Bill.sendBill(email, String.valueOf(discount), namea,String.valueOf(amount), plan, eD);
    }

    @FXML
    private void submitDetails(ActionEvent event) {
        try{
            Connection connection = DataBaseHandler.dbConnection();
           String query = "UPDATE `person` SET `plan`=?,`plan_expire_date`=?,`joining_date`=?,`is_pt`=?,`fees`= ? WHERE `adhaar_number` = ?";
            PreparedStatement sst = (PreparedStatement) connection.prepareStatement(query);

            sst.setString(1, plan);
            sst.setString(2, eD);
            sst.setString(3, joinD);
            sst.setBoolean(4, isPt);
            sst.setInt(5, amount);
            sst.setString(6, adhaarCard);
            sst.executeUpdate();
            System.out.println("Successfully");
        } catch (SQLException ex) {
            Logger.getLogger(ListOfCoustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(adhaarCard+" "+namea+" "+joinD+" "+eD+" "+discount+" "+amount+" "+plan+" "+email+" "+isPt+" ");
    }

    @FXML
    private void trainerOk(ActionEvent event) {
        if(isPt){
            isPt = false;
        }else{
            isPt = true;
        }
    }
    
}
