package test;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by pawelwiejkut on 16.04.2016.
 */
public class Lista5 {

    private static PersistenceManager pm;
    private static Transaction tx;

    public static void exercise3a(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter city name");
//        String s = null;
//        try {
//            s = br.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        select  address.city ,count(address.PERSON_PERSON_ID_OID) as adresy, avg(employees.salary) as avera from address inner join person on person_person_id_oid = person_id inner join employees on person_person_id_oid=employees_id group by city
        Query q = pm.newQuery("select address.city, salary from employee.Employee ");
        List results = (List)q.execute();
        results.forEach(System.out::println);
    }



    public static void main(String[] args) {
        try {
            pm = JDOHelper.getPersistenceManagerFactory("Tutorial").getPersistenceManager();
            tx = pm.currentTransaction();

            exercise3a();
            pm.close ();
        } catch (Exception e) { e.printStackTrace(); }
    }


}
