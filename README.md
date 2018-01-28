# Kiss My Place

Jeu Android basé sur de la localisation

## Contexte

Projet réalisé à l'UPMC dans le cadre de l'UE PPM *Programmation sur Plateforme Mobile* et encadré par **Etienne Renault**.

## Architecture

```
* CSVHandler.java
* ConstantStat.java
* ListViewAdapter.java
* MainActivity.java
* GameActivity.java
* MapView.java
* ScoreView.java
* StreetView.java
```

### CSVHandler

Ouvre un csv en fonction du mode de jeu choisi et récupère N lignes différentes aléatoirement.
Permet de pouvoir modifier et ajouter autant de lieux à trouver en jeu sans avoir à modifier le code.

### ConstantStat

### ListViewAdapter

Gère l'affichage en liste des scores

### MainActivity (Activité)

Défini des listener sur tous les  boutons de l'accueil et gère le passage du mode du type de jeu à l'activité appelée.

### GameActivity (Activité)

Appelle les fragments relatifs au jeu (StreetView et MapView)

### MapView (Fragment)

Gère toute la partie jeu. 
Charge une map après avoir récupéré une liste de valeurs aléatoires via le CSVHandler et lance le jeu sur un nombre N de tours.
Un listener est en attente sur la map, il récupère la coordonnée choisie par le joueur sur la map et trace un deux marqueurs et un trait pour symboliser la distance.
Une fonction affiche alors un message contenant la distance entre les deux marqueurs, le joueur ne peux pas passer à la suite sans avoir validé au préalable la boite de dialogue.
Le score est enregistré en fonction du mode de jeu (si on joue en reverse, plus on est loin plus l'on marque de points) de manière persistante dans un fichier.

Le lvlHandler vérifie s'il reste des tours à jouer, alors il set pour le street view la prochaine destination à trouver, sinon il informe le joueur du score final et retourne sur l'écran d'accueil.

### ScoreView (Activité)

Gère l'affichage des scores

###  StreetView (Fragment)

Initialise la vue Google Street

## Partie Obligatoire

- [x] Affichage de l'écran d'accueil
- [x] Affichage de la carte et du street view
- [x] Affichage d'un trait entre la position pointée et celle réelle
- [x] Affichage des statistiques
- [x] Notifications informant l'utilisateur de ce que l'on est en train de faire (distances et scores)

## Options

- [x] Rotation des écrans
- [x] Persistence durable
- [x] Partage du score
- [x] Mode de jeu inversé

## Difficultés rencontrées

* Des soucis sur les passages d'Objets entre fragments, nous avons donc choisi de ne pas charger tout le contenu des csv d'un coup pour palier à ce problème et de faire une lecture d'un nombre limité de valeurs directement dans le fragment à chaque lancement de partie.

* Sur la compréhension du mode de fonctionnement des fragments et leur liaison avec les Layout

* La récupération de données de latitude et longitude via des opendata n'a pas été fructueuse car celles-ci ne mènent pas forcément sur des lieux accessibles à google street. 

## Authors

* **Abdelatif Mehellou** - *3500130* 
* **Sandra Laduranti** - *3002158*
