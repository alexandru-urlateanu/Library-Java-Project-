package ComponenteLibrarie;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca implements Serializable {

    public List<Carte> listaCarti;
    public ArrayList<Autor> listaAutori;
    public List<Editura> listaEdituri;
    public List<ManualScolar> listaManuale;
    public HashMap<Integer, Sectiune> sectiuni = new HashMap<>();
    public HashMap<Integer, SectiuneManuale> sectiuniM = new HashMap<>();

    private static final String basePath = "D:\\FACULTATE\\ANUL 2\\SEM 2\\EAP\\Proiect EAP 2022\\src\\FisiereCSV\\";

    public Biblioteca() {
        listaCarti = new ArrayList<Carte>();
        listaAutori = new ArrayList<Autor>();
        listaEdituri = new ArrayList<Editura>();
        listaManuale = new ArrayList<ManualScolar>();
//        ComponenteLibrarie.Carte c1 = new ComponenteLibrarie.Carte(10, "carte1", "autor1", 100, ComponenteLibrarie.Categorii.beletristica);
//        ComponenteLibrarie.Carte c2 = new ComponenteLibrarie.Carte(11, "carte2", "autor2", 110, ComponenteLibrarie.Categorii.istorie);
//        ComponenteLibrarie.Carte c3 = new ComponenteLibrarie.Carte(12, "carte3", "autor3", 120, ComponenteLibrarie.Categorii.medicina);
//        ComponenteLibrarie.Carte c4 = new ComponenteLibrarie.Carte(13, "carte4", "autor4", 130, ComponenteLibrarie.Categorii.dezvoltarePersonala);
//        collection.add(c1);
//        collection.add(c2);
//        collection.add(c3);
//        collection.add(c4);
        Sectiune s1 = new Sectiune(Categorii.beletristica);
        Sectiune s2 = new Sectiune(Categorii.istorie);
        Sectiune s3 = new Sectiune(Categorii.medicina);
        Sectiune s4 = new Sectiune(Categorii.dezvoltarePersonala);
        sectiuni.put(1, s1);
        sectiuni.put(2, s2);
        sectiuni.put(3, s3);
        sectiuni.put(4, s4);

        SectiuneManuale sm1 = new SectiuneManuale(TipuriManuale.limbaRomana);
        SectiuneManuale sm2 = new SectiuneManuale(TipuriManuale.matematica);
        SectiuneManuale sm3 = new SectiuneManuale(TipuriManuale.fizica);
        SectiuneManuale sm4 = new SectiuneManuale(TipuriManuale.istorie);
        SectiuneManuale sm5 = new SectiuneManuale(TipuriManuale.geografie);
        SectiuneManuale sm6 = new SectiuneManuale(TipuriManuale.biologie);
        SectiuneManuale sm7 = new SectiuneManuale(TipuriManuale.informatica);
        SectiuneManuale sm8 = new SectiuneManuale(TipuriManuale.limbiStraine);
        sectiuniM.put(1, sm1);
        sectiuniM.put(2, sm2);
        sectiuniM.put(3, sm3);
        sectiuniM.put(4, sm4);
        sectiuniM.put(5, sm5);
        sectiuniM.put(6, sm6);
        sectiuniM.put(7, sm7);
        sectiuniM.put(8, sm8);
    }

    public void adaugaCarte (Carte carte) {
        listaCarti.add(carte);
    }

    public void adaugaManual (ManualScolar manual) {
        listaManuale.add(manual);
    }

    public void adaugaAutor (String numeAutor) {
        List<String> listaNume = listaAutori.stream().map(Autor::getNume).collect(Collectors.toList());
        if(!listaNume.contains(numeAutor)) {
            listaAutori.add(new Autor(numeAutor));
        }
    }

    public void adaugaEditura (String numeEditura){
        List<String> listaEdit = listaEdituri.stream().map(Editura::getNume).collect(Collectors.toList());
        if(!listaEdit.contains(numeEditura)) {
            listaEdituri.add(new Editura(numeEditura));
        }
    }

    @Override
    public String toString() {
        String total = "\n";
        Iterator<Carte> i = listaCarti.iterator();
        while(i.hasNext()) {
            Carte c = (Carte) i.next();
            total = total + c.toString();
        }
        Iterator<ManualScolar> j = listaManuale.iterator();
        while(j.hasNext()) {
            ManualScolar m = (ManualScolar) j.next();
            total = total + m.toString();
        }

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Afisarea continutului librariei, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return total;
    }


}
