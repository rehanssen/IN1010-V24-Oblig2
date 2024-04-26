public class LeseTrad implements Runnable {
    private String path;
    private Monitor1 monitor1;
    private Monitor2 monitor2;

    public LeseTrad(String path, Monitor1 monitor) {
        this.path = path;
        this.monitor1 = monitor;
    }

    public LeseTrad(String path, Monitor2 monitor) {
        this.path = path;
        this.monitor2 = monitor;
    }

    public void run() {
        if (monitor1 != null) {
            monitor1.settInnHashMap(SubsekvensRegister.lesFil(path));
        }
        else if (monitor2 != null) {
            monitor2.settInnHashMap(SubsekvensRegister.lesFil(path));
        }
    }


}
