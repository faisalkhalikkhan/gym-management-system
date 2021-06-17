/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import afc.helper.Bill;
import afc.model.DataPasses;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DashBoardController implements Initializable, ControlledScreen {

    @FXML
    private Text helloAdmin;
    @FXML
    private Label currentMonthAmount;
    @FXML
    private Label lastMonthAmount;
    @FXML
    private ChoiceBox monthsList;
    @FXML
    private AnchorPane customPane;
    @FXML
    private Label incomeLabel;
    @FXML
    private Label expenseLabel;
    @FXML
    private BarChart<String, Number> chart;
    @FXML
    private TextField expenseTF;
    @FXML
    private Label currentDate;

    Connection connection = null;
    String o = "SELECT `fees`,`joining_date` FROM `person`";
    String ll = "SELECT `fees`,`joiningDate` FROM `couple`";

    /**
     * Initializes the controller class.
     */
    ObservableList<String> lav = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");

    String currentDateBox = LocalDate.now().toString();
    @FXML
    private NumberAxis naAxis;
    @FXML
    private CategoryAxis categoryAxis;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        long maal = 0;
        monthsList.setItems(lav);
        currentDate.setText(currentDateBox);
        long kaal = pelheMahenaKikammai();
        long baal = previousMonth();
        String q = "SELECT `fees` FROM `person`";
        try {
            connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(q);
            ResultSet r = statement.executeQuery();

            while (r.next()) {
                maal += Integer.parseInt(r.getString("fees"));
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        try {
            connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ll);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                maal += (int) set.getDouble("fees");
            }
        } catch (Exception a) {

        }

        incomeLabel.setText("₹" + String.valueOf(maal));
        int a, b, c;

        if (kaal > baal) {
            a = 105;
            b = 75;
            c = 35;
        } else {
            b = 100;
            a = 65;
            c = 45;
        }

        String cm = LocalDate.now().getMonth().toString();
        String pm = LocalDate.now().getMonth().minus(1).toString();
        String ppm = LocalDate.now().getMonth().minus(2).toString();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Income Rate");
        series.getData().add(new XYChart.Data<>(cm, a));
        series.getData().add(new XYChart.Data<>(pm, b));
        series.getData().add(new XYChart.Data<>(ppm, c));

        chart.getData().add(series);

    }

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void showBudget(ActionEvent event) {
        long amount = 0;
        String h = (String) monthsList.getValue();
        String query = "SELECT `fees`,`joining_date` FROM `person`";
        try {
            connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String da = result.getString("joining_date");
                LocalDate tra = LocalDate.parse(da);
                if (h.equalsIgnoreCase(tra.getMonth().toString())) {
                    amount += Integer.parseInt(result.getString("fees"));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        currentMonthAmount.setText("₹" + amount);

        System.out.println(h);
    }

    @FXML
    private void userPage(MouseEvent event) {
        myController.setScreen(AFC.screen3ID);
    }

    @FXML
    private void couplePage(MouseEvent event) {
        myController.setScreen(AFC.screen7ID);
    }

    @FXML
    private void ShowPlans(MouseEvent event) {
        myController.setScreen(AFC.screen10ID);
    }

    @FXML
    private void showPlan(MouseEvent event) {
        myController.setScreen(AFC.screen10ID);
    }

    @FXML
    private void goToVisitersPage(MouseEvent event) {
        myController.setScreen(AFC.screen4ID);
    }

    @FXML
    private void showCoustomers(MouseEvent event) {
        myController.setScreen(AFC.screen5ID);
    }

    @FXML
    private void closeMe(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addExpensesButton(ActionEvent event) {
        Bill.sendBill("sonofkhalik@gmail.com","21","Sajid Khan","3999","3 months","2020/12/01");
        //Bill.sendBill("sajidkhan8962158143sajidkhan@gmail.com","Sajid Khan","3999","2020/12/15","2020/12/01");
    }

    public long pelheMahenaKikammai() {
        long ismahineKiKammai = 0;
        try {

            connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(o);
            ResultSet set = statement.executeQuery();

            LocalDate z = LocalDate.parse(currentDateBox);
            while (set.next()) {
                String date = z.getMonth().toString();
                String da = set.getString("joining_date");
                LocalDate tra = LocalDate.parse(da);
                if (date.equalsIgnoreCase(tra.getMonth().toString())) {
                    ismahineKiKammai += Integer.parseInt(set.getString("fees"));
                } else {

                }
            }
            lavdiLappa(ismahineKiKammai);
            currentMonthAmount.setText(String.valueOf(ismahineKiKammai));
            return ismahineKiKammai;

        } catch (Exception e) {
            System.out.println("Lavde lag Gay");
        }
        return 0;
    }

    public void lavdiLappa(long ismahineKiKammai) {
        try {

            connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ll);
            ResultSet set = statement.executeQuery();

            LocalDate z = LocalDate.parse(currentDateBox);
            while (set.next()) {
                String date = z.getMonth().toString();
                String da = set.getString("joiningDate");
                LocalDate tra = LocalDate.parse(da);
                if (date.equalsIgnoreCase(tra.getMonth().toString())) {
                    ismahineKiKammai += (int) set.getDouble("fees");
                } else {

                }
            }

        } catch (Exception e) {
            System.out.println("Lavde lag Gay");
        }
    }

    public long previousMonth() {

        long pichleMahinekiKamai = 0;
        try {

            connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(o);
            ResultSet set = statement.executeQuery();

            LocalDate z = LocalDate.parse(currentDateBox).minusMonths(1);
            while (set.next()) {
                String date = z.getMonth().toString();
                String da = set.getString("joining_date");
                LocalDate tra = LocalDate.parse(da);
                if (date.equalsIgnoreCase(tra.getMonth().toString())) {
                    pichleMahinekiKamai += Integer.parseInt(set.getString("fees"));
                } else {

                }
            }
            padduPadoda(pichleMahinekiKamai);
            lastMonthAmount.setText(String.valueOf(pichleMahinekiKamai));
            return pichleMahinekiKamai;
        } catch (Exception e) {
            System.out.println("Lavde lag Gay");
        }
        return 0;
    }

    public void padduPadoda(long pichleMahinekiKamai) {
        try {

            connection = DataBaseHandler.dbConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ll);
            ResultSet set = statement.executeQuery();

            LocalDate z = LocalDate.parse(currentDateBox).minusMonths(1);
            while (set.next()) {
                String date = z.getMonth().toString();
                String da = set.getString("joiningDate");
                LocalDate tra = LocalDate.parse(da);
                if (date.equalsIgnoreCase(tra.getMonth().toString())) {
                    //pichleMahinekiKamai += Integer.parseInt(set.getString("fees"));
                    pichleMahinekiKamai += (int) set.getDouble("fees");
                } else {

                }
            }
        } catch (Exception e) {
            System.out.println("Lavde lag Gay");
        }
    }

}
