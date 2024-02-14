package ParteUDP;

import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            // Definir el puerto en el que el servidor estará escuchando
            int port = 9876;

            // Crear un socket UDP
            DatagramSocket serverSocket = new DatagramSocket(port);
            System.out.println("Servidor esperando conexiones en el puerto " + port + "...");

            // Crear un buffer para recibir los datos del cliente
            byte[] receiveData = new byte[1024];

            while (true) {
                // Recibir el paquete del cliente
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Obtener los datos recibidos y convertirlos a un número
                String numStr = new String(receivePacket.getData(), 0, receivePacket.getLength());
                int num = Integer.parseInt(numStr);

                // Calcular el cuadrado del número
                int square = num * num;

                // Obtener la dirección IP y el puerto del cliente
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Convertir el resultado a un arreglo de bytes
                byte[] sendData = String.valueOf(square).getBytes();

                // Enviar el resultado al cliente
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
