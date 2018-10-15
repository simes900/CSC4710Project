package review;

import java.util.List;



public class ReviewService {
private ReviewFindAll reviewDao = new ReviewFindAll();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return reviewDao.findall();
	}

}
