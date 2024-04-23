import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        SubsekvensRegister register = new SubsekvensRegister();
        register.settInnHashMap(SubsekvensRegister.lesFil("./TestOppgaveEks/fil1.csv"));

        // System.out.println(register.getAntallHashMaps());


        // System.out.println(register.taUtHashMap(0));

        
        HashMap<String, Subsekvens> hm1 = SubsekvensRegister.lesFil("./TestOppgaveEks/fil1.csv");
        HashMap<String, Subsekvens> hm2 = SubsekvensRegister.lesFil("./TestOppgaveEks/fil2.csv");

        HashMap<String, Subsekvens> mergeHM = SubsekvensRegister.slaaSammenHash(hm1, hm2);

        for (Subsekvens ss : mergeHM.values()) {
            System.out.println(ss);
        }


        //System.out.println(SubsekvensRegister.lesFil("./TestOppgaveEks/fil1.csv"));
    }
}
