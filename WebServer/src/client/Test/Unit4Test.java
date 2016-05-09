package client.Test;


import client.Client.CarModelOptionsIO;

import java.util.Enumeration;
import java.util.Properties;

public class Unit4Test {

    public void testReadProperties() throws Exception{
        CarModelOptionsIO carModelOptionsIO = new CarModelOptionsIO();
        String filepath = "carProperties.properties";
        Properties properties = carModelOptionsIO.readProperties(filepath);
        Enumeration<?> enu = properties.propertyNames();
        System.out.println("Read property file successful!!!The property names are below:");
        while(enu.hasMoreElements()){
            Object key = enu.nextElement();
            System.out.println(key);
        }

    }




    public static void main(String[] args) throws Exception{
       Unit4Test test = new Unit4Test();
        test.testReadProperties();
    }
}
