import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 {
    SubsekvensRegister register = new SubsekvensRegister();

    Lock laas = new ReentrantLock();


    public void settInnHashMap(HashMap<String, Subsekvens> hash) {
        laas.lock();
        register.settInnHashMap(hash);
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
