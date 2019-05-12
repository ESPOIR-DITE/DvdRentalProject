package sample.domain.serverPack;

import com.sun.org.apache.xerces.internal.xs.StringList;
import sample.domain.Customer;
import sample.domain.DvdClass;
import sample.repository.CustomerRep;
import sample.repository.DvdRep;
import sample.repository.RentalRep;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class ServerClass {

    // Server socket
    private ServerSocket listener;

    StringTokenizer token;
    // Client connection
    private Socket client;
    ObjectOutputStream out;
    ObjectInputStream in;

    //Customer instance
    Customer customer;
    DvdClass dvdClass;
    int dvdNumber;
    String title;
    String category;
    double price;
    boolean newRelease;
    boolean availableForRent;
    String response;

    //REPOSITORY INSTANCES
    CustomerRep customerRep;
    DvdRep dvdRep;
    RentalRep rentalRep;
    String reponse = null;
    String espoir = null;

    public ServerClass() {

        customerRep=new CustomerRep();
         dvdRep=new DvdRep();
         rentalRep = new RentalRep();


        try {
            listener = new ServerSocket(12345, 10);
        } catch (IOException ioe) {
            System.out.println("IO Exception: " + ioe.getMessage() + "\n");

        }catch (NullPointerException e){}
    }
    public void listener()
    {
        try {

                System.out.println("Server is listening");
            //listener = new ServerSocket(12345, 10);
                System.out.println("Now moving onto processClient");
            client = listener.accept();

            do {

                client = listener.accept();
                 out = new ObjectOutputStream(client.getOutputStream());
             // out.flush();
                 in = new ObjectInputStream(client.getInputStream());

            // Step 2: communicate

                espoir = (String) in.readObject();      //******WE READ THE COMING LINE HERE***********
                System.out.println("From CLIENT>> " + espoir);
                if(!espoir.equals(null)){
                detoknizer(espoir);}

                /**
                we will reply a string NAMED: reponse.
                 */

                //System.out.println("************************");
                out.writeObject(reponse);   //this is where we will be responding with the valeu that is coming from the database
               // System.out.println("response is :"+reponse);
                out.flush();
                /***/}
           while (!espoir.equalsIgnoreCase("terminat"));
            System.out.println("goodbey");
            // Step 3:close down
            out.close();
            in.close();
            client.close();    //
        } catch (IOException ioe)
        {
            System.out.println("IO Exception: " + ioe.getMessage() + "\n");
        } catch (ClassNotFoundException cnfe)
        {
            System.out.println("Class not found: " + cnfe.getMessage() + "\n");
        }
         catch (Exception e)
         {
            e.printStackTrace();
        }
    }
    public void detoknizer(String valeu)//THIS METHOD IS RESPOSSABLE OF FIRST IDENTIFYING THE FIRST CHARACTORE AND REMOVING THE # BETWEEN THE ELEMENTS OF THE STRING
    {
        System.out.println("Tokenizer method excuting......");
        char firstValeu = valeu.charAt(0);
       if(firstValeu=='a') //if the first valeu Start with 'a' thIS means that we receiving a new customer
       {   String firstString;
           System.out.println(valeu+"received in adding new customer");

           int custNumber;
           String firstName;
           String surname;
           String phoneNum;
           double credit;
           boolean canRent;

            //we need to send this String to a method metho that is resposble for sending the customer details to the database.
          try{ if (valeu != null) {
              //return "a"+custNumber+"#"+firstName+"#"+surname+"#"+phoneNum+"#"+credit+"#"+canRent+"";
              token = new StringTokenizer(valeu, "#");

              firstString = token.nextToken();
              custNumber = Integer.parseInt(token.nextToken());
              firstName = token.nextToken();
              surname = token.nextToken();
              phoneNum = token.nextToken();
              credit = Double.parseDouble(token.nextToken());
              canRent = Boolean.parseBoolean(token.nextToken());

              //Customer(int custNumber, String firstName, String surname, String phoneNum, double credit, boolean canRent)
             // customer = new Customer(custNumber,firstName,surname,phoneNum,credit,canRent);
              /** sending new customerto the database*/
              customerRep.insert(custNumber,firstName,surname,phoneNum,credit,canRent);
              /***********sent to the database*************/
              //repository(customer); //we are sending the object of customer class so that it ca be send to the database
          }//end while
          }// end try
          catch (NullPointerException n)
          {
              System.out.println("there is a null point exception line 97 in server class");
          }
       }
       if (firstValeu=='b') //if the first valeu Start with 'b' that means that we receiving a new dvd
       {

           token = new StringTokenizer(valeu, "#");
           try{ if(valeu != null) {
               //return "b"+dvdNumber+"#"+title+"#"+category+"#"+price +"#"+newRelease +"#"+ availableForRent;
               //token = new StringTokenizer(valeu, "#");

                String vhhj=token.nextToken();
               dvdNumber = Integer.parseInt(token.nextToken());
              // String firstString = token.nextToken();
               title = token.nextToken();
               category = token.nextToken();

               price = Double.parseDouble(token.nextToken());
               newRelease =Boolean.parseBoolean( token.nextToken());
               availableForRent = Boolean.parseBoolean(token.nextToken());
             // dvdClass =new DvdClass(dvdNumber, title,category, price,newRelease,availableForRent);
               System.out.println("this is the data received from gthe client:"+dvdNumber+" "+title+" "+category+" "+ price+" "+newRelease+" "+availableForRent);
               /**seding a new dvd to the database*/
               dvdRep.insert(dvdNumber, title,category, price,newRelease,availableForRent);
               /**seding a new dvd to the database*/
              // repository(dvdClass); //we are sending the object of customer class so that it ca be send to the database
           }//end while
           }// end try
           catch (NullPointerException n)
           {
               System.out.println("there is a null point exception line 97 in server class");
           }catch (NoSuchElementException v){
               System.out.println("NoSuchElementException");}
           //we need to send this String to a method that is respossable for sending the dvd details to the database.
       }
      if(firstValeu=='e')//from rentalController requesting all the movie title
      {
          System.out.println("excuting request of the movie list: "+valeu);
          StringTokenizer token;

          token = new StringTokenizer(valeu, "#");
          if(valeu!=null)
          {
             String va1=token.nextToken();
             String nextValeu = token.nextToken();
             System.out.println("the category received from the client: "+nextValeu);
             reponse = dvdRep.readTitle(nextValeu);
             // System.out.println(reponse);
              System.out.println(reponse+" &&&from database");
          }
          else reponse=null;


      }
      if(firstValeu=='f')// this is a request for the RETURNING THE HIGHEST RENTAL NUMBER
      {//WE NEED TO LOOK FOR THE HIGHEST VALUE FROM THE RENTAL NUMBER AND RETURN IS BACK
          // *

          reponse = rentalRep.hightValeu();
          System.out.println("la reponse from server: "+reponse);


      }
      if(firstValeu=='D')//trying TO GET ALL THE CUSTOMER number ON THE DATABASE AND PUT THEM ON THE LIST
      {
          System.out.println("excuting: "+firstValeu);
        String valeu1=customerRep.readACustColumn();
        String valeu2=valeu1.replace("[","");
        String valeu3=valeu2.replace(" ","");
          String valeuD=valeu3.replace("[","");
          //System.out.println(valeuD);
        reponse =valeuD;
      }
      if(firstValeu=='g')
      {
          System.out.println("JKDHSFJHSDJFHSHKJSDHFJDHS");

      }
      if(firstValeu=='i')
      {
          String x=dvdRep.readHight();
          System.out.println("server has rreceived :"+firstValeu);
          reponse=x;

      }
        if(firstValeu=='j')
        {
            String x=customerRep.read();
            System.out.println("server has rreceived :"+firstValeu);
            reponse=x;

        }
        if(firstValeu=='h')//***********************************THIS IS A REQUEST FOR DVD DETAILS
        {
            dvdRep.read();
            System.out.println("server has rreceived :"+firstValeu);
            reponse=dvdRep.readAll();

        }
        if(firstValeu=='k')//***********************************THIS IS A REQUEST FOR DVD DETAILS
        {
            customerRep.readAllColumn();
            System.out.println("server has rreceived :"+firstValeu);
            reponse=customerRep.readAllColumn();

        }
        if(firstValeu=='l')//***********************************THIS IS A REQUEST FOR DVD DETAILS
        {
            //customerRep.readAllColumn();
            System.out.println("server has rreceived :"+firstValeu);
            reponse=customerRep.readAllColumn();
            System.out.println(reponse);
        }

        if(firstValeu=='n')//***********************************THIS IS A REQUEST FOR credit, customerNumber/ and rentDate
        {System.out.println("server has rreceived :"+firstValeu);
            token = new StringTokenizer(valeu, "#");
            if(valeu!=null)
            {
                String va1=token.nextToken();
                int nextValeu = Integer.parseInt(token.nextToken());
                System.out.println("the category received from the client: "+nextValeu);
                reponse = rentalRep.checker(nextValeu);
                // System.out.println(reponse);
                System.out.println(reponse+" &&&from database");
            }
        }
        if(firstValeu=='o')//***********************************THIS IS A REQUEST FOR credit, customerNumber/ and rentDate
        {System.out.println("server has rreceived :"+firstValeu);


            token = new StringTokenizer(valeu, "#");
            if(valeu!=null)
            {
                String va1=token.nextToken();
                int nextValeu = Integer.parseInt(token.nextToken());
                System.out.println("the category received from the client: "+nextValeu);
                reponse = rentalRep.checker(nextValeu);
                // System.out.println(reponse);
                System.out.println(reponse+" &&&from database");
            }

        }


    }
    public void repository(Object object)
    {
        System.out.println("INTO REPOSITORY METHOD");

        if(object instanceof Customer)
        {
            try {
                int custNumberi = ((Customer) object).getCustNumber();
                String firstNamei = ((Customer) object).getFirstName();
                String surnamei = ((Customer) object).getSurname();
                String phoneNumi = ((Customer) object).getPhoneNum();
                double crediti = ((Customer) object).getCredit();
                boolean canRenti = ((Customer) object).isCanRent();

                System.out.println("This is the valeu that is sent from the client:"+custNumberi+" "+firstNamei+" "+surnamei+" "+phoneNumi+" "+crediti+" "+canRenti);

                // then send the value to the database.
            }catch (NullPointerException n)
            {
                System.out.println("null point exception line 121 server class");
            }
        }
        if(object instanceof DvdClass)
        {
            int dvdNumber=((DvdClass) object).getDvdNumber();
            String title=((DvdClass) object).getTitle();
            String category=((DvdClass) object).getCategory();
            double price=((DvdClass) object).getPrice();
            boolean newRelease=((DvdClass) object).isNewRelease();
            boolean availableForRent=((DvdClass) object).isAvailableForRent();
            System.out.println("this is the data received from gthe client:"+dvdNumber+" "+title+" "+category+" "+ price+" "+newRelease+" "+availableForRent);
        }
    }

    public static void main(String[] args) {
        ServerClass serviceClass=new ServerClass();
        /**for(int i=0;i<5;i++){
        serviceClass. detoknizer("n#2323");
            System.out.println(i);}*/
        serviceClass.listener();
       // serviceClass. detoknizer("a#3233#noone#anyone#8787iu#35.0#false");
       //serviceClass.detoknizer("f");
       // System.out.println(serviceClass.reponse);
    }//
}
