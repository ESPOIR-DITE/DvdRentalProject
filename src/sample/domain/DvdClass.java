package sample.domain;

public class DvdClass {
    private int dvdNumber;
    private String title;
    private String category;
    private double price;
    private boolean newRelease;
    private boolean availableForRent;

    public DvdClass() {
    }

    public DvdClass(int dvdNumber, String title, String category, double price, boolean newRelease, boolean availableForRent) {
        this.dvdNumber = dvdNumber;
        this.title = title;
        this.category = category;
        this.price = price;
        this.newRelease = newRelease;
        this.availableForRent = availableForRent;
    }

    public int getDvdNumber() {
        return dvdNumber;
    }

    public void setDvdNumber(int dvdNumber) {
        this.dvdNumber = dvdNumber;
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

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        if(newRelease=true){
        this.price = 15;}
        else this.price=10;
    }

    public boolean isNewRelease() {
        return newRelease;
    }

    public void setNewRelease(boolean newRelease) {
        this.newRelease = newRelease;
    }

    public boolean isAvailableForRent() {
        return availableForRent;
    }

    public void setAvailableForRent(boolean availableForRent) {
        this.availableForRent = availableForRent;
    }

    @Override
    public String toString() {
        return
                " dvdNumber: " +    dvdNumber +"\n"+
                " title: " +        title + '\'' +"\n"+
                " category: " +     category + '\'' +"\n"+
                " price: " +        price +"\n"+
                " newRelease: " +   newRelease +"\n"+
                " availableForRent: " + availableForRent+"\n";
    }
    public String secondtoString()
    {
        return "b#"+dvdNumber+"#"+title+"#"+category+"#"+price +"#"+newRelease +"#"+ availableForRent;
    }
}
