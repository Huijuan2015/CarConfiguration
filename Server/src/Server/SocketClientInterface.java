/**   
* @Title: SocketClientInterfaces.java 
* @Description: Define class SocketClientInterface
* @author: Huijuan Peng 
* @andrewID: huijuanp 
* @date: 02/20/2016  
*/

package Server;

public interface SocketClientInterface {
	boolean initializeServer();
    void handleSession();
    void closeSession();


}
