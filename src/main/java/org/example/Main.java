package org.example;

import application.Configuration;
import controleur.Interaction;
import modele.*;

public class Main {
    public static void main(String[] args) {

//        PlateauDeJeu plateau = new PlateauDeJeu();
//
//        // création de quatre personnages
//        Roi roi = new Roi();
//        plateau.ajouterPersonnage(roi);
//        Magicienne magicienne = new Magicienne();
//        plateau.ajouterPersonnage(magicienne);
//        Assassin assassin = new Assassin();
//        plateau.ajouterPersonnage(assassin);
//        Condottiere condottiere = new Condottiere();
//        plateau.ajouterPersonnage(condottiere);
//        // création de trois joueurs
//        Joueur joueur1 = new Joueur("Milou");
//        plateau.ajouterJoueur(joueur1);
//        Joueur joueur2 = new Joueur("Billy");
//        plateau.ajouterJoueur(joueur2);
//        Joueur joueur3 = new Joueur("Belle");
//        plateau.ajouterJoueur(joueur3);
//        Joueur joueur4 = new Joueur("Jule");
//
//        // on associe les personnages aux joueurs
//        roi.setJoueur(joueur1);
//        magicienne.setJoueur(joueur3);
//        assassin.setJoueur(joueur2);
//        condottiere.setJoueur(joueur4);
//        Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
//        Quartier quartier2 = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
//        Quartier quartier3 = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 15);
//        // création d'une pioche:
//        Pioche pioche = plateau.getPioche();
//        Quartier q = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
//        pioche.ajouter(q);
//        q = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
//        pioche.ajouter(q);
//        q = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
//        pioche.ajouter(q);
//        q = new Quartier("taverne", Quartier.TYPE_QUARTIERS[3], 1);
//        pioche.ajouter(q);
//        q = new Quartier("échoppe", Quartier.TYPE_QUARTIERS[3], 2);
//        pioche.ajouter(q);
//        q = new Quartier("basilique", Quartier.TYPE_QUARTIERS[4], 4, "A la fin de la partie, ...");
//        pioche.ajouter(q);
//        q = new Quartier("cathédrale", Quartier.TYPE_QUARTIERS[0], 5);
//        pioche.ajouter(q);
//        q = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
//        pioche.ajouter(q);
//        q = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
//        pioche.ajouter(q);
//        q = new Quartier("hôtel de ville", Quartier.TYPE_QUARTIERS[3], 15);
//        pioche.ajouter(q);
//        q = new Quartier("bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, "Si vous choisissez...");
//        pioche.ajouter(q);
//        pioche.melanger();
//
//        // on distribue les cartes aux joueurs:
//        joueur1.ajouterQuartierDansMain(pioche.piocher());
//        joueur1.ajouterQuartierDansMain(pioche.piocher());
//        joueur2.ajouterQuartierDansMain(pioche.piocher());
//        joueur2.ajouterQuartierDansMain(pioche.piocher());
//        joueur2.ajouterQuartierDansMain(pioche.piocher());
//        joueur3.ajouterQuartierDansMain(pioche.piocher());
//        joueur3.ajouterQuartierDansMain(pioche.piocher());
//        roi.construire(quartier1);
//        roi.construire(quartier3);
//        magicienne.construire(quartier2);

//        System.out.println("Joueur 2 : "+joueur2.getMain().size());
//        System.out.println("Joueur 2 : "+joueur2.getMain());   System.out.println(" ");

//        System.out.println("Joueur 3 : "+joueur3.getMain().size());
//        System.out.println("Joueur 3 : "+joueur3.getMain());System.out.println(" ");System.out.println(" ");System.out.println(" ");


        // magicienne.utiliserPouvoir();
//        condottiere.utiliserPouvoir();
//        System.out.println("Joueur 2 : "+joueur2.getMain().size());
//        System.out.println("Joueur 2 : "+joueur2.getMain());
//        System.out.println(" ");
//        System.out.println("Joueur 3 : "+joueur3.getMain().size());
//        System.out.println("Joueur 3 : "+joueur3.getMain());


        Pioche pioche = Configuration.nouvellePioche();
        PlateauDeJeu plateau = Configuration.configurationDeBase(pioche);

        System.out.println(plateau.getPioche());
        System.out.println(plateau.getPioche().nombreElements());
        System.out.println(plateau.getNombrePersonnages());
    }
}
