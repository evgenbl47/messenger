package com.nt4h.messenger.tcp.first;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(27015)) {
            System.out.println("Server socket initialisation");
            Socket clentSocket = serverSocket.accept();

            System.out.println("Client connected to server");

            PrintWriter writer = new PrintWriter(
                    clentSocket.getOutputStream(),
                    true
            );

            BufferedReader reader = new BufferedReader(new InputStreamReader(clentSocket.getInputStream()));

            while (!clentSocket.isClosed()) {
                String recivedMessage = reader.readLine();

                if (recivedMessage.equals("stop")) {
                    clentSocket.close();
                    serverSocket.close();
                    return;
                }

                System.out.println("Recived message from client: " + recivedMessage);

                String reversedMessage = new StringBuilder(recivedMessage)
                        .reverse()
                        .toString();

                writer.println(reversedMessage);

                System.out.println(
                        "Reversed symbols in string: " + reversedMessage
                );
            }

        }
    }
}
