/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import afc.helper.Bill;
import afc.model.Couple;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CoupleKLeyeController implements Initializable, ControlledScreen{
    @FXML
    private TextField mName;
    @FXML
    private TextField fName;
    @FXML
    private TextField fatherNaam;
    @FXML
    private TextField mAdhaar;
    @FXML
    private TextField fAdhaar;
    @FXML
    private TextArea addresstf;
    @FXML
    private TextField phonetf;
    @FXML
    private TextField alternatetf;
    @FXML
    private TextField emailtf;
    @FXML
    private DatePicker mDOB;
    @FXML
    private DatePicker fDOB;
    @FXML
    private DatePicker dJoining;
    @FXML
    private Label expireLabel;

    /**
     * Initializes the controller class.
     */
    
    double amount;
    int discountRate;
    String plan;
    long months;
    boolean isPT;
    FileInputStream photos = null;
    @FXML
    private ImageView heroPhotoHolder;
    @FXML
    private Label amountLabel;
    @FXML
    private TextField percentage;
    
    boolean updated = false;

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    ScreensController myController;
    
    public void setScreenParent (ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void set6MonthsPlane(MouseEvent event) {
        discountRate = 25;
        amount = 12999;
        plan = "6 Months";
        months = 6;
    }

    @FXML
    private void setYearPlan(MouseEvent event) {
        discountRate = 30;
        amount = 23999;
        plan = "One Year";
        months = 12;
    }

    @FXML
    private void getPicture(MouseEvent event) {
        FileChooser r = new FileChooser();
        File s = r.showOpenDialog(null);
        if (s != null) {
            Image image = new Image(s.toURI().toString());
            heroPhotoHolder.setImage(image);
            try {
                photos = new FileInputStream(s);
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            //showAlertBox("Lavdu Lalit");

        }
    }

    @FXML
    private void submitDetails(MouseEvent event) {
        String MaleName = mName.getText();
        String femaleName = fName.getText();
        String father = fatherNaam.getText();
        String maleAdhaar = mAdhaar.getText();
        String femaleAdhaar = fAdhaar.getText();
        String phone = phonetf.getText() ;
        String alternate = alternatetf.getText();
        String email = emailtf.getText();
        String address = addresstf.getText();
        LocalDate mDob = mDOB.getValue();
        LocalDate fDob = fDOB.getValue();
        
        LocalDate joinn = dJoining.getValue();
        String exp = joinn.plusMonths(months).toString();
        Couple ch = new Couple(MaleName,femaleName, 
                    father,phone,alternate,
                    maleAdhaar,femaleAdhaar,address, 
                    mDob.toString(),fDob.toString(),amount, 
                    plan,email,joinn.toString(), 
                    exp,photos,isPT);
        
        String query = "";
        
        
        try{
            
            if (updated){
            query = "UPDATE `couple` SET `maleName`=?,`femaleName`=?,`father`=?,"
                    + "`phone`=?,`alternativePhone`=?,`femaleAdhaar`=?,"
                    + "`address`=?,`maleDOB`=?,`femaleDOB`=?,`fees`=?,`plan`=?,`email`=?,"
                    + "`joiningDate`=?,`expireDate`=?,`couplePhoto`=?,`isPT`=?, "
                    + "WHERE `maleAdhaar`= '"+maleAdhaar+"'";
                
                Connection connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement)connection.prepareStatement(query);
            statement.setString(1,ch.getMaleName());
            statement.setString(2,ch.getFemaleName());
            statement.setString(3,ch.getFather());
            statement.setString(4,ch.getPhone());
            statement.setString(5,ch.getAlternativePhone());
            statement.setString(6,ch.getFemaleAdhaar());
            statement.setString(7,ch.getAddress());
            statement.setString(8,ch.getMaleDOB());
            statement.setString(9,ch.getFemaleDOB());
            statement.setDouble(10,ch.getFees());
            statement.setString(11,ch.getPlan());
            statement.setString(12,ch.getEmail());
            statement.setString(13,ch.getDateOfJoining());
            statement.setString(14,ch.getDateWhenPlanExpire());
            statement.setBinaryStream(15, ch.getPhoto());
            statement.setBoolean(16,ch.isIsPT());
            
            statement.executeLargeUpdate();
            System.out.println("Successfull");
            }
            else{
            query = "INSERT INTO couple(maleName,femaleName,father,phone"
                    + ",alternativePhone,maleAdhaar,femaleAdhaar,address,maleDOB,"
                    + "femaleDOB,fees,plan,email,joiningDate,expireDate,couplePhoto"
                    + ",isPT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
                Connection connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement)connection.prepareStatement(query);
            statement.setString(1,ch.getMaleName());
            statement.setString(2,ch.getFemaleName());
            statement.setString(3,ch.getFather());
            statement.setString(4,ch.getPhone());
            statement.setString(5,ch.getAlternativePhone());
            statement.setString(6,ch.getMaleAdhaar());
            statement.setString(7,ch.getFemaleAdhaar());
            statement.setString(8,ch.getAddress());
            statement.setString(9,ch.getMaleDOB());
            statement.setString(10,ch.getFemaleDOB());
            statement.setDouble(11,ch.getFees());
            statement.setString(12,ch.getPlan());
            statement.setString(13,ch.getEmail());
            statement.setString(14,ch.getDateOfJoining());
            statement.setString(15,ch.getDateWhenPlanExpire());
            statement.setBinaryStream(16, ch.getPhoto());
            statement.setBoolean(17,ch.isIsPT());
            
            statement.executeLargeUpdate();
            Bill.sendBill(email,String.valueOf(p),MaleName,String.valueOf(amount),plan,exp);
            System.out.println("Successfull");
            setBackEmpty();
            }
        
        }catch(Exception exception){
            System.out.println("Something gose wrong in connection!");
        }  
        
       
        
    }

    @FXML
    private void backToDashboard(MouseEvent event) {
        myController.setScreen(AFC.screen2ID);
    }

    @FXML
    private void setPT(ActionEvent event) {
        if (isPT){
            isPT = false;
        }else {
            isPT = true;
        }
    }

    @FXML
    private void showExpire(ActionEvent event) {
        LocalDate joinn = dJoining.getValue();
        String exp = joinn.plusMonths(months).toString();
        expireLabel.setText(exp);
    }
    int p =0;
    @FXML
    private void calculateAmount(ActionEvent event) {
        p = Integer.parseInt(percentage.getText());
        if (p>25 && plan.equals("6 Months")){
            System.out.println("You can not do that");
            percentage.setText("0");
        }else if(p>30 && plan.equalsIgnoreCase("One Year")){
            System.out.println("You can not do that");
            percentage.setText("0");
        }else {
            amount = amount - (amount/100 *p);
            amountLabel.setText("Amount : ₹"+amount);
            
        }
    }
    
    public void setBackEmpty(){
         mName.setText("");
        fName.setText("");
        fatherNaam.setText("");
        mAdhaar.setText("");
        fAdhaar.setText("");
        phonetf.setText("");
alternatetf.setText("");
       emailtf.setText("");
     addresstf.setText("");
    }
    public void setSomething(String MaleName,
        String femaleName,
        String father,
        String maleAdhaar,
        String femaleAdhaar,
        String phone,
        String alternate,
        String email,
        String address){
        mName.setText(MaleName);
        fName.setText(femaleName);
        fatherNaam.setText(father);
        mAdhaar.setText(maleAdhaar);
        fAdhaar.setText(femaleAdhaar);
        phonetf.setText(phone);
alternatetf.setText(alternate);
       emailtf.setText(email);
     addresstf.setText(address);
    }

    @FXML
    private void makeAFuckingBill(ActionEvent event) {
        Bill.sendBill(emailtf.getText().toLowerCase(), percentage.getText(), mName.getText(), String.valueOf(amount), plan, expireLabel.getText());
    }
    
}
