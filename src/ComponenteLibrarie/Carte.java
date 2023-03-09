package ComponenteLibrarie;

import java.io.Serializable;

public class Carte implements Serializable{
    private int isbn;
    public String titlu, autor, editura;
    protected double pret;
    private Categorii categorie;

    public Carte() {
        isbn = 0;
        titlu = null;
        autor = null;
        pret = 0;
        editura = null;
    }

    public int getIsbn() {return isbn;}
    public void setIsbn(int isbn) {this.isbn = isbn;}

    public String getTitlu() {return titlu;}
    public void setTitlu(String titlu) {this.titlu = titlu;}

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {this.autor = autor;}

    public double getPret() {
        return pret;
    }
    public void setPret(double pret) {this.pret = pret;}

    public String getEditura() {
        return editura;
    }
    public void setEditura(String editura) {this.editura = editura;}

    public Categorii getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorii categorie) {this.categorie = categorie;}

    public Carte(int isbn, String titlu, String autor, double pret, Categorii categorie, String editura) {
        this.isbn = isbn;
        this.titlu = titlu;
        this.autor = autor;
        this.pret = pret;
        this.categorie = categorie;
        this.editura = editura;
    }

    @Override
    public String toString() {
        //returns string representations of the object
        return "\nTitlu: " + titlu + "\nAutor: " + autor +
                "\nCategorie: " + categorie + "\nISBN: " + isbn + "\nEditura: " + editura + "\nPret: " + pret + " RON" + "\n";
    }
}

