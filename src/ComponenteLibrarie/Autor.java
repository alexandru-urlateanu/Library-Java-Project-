package ComponenteLibrarie;

public class Autor extends Persoana {
    public void citeste(){
        System.out.println("Autorul citeste");
    }

    public void scrie(){
        System.out.println("Autorul scrie");
    }

    public String getNume(){
        return this.nume;
    }

    public Autor(String numeAutor){
        this.nume = numeAutor;
    }

    @Override
    public String toString() {
        //returns string representations of the object
        return "\nNume: " + nume;
    }
}
