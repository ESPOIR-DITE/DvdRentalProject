package sample.domain.serverPack.serve;

public class HighValeu implements Comparable<HighValeu> {
    private int high;
    private String name="espoir";
    public HighValeu() {
    }

    public HighValeu(int high) {
        this.high = high;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return  ""+high;
    }


    @Override
    public int compareTo(HighValeu o) {
        return o.high-high;
    }


}
