import java.util.HashMap;

public class FletteTrad implements Runnable {
    Monitor2 monitor;

    public FletteTrad(Monitor2 monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (monitor.getAntallHashMaps() > 1) {
            HashMap<String, Subsekvens> h1 = monitor.removeHashMap(0);
            HashMap<String, Subsekvens> h2 = monitor.removeHashMap(0);

            HashMap<String, Subsekvens> retur = SubsekvensRegister.slaaSammenHash(h1, h2);
            monitor.settInnHashMap(retur);
        }
    }
}
