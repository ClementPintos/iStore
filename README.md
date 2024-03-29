# iStore

## Présentation du projet

iStore est une application qui vise à produire la meilleure expérience pour toutes les entreprises stockant des marchandises. Ce projet est une application complète utilisant Java pour permettre aux utilisateurs d'accéder à des informations sur les marchandises stockées ou d'autres données pertinentes. Cette application permet également aux utilisateurs de gérer l'inventaire en ajoutant/supprimant des marchandises.

## Installation

1. Assurez-vous que Java est installé sur votre machine. Vous pouvez vérifier cela en tapant `java -version` dans votre terminal. Si Java n'est pas installé, vous pouvez le télécharger et l'installer à partir du site officiel de Java.

2. Clonez ce dépôt sur votre machine locale en utilisant `git clone https://github.com/ClementPintos/iStore.git`.

3. Naviguez jusqu'au dossier du projet en utilisant `cd iStore`.

## Configuration de la base de données

1. Assurez-vous que vous avez un système de gestion de base de données (SGBD) installé, comme MySQL ou PostgreSQL.
2. Ouvrez votre SGBD et créez une nouvelle base de données pour le projet.
3. Exécutez le script SQL fourni pour configurer la base de données. Le script se trouve dans `src/main/java/Database/script.sql`.
4. Créer un fichier infos.env à la racine du projet, comme ce fichier et notez à l'intérieur les 3 lignes suivantes en faisant attention à bien remplacer vos informations à la place des placeholders :
   DB_PATH=LeCheminAVotreDatabase
   DB_USER=LeNomDUtilisateurDeLaDatabase
   DB_PASSWORD=LeMotDePasseDeLUtilisateurDeLaDatabase

## Compilation et exécution

1. Compilez le projet en utilisant `javac src/main/java/Main.java`.
2. Exécutez le projet en utilisant `java Main.java`.

   Si jamais la compilation ou l'exécution échoue, vérifiez si votre version de votre JDK est à jour et/ou essayez de lancer l'application directement depuis un IDE (exemple : IntelliJ IDEA).

## Utilisation

Après avoir lancé l'application, la première fenêtre demandera à l'utilisateur de créer un compte (requiert un email/mot de passe) ou de se connecter (en utilisant uniquement l'email/mot de passe).

## Contribution

Les contributions sont les bienvenues! Pour contribuer :

1. Forkez ce dépôt.
2. Créez votre branche de fonctionnalités (`git checkout -b feature/AmazingFeature`).
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`).
4. Poussez vers la branche (`git push origin feature/AmazingFeature`).
5. Ouvrez une Pull Request.

## Licence

Distribué sous la licence MIT. Voir `LICENSE` pour plus d'informations.

## Contact

Clément pintos - clement.pintos@outlook.fr

Lien du projet : https://github.com/ClementPintos/iStore
