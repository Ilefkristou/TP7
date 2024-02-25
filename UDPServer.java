import java.net.*;
import java.util.Date;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Création du socket UDP avec le port 1250
            socket = new DatagramSocket(1250);

            byte[] buffer = new byte[1024];

            // Boucle infinie pour écouter continuellement les datagrammes entrants
            while (true) {
                // Réception du datagramme
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);

                // Extraction de l'adresse IP et du port de l'émetteur
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Récupération de la date et de l'heure courantes
                String dateTime = new Date().toString();
                byte[] sendData = dateTime.getBytes();

                // Création du datagramme de réponse
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                // Envoi du datagramme contenant la date et l'heure courantes à l'émetteur
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
