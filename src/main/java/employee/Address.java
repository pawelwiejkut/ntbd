package employee;

import javax.jdo.annotations.PersistenceCapable;

/**
 * Created by pawelwiejkut on 04.04.2016.
 */

@PersistenceCapable
public class Address {
     private String city;
     private String street;
     private String postcode;
     private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", postcode='").append(postcode).append('\'');
        sb.append(", department=").append(department);
        sb.append('}');
        return sb.toString();
    }
}
