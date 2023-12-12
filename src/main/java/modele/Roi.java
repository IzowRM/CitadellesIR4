package modele;

public class Roi extends Personnage {

    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
    }


    @Override
    public void percevoirRessourcesSpecifiques() {

        if (getJoueur() != null) {
            int p = 0;
            for (Quartier quartier : getJoueur().getCite()) {

                if (quartier != null && quartier.getType().equals("NOBLE")) {
                    p++;
                }


            }
            getJoueur().ajouterPieces(p);
        }
    }

    @Override
    public void utiliserPouvoir() {
        if (getJoueur()!= null) {
            getJoueur().setPossedeCouronne(true);
            System.out.println("Je prend la couronne");
        }
    }

    public void utiliserPouvoirAvatar() {
        utiliserPouvoir();
    }
}