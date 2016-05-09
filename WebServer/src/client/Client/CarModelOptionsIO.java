/**   
* @Title: CarModelOptionsIO.java 
* @Description: Define class CarModelOptionsIO
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/20/2016  
*/

package client.Client;


import client.Util.ReadFileUtil;

import java.util.Properties;

public class CarModelOptionsIO {
    //read data from the Properties file
    public Properties readProperties(String filepath) throws Exception{
        return ReadFileUtil.parseProperties(filepath);
    }
    //receive a response from the server
    public boolean varifyCreatedSucc(String response){
        if("OK".equals(response)){
            System.out.println("Model Ctreated successful!!!");
            return true;
        }
        return false;
    }
}
