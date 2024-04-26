import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Oblig2Del1 {
    public static void main(String[] args) {
        SubsekvensRegister reg = new SubsekvensRegister();
        String mappe =  args[0];
        ArrayList<String> filer = lesMetadata(mappe + "\\metadata.csv");
        for (String f : filer) {
            reg.settInnHashMap(SubsekvensRegister.lesFil(mappe + "\\" + f));
        }

        while (reg.beholder.size() > 1) {

            HashMap<String, Subsekvens> h1 = reg.removeHashMap(0);
            HashMap<String, Subsekvens> h2 = reg.removeHashMap(0);

            HashMap<String, Subsekvens> flettet = SubsekvensRegister.slaaSammenHash(h1, h2);

            reg.settInnHashMap(flettet);
        }

        System.out.println(reg.hentHyppigsteSubsekvens());
        
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
