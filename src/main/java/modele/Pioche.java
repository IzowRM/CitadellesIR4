
package modele;
import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
    private ArrayList<Quartier> list ;
    public Pioche() {
        this.list = new ArrayList<Quartier>();
    }
    public Quartier piocher() {
        Quartier q ;
        if (!list.isEmpty()) {
            q=list.get(0);
            list.remove(0);
            return q;
        }
        return null;
    }
    public void ajouter(Quartier q) {
        list.add(q);
        System.out.println("Ajout dans la piche: " + q);
    }
    public int nombreElements() {
        return list.size();
    }
    public void melanger(){

        if (!list.isEmpty()){
            Collections.shuffle(list);
        }
    }
}
