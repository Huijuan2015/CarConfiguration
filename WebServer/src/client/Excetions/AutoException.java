/**   
* @Title: AutoException.java 
* @Description: Define class AutoException
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/10/2016  
*/

package client.Excetions;

public class AutoException extends Exception {
    private static final long serialVersionUID = 3687216170613877964L;
    private int errorNo;

    public AutoException(int no) {
        this.errorNo = no;
    }

    public int getErrorNo() {
        return errorNo;
    }

    public  void fix() {
        Fix1to100 f1 = new Fix1to100();
        switch (errorNo) {
        case 1:
            f1.fix1();
            break;
        case 2:
            f1.fix2();
            break;
        case 3:
            f1.fix3();
            break;
        case 4:
            f1.fix4();
            break;
        case 5:
            f1.fix5();
            break;

        }
    }
}
