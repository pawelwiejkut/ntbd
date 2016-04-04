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
        System.out.println("Enter first and second number");
        int f ;
        int l;
        f=input.nextInt();
        l=input.nextInt();
        Query q = pm.newQuery(Employee.class);
        q.setResult("department, avg(salary)");
        q.setGrouping("department HAVING avg(salary) >= :x && avg(salary) <= :y");
        q.setOrdering("2 desc");
        List results = (List)q.execute(f,l);
        results.forEach(System.out::println);

    }

    public static void exercise3b(){
        Scanner input = new Scanner( System.in );
        System.out.println("Enter first and second number");
        int f ;
        int l;
        f=input.nextInt();
        l=input.nextInt();
        Query q = pm.newQuery(Employee.class);
        q.setResult("department,avg(salary),count(department)");
        q.setGrouping("department HAVING count(department) >= :x && count(department) <= :y");
        q.setOrdering("3 desc");
        List results = (List)q.execute(f,l);
        results.forEach(System.out::println);

    }

    public static void exercise4a(){
        Query q = pm.newQuery(Department.class);
        q.setFilter("head.department.name!=name");
        List results = (List)q.execute();
        results.forEach(System.out::println);

    }

    public static void exercise4b(){
        Query averageSalaryQuery = pm.newQuery(Department.class);

        Query q = pm.newQuery(Employee.class, "department!=depClass");
        q.declareImports("import employee.Department");

        q.declareVariables("Department depClass");
        q.addSubquery(averageSalaryQuery, "Department depClass", null);

        List results = (List)q.execute();
        results.forEach(System.out::println);

    }

    public static void main(String[] args) {
        try {
            pm = JDOHelper.getPersistenceManagerFactory("Tutorial").getPersistenceManager();
            tx = pm.currentTransaction();

            exercise4b();

            pm.close ();
        } catch (Exception e) { e.printStackTrace(); }
    }

}
