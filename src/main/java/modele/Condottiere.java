package modele;

import controleur.Interaction;

public class Condottiere extends Personnage {
    public Condottiere() {
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
    }

    @Override
    public void utiliserPouvoir() {
        //Variables

        boolean choixDejoueurCorrect = true;
        boolean choixDeQuartierCorrect = true;

        //Demande pour d�truire un quartier

        System.out.println("Voulez vous d�truire un quartier ?");
        boolean yesOrNo = Interaction.lireOuiOuNon();
        if (!yesOrNo) {
            System.out.println("Vous n'avez pas d�truit de quartier");
        } else {
            System.out.println("Voici la liste des joueurs avec leurs cit�.: ");

            int choixJoueur = 0;
            // g�re corrctement la selection du joueur � cibl�.
            do {
                listePersonnagesDisponibles();
                System.out.println("Il vous reste " + this.getJoueur().nbPieces() + " pi�ces dans votre tr�sor.");
                choixJoueur = Interaction.lireUnEntier(0, this.getPlateau().getNombreJoueurs(), "Quel joueur voulez vous attaquer? ( pour ne rien faire)");


                if (this.getPlateau().getJoueur(choixJoueur - 1).nbQuartiersDansCite() == 0) {
                    System.out.println("Le joueur que vous avez choisi n'as pas de quartier � detruire.");
                } else {
                    choixDejoueurCorrect = false;
                }
            } while (choixDejoueurCorrect);


            do {

                int choixQuartier = Interaction.lireUnEntier(1, this.getPlateau().getJoueur(choixJoueur - 1).nbQuartiersDansCite(), "Quel quartier voulez vous d�truire?");

                if (this.getPlateau().getJoueur(choixJoueur - 1).getCite()[choixQuartier - 1].getCout() > this.getJoueur().nbPieces()) {

                    System.out.println("Vous n'avez pas asser de pieces pour d�truire ce quartier choisissez en un autre. Il vous reste: " +
                            this.getJoueur().nbPieces() + " piece d'or dans votre tresor.");
                } else {

                    this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choixJoueur - 1).getCite()[choixQuartier - 1].getCout());
                    this.getPlateau().getJoueur(choixJoueur - 1).getCite()[choixQuartier - 1] = null;
                    choixDeQuartierCorrect = false;
                }
            } while (choixDeQuartierCorrect);
        }
    }

    @Override
    public void percevoirRessourcesSpecifiques() {

        int n = 0;
        for (int i = 0; i < this.getJoueur().nbQuartiersDansCite(); i++) {
            if (this.getJoueur().getCite()[i].getType().equals("MILITAIRE")) {
                n++;
            }
        }

        this.getJoueur().ajouterPieces(n);
    }

    @Override
    public void listePersonnagesDisponibles() {

        for (int i = 0; i < this.getPlateau().getNombreJoueurs(); i++) {
            Joueur j = this.getPlateau().getJoueur(i);
            System.out.print(i + 1 + " " + j.getNom() + ": ");
            if (j.nbQuartiersDansCite() == 0) {
                System.out.print("Cite vide.");
            }

            for (int k = 0; k < j.nbQuartiersDansCite(); k++) {

                System.out.print(k + 1 + " " + j.getCite()[k].getNom() + " (co�t " + j.getCite()[k].getCout() + "),");

            }
            System.out.println();

        }

    }

    public void utiliserPouvoirAvatar() {
        System.out.println("NOT READY");
    }
}
