package sample.domain.serverPack.serve;

public class RentalService implements Comparable<RentalService> {
    private int rentalNumber;
    private String dateRented;
    private String dateReturned;
    private int custNumber;
    private int dvdNumber;

    public RentalService() {
    }

    public RentalService(int rentalNumber, String dateRented, String dateReturned, int custNumber, int dvdNumber) {
        this.rentalNumber = rentalNumber;
        this.dateRented = dateRented;
        this.dateReturned = dateReturned;
        this.custNumber = custNumber;
        this.dvdNumber = dvdNumber;
    }

    public int getRentalNumber() {
        return rentalNumber;
    }

    public void setRentalNumber(int rentalNumber) {
        this.rentalNumber = rentalNumber;
    }

    public String getDateRented() {
        return dateRented;
    }

    public void setDateRented(String dateRented) {
        this.dateRented = dateRented;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    public int getCustNumber() {
        return custNumber;
    }

    public void setCustNumber(int custNumber) {
        this.custNumber = custNumber;
    }

    public int getDvdNumber() {
        return dvdNumber;
    }

    public void setDvdNumber(int dvdNumber) {
        this.dvdNumber = dvdNumber;
    }

    @Override
    public String toString() {
        return  rentalNumber+" " +dateRented+" " + dateReturned+" "  + custNumber+" " +dvdNumber;
    }

    @Override
    public int compareTo(RentalService o) {
        int comparator =this.dateRented.compareTo(o.dateRented);
        if(comparator<0)return -1;
        if(comparator>0)return 1;
        return 0;
    }
}
