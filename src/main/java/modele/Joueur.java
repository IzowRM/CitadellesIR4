package modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {
    private String nom;
    private int tresor;
    private int nbQuartiers;
    private boolean possedeCouronne;
    private Quartier[] cite;
    private ArrayList<Quartier> main;
    protected Personnage monPersonnage;

    public Joueur(String nom) {
        this.nom = nom;
        this.tresor = 0;
        this.nbQuartiers = 0;
        this.possedeCouronne = false;
        this.cite = new Quartier[8];
        this.main = new ArrayList<Quartier>();
        this.monPersonnage = null;
    }

    public String getNom() {
        return nom;
    }


    public int nbPieces() {
        return tresor;
    }

    public int nbQuartiersDansCite() {
        int count = 0;
        for (Quartier element : cite) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }

    public Quartier[] getCite() {
        return cite;
    }

    public ArrayList<Quartier> getMain() {
        return main;
    }

    public int nbQuartiersDansMain() {
        return main.size();
    }

    public boolean getPossedeCouronne() {
        return possedeCouronne;
    }

    public void setPossedeCouronne(boolean b) {
        this.possedeCouronne = b;
    }

    public void ajouterPieces(int i) {
        if (i > 0) {
            tresor += i;
        }
    }

    public void retirerPieces(int i) {
        if (i > 0 && tresor >= i) {
            tresor -= i;
        }
    }

    public void ajouterQuartierDansCite(Quartier quartier1) {
        if (nbQuartiers < 8) {
            cite[nbQuartiers] = quartier1;
            nbQuartiers++;
        }
    }
    public boolean quartierPresentDansCite(String s) {
        for (int i = 0; i < nbQuartiers; i++) {
            if (cite[i] != null && cite[i].getNom().equals(s)) {
                return true;
            }
        }
        return false;
    }


    public Quartier retirerQuartierDansCite(String q) {
        if (nbQuartiers > 0) {
            for (int i = 0; i < nbQuartiers; i++) {
                if (cite[i].getNom().equals(q)) {
                    nbQuartiers--;
                    Quartier q1 = cite[i];
                    cite[i] = null;
                    return q1;

                }
            }

        }
        return null;
    }

    public void ajouterQuartierDansMain(Quartier quartier1) {
        main.add(quartier1);
    }
    public Quartier retirerUnQuartierDansMain(int n){
        Quartier q;
        q = main.get(n);
        this.main.remove(q);
        return q;
    }
    public Quartier retirerQuartierDansMain() {
        Quartier q =new Quartier();
        Random generateur = new Random();
        int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
        if (main.size() > 0) {
            q=main.get(numeroHasard);
            main.remove(numeroHasard);
            return q;
        }
        return null;
    }
    public Personnage getPersonnage(){
        return monPersonnage;
    }

    public void reinitialiser() {
        tresor = 0;
        main.clear();
        for (int i = 0; i < nbQuartiers; i++) {
            cite[i] = null;
        }
    }


}