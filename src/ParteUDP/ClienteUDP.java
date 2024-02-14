package ParteUDP;

import java.io.*;
import java.net.*;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            // Definir el host y el puerto al que el cliente se conectará
            String host = "localhost";
            int port = 9876;

            // Crear un socket UDP
            DatagramSocket clientSocket = new DatagramSocket();

            // Crear un buffer para enviar los datos al servidor
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Solicitar al usuario que ingrese un número
                System.out.print("Ingrese un número (o escriba 'salir' para terminar): ");
                String numberStr = userInput.readLine();

                // Si el usuario escribe "salir", terminar el bucle
                if (numberStr.equalsIgnoreCase("salir")) {
                    break;
                }

                // Convertir el número a bytes
                byte[] sendData = numberStr.getBytes();

                // Obtener la dirección IP del servidor
                InetAddress serverAddress = InetAddress.getByName(host);

                // Crear un paquete con los datos a enviar y la dirección del servidor
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);

                // Enviar el paquete al servidor
                clientSocket.send(sendPacket);

                // Crear un buffer para recibir los datos del servidor
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Recibir el paquete del servidor
                clientSocket.receive(receivePacket);

                // Obtener los datos recibidos y mostrarlos
                String squareStr = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("El cuadrado del número es: " + squareStr);
            }

            // Cerrar el socket
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
