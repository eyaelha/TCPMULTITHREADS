import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        String clientAddress = clientSocket.getInetAddress().getHostAddress();
        String threadName = Thread.currentThread().getName();
        
        System.out.println("Thread " + threadName + " traite le client " + clientAddress);

        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Message reçu : " + inputLine);
                
                String message = inputLine.trim().toLowerCase();
                
                if (message.equals("hello")) {
                    out.println("Bonjour client !");
                } else if (message.equals("time")) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    out.println("Date et heure actuelles : " + dtf.format(LocalDateTime.now()));
                } else if (message.equals("bye")) {
                    out.println("Connexion fermée");
                    break;
                } else {
                    out.println("Message reçu : " + inputLine);
                }
            }
        } catch (IOException e) {
            System.err.println("[" + threadName + "] Erreur avec le client " + clientAddress + " : " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("[" + threadName + "] Connexion fermée pour " + clientAddress);
            } catch (IOException e) {
                System.err.println("[" + threadName + "] Erreur lors de la fermeture du socket : " + e.getMessage());
            }
        }
    }
}
