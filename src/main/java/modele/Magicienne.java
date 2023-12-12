package modele;

import controleur.Interaction;

import java.util.ArrayList;
import java.util.Random;

public class Magicienne extends Personnage {
    public void echangeDeListe(ArrayList<Quartier> liste1, ArrayList<Quartier> liste2) {
        ArrayList<Quartier> copieTableau = new ArrayList<>();
        copieTableau.addAll(liste1);
        liste1.clear();
        liste1.addAll(liste2);
        liste2.clear();
        liste2.addAll(copieTableau);
    }

    @Override
    protected void listePersonnagesDisponibles() {

        for (int i = 0; i < this.getPlateau().getNombrePersonnages(); i++) {
            System.out.println(i + 1 + " : Le Personnage " + this.getPlateau().getPersonnage(i).getNom() + " a " + this.getPlateau().getPersonnage(i).getJoueur().nbQuartiersDansMain() + " Cartes");
        }
    }

    ;

    public Magicienne() {
        super("Magicienne", 3, Caracteristiques.MAGICIENNE);
    }

    @Override
    public void utiliserPouvoir() {



        boolean continu = true;
        //demande pour l'�change des cartes.
        System.out.println("Vous avez " + this.getJoueur().nbQuartiersDansMain() + " Carte dans votre main, voulez Voulez-vous echanger vos cartes avec celles d�un autre joueur ?\n" +
                "(o/n)");
        boolean yesOrNo = Interaction.lireOuiOuNon();
        if (yesOrNo) {

            do {
                System.out.println("Avec qui voulez vous �changer vos cartes ?");
                // r�cup�re le choix de l'utilisateur
                listePersonnagesDisponibles();
                int choix = Interaction.lireUnEntier(1, this.getPlateau().getNombrePersonnages(), "Votre choix entre: ");
                //�change les mains des joueurs


                if (this.getPlateau().getPersonnage(choix - 1).equals(this)) {
                    System.out.println("Vous ne pouvez pas vous choir vous m�me. Choisissez un autre joueur.");

                } else {
                    echangeDeListe(this.getPlateau().getPersonnage(choix - 1).getJoueur().getMain(), this.getJoueur().getMain());
                    continu = false;
                }
            } while (continu);
        } else {

            if (this.getJoueur().nbQuartiersDansMain() == 0) {
                System.out.println("Vous n'avez pas de carte dans votre main");
            } else {
                // demande pour le nombre de cartes � �changer
                System.out.println("Combien de cartes voulez vous �changer ? Il reste "+this.getPlateau().getPioche().nombreElements()
                        +"Carte dans la pioche, Et  vous avez Vous avez " + this.getJoueur().nbQuartiersDansMain()+" Carte");

                int nb = Interaction.lireUnEntier(0, this.getJoueur().nbQuartiersDansMain(), "Votre choix entre: ");
                // Action � prendre en fonction du nombre de cartes � �changer
                if (nb == 0) {
                    System.out.println("Vous n'avez pas �chang� de cartes");
                }
                // Si le nombre de cartes � �changer est �gal au nombre de cartes dans la main du joueur, on �change toutes les cartes
                else if (nb == this.getJoueur().nbQuartiersDansMain()) {
                    System.out.println("Vous avez �chang� toutes vos cartes");
                    ArrayList<Quartier> copieMain = new ArrayList<>();
                    copieMain.addAll(this.getJoueur().getMain());
                    this.getJoueur().getMain().clear();
                    for (int i = 0; i < nb; i++) {
                        this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
                        this.getPlateau().getPioche().ajouter(copieMain.get(i));
                    }


                } else {
                    ArrayList<Quartier> copieMain = new ArrayList<>();
                    //on ajoute les cartes � �changer dans une liste temporaire
                    for (int i = 0; i < nb; i++) {
                        System.out.println("Vous avez" + this.getJoueur().nbQuartiersDansMain() +" carte dans votre main. Quelle carte voulez vous �changer ?");
                        listeLaMainDuJoueur(this.getJoueur());
                        int choix = Interaction.lireUnEntier(1, this.getJoueur().nbQuartiersDansMain(), "Votre choix: ");
                        copieMain.add( this.getJoueur().getMain().get(choix-1));
                        this.getJoueur().getMain().remove(choix-1);
                    }
                    //on ajoute les cartes de la pioche dans la main du joueur et  ajoute les carte de la copie dans la pioche
                    for (int i = 0; i < nb; i++) {
                        this.getJoueur().getMain().add(this.getPlateau().getPioche().piocher());
                        this.getPlateau().getPioche().ajouter(copieMain.get(i));

                    }
                }
            }
        }
    }

