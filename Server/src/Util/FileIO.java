/**   
* @Title: FileIO.java 
* @Description: Util: FileIO
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/05/2016  
*/

package Util;


import Model.Automobile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * serializing Automobile into text file
 * and deSerialize from serialized file back to Automobile
 * */
public class FileIO {
    /**
     * serializing Automobile into text file
     * @param auto :the Automobile object to serialize
     * @param filename : the output filename
     * */
    public static void serializeAuto(Automobile auto,String filename){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(auto);
            out.close();
       }catch (Exception e){
            System.out.println("serializeAuto error :"+e.toString());
        }
    }
    /**
     * deSerialize from serialized file back to Automobile
     * @param filename : the input filename
     * */
    public static Automobile deSerializeAuto(String filename){
        Automobile auto = new Automobile();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            auto = (Automobile) in.readObject();
            return auto;
        }catch (Exception e){
            System.out.println("deSerializeAuto error :"+e.toString());
        }
        return auto;
    }
}
