package paper;

import java.util.List;



public class PaperService {
	
private PaperFindAll paperDao = new PaperFindAll();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return paperDao.findall();
	}

}
