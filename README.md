Multi-Threaded TCP Server en JavaUn serveur TCP multi-threadé robuste et performant utilisant un pool de threads fixe. Ce projet démontre comment traiter efficacement plusieurs connexions clients concurrentes sans bloquer le thread principal du serveur.🚀 Caractéristiques Principales
Architecture Multi-threadée : Implémente ExecutorService avec un pool de threads fixe comprenant 5 threads.
Traitement Concurrent Optimisé : Gère simultanément jusqu'à 5 clients actifs ; les connexions supplémentaires sont placées en file d'attente.
Protocole Interactif Intelligent : Traite les commandes client selon le schéma suivant :

hello → Envoie une salutation personnalisée.
time → Transmet la date et l'heure actuelles au format yyyy/MM/dd HH:mm:ss.
bye → Ferme proprement la session client.
Autres entrées → Retourne un écho du message reçu.


Journalisation Avancée : La console serveur enregistre en détail l'activité des threads, les adresses IP des clients et l'historique complet des messages.
🛠️ Organisation du ProjetTCPServer/└── src/    ├── MultiThreadServer.java    # Classe serveur principale avec gestion du pool de threads    └── ClientHandler.java        # Implémentation Runnable pour traitement individuel des clients💻 Lancement dans IntelliJ IDEA Ultimate
Identifiez MultiThreadServer.java dans l'explorateur Project (répertoire src).
Effectuez un clic droit et sélectionnez Run 'MultiThreadServer.main()'.
La console affichera un message de confirmation du démarrage sur le port 5000.
🧪 Procédure de TestTest Basique via Telnet (Windows CMD/PowerShell)
Lancez un terminal (CMD ou PowerShell).
Exécutez la commande : telnet localhost 5000
Testez les commandes disponibles :

Saisissez hello et appuyez sur Entrée → Réception d'une salutation.
Saisissez time et appuyez sur Entrée → Affichage de l'heure serveur.
Saisissez bye et appuyez sur Entrée → Déconnexion propre.
Saisissez tout autre texte → Réception d'un écho du message.


Validation de la Limite du Pool de Threads
Ouvrez 5 terminaux distincts et établissez une connexion sur chacun : telnet localhost 5000
Consultez les journaux serveur pour observer :

L'attribution des threads pool-1-thread-1 jusqu'à pool-1-thread-5 aux clients actifs.
La correspondance entre chaque client et son thread assigné.


Lancez une 6e connexion depuis un nouveau terminal : telnet localhost 5000
Comportement Observé :

Le 6e client établit la connexion avec succès mais reste en attente.
Celui-ci demeure dans la file d'attente du pool sans traitement immédiat.
Au moment où un client existant se déconnecte (commande bye), un thread se libère.
Le 6e client obtient alors un thread libre et commence son traitement.


✅ Critères du Projet - Conformité Validée
 Implémentation conforme de Runnable : La classe ClientHandler implémente correctement l'interface.
 Utilisation de Executors.newFixedThreadPool(5) : Pool configuré avec taille fixe de 5 threads.
 Gestion robuste des exceptions : Gestion complète des IOException et Throwable.
 Architecture non-bloquante : Le serveur accepte les connexions sans monopoliser les ressources du pool.
 Journalisation exhaustive : Suivi détaillé de toutes les opérations serveur et interactions client.
