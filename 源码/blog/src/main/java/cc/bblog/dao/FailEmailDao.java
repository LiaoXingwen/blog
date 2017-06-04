package cc.bblog.dao;

import java.util.List;

import cc.bblog.entity.FailSendEmail;

public interface FailEmailDao {
	/**
	 * 查找发送失败的信息
	 * @return
	 */
	List<FailSendEmail> findFailEmails();
	/**
	 * 删除邮件
	 * @param id
	 */
	void deleteEmail(int id );
	/**
	 * 保存
	 */
	void save(FailSendEmail email);
	
	/**
	 * 更新
	 * @param email
	 */
	void update(FailSendEmail email);

}
