import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Oblig2Hele {
    public static void main(String[] args) {
        Monitor2 friske = new Monitor2();
        Monitor2 syke = new Monitor2();

        ArrayList<Thread> lesetrader = new ArrayList<>();
        Thread[] friskeFlettere = new Thread[8];
        Thread[] sykeFlettere = new Thread[8];

        try {
            Scanner metaScanner = new Scanner(new File("./" + args[0] + "/metadata.csv"));

            // String[] linje holder et tuppel med filnavn og true/false for henholdsvis positive og negative individer.
            String[] linje = new String[2];
            while (metaScanner.hasNextLine()) {
                linje = metaScanner.nextLine().split(",");
                if (linje[1].equals("True")) {
                    lesetrader.add(new Thread(new LeseTrad(args[0] + "/" + linje[0], syke)));
                }
                else if (linje[1].equals("False")) {
                    lesetrader.add(new Thread(new LeseTrad(args[0] + "/" + linje[0], friske)));
                }
            }

            metaScanner.close();
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        // Starter alle lesetråder
        for (Thread lesetrad : lesetrader) {
            lesetrad.start();
        }
        // Kaller join() på alle lesetråder for å sikre at alle er ferdige
        for (Thread lesetrad : lesetrader) {
            try {
                lesetrad.join();
            }
            catch (InterruptedException e) {
                System.err.println("InterruptedException oppsto i lesetrad under Thread.join().");
            }
        }

        // Skal nå flette HashMaps for både friste og syke med 8 flettetråder til hver
        for (int i=0; i<8; i++) {
            friskeFlettere[i] = new Thread(new FletteTrad(friske));
            friskeFlettere[i].start();
            sykeFlettere[i] = new Thread(new FletteTrad(syke));
            sykeFlettere[i].start();
        }

        for (int i=0; i<8; i++) {
            try {
                friskeFlettere[i].join();
                sykeFlettere[i].join();
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }


        Subsekvens subsekvens = null;
        int forskjellIForekomster = 0;

        // Litt dårlig stil med dirkete aksess til instansvariabler og rotete kode.
        for (Subsekvens ss : syke.register.beholder.get(0).values()) {
            try {

                if ((ss.antall - friske.register.beholder.get(0).get(ss.subsekvens).antall) > forskjellIForekomster) {
                    subsekvens = ss;
                    forskjellIForekomster = (ss.antall - friske.register.beholder.get(0).get(ss.subsekvens).antall);
                }
            }
            catch (NullPointerException e) {;}
        }

        System.out.println(subsekvens);

        System.out.println("\n\n\n\n\n**** FRISKE *****");
        System.out.println(friske.register.beholder.size());
        System.out.println("\n\n\n\n****** SYKE ******");
        System.out.println(syke.register.beholder.size());
    }

}
