/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

/**
 *
 * @author Shahriar Ivan
 */
public class JavaFXMLApplication extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public JavaFXMLApplication() {
    }
    
    public static Stage mainStage;
    public static Stage aboutStage;
    public static Stage closeStage;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        mainStage = stage;
        
        mainStage.setTitle("English Vocabulary Trainer");
        
        mainStage.setScene(scene);
        
        
        mainStage.show();
        
        mainStage.setOnCloseRequest(e -> {
                e.consume();    //consume the default close button event for handling it manually
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CloseAlert.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    closeStage = new Stage();
                    closeStage.setTitle("Confirm close");
                    closeStage.initModality(Modality.APPLICATION_MODAL);
                    closeStage.setScene(new Scene(root1));
                    closeStage.show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            });
        
    }
    
    
}
