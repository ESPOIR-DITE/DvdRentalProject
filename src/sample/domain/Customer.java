package sample.domain;

public class Customer {
    private int custNumber;
    private String firstName;
    private String surname;
    private String phoneNum;
    private double credit;
    boolean canRent;

    public Customer() {
    }

    public Customer(int custNumber, String firstName, String surname, String phoneNum, double credit, boolean canRent) {
        this.custNumber = custNumber;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.credit = credit;
        this.canRent = canRent;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public boolean isCanRent() {
        return canRent;
    }

    public void setCanRent(boolean canRent) {
        this.canRent = canRent;
    }

    @Override
    public String toString() {
        return  " Customer \n_________" +
                " custNumber: " + custNumber +"\n"+
                " firstName: " + firstName + "\n" +
                " surname: " + surname + "\n" +
                " phoneNum: " + phoneNum + "\n" +
                " credit: " + credit +"\n"+
                " canRent: " + canRent +"\n\n\n"
                ;
    }
    public String secondtoString()
    {
        return "a#"+custNumber+"#"+firstName+"#"+surname+"#"+phoneNum+"#"+credit+"#"+canRent+"";
    }
}
