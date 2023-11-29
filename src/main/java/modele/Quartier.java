package modele;

public class Quartier {
    private String nom;
    private String type;
    private int coutConstruction;
    private String caracteristiques;
    public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};

    public Quartier(String type) {
        setType(type);
        this.coutConstruction = 0;
        this.caracteristiques = "";
        this.nom = "";
    }
    public Quartier() {
        this.nom = "";
        this.type = "";
        this.coutConstruction = 0;
        this.caracteristiques = "";
    }

    public Quartier(String nom, String type, int coutConstruction) {
        this.nom = nom;
        this.type = type;
        this.coutConstruction = coutConstruction;
        this.caracteristiques = "";
    }

    public Quartier(String nom, String type, int coutConstruction, String caracteristiques) {
        this.nom = nom;
        this.type = type;
        this.coutConstruction = coutConstruction;
        this.caracteristiques = caracteristiques;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public String getType() {
        return this.type;
    }

    private boolean isTypeQuartier(String type) {
        for (String typeQuartier : TYPE_QUARTIERS) {
            if (typeQuartier.equals(type)) {
                return true;
            }
        }
        return false;
    }

    public void setType(String type) {
        if (isTypeQuartier(type)) {
            this.type = type;
        } else {
            this.type = "";
        }
    }

    public int getCout() {
        return this.coutConstruction;
    }

    public void setCout(int coutConstruction) {
        if (coutConstruction < 0 || coutConstruction > 6) {
            coutConstruction = 0;
        }
        this.coutConstruction = coutConstruction;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    @Override
    public String toString() {
        return "Quartier{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", coutConstruction=" + coutConstruction +
                ", caracteristiques='" + caracteristiques + '\'' +
                '}';
    }
}
