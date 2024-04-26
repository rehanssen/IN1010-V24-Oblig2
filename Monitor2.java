import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor2 {
    SubsekvensRegister register = new SubsekvensRegister();

    Lock laas = new ReentrantLock();
    Condition ikkeTomt = laas.newCondition();


    public void settInnHashMap(HashMap<String, Subsekvens> hash) {
        laas.lock();
        register.settInnHashMap(hash);
        ikkeTomt.signal();
        laas.unlock();
    }

    public HashMap<String, Subsekvens> taUtHashMap(int indeks) {
        laas.lock();
        HashMap<String, Subsekvens> tmp = register.beholder.get(indeks);
        laas.unlock();
        return tmp;
    }

    public HashMap<String, Subsekvens> removeHashMap(int indeks) {
        laas.lock();
        HashMap<String, Subsekvens> tmp = register.beholder.remove(indeks);
        laas.unlock();
        return tmp;
    }

    public List<HashMap<String, Subsekvens>> removeTwoHashes() throws InterruptedException {
        laas.lock();

        try {

            while (register.getAntallHashMaps() < 2) {
                ikkeTomt.await();
            }
            List<HashMap<String, Subsekvens>> r = new ArrayList<HashMap<String, Subsekvens>>();
            r.add(removeHashMap(0));
            r.add(removeHashMap(0));
            
            return r;
        }
        finally {
            laas.unlock();
        }

    }

    public int getAntallHashMaps() {
        laas.lock();
        int n = register.beholder.size();
        laas.unlock();
        return n;
    }

    public Subsekvens hentHyppigsteSubsekvens() {
        laas.lock();
        Subsekvens tmp = register.hentHyppigsteSubsekvens();
        laas.unlock();
        return tmp;
    }
}
