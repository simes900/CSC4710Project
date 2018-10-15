package paper;

public class Paper {
	 private int paperid;
     private String title;
	 public String abstracttext;
	public String pdf;
	
	public int getPaperId(){
		return paperid;
	}
	
	public void setPaperId(int paperid){
		this.paperid = paperid;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getAbstractText(){
		return abstracttext;
	}
	
	public String getPdf(){
		return pdf;
	}
	
	public void setTitle(String title){
		this.title =title;
	}
	
	public void setAbstractText(String abstracttext){
		this.abstracttext = abstracttext;
	}
	
	public void setPdf(String pdf){
		this.pdf = pdf;
	}
	
	public String toString(){
		return "Paper { paperid= " + paperid + ", title= " + title+", abstract= "
				+ abstracttext + ", pdf= " + pdf + "}";
	}
}
