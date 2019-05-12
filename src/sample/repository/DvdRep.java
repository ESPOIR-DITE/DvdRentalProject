package sample.repository;

import com.sun.org.apache.xerces.internal.xs.StringList;
import sample.domain.DvdClass;
import sample.domain.serverPack.serve.Dvdservice;
import sample.domain.serverPack.serve.Dvdservice;

import java.sql.*;
import java.util.*;

public class DvdRep {

    private String url="jdbc:mysql://localhost:3306/mystudent?autoReconnect=true&useSSL=false";
    String user="root";
    String password="";
    Connection conne;
    ArrayList<String> dvdList=new ArrayList<String>();
    ArrayList<Dvdservice> dvdListObject=new ArrayList<Dvdservice>();
    Dvdservice dvdservice;
    String hautrentalNumber;

    String valeuD;
    String dvddtailsString;
    ArrayList<String>customerList=new ArrayList<String>();


    public DvdRep()
    {
        try {
            conne = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable()
    {
        try {
            PreparedStatement pstmt = conne.prepareStatement("CREATE TABLE DVD(DVD_NUMBER integer (4),TITLE VARCHAR(20),CATEGORY varchar(20),PRICE DOUBLE (8,2),NEW_RELEASE boolean,AVAILABLE boolean);");
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DVD TABLE CREATED");


    }
    public void read()
    { try {
        PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM RENTAL;");
        ResultSet rs=pstmt.executeQuery();
        while (rs.next())
        {
            dvdList.add(rs.getString("DVD_NUMBER"));

        }
        for(int i=0; i<dvdList.size();i++)
        {
            System.out.println(dvdList.get(i));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    }


    public String readTitle(String category)
    {
        try {dvdList.clear();
        PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM DVD where CATEGORY='"+category+"';");
        ResultSet rs=pstmt.executeQuery();
        while (rs.next())
        {
            dvdList.add(rs.getString("TITLE"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String []valeus= dvdList.toArray(new String[dvdList.size()]);
            String svalue1= Arrays.toString(valeus);
            System.out.println("check the response "+svalue1);

            String valeu2=svalue1.replace("[","");
            valeuD=valeu2.replace("[","");
            System.out.println("AFTER REMOVING BLOCKS "+valeuD);


    return valeuD;

    }


    public void update()
    {

    }
    public void delete(int dvdNumber)
    {
        String value ="DELETE FROM DVD WHERE (DVD_NUMBER="+dvdNumber+");";
        try {
            Statement pstmt = conne.createStatement();
            pstmt.executeUpdate(value);
            System.out.println(dvdNumber+" DELETED");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert(int dvdNumber, String title, String category, double price, boolean newRelease, boolean availableForRent)
    {
                                                                                                                //   DvdClass(int dvdNumber, String title, String category, double price, boolean newRelease, boolean availableForRent)
        String valeu3="INSERT INTO DVD(DVD_NUMBER,TITLE,CATEGORY,PRICE,NEW_RELEASE,AVAILABLE) VALUES("+dvdNumber+",'"+title+"','"+category+"','"+price+"',"+newRelease+","+availableForRent+");";
        try {

            Statement pstmt = conne.createStatement();
            pstmt.executeUpdate(valeu3);
            System.out.println("THE TABLE IS UPDATED");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteTable()
    {
        try {
            PreparedStatement pstmt = conne.prepareStatement("DROP TABLE DVD;");
            pstmt.execute();
            System.out.println("DVD TABLE IS DELETED");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void readRented()
    {
        try {
            PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM DVD where AVAILABLE=false ;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {
                dvdList.add(rs.getString("DVD_NUMBER"));

            }
            System.out.println("RENTED DVD");
            for(int i=0; i<dvdList.size();i++)
            {
                System.out.println(dvdList.get(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    public void readTiles()
//    {
//        try {
//            PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM DVD where AVAILABLE=true;");
//            ResultSet rs=pstmt.executeQuery();
//            while (rs.next())
//            {
//                dvdList.add(rs.getString("DVD_NUMBER"));
//
//            }
//            System.out.println("OUTSTANDING DVD");
//            for(int i=0; i<dvdList.size();i++)
//            {
//                System.out.println(dvdList.get(i));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void readOutstanding()
    {
        try {
            PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM DVD where AVAILABLE=true;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {
                dvdList.add(rs.getString("DVD_NUMBER"));

            }
            System.out.println("OUTSTANDING DVD");
            for(int i=0; i<dvdList.size();i++)
            {
                System.out.println(dvdList.get(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String readAll()
    {
        //dvdservice=new Dvdservice(rs.getString("TITLE"),rs.getString("CATEGORY"),rs.getBoolean("AVAILABLE"),rs.getDouble("PRICE"));

        try {
            dvdListObject.clear();
            customerList.clear();
            PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM DVD;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {
                //dvdList.add(rs.getString("DVD_NUMBER"));
                dvdservice=new Dvdservice(rs.getString("TITLE"),rs.getString("CATEGORY"),rs.getBoolean("AVAILABLE"),rs.getDouble("PRICE"));
                dvdListObject.add(dvdservice);
            }
            System.out.println("READING ALL DVD");
            for(int i=0; i<dvdListObject.size();i++)// just printing
            {
                //System.out.println(dvdListObject.get(i));
                Collections.sort(dvdListObject);
                System.out.println(dvdListObject.get(i));
            }
            /** converting the dvdList object to a String Array */
            for(int i=0; i<dvdListObject.size();i++) {
                customerList.add(String.format(""+dvdListObject.get(i)+"          "));
            }
            /** DONE. NOW COPING THE ARRAYLIST TO AN ARRAY THEN TO A STRING*/
            String []valeus=customerList.toArray(new String[customerList.size()]);
            String svalue= Arrays.toString(valeus);

            /**DONE    NOW I AM GOING TO REMOVE THE SOME UNWANTED CHARACTERS*/
            dvddtailsString=svalue.replace("[","");

            /** now we are going to return a String */

            System.out.println("printing out the string "+ svalue);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dvddtailsString;
    }


    public String readHight()  // tested!!!!
    {

        try {
            PreparedStatement pstmt = conne.prepareStatement("SELECT MAX(DVD_NUMBER)+1 FROM DVD;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {
                hautrentalNumber=rs.getString(1);
                System.out.println(hautrentalNumber);

            }
            try {


                if (hautrentalNumber.equals(null)) {
                    hautrentalNumber = String.format("0000");
                }
                //System.out.println(hautrentalNumber + " check if there is nothing in the table");
            }catch (NullPointerException e){ hautrentalNumber = String.format("1000");
               // System.out.println(hautrentalNumber + " check if there is nothing in the table\nafter exception");
                 }
/**
            System.out.println("READING ALL DVD");
            Collections.sort(customerList);
            String []valeus=customerList.toArray(new String[customerList.size()]);
            String svalue= Arrays.toString(valeus);
            StringTokenizer token = new StringTokenizer(svalue, ",");
            String yyyy = token.nextToken();
            hautrentalNumber=yyyy.replace("[","");

            int myInteger=Integer.parseInt(hautrentalNumber);
            myInteger=myInteger+1;
            String x= String.format(""+myInteger);
            hautrentalNumber=x;
            for(int i=0; i<customerList.size();i++)
            {
                System.out.println(customerList.get(i));
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hautrentalNumber;
    }

    public static void main(String[] args) {
        DvdRep dvdRep = new DvdRep();
        //dvdRep.deleteTable();
        //dvdRep.createTable();
        //System.out.println( dvdRep.readHight());

        //dvdRep.insert(7373,"zero","video",84,true,true);
       // dvdRep.delete(7373);
       // dvdRep.read();
        for(int i=0;i<5;i++){
        System.out.println(dvdRep.readTitle("ACTION"));}
       // System.out.println( dvdRep.readHight());
    }
}
