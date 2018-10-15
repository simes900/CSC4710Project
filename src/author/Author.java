package author;



public class Author {
	private String email;
	private String lastName;
	private String firstName;
	private String affiliation; 
	
	public String getEmail(){
		return email;
	}
	public String getLastName(){
		return lastName;
		
	}
	public String getFirstName(){
		return firstName;
	}
	public String getAffiliation(){
		return affiliation;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public void setFirstName (String firstName){
		this.firstName = firstName;
	}
	public void setAffiliation(String affiliation){
		this.affiliation = affiliation;
	}
	
	public String toString(){
		return "Author { email= " + email + ", last name= " + lastName+", firstName= "
				+ firstName + ", affiliation= " + affiliation + "}";
	}
}

