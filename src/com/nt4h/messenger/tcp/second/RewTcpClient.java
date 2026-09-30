package com.nt4h.messenger.tcp.second;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RewTcpClient {
    private static final String HOST = "127.0.0.1";
    //add repeating
    public static void main(String[] args) throws IOException {

        try (Socket clientSocket = new Socket(HOST, RawTcpServer.PORT)) {
            OutputStream outputStream = clientSocket.getOutputStream();

            Scanner scanner = new Scanner(System.in);

            while (!clientSocket.isClosed()) {

            System.out.print("Введите сообщение: ");
            String message = scanner.nextLine();
            if ("exit".equalsIgnoreCase(message.trim())) {
                System.out.println("Завершение работы...");
                break;
            }

            byte[] messageAsByteArray = message.getBytes(StandardCharsets.UTF_8);
            int bytesInArray = messageAsByteArray.length;

            byte[] encodedBytesInArray = {
                    (byte) (bytesInArray >>> 24),
                    (byte) (bytesInArray >>> 16),
                    (byte) (bytesInArray >>> 8),
                    (byte) (bytesInArray)
            };

            outputStream.write(encodedBytesInArray);
            outputStream.write(messageAsByteArray);
            }
        }
    }
}
