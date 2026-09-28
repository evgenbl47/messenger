package com.nt4h.messenger.tcp.second;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RawTcpServer {

    public static final int PORT = 27015;

//add repeating
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket clientSocket = serverSocket.accept();

            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] encodedBytesInArray = inputStream.readNBytes(4);
            System.out.println(Arrays.toString(encodedBytesInArray));

            int byteInArray =
                    ((encodedBytesInArray[0] & 0xFF) << 24) |
                    ((encodedBytesInArray[1] & 0xFF) << 16) |
                    ((encodedBytesInArray[2] & 0xFF) << 8) |
                    ((encodedBytesInArray[3] & 0xFF));

            System.out.println(byteInArray);

            byte[] messageAsByteArray = inputStream.readNBytes(byteInArray);

            String message = new String(messageAsByteArray, StandardCharsets.UTF_8);
            System.out.println(message);
        }

    }
}
