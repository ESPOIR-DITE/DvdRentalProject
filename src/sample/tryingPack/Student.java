package sample.tryingPack;

import sample.ReturnController;
import sample.domain.Rental;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Student {
    private String url="jdbc:mysql://localhost:3306/mystudent?autoReconnect=true&useSSL=false";
    String user="root";
    String password="";
    Connection conne;
    ArrayList<String>mylist = new ArrayList<String>();
    public void getdata(){


        {
            try {
                conne = DriverManager.getConnection(url,user,password);
                Statement statement=conne.createStatement();
                String sql = "select * from STUDENT;";
                ResultSet rsql= statement.executeQuery(sql);
                while (rsql.next())
                {
                    System.out.println(rsql.getString(1)+" "+rsql.getString(2));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (NullPointerException e)
            {
                System.out.println("null recontre dans student class methode getData");
            }
        }}
        public void toke()
        {

            String valeu="efee/sdsd/sdsd/ewew/";
            StringTokenizer token = new StringTokenizer(valeu, "#");

            String nextValeu="wu";
            token = new StringTokenizer(valeu, "/");
            try{
            while (nextValeu!=null)
            {


                nextValeu = token.nextToken();
                mylist.add(nextValeu);
                System.out.println("**"+nextValeu);
        }}catch (NoSuchElementException e){
                System.out.println("contitnue");
            }
            System.out.println("je suis vivant");
            }

            public void tryingStrings()
            {
                String espoir="espoir";
                int v=espoir.length();
                System.out.println(v);
                int x=v-1;
                double w=20;
                double r=10;
                double vw=w-r;
                //JOptionPane.SELECTION_VALUES_PROPERTY

//                String svsv=v.subString(0,6);
//                substring();
            }

    public static void main(String[] args) {
        //Student st = new Student();
        Rental rental=new Rental();
        System.out.println(rental.numberOfDaysOverdue("10/12/2012","10/12/2012"));
        //ReturnController returnController=new ReturnController();
        //returnController.getDate();
        //st.tryingStrings();
    }
}

