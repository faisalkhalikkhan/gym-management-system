/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class UpdatePerconController implements Initializable, ControlledScreen{
    
    
    ScreensController myController;
    @FXML
    private TextField name;
    @FXML
    private TextField fatherName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField adhaarNumber;
    @FXML
    private TextArea adhaar;
    @FXML
    private TextField percentage;
    @FXML
    private DatePicker dateOfJoining = new DatePicker();
    @FXML
    private Text expireLabel;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private TextField alternatePhone;
    @FXML
    private ImageView photoHolder;
    
    int fees = 0;
    long months = 0;
    String plan = "";
    int dis = Integer.parseInt(percentage.getText());
    boolean isPT;
    FileInputStream p =null;
    LocalDate d =dateOfJoining.getValue();
    String joining = d.toString();
    String expired = d.plusMonths(months).toString();
    
    
    public void setScreenParent (ScreensController screenParent) {
        myController = screenParent;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void set3MonthsPlan(MouseEvent event) {
        if (dis > 20){
            System.out.println("You can not do this ");
        }else {
        int f = 3999;
        fees = (int) (f - (f/100 * dis));
        plan = "3 Months";
        months = 3;
            
        }
    }

    @FXML
    private void set6MonthsPlan(MouseEvent event) {
        int f = 6999;
        fees = (int) (f - (f/100 * dis));
        plan = "6 Months";
        months = 6;
    }

    @FXML
    private void setYearPlan(MouseEvent event) {
        int f = 11999;
        fees = (int) (f - (f/100 * 25));
        plan = "One Year";
        months = 12;
    }

    @FXML
    private void calculateDiscount(ActionEvent event) {
        
    }

    @FXML
    private void setIsPT(ActionEvent event) {
        if(isPT){
            isPT = false;
        }else{
            isPT = true;
        }
    }

    @FXML
    private void picPhoto(ActionEvent event) {
        FileChooser r = new FileChooser();
        File s = r.showOpenDialog(null);
        if (s != null) {
            Image image = new Image(s.toURI().toString());
            photoHolder.setImage(image);
            try {
                p = new FileInputStream(s);
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            showAlertBox("Kat Gaya Chutiya!!");

        }
    }
    public void showAlertBox(String message) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        VBox box = new VBox(new Text(message), new Button("OK"));
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(15));

        dialogStage.setScene(new Scene(box));
        dialogStage.show();
    }

    @FXML
    private void submitDetails(ActionEvent event) {
       String namea = name.getText();
       String father = fatherName.getText();
       String emailla = email.getText();
       String phoneo = phone.getText();
       String adhaara = adhaarNumber.getText();
       String address = adhaar.getText();
       
       System.out.println(p);
    }

    @FXML
    private void backToDashboard(ActionEvent event) {
    }

    @FXML
    private void setDateofExpire(ActionEvent event) {
        expireLabel.setText(expired);
    }
    
}
