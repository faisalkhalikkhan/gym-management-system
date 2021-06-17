/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author LENOVO
 */
public class AFC extends Application {

    public static String screen1ID = "main";
    public static String screen1File = "Home.fxml";
    public static String screen2ID = "Dashboard";
    public static String screen2File = "DashBoard.fxml";

    public static String screen3ID = "AddUser";
    public static String screen3File = "AddUser.fxml";

    public static String screen4ID = "AddVisiters";
    public static String screen4File = "visitors.fxml";

    public static String screen5ID = "ListOfCoustomer";
    public static String screen5File = "listOfCoustomers.fxml";

    public static String screen6ID = "AddSingleUser";
    public static String screen6File = "UpdatePercon.fxml";
    public static String screen7ID = "Couples";
    public static String screen7File = "CoupleKLeye.fxml";
    public static String screen8ID = "FullDetails";
    public static String screen8File = "FullDetailsOfSingle.fxml";

    public static String screen9ID = "CoupleDetails";
    public static String screen9File = "FullDetailsOfCouples.fxml";
    
    public static String screen10ID = "PlanDetails";
    public static String screen10File = "Plan.fxml";
    public static String screen11ID = "UpdatePersonDetails";
    public static String screen11File = "Uperson.fxml";
    
    
    public static String screen12ID = "LavduLalit";
    public static String screen12File = "LavduLalit.fxml";
    
    
    public static String screen13ID = "Cappula";
    public static String screen13File = "DueCap.fxml";
    

    

    @Override
    public void start(Stage stage) throws Exception {
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(AFC.screen1ID, AFC.screen1File);
        mainContainer.loadScreen(AFC.screen2ID, AFC.screen2File);
        mainContainer.loadScreen(AFC.screen4ID, AFC.screen4File);
        mainContainer.loadScreen(AFC.screen5ID, AFC.screen5File);
        mainContainer.loadScreen(AFC.screen6ID, AFC.screen6File);
        mainContainer.loadScreen(AFC.screen3ID, AFC.screen3File);
        mainContainer.loadScreen(AFC.screen7ID, AFC.screen7File);
        mainContainer.loadScreen(AFC.screen8ID, AFC.screen8File);
        mainContainer.loadScreen(AFC.screen9ID, AFC.screen9File);
        mainContainer.loadScreen(AFC.screen10ID, AFC.screen10File);
        mainContainer.loadScreen(AFC.screen11ID, AFC.screen11File);
        mainContainer.loadScreen(AFC.screen12ID, AFC.screen12File);
        mainContainer.loadScreen(AFC.screen13ID, AFC.screen13File);
        

        mainContainer.setScreen(AFC.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);

        stage.getIcons().add(new Image(AFC.class.getResourceAsStream("dumbbell.png")));
        Process process = Runtime.getRuntime().exec("C:\\xampp\\xampp_start.exe");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
       
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
