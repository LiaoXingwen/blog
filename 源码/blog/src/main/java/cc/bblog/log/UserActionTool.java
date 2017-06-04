package cc.bblog.log;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;





import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.bblog.dao.BaseDao;
import cc.bblog.entity.UserAction;

@Service("userActionTool")
public class UserActionTool {

	BaseDao baseDao ; 
	@Resource(name="baseDao")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public static Integer REGISTER = -1, LOGIN = 0 ,VIEWARTICLE = 1,
			VIEWUSERINFO = 2,COMMENT = 3,THUMBS = 4 ,SEARCH = 5,addart=6,delart=7,updateart = 8,pushart=9,follow = 20;
	Set<UserAction> userActions = Collections.synchronizedSet(new HashSet<>());
	public UserActionTool() {
	}
	public void addAction(UserAction userAction) {
		userActions.add(userAction);
		System.out.println(userActions.size());
		//记录动作100条插入一次
		if (userActions.size()>100) {
			insert();
			userActions.clear();
		}
	}
	private void insert() {
		baseDao.saveAll(userActions);
	}

	@Override
	protected void finalize() throws Throwable {
		if (userActions.size()>0) {
			insert();
		}
		super.finalize();
	}
}
