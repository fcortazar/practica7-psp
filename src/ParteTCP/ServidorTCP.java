package ParteTCP;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        try {
            // Definir el puerto en el que el servidor estará escuchando
            int portNumber = 12345;

            // Crear el socket del servidor
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Servidor esperando conexiones en el puerto " + portNumber + "...");

            // Esperar a que el cliente se conecte
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());

            // Establecer streams de entrada y salida para comunicarse con el cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Leer el número enviado por el cliente
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Convertir el número a entero
                int num = Integer.parseInt(inputLine);

                // Calcular el cuadrado del número
                int square = num * num;

                // Enviar el cuadrado al cliente
                out.println(square);
            }

            // Cerrar conexiones
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
