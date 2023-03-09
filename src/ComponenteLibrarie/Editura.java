package ComponenteLibrarie;

import java.io.Serializable;

public class Editura implements Serializable {
    String nume;

    public Editura(String numeEditura){
        this.nume = numeEditura;
    }

    public String getNume() {
        return this.nume;
    }

    @Override
    public String toString() {
        //returns string representations of the object
        return "\nNume: " + nume;
    }
}
