import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


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

    public HashMap<String, Subsekvens> removeHashMap(int indeks) {
        return beholder.remove(indeks);
    }

    public int getAntallHashMaps() {
        return beholder.size();
    }

    public static HashMap<String, Subsekvens> lesFil(String filbane) {
        HashMap<String, Subsekvens> nyHash = new HashMap<>();
        File fil = new File(filbane);
        try {

            Scanner sc = new Scanner(fil);
            String linje = "";
            while (sc.hasNextLine()) {
                linje = sc.nextLine();
                if (linje.length() < 3) {   //Kan ikke lese substringer med lengde 3 fra linje med færre enn 3 tegn.
                    System.exit(-1);
                }
                for (int i=0; i<linje.length() - 2; i++) {
                    String sekvens = linje.substring(i, i+3);
                    if (!nyHash.containsKey(sekvens)) {
                        nyHash.put(sekvens, new Subsekvens(sekvens));
                    }
                }
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        
        //beholder.add(nyHash);
        return nyHash;
    }
    

    /* 
     * Hjelpemetode. Forutsetter at beholders ArrayList inneholder én HashMap
     * Returnerer den første forekomsten av Subsekvens med høyest antall forekomster (Dersom 2 ulike har samme høyeste antall er det bare én som returneres.)
     * Returnerer en Subsekvens
     */
    public Subsekvens hentHyppigsteSubsekvens() {
        Subsekvens tmp = null;
        for (Subsekvens ss : beholder.get(0).values()) {
            if (tmp != null) {
                if (ss.getAntall() > tmp.getAntall()) {
                    tmp = ss;
                }
            } else {
                tmp = ss;
            }
        }

        return tmp;
    }





    public static HashMap<String, Subsekvens> slaaSammenHash(HashMap<String, Subsekvens> h1, HashMap<String, Subsekvens> h2) {
        
        for (String s : h2.keySet()) {
            if (h1.containsKey(s)) {
                h1.get(s).antall = h1.get(s).getAntall() + h2.get(s).getAntall();
            } else {
                h1.putIfAbsent(s, h2.get(s));
            }
        }

        return h1;


        // HashMap<String, Subsekvens> nyHash = new HashMap<>();
        // nyHash.putAll(h1);
        
        // for (Map.Entry<String, Subsekvens> set : h2.entrySet()) {
        //     if (nyHash.containsKey(set.getKey())) {
        //         nyHash.get(set.getKey()).oekAntall();
        //     }
        //     else {
        //         nyHash.put(set.getKey(), set.getValue());
        //     }

        // }

        // return nyHash;
    }



}