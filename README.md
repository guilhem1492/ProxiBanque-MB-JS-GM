# ProxiBanqueSI

Projet développé par Julien S. Mathieu B. et Guilhem M.

Le système d'information commandité par la banque ProxiBanque est un web service qui doit permettre à ses conseillers clientèle de créer un client, modifier les informations du client, lire les informations du client et supprimer un client. En outre, le conseiller clientèle peut faire des virements de compte à compte ainsi que des simulations de crédit.

Les conseillers travaillent sous la responsabilité du gérant de leur agence et peuvent assister jusqu'à 10 clients maximum. Chaque client peut disposer d'un compte courant et d'un compte épargne et doit choisir entre une carte Visa Electron et une carte Visa Premier. Il bénéficie d'une autorisation de découvert par défaut de 1000 euros. Le taux de rémunération de son compte épargne s'élève à 3 %.

Une fonctionnalité d'audit proposée par le système d'information ProxiBanque permet au conseiller d'analyser les comptes de ses clients et d'afficher les comptes de particuliers débiteurs de plus de 5000 euros et les comptes d'entreprises débiteurs de plus de 50000 euros.

# Technologies

Java, Spring Boot, JPA, PostgreSQL.

# Fonctionnalités

- Un conseiller d'une agence doit saisir son identifiant et son mot de passe pour se connecter à l'application ;
- Un conseiller peut afficher la liste de ses clients avec leurs données ;
- Un conseiller peut enregistrer un nouveau client ;
- Un conseiller peut modifier les informations d'un client ;
- Un conseiller peut supprimer un client si ses compte sont à 0 ;
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
