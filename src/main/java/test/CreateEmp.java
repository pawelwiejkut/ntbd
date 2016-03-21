package test;

import employee.Department;
import employee.Employee;
import employee.Project;
import test.DataFactory.impl.DataFactExt;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class CreateEmp
{
	private static PersistenceManager pm;
	private static Transaction tx;
	private static Query q;
	private static DataFactExt df ;
	private static List objects = new LinkedList ();


	public static void deleteObjects (Collection objects, PersistenceManager pm) 	{
		pm.deletePersistentAll (objects);
		System.out.println ("Deleted " + objects.size () + " objects.");
	}

	public static void deleteObjects(PersistenceManager pm) 	{
		q = pm.newQuery(Project.class);
		Collection res1 = (Collection) q.execute();
		deleteObjects(res1,pm);
		q = pm.newQuery(Department.class);
		Collection res2 = (Collection) q.execute();
		for (Iterator i = res2.iterator(); i.hasNext();) {
			Department d = (Department)(i.next());
			d.setHead(null);
		}
		q = pm.newQuery(Employee.class);
		Collection res3 = (Collection) q.execute();
		deleteObjects(res3,pm);
		deleteObjects(res2,pm);
	}

	public static Employee generateEmployee(){
		Employee employee = new Employee();
		df = new DataFactExt();
		employee.setName(df.getEmployeeName());
		employee.setJob(df.getEmployeeJob());
		employee.setSalary(df.getEmployeeSalary());
		employee.setHiredate(df.getEmployeeHireDate());
		objects.add(employee);
		return employee;
	}

	public static Department generateDepartment(){
		Department department = new Department();
		df = new DataFactExt();
		department.setName(df.getDepartmentName());
		department.setLocation(df.getDepartmentLocation());
		objects.add(department);
		return department;
	}

	public static Project generateProject(){
		Project project = new Project();
		df = new DataFactExt();
		project.setName(df.getProjectName());
		project.setSubject(df.getProjectSubject());
		objects.add(project);
		return project;
	}

	public static void createObjects(PersistenceManager pm) 	{


		for(int i=0;i<50;i++) {
			Employee emp ;
			Department dep;
			dep = generateDepartment();
			emp = generateEmployee();
			emp.addProject(generateProject());
			emp.addProject(generateProject());
			generateEmployee();
			emp.setDepartment(dep);
			dep.setHead(emp);
		}

		pm.makePersistentAll (objects);
		System.out.println ("Created " + objects.size () + " new objects.");
	}

//	Lista 3 zad 1
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
	List<Project> results = (List<Project>) q.execute(20);
	Iterator<Project> iter = results.iterator();

	while (iter.hasNext()) {
		Project p = iter.next();
		System.out.println(">  " + p);

	}

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
		q.setFilter("name.matches(\"\\\\BW|W\\\\B\")");
		List results = (List)q.execute();
		Iterator<Project> iter = results.iterator();

		while (iter.hasNext()) {
			Project p = iter.next();
			System.out.println(">  " + p);

		}

	}

	public static void main(String[] args) {
		try {
			pm = JDOHelper.getPersistenceManagerFactory("Tutorial").getPersistenceManager();
			tx = pm.currentTransaction();

			tx.begin ();
			deleteObjects(pm);
			tx.commit();

			tx.begin ();
			createObjects(pm);
			tx.commit();

//			firstLetQuery();
			letInQuery();
			pm.close ();
		} catch (Exception e) { e.printStackTrace(); }
	}

}
