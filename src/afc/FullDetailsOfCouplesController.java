/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import com.mysql.jdbc.Blob;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FullDetailsOfCouplesController implements Initializable, ControlledScreen {

    @FXML
    private ImageView cupPhotoHolder;
    @FXML
    private Label capMaleName;
    @FXML
    private Label cupFemaleName;
    @FXML
    private Label cupFatherName;
    @FXML
    private Label cupMaleDOB;
    @FXML
    private Label cupFemaleDOB;
    @FXML
    private Label cupContact;
    @FXML
    private Label cupAdhaarMale;
    @FXML
    private Label cupFemaleAdhaar;
    @FXML
    private Label cupAlternatePhone;
    @FXML
    private Label cupJoining;
    @FXML
    private Label cupExpire;
    @FXML
    private Label cupPlan;
    @FXML
    private Label cupFees;
    @FXML
    private Label cupTrainer;
    @FXML
    private Label cupEmail;
    @FXML
    private Label cupAddress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    InputStream inputStream;
    public void setTextvalues(String maleName, String femaleName, String father,
            String phone, String alternativePhone, String maleAdhaar, String femaleAdhaar,
            String address, String maleDOB, String femaleDOB, double fees, String plan, String email,
            String dateOfJoining, String dateWhenPlanExpire, Blob photo, boolean isPT) {
        
        try {
            inputStream = photo.getBinaryStream();
            Image image = new Image(inputStream);
            cupPhotoHolder.setImage(image);
        } catch (SQLException ex) {
            System.out.println("Photo me Gadbadh h");
        }
        
        
        capMaleName.setText("" + maleName);
        cupFemaleName.setText("" + femaleName);
        cupFatherName.setText("" + father);
        cupMaleDOB.setText(maleDOB);
        cupFemaleDOB.setText(femaleDOB);
        cupContact.setText(phone);
        cupAdhaarMale.setText(maleAdhaar);
        cupFemaleAdhaar.setText(femaleAdhaar);
        cupAlternatePhone.setText(alternativePhone);
        cupJoining.setText(dateOfJoining);
        cupExpire.setText(dateWhenPlanExpire);
        cupPlan.setText(plan);
        cupFees.setText(String.valueOf(fees));
        cupTrainer.setText(String.valueOf(isPT));
        cupEmail.setText(email);
        cupAddress.setText(address);
        
    }
    
}
