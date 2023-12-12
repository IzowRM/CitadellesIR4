
package modele;
import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
    private ArrayList<Quartier> listPioche;
    public Pioche() {

        this.listPioche = new ArrayList<Quartier>();
    }

    public Quartier piocher() {
        Quartier q ;

        if (!listPioche.isEmpty()) {
            q= listPioche.get(0);
            listPioche.remove(0);
            return q;
        }
        return null;
    }
    public void ajouter(Quartier q) {
        listPioche.add(q);
//        System.out.println("Ajout dans la pioche: " + q);
    }
    public int nombreElements() {
        return listPioche.size();
    }
    public void melanger(){

        if (!listPioche.isEmpty()){
            Collections.shuffle(listPioche);
        }
    }
}
