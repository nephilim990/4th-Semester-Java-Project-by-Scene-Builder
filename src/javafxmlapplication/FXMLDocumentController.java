/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;

/**
 *
 * @author Shahriar Ivan
 */
public class FXMLDocumentController implements Initializable {
      
    User[] user = new User[3];
    
    //initialize is called as soon as the view loads into the screen
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i=0;i<user.length;i++)
        {
            user[i] = new User();
        }
        user[0].setUser("Ivan", "ivan");
        user[1].setUser("Shakkhor", "shakkhor");
        user[2].setUser("Ahsan", "ahsan");
    }
    
    public TextField usernameInput;
    public TextField passwordInput;
    public Label passwordInvalidText;
    @FXML
    public void loginButtonClicked(ActionEvent event) {
        if(validatePassword(user,3,usernameInput.getText(),passwordInput.getText()))
        {
            passwordInput.setText("");
            passwordInvalidText.setVisible(false);
            //mainWindow.setScene(scene2);
        }
        else
        {
            passwordInput.setText("");
            passwordInvalidText.setVisible(true);
        }
    }
    
    
    
    //validate Password - scene1
    private boolean validatePassword(User[] user,int num, String name, String pass){
        
        boolean found=false;
        for(int i=0;i<num;i++)
        {
            if(name.equals(user[i].get_name()) && pass.equals(user[i].get_pass()))
            {
                found = true;
                break;
            }
            
        }
        
        if(found)
            return true;
        else
            return false;
    }
    
}
