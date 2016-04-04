package employee;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Department
{
	private String name;

	private Employee head;

	private Address address;

	public String getName()
	{
		return name;
	}

	public Employee getHead()
	{
		return head;
	}

	public void setName(String s)
	{
		name = s;
	}

	public void setHead(Employee h)
	{
		head = h;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Department{");
		sb.append("name='").append(name).append('\'');
		sb.append(", head=").append(head);
		sb.append(", address=").append(address);
		sb.append('}');
		return sb.toString();
	}
}
