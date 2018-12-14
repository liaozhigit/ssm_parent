package cn.liaozhi.mail.service;

import cn.liaozhi.mail.MailVO;

/**
 *  邮件服务，支持基于模板发送和非模板发送
 * @author liaozhi
 *2018年11月18日下午11:51:28
 */
public interface IMailService {
	void sendMail(MailVO newMail) throws Exception;
}
