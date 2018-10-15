package written;

import java.util.List;


public class WrittenService {
private WrittenFindAll writtenDao = new WrittenFindAll();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return writtenDao.findall();
	}
}
