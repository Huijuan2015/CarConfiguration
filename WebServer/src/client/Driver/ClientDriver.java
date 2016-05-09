package client.Driver;


import client.Client.DefaultSocketClient;

public class ClientDriver {

    public static void main(String[] args) {
        DefaultSocketClient client = new DefaultSocketClient("localhost", 6543);
        Thread clientStart = new Thread(client);
        clientStart.start();
    }
}
