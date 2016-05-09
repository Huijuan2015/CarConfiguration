package Test;


import Adapter.BuildAuto;
import Adapter.ProxyAutomobile;
import Excetions.AutoException;
import Model.Automobile;
import Model.OptionSet;
import Util.ReadFileUtil;

import java.util.ArrayList;

public class ModelTest extends OptionSet{

    public static void main(String[] args)  throws AutoException,InterruptedException{
        //Automobile FordZTW = ReadFileUtil.buildAutoObject("PartB/doc/autoTest.txt");
        ProxyAutomobile FordZTW = new BuildAuto();
        FordZTW.buildAuto("doc/autoTest.txt");
        FordZTW.printAuto("Focus Wagon ZTW");
        FordZTW.updateOptionSetName("Focus Wagon ZTW","Color","New Color");
        FordZTW.updateOptionPrice("Focus Wagon ZTW","New Color","Fort Knox Gold Clearcoat Metallic",(float)10);
        FordZTW.printAuto("Focus Wagon ZTW");
    }
}
