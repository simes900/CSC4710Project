package author;

import java.util.List;

import author.Author;

public class AuthorService  {
	private AuthorFindAll authorDao = new AuthorFindAll();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return authorDao.findall();
	}
}
