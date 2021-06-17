/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author LENOVO
 */
public class ScreensController extends StackPane{
    // It holds screen to display
    private HashMap<String, Node> screens = new HashMap<>();
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    public ScreensController(){
        super();
    }
    //Add screens to the collection
    public void addScreen( String name, Node screen){
        screens.put(name, screen);
    }
    // Returns the Node with the appropriate name
    public Node getScreen(String name){
        return screens.get(name);
    }
    
    public boolean loadScreen (String name, String resource) {
        try{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen)myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean setScreen (final String name) {
        if(screens.get(name) != null ) {
            final DoubleProperty opacity = opacityProperty();
            
            if (!getChildren().isEmpty()) {
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t){
                                getChildren().remove(0);
                                getChildren().add(0, screens.get(name));
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0))
                                );
                                fadeIn.play();
                            }
                        },new KeyValue(opacity, 0.0))
                );
                fade.play();
            }else {
            setOpacity(0.0);
            getChildren().add(screens.get(name));
            Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                    new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0))
            );
            fadeIn.play();
        }return true;
        }else {
        System.out.println("Screens hasn't been loaded !!! \\n");
        return false;
        }
    }
    
    public boolean unloadScreen (String name) {
        if (screens.remove(name) == null ){
            System.out.println("Screen didn't exist ");
            return false;
        }else {
            return true;
        }
    }

}
