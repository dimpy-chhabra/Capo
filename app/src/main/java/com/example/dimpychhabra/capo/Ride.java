package com.example.dimpychhabra.capo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dimpy Chhabra on 3/25/2017.
 */

public class Ride {

    private String r_id;
    public String driver_id;
    private String start_loc;
    private String desti_loc;
    private String start_time  ;
    private String exp_desti_time  ;
    private String no_seats ;
    private String no_seats_occ  ;
    private String extras  ;
    private String[] ppat= new String [10];  //pickupPointAndTime : max 10
    private String[] occupents = new String[5]; //max 5 riders per car : otherwise its being commercialized
   // private Context context;

    public Ride(){

    }

    public Ride(String startLoc, String DestiLoc) {
        start_loc = startLoc;
        desti_loc = DestiLoc;
        no_seats = "3";
        start_time = "10am";
        exp_desti_time = "11am";

    }


    public String getR_id( String mobNo){
        return r_id;
    }
    public String getStart_loc( String mobNo){
        return start_loc;
    }
    public String getDesti_loc( String mobNo){
        return desti_loc;
    }
    public String getStart_time( String mobNo){
        return start_time;
    }
    public String getExp_desti_time( String mobNo){
        return exp_desti_time;
    }
    public String getNo_seats( String mobNo){
        return no_seats;
    }
    public String getNo_seats_occ( String mobNo){
        return no_seats_occ;
    }
    public String getExtras( String mobNo){
        return extras;
    }

    public void putDriver_id( String mobNo){
        driver_id = mobNo;
    }
    public void putR_id( String mobNo, String R_ID){
        r_id = R_ID;
    }
    public void putStart_loc( String mobNo, String Start_LOC){
        start_loc = Start_LOC;
    }
    public void putDesti_loc( String mobNo, String Desti_lo){
        desti_loc = Desti_lo;
    }
    public void putStart_time( String mobNo, String Start_Time){
        start_time = Start_Time;
    }
    public void putExp_desti_time( String mobNo, String Exp_Desti_Time){
        exp_desti_time = Exp_Desti_Time;
    }
    public void putNo_seats( String mobNo, String No_Of_Seats){
        no_seats = No_Of_Seats;
    }
    public void putNo_seats_occ( String mobNo, String No_Seats_OCCUPIED){
        no_seats_occ = No_Seats_OCCUPIED;
    }
    public void putExtras( String mobNo, String Extras){
        extras = Extras;
    }





}