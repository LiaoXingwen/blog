package cc.bblog.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;

import cc.bblog.entity.QueryMapping;


public interface BaseDao {
	/**
	 * 删除
	 * @return
	 */
	public void delete(Object object);
	/**
	 * 删除
	 * @return
	 */
	public void delete(List<?> list);
	
	/**
	 * 执行hql，非查询
	 * @return
	 */
	public void execute(String hql);
	
	/**
	 * 执行hql，非查询
	 * @return
	 */
	public List executeSQL(String sql);


	/**
	 * 执行hql，查询
	 * @return
	 */
	public Object query(String hql);
	
	/**
	 * 保存，必须不存在
	 * @param object
	 * @return
	 */
	public Object save(Object object) ;
	/**
	 * 保存
	 * @param object
	 * @return
	 */
	public void saveAll(Set<?> object) ;
	/**
	 * 更新，必须存在
	 * @param object
	 * @return
	 */
	public boolean update(Object object);
	
	/**
	 * 保存或者更新
	 * @param object
	 * @return
	 */
	public boolean saveOrUpdate(Object object);
	/**
	 * 通过id查询,id不超过int
	 * @param object
	 * @param i
	 * @return
	 */
	public Object findById(Object object , Serializable id );

	/**
	 * 通过查询映射查询
	 * @param object
	 * @param i
	 * @return
	 */
	public List<Object> findByQueryMapping(Object object , QueryMapping queryMapping,int firstResult , int maxResults ,Order order );
	
	/**
	 * 通过查询映射查询
	 * @param object
	 * @param i
	 * @return
	 */
	public List<Object> findByJunction(Object object , Junction junction,int firstResult , int maxResults ,Order order );
	/**
	 * 通过查询映射查询总数
	 * @param object
	 * @param i
	 * @return
	 */
	public long count(Object object , Junction junction );
}
