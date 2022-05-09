package model;

/**
 * A client is defined by id, name, address, email and age.
 * @author Pinzariu Razvan-George
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private int age;

    public Client(String name, String address, String eMail, int age) {
        this.name = name;
        this.address = address;
        this.email = eMail;
        this.age = age;
    }

    public Client(int id, String name, String address, String eMail, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = eMail;
        this.age = age;
    }

    public Client(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String eMail) {
        this.email = eMail;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return id + " - " + name;
    }
}
