public class Subsekvens {
    protected String subsekvens;
    protected final int antall;

    public Subsekvens(String s) {
        this.subsekvens = s;
        antall = 1;
    }

    @Override
    public String toString() {
        return "(" + this.subsekvens + "," + this.antall + ")";
    }

}