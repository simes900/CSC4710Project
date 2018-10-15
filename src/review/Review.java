package review;

public class Review {
	
	private int reportid;
	private String date;
	private String comment;
	private String recommendation;
	private int paperid;
	private String email;
	

	public int getReportid(){
		return reportid;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getComment(){
		return comment;
	}
	
	public String getRecommendation(){
		return recommendation;
	}
	
	public int getPaperid(){
		return paperid;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setReportid(int reportid){
		this.reportid = reportid;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public void setRecommendation(String recommendation){
		this.recommendation = recommendation;
	}
	
	public void setPaperid(int paperid){
		this.paperid = paperid;
	}
	
	
	public void setEmail(String email){
		this.email = email;
	}
	
	
}


