package employee;


import javax.jdo.annotations.PersistenceCapable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@PersistenceCapable
public class Employee {
	private String name;
	private Date hiredate;
	private String job;
	private double salary;
	private Department department;
	private Set project;

	public Department getDepartment() {
		return department;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public String getJob() {
		return job;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setHiredate(Date date) {
		hiredate = date;
	}

	public void setJob(String string) {
		job = string;
	}

	public void setName(String string) {
		name = string;
	}

	public void setSalary(double d) {
		salary = d;
	}

	public Set getProject() {
		if (project == null) {
			project = new HashSet();
		}
		return project;
	}

	public void addProject(Project project) {
		getProject().add(project);
	}

	public void removeProject(Project project) {
		getProject().remove(project);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Employee{");
		sb.append("name='").append(name).append('\'');
		sb.append(", hiredate=").append(hiredate);
		sb.append(", job='").append(job).append('\'');
		sb.append(", salary=").append(salary);
		sb.append(", department=").append(department);
		sb.append(", project=").append(project);
		sb.append('}');
		return sb.toString();
	}
}
