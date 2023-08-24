package mvc.model;
public class Person {
	private String firstname, lastname;
	private int age;
	public Person(String firstname, String lastname, int age) {
		this.firstname = firstname != null && !firstname.isBlank() ? firstname.strip() : "(firstname)";
		this.lastname = lastname != null && !lastname.isBlank() ? lastname.strip() : "(lastname)";
		this.age = age > 0 ? age : 0;
	}
	public Person(Person p) {
		this(p.firstname, p.lastname, p.age);
	}
	public void set(Person p) {
		setFirstname(p.firstname);
		setLastname(p.lastname);
		setAge(age);
	}
	public void setFirstname(String firstname) {
		if(firstname != null && !firstname.isBlank())
			this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		if(lastname != null && !lastname.isBlank())
			this.lastname = lastname;
	}
	public void setAge(int age) {
		if(age > 0)
			this.age = age;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return
		"""
		{
			"firstname": "%s",
			"lastname": "%s",
			"age": %d
		}		
		"""
		.formatted(firstname,lastname,age)
		.strip();
	}
}