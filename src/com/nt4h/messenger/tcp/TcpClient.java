package com.nt4h.messenger.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        String message = "Hello world!";

        Socket socket = new Socket("127.0.0.1", 2015);

        OutputStream out = socket.getOutputStream();

        out.write(message.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();

    }
}

