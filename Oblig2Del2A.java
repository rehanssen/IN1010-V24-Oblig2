import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig2Del2A {
    public static void main(String[] args) {

        Monitor1 monitor = new Monitor1();

        String mappe =  args[0];
        ArrayList<String> filer = lesMetadata(mappe + "\\metadata.csv");

        for (String f : filer) {
            LeseTrad trad = new LeseTrad(mappe + "\\" + f, monitor);
            trad.run();
        }

        while (monitor.getAntallHashMaps() > 1) {

            HashMap<String, Subsekvens> h1 = monitor.removeHashMap(0);
            HashMap<String, Subsekvens> h2 = monitor.removeHashMap(0);

            HashMap<String, Subsekvens> flettet = SubsekvensRegister.slaaSammenHash(h1, h2);

            monitor.settInnHashMap(flettet);
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
