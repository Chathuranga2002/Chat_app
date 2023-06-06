import Modle.ClientHndler;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer {
    private static ArrayList<ClientHndler> clients = new ArrayList<ClientHndler>();

    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket(50000);

        Socket accept;

        System.out.println("Waiting for Client ...");
        while (true) {


            accept = serverSocket.accept();
            System.out.println("Client Connected");
            ClientHndler clientThread = new ClientHndler(accept, clients);
            clients.add(clientThread);
            clientThread.start();
        }
    }
}