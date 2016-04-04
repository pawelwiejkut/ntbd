package employee;

import javax.jdo.annotations.PersistenceCapable;
import java.util.HashSet;
import java.util.Set;

@PersistenceCapable
public class Project
{
    private String name;
    private String subject;
    private Set task;
    private Set employee;

    public String getName()
    {
        return name;
    }

    public void setName(String s)
    {
        name = s;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String s)
    {
        subject = s;
    }

    public Set getTask()
    {
        if( task == null )
        {
            task = new HashSet();
        }
        return task;
    }

    public void addTask(Task task)
    {
        getTask().add(task);
    }

    public void removeTask(Task task)
    {
        getTask().remove(task);
    }

    public Set getEmployee()
    {
        if( employee == null )
        {
            employee = new HashSet();
        }
        return employee;
    }

    public void addEmployee(Employee employee)
    {

        getEmployee().add(employee);
    }

    public void removeEmployee(Employee employee)
    {
        getEmployee().remove(employee);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("name='").append(name).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", tasks=").append(task);
        sb.append(", employee=").append(employee);
        sb.append('}');
        return sb.toString();
    }
}
