/**   
* @Title: DefaultSocketClient.java 
* @Description: Define class DefaultSocketClient
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/20/2016  
*/

package client.Client;


import Model.Automobile;
import client.Server.SocketClientConstants;
import client.Server.SocketClientInterface;

import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Scanner;


public class DefaultSocketClient extends Thread
        implements SocketClientInterface, SocketClientConstants {

    private ObjectOutputStream objToServer;
    private ObjectInputStream objFromServer;
    private PrintWriter strToServer;
    private BufferedReader strFromServer;

    private Socket socket;
    private String strHost = "localhost";
    private int iPort;


    public DefaultSocketClient(String strHost, int iPort) {
        setPort(iPort);
        setStrHost(strHost);
        initializeServer();
    }//constructor

    /**
     * new function added in Unit5--to get the list of available models
     * */
     public LinkedHashMap<String,Automobile> getAllModels(){
         try{
             objFromServer = new ObjectInputStream(socket.getInputStream());
             objToServer = new ObjectOutputStream(socket.getOutputStream());
             strToServer = new PrintWriter(socket.getOutputStream(),true);
             strFromServer = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
         } catch (IOException e) {
             e.printStackTrace();
         }

         int operetion =3;

         strToServer.println(operetion);
         LinkedHashMap<String,Automobile> modelList = new LinkedHashMap<String,Automobile>();
         try{
             LinkedHashMap<String,Automobile> mapFromServer = new LinkedHashMap<String, Automobile>();
             mapFromServer=(LinkedHashMap<String,Automobile>) objFromServer.readObject();
             System.out.println(mapFromServer.size());
             modelList.putAll(mapFromServer);
         }catch (Exception e){
             e.printStackTrace();
         }
         return modelList;
     }
    /**
     * new function added in Unit5--to get AutoMobile by model name
     * */
   /* public Automobile getModelByName(String model){
        try{
            objFromServer = new ObjectInputStream(socket.getInputStream());
            objToServer = new ObjectOutputStream(socket.getOutputStream());
            strToServer = new PrintWriter(socket.getOutputStream(),true);
            strFromServer = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int operetion =4;

        strToServer.println(operetion);

        Automobile auto = new Automobile();
        try{
            auto= (Automobile)objFromServer.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return auto;
    }*/

     public void run() {
        if (initializeServer()) {
            createSession();
            closeSession();
        }
    }//run

    public boolean initializeServer() {
        try {
            socket = new Socket(strHost, iPort);
            System.out.println("client.Client started.");
        } catch (IOException socketError) {
            if (DEBUG) {
                System.err.println("Unable to connect to " + strHost);
            }
            return false;
        }

        return true;
    }

    public void createSession() {

        try {
            //the input and output stream has to match the order in client.Server
            //Like <clientOut,serverIn>
            objFromServer = new ObjectInputStream(socket.getInputStream());
            objToServer = new ObjectOutputStream(socket.getOutputStream());
            strToServer = new PrintWriter(socket.getOutputStream(), true);
            strFromServer = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        int operation = -1;
        String filePath = null;
        Scanner scanner = new Scanner(System.in);
        //boolean running = true;
        //while(running){
        System.out.println(
                "Please input Number to select operation:\n"
                        + "\t0:Upload Properties File\n"
                        + "\t1:Configure Car Model Options\n"
                        + "\t2:Select Car Option");
        try {
            if (scanner.hasNextLine()) {

                operation = Integer.parseInt(scanner.nextLine());
                //System.out.println("Scanner:" + operation);
                strToServer.println(operation);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input ERROR! "
                    + "Please input number 1,2 or 3 to choose the operation.");
        }

        //invoke method according operation code
        switch (operation) {
        /******************************************************************/
        case 0:
            System.out.println("Please input the filePath of Properties File:");
            if (scanner.hasNextLine()) {
                filePath = scanner.nextLine();
            }
            Properties props = new Properties();

            CarModelOptionsIO carOptionIO = new CarModelOptionsIO();
            try {

                props = carOptionIO.readProperties(filePath);
                objToServer.writeObject(props);
                carOptionIO.varifyCreatedSucc(strFromServer.readLine());

                //System.out.println("Do you want to continue? (y/n)");
                //					if(scanner.hasNextLine()){
                //						String op = scanner.nextLine();
                //						if(op.equals("n")) {
                //							scanner.close();
                System.out.println("System exit.");
                //							running = false;
                //						}
                //						else if (op.equals("y")) {
                //							;
                //						}
                //						else {
                //							System.out.println("Illegal operation. System exit.");
                //							running = false;
                //						}
                //					}
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            /******************************************************************/
        case 1:
            try {
                System.out.println(strFromServer.readLine());
                System.out.println("Please input the model number to configure:");
                int modelIndex = -1;
                if (scanner.hasNextLine()) {
                    modelIndex = Integer.parseInt(scanner.nextLine());
                }
                strToServer.println(modelIndex);

                Automobile auto = (Automobile) objFromServer.readObject();
                System.out.println("===============Model Details Begin================");
                System.out.println(auto.toString());
                System.out.println("===============Model Details End==================");
                //System.out.println("Do you want to continue? (y/n)");
                //if(scanner.nextLine().equals("n")) {
                scanner.close();
                System.out.println("System exit.");
                //	running = false;
                //}
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Wrong input! "
                        + "Please input exact one number to choose the operation.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            /******************************************************************/
        case 2:
            try {
                System.out.println(strFromServer.readLine());
                System.out.println("Please input the model number to Select Details:");
                int modelIndex = -1;
                if (scanner.hasNextLine()) {
                    modelIndex = Integer.parseInt(scanner.nextLine());
                }
                strToServer.println(modelIndex);
                Automobile auto = (Automobile) objFromServer.readObject();
                System.out.println("===============Model Details Begin================");
                System.out.println(auto.toString());
                System.out.println("===============Model Details End==================");
                System.out.println("Please input the option name first, "
                        + "press Enter, and input your selection:");
                while (scanner.hasNextLine()) {
                    String optionsetName = scanner.nextLine();
                    if (scanner.hasNextLine()) {
                        String optiong = scanner.nextLine();
                        auto.setOptionChoice(optionsetName, optiong);
                    }
                    System.out.println("Do you want to set more selection? (y/n)");
                    String op = scanner.nextLine();
                    if (op.equals("n")) {
                        break;
                    }
                }
                System.out.println("===============Selection Bills Head================");
                System.out.println(auto.toString());
                System.out.println("===============Selection Bills End==================");
                //System.out.println("Do you want to continue? (y/n)");
                //if(scanner.nextLine().equals("n")) {
                scanner.close();
                System.out.println("System exit.");
                //	running = false;
                //}
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            /******************************************************************/
        }//switch
        //}//while
    }//createSession


    public void closeSession() {
        try {
            socket.close();
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }

    public void setPort(int iPort) {
        this.iPort = iPort;
    }

    public void setStrHost(String strHost) {
        this.strHost = strHost;
    }


}// class DefaultSocketClient
