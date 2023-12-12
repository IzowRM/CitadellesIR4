package modele;

import java.util.Random;

import controleur.Interaction;


public class Assassin extends Personnage{

    public Assassin() {
        super("Assassin", 1, Caracteristiques.ASSASSIN);
        // Appelle le constructeur de Personnage avec les valeurs appropri�es
    }

    @Override
    public void utiliserPouvoir() {
        boolean continu = true;
        // pose la question � l'utilisateur
        System.out.println("Quel personnage voulez-vous assassiner ?");
        // r�cup�re le choix de l'utilisateur
        super.listePersonnagesDisponibles();

        do {
          int  choix = Interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages(), "Votre choix entre: ");
            if (this.getPlateau().getPersonnage(choix - 1).equals(this)) {
                System.out.println("Vous ne pouvez pas vous assassiner vous m�me");
            } else {
                this.getPlateau().getPersonnage(choix - 1).setAssassine();
                continu = false;
            }

        } while (continu);
    }

    public void utiliserPouvoirAvatar() {
        boolean continu = true;
        System.out.println("Quel personnage voulez-vous assassiner ?");
        super.listePersonnagesDisponibles();

        int choix = new Random().nextInt(this.getPlateau().getNombrePersonnages()) + 1;
        do {
            System.out.println("Votre choix entre: "+this.getPlateau().getNombrePersonnages());
            System.out.println(choix);
            if (this.getPlateau().getPersonnage(choix - 1).equals(this)) {
                System.out.println("Vous ne pouvez pas vous assassiner vous m�me");
                choix++;
                if(choix>this.getPlateau().getNombrePersonnages()){
                    choix=1;
                }
            } else {
                this.getPlateau().getPersonnage(choix - 1).setAssassine();
                continu = false;
            }

        } while (continu);
    }
}
