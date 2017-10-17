/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.*;
import javafx.stage.*;
import static javafxmlapplication.FXMLDocumentController.current_user;


/**
 * FXML Controller class
 *
 * @author Shahriar Ivan
 */
public class MainScreenController implements Initializable {
    
    private int q_num, c_num;

    @FXML
    public StackPane rootPane;
    
    @FXML
    public TableView<Word> wordTable;
    
    @FXML
    public TableColumn<Word, String> wordColumn; 
    
    public ObservableList<Word> wordList = FXCollections.observableArrayList();
    
    @FXML
    public AnchorPane questionPane, resultPane;
    
    @FXML
    public Button beginTestButton, choiceA, choiceB,choiceC,choiceD;
    
    @FXML
    public ImageView userImage;
    
    @FXML
    public Image userAvatar1 = new Image("images/user3.png");
    @FXML
    public Image userAvatar2 = new Image("images/user5.png");
    
    @FXML
    public Label currentUsername, currentUsergender, currentUserrating, incorrectOldPasswordText, newPasswordMismatchText, wordText, descriptionText, posText, tagText, synonymText, questionNum, questionText, correct_num, current_rating;
    
    @FXML
    public PasswordField oldPasswordInput, newPasswordInput, newPasswordConfirm;
    
    @FXML
    public TextField searchBox;
    
    
    public Vector<Word> Set = new Vector<Word>();
    public Vector<Question> Ques = new Vector<Question>();

