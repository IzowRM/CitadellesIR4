package modele;

import controleur.Interaction;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Assassin extends Personnage{

    public Assassin() {
        super("Assassin", 1, Caracteristiques.ASSASSIN);
        // Appelle le constructeur de Personnage avec les valeurs appropriées
    }

    @Override
    public void utiliserPouvoir() {
        boolean continu = true;
        Interaction interaction = new Interaction();
        // pose la question à l'utilisateur
        System.out.println("Quel personnage voulez-vous assassiner ?");
        // récupère le choix de l'utilisateur
        super.listePersonnagesDisponibles();

        do {
          int  choix = interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages(), "Votre choix entre: ");
            if (this.getPlateau().getPersonnage(choix - 1).equals(this)) {
                System.out.println("Vous ne pouvez pas vous assassiner vous même");

            } else {
                this.getPlateau().getPersonnage(choix - 1).setAssassine();
                continu = false;
            }
        } while (continu);
    }

    // Listes les peronnages disponibles

}
