public class Subsekvens {
    protected final String subsekvens;
    protected int antall;

    public Subsekvens(String s) {
        this.subsekvens = s;
        antall = 1;
    }

    public void oekAntall() {
        this.antall++;
    }

    public int getAntall() {
        return this.antall;
    }

    @Override
    public String toString() {
        return "(" + this.subsekvens + "," + this.antall + ")";
    }

}