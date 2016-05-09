/**   
* @Title: DefaultSocketServer.java 
* @Description: Define class DefaultSocketServer
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/20/2016  
*/

package Server;


import Adapter.BuildAuto;
import Model.Automobile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Properties;


public class DefaultSocketServer extends Thread implements SocketClientInterface, SocketClientConstants {

    private ObjectOutputStream objToClient;
    private ObjectInputStream objFromClient;
    private PrintWriter strToClient;
    private BufferedReader strFromClient;

    private ServerSocket serverSocket;
    private String strHost;
    private int iPort;

    private BuildAuto buildAuto = new BuildAuto();

    public DefaultSocketServer(String strHost, int iPort) {
        setPort(iPort);
    }// constructor

    public void run() {
        if (initializeServer()) {
            handleSession();
            closeSession();
        }
    }

    public boolean initializeServer() {
        try {
            serverSocket = new ServerSocket(iPort);
            System.out.println("Server started.");
            buildAuto.buildAuto("doc/auto.txt");
            buildAuto.buildAuto("doc/autoB.txt");
        } catch (IOException socketError) {
            if (DEBUG) {
                System.err.println("Unable to connect to " + strHost);
            }
            return false;
        }
        return true;
    }

    public void handleSession() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                objToClient = new ObjectOutputStream(socket.getOutputStream());
                objFromClient = new ObjectInputStream(socket.getInputStream());

                strFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                strToClient = new PrintWriter(socket.getOutputStream(), true);

                // 1.get operation code
                int operation = Integer.parseInt(strFromClient.readLine());
                // 2.invoke respective method according to operation
                switch (operation) {
                    case 0:
                        acceptPropertiesAsAuto();
                        break;
                    case 1:
                        LinkedHashMap<String, Automobile> map = BuildAuto.getMap();
                        String optionStr = "";
                        for (int i = 0; i < map.size(); ++i) {
                            optionStr += (Integer.toString(i) + "--" + buildAuto.getModelByIndex(i).getModel() + "\t");
                        }
                        strToClient.println(optionStr);
                        int modelIndex = Integer.parseInt(strFromClient.readLine());
                        objToClient.writeObject(buildAuto.getModelByIndex(modelIndex));
                        break;
                    case 2:
                        LinkedHashMap<String, Automobile> amap = BuildAuto.getMap();
                        String optStr = "";
                        for (int i = 0; i < amap.size(); ++i) {
                            optStr += (Integer.toString(i) + "--" + buildAuto.getModelByIndex(i).getModel() + "\t");
                        }
                        strToClient.println(optStr);
                        int modelIndex2 = Integer.parseInt(strFromClient.readLine());
                        objToClient.writeObject(buildAuto.getModelByIndex(modelIndex2));
                        break;
                    //add in unit5--to get available models
                    case 3:
                        System.out.println("Server:receive Operation 3 -- get available models");
                        LinkedHashMap<String, Automobile> modelList = BuildAuto.getMap();
                        System.out.println("modelList's size is:" +modelList.size());
                        objToClient.writeObject(modelList);
                        break;

                }
            }
        } catch (IOException e) {
            System.err.println("Unable to connect to open IO Stream");
        }
    }

    //Accept Properties Object sent from Client
    //Add it into Server Automobile LinkedHashMap
    //Response OK to Client
    private void acceptPropertiesAsAuto() {
        Properties props = new Properties();
        try {
            props = (Properties) objFromClient.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BuildCarModelOptions buildOptions = new BuildCarModelOptions(buildAuto, props);

        System.out.println("===============================");
        System.out.println("Properties upload successful!\n" + buildOptions.toString());
        System.out.println("===============================");
        strToClient.println("OK");
    }

    public void closeSession() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }

    public void setPort(int iPort) {
        this.iPort = iPort;
    }

}// class DefaultSocketClient
