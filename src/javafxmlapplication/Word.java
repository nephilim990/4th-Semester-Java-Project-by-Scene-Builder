/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.util.Vector;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Shahriar Ivan
 */
public class Word {
    StringProperty name;
    StringProperty description;
    StringProperty pos;
    StringProperty tag;
    StringProperty synonym;
    StringProperty difficulty;
    
    public Word(){
        ;
    }

    public Word(String name) {
        this.name = new SimpleStringProperty(name);
    }
    public Word(String name,String description,String pos,String tag,String synonym,String difficulty) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.pos = new SimpleStringProperty(pos);
        this.tag = new SimpleStringProperty(tag);
        this.synonym = new SimpleStringProperty(synonym);
        this.difficulty = new SimpleStringProperty(difficulty);
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    public StringProperty descriptionProperty(){
        return description;
    }
    public StringProperty tagProperty(){
        return tag;
    }    
    public StringProperty synProperty(){
        return synonym;
    }    
     public StringProperty posProperty(){
        return pos;
    }    
    public String getName(){
        return name.get();
    }
    public String getDescription(){
        return description.get();
    }
    public String getTag(){
        return tag.get();
    }
    public String getPos(){
        return pos.get();
    }
    public String getSynonym(){
        return synonym.get();
    }
    public String getDifficulty(){
        return difficulty.get();
    }
    public StringProperty difficultyProperty(){
        return difficulty;
    }
    
    
    
}
