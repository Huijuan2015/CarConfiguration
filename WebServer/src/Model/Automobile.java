/**   
* @Title: Automobile.java 
* @Description: Define class Automobile
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/05/2016  
*/

package Model;

import client.Excetions.AutoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Automobile implements Serializable {

    private static final long serialVersionUID = -8381379922951884761L;
    private String make;
    private String model;
    private float baseprice;
    private ArrayList<OptionSet> optionSet;


    public Automobile() {
    }

    /**
     * creat avoiding NullPointerException
     */
    public Automobile(String name, float baseprice, int size) {
        this.model = name;
        this.baseprice = baseprice;
        this.optionSet = new ArrayList<OptionSet>(size);
        for (int i = 0; i < size; ++i) {
            optionSet.add(new OptionSet());
        }
    }
    public Automobile(String modelName,int optSetSize){
        this.model = modelName;
        this.optionSet = new ArrayList<OptionSet>(optSetSize);
        for (int i = 0; i < optSetSize; ++i) {
            optionSet.add(new OptionSet());
        }
    }

    //Getter and Setter
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ArrayList<OptionSet> getOptionSet() {
        return optionSet;
    }


    public void setOptionSet(ArrayList<OptionSet> optionSet) {
        this.optionSet = optionSet;
    }

    /**
     * for choosing a particular option in an option set
     */
    public void setOptionChoice(String setName, String optionName) {
        for (int i = 0; i < this.optionSet.size(); ++i) {
            if (optionSet.get(i).getName().equals(setName)) {
                optionSet.get(i).setOptionChoice(optionName);
            }
        }
    }

    public String getOptionChoice(String setName) {
        for (int i = 0; i < this.optionSet.size(); ++i) {
            if (optionSet.get(i).getName().equals(setName)) {
                return optionSet.get(i).getOptionChoice().getName();
            }
        }
        return null;
    }

    public float getOptionChoicePrice(String setName) {
        for (int i = 0; i < this.optionSet.size(); ++i) {
            if (optionSet.get(i).getName().equals(setName)) {
                return optionSet.get(i).getOptionChoice().getPrice();
            }
        }

        return 0;
    }

    public float getTotalPrice() {
        float total = baseprice;
        for (int i = 0; i < optionSet.size(); ++i) {
            total += optionSet.get(i).getOptionChoice().getPrice();
        }
        return total;
    }

    /**
     * creat method :creat one OptSet with name and size of Options
     *
     * @param name :name of OptSet
     * @param size :size of Options
     */
    public synchronized boolean creatOneOptSet(String name, int size) throws AutoException {
        int index = findIndexOfOptset(name);
        boolean ret = false;
        if (index != -1) {  //exist
            optionSet.add(index, new OptionSet(name, size));
            ret = true;
        } else {   //not exists
            for (int i = 0; i < this.optionSet.size(); ++i) {
                if ("".equals(this.optionSet.get(i).getName())) {
                    this.optionSet.set(i, new OptionSet(name, size));
                    ret = true;
                    break;
                }
            }
            if (!ret) {
                throw new AutoException(3);
            }
        }
        return ret;
    }

    /**
     * creat method : creat optionset's option
     *
     * @param optsetName :OptionSet's name
     * @param optionName :Option's name to creat
     * @param price      :Option's price to creat
     */
    public synchronized boolean creatOneOption(String optsetName, String optionName, float price) throws AutoException {
        OptionSet optSet = findOneOptSetByName(optsetName);
        boolean ret = false;
        if (optSet != null) {
            OptionSet.Option opt = optSet.findOneOptionByName(optionName);
            if (opt != null) {  //option exists,update its price
                opt.setPrice(price);
                ret = true;
            } else {
                ArrayList<OptionSet.Option> options = optSet.getOpt();
                for (int i = 0; i < options.size(); ++i) {
                    if ("".equals(options.get(i).getName())) {
                        options.set(i, new OptionSet().new Option(optionName, price));
                        ret = true;
                        break;
                    }
                }
                if (!ret) {
                    throw new AutoException(4);
                }
            }
        } else {
            System.out.println("OptSet not exists :" + optsetName);
            return false;
        }
        return false;
    }

    public synchronized boolean creatOneOption(String optsetName, String optionName) throws AutoException {
        OptionSet optSet = findOneOptSetByName(optsetName);
        boolean ret = false;
        if (optSet != null) {
            OptionSet.Option opt = optSet.findOneOptionByName(optionName);
            if (opt != null) {  //option exists,update its price

                ret = true;
            } else {
                ArrayList<OptionSet.Option> options = optSet.getOpt();
                for (int i = 0; i < options.size(); ++i) {
                    if ("".equals(options.get(i).getName())) {
                        options.set(i, new OptionSet().new Option(optionName));
                        ret = true;
                        break;
                    }
                }
                if (!ret) {
                    throw new AutoException(4);
                }
            }
        } else {
            System.out.println("OptSet not exists :" + optsetName);
            return false;
        }
        return false;
    }


    /**
     * find method :find one OptionSet in model by OptionSet's name
     * if exists ,return optionSet with that name
     * else return null
     */
    public synchronized OptionSet findOneOptSetByName(String name) {
        ArrayList<OptionSet> optionSets = this.getOptionSet();
        for (int i = 0; i < optionSets.size(); ++i) {
            if (name.equals(optionSets.get(i).getName())) {
                return optionSets.get(i);
            }
        }
        return null;
    }

    /**
     * find method :find optionSet's index in model's optset by  name
     * if exists ,return the index of the optionSet with that name
     * else return -1
     */
    public synchronized int findIndexOfOptset(String name) {
        List<OptionSet> optionSets = this.getOptionSet();
        for (int i = 0; i < optionSets.size(); ++i) {
            if (name.equals(optionSets.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * find method :find OptionSets in model by OptionSet's name array
     * if exists ,return OptionSet found by each name
     * calls the findOneOptSetByName() method above
     */
    public synchronized OptionSet[] findOptSetsByNames(String[] names) {
        OptionSet[] optionSets = new OptionSet[names.length];
        for (int i = 0; i < names.length; ++i) {
            optionSets[i] = findOneOptSetByName(names[i]);
        }
        return optionSets;
    }

    /**
     * update method : update optionset's name
     * first,calls findOneOptSetByName
     * if exists ,update; else print not exist message
     */
    public synchronized void updateOptsetName(String oldname, String newname) {
        OptionSet optset = findOneOptSetByName(oldname);
        if (optset != null) {
            optset.setName(newname);
        } else {
            System.out.println("No optionSet named " + oldname + "in this model !!!");
        }
    }

    /**
     * update method : update optionset's opt
     * first,calls findOneOptSetByName
     * if exists ,update; else print not exist message
     */
    public synchronized void updateOptsetOption(String name, ArrayList<OptionSet.Option> options) {
        OptionSet optionSet = findOneOptSetByName(name);
        if (optionSet != null) {
            optionSet.setOpt(options);
        } else {
            System.out.println("No optionSet named " + name + "in this model !!!");
        }
    }

    public synchronized boolean updateOptionPrice(String optionSetname, String optionName, float newPrice) throws InterruptedException {
        for (int i = 0; i < this.optionSet.size(); i++) {
            if (optionSet.get(i).getName().equals(optionSetname)) {
                return optionSet.get(i).updateOptionPrice(optionName, newPrice);
            }
        }
        return false;
    }

    /**
     * delete method : delete all OptionSets in model
     */
    public void deleteAllOptset() {
        this.optionSet = new ArrayList<OptionSet>();
    }

    /**
     * delete method :delete one OptionSet in model by name
     * calls findIndexOfOptset() to get the index
     */
    public void deleteOptsetByName(String name) {
        int index = findIndexOfOptset(name);
        if (index != -1) {
            this.optionSet.remove(index);
        } else {
            System.out.println("The optionset named " + name + " no exists !!!");
        }
    }


    public float getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(float baseprice) {
        this.baseprice = baseprice;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String optsetStr = "";
        String choice = "";

        if (optsetStr != null) {
            for (OptionSet optionSet : this.optionSet) {
                optsetStr += optionSet.toString();
                if (!"".equals(optionSet.getOptionChoice().getName())) {
                    choice += (optionSet.getOptionChoice().toString());
                }
            }
        }

        return model + '\'' + "\n" +
                "\t baseprice=$" + baseprice + "\t make=" + make + "\n" +
                "\t optionSets:" + "\n" + optsetStr + "\n"
                + "\t choice:" + "\n" + choice + "\n"
                + "\t Total price:" + this.getTotalPrice()
                ;
    }
}
