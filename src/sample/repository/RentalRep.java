package sample.repository;

import sample.domain.serverPack.serve.Dvdservice;
import sample.domain.serverPack.serve.HighValeu;
import sample.domain.serverPack.serve.RentalService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class RentalRep {
        private String url="jdbc:mysql://localhost:3306/mystudent?autoReconnect=true&useSSL=false";
        String user="root";
        String password="";
        Connection conne;
        ArrayList<String> dvdList=new ArrayList<String>();
        ArrayList<RentalService> dvdListObject=new ArrayList<RentalService>();
        ArrayList<String> StringList=new ArrayList<String>();
        Dvdservice dvdservice;
        RentalService rentalService;
    String hautrentalNumber,svaluex,sval,string1,credi,string2,resul;

    double creditD;
    String date;
    int rentalNumbe;
    int CUSTNUMBER;

        //i'm using thios valeu to save in the highest value
    int myhighestvaleu;

        public RentalRep()
        {
            try {
                conne = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void createTable()//OK
        {
            try {// return "Rental#:" + rentalNumber + "  Date Rented:" + dateRented + "   Date Returned:" + dateReturned + "\nPenalty cost per day:R" + PENALTY_COST_PER_DAY + "  Total Penalty Cost:R" +
               // totalPenaltyCost + "  Customer#:" + custNumber + "  Movie#:" + dvdNumber +"\nNo of Days overdue:" + numberOfDaysOverdue()+"\n";
                PreparedStatement pstmt = conne.prepareStatement("CREATE TABLE RENTAL(RENT_NUMBER integer (4),CUSTOMER_NUMBER INTEGER(4),DVD_NUMBER INTEGER(4),RENT_DATE VARCHAR(20),RETURN_DATE varchar(25));");
                pstmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("RENTAL TABLE CREATED");


        }
        public void read()// NOT YET
        { try {
            PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM RENTAL;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {
                rentalService=new RentalService(rs.getInt("RENT_NUMBER"),rs.getString("RENT_DATE"),rs.getString("RETURN_DATE"),rs.getInt("CUSTOMER_NUMBER"),rs.getInt("DVD_NUMBER"));
                dvdListObject.add(rentalService);

            }
            for(int i=0; i<dvdListObject.size();i++)
            {
                Collections.sort(dvdListObject);
                System.out.println(dvdListObject.get(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        }
        public void update(int rentalNumber,String retunDate)//OK
        {
            String value ="UPDATE RENTAL SET RETURN_DATE='"+retunDate+"' WHERE RENT_NUMBER="+rentalNumber+";";
            try {
                Statement pstmt = conne.createStatement();
                pstmt.executeUpdate(value);
                System.out.println(rentalNumber+" UPDATED WITH"+retunDate);
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }
        public void delete(int rentalNumber)//OK
        {
            String value ="DELETE FROM RENTAL WHERE (RENT_NUMBER="+rentalNumber+");";
            try {
                Statement pstmt = conne.createStatement();
                pstmt.executeUpdate(value);
                System.out.println(rentalNumber+" DELETED");
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }
        public void insert(int  rentalNumber, String dateRented,String returnDate, int custNumber , int dvdNumber)//ok
        {
            //   DvdClass(int dvdNumber, String title, String category, double price, boolean newRelease, boolean availableForRent)
            String valeu3="INSERT INTO RENTAL(RENT_NUMBER,CUSTOMER_NUMBER,DVD_NUMBER,RENT_DATE,RETURN_DATE) VALUES("+rentalNumber+",'"+custNumber+"','"+dvdNumber+"','"+dateRented+"','"+returnDate+"');";
            try {

                Statement pstmt = conne.createStatement();
                pstmt.executeUpdate(valeu3);
                System.out.println("ROW INSERTED");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void deleteTable()//OK
        {
            try {
                PreparedStatement pstmt = conne.prepareStatement("DROP TABLE RENTAL;");
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

       /* public void readOneValeu()
        {
            //dvdservice=new Dvdservice(rs.getString("TITLE"),rs.getString("CATEGORY"),rs.getBoolean("AVAILABLE"),rs.getDouble("PRICE"));

            try {
                PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM RENTAL;");
                ResultSet rs=pstmt.executeQuery();
                while (rs.next())
                {
                    //dvdList.add(rs.getString("DVD_NUMBER"));
                    //dvdservice=new Dvdservice(rs.getString("TITLE"));
                   // dvdListObject.add(dvdservice);
                }
                System.out.println("READING ALL DVD");
                for(int i=0; i<dvdListObject.size();i++)
                {
                    //System.out.println(dvdListObject.get(i));
                    Collections.sort(dvdListObject);
                    System.out.println(dvdListObject.get(i));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }*/
    public String hightValeu() //TRYING TO GET THE HIGHEST VALEU FROM THE RENTAL NUMBER// !!!will need to check if the table is empty
    {

        try {
            String sqlstr="SELECT MAX(RENT_NUMBER) FROM RENTAL;";
            Statement pstmt = conne.createStatement();
            ResultSet rs=pstmt.executeQuery(sqlstr);
            while (rs.next())
            {

                hautrentalNumber=rs.getString(1);

            }try {int entalNumbe=Integer.parseInt(hautrentalNumber)+1;

                hautrentalNumber = String.format("" + entalNumbe);
            }catch (NullPointerException e){ hautrentalNumber = String.format("1000");
                // System.out.println(hautrentalNumber + " check if there is nothing in the table\nafter exception");
            }catch (NumberFormatException y){}
            try {


                if (hautrentalNumber.equals(null)) {
                    hautrentalNumber = String.format("1000");
                }
                //System.out.println(hautrentalNumber + " check if there is nothing in the table");
            }catch (NullPointerException e){ hautrentalNumber = String.format("1000");
                // System.out.println(hautrentalNumber + " check if there is nothing in the table\nafter exception");
            }
            /**IN THE FOLLOWING BLOCK WE FIRST SORT THE ARRAYLIST
            *SECONDLY WE CONVERT THE ARRAYLIST TO AN ARRAY OF STRINGS
            *THIRDLY WE CONVERT THE ARRAY TO A STRING
            * FORTHLY WE TOKENIZE JUST TO GET THE FIRST VALUE FROM THE STRING
            * SIXTHLY WE REMOVE THE FIRST CHARACTOR FROM THE STRING
            * FINALLY WE CONVERT IT TO INTEGER +1


            System.out.println("READING ALL DVD");
            Collections.sort(highlist);
            String []valeus=StringList.toArray(new String[StringList.size()]);
            String svalue= Arrays.toString(valeus);
            StringTokenizer token = new StringTokenizer(svalue, ",");
            String yyyy = token.nextToken();
            hautrentalNumber=yyyy.replace("[","");
            int myInteger=Integer.parseInt(hautrentalNumber);
            myInteger=myInteger+1;
            System.out.println("check"+hautrentalNumber+" :after replacement");
            System.out.println(myInteger);          */                //********************THE VALEU TO SENT TO THE CLIENT**********************
          /*
          IN THIS BLOCK!!!
           */

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hautrentalNumber;
    }

    public String readAllColumn()
    {
        //dvdservice=new Dvdservice(rs.getString("TITLE"),rs.getString("CATEGORY"),rs.getBoolean("AVAILABLE"),rs.getDouble("PRICE"));

        try {
            StringList.clear();
            PreparedStatement pstmt = conne.prepareStatement("SELECT * FROM RENTAL order by DVD_NUMBER;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {                                                               //CUSTOMER_NUMBER,FIRST_NAME,SURNAME,PHONE_NUMBER,CREDIT,CAN_RENT)
                //dvdList.add(rs.getString("DVD_NUMBER"));    (int custNumber, String firstName, String surname, double credit)
                StringList.add(rs.getString("CUSTOMER_NUMBER"));
                //customerService.setCustNumber(rs.getInt("CUSTOMER_NUMBER"));
                //customerService= new CustomerService(rs.getInt("CUSTOMER_NUMBER"));
                //customerListObject.add(rs.getInt("CUSTOMER_NUMBER"));
            }
            System.out.println("READING ALL DVD");
            for(int i=0; i<StringList.size();i++)
            {
                //System.out.println(dvdListObject.get(i));

                System.out.println(StringList.get(i));
            }
            System.out.println("testing the arry\n\n\n");
            // Collections.sort(sansType);//
            String []valeus=StringList.toArray(new String[StringList.size()]);
            svaluex= Arrays.toString(valeus);
            String aluex=svaluex.replace("[","");
            sval=aluex.replace(" ","");
            System.out.println("into a string: "+sval );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sval;
    }
    public String checker(int custN)// this method will return the customer number and the data rented then the customer credit
    {
        System.out.println("into checking");
        String getCustNumber_date="SELECT * FROM RENTAL WHERE RENT_NUMBER="+custN+";";
        System.out.println("sql statement excuted");
        try {
            Statement pstmt = conne.createStatement();
            ResultSet rs=pstmt.executeQuery(getCustNumber_date);
            while (rs.next())
            {
               string1=rs.getString(2); /**customer number*/
                string2=rs.getString(4); /** date*/
                System.out.println(string1+"RENTED DVD"+string2);   //
            }} catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("SELECT * FROM RENTAL WHERE RENT_NUMBER=\"+custN+\";\">>>>> SUCCESSFUL");
        try {
            /**now trying to get the credit of the customer*/
            String sqlCredit="SELECT * FROM CUSTOMER WHERE CUSTOMER_NUMBER="+string1+";";
            Statement pstmt1 = conne.createStatement();
            ResultSet rs1=pstmt1.executeQuery(sqlCredit);
            while (rs1.next())
            {
                credi=rs1.getString(5);/**credit*/
                System.out.println(credi+" CREDIT OF CUSTOMER");
            }

            for(int i=0; i<dvdList.size();i++)
            {
                System.out.println(dvdList.get(i));
            }
            System.out.println("SELECT * FROM CUSTOMER WHERE CUSTOMER_NUMBER=\"+string1+\";\";>>>>>  successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resul=String.format(string1+"#"+string2+"#"+credi);   /**returning customer number date of renting and the credit of the customer*/

return resul;
    }
    public void mulitpleUpdate(String updater)
    {//System.out.println("balance:>>>"+balance+"actual date:>>>"+getDate()+" rental Number>>>"+cust+"customerNumber>>>>: "+custnumber);
        //"o#"+balance+"#"+getDate()+"#"+cust+"#"+custnumber);
        System.out.println(updater+"  reception");
    try{
        StringTokenizer token = new StringTokenizer(updater, "#");
        if(updater!=null)
        {
            String va1=token.nextToken();
            creditD=Double.parseDouble(token.nextToken());
            date=token.nextToken();
            rentalNumbe = Integer.parseInt(token.nextToken());
            CUSTNUMBER = Integer.parseInt(token.nextToken());
            System.out.println("elements of update "+creditD+" "+date+" "+rentalNumbe+" "+CUSTNUMBER);

        }}
    catch (NullPointerException w){
        System.out.println("null point exceptions");
    }
    catch (NumberFormatException n){
        System.out.println("number format exception");
    }
        System.out.println();


        /**need to get the dvd number from the retal table */
    /**    try {
            credi=null;
            String sqlCredit1="SELECT * FROM RENTAL WHERE CUSTOMER_NUMBER="+CUSTNUMBER+";";
            Statement pstmt1 = conne.createStatement();
            ResultSet rs1=pstmt1.executeQuery(sqlCredit1);
            while (rs1.next())
            {
                credi=rs1.getString(3);//DVDNUMBER
                System.out.println(credi+" DVDNUMBER");
            }
            System.out.println("SELECT * FROM RENTAL WHERE CUSTOMER_NUMBER=\"+CUSTNUMBER+\";\";>>>>>SUCCESSFUL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String sqlCredit2="UPDATE DVD SET AVAILABLE=TRUE WHERE DVD_NUMBER="+credi+";";
            Statement pstmt1 = conne.createStatement();
            pstmt1.executeUpdate(sqlCredit2);
            System.out.println("UPDATE DVD SET AVAILABLE=TRUE WHERE DVD_NUMBER=\"+credi+\";\";>>>>>SUCCESSFUL");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        //now setting the  customer
        boolean canRent;
                if(creditD<=0){ canRent=false;}else canRent=true;

        try {
            String sqlCredit2="UPDATE CUSTOMER SET CREDIT='"+creditD+"',CAN_RENT='"+canRent+"' WHERE CUSTOMER_NUMBER='"+CUSTNUMBER+"';";
            Statement pstmt1 = conne.createStatement();
           pstmt1.executeUpdate(sqlCredit2);
            System.out.println("UPDATE CUSTOMER SET CREDIT=\"+creditD+\",CAN_RENT=\"+canRent+\" WHERE CUSTOMER_NUMBER=\"+CUSTNUMBER+\";\";>>>>SUCCESSFULL");
        } catch (SQLException e) {
            e.printStackTrace();
        }/**
        try {
            String sqlCredit2="UPDATE RENTAL SET RETURN_DATE='"+date+"' WHERE RENT_NUMBER='"+rentalNumbe+"';";
            Statement pstmt1 = conne.createStatement();
            pstmt1.executeUpdate(sqlCredit2);
            System.out.println("UPDATE RENTAL SET RETURN_DATE=\"+date+\" WHERE RENT_NUMBER=\"+rentalNumbe+\";\";>>>>>SUCCESSFULL");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }



    public static void main(String[] args)
    {
        RentalRep rentalRep = new RentalRep();
       // rentalRep.createTable(); //OK
        //rentalRep.insert(52,"33/43/23","33/43/23",3728,232);
        //rentalRep.delete(52);
        System.out.println(rentalRep.hightValeu());//OK
       // rentalRep.update(522,"34/232/");
       // rentalRep.mulitpleUpdate("o#32932#21/23/1990#2323#3233");
        //System.out.println(rentalRep.checker(2323));    }
}}
