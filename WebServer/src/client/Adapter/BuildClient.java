/**   
* @Title: CreateAuto.java 
* @Description: Define class CreateAuto
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/10/2016  
*/

package client.Adapter;


import client.Client.DefaultSocketClient;

public class BuildClient extends DefaultSocketClient implements ClientAuto{

    public BuildClient(String strHost,int port){
        super(strHost,port);
    }
}
