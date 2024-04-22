import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;


public class SubsekvensRegister {
    ArrayList<HashMap<String, Subsekvens>> beholder;

    public SubsekvensRegister() {
        beholder = new ArrayList<HashMap<String, Subsekvens>>();
    }

    public void settInnHashMap(HashMap<String, Subsekvens> hash) {
        beholder.add(hash);
    }

    public HashMap<String, Subsekvens> taUtHashMap(int indeks) {
        return beholder.get(indeks);
    }

    public int getAntallHashMaps() {
        return beholder.size();
    }

    public static HashMap<String, Subsekvens> lesFil(String filbane) {
        HashMap<String, Subsekvens> nyHash = new HashMap<>();
        File fil = new File(filbane);
        Scanner sc = new Scanner(fil);
        String linje = "";
        while (sc.hasNextLine()) {
            linje = sc.nextLine();
            if (linje.length() < 3) {   //Kan ikke lese substringer med lengde 3 fra linje med færre enn 3 tegn.
                System.exit(-1);
            }
            for (int i=0; i<linje.length() - 3; i++) {
                String sekvens = linje.substring(i, i+3);
                if (!nyHash.containsKey(sekvens)) {
                    nyHash.put(sekvens, 1);
                }
            }
        }

        beholder.add(nyHash);
        return nyHash;
    }
}