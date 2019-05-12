package sample.repository;

import com.sun.org.apache.xerces.internal.xs.StringList;
import sample.domain.serverPack.serve.CustomerService;
import sample.domain.serverPack.serve.Dvdservice;
import sample.domain.serverPack.serve.HighValeu;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class CustomerRep {

    private String url="jdbc:mysql://localhost:3306/mystudent?autoReconnect=true&useSSL=false";
    String user="root";
    String password="";
    Connection conne;
    HighValeu highValeu;
    String svalue;
    String sval;

    CustomerService customerService;
    ArrayList<String>customerList=new ArrayList<String>();
    ArrayList<CustomerService>customerListObject=new ArrayList<CustomerService>();
    ArrayList <String>sansType=new ArrayList<String>();
    ArrayList<HighValeu> gethight=new ArrayList< >();

    Statement rs;
    String hautrentalNumber;
    String svaluex;

    public CustomerRep()  {
        try {
            conne = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable()/*int custNumber, String firstName, String surname, String phoneNum, double credit, boolean canRent*/
    {
        try {
            PreparedStatement pstmt = conne.prepareStatement("CREATE TABLE CUSTOMER(CUSTOMER_NUMBER integer (4),FIRST_NAME VARCHAR(20),SURNAME varchar(20),PHONE_NUMBER VARCHAR(20),CREDIT DOUBLE (8,2),CAN_RENT boolean);");
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("CUSTOMER TABLE CREATED");

    }
    public String read()  // tested!!!!
    {

        try {
            customerList.clear();
            String sqlS="SELECT MAX(CUSTOMER_NUMBER)+1 FROM CUSTOMER;";
            Statement pstmt = conne.createStatement();
            ResultSet rs=pstmt.executeQuery(sqlS);
            while (rs.next())
            {
                hautrentalNumber=rs.getString(1);

                System.out.println(hautrentalNumber);
               /** highValeu=new HighValeu();
                highValeu.setHigh(Integer.parseInt(rs.getString("CUSTOMER_NUMBER")));
                gethight.add(highValeu);
                //customerList.add(highValeu.getHigh());
                */
            }
             try {


             if (hautrentalNumber.equals(null)) {
             hautrentalNumber = String.format("0000");
             }
             //System.out.println(hautrentalNumber + " check if there is nothing in the table");
             }catch (NullPointerException e){ hautrentalNumber = String.format("1000");
             // System.out.println(hautrentalNumber + " check if there is nothing in the table\nafter exception");
             }/**
            Collections.sort(gethight);
            for(int i=0;i<gethight.size();i++)
            {
                customerList.add(String.format(""+gethight.get(i)));
            }
            for(int i=0; i<gethight.size();i++)
            {
                System.out.println(gethight.get(i)+" the get hight one");
            }
            for(int i=0; i<customerList.size();i++)
            {
                System.out.println(customerList.get(i)+" the get string one");
            }
            System.out.println("READING ALL DVD");


            String []valeus=customerList.toArray(new String[customerList.size()]);
            String svalue= Arrays.toString(valeus);
            StringTokenizer token = new StringTokenizer(svalue, ",");
            String yyyy = token.nextToken();
            hautrentalNumber=yyyy.replace("[","");

            int myInteger=Integer.parseInt(hautrentalNumber);
            myInteger=myInteger+1;
            String x=String.format(""+myInteger);
            hautrentalNumber=x;
            for(int i=0; i<customerList.size();i++)
            {
                System.out.println(customerList.get(i));
            }
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hautrentalNumber;
    }
    public void update()
    {

    }
    public void delete( int custNumber)
    {
        String value ="DELETE FROM CUSTOMER WHERE (CUSTOMER_NUMBER="+custNumber+");";
        try {
            Statement pstmt = conne.createStatement();
            pstmt.executeUpdate(value);
            System.out.println(custNumber+" DELETED");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(int custNumber, String firstName, String surname, String phoneNum, double credit, boolean canRent)
    {

       String valeu3="INSERT INTO CUSTOMER(CUSTOMER_NUMBER,FIRST_NAME,SURNAME,PHONE_NUMBER,CREDIT,CAN_RENT) VALUES("+custNumber+",'"+firstName+"','"+surname+"','"+phoneNum+"',"+credit+","+canRent+");";
        try {

            Statement pstmt = conne.createStatement();
            pstmt.executeUpdate(valeu3);
            System.out.println("THE TABLE IS UPDATED");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deleteCustomerTable()
    {
        try {
            PreparedStatement pstmt = conne.prepareStatement("DROP TABLE CUSTOMER;");
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String readAll()
    {
        //dvdservice=new Dvdservice(rs.getString("TITLE"),rs.getString("CATEGORY"),rs.getBoolean("AVAILABLE"),rs.getDouble("PRICE"));

        try {
            customerList.clear();
            customerListObject.clear();
            PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM CUSTOMER;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {                                                               //CUSTOMER_NUMBER,FIRST_NAME,SURNAME,PHONE_NUMBER,CREDIT,CAN_RENT)
                //dvdList.add(rs.getString("DVD_NUMBER"));    (int custNumber, String firstName, String surname, double credit)
                customerService= new CustomerService(rs.getInt("CUSTOMER_NUMBER"),rs.getString("FIRST_NAME"),rs.getString("SURNAME"),rs.getDouble("CREDIT"));
                customerListObject.add(customerService);
            }
            System.out.println("READING ALL DVD");
            for(int i=0; i<customerListObject.size();i++)
            {
                //System.out.println(dvdListObject.get(i));
                Collections.sort(customerListObject);
                System.out.println(customerListObject.get(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        /** converting the dvdList object to a String Array */
        for(int i=0; i<customerListObject.size();i++) {
            customerList.add(String.format(""+customerListObject.get(i)+"          "));
        }
        /** DONE. NOW COPING THE ARRAYLIST TO AN ARRAY THEN TO A STRING*/
        String []valeus=customerList.toArray(new String[customerList.size()]);
        svalue= Arrays.toString(valeus);

        return svalue;
    }
    public String readAllColumn()
    {
        //dvdservice=new Dvdservice(rs.getString("TITLE"),rs.getString("CATEGORY"),rs.getBoolean("AVAILABLE"),rs.getDouble("PRICE"));

        try {
            sansType.clear();
            PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM RENTAL order by RENT_NUMBER;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {

                sansType.add(rs.getString(1));

            }
            System.out.println("READING ALL DVD");
            for(int i=0; i<sansType.size();i++)
            {

                System.out.println(sansType.get(i));
            }
            System.out.println("testing the arry\n\n\n");
           // Collections.sort(sansType);//
            String []valeus=sansType.toArray(new String[sansType.size()]);
             svaluex= Arrays.toString(valeus);
              String aluex=svaluex.replace("[","");
           sval=aluex.replace(" ","");
            System.out.println("into a string: "+sval );
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return sval;
}
    public String readACustColumn()
    {
        //dvdservice=new Dvdservice(rs.getString("TITLE"),rs.getString("CATEGORY"),rs.getBoolean("AVAILABLE"),rs.getDouble("PRICE"));

        try {
            sansType.clear();
            PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM CUSTOMER order by CUSTOMER_NUMBER;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {

                sansType.add(rs.getString("CUSTOMER_NUMBER"));

            }
            System.out.println("READING ALL DVD");
            for(int i=0; i<sansType.size();i++)
            {

                System.out.println(sansType.get(i));
            }
            System.out.println("testing the arry\n\n\n");
            // Collections.sort(sansType);//
            String []valeus=sansType.toArray(new String[sansType.size()]);
            svaluex= Arrays.toString(valeus);
            String aluex=svaluex.replace("[","");
            sval=aluex.replace(" ","");
            System.out.println("into a string: "+sval );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sval;}


    public static void main(String[] args) {
        CustomerRep customerRep = new CustomerRep();
        //customerRep.createTable();
        //System.out.println(customerRep.read());
        /*int custNumber, String firstName, String surname, String phoneNum, double credit, boolean canRent*/
       // customerRep.read();
    // customerRep.insert(3233,"kanyi","diteo","0617825205",8484,true);
       // customerRep.delete(28282);
       // customerRep.readAll();
       // for(int i=0;i<5;i++){
       // customerRep.deleteCustomerTable();
        System.out.println(customerRep.readACustColumn());
    }

}
