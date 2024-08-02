public class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String name,String surname,String email){
        this.setname(name);
        this.setsurname(surname);
        this.setemail(email);
    }

    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name=name;
    }
    public String getsurname(){
        return surname;
    }
    public void setsurname(String surname){
        this.surname=surname;
    }
    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email=email;
    }


}
