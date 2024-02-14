package ParteTCP;

import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            // Definiremos el host y el puerto al que el cliente se conectará
            String hostName = "localhost";
            int portNumber = 12345;

            // Crear el socket del cliente
            Socket socket = new Socket(hostName, portNumber);

            // Establecer streams de entrada y salida para comunicarse con el servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Crear un lector de entrada para leer la entrada del usuario
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Bucle para solicitar números al usuario
            while (true) {
                // Leer el número del usuario
                System.out.print("Ingrese un número (o escriba 'salir' para terminar): ");
                String number = userInput.readLine();

                // Si el usuario escribe "salir", terminar el bucle
                if (number.equalsIgnoreCase("salir")) {
                    break;
                }

                // Enviar el número al servidor
                out.println(number);

                // Leer la respuesta del servidor y mostrarla
                String response = in.readLine();
                System.out.println("El cuadrado del número es: " + response);
            }

            // Cerrar conexiones
            out.close();
            in.close();
            userInput.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
