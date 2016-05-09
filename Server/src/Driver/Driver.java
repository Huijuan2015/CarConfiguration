package Driver;


import Adapter.BuildAuto;
import Adapter.ProxyAutomobile;


public class Driver {
    public static void main(String[] args) throws InterruptedException  {
        ProxyAutomobile auto = new BuildAuto();
        auto.buildAuto("PartB/doc/auto.txt");
        auto.printAuto("Focus Wagon ZTW");

        auto.updateOptionSetName("Focus Wagon ZTW","Color","New Color");
        auto.updateOptionPrice("Focus Wagon ZTW","New Color","Fort Knox Gold Clearcoat Metallic",(float)10);
        auto.printAuto("Focus Wagon ZTW");


    }
}
