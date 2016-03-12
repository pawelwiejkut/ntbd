package employee;

import org.h2.command.dml.Set;

import javax.jdo.annotations.PersistenceCapable;
import java.util.Date;


@PersistenceCapable
public class Employee
{
	private String name;
	private Date hiredate;
	private String job;
	private double salary;
	private Department department;
	private Set projects;

	public Department getDepartment()
	{
		return department;
	}

	public Date getHiredate()
	{
		return hiredate;
	}

	public String getJob()
	{
		return job;
	}

	public String getName()
	{
		return name;
	}

	public double getSalary()
	{
		return salary;
	}

	public void setDepartment(Department department)
	{
		this.department = department;
	}

	public void setHiredate(Date date)
	{
		hiredate = date;
	}

	public void setJob(String string)
	{
		job = string;
	}

	public void setName(String string)
	{
		name = string;
	}

	public void setSalary(double d)
	{
		salary = d;
	}

//	public Set getProjects()
//	{
//		if( projects == null )
//		{
//			projects = new HashSet();
//		}
//		return projects;
//	}
//
//	public void addProject(Project project)
//	{
//		getProjects().add(project);
//	}
//
//	public void removeProject(Project project)
//	{
//		getProjects().remove(project);
//	}
//
//	public Employee(String name, Date hiredate, String job, double salary, Department department ) {
//		this.name = name;
//		this.hiredate = hiredate;
//		this.job = job;
//		this.salary = salary;
//		this.department = department;
//
//	}

	public String toString()
	{
		return " [NAME] "
				+ name
				+ " [DEPARTMENT] "
				+ department
				+ " [HIREDATE] "
				+ hiredate
				+ " [JOB] "
				+ job
				+ " [SALARY] "
				+ salary;
	}
}
