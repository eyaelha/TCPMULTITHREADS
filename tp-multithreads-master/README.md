# Multi-Threaded TCP Server

A robust, multi-threaded Java TCP server implementation using a fixed thread pool. This project demonstrates how to handle multiple concurrent client connections efficiently without blocking the main server thread.

## 🚀 Features

- **Multi-threading**: Uses `ExecutorService` with a fixed thread pool of 5 threads.
- **Concurrent Processing**: Handles up to 5 clients simultaneously; additional clients are queued.
- **Interactive Protocol**: Responds to specific commands:
  - `hello` -> Greets the client.
  - `time` -> Returns current server date and time.
  - `bye` -> Gracefully closes the specific client connection.
  - *Other* -> Echoes the received message.
- **Detailed Logging**: Server console tracks thread activity, client IPs, and incoming messages.

## 🛠️ Project Structure

```Serveur TCP Multi-Threadé - Implémentation RobusteUn serveur TCP multi-threadé en Java performant, utilisant un pool de threads fixe. Ce projet démontre comment gérer efficacement plusieurs connexions clients concurrentes sans bloquer le thread principal du serveur.🚀 Fonctionnalités Clés
Multi-threading Avancé : Utilise ExecutorService avec un pool de threads fixe de 5 threads.
Traitement Concurrent : Gère jusqu'à 5 clients simultanément ; les clients supplémentaires sont mis en attente dans une queue.
Protocole Interactif Intelligent : Répond intelligemment à des commandes spécifiques :

hello → Salutation personnalisée au client.
time → Retourne la date et l'heure actuelles du serveur au format yyyy/MM/dd HH:mm:ss.
bye → Ferme gracieusement la connexion du client spécifique.
Autres commandes → Fait un écho du message reçu.


Journalisation Détaillée : Console serveur affiche l'activité des threads, les adresses IP des clients, les timestamps et tous les messages entrants/sortants.
🛠️ Architecture du ProjetTCPServer/└── src/    ├── MultiThreadServer.java    # Classe principale, gestion du pool & acceptation des connexions    └── ClientHandler.java        # Logique Runnable pour chaque client (traitement individuel)💻 Exécution dans IntelliJ IDEA Ultimate
Localisez MultiThreadServer.java dans la fenêtre Project (dossier src).
Cliquez droit sur le fichier et sélectionnez Run 'MultiThreadServer.main()'.
La console affichera un bloc de démarrage avec confirmation du port 5000.
🧪 Méthodologie de TestTest Basique avec Telnet (Windows CMD/PowerShell)
Ouvrez un terminal (CMD ou PowerShell).
Tapez : telnet localhost 5000
Testez les commandes interactives :

Entrez hello → Réponse : Bonjour client !
Entrez time → Réponse : Date et heure actuelles du serveur
Entrez bye → Fermeture de la connexion
Entrez n'importe quel texte → Écho du message


Test de la Limite du Pool de Threads (Cas Avancé)
Ouvrez 5 terminaux séparés et connectez chacun au serveur : telnet localhost 5000
Observez les logs serveur :

Vous verrez les threads pool-1-thread-1 à pool-1-thread-5 en utilisation active.
Chaque client est assigné à un thread du pool.


Ouvrez un 6e terminal et tentez une connexion supplémentaire : telnet localhost 5000
Résultat Observé :

Le 6e client se connecte avec succès mais n'est pas traité immédiatement.
Il reste en attente dans la queue du pool de threads.
Dès qu'un client existant se déconnecte (commande bye), un thread se libère.
Le 6e client reçoit alors un thread disponible et commence à être traité.


✅ Exigences du Projet - Vérifiées
 Implémentation correcte de Runnable : ClientHandler implémente Runnable.
 Utilisation de Executors.newFixedThreadPool(5) : Pool fixe de 5 threads configuré.
 Gestion appropriée des exceptions : IOException et Throwable gérées correctement.
 Architecture non-bloquante : Le serveur accepte les connexions sans bloquer le pool de threads.
 Journalisation complète : Affichage détaillé de l'activité serveur et des clients.
TCPServer/
└── src/
    ├── MultiThreadServer.java  # Main server class & Thread Pool management
    └── ClientHandler.java      # Runnable logic for individual clients
```

## 💻 How to Run in IntelliJ IDEA Ultimate
   - Locate `MultiThreadServer.java` in the Project tool window (`src` folder).
   - Right-click the file and select **Run 'MultiThreadServer.main()'**.
   - The console should display: `Serveur démarré sur le port 5000`.

## 🧪 How to Test

### Using Telnet (Windows CMD)
1. Open a terminal (CMD or PowerShell).
2. Type: `telnet localhost 5000`
3. Try the following commands:
   - Type `hello` and press Enter.
   - Type `time` and press Enter.
   - Type `bye` to disconnect.

### Testing the Thread Pool Limit
1. Open **5 separate terminals** and connect each to the server via `telnet localhost 5000`.
2. Observe the server logs; you will see `pool-1-thread-1` through `pool-1-thread-5` being used.
3. Open a **6th terminal** and attempt to connect.
4. **Observation**: The 6th client will connect but will not receive responses until one of the first 5 clients disconnects (using `bye`), freeing up a thread in the pool.

![Thread Pool Limit Test](TCPServer/src/Screenshot-multiple-request-cmds.png)

## 📝 Requirements Verified
- [x] Implementation of `Runnable`.
- [x] Use of `Executors.newFixedThreadPool(5)`.
- [x] Proper exception handling (`IOException`).
- [x] Non-blocking server architecture.

## 📸 Proof of Testing

![Server and Client Testing Proof](TCPServer/src/tp-multithreads-snapshot.png)

---
**Author:** HYNDI ELMEHDI  
**Date:** April 19, 2026
