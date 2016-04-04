package test;

import employee.*;
import test.DataFactory.impl.DataFactExt;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
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

	public static Address generateAddress(){
		Address address = new Address();
		df = new DataFactExt();
		address.setCity(df.getCity());
		address.setStreet(df.getAddress());
		address.setPostcode(df.getAddressLine2());
		objects.add(address);
		return address;
	}

	public static Task generateTask(){
		Task task = new Task();
		df = new DataFactExt();
		task.setDescription(df.getProjectSubject());
		task.setStartDate(df.getEmployeeHireDate());
		task.setEndDate(df.getBirthDate());
		objects.add(task);
		return task;
	}
	public static void createObjects(PersistenceManager pm) 	{


		for(int i=0;i<50;i++) {
			Employee emp ;
			Employee emp1;
			Department dep;
			Address adr;
			Task task;
			Project proj;
			dep = generateDepartment();
			emp = generateEmployee();
			emp1 = generateEmployee();

			adr = generateAddress();
			task = generateTask();
			proj = generateProject();

			adr.setDepartment(dep);
			proj.addEmployee(emp1);
			proj.addEmployee(emp);
			proj.addTask(task);
			dep.setHead(emp);
			dep.setAddress(adr);

			task.setProject(proj);

			emp.addProject(proj);
			emp1.addProject(proj);
			emp.setDepartment(dep);
			emp1.setDepartment(dep);
		}



		pm.makePersistentAll (objects);
		System.out.println ("Created " + objects.size () + " new objects.");
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

			pm.close ();
		} catch (Exception e) { e.printStackTrace(); }
	}

}
