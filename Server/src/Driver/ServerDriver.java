/**   
* @Title: ServerDrivere.java 
* @Description: start server
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/20/2016  
*/

package Driver;

import Server.DefaultSocketServer;


public class ServerDriver {

    public static void main(String[] args) {
        DefaultSocketServer server = new DefaultSocketServer("localhost", 6543);
        Thread serverStart = new Thread(server);
        serverStart.start();
    }
}
