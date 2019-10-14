package com.example.login;

public class Customer {

    private String email;
    private String name;
    private String password;
    private String tier;
    private int numPointsAvailable;

    public Customer(String email, String name, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Customer(String email, String name, String password, String tier, int numPointsAvailable){
        this.name = name;
        this.email = email;
        this.password = password;
        this.tier = tier;
        this.numPointsAvailable = numPointsAvailable;
    }

    public String getEmail(){return email;}

    public void setEmail(String email){this.email = email; }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getTier(){return tier;}

    public void setTier(String tier){this.tier = tier;}

    public int getNumPointsAvailable(){return numPointsAvailable;}

    public void setNumPointsAvailable(int numPointsAvailable){this.numPointsAvailable = numPointsAvailable;}
}
