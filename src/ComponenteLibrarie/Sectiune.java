package ComponenteLibrarie;

import java.io.Serializable;
import java.util.HashSet;

public class Sectiune implements Serializable{
    protected HashSet<Carte> carti;
    private Categorii categorie;

    public Sectiune(Categorii nume) {
        this.categorie = nume;
    }

    public Categorii getCategorie() {
        return categorie;
    }

    public String afiseazaCarti(Biblioteca biblioteca) {
        String result = "\n";
        for(Carte c : biblioteca.listaCarti){
            if(c.getCategorie().equals(this.categorie)){
                result = result + c;
            }
        }
        return result;
    }
}
