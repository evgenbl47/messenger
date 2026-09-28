package com.nt4h.messenger.tcp.first;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {

//    private static String MESSAGE = "Hello world!";

    public static void main(String[] args) throws IOException {
        System.out.println("Connected to server");

        try(Socket socket = new Socket("127.0.0.1", 27015)) {

            System.out.println("Connection success");

            PrintWriter writer = new PrintWriter(
                    socket.getOutputStream(),
                    true);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

//            writer.println(MESSAGE);
            Scanner sc = new Scanner(System.in);
            String message = "";
            String recivedMessage = "";
            while (!socket.isClosed()){
                    System.out.print("Enter your message: ");
                    message = sc.nextLine();

                if (message.equals("stop")) {
                socket.close();
                return;
                }
                writer.println(message);

                    recivedMessage = reader.readLine();
                    System.out.println("Message from server: " + recivedMessage);

            }
        }

    }
}

