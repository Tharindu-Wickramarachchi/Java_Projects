public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person Person;


    public Ticket(int row,int seat,double price,Person Person){
        this.setrow(row);
        this.setseat(seat);
        this.setprice(price);
        this.setPerson(Person);
    }

    public int getrow(){
        return row;
    }
    public void setrow(int row){
        this.row=row;
    }
    public int getseat(){
        return seat;
    }
    public void setseat(int seat){
        this.seat=seat;
    }
    public double getprice(){
        return price;
    }
    public void setprice(double price){
        this.price=price;
    }
    public Person getPerson(){
        return Person;
    }
    public void setPerson(Person Person){
        this.Person=Person;
    }

    public void print() {
        System.out.println("\nTicket");
        System.out.println("Name : " + Person.getname());
        System.out.println("Surname : " + Person.getsurname());
        System.out.println("G-mail : " + Person.getemail());
        System.out.println("Row number : " + row);
        System.out.println("Seat number : " + seat);
        System.out.println("Ticket price : " + price);
    }

    @Override
    public String toString() {
        return "{" + row + "," + seat + "," + price + "," + Person + "}";
    }


}
