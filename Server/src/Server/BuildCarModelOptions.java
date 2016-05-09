/**   
* @Title: BuildCarModelOptions.java 
* @Description: Define class BuildCarModelOptions
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/20/2016  
*/

package Server;


import Adapter.BuildAuto;
import Model.Automobile;

import java.util.Properties;

public class BuildCarModelOptions {
    private Automobile auto = new Automobile();

    public BuildCarModelOptions(BuildAuto buildAuto,Properties props){
       try{
           auto = buildAuto.parsePropertiesToAuto(props);
           buildAuto.addAutoToMap(auto);
       }catch (Exception e){
           System.out.println("BuildCarModelOptions error:"+e);
       }
    }

    public String toString(){
        return auto.toString();
    }
}
