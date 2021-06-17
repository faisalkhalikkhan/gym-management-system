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
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class UpersonController implements Initializable, ControlledScreen {
    @FXML
    private AnchorPane slide1;
    @FXML
    private AnchorPane slide2;
    @FXML
    private AnchorPane slide3;
    @FXML
    private DatePicker join;
    @FXML
    private Label expire;
    @FXML
    private TextField discount;
    @FXML
    private Text amountTF;
    
    
    int amount;
    int dic = Integer.parseInt(discount.getText());
    String jDate = join.getValue().toString();
    String eDate ;
    LocalDate f ;
    long months ;
    String plan;
    boolean isPersonalTrainer =false;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    SinglePerson sunil;
    
    public void setPerson(SinglePerson sanjay){
        sunil = sanjay;
    }

    @FXML
    private void calculateDiscount(ActionEvent event) {
        f = LocalDate.parse(jDate).plusMonths(months);
        eDate = f.toString();
        
        if (dic > 20 && plan.equalsIgnoreCase("3 Months")){
            discount.setText("0");
            
        }else if (dic > 20 && plan.equalsIgnoreCase("6 Months")) {
            discount.setText("0");
        }else if (dic > 25 && plan.equalsIgnoreCase("1 Year")) {
            discount.setText("0");
        }else {
            amount = (int) amount - (amount/100 *dic);
        }
    }

    @FXML
    private void makeBill(ActionEvent event) {
        Bill.sendBill(sunil.getEmail(), String.valueOf(dic), sunil.getName(), String.valueOf(amount), plan, eDate);
    }

    @FXML
    private void submitDetails(ActionEvent event) {
        try{
            Connection connection = DataBaseHandler.dbConnection();
           String query = "UPDATE `person` SET `plan`=?,`plan_expire_date`=?,`joining_date`=?,`is_pt`=?,`fees`= ? WHERE `adhaar_number` = ?";
            PreparedStatement sst = (PreparedStatement) connection.prepareStatement(query);

            sst.setString(1, plan);
            sst.setString(2, eDate);
            sst.setString(3, jDate);
            sst.setBoolean(4, isPersonalTrainer);
            sst.setInt(5, amount);
            sst.setString(6, sunil.getAdhaarNumber());
            sst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ListOfCoustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void isPTChecker(ActionEvent event) {
        if(isPersonalTrainer){
            isPersonalTrainer = false;
        }else{
            isPersonalTrainer = true;
        }
    }

    @FXML
    private void set3(MouseEvent event) {
        plan = "3 months";
        months = 3;
        amount = 3999;
    }

    @FXML
    private void set6(MouseEvent event) {
        plan = "6 months";
        months = 6;
        amount = 6999;
    }

    @FXML
    private void set1(MouseEvent event) {
        plan = "1 Year";
        months = 12;
        amount = 11999;
    }
    
    public void node1(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slide1);
        slider.setDuration(Duration.millis(3000));
        slider.setToX(0);
        slider.setToY(20);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        slider.play();
    }
    public void node2(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slide2);
        slider.setDuration(Duration.millis(3500));
        slider.setToX(0);
        slider.setToY(30);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        slider.play();
    }
    public void node3(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slide3);
        slider.setDuration(Duration.millis(4000));
        slider.setToX(0);
        slider.setToY(30);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        slider.play();
    }

    @FXML
    private void startMujra(ActionEvent event) {
        node1();
        node2();
        node3();
    }
    
}
