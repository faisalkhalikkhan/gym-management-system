/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import com.mysql.jdbc.Blob;
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
 * @author Faisal
 */
public class FullDetailsOfSingleController implements Initializable, ControlledScreen {

    @FXML
    private ImageView userPhotoHolder;
    @FXML
    private Label userName;
    @FXML
    private Label userDateOfbirth;
    @FXML
    private Label userDateOfJoining;
    @FXML
    private Label userDateExpire;
    @FXML
    private Label userEmail;
    @FXML
    private Label userFees;
    @FXML
    private Label UserIsPt;
    @FXML
    private Label userPhone;
    @FXML
    private Label userAddress;
    @FXML
    private Label UserAlternatePhone;
    @FXML
    private Label adhaar;
    @FXML
    private Label father;
    @FXML
    private Label plan;

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

    public void setActualText(String name, String DOB, String DJoining, String expire, String email,
            String fees, String isPT, String phone, String address, String alternatePhone,
            Blob blob, String adhaarC, String fathers, String plana) {

        try {
            inputStream = blob.getBinaryStream();
            Image image = new Image(inputStream);
            userPhotoHolder.setImage(image);
        } catch (SQLException ex) {
            Logger.getLogger(FullDetailsOfSingleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        userName.setText("Name :" + name);
        userDateOfbirth.setText("Date Of Birth :" + DOB);
        userDateOfJoining.setText("Date Of Joining :" + DJoining);
        userDateExpire.setText("Plan Will Expire On :" + expire);
        userEmail.setText("Email :" + email);
        userFees.setText("Fees :" + fees);
        if (isPT.equalsIgnoreCase("true")) {
            UserIsPt.setText("Presonal Trainer: True");
        } else {
            UserIsPt.setText("Presonal Trainer: False");
        }

        userPhone.setText("Phone :" + phone);
        userAddress.setText(address);
        UserAlternatePhone.setText("Alternative Phone:" + alternatePhone);
        adhaar.setText(adhaarC);
        father.setText("Father :" + fathers);
        plan.setText(plana + "Plan");

    }

}
