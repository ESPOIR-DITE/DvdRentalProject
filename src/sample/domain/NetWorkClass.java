package sample.domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
this class will eb responsible for sending and receiving data from and to the server
 */
public class NetWorkClass {

        private Socket server;
        ObjectOutputStream out;
        ObjectInputStream in;
        String response= null;


    public NetWorkClass()
    {}

        /**
         * Creates a new instance of ClientApp
         */

       /** public NetWorkClass()
        {

            // Attempt to establish connection to server
            try
            {
                // Create socket
                server = new Socket("127.0.0.1", 12345);
            } catch (IOException ioe)
            {
                System.out.println("IOException: " + ioe.getMessage());
            }
        }*/

        public String sendData(String espoir)
        {
            // The connection has been established - now send/receive.
            System.out.println("THIS IS WHAT IS GOING TO BE SENT TO THE SERVER\n"+espoir);
            try {
                server = new Socket("127.0.0.1", 12345);
                out = new ObjectOutputStream(server.getOutputStream());
                in = new ObjectInputStream(server.getInputStream());
                out.writeObject(espoir);
                out.flush();
                System.out.println("data sent");
            }
            // Step 3: close down
            catch (IOException ioe)
            {
                System.out.println("IO Exception: " + ioe.getMessage());
            }
            try {/**"this is the respose "+ */
                response = (String) in.readObject();
               // System.out.println(response+" response FROM THE NETWORK");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (NullPointerException r){
                System.out.println("null enco");
            }



            return response;
        }
        public void closeConnection()
        {

            try {
                out.close();
                in.close();
                server.close();
            } catch (IOException e)
            {
                System.out.println("socket blallaaa");
            }

        }
        public String getString()
        {
            return response;
        }

    public static void main(String[] args) {
        NetWorkClass netWorkClass = new NetWorkClass();
        // String x=String.format("b#dvdNumber#title#category#price #newRelease #availableForRent");
        //for (int i = 0; i < 5; i++) {
         //   System.out.println(netWorkClass.sendData(i + " <<<<D"));
       // }

        for (int i = 0; i < 5; i++) {
            System.out.println(i+netWorkClass.sendData("D<<<<<"));
        }
    }

}
