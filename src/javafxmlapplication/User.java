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
public class User {
    private String name;
    private String pass;
    private String gender;
    private int rating;
    

    public User(String name, String pass, String gender, int rating) {
        this.name = name;
        this.pass = pass;
        this.gender = gender;
        this.rating = rating;
    }
    public User() {
        this.name = "";
        this.pass = "";
        this.gender = "";
        this.rating = 0;
    }
    
    public void setUser(String _name, String _pass, String _gender, int _rating) {
        name = _name;
        pass = _pass;
        gender = _gender;
        rating = _rating;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public void setRating(int rating){
        this.rating = rating;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPass(){
        return pass;
    }
    
    public String getGender(){
        return gender;
    }
    
    public int getRating(){
        return rating;
    }
    public void addRating(int val){
        rating  += val;
    }
}
