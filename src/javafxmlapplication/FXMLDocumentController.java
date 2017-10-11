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
        
        /*
        //testing database connectivity
        System.out.println("Testing");
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","ivan","ivan");

            Statement stmt = conn.createStatement();
            
//            stmt.executeUpdate("CREATE TABLE Dept(d_id int primary key,dept_name char(3))");
//            stmt.executeUpdate("insert into Dept values('1','MCE')");
//            stmt.executeUpdate("insert into Dept values('2','EEE')");
//            stmt.executeUpdate("insert into Dept values('3','TVE')");
//            stmt.executeUpdate("insert into Dept values('4','CSE')");
//            stmt.executeUpdate("insert into Dept values('5','CEE')");
//            stmt.executeUpdate("delete from Dept where d_id='3'");

            ResultSet rset = stmt.executeQuery("select * from Dept order by d_id");
            while(rset.next()){
                int id = rset.getInt(1);
                String dept_name = rset.getString(2);
                System.out.println(id+" "+dept_name);
            }
            stmt.close();
            conn.close();
        }
        catch(Exception sqle){
            System.out.println("SQLException: "+sqle);
        }
        System.out.println("Testing ended");
        */
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
    
}
