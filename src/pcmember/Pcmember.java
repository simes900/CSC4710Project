package pcmember;

public class Pcmember {
	
	private String email;
	private String name;
	
	public String getEmail(){
		return email;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String toString(){
		return "Pcmember { email= " + email + ", name= " + name+"}";
	}

}
