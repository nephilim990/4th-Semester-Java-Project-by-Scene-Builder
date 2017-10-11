/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Shahriar Ivan
 */
public class CloseAlertController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void OkButtonClicked(){
        JavaFXMLApplication.closeStage.close();
        JavaFXMLApplication.mainStage.close();
    }
    
    public void CancelButtonClicked(){
        JavaFXMLApplication.closeStage.close();
    }
    

    public CloseAlertController() {
    }
    
}
