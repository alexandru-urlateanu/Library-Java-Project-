import ComponenteLibrarie.*;

import java.io.*;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.sql.Timestamp;

public class Service {
    static boolean running = true;
    static Scanner in = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static String fileName = null;
    static Biblioteca bibl = new Biblioteca();
    private static String line = "";
    private static final String basePath = "D:\\FACULTATE\\ANUL 2\\SEM 2\\EAP\\Proiect EAP 2022\\src\\FisiereCSV\\";

    //Singleton
    private static Service single_instance = null;
    private Service() {}
    public static Service getInstance() {
        if(single_instance == null)
            single_instance = new Service();
        return single_instance;
    }

    public void menuConsole(){
        System.out.println("\n\t\t\t----- LIBRARIE -----"
                + "\nApasati tasta corespunzatoare actiunii dorite: "
                + "\n\t 0 ――> iesire din aplicatie"
                + "\n\t 1 ――> incarcare librarie"
                + "\n\t 2 ――> salvare si iesire"
                + "\n\t 3 ――> adauga o carte in librarie"
                + "\n\t 4 ――> afisarea tuturor articolelor din librarie"
                + "\n\t 5 ――> afisarea tuturor cartilor dintr-o sectiune"
                + "\n\t 6 ――> afisarea tuturor cartilor scrise de un autor"
                + "\n\t 7 ――> afisarea tututor cartilor de la o editura"
                + "\n\t 8 ――> afisarea tuturor autorilor"
                + "\n\t 9 ――> sorteaza cartile dupa titlu"
                + "\n\t10 ――> sorteaza cartile crescator dupa pret"
                + "\n\t11 ――> sorteaza cartile descrescator dupa pret"
                + "\n\t12 ――> adauga un manual in librarie"
                + "\n\t13 ――> afiseaza toate manualele pentru o disciplina"
                + "\nOptiunea aleasa: ");
    }
    public void startApplicationConsole() throws Exception {
        while (running) {

            menuConsole();

            int option = in.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Introduceti numele fisierului pe care doriti sa-l incarcati");
                    loadScript(in.next());
                    break;
                case 2: {
                    saveAndQuit();
                    running = false;
                }
                    break;
                case 3:
                    try {
                        adaugaCarte();
                    } catch(Exception e){
                        System.out.println("Eroare!");
                    }
                    break;
                case 4:
                    System.out.println(bibl.toString());
                    break;
                case 5:
                    afiseazaCartiDupaSectiune();
                    break;
                case 6:
                    try {
                        afiseazaCartiDupaAutor();
                    } catch(Exception e){
                        System.out.println("Nu exista carti!");
                    }
                    break;
                case 7:
                    afiseazaCartiDupaEditura();
                    break;
                case 8:
                    afiseazaAutori();
                    break;
                case 9:
                    sorteazaCartileDupaTitlu();
                    System.out.println("\nCartile au fost sortate alfabetic dupa titlu!");
                    break;
                case 10:
                    sorteazaCartileDupaPretCrescator();
                    System.out.println("\nCartile au fost sortate crescator dupa pret!");
                    break;
                case 11:
                    sorteazaCartileDupaPretDescrescator();
                    System.out.println("\nCartile au fost sortate descrescator dupa pret!");
                    break;
                case 12:
                    try {
                        adaugaManual();
                    } catch(Exception e){
                        System.out.println("Eroare!");
                    }
                    break;
                case 13:
                    afiseazaManualeDupaDisciplina();
                    break;
                case 0:
                    try(FileWriter fw = new FileWriter(basePath + "Audit.txt",true)){
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        fw.write("Iesirea din program, " + timestamp + "\n");
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    return;
                default:
                    System.out.println("\nOptiunea selectata nu este disponibila!");
            }
        }
        System.exit(0);
    }

