package test;

import employee.Department;
import employee.Employee;
import employee.Project;
import org.jfairy.Fairy;
import org.jfairy.producer.company.Company;
import org.jfairy.producer.person.Person;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.*;

public class CreateEmp
{
	private static PersistenceManager pm;
	private static Transaction tx;
	private static Query q;

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

	public static void generateData(){
		Fairy plFairy = Fairy.create(Locale.forLanguageTag("pl"));
		Person pserson = plFairy.person();
		Company company = plFairy.company();
	}

	public static void createObjects(PersistenceManager pm) 	{
		List objects = new LinkedList ();
		Calendar c = Calendar.getInstance();

		Department dept1 = new Department();
		dept1.setLocation("Wroclaw");
		dept1.setName("Oddzial");
		objects.add (dept1);

		Department dept2 = new Department();
		dept2.setLocation("Warszawa");
		dept2.setName("Centrala");
		objects.add (dept2);

		Project proj1 = new Project();
		proj1.setName("FK");
		proj1.setSubject("System finansowo-ksiegowy");
		objects.add (proj1);

		Project proj2 = new Project();
		proj2.setName("WWW");
		proj2.setSubject("Portal korporacyjny");
		objects.add (proj2);

		Project proj3 = new Project();
		proj3.setName("EDI");
		proj3.setSubject("Modul wymiany dokumentów");
		objects.add (proj3);

		Employee emp1 = new Employee();
		emp1.setJob("Dyrektor");
		emp1.setName("Jan Kowalski");
		emp1.setSalary(3400);
		c.set(1967,5,26);
		emp1.setHiredate(c.getTime());
		emp1.setDepartment(dept1);
		dept1.setHead(emp1);
		objects.add (emp1);

		Employee emp2 = new Employee();
		emp2.setJob("Prezes");
		emp2.setName("Jan Nowak");
		emp2.setSalary(7000);
		c.set(1963,3,6);
		emp2.setHiredate(c.getTime());
		emp2.setDepartment(dept2);
		dept2.setHead(emp2);
		objects.add (emp2);

		Employee emp3 = new Employee();
		emp3.setJob("Developer");
		emp3.setName("Adam Iksinski");
		emp3.setSalary(2000);
		c.set(1979,3,5);
		emp3.setHiredate(c.getTime());
		emp3.setDepartment(dept1);
		objects.add (emp3);

		Employee emp4 = new Employee();
		emp4.setJob("Developer");
		emp4.setName("Adam Adamski");
		emp4.setSalary(2500);
		c.set(1980,3,5);
		emp4.setHiredate(c.getTime());
		emp4.setDepartment(dept1);
		objects.add (emp4);

		Employee emp5 = new Employee();
		emp5.setJob("Team Leader");
		emp5.setName("Tomasz Tomaszewski");
		emp5.setSalary(3000);
		c.set(1978,3,5);
		emp5.setHiredate(c.getTime());
		emp5.setDepartment(dept1);
		objects.add (emp5);

		Employee emp6 = new Employee();
		emp6.setJob("Ksiegowa");
		emp6.setName("Ewa Warszawska");
		emp6.setSalary(3500);
		c.set(1972,6,6);
		emp6.setHiredate(c.getTime());
		emp6.setDepartment(dept2);
		objects.add (emp6);

		Employee emp7 = new Employee();
		emp7.setJob("Kadrowa");
		emp7.setName("Ola Olszewska");
		emp7.setSalary(3300);
		c.set(1970,6,6);
		emp7.setHiredate(c.getTime());
		emp7.setDepartment(dept2);
		objects.add (emp7);

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
