package Driver;

import Adapter.BuildAuto;
import Adapter.UpdateAuto;
import Scale.EditOptions;

public class NThreadDriver {
    public static void main(String[] args) {
        BuildAuto auto = new BuildAuto();
        auto.buildAuto("doc/auto.txt");

        String givenModel = "Focus Wagon ZTW";
        String givenOptSet = "Transmission";
        String optionName = "automatic";
        float newOptPrice1 = 100;
        float newOptPrice2 = 200;
        float newOptPrice3 = 300;

        EditOptions edit1 =new EditOptions(auto,givenModel,givenOptSet,optionName,newOptPrice1);
        Thread thread1 = new Thread(edit1);
        thread1.start();

        EditOptions edit2 =new EditOptions(auto,givenModel,givenOptSet,optionName,newOptPrice2);
        Thread thread2 = new Thread(edit2);
        thread2.start();

        EditOptions edit3 =new EditOptions(auto,givenModel,givenOptSet,optionName,newOptPrice3);
        Thread thread3 = new Thread(edit3);
        thread3.start();
    }
}
