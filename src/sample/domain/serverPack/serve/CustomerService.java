package sample.domain.serverPack.serve;

public class CustomerService implements Comparable<CustomerService> {
    private int custNumber;
    private String firstName;
    private String surname;
    private double credit;

    public CustomerService() {

    }

    public CustomerService(int custNumber, String firstName, String surname, double credit) {
        this.custNumber = custNumber;
        this.firstName = firstName;
        this.surname = surname;
        this.credit = credit;
    }

    public int getCustNumber() {
        return custNumber;
    }

    public void setCustNumber(int custNumber) {
        this.custNumber = custNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return custNumber +" "+ firstName +" "+  surname+" " +  credit ;
    }

    @Override
    public int compareTo(CustomerService o) {
        int compareter=this.firstName.compareTo(o.firstName);
        if(compareter<0)return -1;
        if(compareter>0)return 1;
        return 0;
    }
}
