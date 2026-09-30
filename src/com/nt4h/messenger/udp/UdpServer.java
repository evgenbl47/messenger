package com.nt4h.messenger.udp;

import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {
    public static final int PORT = 27015;

    public static void main(String[] args) throws SocketException {

        try (DatagramSocket datagramSocket = new DatagramSocket(PORT)) {

        }

    }
}