    public void utiliserPouvoirAvatar() {

        boolean continu = true;
        //demande pour l'�change des cartes.
        System.out.println("Vous avez " + this.getJoueur().nbQuartiersDansMain() + " Carte dans votre main, voulez Voulez-vous echanger vos cartes avec celles d�un autre joueur ?\n" +
                "(o/n)");
        boolean yesOrNo = new Random().nextBoolean();
        System.out.println(yesOrNo);
        if (yesOrNo) {

            int choix = new Random().nextInt(this.getPlateau().getNombrePersonnages()) + 1;
            do {
                System.out.println("Avec qui voulez vous �changer vos cartes ?");
                // r�cup�re le choix de l'utilisateur
                listePersonnagesDisponibles();
                System.out.println("Votre choix entre: ");
                System.out.println(choix);
                //�change les mains des joueurs


                if (this.getPlateau().getPersonnage(choix - 1).equals(this)) {
                    System.out.println("Vous ne pouvez pas vous choir vous m�me. Choisissez un autre joueur.");
                    choix++;
                    if(choix>this.getPlateau().getNombrePersonnages()+1){
                        choix = 1;
                    }
                } else {
                    echangeDeListe(this.getPlateau().getPersonnage(choix - 1).getJoueur().getMain(), this.getJoueur().getMain());
                    continu = false;
                }
            } while (continu);
        } else {

            if (this.getJoueur().nbQuartiersDansMain() == 0) {
                System.out.println("Vous n'avez pas de carte dans votre main");
            } else {
                // demande pour le nombre de cartes � �changer
                System.out.println("Combien de cartes voulez vous �changer ? Il reste "+this.getPlateau().getPioche().nombreElements()
                        +"Carte dans la pioche, Et  vous avez Vous avez " + this.getJoueur().nbQuartiersDansMain()+" Carte");
                
                System.out.println("Votre choix entre: ");
                int nb = new Random().nextInt(this.getJoueur().nbQuartiersDansMain()+1);
                System.out.println(nb);
                // Action � prendre en fonction du nombre de cartes � �changer
                if (nb == 0) {
                    System.out.println("Vous n'avez pas �chang� de cartes");
                }
                // Si le nombre de cartes � �changer est �gal au nombre de cartes dans la main du joueur, on �change toutes les cartes
                else if (nb == this.getJoueur().nbQuartiersDansMain()) {
                    System.out.println("Vous avez �chang� toutes vos cartes");
                    ArrayList<Quartier> copieMain = new ArrayList<>();
                    copieMain.addAll(this.getJoueur().getMain());
                    this.getJoueur().getMain().clear();
                    for (int i = 0; i < nb; i++) {
                        this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
                        this.getPlateau().getPioche().ajouter(copieMain.get(i));
                    }


                } else {
                    ArrayList<Quartier> copieMain = new ArrayList<>();
                    //on ajoute les cartes � �changer dans une liste temporaire
                    for (int i = 0; i < nb; i++) {
                        System.out.println("Vous avez" + this.getJoueur().nbQuartiersDansMain() +" carte dans votre main. Quelle carte voulez vous �changer ?");
                        listeLaMainDuJoueur(this.getJoueur());
                        System.out.println("Votre choix: ");
                        int choix = new Random().nextInt(this.getJoueur().nbQuartiersDansMain())+1;
                        copieMain.add( this.getJoueur().getMain().get(choix-1));
                        this.getJoueur().getMain().remove(choix-1);
                    }
                    //on ajoute les cartes de la pioche dans la main du joueur et  ajoute les carte de la copie dans la pioche
                    for (int i = 0; i < nb; i++) {
                        this.getJoueur().getMain().add(this.getPlateau().getPioche().piocher());
                        this.getPlateau().getPioche().ajouter(copieMain.get(i));

                    }
                }
            }
        }
    }
}