    public MainScreenController() {
    }
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
       
        
        try {
            loadWords();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadQuestion("src/question.txt");
        
        showWordDetails(null); //clear labels
        
        
        //adds a listener for the word search box

        
        //adds a listener for generating word details
        wordTable.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> showWordDetails(newValue));
    }    
    
    @FXML
    public void BeginTestButtonClicked(ActionEvent event){
        q_num = 0;
        c_num = 0;
        
        beginTestButton.setVisible(false);
        questionPane.setVisible(true);
        
        questionNum.setText(Integer.toString(q_num+1));
        questionText.setText(Ques.elementAt(q_num).getDescription());
        choiceA.setText(Ques.elementAt(q_num).getChoiceA());
        choiceB.setText(Ques.elementAt(q_num).getChoiceB());
        choiceC.setText(Ques.elementAt(q_num).getChoiceC());
        choiceD.setText(Ques.elementAt(q_num).getChoiceD());
    }
    
    @FXML
    public void ChoiceButtonClicked(ActionEvent event){
        if(event.getSource()==choiceA){
            
            if(choiceA.getText().equals(Ques.elementAt(q_num).getCorrectAnswer())){
                System.out.println("correct");
                current_user.addRating(25);
                c_num++;
            }
            else{
                System.out.println("wrong");
                current_user.addRating(-25);
            }
        }
        else if(event.getSource()==choiceB){

            if(choiceB.getText().equals(Ques.elementAt(q_num).getCorrectAnswer())){
                System.out.println("correct");
                current_user.addRating(25);
                c_num++;
            }
            else{
                System.out.println("wrong");
                current_user.addRating(-25);
            }
        }
        else if(event.getSource()==choiceC){
            
            if(choiceC.getText().equals(Ques.elementAt(q_num).getCorrectAnswer())){
                System.out.println("correct");
                current_user.addRating(25);
                c_num++;
            }
            else{
                System.out.println("wrong");
                current_user.addRating(-25);
            }
        }
        else if(event.getSource()==choiceD){
            
            if(choiceD.getText().equals(Ques.elementAt(q_num).getCorrectAnswer())){
                System.out.println("correct");
                current_user.addRating(25);
                c_num++;
            }
            else{
                System.out.println("wrong");
                current_user.addRating(-25);
            }
        }
        
        q_num++;
        if(q_num>=10){
            questionPane.setVisible(false);
            resultPane.setVisible(true);
            correct_num.setText(Integer.toString(c_num));
            current_rating.setText(Integer.toString(FXMLDocumentController.current_user.getRating()));
            
            currentUserrating.setText(Integer.toString(current_user.getRating()));
            
            try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","ivan","ivan");

                    Statement stmt = conn.createStatement();

                    stmt.executeUpdate("UPDATE userTable set rating = "+Integer.toString(FXMLDocumentController.current_user.getRating())+" where username = '"+FXMLDocumentController.current_user.getName()+"'");
                    stmt.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
        }
        else{
            questionNum.setText(Integer.toString(q_num+1));
            questionText.setText(Ques.elementAt(q_num).getDescription());
            choiceA.setText(Ques.elementAt(q_num).getChoiceA());
            choiceB.setText(Ques.elementAt(q_num).getChoiceB());
            choiceC.setText(Ques.elementAt(q_num).getChoiceC());
            choiceD.setText(Ques.elementAt(q_num).getChoiceD());
        }
    }
    
    @FXML
    public void ChangePasswordButtonClicked(ActionEvent event){
        if(oldPasswordInput.getText().equals(FXMLDocumentController.current_user.getPass())){
            if(newPasswordInput.getText().equals(newPasswordConfirm.getText())){
                
                FXMLDocumentController.current_user.setPass(newPasswordInput.getText());
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","ivan","ivan");

                    Statement stmt = conn.createStatement();

                    stmt.executeUpdate("UPDATE userTable set password = '"+newPasswordInput.getText()+"' where username = '"+FXMLDocumentController.current_user.getName()+"'");
                    stmt.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                
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
    
    private void loadWords() throws IOException{
        wordColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        wordColumn.setMinWidth(100);
        
        wordTable.setItems(formWords());
    }
    
    public ObservableList<Word> formWords() throws IOException{
        
        magic("src/word.txt");
        magic("src/word2.txt");
        showWordTable("");
        searchBox.textProperty().addListener( (observable, oldValue, newValue) -> showWordTable(searchBox.getText()));
        return wordList;
    }
    public void magic(String str){
        FileReader in = null;
        BufferedReader buf = null;

        try {
             in = new  FileReader(new File(str));
             buf = new BufferedReader(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int FileInputFinder = 1;;
        while(FileInputFinder == 1){
            try {
                String _name,_desp,_pos,_tag,_syn,_int;
                
                if( (_name = buf.readLine()) == null){
                    break;
                }
                _desp = buf.readLine();
                _pos = buf.readLine();
                _syn = buf.readLine();
                _tag = buf.readLine();
                _int  = buf.readLine();
                Set.add(new Word(_name,_desp,_pos, _tag, _syn, _int));
              
//                System.out.println( _name + " ");
//                System.out.println( _desp + " ");
//                System.out.println( _pos + " ");
//                System.out.println( _syn + " ");                
//                System.out.println( _tag + " ");
//                System.out.println( _int + " ");                
                //int temp = Integer.parseInt(_int);
                
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    public void showWordDetails(Word word){
        if(word!=null){
            wordText.setText(word.getName());
            descriptionText.setText(word.getDescription());
            posText.setText(word.getPos());
            tagText.setText(word.getTag());
            synonymText.setText(word.getSynonym());
            
        }
        else{
            wordText.setText("");
            descriptionText.setText("");
            posText.setText("");
            tagText.setText("");
            synonymText.setText("");
            
        }
    }
    
    public void showWordTable(String str){
        
        System.out.println(str);
        wordList.clear();
        for(Word I : Set){
            String s  = I.getName();
            
            if(str.length() == 0){
                wordList.add(I);
                continue;
            }
            for(int i = 0;i<s.length();i++){
                for(int j = i;j<s.length();j++){
                    String temp = s.substring(i, j + 1);
                    int len = j - i + 1;
                    if(temp.equals(str) == true && (len > 1 || i==0)){
                        wordList.add(I);
                        break;
                    }
                }
            }
        }
    }
    public Vector<String> Shuffle(Vector<String>v){
        Random rand = new Random();

        Vector<String>vec = new Vector<>();
        for(int i = 4;i>0;i--){
            int r = rand.nextInt(i);
            vec.add(v.elementAt(r));
            v.removeElementAt(r);
        }
        return vec;
    }
    
    public Vector<Question> ShuffleQuestions(Vector<Question> v){
        Random rand = new Random();

        Vector<Question>vec = new Vector<>();
        int lim = v.size();
        for(int i = lim;i>0;i--){
            int r = rand.nextInt(i);
            vec.add(v.elementAt(r));
            v.removeElementAt(r);
        }        
        return vec;
    }
    
    public void loadQuestion(String str){
        FileReader in = null;
        BufferedReader buf = null;

        try {
             in = new  FileReader(new File(str));
             buf = new BufferedReader(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int FileInputFinder = 1;;
        while(FileInputFinder == 1){
            try {
                String desp,correct,temp;
                Vector<String>vec = new Vector<String>();
                
                if( (desp = buf.readLine()) == null){
                    break;
                }
                temp = buf.readLine();
                correct = temp;
                vec.add(temp);
                temp = buf.readLine();
                vec.add(temp);
                temp = buf.readLine();
                 vec.add(temp);
                temp = buf.readLine();
                vec.add(temp);
                
                vec = Shuffle(vec);
                Ques.add(new Question(desp,correct,vec.elementAt(0),vec.elementAt(1),vec.elementAt(2),vec.elementAt(3)));
                    
                
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Ques = ShuffleQuestions(Ques);
        for(int i = 0;i< Ques.size();i++){
            System.out.println(Ques.elementAt(i).getDescription());
        }
    }
    
    
}
