/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

/**
 *
 * @author Shahriar Ivan
 */
public class Question {
    String description;
    String correct_ans;
    String choiceA;
    String choiceB;
    String choiceC;
    String choiceD;

    public Question() {
        ;
    }
    
    public Question(String description, String correct_ans, String choiceA, String choiceB, String choiceC, String choiceD){
        this.description = description;
        this.correct_ans = correct_ans;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getCorrectAnswer(){
        return correct_ans;
    }
    
    public String getChoiceA(){
        return choiceA;
    }
    
    public String getChoiceB(){
        return choiceB;
    }
    
    public String getChoiceC(){
        return choiceC;
    }
    
    public String getChoiceD(){
        return choiceD;
    }
        
}
