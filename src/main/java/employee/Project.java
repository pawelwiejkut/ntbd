package employee;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Project
{
    private String name;
    private String subject;

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

    public String toString()
    {
        return " [NAME] " + name + " [SUBJECT] " + subject;
    }
}
