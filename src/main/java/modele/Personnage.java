package modele;

import java.util.ArrayList;

public abstract class Personnage {
    private String nom;
    private Integer rang;
    private boolean isHidden;
    private boolean ispick;
    private String caracteristique;
    private boolean assassine;
    private boolean vole;
    private Joueur joueur;
    private PlateauDeJeu plateauDeJeu;

    public Personnage(String nom, Integer rang, String caracteristique) {
        this.nom = nom;
        this.rang = rang;
        this.caracteristique = caracteristique;
        joueur= null;
        vole= false;
        assassine= false;
        isHidden = false;
        ispick = false;
    }
    public boolean getIspick(){
        return ispick;
    }
    public void setIspick(boolean ispick){
        this.ispick = ispick;
    }
    public boolean getIsHidden(){
        return isHidden;
    }
    public void setIsHidden(boolean isHidden){
        this.isHidden = isHidden;
    }

    public Personnage() {

    }
    public String getNom() {
        return nom;
    }

    public Integer getRang() {
        return rang;
    }

    public String getCaracteristiques() {
        return caracteristique;
    }

    public boolean getAssassine() {
        return assassine;
    }

    public boolean getVole() {
        return vole;
    }

    public Joueur getJoueur() {
        return joueur;
    }
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        joueur.setPersonnage(this);
    }
    public void setAssassine() {
        this.assassine = true;
    }
    public void reinitialiserAssassine(){
        this.assassine = false;
    }
    public void reinitialiserVole(){
        this.vole = false;
    }
    public void setVole() {
        this.vole = true;
    }
    public void ajouterPieces(){
        if (joueur != null && !assassine){
            joueur.ajouterPieces(2);
        }
    }
    public void ajouterQuartier(Quartier q){
        if (joueur != null && !assassine){
            joueur.ajouterQuartierDansMain(q);
        }
    }
    public void construire(Quartier q){
        if (joueur != null && !assassine){
            joueur.ajouterQuartierDansCite(q);
        }
    }
       public void percevoirRessourcesSpecifiques(){
            if (joueur != null && !assassine){
                System.out.println("aucune ressource specifique");
            }
        }
    public abstract void utiliserPouvoir();
    public abstract void utiliserPouvoirAvatar();

    public void reinitialiser(){
        assassine = false;
        vole = false;
        joueur = null;
        this.joueur.monPersonnage= null;
    }

    public PlateauDeJeu getPlateau() {
        return plateauDeJeu;
    }
    protected void listePersonnagesDisponibles(){
        for(int i = 0; i < this.getPlateau().getNombrePersonnages(); i++) {
            System.out.println(i+1+" "+this.getPlateau().getPersonnage(i).getNom());

        }
    }
    protected void listeLaMainDuJoueur(Joueur joueur) {
        ArrayList<Quartier> mainJoueur = this.getJoueur().getMain();
        for (int i = 0; i < joueur.nbQuartiersDansMain();i++){
            System.out.println( i+1 + " "+mainJoueur.get(i).getNom());
        }
    }
    public void setPlateau(PlateauDeJeu plateauDeJeu) {
        this.plateauDeJeu = plateauDeJeu;
    }
}
