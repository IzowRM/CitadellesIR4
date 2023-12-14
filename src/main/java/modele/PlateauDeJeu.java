package modele;

import java.util.Arrays;

public class PlateauDeJeu {
    private Personnage[] listePersonnages;
    private Joueur[] listeJoueurs;
    private Pioche pioche;
    private Integer nombrePersonnages;
    private Integer nombreJoueurs;

    public PlateauDeJeu() {
        this.listePersonnages = new Personnage[8];
        this.listeJoueurs = new Joueur[4];
        this.pioche = new Pioche();
        this.nombrePersonnages = 0;
        this.nombreJoueurs = 0;
    }

    public Integer getNombrePersonnages() {
        return nombrePersonnages;
    }

    public Integer getNombreJoueurs() {
        return nombreJoueurs;
    }

    public Pioche getPioche() {
        return pioche;
    }
    public void setPioche(Pioche pioche) {
        this.pioche = pioche;
    }

    public Personnage getPersonnage(Integer i) {
        if (i < 0 || i >= listePersonnages.length) {
            return null;
        }
        return listePersonnages[i];
    }

    public Personnage[] getListePersonnages() {
        return listePersonnages;
    }

    public Joueur[] getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(Joueur[] listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    public Joueur getJoueur(Integer i) {
        if (i < 0 || i >= listeJoueurs.length) {
            return null;
        }
        return listeJoueurs[i];
    }

    public void ajouterPersonnage(Personnage personnage) {
        if (personnage != null && nombrePersonnages < 9) {
            listePersonnages[nombrePersonnages] = personnage;
            personnage.setPlateau(this);
            nombrePersonnages++;
        }

    }

    public void ajouterJoueur(Joueur joueur) {
        if (joueur != null && nombreJoueurs < 9) {
            listeJoueurs[nombreJoueurs] = joueur;
            nombreJoueurs++;
        }
    }

    @Override
    public String toString() {
        return "PlateauDeJeu{" +
                "listePersonnages=" + Arrays.toString(listePersonnages) +
                ", listeJoueurs=" + Arrays.toString(listeJoueurs) +
                ", pioche=" + pioche +
                ", nombrePersonnages=" + nombrePersonnages +
                ", nombreJoueurs=" + nombreJoueurs +
                '}';
    }
}
