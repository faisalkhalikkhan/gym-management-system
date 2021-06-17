/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import afc.model.Couple;
import afc.model.SinglePerson;
import afc.model.VisitorModel;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ListOfCoustomersController implements Initializable, ControlledScreen {

    @FXML
    private TableView<VisitorModel> visitorTableView;
    @FXML
    private TableColumn<VisitorModel, String> colVisitorName;
    @FXML
    private TableColumn<VisitorModel, String> colVisitorPhone;
    @FXML
    private TableColumn<VisitorModel, String> colVisitorComments;

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    String query = null;
    VisitorModel visitor = null;

    ObservableList<VisitorModel> VisitorsList = FXCollections.observableArrayList();
    @FXML
    private TableView<SinglePerson> singlesTable;
    @FXML
    private TableColumn<SinglePerson, String> colSingleAdhaarNumber;
    @FXML
    private TableColumn<SinglePerson, String> colSingleName;
    @FXML
    private TableColumn<SinglePerson, String> colSingleEmail;
    @FXML
    private TableColumn<SinglePerson, String> colSinglePhone;
    @FXML
    private TableColumn<SinglePerson, String> colSingleAddress;
    @FXML
    private TableColumn<SinglePerson, String> colSingleDateOfJoining;
    @FXML
    private TableColumn<SinglePerson, String> colSingleDateOfExpirePlan;
    @FXML
    private TableColumn<SinglePerson, String> colSinglePlan;
    @FXML
    private TableColumn<SinglePerson, String> colSingleFees;

    ObservableList<SinglePerson> PersonList = FXCollections.observableArrayList();
    SinglePerson lalit = null;

    @FXML
    private TableView<Couple> couplesTable;
    @FXML
    private TableColumn<Couple, String> colMaleAdhaar;
    @FXML
    private TableColumn<Couple, String> colMaleName;
    @FXML
    private TableColumn<Couple, String> colFemaleName;
    @FXML
    private TableColumn<Couple, String> colCoupleNumber;
    @FXML
    private TableColumn<Couple, String> colMaleEmail;
    @FXML
    private TableColumn<Couple, String> colCoupleDateOfJoining;
    @FXML
    private TableColumn<Couple, String> colCoupleDateOfPlanExpire;
    @FXML
    private TableColumn<Couple, String> colCoupleFees;
    ObservableList<Couple> CouplesList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<SinglePerson, String> dAdhaar;
    @FXML
    private TableColumn<SinglePerson, String> dName;
    @FXML
    private TableColumn<SinglePerson, String> dEmail;
    @FXML
    private TableColumn<SinglePerson, String> dPhone;
    @FXML
    private TableColumn<SinglePerson, String> ddateOfJoin;
    @FXML
    private TableColumn<SinglePerson, String> dexpire;
    @FXML
    private TableColumn<SinglePerson, String> dPlan;
    @FXML
    private TableColumn<SinglePerson, String> dfees;

    ObservableList<SinglePerson> DuePersonList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Couple, String> dMaleAdhaar;
    @FXML
    private TableColumn<Couple, String> dcMale;
    @FXML
    private TableColumn<Couple, String> dcFemalename;
    @FXML
    private TableColumn<Couple, String> dcContact;
    @FXML
    private TableColumn<Couple, String> dcMaleEmail;
    @FXML
    private TableColumn<Couple, String> dcDateOfJoining;
    @FXML
    private TableColumn<Couple, String> dcExpire;
    @FXML
    private TableColumn<Couple, String> dcFees;
    @FXML
    private TableView<SinglePerson> DueBhai;
    @FXML
    private TableView<Couple> DueCouple;
    ObservableList<Couple> DueCouplesList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        singlesTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    oneClickForSingle();
                }
            }
        });

        couplesTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    String maleName = null, femaleName = null, father = null, phone = null, alternativePhone = null, maleAdhaar = null;
                    String femaleAdhaar = null, address = null, maleDOB = null, femaleDOB = null;
                    double fees = 0;
                    String plan = null, email = null, dateOfJoining = null, dateWhenPlanExpire = null;
                    Blob photu = null;
                    boolean isPT = false;
                    Couple cappu = couplesTable.getSelectionModel().getSelectedItem();
                    try {
                        String qq = "SELECT * FROM `couple` WHERE `maleName` = '" + cappu.getMaleAdhaar() + "'";
                        connection = DataBaseHandler.dbConnection();
                        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(qq);
                        ResultSet fuck = stmt.executeQuery();
                        while (fuck.next()) {
                            maleName = fuck.getString("maleName");
                            femaleName = fuck.getString("femaleName");
                            father = fuck.getString("father");
                            phone = fuck.getString("phone");
                            alternativePhone = fuck.getString("alternativePhone");
                            maleAdhaar = fuck.getString("maleAdhaar");
                            femaleAdhaar = fuck.getString("femaleAdhaar");
                            address = fuck.getString("address");
                            maleDOB = fuck.getString("maleDOB");
                            femaleDOB = fuck.getString("femaleDOB");
                            fees = fuck.getDouble("fees");
                            plan = fuck.getString("plan");
                            email = fuck.getString("email");
                            dateOfJoining = fuck.getString("joiningDate");
                            dateWhenPlanExpire = fuck.getString("expireDate");
                            photu = (Blob) fuck.getBlob("couplePhoto");
                            isPT = fuck.getBoolean("isPT");
                        }
                    } catch (Exception es) {
                        showAlertBox("Error!!");
                    }
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FullDetailsOfCouples.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        showAlertBox("Loading Error found, Please Restart the application!");
                    }
                    FullDetailsOfCouplesController pontroller = loader.getController();
                    System.out.println("Working");
                    pontroller.setTextvalues(maleName, femaleName, father, phone, alternativePhone, maleAdhaar, femaleAdhaar, address, maleDOB, femaleDOB, fees, plan, email, dateOfJoining, dateWhenPlanExpire, photu, isPT);
                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.show();
                }
            }
        });
    }

    @FXML
    public void loadDuePerson() {
        connection = DataBaseHandler.dbConnection();
        refreshDuePerson();
        dAdhaar.setCellValueFactory(new PropertyValueFactory<>("adhaarNumber"));
        dName.setCellValueFactory(new PropertyValueFactory<>("name"));
        dEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        dPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        ddateOfJoin.setCellValueFactory(new PropertyValueFactory<>("dateOfJoining"));
        dexpire.setCellValueFactory(new PropertyValueFactory<>("dateWhenPlanExpired"));
        dPlan.setCellValueFactory(new PropertyValueFactory<>("plan"));
        dfees.setCellValueFactory(new PropertyValueFactory<>("fees"));
    }

    public void oneClickForSingle() {
        String name = null, DOB = null, DJoining = null, expire = null, email = null,
                fees = null, isPT = null, phone = null, address = null, alternatePhone = null, adhar = null, father = null, plan = null;
        Blob blob = null;
        lalit = singlesTable.getSelectionModel().getSelectedItem();

        System.out.println("I am Clicked");
        try {
            query = "SELECT * FROM `person` WHERE `phone` = '" + lalit.getPhone() + "'";
            statement = (PreparedStatement) connection.prepareStatement(query);

            result = statement.executeQuery();

            while (result.next()) {
                name = result.getString("p_name");
                father = result.getString("father_name");
                phone = result.getString("phone");
                alternatePhone = result.getString("alternativePhone");
                adhar = result.getString("adhaar_number");
                DOB = result.getString("DOB");
                address = result.getString("address");
                plan = result.getString("plan");
                DJoining = result.getString("joining_date");
                expire = result.getString("plan_expire_date");
                isPT = String.valueOf(result.getBoolean("is_pt"));
                fees = String.valueOf(result.getInt("fees"));
                email = result.getString("email");
                blob = (Blob) result.getBlob("photo");
            }
        } catch (Exception kl) {
            showAlertBox("Server Problem !!");
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FullDetailsOfSingle.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            showAlertBox("Error :Please Reload the App!!");
        }
        FullDetailsOfSingleController jontroller = loader.getController();
        jontroller.setActualText(name, DOB, DJoining, expire, email, fees, isPT, phone,
                address, alternatePhone, blob, adhar, father, plan);
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }
    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    private void refreshVisitorTable() {
        try {
            VisitorsList.clear();

            query = "SELECT * FROM `visitor`";
            statement = (PreparedStatement) connection.prepareStatement(query);

            result = statement.executeQuery();

            while (result.next()) {
                VisitorsList.add(new VisitorModel(
                        result.getString("name"),
                        result.getString("phone"),
                        result.getString("comment")
                ));
                visitorTableView.setItems(VisitorsList);
            }

        } catch (Exception e) {
            showAlertBox("Data Not Found ,Restart App ");
        }
    }

    @FXML
    private void loadVisitorsData() {
        connection = DataBaseHandler.dbConnection();
        refreshVisitorTable();
        colVisitorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colVisitorPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colVisitorComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
    }

    @FXML
    private void backToDashBoard(ActionEvent event) {
        myController.setScreen(AFC.screen2ID);
    }

    @FXML
    private void updateVisitor(ActionEvent event) {
        visitor = visitorTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("visitors.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            showAlertBox("Please Restart The App");
        }
        VisitorsController visitorsController = loader.getController();
        visitorsController.setUpdate(true);
        visitorsController.setTextField(visitor.getName(), visitor.getPhone(), visitor.getComments());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();

    }

    @FXML
    private void deleteVisitor(ActionEvent event) {
        visitor = visitorTableView.getSelectionModel().getSelectedItem();
        query = "DELETE FROM `visitor` WHERE phone = " + visitor.getPhone();
        connection = DataBaseHandler.dbConnection();
        try {
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.execute();
            refreshVisitorTable();

        } catch (SQLException ex) {
            showAlertBox("Invalide Input");
        }
    }

    private void refreshSinglesRecord() {

        System.out.println("I am refresh Here");
        try {
            PersonList.clear();
            query = "SELECT * FROM `person`";
            statement = (PreparedStatement) connection.prepareStatement(query);

            result = statement.executeQuery();

            while (result.next()) {
                PersonList.add(new SinglePerson(
                        result.getString("p_name"),
                        result.getString("father_name"),
                        result.getString("phone"),
                        result.getString("alternativePhone"),
                        result.getString("adhaar_number"),
                        result.getString("DOB"),
                        result.getString("address"),
                        result.getString("plan"),
                        result.getString("joining_date"),
                        result.getString("plan_expire_date"),
                        result.getBoolean("is_pt"),
                        result.getInt("fees"),
                        result.getString("email")
                ));
                singlesTable.setItems(PersonList);
            }

        } catch (Exception exception) {
            showAlertBox("No Data Found, Please Restart The App!!");
        }

    }

    @FXML
    private void loadSinglesRecord(MouseEvent event) {
        connection = DataBaseHandler.dbConnection();
        refreshSinglesRecord();

        colSingleAdhaarNumber.setCellValueFactory(new PropertyValueFactory<>("adhaarNumber"));
        colSingleName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSingleEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSinglePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSingleAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSingleDateOfJoining.setCellValueFactory(new PropertyValueFactory<>("dateOfJoining"));
        colSingleDateOfExpirePlan.setCellValueFactory(new PropertyValueFactory<>("dateWhenPlanExpired"));
        colSinglePlan.setCellValueFactory(new PropertyValueFactory<>("plan"));
        colSingleFees.setCellValueFactory(new PropertyValueFactory<>("fees"));

    }

    @FXML
    private void loadCoupleRecords(MouseEvent event) {
        System.out.println("I am Here");
        connection = DataBaseHandler.dbConnection();
        refreshCoupleRecord();
        colMaleAdhaar.setCellValueFactory(new PropertyValueFactory<>("maleAdhaar"));
        colMaleName.setCellValueFactory(new PropertyValueFactory<>("maleName"));
        colFemaleName.setCellValueFactory(new PropertyValueFactory<>("femaleName"));
        colCoupleNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colMaleEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCoupleDateOfJoining.setCellValueFactory(new PropertyValueFactory<>("dateOfJoining"));
        colCoupleDateOfPlanExpire.setCellValueFactory(new PropertyValueFactory<>("dateWhenPlanExpire"));
        colCoupleFees.setCellValueFactory(new PropertyValueFactory<>("fees"));
    }

    private void updateCoupleRecord(ActionEvent event) {
        Couple cappu = couplesTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CoupleKLeye.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            showAlertBox("Page Dose Not Exist, Please Restart The App!!");
        }
        CoupleKLeyeController vontroller = loader.getController();
        vontroller.setUpdated(true);
        vontroller.setSomething(cappu.getMaleName(), cappu.getFemaleName(), cappu.getFather(),
                cappu.getMaleAdhaar(), cappu.getFemaleAdhaar(),
                cappu.getPhone(), cappu.getAlternativePhone(), cappu.getEmail(), cappu.getAddress());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void deleteCoupleRecord(ActionEvent event) {
        Couple cappu = couplesTable.getSelectionModel().getSelectedItem();
        query = "DELETE FROM `couple` WHERE maleAdhaar = " + cappu.getMaleAdhaar();
        connection = DataBaseHandler.dbConnection();
        try {
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.execute();
            refreshVisitorTable();

        } catch (SQLException ex) {
            showAlertBox("Invalide Input!!");
        }
    }

    private void updateSingleRecord(ActionEvent event) {

        lalit = singlesTable.getSelectionModel().getSelectedItem();
        /*
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("UpdatePrecon.fxml"));
         try {
         loader.load();
         } catch (IOException ex) {
         Logger.getLogger(ListOfCoustomersController.class.getName()).log(Level.SEVERE, null, ex);
         }
         UpdatePerconController visitorsController = loader.getController();
         //visitorsController.setUpdate(true);
         //visitorsController.setTextField(visitor.getName(),visitor.getPhone(),visitor.getComments());
         Parent parent = loader.getRoot();
         Stage stage = new Stage();
         stage.setScene(new Scene(parent));
         stage.show();*/
    }

    @FXML
    private void deleteSingleRecord(ActionEvent event) {
        lalit = singlesTable.getSelectionModel().getSelectedItem();
        query = "DELETE FROM `person` WHERE adhaar_number = " + lalit.getAdhaarNumber();
        connection = DataBaseHandler.dbConnection();
        try {
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.execute();
            refreshSinglesRecord();

        } catch (SQLException ex) {
            showAlertBox("Invalide Input!!");
        }
    }

    private void refreshCoupleRecord() {

        try {
            CouplesList.clear();
            query = "SELECT * FROM `couple`";
            statement = (PreparedStatement) connection.prepareStatement(query);

            result = statement.executeQuery();

            while (result.next()) {
                CouplesList.add(new Couple(
                        result.getString("maleName"),
                        result.getString("femaleName"),
                        result.getString("father"),
                        result.getString("phone"),
                        result.getString("alternativePhone"),
                        result.getString("maleAdhaar"),
                        result.getString("femaleAdhaar"),
                        result.getString("address"),
                        result.getString("maleDOB"),
                        result.getString("femaleDOB"),
                        result.getDouble("fees"),
                        result.getString("plan"),
                        result.getString("email"),
                        result.getString("joiningDate"),
                        result.getString("expireDate"),
                        result.getBoolean("isPT")
                /*
                 (maleName,femaleName,father,phone,alternativePhone,maleAdhaar,femaleAdhaar,
                 address,maleDOB,femaleDOB,fees,plan,email,joiningDate,expireDate,couplePhoto,isPT)
                        
                        
                 */
                ));
                couplesTable.setItems(CouplesList);
            }

        } catch (Exception exception) {
            showAlertBox("Please Restart the App!!");
        }

    }

    private void UpdateAA(ActionEvent event) {
        
    }

    @FXML
    private void DeleteAA(ActionEvent event) {
        Couple cappu = DueCouple.getSelectionModel().getSelectedItem();
        query = "DELETE FROM `couple` WHERE maleAdhaar = " + cappu.getMaleAdhaar();
        connection = DataBaseHandler.dbConnection();
        try {
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.execute();
            refreshVisitorTable();

        } catch (SQLException ex) {
            showAlertBox("Select Properly Or Restart The App!!");
        }
    }

    @FXML
    private void updateDuePerson(ActionEvent event) {
            System.out.println("I am Working!!");
            lalit = DueBhai.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(getClass().getResource("LavduLalit.fxml"));
            System.out.println("I am Working too!!");
            try {
                loader.load();
            } catch (IOException ex) {
                
                showAlertBox("Page Does Not Exist,Please Restart The App");
            }
            LavduLalitController sitorsController = loader.getController();
            System.out.println(lalit);
            sitorsController.setNamea(lalit.getName());
            sitorsController.setAdhaarCard(lalit.getAdhaarNumber());
            sitorsController.setEmail(lalit.getEmail());
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
    }

    @FXML
    private void deleteDuePerson(ActionEvent event) {
        lalit = DueBhai.getSelectionModel().getSelectedItem();
        query = "DELETE FROM `person` WHERE adhaar_number = " + lalit.getAdhaarNumber();
        connection = DataBaseHandler.dbConnection();
        try {
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.execute();
            refreshSinglesRecord();

        } catch (SQLException ex) {
            showAlertBox("Invalide Input!!");
        }
    }

    private void refreshDuePerson() {
        System.out.println("Wooooooow");
        DuePersonList.clear();
        query = "SELECT * FROM `person`";
        try {
            PreparedStatement pp = (PreparedStatement) connection.prepareStatement(query);
            ResultSet fuck = pp.executeQuery();

            while (fuck.next()) {
                LocalDate exDate = LocalDate.parse(fuck.getString("plan_expire_date"));
                if (exDate.isBefore(LocalDate.now())) {
                    DuePersonList.add(new SinglePerson(
                            fuck.getString("p_name"),
                            fuck.getString("father_name"),
                            fuck.getString("phone"),
                            fuck.getString("alternativePhone"),
                            fuck.getString("adhaar_number"),
                            fuck.getString("DOB"),
                            fuck.getString("address"),
                            fuck.getString("plan"),
                            fuck.getString("joining_date"),
                            fuck.getString("plan_expire_date"),
                            fuck.getBoolean("is_pt"),
                            fuck.getInt("fees"),
                            fuck.getString("email")
                    ));
                    DueBhai.setItems(DuePersonList);
                }

            }

        } catch (SQLException ex) {
            showAlertBox("Please Restart The App!!");
        }
    }

    @FXML
    private void refreshC(ActionEvent event) {
        System.out.println("I am Here");
        connection = DataBaseHandler.dbConnection();
        lCup();
        dMaleAdhaar.setCellValueFactory(new PropertyValueFactory<>("maleAdhaar"));
        dcMale.setCellValueFactory(new PropertyValueFactory<>("maleName"));
        dcFemalename.setCellValueFactory(new PropertyValueFactory<>("femaleName"));
        dcContact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dcMaleEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        dcDateOfJoining.setCellValueFactory(new PropertyValueFactory<>("dateOfJoining"));
        dcExpire.setCellValueFactory(new PropertyValueFactory<>("dateWhenPlanExpire"));
        dcFees.setCellValueFactory(new PropertyValueFactory<>("fees"));
    }

    private void lCup() {
        String qqq = "SELECT * FROM `couple`";
        DueCouplesList.clear();
        connection = DataBaseHandler.dbConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(qqq);
            ResultSet fuck = statement.executeQuery();
            
            while (fuck.next()){
                LocalDate expDate = LocalDate.parse(fuck.getString("expireDate"));
                if (expDate.isBefore(LocalDate.now())){
                    DueCouplesList.add(new Couple(
                            fuck.getString("maleName"),
                            fuck.getString("femaleName"),
                             fuck.getString("father"),
                            fuck.getString("phone"), 
                            fuck.getString("alternativePhone") , 
                            fuck.getString("maleAdhaar"),
                            fuck.getString("femaleAdhaar"),
                            fuck.getString("address"),
                            fuck.getString("maleDOB"),
                            fuck.getString("femaleDOB"),
                            fuck.getDouble("fees"),
                            fuck.getString("plan"),
                            fuck.getString("email"), 
                            fuck.getString("joiningDate"),
                            fuck.getString("expireDate"),
                            Boolean.valueOf(fuck.getString("isPT")) ));
                    DueCouple.setItems(DueCouplesList);
                }
            }
            
        } catch (SQLException ex) {
            showAlertBox("Please Restart The App!!");
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
    private void UPUPUP(ActionEvent event) {
        System.out.println("I am Working!!");
            Couple abcdef = DueCouple.getSelectionModel().getSelectedItem();
            //FXMLLoader loader = new FXMLLoader();
            /*
            loader.setLocation(getClass().getResource("DueCap.fxml"));
            System.out.println("I am Working too!!");
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println("Problems Lies with me!!");
                showAlertBox("Page Does Not Exist!!");
            }*/
            myController.setScreen(AFC.screen13ID);
            
            //DueCapController sController = loader.getController();
            //sController.setEmail(abcdef.getEmail());
            //sController.setMaleAdhar(abcdef.getMaleAdhaar());
            //sController.setName(abcdef.getMaleName());
            
    }

}
