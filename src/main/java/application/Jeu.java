package application;

import controleur.Interaction;
import modele.*;

import javax.annotation.processing.Generated;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jeu {
    private List<Integer> ordreJoueur = new ArrayList<>(Arrays.asList(0, 1, 2, 3)) {
    };
    private PlateauDeJeu plateau;
    private int numeroConfiguration;
    private Generated randomGenerator;
    List<Personnage> listePersonnagesEcarte = new ArrayList<>() {
    };

    public void jouer() {
        System.out.println("Bienvenue dans Citadelle");
        System.out.println("Menu: ");
        System.out.println("1. Jouer une partie");
        System.out.println("2. Afficher les règles");
        System.out.println("3. Quitter");

        boolean continu = true;

        do {
            int choix = Interaction.lireUnEntier(1, 3, "Votre choix entre: ");
            if (choix == 1) {
                jouerPartie();
            } else if (choix == 2) {
                afficherLesRegles();

            } else {
                System.out.println("Vous avez choisi de quitter");
                continu = false;
                return;
            }
        } while (continu);

    }

    private void afficherLesRegles() {
        System.out.println("Les règles sont les suivantes: ");
    }

    private void jouerPartie() {
        initialisation();
        System.out.println("Vous avez choisi de jouer une partie");
        boolean partieFini = true;
        do {

            if (!partieFinie()) {
                tourDeJeu();
                gestionCouronne();
                reinitialisationPersonnages();
            } else {
                System.out.println("La partie est finie");
                System.out.println("Felicitations au vainqueur");

                System.out.println("1. Voulez vous rejouer une partie");
                System.out.println("2. Afficher les règles");
                System.out.println("3. Quitter");
                System.out.println(" ");
                calculDesPoints();
                partieFini = false;
            }
        } while (partieFini);

    }

    private void initialisation() {
        Pioche pioche = Configuration.nouvellePioche();

        this.plateau = Configuration.configurationDeBase(pioche);
        System.out.println("Nombre de carte dans l'initialisation" + this.plateau.getPioche().nombreElements());
        for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
            this.plateau.getJoueur(i).ajouterPieces(2);
            for (int j = 0; j < 4; j++) {
                this.plateau.getJoueur(i).piocherUnQuartier(this.plateau.getPioche());
            }
        }
        this.plateau.getJoueur(Interaction.returnInt(4)).setPossedeCouronne(true);

        System.out.println("La partie est initialisée");
    }

    private void gestionCouronne() {
        if (this.plateau.getPersonnage(4).getJoueur() != null) {
            for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
                this.plateau.getJoueur(i).setPossedeCouronne(false);
            }
            this.plateau.getPersonnage(4).getJoueur().setPossedeCouronne(true);
        }


    }

    private void reinitialisationPersonnages() {
        //retire les joueurs
        for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
            this.plateau.getJoueur(i).setPersonnage(null);
        }
        //retire les personnages
        for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
            this.plateau.getPersonnage(i).setJoueur(null);
            this.plateau.getPersonnage(i).reinitialiserVole();
            this.plateau.getPersonnage(i).reinitialiserAssassine();
            this.plateau.getPersonnage(i).setIspick(false);
        }
    }

    private boolean partieFinie() {
        for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
            if (plateau.getJoueur(i).nbQuartiersDansCite() >= 8) {
                calculDesPoints();
                return true;
            }
        }
        return false;
    }

    private void tourDeJeu() {

        System.out.println("vous jouer un tour de jeu.");
        System.out.println(" ");

        //Set des personnages
        choixPersonnages();
        List<Personnage> personnageList = new ArrayList<>();
        personnageList.addAll(Arrays.asList(this.plateau.getListePersonnages()));

        // Organiser l'ordre des joueurs en fonction de la couronne
        Collections.sort(ordreJoueur, Comparator.comparingInt(joueurIndex -> this.plateau.getJoueur(joueurIndex).getPossedeCouronne() ? 0 : 1));


        //appel des joueurs/personnages :
        for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
            // 1) Le personnage est-il assassiné ?

            if (!this.plateau.getPersonnage(i).getAssassine() && this.plateau.getPersonnage(i).getIspick()) {
                //le personnage est volé.
                if (this.plateau.getPersonnage(i).getVole()) {
                    // Le joueurs est volé
                    this.plateau.getPersonnage(1).getJoueur().ajouterPieces(this.plateau.getPersonnage(i).getJoueur().nbPieces());
                    // retirer les pieces du joueurvolé
                    this.plateau.getPersonnage(i).getJoueur().retirerPieces(this.plateau.getPersonnage(i).getJoueur().nbPieces());
                    //le personnage percevoir Ressources Specifiques
                    this.plateau.getPersonnage(i).percevoirRessourcesSpecifiques();
                    //utilisation des pouvoir des personnages

                    if (!this.plateau.getPersonnage(i).getJoueur().getIsOrdinateur()) {
                        System.out.println("vous nétes pas un ordi " + this.plateau.getPersonnage(i).getJoueur().getNom() + " personnage " + this.plateau.getPersonnage(i).getNom());
                        //le joueur choisi les ressources qu'il veut percevoir
                        piocheCarteOuPiece(i);
                        // le personnage utilise sont pouvoir
                        this.plateau.getPersonnage(i).utiliserPouvoir();
                        buildQuartier(i);
                    } else {
                        System.out.println("vous étes un ordi " + this.plateau.getPersonnage(i).getJoueur().getNom() + " personnage " + this.plateau.getPersonnage(i).getNom());
                        piocheCarteOuPieceOrdi(i);
                        this.plateau.getPersonnage(i).utiliserPouvoirAvatar();
                        buildQuartierOrdi(i);
                        System.out.println(this.plateau.getPersonnage(i).getNom() + "a joué");
                        if (this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansCite() == 8 && manageIsFirst()) {
                            this.plateau.getPersonnage(i).getJoueur().isFirst();
                        }
                    }

                } // méme scénario sans le vol
                else {

                    if (!this.plateau.getPersonnage(i).getJoueur().getIsOrdinateur()) {

                        System.out.println("vous nétes pas un ordi " + this.plateau.getPersonnage(i).getJoueur().getNom() + " personnage " + this.plateau.getPersonnage(i).getNom());
                        //le joueur choisi les ressources qu'il veut percevoir
                        piocheCarteOuPiece(i);
                        // le personnage utilise sont pouvoir
                        this.plateau.getPersonnage(i).utiliserPouvoir();
                        buildQuartier(i);
                    } else {
                        System.out.println("vous étes un ordi " + this.plateau.getPersonnage(i).getJoueur().getNom() + " personnage " + this.plateau.getPersonnage(i).getNom());
                        piocheCarteOuPieceOrdi(i);
                        this.plateau.getPersonnage(i).utiliserPouvoirAvatar();
                        buildQuartierOrdi(i);
                        System.out.println(this.plateau.getPersonnage(i).getNom() + "a joué");
                        if (this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansCite() == 8 && manageIsFirst()) {
                            this.plateau.getPersonnage(i).getJoueur().isFirst();
                        }
                    }
                }
            } else if (this.plateau.getPersonnage(i).getAssassine()) {
                System.out.println("Vous etes assassiné vous ne pouvez pas jouer ce tour");
            }


        }
    }

    private boolean manageIsFirst() {
        for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
            if (this.plateau.getJoueur(i).isFirst()) {
                return false;
            }
        }
        return true;
    }

    // method qui pioche deux cartes est remplace l'une dans le packet prend en entrée le numéro du personnage qui joue:
    private void piocheCarteOuPiece(int i) {
        System.out.println("1) Voulez vous piocher?");
        System.out.println("1) Voulez vous récupéré des piéces?");
        int choixjoueurs = Interaction.lireUnEntier(1, 2, "Que faite vous ?: ");
        //Si le joueurs choisi les cartes
        if (choixjoueurs == 1) {

            ArrayList<Quartier> choixDecarte = new ArrayList<>();
            System.out.println("Nombre de cates dans pioches " + this.plateau.getPioche().nombreElements());
            choixDecarte.add(this.plateau.getPioche().piocher());
            choixDecarte.add(this.plateau.getPioche().piocher());
            System.out.println("Vous avez pioché: " + choixDecarte.get(0) + " et " + choixDecarte.get(1));
            int choixCarte = Interaction.lireUnEntier(1, 2, "Quel carte voulez vous garder?: ");
            plateau.getPersonnage(i).getJoueur().ajouterQuartierDansMain(choixDecarte.get(choixCarte - 1));
            choixDecarte.remove(choixCarte - 1);
            plateau.getPioche().ajouter(choixDecarte.get(0));
        }
        //Si le joueur choisi les piéces
        else {
            this.plateau.getPersonnage(i).getJoueur().ajouterPieces(2);
        }
    }

    private void piocheCarteOuPieceOrdi(int i) {

        int choixjoueurs = new Random().nextInt(2) + 1;
        //Si le joueurs choisi les cartes
        if (choixjoueurs == 1) {

            ArrayList<Quartier> choixDecarte = new ArrayList<>();
            System.out.println("Nombre de cates dans pioches " + this.plateau.getPioche().nombreElements());
            choixDecarte.add(this.plateau.getPioche().piocher());
            choixDecarte.add(this.plateau.getPioche().piocher());
            System.out.println("Vous avez pioché: " + choixDecarte.get(0) + " et " + choixDecarte.get(1));
            int choixCarte = new Random().nextInt(2) + 1;
            plateau.getPersonnage(i).getJoueur().ajouterQuartierDansMain(choixDecarte.get(choixCarte - 1));
            choixDecarte.remove(choixCarte - 1);
            plateau.getPioche().ajouter(choixDecarte.get(0));
        }
        //Si le joueur choisi les piéces
        else {
            this.plateau.getPersonnage(i).getJoueur().ajouterPieces(2);
        }
    }

    //Method pour contruire un quartier
    private void buildQuartier(int i) {

        boolean isSold = false;

        for (int l = 0; l < this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain(); l++) {

            if (this.plateau.getPersonnage(i).getJoueur().nbPieces() >= this.plateau.getPersonnage(i).getJoueur().getMain().get(l).getCout()) {
                isSold = true;
            }

        }
        if (isSold) {
            System.out.println("Voulez vous construire un quartier ?" + "Il vous reste " + this.plateau.getPersonnage(i).getJoueur().nbPieces() + " pieces");
            boolean isBuild = Interaction.lireOuiOuNon();
            //Oui le joueur veut construire un quartier
            if (isBuild) {

                boolean continu = true;
                System.out.println("Quel quartier voulez vous construire ?" + "Il vous reste " + this.plateau.getPersonnage(i).getJoueur().nbPieces() + " pieces");

                for (int k = 0; k < this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain(); k++) {
                    System.out.println(k + 1 + " " + this.plateau.getPersonnage(i).getJoueur().getMain().get(k));
                }
                System.out.println(this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain() + 1 + " pour ne pas construire");
                do {
                    //le choix du  joueur
                    int choixQuartier = Interaction.lireUnEntier(1, this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain(), "Votre choix entre: ");
                    //si le joueur a les fonds pour construire le quartier
                    if (this.plateau.getPersonnage(i).getJoueur().getMain().get(choixQuartier - 1).getCout() <= this.plateau.getPersonnage(i).getJoueur().nbPieces()) {
                        this.plateau.getPersonnage(i).getJoueur().ajouterQuartierDansCite(this.plateau.getPersonnage(i).getJoueur().getMain().get(choixQuartier - 1));
                        this.plateau.getPersonnage(i).getJoueur().getMain().remove(choixQuartier - 1);
                        continu = false;
                    }//si le joueur ne veut plus construire de quartier
                    else if (choixQuartier == this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain() + 1) {
                        System.out.println("Vous ne voulez pas construire de quartier");
                        continu = false;

                    }//si le joueur n'a pas les fonds pour construire le quartier
                    else {
                        System.out.println("Vous n'avez pas les fonds pour construire ce quartier.");
                    }

                } while (continu);
            }
            //Non le joueur ne veut pas construire de quartier
            else {
                System.out.println("Vous ne voulez pas construire de quartier");
            }
        } else {
            System.out.println("Vous n'avez pas les fond pour construire un quartier");
        }

    }

    private void buildQuartierOrdi(int i) {

        boolean isSold = false;

        for (int l = 0; l < this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain(); l++) {

            if (this.plateau.getPersonnage(i).getJoueur().nbPieces() >= this.plateau.getPersonnage(i).getJoueur().getMain().get(l).getCout()) {
                isSold = true;
            }

        }
        if (isSold) {
            System.out.println("Voulez vous construire un quartier ?" + "Il vous reste " + this.plateau.getPersonnage(i).getJoueur().nbPieces() + " pieces");
            boolean isBuild = new Random().nextBoolean();
            //Oui le joueur veut construire un quartier
            if (isBuild) {

                boolean continu = true;
                System.out.println("Quel quartier voulez vous construire ?" + "Il vous reste " + this.plateau.getPersonnage(i).getJoueur().nbPieces() + " pieces");

                for (int k = 0; k < this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain(); k++) {
                    System.out.println(k + 1 + " " + this.plateau.getPersonnage(i).getJoueur().getMain().get(k));
                }
                System.out.println(this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain() + 1 + " pour ne pas construire");
                do {
                    //le choix du joueur
                    int choixQuartier = new Random().nextInt(this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain());
                    //si le joueur a les fonds pour construire le quartier
                    if (this.plateau.getPersonnage(i).getJoueur().getMain().get(choixQuartier).getCout() <= this.plateau.getPersonnage(i).getJoueur().nbPieces()) {
                        this.plateau.getPersonnage(i).getJoueur().ajouterQuartierDansCite(this.plateau.getPersonnage(i).getJoueur().getMain().get(choixQuartier));
                        this.plateau.getPersonnage(i).getJoueur().getMain().remove(choixQuartier);
                        continu = false;
                    }//si le joueur ne veut plus construire de quartier
                    else if (choixQuartier == this.plateau.getPersonnage(i).getJoueur().nbQuartiersDansMain() + 1) {
                        System.out.println("Vous ne voulez pas construire de quartier");
                        continu = false;

                    }//si le joueur n'a pas les fonds pour construire le quartier
                    else {
                        System.out.println("Vous n'avez pas les fonds pour construire ce quartier.");
                    }

                } while (continu);
            }
            //Non le joueur ne veut pas construire de quartier
            else {
                System.out.println("Vous ne voulez pas construire de quartier");
            }
        } else {
            System.out.println("Vous n'avez pas les fond pour construire un quartier");
        }

    }

    private void choixPersonnages() {
        List<Personnage> personnageList = new ArrayList<>();
        personnageList.addAll(Arrays.asList(this.plateau.getListePersonnages()));

        //Set les personnages face visible et face caché
        setHiddenPersonnage(personnageList);
        System.out.println("Taille de la liste des personnages: " + personnageList.size());
        System.out.println("Liste des personnages: ");
        boolean continu = true;

        for (int j = 0; j < 4; j++) {

            do {

                //reste dans la boucle temps qu'il reste un perso a choisir.
                if (!personnageList.isEmpty()) {

                    // Si c'est un ordinateur qui joue
                    if (this.plateau.getJoueur(ordreJoueur.get(j)).getIsOrdinateur()) {
                        // Choix du personnage
                        int choix = new Random().nextInt(personnageList.size());
                        this.plateau.getJoueur(ordreJoueur.get(j)).setPersonnage(personnageList.get(choix));
                        personnageList.get(choix).setJoueur(this.plateau.getJoueur(ordreJoueur.get(j)));
                        personnageList.get(choix).setIspick(true);
                        personnageList.remove(choix);
                        System.out.println("le joueur " + this.plateau.getJoueur(ordreJoueur.get(j)).getNom() + "a choisi");
                        //Acconce le personnage choisi
                        continu = false;

                    }// si c'est un humain
                    else {
                        System.out.println("Quel personnage voulez-vous choisir ?");
                        //renvoie la liste des personnages encore disponible
                        listePersoDispo(personnageList);

                        int choix = Interaction.lireUnEntier(1, personnageList.size(), "Votre choix entre: ");
                        this.plateau.getJoueur(ordreJoueur.get(j)).setPersonnage(personnageList.get(choix - 1));
                        personnageList.get(choix - 1).setJoueur(this.plateau.getJoueur(ordreJoueur.get(j)));
                        personnageList.get(choix - 1).setIspick(true);
                        personnageList.remove(choix - 1);
                        System.out.println("le joueur " + this.plateau.getJoueur(ordreJoueur.get(j)).getNom() + "a choisi");
                        continu = false;
                    }
                }
            } while (continu);
        }
        personnageList.get(0).getIsHidden();
    }

    private void setHiddenPersonnage(List<Personnage> perso) {
        int peros1 = Interaction.returnInt(7);
        int peros2 = Interaction.returnInt(6);
        int peros3 = Interaction.returnInt(5);
//        int peros4 = Interaction.returnInt(4);
        System.out.println("Le personnage " + this.plateau.getPersonnage(peros1) + " est écarte face visible");
//        System.out.println("Le personnage " + this.plateau.getPersonnage(peros2) + " est écarte face visible");
        perso.get(peros1).setIsHidden(true);
        perso.get(peros2).setIsHidden(true);
        perso.get(peros3).setIsHidden(true);
//        perso.get(peros4).setIsHidden(true);
        perso.remove(peros1);
        perso.remove(peros2);
        perso.remove(peros3);
//        perso.remove(peros4);
        System.out.println("Deux personnages sont écarte face cacher");
    }

    private void listePersoDispo(List<Personnage> listPerso) {
        // Renvoie la liste des personnages encore disponibles
        for (int i = 0; i < listPerso.size(); i++) {
            Personnage personnage = listPerso.get(i);
            if (personnage != null) {
                String nom = personnage.getNom();
                int rang = personnage.getRang();
                System.out.println((i + 1) + " " + nom + " de rang " + rang);
            }
        }
    }

private void calculDesPoints() {
    Map<Joueur, Integer> mapDesJoeurs = new HashMap<>();

    for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
        Joueur joueur = this.plateau.getJoueur(i);
        int totalPoints = joueur.calculerPoints();
        mapDesJoeurs.put(joueur, totalPoints);
    }

    // Tri les joueurs par score croissant
    List<Map.Entry<Joueur, Integer>> list = new ArrayList<>(mapDesJoeurs.entrySet());
    list.sort(Map.Entry.comparingByValue());

    // Affichage des scores par joueur croissant
    System.out.println("Scores par joueur:");
    for (Map.Entry<Joueur, Integer> entry : list) {
        Joueur joueur = entry.getKey();
        int points = entry.getValue();
        System.out.println(joueur.getNom() + ": " + points + " points");
    }

    // Désignation du gagnant (le dernier de la liste triée)
    if (!list.isEmpty()) {
        Map.Entry<Joueur, Integer> winnerEntry = list.get(list.size() - 1);
        Joueur winner = winnerEntry.getKey();
        int winnerPoints = winnerEntry.getValue();

        System.out.println("\nLe gagnant est : " + winner.getNom() + " avec " + winnerPoints + " points!");
    }
}
}
