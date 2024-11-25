/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Samue
 */


public class Server extends Thread {

    private static final List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor iniciado en el puerto 12345.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado.");

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized void addClient(PrintWriter out) {
        clientWriters.add(out);
    }

    public static synchronized void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message); // Envía el mensaje con un salto de línea
        }
    }
}

class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Auto-flush habilitado

            Server.addClient(out);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Mensaje recibido del ESP32: " + message);

                Server.broadcastMessage(message);
            }
        } catch (IOException e) {
            System.err.println("Error en el manejo del cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Conexión cerrada con el cliente.");
            } catch (IOException e) {
                System.err.println("Error cerrando el socket: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
