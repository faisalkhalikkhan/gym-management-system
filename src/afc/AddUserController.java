
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
import java.io.FileInputStream;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
public class AddUserController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private TextField singleUserName;
    @FXML
    private TextField singleUserFather;
    @FXML
    private DatePicker singleUserDOB;
    @FXML
    private TextField singleUserPhone;
    @FXML
    private TextField singleUserAdhaar;
    @FXML
    private TextArea singleUserAddress;
    @FXML
    private AnchorPane single3MonthPlan;
    @FXML
    private AnchorPane singleYearPlan;
    @FXML
    private AnchorPane single6MonthPlan;
    @FXML
    private TextField singleUserEmail;
    @FXML
    private Text singleUserAmount;
    @FXML
    private ImageView singleUserphotoHolder;
    @FXML
    private Button fileChooserButton;
    @FXML
    private DatePicker singleUserJoindate;
    @FXML
    private Text singleUserPlanExpireDate;
    Double planeAmount = 0.0;
    long monthAdded = 0;
    String plan = "";
    Boolean isPT = false;
    FileInputStream photo;
    Date dates = null;
    @FXML
    private TextField discount;
    @FXML
    private TextField singleUserAlternativePhone;
    @FXML
    private TextField maleName;
    @FXML
    private TextField femaleName;
    @FXML
    private TextField cFatherName;
    @FXML
    private TextField cPhone;
    @FXML
    private TextField maleAdhaar;
    @FXML
    private TextField femaleAdhaar;
    @FXML
    private TextArea cAddress;
    @FXML
    private DatePicker maleDOB;
    @FXML
    private DatePicker femaleDOB;
    @FXML
    private Text cAmount;
    @FXML
    private TextField cdiscount;
    @FXML
    private TextField coupleAlternativePhone;
    @FXML
    private AnchorPane cSixMonthPlan;
    @FXML
    private AnchorPane cOneYearPlan;
    @FXML
    private TextField coupleEmail;
    @FXML
    private DatePicker cJoiningDate;
    @FXML
    private Text cPlanExpireDate;
    @FXML
    private ImageView couplePhotoHolder;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void pickPhoto(ActionEvent e) {
        FileChooser r = new FileChooser();
        File s = r.showOpenDialog(null);
        if (s != null) {
            Image image = new Image(s.toURI().toString());
            singleUserphotoHolder.setImage(image);
            try {
                photo = new FileInputStream(s);
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            showAlertBox("Lavdu Lalit");

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
    public void threegetPlan() {
        planeAmount = 3999.0;
        monthAdded = 3;
        plan = "3 Months";
    }

    @FXML
    public void sixgetPlan() {
        planeAmount = 6999.0;
        monthAdded = 6;
        plan = "6 Months";
    }

    @FXML
    public void yeargetPlan() {
        planeAmount = 11999.0;
        monthAdded = 12;
        plan = "1 year";
    }

    double finalFeesOFPerson;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToDashBoard(ActionEvent event) {
        myController.setScreen(AFC.screen2ID);
    }

    @FXML
    private void submitDetails(ActionEvent event) throws SQLException {
        String name = singleUserName.getText();
        String father = singleUserFather.getText();
        LocalDate DOB = singleUserDOB.getValue();
        String phone = singleUserPhone.getText();
        String address = singleUserAddress.getText();
        String adhaarNumber = singleUserAdhaar.getText();
        String email = singleUserEmail.getText();
        String amount = singleUserAmount.getText();
        LocalDate planLaunchDate = singleUserJoindate.getValue();
        showAlertBox(planLaunchDate.plusMonths(monthAdded).toString());
        String alternativePhone = singleUserAlternativePhone.getText();
        LocalDate planExpireDate = LocalDate.parse(planLaunchDate.plusMonths(monthAdded).toString());

        if (phone.length() <= 7 || alternativePhone.length() <= 7) {
            showAlertBox("Please Check Your Mobile Number!!");
            singleUserAlternativePhone.setText("");
            singleUserPhone.setText("");
        } else if (name.isEmpty() || name.length() < 3) {
            singleUserName.setText("");
            showAlertBox("Invalide Name !!");
        } else if (father.isEmpty() || email.isEmpty() || address.isEmpty() || adhaarNumber.isEmpty()) {
            showAlertBox("Please Fill Complete Details !!");
        } else {
            SinglePerson lavduLalit = new SinglePerson(name, father, phone, alternativePhone, adhaarNumber, DOB.toString(), photo, address,
                    plan, planLaunchDate.toString(), planExpireDate.toString(), isPT, (int) finalFeesOFPerson, email);
            String query = "INSERT INTO `person`(`p_name`, `father_name`, `phone`, `adhaar_number`,"
                    + " `DOB`, `photo`, `address`, `alternativePhone`, `plan`, `plan_expire_date`,"
                    + " `joining_date`, `is_pt`, `fees`, `email`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            System.out.println(lavduLalit);

            try {
                Connection connection = DataBaseHandler.dbConnection();
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);

                statement.setString(1, lavduLalit.getName());
                statement.setString(2, lavduLalit.getFather());
                statement.setString(3, lavduLalit.getPhone());
                statement.setString(4, lavduLalit.getAdhaarNumber());
                statement.setString(5, lavduLalit.getDOB());
                statement.setBinaryStream(6, lavduLalit.getPhoto());
                statement.setString(7, lavduLalit.getAddress());
                statement.setString(8, lavduLalit.getAlternateNumber());
                statement.setString(9, lavduLalit.getPlan());
                statement.setString(10, lavduLalit.getDateWhenPlanExpired());
                statement.setString(11, lavduLalit.getDateOfJoining());
                statement.setBoolean(12, lavduLalit.isIsPT());
                statement.setInt(13, lavduLalit.getFees());
                statement.setString(14, lavduLalit.getEmail());

                statement.executeLargeUpdate();
                setBackToEmpty();
                showAlertBox("User Added Successfully !!");

            } catch (Exception exception) {
                showAlertBox("Photo is tooo Big !!");
            }
        }

    }

    @FXML
    private void showAmount(ActionEvent event) {
        Double d = Double.parseDouble(discount.getText());
        if (d > 20 && plan.equalsIgnoreCase("6 Months") || d > 20 && plan.equalsIgnoreCase("3 Months")) {
            showAlertBox("Discount Should Not Be Greater then 20!! ");
            discount.setText("0");
        }
        if (d > 25 && plan.equalsIgnoreCase("1 year")) {
            showAlertBox("Discount Should Not Be Greater then 25!! ");
            discount.setText("0");
        } else {
            finalFeesOFPerson = planeAmount - (planeAmount / 100 * d);
            singleUserAmount.setText(Double.toString(planeAmount - (planeAmount / 100 * d)));
        }
    }

    @FXML
    private void showExpireDate(ActionEvent event) {
        singleUserPlanExpireDate.setText(singleUserJoindate.getValue().plusMonths(monthAdded).toString());
    }

    public void setBackToEmpty() {
        singleUserName.setText("");
        singleUserFather.setText("");
        singleUserPhone.setText("");
        singleUserAddress.setText("");
        singleUserAdhaar.setText("");
        singleUserEmail.setText("");
        singleUserAmount.setText("");
        singleUserDOB.setValue(LocalDate.now());
        singleUserAmount.setText("00.00RS");
    }

    //********************************COUPLE **************************************************
    int coupleFullFees = 0;
    String couplePlan = "";
    double finalFees = 0;
    long cmonths = 0;
    FileInputStream cPhotoUri = null;
    String date1 = "";
    String date2 = "";

    @FXML
    public void pick6MonthPlan() {
        coupleFullFees = 12999;
        couplePlan = "6 Months";
        cmonths = 6;
    }

    @FXML
    public void pick1yearPlan() {
        coupleFullFees = 23999;
        couplePlan = "1 year";
        cmonths = 12;

    }

    @FXML
    private void calculateCoupleFees(ActionEvent event) {
        Double d = Double.parseDouble(cdiscount.getText());
        if (d > 25 && couplePlan.equalsIgnoreCase("6 Months")) {
            showAlertBox("Discount Should Not Be Greater then 20!! ");
            cdiscount.setText("0");
        } else if (d > 30 && couplePlan.equalsIgnoreCase("1 year")) {
            showAlertBox("Discount Should Not Be Greater then 30!! ");
            cdiscount.setText("0");
        } else {
            cAmount.setText(Double.toString(coupleFullFees - (coupleFullFees / 100 * d)));
            finalFees = (coupleFullFees - (coupleFullFees / 100 * d));
        }
    }

    int counter = 0;

    @FXML
    private void submitCoupleDetails(ActionEvent event) {
        if (counter > 3) {
            showAlertBox("Yahi Pattak k chod denge Chal Nikal Maderchod!!");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

        String HName = maleName.getText();
        String FName = femaleName.getText();
        String father = cFatherName.getText();
        String contactNumber = cPhone.getText();
        String contact2 = coupleAlternativePhone.getText();
        String mAdhaar = maleAdhaar.getText();
        String fAdhaar = femaleAdhaar.getText();
        String address = cAddress.getText();
        LocalDate maleDateOfBirth = maleDOB.getValue();
        LocalDate femaleDateOfBirth = femaleDOB.getValue();
        String email = coupleEmail.getText();

        if (contactNumber.length() < 8 || contact2.length() < 8) {
            showAlertBox("Please check Your Number");
            cPhone.setText("");
            coupleAlternativePhone.setText("");
            counter++;
        } else if (HName.isEmpty() || FName.isEmpty()) {
            counter++;
            showAlertBox("Please Fill your name properly");
        } else if (fAdhaar.length() > 19 || mAdhaar.length() > 19) {
            showAlertBox("Invalide Adhaar");
            maleAdhaar.setText("");
            femaleAdhaar.setText("");
            counter++;
        } else {
            String query = "INSERT INTO couple(maleName,femaleName,father,phone,alternativePhone,maleAdhaar,femaleAdhaar,address,maleDOB,femaleDOB,fees,plan,email,joiningDate,expireDate,couplePhoto,isPT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                Connection connection = DataBaseHandler.dbConnection();
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);

                statement.setString(1, HName);
                statement.setString(2, FName);
                statement.setString(3, father);
                statement.setString(4, contactNumber);
                statement.setString(5, contact2);
                statement.setString(6, mAdhaar);
                statement.setString(7, fAdhaar);
                statement.setString(8, address);
                statement.setString(9, maleDateOfBirth.toString());
                statement.setString(10, femaleDateOfBirth.toString());
                statement.setDouble(11, finalFees);
                statement.setString(12, couplePlan);
                statement.setString(13, email);
                statement.setString(14, date1);
                statement.setString(15, date2);
                statement.setBinaryStream(16, cPhotoUri);
                statement.setString(17, "false");

                statement.executeLargeUpdate();

                maleName.setText("");
                femaleName.setText("");
                cFatherName.setText("");
                cPhone.setText("");
                coupleAlternativePhone.setText("");
                maleAdhaar.setText("");
                femaleAdhaar.setText("");
                cAddress.setText("");
                coupleEmail.setText("");
                cPhotoUri = null;
            } catch (Exception e) {
                System.out.println("NallaWange " + e);
                showAlertBox("Photo is tooo Big !!");
            }
        }

    }

    @FXML
    private void addCouplePhoto(ActionEvent event) {
        FileChooser r = new FileChooser();
        File s = r.showOpenDialog(null);
        if (s != null) {
            Image image = new Image(s.toURI().toString());
            couplePhotoHolder.setImage(image);
            try {
                cPhotoUri = new FileInputStream(s);
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            showAlertBox("Kat Gaya Chutiya!!");

        }
    }

    @FXML
    private void showCoupleExpireDate(ActionEvent event) {
        LocalDate date = cJoiningDate.getValue();
        String expire = date.plusMonths(cmonths).toString();
        cPlanExpireDate.setText(expire);
        date1 = date.toString();
        date2 = expire;
    }

    @FXML
    private void sendaFuckingBill(ActionEvent event) {
        String to = singleUserEmail.getText();
        String dis = discount.getText();
        String namea = singleUserName.getText();
        String am = String.valueOf(finalFeesOFPerson);
        LocalDate planLaunchDate = singleUserJoindate.getValue();
        String wwe = planLaunchDate.plusMonths(monthAdded).toString();
        Bill.sendBill(to, dis, namea, am, plan, wwe);

    }

    @FXML
    private void makeCoupleBill(ActionEvent event) {
        String to = coupleEmail.getText();
        String dis = cdiscount.getText();
        String naam = maleName.getText();
        String f = String.valueOf(finalFees);
        Bill.sendBill(to, dis, naam, f, couplePlan, date2);
    }

}
