import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Création de la socket
            DatagramSocket socket = new DatagramSocket(12345); // Port d'écoute
            
            // Réception de l'objet du client
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Voiture voiture = (Voiture) objectInputStream.readObject();
            
            // Traitement de l'objet
            voiture.setCarburant(50); // Exemple de modification
            
            System.out.println("Objet voiture reçu du client : Carburant = " + voiture.getCarburant());
            
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
