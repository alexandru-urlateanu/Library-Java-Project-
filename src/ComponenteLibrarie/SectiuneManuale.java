package ComponenteLibrarie;

import java.io.Serializable;
import java.util.HashSet;

public class SectiuneManuale implements Serializable {
    protected HashSet<Carte> manuale;
    private TipuriManuale tipManual;

    public SectiuneManuale(TipuriManuale nume) {
        this.tipManual = nume;
    }

    public TipuriManuale getTipManual() {
        return tipManual;
    }

    public String afiseazaManuale(Biblioteca biblioteca) {
        String result = "\n";
        for(ManualScolar m : biblioteca.listaManuale){
           if(m.getTipManual().equals(this.tipManual)){
               result = result + m;
           }
        }
        return result;
    }
}
