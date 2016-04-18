package test;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by pawelwiejkut on 16.04.2016.
 */
public class Lista5 {

    private static PersistenceManager pm;
    private static Transaction tx;

    public static void exercise3a(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner( System.in );
        System.out.println("Enter first and second number");
        int x ;
        int y;
        x=input.nextInt();
        y=input.nextInt();

//        select  address.city ,count(address.PERSON_PERSON_ID_OID) as adresy, avg(employees.salary) as avera from address inner join person on person_person_id_oid = person_id inner join employees on person_person_id_oid=employees_id group by city
        Query q = pm.newQuery("select address.city, avg(salary), count(name)  from employee.Employee group by address.city HAVING avg(salary) >= :x && avg(salary) <= :y");
        List results = (List)q.execute(x,y);
        results.forEach(System.out::println);
    }

    public static void exercise3b(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Scanner input = new Scanner( System.in );
//        System.out.println("Enter first and second number");
//        int x ;
//        int y;
//        x=input.nextInt();
//        y=input.nextInt();

        Query q = pm.newQuery("select  from employee.Employee where project.contains(proj) && proj.task.contains(tsk) && sum(tsk.endDate-tsk.startDate)>=:x");
        List results = (List)q.execute();
        results.forEach(System.out::println);
    }


    public static void main(String[] args) {
        try {
            pm = JDOHelper.getPersistenceManagerFactory("Tutorial").getPersistenceManager();
            tx = pm.currentTransaction();

            exercise3b();

            pm.close ();
        } catch (Exception e) { e.printStackTrace(); }
    }


}
