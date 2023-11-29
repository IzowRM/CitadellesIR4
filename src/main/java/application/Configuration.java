package application;

import controleur.Interaction;
import modele.*;

import java.util.HashMap;

public class Configuration {

    // Methods qui cr�e les 54 quartiers pour la pioche
    public static Pioche nouvellePioche() {
        Pioche p = new Pioche();

        quartiersReligieux(p);
        quartiersMilitaire(p);
        quartiersCommercants(p);
        quartierNobles(p);
        return p;
    }

    private static void quartiersReligieux(Pioche p) {
        Quartier a;
        String[] nom = {"temple", "temple", "temple", "�glise", "�glise", "�glise", "monast�re", "monast�re", "monast�re", "cath�drale", "cath�drale"};
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
        String[] nom = {"taverne", "taverne", "taverne", "taverne", "taverne", "�choppe", "�choppe", "�choppe", "march�", "march�", "march�", "march�", "comptoir", "comptoir", "comptoir", "port", "port", "port", "h�tel de ville", "h�tel de ville"};
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

    //Impl�mentation des Merveilles
    private static void quartiersMerveilles(Pioche p) {
        Quartier a;
        String[] nom = {"Laboratoire", "Biblioth�que", "Carri�re", "Forge", "Manufacture", "Salle des Cartes", "Statue Equestre", "Tr�sor Imp�rial", "Tripot", "Fontaine aux Souhaits", "Ecole de Magie", "Dracoport", "Donjon", "Cour des Miracles"};
        Integer[] cout = {5, 6, 5, 5, 5, 5, 3, 5, 6, 5, 6, 6, 3, 2};
        for (int i = 0; i < 14; i++) {
            a = new Quartier(nom[i], "MERVEILLE", cout[i]);
            p.ajouter(a);
        }
    }

    // va cr�er la m�thode configurationDeBase
    //Methode qui ajoute les joueurs au plateau
    private static void ajouterJoueurs(PlateauDeJeu p) {

        int a = Interaction.lireUnEntier(2, 4, "Combien de joueurs ?");
        for (int i = 0; i < a; i++) {
            System.out.println("Nom du joueur " + i);
            String name = Interaction.lireUneChaine();
            Joueur j = new Joueur(name);
            p.ajouterJoueur(j);
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

    public static PlateauDeJeu configurationDeBase(Pioche pc) {

        quartiersMerveilles(pc);
        PlateauDeJeu p = new PlateauDeJeu();
        ajouterPersonnages(p);
        ajouterJoueurs(p);
        quartiersMerveilles(pc);
        return p;
    }


}
