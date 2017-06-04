package cc.bblog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.bblog.dao.BaseDao;
import cc.bblog.entity.QueryMapping;
import cc.bblog.entity.ViewModelByPager;
import cc.bblog.service.BaseSrvice;
@Component("baseService")
public class BaseServiceImpl implements BaseSrvice{

	BaseDao baseDao ; 
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public void delete(Object object) {
		baseDao.delete(object);
	}

	@Override
	public void delete(List<?> list) {
		baseDao.delete(list);
	}

	@Override
	public void execute(String hql) {
		baseDao.execute(hql);
	}

	@Override
	public Object query(String hql) {
		return baseDao.query(hql);
	}

	@Override
	public Object save(Object object) {
		return baseDao.save(object);
	}

	@Override
	public boolean update(Object object) {
		return baseDao.update(object);
	}

	@Override
	public boolean saveOrUpdate(Object object) {
		return baseDao.saveOrUpdate(object);
	}

	@Override
	public Object findById(Object object, int id) {
		return baseDao.findById(object, id);
	}

	@Override
	public ViewModelByPager findByQueryMapping(Object object,
			QueryMapping queryMapping, int strip , int pager ,
			Order order) {
		return findByJunction(object, queryMapping.getRestrictions(), strip, pager, order);
	}

	@Override
	public ViewModelByPager findByJunction(Object object, Junction junction,
			int strip , int pager , Order order) {
		ViewModelByPager viewModelByPager = new ViewModelByPager();
		long count = baseDao.count(object, junction);
		if (count<1) {
			viewModelByPager.setNull(true);
		}else {
			viewModelByPager.setMaxPager(maxPager(count,strip));
			viewModelByPager.setPager(pager);
			viewModelByPager.setList(baseDao.findByJunction(object, junction, (pager-1)*strip, strip, order));
			viewModelByPager.setPagers(pagers(pager, viewModelByPager.getMaxPager()));
		}
		return viewModelByPager;
	}

	public List<Integer> pagers(int pager,int max){
		List<Integer> list = new ArrayList<Integer>();
		if (max<=5) {
			for (int i = 1; i <= max; i++) {
				list.add(i);
			}
		}else {
			// ( 1 2 》 3 4 5 ) 6 7 8 9			
			if (pager<3) {
				for (int i = 1; i <= 5; i++) {
					list.add(i);
				}
			}else {
			//  1 2  3 4 (5 6 7 》8 9)
				if (max-pager<3) {
					for (int i = max-4; i <= max; i++) {
						list.add(i);
					}
				}else {
				//  1 2 (3 4 >5 6 7 )8 9
					for (int i = pager-2; i <= pager+2; i++) {
						list.add(i);
					}
				}
			}
					
			
		}
		return list ;
	}
	public int maxPager(long count,int strip){
		if (count%strip==0) {
			return (int) (count/strip);
		}else {
			return (int) (count/strip+1);
		}
	}
	
	@Override
	public long count(Object object, Junction junction) {
		return baseDao.count(object, junction);
	}
}

