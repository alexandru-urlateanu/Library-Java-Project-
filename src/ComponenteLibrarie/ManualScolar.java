package ComponenteLibrarie;

import java.io.Serializable;

public class ManualScolar implements Serializable {
    private int isbn;
    public String titlu, autor, editura;
    protected double pret;
    private TipuriManuale tipManual;

    public ManualScolar(){
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
    public String getAutor() {return autor;}
    public void setAutor(String autor) {this.autor = autor;}
    public String getEditura() {return editura;}
    public void setEditura(String editura) {this.editura = editura;}
    public double getPret() {return pret;}
    public void setPret(double pret) {this.pret = pret;}
    public TipuriManuale getTipManual() {return tipManual;}
    public void setTipManual(TipuriManuale tipManual) {this.tipManual = tipManual;}

    public ManualScolar(int isbn, String titlu, String autor, double pret, TipuriManuale tipManual, String editura){
        this.isbn = isbn;
        this.titlu = titlu;
        this.autor = autor;
        this.pret = pret;
        this.tipManual = tipManual;
        this.editura = editura;
    }

    @Override
    public String toString() {
        //returns string representations of the object
        return "\nTitlu: " + titlu + "\nAutor: " + autor +
                "\nTip manual: " + tipManual + "\nISBN: " + isbn + "\nEditura: " + editura + "\nPret: " + pret + " RON" + "\n";
    }
}

