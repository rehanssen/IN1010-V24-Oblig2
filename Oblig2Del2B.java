import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Oblig2Del2B {
    public static void main(String[] args) {

        Monitor2 monitor = new Monitor2();
        ArrayList<Thread> lesetrader = new ArrayList<>();
        Thread[] flettetrader = new Thread[8];

        String mappe =  args[0];
        ArrayList<String> filer = lesMetadata(mappe + "\\metadata.csv");

        for (String f : filer) {
            Thread lesetrad = new Thread(new LeseTrad(mappe + "\\" + f, monitor));
            lesetrader.add(lesetrad);
            lesetrad.start();
        }

        for (Thread lt : lesetrader) {
            try {
                lt.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Alle lesetråder er ferdige.");

        for (int i=0; i<8; i++) {
            flettetrader[i] = new Thread(new FletteTrad(monitor));
            flettetrader[i].start();
        }

        //TODO: se gjennom om det er en bedre måte å gjøre dette.
        while (true) {
            if (monitor.getAntallHashMaps() != 1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {;}
                continue;
            }

            break;
            
        }

        for (Thread t : flettetrader) {
            if (t.isAlive()) {
                t.interrupt();
            }
        }

        

        System.out.println(monitor.hentHyppigsteSubsekvens());
        
    }







    public static ArrayList<String> lesMetadata(String path) {
        ArrayList<String> filer = new ArrayList<>();
        File metaFil = new File(path);
        try {
            Scanner sc = new Scanner(metaFil);

            while (sc.hasNextLine()) {
                filer.add(sc.nextLine());
            }

            
            sc.close();
            return filer;
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
