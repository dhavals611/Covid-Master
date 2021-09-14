package com.example.yo7a.healthwatcher;

public class Covid_Symtoms {
    int heart_rate=0;
    int RR_Rate=0;
    int fever=0;
    String cough="None";

    Covid_Symtoms()
    {
        this.heart_rate =0;
        this.RR_Rate = 0;
        this.fever=0;
        this.cough = "None";
    }
    Covid_Symtoms(int heart_rate,int RR_Rate,int fever,String cough)
    {
        this.heart_rate=heart_rate;
        this.RR_Rate = RR_Rate;
        this.fever = fever;
        this.cough = cough;
    }

    public int getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate) {
        this.heart_rate = heart_rate;
    }

    public int getRR_Rate() {
        return RR_Rate;
    }

    public void setRR_Rate(int RR_Rate) {
        this.RR_Rate = RR_Rate;
    }

    public int getFever() {
        return fever;
    }

    public void setFever(int fever) {
        this.fever = fever;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }
}
