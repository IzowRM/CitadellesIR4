package modele;

import controleur.Interaction;

public class Voleur extends Personnage{
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
            int  choix = Interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages(), "Votre choix entre: ");
            if (this.getPlateau().getPersonnage(choix - 1).equals(this)) {
                System.out.println("Vous ne pouvez pas vous voler vous m�me");

            } else {
                int pieces = this.getPlateau().getPersonnage(choix-1).getJoueur().nbPieces();
                this.getPlateau().getPersonnage(choix - 1).setVole();
                this.getPlateau().getPersonnage(choix-1).getJoueur().retirerPieces(pieces);
                this.getJoueur().ajouterPieces(pieces);
                continu = false;
            }
        } while (continu);
    }

    public void utiliserPouvoirAvatar() {
        System.out.println("NOT READY");
    }

}
