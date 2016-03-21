package test;

import employee.Project;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by pawelwiejkut on 21.03.2016.
 */
public class Lista3 {

    private static PersistenceManager pm;
    private static Transaction tx;

    public static void firstLetQuery() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter first letter of project name");
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Query q = pm.newQuery(Project.class);
        q.setFilter("name.startsWith(\""+s+"\")");
        List results = (List)q.execute();
        results.forEach(System.out::println);
    }

//	https://db.apache.org/jdo/jdoql_methods.html

    public static void letInQuery() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter letter in project name");
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Query q = pm.newQuery(Project.class);
        q.setFilter(String.format("name.matches(\".*%s.*\")", s));
        List results = (List)q.execute();
        results.forEach(System.out::println);
    }

    public static void main(String[] args) {
        try {
            pm = JDOHelper.getPersistenceManagerFactory("Tutorial").getPersistenceManager();
            tx = pm.currentTransaction();

            letInQuery();

            pm.close ();
        } catch (Exception e) { e.printStackTrace(); }
    }

}
