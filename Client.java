import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Création de la socket
            DatagramSocket socket = new DatagramSocket();
            
            // Création de l'objet voiture
            Voiture voiture = new Voiture("Modèle", "Type");
            
            // Envoi de l'objet au serveur
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(voiture);
            byte[] data = outputStream.toByteArray();
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Adresse du serveur
            int serverPort = 12345; // Port du serveur
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
            socket.send(packet);
            
            System.out.println("Objet voiture envoyé au serveur.");
            
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
