/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import afc.model.DataPasses;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author LENOVO
 */
public class HomeController implements Initializable , ControlledScreen{
    
    ScreensController myController;
    @FXML
    private VBox loginVbox;
    @FXML
    private TextField loginEmail;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private VBox signupVbox;
    @FXML
    private TextField signupFullname;
    @FXML
    private TextField signupEmail;
    @FXML
    private PasswordField signupPassword;
    @FXML
    private PasswordField signupConfirmPassword;
    @FXML
    private Pane slidingPane;
    
    
    public void setScreenParent (ScreensController screenParent) {
        myController = screenParent;
    }
    public void showAlertBox(String message){
        Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            VBox box = new VBox(new Text(message), new Button("OK"));
            box.setAlignment(Pos.CENTER);
            box.setPadding(new Insets(15));
            
            dialogStage.setScene(new Scene(box));
            dialogStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TranslateTransition t= new TranslateTransition(Duration.seconds(1), slidingPane);
        t.setToX((slidingPane.getLayoutX() +12) * 45 );
        t.play();
        t.setOnFinished((e) -> {
            try{
                signupVbox.setVisible(false);
                signupVbox.managedProperty().bind(signupVbox.visibleProperty());
                loginVbox.setVisible(true);
                loginVbox.managedProperty().bind(loginVbox.visibleProperty());
            }catch(Exception ex){}
        });
    }    

    @FXML
    private void goToSignUpVbox(ActionEvent event) {
        System.out.println("I am  working!");
        TranslateTransition t= new TranslateTransition(Duration.seconds(1), slidingPane);
        t.setToX((slidingPane.getLayoutX() +12) * 0 + 20 );
        t.play();
        t.setOnFinished((e) -> {
            try{
                signupVbox.setVisible(true);
                signupVbox.managedProperty().bind(signupVbox.visibleProperty());
                loginVbox.setVisible(false);
                loginVbox.managedProperty().bind(loginVbox.visibleProperty());
            }catch(Exception ex){}
        });
    }

    @FXML
    private void goToLoginVbox(ActionEvent event) {
        TranslateTransition t= new TranslateTransition(Duration.seconds(1), slidingPane);
        t.setToX((slidingPane.getLayoutX() +12) * 45 );
        t.play();
        t.setOnFinished((e) -> {
            try{
                signupVbox.setVisible(false);
                signupVbox.managedProperty().bind(signupVbox.visibleProperty());
                loginVbox.setVisible(true);
                loginVbox.managedProperty().bind(loginVbox.visibleProperty());
            }catch(Exception ex){}
        });
    }

    @FXML
    private void handleLoginButton(ActionEvent event) throws SQLException {
        String email = loginEmail.getText();
        String password = loginPassword.getText();
        boolean allOK = false;
        if (email.isEmpty() && password.isEmpty()){
            System.out.println("Failed");
            showAlertBox("Please Fill Your Details");
            
        }else {
            System.out.println(email+" "+password);
            Connection connection = (Connection) DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select * from admin where admin_email= ? and password = ? ");
            statement.setString(1, email.toLowerCase());
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                //System.out.println("Login hogaya");
                allOK = true;
                myController.setScreen(AFC.screen2ID);
            }else {
                showAlertBox("Data not Found!!");
            }
        }
        
    }

    @FXML
    private void goToSignUp(MouseEvent event) {
        
        signupVbox.setVisible(false);
        signupVbox.managedProperty().bind(signupVbox.visibleProperty());
        loginVbox.setVisible(true);
        loginVbox.managedProperty().bind(loginVbox.visibleProperty());
    }

    @FXML
    private void handleSignUpButton(ActionEvent event) throws SQLException {
        System.out.println("signup button is working!");
        String name = signupFullname.getText();
        String email = signupEmail.getText();
        String password = signupPassword.getText();
        String confirmPassword = signupConfirmPassword.getText();
        
        if (password.length() < 8){
            //System.out.println("Password should be atleast 8 character long");
            signupPassword.setText("");
            showAlertBox("Password should be atleast 8 character long");
            signupConfirmPassword.setText("");
        }else if (!password.equals(confirmPassword)){
            System.out.println("Please check the Password");
            showAlertBox("Please check the Password");
            signupPassword.setText("");
            signupConfirmPassword.setText("");
        }else {
            Connection connection = (Connection) DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("INSERT INTO admin(name,admin_email,password) VALUES(?,?,?)");
            statement.setString(1, name);
            statement.setString(2, email.toLowerCase());
            statement.setString(3, password);
            
            statement.executeUpdate();
           //System.out.println("Successfull!! ");
           showAlertBox("Successfull!! ");
           myController.setScreen(AFC.screen2ID);
        }
    }
    
}
