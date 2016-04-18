package employee;

import javax.jdo.annotations.PersistenceCapable;

/**
 * Created by pawelwiejkut on 16.04.2016.
 */
@PersistenceCapable
public class Person {

    private String name;
    private Address address;

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
