/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Faisal
 */
public class VisitorsController implements Initializable, ControlledScreen {

    @FXML
    private TextField visitorName;
    @FXML
    private TextField visitorPhone;
    @FXML
    private TextArea visitorComments;

    boolean update = false;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

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

    @FXML
    private void submitDetails(ActionEvent event) {

        String name = visitorName.getText();
        String phone = visitorPhone.getText();
        String comment = visitorComments.getText();
        String query = "";

        if (name.length() <= 0) {
            System.out.println("Name is required !");
        } else if (phone.length() <= 8) {
            System.out.println("Phone number is required !");
        } else if (comment.length() <= 0) {
            System.out.println("Please Let Us Know what you are thinking about us \n it will help us to grow fast !");
        } else {
            try {
                Connection connection = DataBaseHandler.dbConnection();
                if (update) {
                    query = "UPDATE `visitor` SET "
                            + "`phone`=?,"
                            + "`comment`=?"
                            + " WHERE name = '" + name + "'";
                } else {
                    query = "INSERT INTO visitor(name,phone,comment) VALUES(?,?,?)";
                }
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
                if (update) {
                    statement.setString(1, phone);
                    statement.setString(2, comment);
                } else {

                    statement.setString(1, name);
                    statement.setString(2, phone);
                    statement.setString(3, comment);
                }

                statement.executeUpdate();
                System.out.println("Successfull!! ");
                visitorName.setText("");
                visitorPhone.setText("");
                visitorComments.setText("");
            } catch (Exception e) {
                System.out.println("Something gose wrong with Database!");
            }
        }
    }

    @FXML
    private void goBackToDashboard(ActionEvent event) {
        myController.setScreen(AFC.screen2ID);
    }

    void setTextField(String name, String phone, String comments) {
        visitorName.setText(name);
        visitorPhone.setText(phone);
        visitorComments.setText(comments);
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
