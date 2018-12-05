package myCompany;

public class Entity {
	private int Id;
	private String firstName;
	private String lastName;
	
	public Entity() {
		
	}
	
	public Entity(int Id, String firstName, String lastName) {
		this.Id = Id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void setId(int Id) {
		this.Id = Id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getId() {
		return this.Id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String toString() {
		return new String(Integer.toString(this.Id) + "\n" + this.firstName + "\n" + this.lastName);
	}
	
//	public boolean equals(Object o) {
//		if(o==null || o.getClass()!=this.getClass())
//			return false;
//		else {
//			Entity e = (Entity)o;
//			return this.getId()==e.getId() && this.getFirstName().equals(e.getFirstName()) && this.getLastName().equals(e.getLastName());
//		}		
//	}
}
