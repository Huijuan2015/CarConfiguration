/**   
* @Title: CreateAuto.java 
* @Description: Define class CreateAuto
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/28/2016  
*/

package client.Adapter;


public interface CreateAuto {
    /**
     * Given a text file name a method called BuildAuto can be written to build an instance of
     * Automobile. This method does not have to return the Auto instance.
     * */
    public void buildAuto(String filename);
    /**
     * This function searches and prints the properties of a given Automodel.
     * */
    public void printAuto(String modelname);

}
