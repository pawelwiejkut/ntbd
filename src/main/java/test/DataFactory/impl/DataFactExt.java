package test.DataFactory.impl;

import org.jfairy.Fairy;
import org.jfairy.producer.person.Person;
import test.DataFactory.DepartmentDataValues;
import test.DataFactory.EmployeeDataValues;
import test.DataFactory.ProjectDataValues;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by pawelwiejkut on 13.03.2016.
 */
public final class DataFactExt extends DataFactory {

    private ProjectDataValues projectDataValues = new DefaultProjectDataValues();
    private EmployeeDataValues employeeDataValues = new DefaultEmployeeDataValue();
    private DepartmentDataValues departmentDataValues = new DefaultDepartmentDataValues();

    public String getProjectName() {
        return getItem(projectDataValues.getProjectName());
    }

    public String getProjectSubject() {
        return getItem(projectDataValues.getProjectSubject());
    }

    public String getEmployeeJob(){
        return getItem(employeeDataValues.getEmployeeJob());
    }

    public String getEmployeeName(){
        Fairy plFairy = Fairy.create(Locale.forLanguageTag("pl"));
        Person person = plFairy.person();
        return person.fullName();
    }

    public Date getEmployeeHireDate(){
        return getBirthDate();
    }

    public double getEmployeeSalary(){
        Random r = new Random();
        long salary;
        salary = Math.round(2000 + (5000 - 1000) * r.nextDouble());
        return (double)salary;
    }

    public String getDepartmentName(){
        return getItem(departmentDataValues.getDepartmentName());
    }

    public String getDepartmentLocation(){
        Fairy plFairy = Fairy.create(Locale.forLanguageTag("pl"));
        Person person = plFairy.person();
        return person.getAddress().toString().replaceAll("\\d","").replaceAll("-","").trim();
    }

}
