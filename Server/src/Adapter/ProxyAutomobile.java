/**   
* @Title: ProxyAutomobile.java 
* @Description: Define class ProxyAutomobile
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/10/2016  
*/

package Adapter;


import Excetions.AutoException;
import Model.Automobile;
import Util.ReadFileUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

public abstract class ProxyAutomobile {

    private static Automobile auto;
    private static LinkedHashMap<String, Automobile> map = new LinkedHashMap<String, Automobile>();

    /**
     * Given a text file name a method called BuildAuto can be written to build an instance of
     * Automobile. This method does not have to return the Auto instance.
     */
    public void buildAuto(String filename) {
        try {
            auto = ReadFileUtil.buildAutoObject(filename);
            map.put(auto.getMake(), auto);
        } catch (AutoException e) {
            e.fix();
        }
    }

    /**
     * This function searches and prints the properties of a given Automodel.
     */
    public void printAuto(String modelname) {
        if (auto.getModel().equals(modelname)) {
            auto.print();
        }
    }
    public String printAuto(){
        return auto.toString();
    }

    /**
     * This function searches the Model for a given OptionSet and sets the name of OptionSet to newName.
     */
    public void updateOptionSetName(String modelname, String optionSetname, String newName) {
        if (auto.getModel().equals(modelname)) {
            auto.updateOptsetName(optionSetname, newName);
        }
    }

    /**
     * This function searches the Model for a given OptionSet and Option name, and sets the price to newPrice.
     */
    public void updateOptionPrice(String modelname, String optsetname, String option, float newprice) throws InterruptedException {
        if (auto.getModel().equals(modelname)) {
            auto.updateOptionPrice(optsetname, option, newprice);
        }
    }

    /**
     * add AutoMobile object into linkedHashMap
     */
    public void addAutoToMap(Automobile auto) {
        map.put(auto.getMake(), auto);
    }

    public Automobile getModelByIndex(int modelIndex) {
        return (new ArrayList<Automobile>(map.values())).get(modelIndex);
    }
    /**
     * add in unit5 -- get model by its name
     *
     * */
    public Automobile getModelByName(String name){
        for (Automobile automobile : map.values()) {
            if(automobile.getModel().equalsIgnoreCase(name)){
                return automobile;
            }
        }
        return null;
    }

    public Automobile parsePropertiesToAuto(Properties props) throws Exception {

        String modelName = props.getProperty("Model");
        String carMake = props.getProperty("Make");
        float basePrice = Float.parseFloat(props.getProperty("BasePrice"));

        int optionsetSetSize = 1;

        while (props.getProperty("Option" + Integer.toString(optionsetSetSize)) != null) {
            optionsetSetSize++;
        }

        optionsetSetSize--;

        Automobile auto = new Automobile(modelName,basePrice,optionsetSetSize);
        auto.setMake(carMake);

        for (int i = 0; i < optionsetSetSize; i++) {
            String optionsetName = props.getProperty("Option" + Integer.toString(i + 1));
            int optionSize = 1;
            while (props.getProperty("OptionValue" + Integer.toString(i + 1) + "_" + Integer.toString(optionSize)) != null) {
                optionSize++;
            }
            optionSize--;

            auto.creatOneOptSet(optionsetName, optionSize);

            for (int j = 0; j < optionSize; j++) {
                String optionName = props.getProperty("OptionValue" + Integer.toString(i + 1) + "_" + Integer.toString(j+1));
                float optionPrice = Float.parseFloat(props.getProperty("OptionValue" + Integer.toString(i + 1) + "_"+ Integer.toString(j+1)+"_price"));
                auto.creatOneOption(optionsetName, optionName,optionPrice);
            }
        }
        return auto;
    }

    public static LinkedHashMap<String, Automobile> getMap() {
        return map;
    }

    public static void setMap(LinkedHashMap<String, Automobile> map) {
        ProxyAutomobile.map = map;
    }
}
