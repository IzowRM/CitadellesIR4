package application;

import controleur.Interaction;
import modele.*;


public class Configuration {

    // Methods qui crée les 54 quartiers pour la pioche
    public static Pioche nouvellePioche() {
        Pioche p = new Pioche();
        quartiersReligieux(p);
        quartiersMilitaire(p);
        quartiersCommercants(p);
        quartierNobles(p);
        quartiersMerveilles(p);
        return p;
    }

    public static PlateauDeJeu configurationDeBase(Pioche pc) {

        PlateauDeJeu p = new PlateauDeJeu();
        pc.melanger();
        p.setPioche(pc);

        ajouterPersonnages(p);
        ajouterJoueurs(p);
        return p;
    }


    private static void quartiersReligieux(Pioche p) {
        Quartier a;
        String[] nom = {"temple", "temple", "temple", "église", "église", "église", "monastère", "monastère", "monastère", "cathédrale", "cathédrale"};
        Integer[] cout = {1, 1, 1, 2, 2, 2, 3, 3, 3, 5, 5};
        for (int i = 0; i < 11; i++) {
            a = new Quartier(nom[i], "RELIGIEUX", cout[i]);
            p.ajouter(a);
        }

    }

    private static void quartiersMilitaire(Pioche p) {
        Quartier a;
        String[] nom = {"tour de guet", "tour de guet", "tour de guet", "prison", "prison", "prison", "caserne", "caserne", "caserne", "forteresse", "forteresse"};
        Integer[] cout = {1, 1, 1, 2, 2, 2, 3, 3, 3, 5, 5};
        for (int i = 0; i < 11; i++) {
            a = new Quartier(nom[i], "MILITAIRE", cout[i]);
            p.ajouter(a);
        }

    }

    private static void quartiersCommercants(Pioche p) {
        Quartier a;
        String[] nom = {"taverne", "taverne", "taverne", "taverne", "taverne", "échoppe", "échoppe", "échoppe", "marché", "marché", "marché", "marché", "comptoir", "comptoir", "comptoir", "port", "port", "port", "hôtel de ville", "hôtel de ville"};
        Integer[] cout = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5};
        for (int i = 0; i < 20; i++) {
            a = new Quartier(nom[i], "COMMERCANT", cout[i]);
            p.ajouter(a);
        }
    }

    private static void quartierNobles(Pioche p) {
        Quartier a;
        String[] nom = {"manoir", "manoir", "manoir", "manoir", "manoir", "chateau", "chateau", "chateau", "chateau", "palais", "palais", "palais"};
        Integer[] cout = {3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5};
        for (int i = 0; i < 12; i++) {
            a = new Quartier(nom[i], "COMMERCANT", cout[i]);
            p.ajouter(a);
        }
    }

    //Implémentation des Merveilles
    private static void quartiersMerveilles(Pioche p) {
        Quartier a;
        String[] nom = {"Laboratoire", "Bibliothèque", "Carrière", "Forge", "Manufacture", "Salle des Cartes", "Statue Equestre", "Trésor Impérial", "Tripot", "Fontaine aux Souhaits", "Ecole de Magie", "Dracoport", "Donjon", "Cour des Miracles"};
        Integer[] cout = {5, 6, 5, 5, 5, 5, 3, 5, 6, 5, 6, 6, 3, 2};
        for (int i = 0; i < 14; i++) {
            a = new Quartier(nom[i], "MERVEILLE", cout[i]);
            p.ajouter(a);
        }
    }

    // va créer la méthode configurationDeBase
    //Methode qui ajoute les joueurs au plateau

    public static void ajouterJoueurs(PlateauDeJeu p) {
//        System.out.println("Quel est le Nom du joueur ?");
//        System.out.println(" ");
//
//       String name = Interaction.lireUneChaine();

        String name = "Robin";
        Joueur j = new Joueur(name);
        p.ajouterJoueur(j);
        for (int i = 0; i < 3; i++) {
            String nameOrdinateur = "Ordinateur " + (i + 1);
            Joueur jO = new Joueur(nameOrdinateur);
            jO.setIsOrdinateur();
            p.ajouterJoueur(jO);
        }
    }

    // Method qui ajoute les personnages au plateau
    private static void ajouterPersonnages(PlateauDeJeu plateau) {
        Personnage[] personnages = {
                new Assassin(),
                new Voleur(),
                new Magicienne(),
                new Roi(),
                new Eveque(),
                new Marchande(),
                new Architecte(),
                new Condottiere()
        };

        for (Personnage personnage : personnages) {
            plateau.ajouterPersonnage(personnage);
        }
    }

    public static PlateauDeJeu cinfigurationMulti(Pioche pc) {
        PlateauDeJeu p = new PlateauDeJeu();
        ajouterPersonnages(p);
        ajouterJoueurs(p);
        quartiersMerveilles(pc);
        return p;
    }

}
