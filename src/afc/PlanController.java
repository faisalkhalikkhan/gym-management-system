/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PlanController implements Initializable , ControlledScreen{
    @FXML
    private AnchorPane slide1;
    @FXML
    private AnchorPane slide2;
    @FXML
    private AnchorPane slide3;
    @FXML
    private AnchorPane slideC1;
    @FXML
    private AnchorPane slideC2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    ScreensController myController;
    
    public void setScreenParent (ScreensController screenParent) {
        myController = screenParent;
    }
    
    
    public void node1(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slide1);
        slider.setDuration(Duration.millis(3000));
        slider.setToX(0);
        slider.setToY(30);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        slider.play();
    }
    public void node2(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slide2);
        slider.setDuration(Duration.millis(3000));
        slider.setToX(0);
        slider.setToY(30);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        slider.play();
    }
    public void node3(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slide3);
        slider.setDuration(Duration.millis(3000));
        slider.setToX(0);
        slider.setToY(30);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        slider.play();
    }
    public void nodec1(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slideC1);
        slider.setDuration(Duration.millis(3000));
        slider.setToX(0);
        slider.setToY(30);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        slider.play();
    }
    public void nodec2(){
        TranslateTransition slider = new TranslateTransition();
        slider.setNode(slideC2);
        slider.setDuration(Duration.millis(3000));
        slider.setToX(0);
        slider.setToY(30);
        slider.setAutoReverse(true);
        slider.setCycleCount(TranslateTransition.INDEFINITE);
        slider.play();
    }

    @FXML
    private void slideShow(ActionEvent event) {
        node1();
        node2();
        node3();
        nodec1();
        nodec2();
    }

    @FXML
    private void backToDashboard(ActionEvent event) {
        myController.setScreen(AFC.screen2ID);
    }
    
}
