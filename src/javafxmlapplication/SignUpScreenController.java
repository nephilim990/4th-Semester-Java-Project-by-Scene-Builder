/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Shahriar Ivan
 */
public class SignUpScreenController implements Initializable {
    @FXML
    public StackPane rootPane;
    
    @FXML
    public RadioButton maleButton , femaleButton;
    
    @FXML
    public Label userInvalidText , genderInvalidText , passwordMismatchText;
    
    @FXML
    public TextField newUserInput;
    public PasswordField newPasswordInput , newPasswordConfirm;
    
    private boolean userFlag=false;
    private boolean genderFlag=false;
    private boolean passwordFlag=false;
    

    public SignUpScreenController() {
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void backToLogInButtonClicked(ActionEvent event) throws IOException {
        loadLogInScreen();
    }
    
    
    @FXML
    public void signupButtonClicked(ActionEvent event) throws IOException {
        //Checking if username field is not filled
        if(newUserInput.getText().equals("")){
            userInvalidText.setVisible(true);
            userFlag = false;
        }
        else{
            FXMLDocumentController.current_user.setName(newUserInput.getText());
            userInvalidText.setVisible(false);
            userFlag = true;
        }
        
        //Checking if a gender is selected
        if(maleButton.isSelected()){
            FXMLDocumentController.current_user.setGender("Male");
            genderInvalidText.setVisible(false);
            genderFlag=true;
        }
        else if(femaleButton.isSelected()){
            FXMLDocumentController.current_user.setGender("Female");
            genderInvalidText.setVisible(false);
            genderFlag=true;
        }
        else{
            genderInvalidText.setVisible(true);
            genderFlag=false;
        }
        
        //Checking if password matches with confirmation
        if(newPasswordInput.getText().equals(newPasswordConfirm.getText())){
            FXMLDocumentController.current_user.setPass(newPasswordInput.getText());
            passwordMismatchText.setVisible(false);
            passwordFlag = true;
        }
        else{
            passwordMismatchText.setVisible(true);
            passwordFlag = false;
        }
        
        if(userFlag && genderFlag && passwordFlag){  //all three fields must be valid for registering new user

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/user.txt",true));
                bw.write(FXMLDocumentController.current_user.getName());
                bw.newLine();
                bw.write(FXMLDocumentController.current_user.getPass());
                bw.newLine();
                bw.write(FXMLDocumentController.current_user.getGender());
                bw.newLine();
                bw.write(Integer.toString(FXMLDocumentController.current_user.getRating()));
                bw.newLine();
                bw.close();
               
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            loadMainScreen();
        }
    }
    
    
    //method for going back to Log In Screen
    private void loadLogInScreen() throws IOException{
        StackPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
     //load Main Screen
    private void loadMainScreen() throws IOException{
        StackPane pane = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
