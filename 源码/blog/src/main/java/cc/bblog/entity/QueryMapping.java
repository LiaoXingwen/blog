package cc.bblog.entity;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Junction.Nature;

/**   
 *   适用于复杂的查询 
 * 类名称：QueryMapping   
 * 类描述：   
 * 创建人：廖兴文
 * 创建时间：2017年5月22日 下午8:27:57   
 * 修改人：lxw   
 * 修改时间：2017年5月22日 下午8:27:57   
 * 修改备注：   
 * @version 1.0.0   
 *    
 */
public class QueryMapping {
	Map<String, Object> eqMap = new HashMap<String, Object>();
	Map<String, Object> notEqMap = new HashMap<String, Object>();
	Map<String, Object> likeMap = new HashMap<String, Object>();
	Map<String, Object> notLikeMap = new HashMap<String, Object>();
	Nature eqNature, notEqNature, likeNature, nolikeNature,nature;
	/**
	 * 默认：除like模糊查询为or关系，其他默认and
	 */
	public QueryMapping(Nature nature) {
		this.nature = nature ; 
		eqNature = Nature.AND;
		notEqNature = Nature.AND;
		likeNature = Nature.OR ; 
		nolikeNature = Nature.AND ; 
	}

	/**
	 * 各逻辑以或逻辑封装
	 * @return
	 */
	public Junction getRestrictions(){
		Junction junction ;
		if (nature.equals(Nature.OR)) {
			junction = Restrictions.disjunction();
		}else {
			junction = Restrictions.conjunction();
		}
		junction.add(getEqJunction(eqNature));
		junction.add(getNotEqJunction(notEqNature));
		junction.add(getLikeJunction(likeNature));
		junction.add(getNotLikeJunction(nolikeNature));
		return junction; 
	}

	/**
	 * @param nature
	 * @return
	 */
	public Junction getEqJunction(Nature nature) {
		Junction junction ;
		if (nature.equals(Nature.OR)) {
			junction = Restrictions.disjunction();
		}else {
			junction = Restrictions.conjunction();
		}
		for (String key:eqMap.keySet()) {
			if ( eqMap.get(key)==null) {
				junction.add( Restrictions.isNull(key));
			}else {
				junction.add( Restrictions.eq(key, eqMap.get(key))) ;
			}
		}
		return junction;
	}

	/**
	 * @param nature
	 * @return
	 */
	public Junction getNotEqJunction(Nature nature) {
		Junction junction ;
		if (nature.equals(Nature.OR)) {
			junction = Restrictions.disjunction();
		}else {
			junction = Restrictions.conjunction();
		}
		for (String key:notEqMap.keySet()) {
			if ( notEqMap.get(key)==null) {
				junction.add( Restrictions.isNotNull(key));
			}else {
				junction.add( Restrictions.not(Restrictions.eq(key, notEqMap.get(key)))) ;
			}
		}
		return junction;
	}

	/**
	 * @param nature
	 * @return
	 */
	public Junction getNotLikeJunction(Nature nature) {
		Junction junction ;
		if (nature.equals(Nature.OR)) {
			junction = Restrictions.disjunction();
		}else {
			junction = Restrictions.conjunction();
		}
		for (String key:notLikeMap.keySet()) {
			if ( notLikeMap.get(key)==null) {continue;}
			junction.add( Restrictions.not(Restrictions.like(key, notLikeMap.get(key)))) ;
		}
		return junction;
	}

	/**
	 * @param nature
	 * @return
	 */
	public Junction getLikeJunction(Nature nature) {
		Junction junction ;
		if (nature.equals(Nature.OR)) {
			junction = Restrictions.disjunction();
		}else {
			junction = Restrictions.conjunction();
		}
		for (String key:likeMap.keySet()) {
			if ( likeMap.get(key)==null) {continue;}
			junction.add(Restrictions.like(key, likeMap.get(key))) ;
		}
		return junction;
	}
	/**
	 * 添加查询相等关系
	 * @param propertyName 变量命，非sql字段名
	 * @param value 值
	 */
	public void addEq(String propertyName,Object value) {
		eqMap.put(propertyName, value);
	}
	/**
	 * 移除字段的相等关系
	 * @param propertyName
	 */
	public void removeEq(String propertyName) {
		eqMap.remove(propertyName);
	}
	/**
	 * 清除所有相等关系
	 */
	public void clearEq() {
		eqMap.clear();
	}
	/**
	 * 添加查询不相等关系
	 * @param propertyName 变量命，非sql字段名
	 * @param value 值
	 */
	public void addNotEq(String propertyName,Object value) {
		notEqMap.put(propertyName, value);
	}
	/**
	 * 移除字段的不相等关系
	 * @param propertyName
	 */
	public void removeNotEq(String propertyName) {
		notEqMap.remove(propertyName);
	}
	/**
	 * 清除所有不相等关系
	 */
	public void clearNotEq() {
		notEqMap.clear();
	}

	/**
	 * 添加字段的模糊查询关系
	 * @param propertyName 变量命，非sql字段名
	 * @param value 值
	 */
	public void addLike(String propertyName,Object value) {
		likeMap.put(propertyName, value);
	}
	/**
	 * 移除字段的模糊查询关系
	 * @param propertyName
	 */
	public void removeLike(String propertyName) {
		likeMap.remove(propertyName);
	}
	/**
	 * 清除所有模糊查询关系
	 */
	public void clearLike() {
		likeMap.clear();
	}

	/**
	 * 添加查询不相等关系
	 * @param propertyName 变量命，非sql字段名
	 * @param value 值
	 */
	public void addNotLike(String propertyName,Object value) {
		notLikeMap.put(propertyName, value);
	}
	/**
	 * 移除字段的不相等关系
	 * @param propertyName
	 */
	public void removeNotLike(String propertyName) {
		notLikeMap.remove(propertyName);
	}
	/**
	 * 清除所有不相等关系
	 */
	public void clearNotLike() {
		notLikeMap.clear();
	}

	public void setEqNature(Nature eqNature) {
		this.eqNature = eqNature;
	}

	public void setNotEqNature(Nature notEqNature) {
		this.notEqNature = notEqNature;
	}

	public void setLikeNature(Nature likeNature) {
		this.likeNature = likeNature;
	}

	public void setNolikeNature(Nature nolikeNature) {
		this.nolikeNature = nolikeNature;
	}
}
