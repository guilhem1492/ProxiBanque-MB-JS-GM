# ProxiBanqueSI

Projet développé par Julien S. Mathieu B. et Guilhem M.
Il s'agit d'un webservice.

Le système d'information (SI) de ProxiBanque s'appelle ProxiBanqueSI. Il permet au conseiller
clientèle de créer un client, modifier les informations du client, lire les informations du client et supprimer un client. Le conseiller clientèle peut faire des virements de compte à compte ainsi que des simulations de crédit.


# Technologies

Java, Spring Boot, JPA, PostgreSQL.

# Fonctionnalités

- Un conseiller d'une agence doit saisir son identifiant et son mot de passe pour se connecter à l'application ;
- Un conseiller peut afficher la liste de ses clients avec leurs données ;
- Un conseiller peut enregistrer un nouveau client ;
- Un conseiller peut modifier les informations d'un client ;
- Un conseiller peut supprimer un client ;
- Un conseiller peut effectuer des virements externes entre ses clients, de comptes courants à comptes courants ;
- Un conseiller peut effectuer des virements internes pour un client, d'un compte courant à un compte épargne et vice-versa ;
- Un conseiller peut afficher sur la page "Audit" les comptes courants débiteurs de plus de 5000 euros.

# Installation

Clonez le projet sur votre poste :

git clone https://github.com/guilhem1492/ProxiBanque-MB-JS-GM.git

Importez le dans votre Workspace STS/Eclipse et lancez le projet Spring Boot dans le Dashboard.

# Reste à implémenter

- Lier les conseillers à des agences ;
- Lier chaque agence à un gérant unique ;
- Lier des cartes de crédit à chaque client, au choix : carte Visa Electron ou Visa Premier ;
- Appliquer un taux de rémunération de 3% aux comptes épargnes des clients ;
- La possibilité pour le conseiller d'effectuer des simulations de crédits pour ses clients.
