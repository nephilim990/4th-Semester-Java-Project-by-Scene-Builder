/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.util.Vector;
import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.*;


/**
 *
 * @author Shahriar Ivan
 */
public class FXMLDocumentController implements Initializable {

    
    @FXML
    public StackPane rootPane;
    
    static Vector<User> user = new Vector<>();
    static User current_user;
    
    //initialize is called as soon as the view loads into the screen
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadUserFile();
        
        FileReader in;
        BufferedReader buf = null;

        try {
             in = new  FileReader(new File("src/user.txt"));
             buf = new BufferedReader(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        current_user = new User();
        int FileInputFinder = 1;
        while(FileInputFinder == 1){
            try {
                String a;             
                if( (a = buf.readLine()) == null){
                    break;
                }
                String b = buf.readLine();
                String c = buf.readLine();
                String d = buf.readLine();
                user.addElement(new User(a,b,c,Integer.parseInt(d)));
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
    }
    
        
    @FXML
    public TextField usernameInput;
    public TextField passwordInput;
    public Label passwordInvalidText;
    
    @FXML
    public void loginButtonClicked(ActionEvent event) throws IOException {
        if(validatePassword(user,usernameInput.getText(),passwordInput.getText()))
        {
            passwordInput.setText("");
            passwordInvalidText.setVisible(false);
            loadMainScreen();
        }
        else
        {
            passwordInput.setText("");
            passwordInvalidText.setVisible(true);
        }
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
    
    @FXML
    public void signupLinkClicked(ActionEvent event) throws IOException {
        loadSignUpScreen();
    }
    
    
    
    //validate Password - Log In Screen
    private boolean validatePassword(Vector<User>user, String name, String pass){
        
        boolean found=false;
        for(int i=0;i<user.size();i++)
        {
            if(name.equals(user.elementAt(i).getName()) && pass.equals(user.elementAt(i).getPass())){
                current_user = user.elementAt(i);
                found = true;
                break;
                
            }            
        }
        
        if(found)
            return true;
        else
            return false;
    }
    
    
    //load Sign Up Screen
    private void loadSignUpScreen() throws IOException{
        StackPane pane = FXMLLoader.load(getClass().getResource("SignUpScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
     //load Main Screen
    private void loadMainScreen() throws IOException{
        StackPane pane = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    //load user.txt from database
    private void loadUserFile(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/user.txt"));
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","ivan","ivan");

            Statement stmt = conn.createStatement();

            ResultSet rset = stmt.executeQuery("select * from userTable");
            while(rset.next()){
                String username = rset.getString(1);
                String password = rset.getString(2);
                String gender = rset.getString(3);
                int rating = rset.getInt(4);
                System.out.println(username+" "+password+" "+gender+" "+rating);
                
                //writing each tuple as four lines in user.txt
                bw.write(username);
                bw.newLine();
                bw.write(password);
                bw.newLine();
                bw.write(gender);
                bw.newLine();
                bw.write(Integer.toString(rating));
                bw.newLine();
                
            }
            bw.close();
            stmt.close();
            conn.close();
        }
        catch(Exception sqle){
            System.out.println("SQLException: "+sqle);
        }
    }
    
}