    public void menuCSV(){
        System.out.println("\n\t\t\t----- LIBRARIE -----"
                + "\nApasati tasta corespunzatoare actiunii dorite: "
                + "\n\t 0 ――> iesire din program"
                + "\n\t 1 ――> citeste cartile din fisier si afiseaza-le"
                + "\n\t 2 ――> adauga o noua carte in fisier"
                + "\n\t 3 ――> sterge o carte din fisier"
                + "\n\t 4 ――> sorteaza cartile alfabetic dupa titlu"
                + "\n\t 5 ――> citeste manualele din fisier si afiseaza-le"
                + "\n\t 6 ――> adauga un nou manual in fisier"
                + "\n\t 7 ――> sterge un manual din fisier"
                + "\n\t 8 ――> sorteaza manualele alfabetic dupa titlu"
                + "\nOptiunea aleasa: ");
    }
    public void startApplicationCSV() throws Exception {
        while (running) {

            menuCSV();

            int option = in.nextInt();

            switch (option) {
                case 1:
                    try {
                        citireCartiDinFisier();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        adaugaCarteInFisier();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    stergeCarteDinFisier();
                    break;
                case 4:
                    sorteazaFisierulCartiDupaTitlu();
                    break;
                case 5:
                    try {
                        citireManualeDinFisier();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        adaugaManualInFisier();
                    } catch (IOException e) {
                        e.printStackTrace();    
                    }
                    break;
                case 7:
                    stergeManualDinFisier();
                    break;
                case 8:
                    sorteazaFisierulManualeDupaTitlu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Optiunea selectata nu este disponibila!");
            }
        }
        System.exit(0);
    }

    //Functii pentru utilizarea aplicatiei la consola
    private static void loadScript(String name) {
        FileInputStream fis = null;
        ObjectInputStream in = null;

        File file = new File(name);
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                in = new ObjectInputStream(fis);
                bibl = (Biblioteca) in.readObject();
                fis.close();
                in.close();
                System.out.println("Fisierul a fost incarcat!");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("\nFisierul nu exista!");
        }

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Incarcarea salvarii cu continutul librariei, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void saveAndQuit() {
        System.out.println("Introduceti numele fisierului: ");
        fileName = in.next();
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(bibl);
            fos.close();
            out.close();
            System.out.println("Continutul a fost salvat in fisier!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Salavarea continutului librariei si iesirea din program, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void adaugaCarte() throws Exception{
        int isbn;
        String titlu, autor, editura;
        double pret;
        Categorii categorie;

        System.out.println("\nIntroduceti titlul: ");
        titlu = reader.readLine();

        System.out.println("\nIntroduceti autorul: ");
        autor = reader.readLine();

        System.out.println("\nIntroduceti categoria: \nbeletristica / istorie / medicina / dezvoltarePersonala");
        String s = reader.readLine();

        categorie = Categorii.valueOf(s);

        System.out.println("\nIntroduceti ISBN-ul: ");
        isbn = in.nextInt();

        System.out.println("\nIntroduceti editura: ");
        editura = reader.readLine();

        System.out.println("\nIntroduceti pretul: ");
        pret = in.nextDouble();

        Carte c = new Carte(isbn, titlu, autor, pret, categorie, editura);
        bibl.adaugaCarte(c);
        bibl.adaugaAutor(autor);
        bibl.adaugaEditura(editura);

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Adaugarea unei carti, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void afiseazaCartiDupaSectiune() {
        System.out.println("1 - beletristica\n2 - istorie\n3 - medicina\n4 - dezvoltare personala");
        System.out.println("\nIntroduceti tasta pentru categoria dorita");
        int tasta = in.nextInt();
        Sectiune sectiune = bibl.sectiuni.get(tasta);
        System.out.println("\nSectiunea selectata - " + sectiune.getCategorie().toString());
        System.out.println(sectiune.afiseazaCarti(bibl));

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Afisarea cartilor dintr-o sectiune, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void afiseazaCartiDupaAutor() throws Exception{
        System.out.println("\nIntroduceti autorul:");
        String autor = reader.readLine();
        String result = "\n";
        for(Carte c : bibl.listaCarti){
            if(c.getAutor().equals(autor)) {
                result = result + c;
            }
        }
        System.out.println(result);

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Afisarea cartilor scrise de un autor, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void afiseazaCartiDupaEditura() throws Exception{
        System.out.println("\nIntroduceti editura:");
        String editura = reader.readLine();
        String result = "\n";
        for(Carte c : bibl.listaCarti){
            if(c.getEditura().equals(editura)) {
                result = result + c;
            }
        }
        System.out.println(result);

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Afisarea cartilor de la o editura, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void afiseazaAutori(){
        String result = "\n";
        for(Autor a : bibl.listaAutori){
            result = result + a.toString();
        }
        System.out.println(result);

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Afisarea autorilor, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void sorteazaCartileDupaTitlu(){
        bibl.listaCarti = bibl.listaCarti.stream().sorted(Comparator.comparing(carte -> carte.titlu.toLowerCase(Locale.ROOT))).collect(Collectors.toList());

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Sortarea cartilor alfabetic dupa titlu, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void sorteazaCartileDupaPretCrescator(){
        bibl.listaCarti = bibl.listaCarti.stream().sorted(Comparator.comparing(Carte::getPret)).collect(Collectors.toList());
        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Sortarea cartilor crescator dupa pret, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void sorteazaCartileDupaPretDescrescator() {
        bibl.listaCarti = bibl.listaCarti.stream().sorted(Comparator.comparing(Carte::getPret).reversed()).collect(Collectors.toList());
        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Sortarea cartilor descrescator dupa pret, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void adaugaManual() throws Exception{
        int isbn;
        String titlu, autor, editura;
        double pret;
        TipuriManuale tipManual;

        System.out.println("\nIntroduceti titlul: ");
        titlu = reader.readLine();

        System.out.println("\nIntroduceti autorul: ");
        autor = reader.readLine();

        System.out.println("\nIntroduceti tipul manualului: \nlimbaRomana / matematica / fizica / istorie / geografie / biologie / informatica / limbiStraine");
        String tip = reader.readLine();

        tipManual = TipuriManuale.valueOf(tip);

        System.out.println("\nIntroduceti ISBN-ul: ");
        isbn = in.nextInt();

        System.out.println("\nIntroduceti editura: ");
        editura = reader.readLine();

        System.out.println("\nIntroduceti pretul: ");
        pret = in.nextDouble();

        ManualScolar m = new ManualScolar(isbn, titlu, autor, pret, tipManual, editura);

        bibl.adaugaManual(m);
        bibl.adaugaAutor(autor);
        bibl.adaugaEditura(editura);

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Adaugarea unui manual, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void afiseazaManualeDupaDisciplina() {
        System.out.println("1 - limba romana\n2 - matematica\n3 - fizica\n4 - istorie\n5 - geografie\n6 - biologie\n7 - informatica\n8 - limbi straine");
        System.out.println("\nIntroduceti tasta pentru disciplina dorita");
        int tasta = in.nextInt();
        SectiuneManuale sectiune = bibl.sectiuniM.get(tasta);
        System.out.println("\nDisciplina selectat - " + sectiune.getTipManual().toString());
        System.out.println(sectiune.afiseazaManuale(bibl));

        try{
            FileWriter fw = new FileWriter(basePath + "Audit.txt",true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fw.write("Afisarea manualelor pentru o disciplina, " + timestamp + "\n");
            fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //Functii pentru utilizarea aplicatiei cu fisiere
    private static void citireCartiDinFisier() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(basePath + "fisierCarti.txt"));
        List<Carte> books = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length > 0) {
                Carte carte = new Carte();
                carte.setTitlu(values[0]);
                carte.setAutor(values[1]);
                carte.setCategorie(Categorii.valueOf(values[2]));
                carte.setIsbn(Integer.parseInt(values[3]));
                carte.setEditura(values[4]);
                carte.setPret(Double.parseDouble(values[5]));
                books.add(carte);
            }
        }
        for (Carte c : books) {
            System.out.println(c);
        }
    }
    private static void adaugaCarteInFisier() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(basePath + "fisierCarti.txt",true));
        System.out.println("Introduceti cartea pe care doriti sa o adaugati:"
                +"\n(de forma: titlu,autor,categorie,isbn,editura,pret)");
        String str;
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();
        bw.newLine();
        bw.write(str);
        bw.close();
        System.out.println("\nCartea a fost adaugata!");
    }
    private static void stergeCarteDinFisier() throws IOException {
        File inputFile = new File(basePath + "fisierCarti.txt");
        File tempFile = new File(basePath + "fisierCartiDupaStergere.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        System.out.println("Introduceti cartea pe care doriti sa o stergeti: ");

        String lineToRemove;
        Scanner scan = new Scanner(System.in);
        lineToRemove = scan.nextLine();

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();

            if(trimmedLine.equals(lineToRemove)) continue;

            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.close();
        reader.close();

        System.out.println("\nCartea a fost stearsa din fisier!");
    }
    private static void sorteazaFisierulCartiDupaTitlu() throws IOException {
        FileReader fileReader = new FileReader(basePath + "fisierCarti.txt");
        BufferedReader br = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        Collections.sort(lines, Collator.getInstance());
        FileWriter fr = new FileWriter(basePath + "fisierCartiSortate.txt");
        for(String str: lines) {
            fr.write(str + "\r\n");
        }
        fr.close();
        System.out.println("Cartile au fost sortate alfabetic dupa titlu!");
    }
    private static void citireManualeDinFisier() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(basePath + "fisierManuale.txt"));
        List<ManualScolar> manuals = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length > 0) {
                ManualScolar manual = new ManualScolar();
                manual.setTitlu(values[0]);
                manual.setAutor(values[1]);
                manual.setTipManual(TipuriManuale.valueOf(values[2]));
                manual.setIsbn(Integer.parseInt(values[3]));
                manual.setEditura(values[4]);
                manual.setPret(Double.parseDouble(values[5]));
                manuals.add(manual);
            }
        }
        for (ManualScolar m : manuals) {
            System.out.println(m);
        }
    }
    private static void adaugaManualInFisier() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(basePath + "fisierManuale.txt",true));
        System.out.println("Introduceti manualu pe care doriti sa il adaugati:"
                +"\n(de forma: titlu,autor,tipManual,isbn,editura,pret)");
        String str;
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();
        bw.newLine();
        bw.write(str);
        bw.close();
        System.out.println("\nManualul a fost adaugat!");
    }
    private static void stergeManualDinFisier() throws IOException {
        File inputFile = new File(basePath + "fisierManuale.txt");
        File tempFile = new File(basePath + "fisierManualeDupaStergere.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        System.out.println("Introduceti manualul pe care doriti sa o stergeti: ");

        String lineToRemove;
        Scanner scan = new Scanner(System.in);
        lineToRemove = scan.nextLine();

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();

            if(trimmedLine.equals(lineToRemove)) continue;

            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.close();
        reader.close();

        System.out.println("\nManualul a fost sters din fisier!");
    }
    private static void sorteazaFisierulManualeDupaTitlu() throws IOException {
        FileReader fileReader = new FileReader(basePath + "fisierManuale.txt");
        BufferedReader br = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        Collections.sort(lines, Collator.getInstance());
        FileWriter fr = new FileWriter(basePath + "fisierManualeSortate.txt");
        for(String str: lines) {
            fr.write(str + "\r\n");
        }
        fr.close();
        System.out.println("Manualele au fost sortate alfabetic dupa titlu!");
    }
}
