package employee;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Department
{
	private String name;
	private String location;
	private Employee head;

	public String getLocation()
	{
		return location;
	}

	public String getName()
	{
		return name;
	}

	public Employee getHead()
	{
		return head;
	}

	public void setLocation(String s)
	{
		location = s;
	}

	public void setName(String s)
	{
		name = s;
	}

	public void setHead(Employee h)
	{
		head = h;
	}

	public String toString()
	{
		return " [NAME] " + name + " [LOCATION] " + location;
	}
}
