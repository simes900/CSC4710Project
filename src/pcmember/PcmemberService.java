package pcmember;

import java.util.List;



public class PcmemberService {
	private PcmemberFindAll pcmemberDao= new PcmemberFindAll();
	
	public List<Object> findall() throws InstantiationException,IllegalAccessException,ClassNotFoundException
	{
		return pcmemberDao.findall();
	}
}
