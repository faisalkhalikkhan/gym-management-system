/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import afc.helper.Bill;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DueCapController implements Initializable, ControlledScreen{

    @FXML
    private Label heroNameL;
    @FXML
    private DatePicker jDate;
    @FXML
    private Label eDate;
    @FXML
    private Label amountLabel;
    @FXML
    private TextField discount;
    @FXML
    private AnchorPane slide1;
    @FXML
    private AnchorPane slide2;

    /**
     * Initializes the controller class.
     */
    double amount;
    int discountPerc = Integer.parseInt(discount.getText());
    boolean ispt = false;
    long months;
    String plan;
    String email;
    String maleAdhar;
    String name;

    String joinDate;
    String eD;
    
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setMaleAdhar(String maleAdhar) {
        this.maleAdhar = maleAdhar;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void set6Months(MouseEvent event) {
        amount = 12999;
        months = 6;
        plan = "6 Months";
    }

    @FXML
    private void setOneYear(MouseEvent event) {
        amount = 23999;
        months = 12;
        plan = "1 Year";
    }

    @FXML
    private void showExpire(ActionEvent event) {
        LocalDate date = jDate.getValue();
        joinDate = date.toString();
        eD = date.plusMonths(months).toString();
        eDate.setText(eD);
    }

    @FXML
    private void showAmount(ActionEvent event) {
        if (discountPerc > 25 && plan.equalsIgnoreCase("6 Months")) {
            discountPerc = 0;
            showAlertBox("Discount should not be Greater then 25%");
            discount.setText("0");
            amountLabel.setText("Amount :?");
        } else if (discountPerc > 30 && plan.equalsIgnoreCase("1 Year")) {
            discountPerc = 0;
            showAlertBox("Discount should not be Greater then 30%");
            discount.setText("0");
            amountLabel.setText("Amount :?");
        } else {
            amount = amount - (amount / 100 * discountPerc);
            amountLabel.setText("Amount :" + String.valueOf(amount));
        }
    }

    @FXML
    private void trainerChecker(ActionEvent event) {
        if (ispt) {
            ispt = false;
        } else {
            ispt = true;
        }
    }

    @FXML
    private void makeBill(ActionEvent event) {
        Bill.sendBill(email, String.valueOf(discountPerc), name, String.valueOf(amount), plan, eD);
        
    }

    @FXML
    private void submitUpdate(ActionEvent event) {
        try {
            String query = "UPDATE `couple` SET `fees`=?,`plan`=?,`joiningDate`=?,`expireDate`=?,`isPT`=? WHERE `maleAdhaar`=? ";
            Connection connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
            statement.setDouble(1, amount);
            statement.setString(2, plan);
            statement.setString(3, joinDate);
            statement.setString(4, eD);
            statement.setString(5, Boolean.toString(ispt));
            statement.setString(6, maleAdhar);
            statement.executeUpdate();
            showAlertBox("Updation Successfull!!");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            showAlertBox("Updation Failed!!");
        }
    }

}
