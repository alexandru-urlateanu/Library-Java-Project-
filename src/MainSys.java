public class MainSys {

    public static void main(String[] args) throws Exception {
        Service serv = Service.getInstance();

        System.out.println("Alegeti modul de utilizare: \n1 - consola \n2 - fisiere");

        int modUtilizare = Service.in.nextInt();

        if(modUtilizare == 1)
            serv.startApplicationConsole();
        else
            serv.startApplicationCSV();
    }
}
