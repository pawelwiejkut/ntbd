package test;

import employee.Employee;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by pawelwiejkut on 04.04.2016.
 */
public class Lista4 {

    private static PersistenceManager pm;
    private static Transaction tx;

    public static void exercise2a(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter department name");
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Query q = pm.newQuery(Employee.class);
        q.setFilter(String.format("department.address.city==\"%s\"",s));
        q.setResult("project");
        List results = (List)q.execute();
        results.forEach(System.out::println);
    }


    public static void main(String[] args) {
        try {
            pm = JDOHelper.getPersistenceManagerFactory("Tutorial").getPersistenceManager();
            tx = pm.currentTransaction();

            exercise2a();

            pm.close ();
        } catch (Exception e) { e.printStackTrace(); }
    }

}
