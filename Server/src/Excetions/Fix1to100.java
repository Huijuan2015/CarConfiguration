/**   
* @Title: Fix1to100.java 
* @Description: Define class Fix1to100
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/10/2016  
*/


package Excetions;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Fix1to100 {
     float DEFAULT_PRICE = 0;

    //model name line not in the pattern :Model_{model name}_{$price}_{optset size}
    public void fix1(){
        log("ERROR_1 : Data file error : model name line not in the pattern :Model_{model name}_{$price}_{optset size}");
    }
    //price missing ;fix : set price =0
    public float fix2(){
        log("Error_2 : Data file error : option has no price");
        return  DEFAULT_PRICE;
    }
    //no empty OptionSet
    public void fix3(){
        log("ERROR_3 : No empty OptionSet to append to automobile");
    }

    //no empty Option
    public void fix4(){
        log("ERROR_4 : No empty Option to append to OptionSet");
    }
    //price error
    public void fix5(){
        log("ERROR_5 : Price contains illegal char");
    }
    public void log(String log){
        System.out.println(log);
        File logFile = new File("PartB/doc/log.dat");
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile,true)));
            bw.write(log);
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
