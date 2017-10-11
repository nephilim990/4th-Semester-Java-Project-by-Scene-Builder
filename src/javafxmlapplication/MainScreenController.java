/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Shahriar Ivan
 */
public class MainScreenController implements Initializable {

    @FXML
    public StackPane rootPane;
    
    @FXML
    public ImageView userImage;
    
    @FXML
    public Image userAvatar1 = new Image("images/user3.png");
    @FXML
    public Image userAvatar2 = new Image("images/user5.png");
    
    @FXML
    public Label currentUsername, currentUsergender, currentUserrating, incorrectOldPasswordText, newPasswordMismatchText;
    
    @FXML
    public PasswordField oldPasswordInput, newPasswordInput, newPasswordConfirm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(FXMLDocumentController.current_user.getGender().equals("Male")){
            userImage.setImage(userAvatar1);
        }
        else{
            userImage.setImage(userAvatar2);
        }
        currentUsername.setText(FXMLDocumentController.current_user.getName());
        currentUsergender.setText(FXMLDocumentController.current_user.getGender());
        currentUserrating.setText(Integer.toString(FXMLDocumentController.current_user.getRating()));
    }    
    
    @FXML
    public void ChangePasswordButtonClicked(ActionEvent event){
        if(oldPasswordInput.getText().equals(FXMLDocumentController.current_user.getPass())){
            if(newPasswordInput.getText().equals(newPasswordConfirm.getText())){
                FXMLDocumentController.current_user.setPass(newPasswordInput.getText());
                incorrectOldPasswordText.setVisible(false);
                newPasswordMismatchText.setVisible(false);
            }
            else{
                incorrectOldPasswordText.setVisible(false);
                newPasswordMismatchText.setVisible(true);
            }
        }
        else{
            incorrectOldPasswordText.setVisible(true);
            
        }
        
    }
    
    @FXML
    public void AboutButtonClicked(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutScreen.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            JavaFXMLApplication.aboutStage = new Stage();
            JavaFXMLApplication.aboutStage.initStyle(StageStyle.UTILITY);
            JavaFXMLApplication.aboutStage.initModality(Modality.APPLICATION_MODAL);
            JavaFXMLApplication.aboutStage.setScene(new Scene(root2));
            JavaFXMLApplication.aboutStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    @FXML
    public void LogInWithDifferentAccountButtonClicked(ActionEvent event) throws IOException {
        loadLogInScreen();
    }
    
    @FXML
    public void exitButtonClicked(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CloseAlert.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            JavaFXMLApplication.closeStage = new Stage();
            JavaFXMLApplication.closeStage.initModality(Modality.APPLICATION_MODAL);
            JavaFXMLApplication.closeStage.setScene(new Scene(root1));
            JavaFXMLApplication.closeStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //method for going back to Log In Screen
    private void loadLogInScreen() throws IOException{
        StackPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
