/**   
* @Title: ClientAuto.java 
* @Description: Define class ClientAuto
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/28/2016  
*/

package client.Adapter;


import Model.Automobile;

import java.util.LinkedHashMap;

public interface ClientAuto {

    public LinkedHashMap<String,Automobile> getAllModels();

    //public Automobile getModelByName(String model);
}
