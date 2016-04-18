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

	public static Applicant generateApplicant(){
		Applicant applicant= new Applicant();
		df = new DataFactExt();
		applicant.setName(df.getName());
		applicant.setPosition(df.getEmployeeJob());
		applicant.setApplicationdate(df.getBirthDate());
		objects.add(applicant);
		return applicant;
	}



	public static void createObjects(PersistenceManager pm) 	{


		for(int i=0;i<50;i++) {
			Employee emp ;
			Employee emp1;
			Department dep;
			Address adr;
			Address adr1;
			Address adr3;
			Address adr4;
			Task task;
			Task task1;
			Project proj;
			Project proj1;
			Applicant applic;

			dep = generateDepartment();
			emp = generateEmployee();
			emp1 = generateEmployee();

			adr = generateAddress();
			adr1=generateAddress();
			task = generateTask();
			proj = generateProject();
			proj1 = generateProject();
			adr3 = generateAddress();
			adr4=generateAddress();
			task1=generateTask();

			applic = generateApplicant();


			adr.setDepartment(dep);
			proj.addEmployee(emp1);
			proj.addEmployee(emp);
			proj.addTask(task);
			proj1.addEmployee(emp);
			proj1.addEmployee(emp1);
			proj1.addTask(task1);
			dep.setHead(emp);
			dep.setAddress(adr);

			emp.setAddress(adr3);
			adr3.setPerson(emp);

			applic.setAddress(adr1);
			adr1.setPerson(applic);

			task.setProject(proj);
			task1.setProject(proj1);

			emp.addProject(proj);
			emp1.addProject(proj1);
			emp.setDepartment(dep);
			emp1.setDepartment(dep);
			emp1.setAddress(adr4);
			adr4.setPerson(emp1);
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
