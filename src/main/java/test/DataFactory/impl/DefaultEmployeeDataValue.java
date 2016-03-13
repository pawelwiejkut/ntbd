package test.DataFactory.impl;

import test.DataFactory.EmployeeDataValues;

/**
 * Created by pawelwiejkut on 13.03.2016.
 */
public class DefaultEmployeeDataValue implements EmployeeDataValues{


    private String[] employeeJob={"Dyrektor","Prezes","Developer","Księgowa","Kadrowa"};


    @Override
    public String[] getEmployeeJob() {
        return employeeJob;
    }

}
