/**   
* @Title: ReadFileUtil.java 
* @Description: Util: ReadFileUtil
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/05/2016  
*/


package Util;


import Excetions.AutoException;
import Model.Automobile;

import java.io.*;
import java.util.Properties;

/**
 * readFile and build an Automobile object
 */
public class ReadFileUtil {
    private static final String NAME_OPTIONS_SPLITTER = "-";
    private static final String OPTIONS_SPLITTER = ",";
    private static final String OPTION_PRICE_SPLITTER = "_";
    private static final String MODEL_BASEPRICE_PREFIX = "Model_";
    private static final String MODEL_LINE_SPLITTER = "_";
    private static final int POSIBAL_MAX_OPTIONSET_SIZE = 5;

    /**
     * new function : to parse properties file
     *
     * */
    public static Properties parseProperties(String filepath) throws Exception{
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(filepath);
        props.load(in);

        //add an addtional parameter called fileType,that is,the extension of the file
        int indexOfLastDot = filepath.lastIndexOf(".");
        String extName = filepath.substring(indexOfLastDot+1);
        props.put("fileType",extName);

        return props;
    }

    public static Automobile buildAutoObject(String filename) throws AutoException {
        Automobile automobile = new Automobile();
        try {
            FileReader file = new FileReader(filename);     //read file into buff
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    if (line.startsWith(MODEL_BASEPRICE_PREFIX)) {  //model name and baseprice line
                        try {
                            String[] model_baseprice = line.trim().split(MODEL_LINE_SPLITTER);
                            String name = model_baseprice[1].trim();
                            float price = Float.parseFloat(model_baseprice[2].trim().replace(",", "").replace("$", ""));
                            int size = Integer.parseInt(model_baseprice[3]);
                            String make = model_baseprice[4].substring(5);
                            automobile = new Automobile(name, price, size);
                            automobile.setMake(make);
                        } catch (Exception e) {
                            throw new AutoException(1);
                        }

                    } else {   //properties line
                        try {
                            buildOptset(automobile, line);
                        } catch (AutoException e) {

                        }

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ReadFile error--" + e.toString());
            //throw new BuildAutoException("Read file and build Automobile error:");
        }
        return automobile;
    }

    /**
     * build a property line into an OptionSet object
     */
    private static void buildOptset(Automobile auto, String line) throws AutoException {

        try {
            String[] strs = line.trim().split(NAME_OPTIONS_SPLITTER, 2);   //split line into two parts :opset name and options
            if (strs.length == 2) {  //name + options
                String optsetName = strs[0].trim();
                String optionsStr = strs[1].trim();
                String[] options = optionsStr.split(OPTIONS_SPLITTER); //split options with "," to single option
                try {
                    auto.creatOneOptSet(optsetName, options.length);
                } catch (AutoException e) {
                    e.fix();
                }
                for (int i = 0; i < options.length; ++i) {
                    String[] name_price = options[i].split(OPTION_PRICE_SPLITTER); //split each option into two parts:name and price
                    if (name_price.length == 1) {            //no price ,set price = 0
                        auto.creatOneOption(optsetName, options[i].trim(), 0);
                    } else if (name_price.length == 2) {     //name-price pair
                        auto.creatOneOption(optsetName, name_price[0].trim(), Float.parseFloat(name_price[1].trim().replace(",", "").replace("$", "")));
                    } else {                                 //illegal
                        System.out.println("This is an illegal option :" + options[i]);
                    }
                }
            } else {
                System.out.println("This line illegal(not in 'optsetName - options' pattern :" + line);
            }
        } catch (Exception e) {
            System.out.println("build option error :" + e.toString());
        }
    }
}
