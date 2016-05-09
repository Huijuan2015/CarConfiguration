/**   
* @Title: OptionSet.java 
* @Description: Define class OptionSet
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/05/2016  
*/

package Model;

import java.io.Serializable;
import java.util.ArrayList;


public class OptionSet implements Serializable {

    private ArrayList<Option> opt;
    private String name;
    private Option choice = new Option();

    public OptionSet() {
        super();
        this.name = "";

    }

    /**
     * creat
     */
    protected OptionSet(String name, int size) {
        this.opt = new ArrayList<Option>(size);
        this.name = name;
        for (int i = 0; i < size; ++i) {
            opt.add(new Option());
        }

    }

    protected OptionSet(int size) {
        this.opt = new ArrayList<Option>(size);
        for (int i = 0; i < opt.size(); ++i) {
            opt.add(new Option());
        }
    }

    public Option getOptionChoice() {
        return choice;
    }

    public void setOptionChoice(String optionName) {
        int index = findIndexOfOption(optionName);
        this.choice = new Option(optionName, opt.get(index).getPrice());
    }

    /**
     * general getter : get all Options
     */
    protected ArrayList<Option> getOpt() {
        return opt;
    }

    /**
     * find method :find one Option in optionSet by option's name
     * if exists ,return option with that name
     * else return null
     */
    protected Option findOneOptionByName(String name) {
        ArrayList<Option> options = this.getOpt();
        for (int i = 0; i < options.size(); ++i) {
            if (name.equals(options.get(i).getName())) {
                return options.get(i);
            }
        }
        return null;
    }

    /**
     * find method :find one Option in optionSet by option's name
     * if exists ,return option with that name
     * else return null
     */
    protected int findOptionIndex(String name) {
        ArrayList<Option> options = this.getOpt();
        for (int i = 0; i < options.size(); ++i) {
            if (name.equals(options.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * find method :find option's index in optionset by  name
     * if exists ,return the index of the option with that name
     * else return -1
     */
    protected int findIndexOfOption(String name) {
        ArrayList<Option> options = this.getOpt();
        for (int i = 0; i < options.size(); ++i) {
            if (name.equals(options.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }


    /**
     * find method :find Options in optionSet by option's name array
     * if exists ,return options found by each name
     * calls the findOneOptionByName() method above
     */
    protected Option[] findOptionsByNames(String[] names) {
        Option[] options = new Option[names.length];
        for (int i = 0; i < names.length; ++i) {
            options[i] = findOneOptionByName(names[i]);
        }
        return options;
    }

    /**
     * update method : update name
     * first find OneOption By Name
     * if exists ,update and return true;
     * else print not exist message and return false
     */
    protected boolean updateOptionName(String oldName, String newName) {
        Option option = findOneOptionByName(oldName);
        if (option != null) {
            option.setName(newName);
            return true;
        } else {
            System.out.println("No option named " + oldName + "in this optionSet !!!");
            return false;
        }
    }

    /**
     * update method : update price
     * first, find OneOption By Name
     * if exists ,update and return true;
     * else print not exist message and return false
     */
    protected boolean updateOptionPrice(String name, float newPrice) {
        Option option = findOneOptionByName(name);
        if (option != null) {
            option.setPrice(newPrice);
            return true;
        } else {
            System.out.println("No option named " + name + "in this optionSet !!!");
            return false;
        }
    }

    /**
     * delete all options
     */
    protected void deleteAllOptions() {
        this.opt = new ArrayList<Option>();
    }

    /**
     * delete method :delete one Option in optionset by name
     * calls findIndexOfOption() to get the index
     */
    protected void deleteOptionByName(String name) {
        int index = findIndexOfOption(name);
        if (index != -1) {
            ArrayList<Option> options = new ArrayList<Option>(opt.size() - 1);
            System.arraycopy(opt, 0, options, 0, index);
            System.arraycopy(opt, index + 1, options, index, options.size() - index);
            this.opt = options;
        }
    }

    protected void setOpt(ArrayList<Option> opt) {
        this.opt = opt;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String optionStr = "";
        if (opt != null) {
            for (Option option : opt) {
                optionStr += option.toString();
            }
        }
        return "\t\toptionSet name='" + name + '\'' + "\n" +
                "\t\t\toptions:" + "\n" + optionStr +
                '\n';
    }

    /**
     * inner class Option having properties name and price
     */
    public class Option implements Serializable {

        private String name = "";
        private float price;

        public Option() {
        }

        public Option(String name, float price) {
            this.name = name;
            this.price = price;
        }

        public Option(String name){
            this.name=name;
        }

        protected String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        protected float getPrice() {
            return price;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "\t\t\t\t" + name + '\'' + "   $" + price + '\n';
        }
    }
}
