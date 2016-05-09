/**   
* @Title: EditOptions.java 
* @Description: Define class EditOptions
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/10/2016  
*/

package client.Scale;


import client.Adapter.BuildAuto;
import client.Adapter.UpdateAuto;


public class EditOptions implements Runnable {
    /**
     * auto to EditOptions is what BankAccount to Saver or Spender
     * that is :the shared resource
     * */
    private UpdateAuto auto;
    //these are params for editing an Option's price
    private String givenModel;
    private String givenOptSet;
    private String optionName;
    private float newOptPrice;

    public EditOptions(BuildAuto auto, String givenModel, String givenOptSet, String optionName, float newOptPrice) {
        this.auto = auto;
        this.givenOptSet = givenOptSet;
        this.givenModel = givenModel;
        this.optionName = optionName;
        this.newOptPrice = newOptPrice;

    }

    @Override
    public void run() {
        try {
            editOptionName();
            System.out.println("current thread :" + Thread.currentThread().getName());
            System.out.println(optionName +" set new price :"+newOptPrice);
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e);
        }

    }

    public synchronized void editOptionName() throws InterruptedException {
        auto.updateOptionPrice(givenModel, givenOptSet, optionName, newOptPrice);
    }
}
