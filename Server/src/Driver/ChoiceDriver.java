package Driver;


import Excetions.AutoException;
import Model.Automobile;
import Util.ReadFileUtil;

public class ChoiceDriver {
    public static void main(String[] args)throws AutoException{
        String fileName = "PartB/doc/auto.txt";
        Automobile auto = ReadFileUtil.buildAutoObject(fileName);

        auto.setOptionChoice("Color", "Liquid Grey Clearcoat Metallic");
        auto.setOptionChoice("Transmission", "manual");
        auto.setOptionChoice("Brakes/Traction Control", "ABS");
        auto.setOptionChoice("Side Impact Air Bags", "present");
        auto.setOptionChoice("Power Moonroof", "present");
        auto.print();

    }
}
