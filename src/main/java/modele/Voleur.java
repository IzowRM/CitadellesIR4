package modele;

import java.util.Random;

import controleur.Interaction;

public class Voleur extends Personnage {
    public Voleur() {
        super("Voleur", 2, Caracteristiques.VOLEUR);

    }

    @Override
    public void utiliserPouvoir() {
        boolean continu = true;

        // pose la question � l'utilisateur
        System.out.println("Quel personnage voulez-vous voler ?");
        // r�cup�re le choix de l'utilisateur
        super.listePersonnagesDisponibles();

        do {
            int choix = Interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages(), "Votre choix entre: ");
            if (this.getPlateau().getPersonnage(choix - 1).equals(this)) {
                System.out.println("Vous ne pouvez pas vous voler vous m�me");

            } else {
                if (this.getPlateau().getPersonnage(choix - 1).getJoueur() != null) {
                    this.getPlateau().getPersonnage(choix - 1).setVole();
                }
                continu = false;
            }
        } while (continu);
    }

    public void utiliserPouvoirAvatar() {
        boolean continu = true;
        // pose la question � l'utilisateur
        System.out.println("Le voleur va voler");
        // r�cup�re le choix de l'utilisateur
        super.listePersonnagesDisponibles();

        int choix = new Random().nextInt(this.getPlateau().getNombrePersonnages()) + 1;
        do {
            if (this.getPlateau().getPersonnage(choix - 1).equals(this)) {
                choix++;
                if (choix > this.getPlateau().getNombrePersonnages()) {
                    choix = 1;
                }
            } else {
                if (this.getPlateau().getPersonnage(choix - 1).getJoueur() != null) {
                    this.getPlateau().getPersonnage(choix - 1).setVole();
                    continu = false;
                } else {
                    continu = false;
                }
            }
        } while (continu);
    }
}
