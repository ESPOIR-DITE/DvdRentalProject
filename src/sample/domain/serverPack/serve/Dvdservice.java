package sample.domain.serverPack.serve;

public class Dvdservice implements Comparable<Dvdservice> {
    private String title;
    private String category;
    private boolean available;
    private double price;

    public Dvdservice() {
    }

    public Dvdservice(String title, String category, boolean available, double price) {
        this.title = title;
        this.category = category;
        this.available = available;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return   title +" " + category  +" "+ available  +" "+ price ;
    }

    @Override
    public int compareTo(Dvdservice o) {
        int compareInt=this.title.compareTo(o.title);
        if(compareInt<0)return -1;
        if(compareInt>0)return 1;
        return 0;

    }
}
