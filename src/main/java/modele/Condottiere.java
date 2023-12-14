package modele;

import java.util.Random;

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
            System.out.println("Voici la liste des joueurs avec leurs cite.: ");

            int choixJoueur = 0;
            // g�re corrctement la selection du joueur � cibl�.
            do {

                listePersonnagesDisponibles();
                System.out.println("Il vous reste " + this.getJoueur().nbPieces() + " pi�ces dans votre tr�sor.");
                choixJoueur = Interaction.lireUnEntier(0, this.getPlateau().getNombreJoueurs(), "Quel joueur voulez vous attaquer? ( pour ne rien faire)");
                if (choixJoueur == 0) {
                    return;
                }
                if (this.getPlateau().getJoueur(choixJoueur - 1).getPersonnage().getRang() != 5 || this.getPlateau().getJoueur(choixJoueur - 1).getPersonnage().getAssassine()) {
                    if (this.getPlateau().getJoueur(choixJoueur - 1).nbQuartiersDansCite() == 0) {
                        System.out.println("Le joueur que vous avez choisi n'as pas de quartier � detruire.");
                    } else {
                        choixDejoueurCorrect = false;
                    }
                } else {
                    System.out.println("Le joueur que vous avez choisi est un évéque, vous ne pouvez pas détruire.");
                }
            } while (choixDejoueurCorrect);


            do {

                int choixQuartier = Interaction.lireUnEntier(1, this.getPlateau().getJoueur(choixJoueur - 1).nbQuartiersDansCite(), "Quel quartier voulez vous d�truire?");

                if (choixQuartier == 0) {
                    return;
                }
                if (this.getPlateau().getJoueur(choixJoueur - 1).getCite()[choixQuartier - 1].getCout() > this.getJoueur().nbPieces()) {

                    System.out.println("Vous n'avez pas asser de pieces pour d�truire ce quartier choisissez en un autre. Il vous reste: " +
                            this.getJoueur().nbPieces() + " piece d'or dans votre tresor.");
                } else {

                    this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choixJoueur - 1).getCite()[choixQuartier - 1].getCout());
                    this.getPlateau().getJoueur(choixJoueur-1).retirerQuartierDansCite(this.getPlateau().getJoueur(choixJoueur-1).getCite()[choixQuartier-1].getNom());
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
            System.out.print(i + 1 + " " + this.getPlateau().getJoueur(i).getNom() + ": ");
            if (this.getPlateau().getJoueur(i).nbQuartiersDansCite() == 0) {
                System.out.print("Cite vide.");
            }
            if(this.getPlateau().getJoueur(i).nbQuartiersDansCite()!=0) {

                System.out.print("la cité contient :" +this.getPlateau().getJoueur(i).nbQuartiersDansCite()+" quartiers: ");
                for (int k = 0; k < this.getPlateau().getJoueur(i).nbQuartiersDansCite(); k++) {
                    System.out.println(k+"est le numero de quartier");
                    System.out.print(k + 1 + " " + this.getPlateau().getJoueur(i).getCite()[k].getNom() + " (co�t " + this.getPlateau().getJoueur(i).getCite()[k].getCout() + "),");

                }
            }else {
                System.out.print("la Cite de "+this.getPlateau().getJoueur(i).getNom()+" est vide.");
            }
            System.out.println();

        }

    }

    public void utiliserPouvoirAvatar() {

        boolean choixDejoueurCorrect = true;
        boolean choixDeQuartierCorrect = true;

        System.out.println("Voulez vous d�truire un quartier ?");
        boolean yesOrNo = new Random().nextBoolean();
        System.out.println(yesOrNo);
        if (!yesOrNo) {
            System.out.println("Vous n'avez pas d�truit de quartier");
        } else {
            System.out.println("Voici la liste des joueurs avec leurs cit�.: ");

            int choixJoueur = 0;
            do {

                choixJoueur = new Random().nextInt(this.getPlateau().getNombreJoueurs()) ;

                System.out.println("Il vous reste " + this.getJoueur().nbPieces() + " pi�ces dans votre tr�sor.");
                System.out.println("Quel joueur voulez vous attaquer? ( pour ne rien faire)");
                System.out.println(choixJoueur);
                if (choixJoueur == 0) {
                    return;
                }
                if (this.getPlateau().getJoueur(choixJoueur).getPersonnage().getRang() != 5 || this.getPlateau().getJoueur(choixJoueur).getPersonnage().getAssassine()) {
                    if (this.getPlateau().getJoueur(choixJoueur).nbQuartiersDansCite() == 0) {
                        System.out.println("Le joueur que vous avez choisi n'as pas de quartier � detruire.");
                        choixJoueur++;
                        if (choixJoueur > this.getPlateau().getNombreJoueurs()) {
                            choixJoueur = 0;
                        }
                    } else {
                        choixDejoueurCorrect = false;
                    }
                } else {
                    System.out.println("Le joueur que vous avez choisi est un évéque, vous ne pouvez pas détruire.");
                    choixJoueur++;
                    if (choixJoueur > this.getPlateau().getNombreJoueurs()) {
                        choixJoueur = 0;
                    }
                }

            } while (choixDejoueurCorrect);



            do {
                int choixQuartier = new Random().nextInt(this.getPlateau().getJoueur(choixJoueur).nbQuartiersDansCite());
                System.out.println("Quel quartier voulez vous d�truire?");
                System.out.println(choixQuartier);

                if (choixQuartier == 0) {
                    return;
                }
                System.out.println("Mon choix de quartier est: "+choixQuartier);
                System.out.println("Mon chois de joueur est: "+choixJoueur);

                System.out.println("Les joueur ciblé à  "+this.getPlateau().getJoueur(choixJoueur).nbQuartiersDansCite());
                System.out.println("Le quartier est: "+this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getNom());
                System.out.println("Le cout du quartier est: "+this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getCout());
                if (this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getCout() > this.getJoueur().nbPieces()) {

                    System.out.println("Vous n'avez pas asser de pieces pour d�truire ce quartier choisissez en un autre. Il vous reste: " +
                            this.getJoueur().nbPieces() + " piece d'or dans votre tresor.");

                    choixQuartier++;
                    if (choixQuartier > this.getPlateau().getJoueur(choixJoueur ).nbQuartiersDansCite()) {
                        choixQuartier = 1;
                    }
                } else {

                    this.getJoueur().retirerPieces(this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getCout());
                    this.getPlateau().getJoueur(choixJoueur).retirerQuartierDansCite(this.getPlateau().getJoueur(choixJoueur).getCite()[choixQuartier].getNom());
                    reorganiserTableau(this.getPlateau().getJoueur(choixJoueur).getCite());
                    choixDeQuartierCorrect = false;
                }

            } while (choixDeQuartierCorrect);
        }
    }

    private void reorganiserTableau(Quartier[] tableau) {
        int indexDestination = 0;

        for (int i = 0; i < tableau.length; i++) {
            if (tableau[i] != null) {
                tableau[indexDestination++] = tableau[i];
            }
        }
        // Remplir les éléments restants avec null
        for (int i = indexDestination; i < tableau.length; i++) {
            tableau[i] = null;
        }
    }
}
