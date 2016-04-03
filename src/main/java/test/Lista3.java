package test;

import employee.Department;
import employee.Employee;
import employee.Project;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by pawelwiejkut on 21.03.2016.
 */
public class Lista3 {

    //	https://db.apache.org/jdo/jdoql_methods.html

    private static PersistenceManager pm;
    private static Transaction tx;

    public static void exercise1a() {
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

    public static void exercise1b() {
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

    public static void exercise2a(){
//  select employees_id from employees inner join department on department_id = employees_id where department.name='Filia'
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter department name");
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Query q = pm.newQuery(Employee.class);
        q.setFilter(String.format("department.name==\"%s\"",s));
        List results = (List)q.execute();
        results.forEach(System.out::println);
    }

    public static void exercise2b(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter employee");
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Query q = pm.newQuery(Department.class);
        q.setFilter(String.format("name==\"%s\"",s));
        q.setResult("head");
        List results = (List)q.execute();
        results.forEach(System.out::println);
    }

    public static void exercise2c(){
//  select employees_id, employees.name  from employees inner join projects  on projects_employees_id_oid = employees_id where projects.name='WWW'
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter project name to see Employees");
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Query q = pm.newQuery(Employee.class);
        q.setFilter(String.format("projects.contains(proj) && proj.name==\"%s\"",s));
        List results = (List)q.execute();
        results.forEach(System.out::println);
    }

    public static void exercise3a(){
        Scanner input = new Scanner( System.in );
        System.out.println("Enter first and seccond number");
        int f ;
        int l;
        f=input.nextInt();
        l=input.nextInt();
        Query q = pm.newQuery(Employee.class);
        q.setFilter("salary > "+f+" && salary < "+l);
        q.setOrdering("avg(salary)");
        List results = (List)q.execute();
        results.forEach(System.out::println);

    }

    public static void exercise3b(){
        Query averageSalaryQuery = pm.newQuery(Employee.class);
        averageSalaryQuery.setFilter("department!=null");
        averageSalaryQuery.setResult("count(department)");

        Query q = pm.newQuery(Employee.class);
        q.declareVariables("long department1");
        q.declareVariables("long department2");


        q.addSubquery(averageSalaryQuery, "long department1","long department2");

        List results = (List)q.execute();
        results.forEach(System.out::println);

    }

    public static void exercise4a(){
        Query q = pm.newQuery(Department.class);
        q.setFilter("head.department.name!=name");
        List results = (List)q.execute();
        results.forEach(System.out::println);

    }

    public static void exercise4b(){
        Query q1 = pm.newQuery(Employee.class);
        q1.setResult("department");
        List results = (List)q1.execute();

        Query q = pm.newQuery(Department.class,"Arrays.asList(result).contains(department.head)");

        List results2 = (List)q.execute();
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
