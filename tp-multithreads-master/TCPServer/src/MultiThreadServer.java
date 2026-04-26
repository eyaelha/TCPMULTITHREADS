import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {
    private static final int PORT = 5000;
    private static final int THREAD_POOL_SIZE = 5;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur démarré sur le port " + PORT);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    String clientAddress = clientSocket.getInetAddress().getHostAddress();
                    System.out.println("Client connecté : " + clientAddress);

                    ClientHandler handler = new ClientHandler(clientSocket);
                    executor.submit(handler);
                } catch (IOException e) {
                    System.err.println("Erreur lors de l'acceptation d'une connexion client : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du démarrage du serveur sur le port " + PORT + " : " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
