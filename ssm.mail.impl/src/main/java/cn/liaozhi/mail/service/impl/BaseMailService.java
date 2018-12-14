package cn.liaozhi.mail.service.impl;

import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.liaozhi.mail.MailVO;
import cn.liaozhi.mail.service.IMailService;
import cn.liaozhi.mail.service.impl.internal.MailConfigBean;
import cn.liaozhi.ssm.core.util.StringUtil;

/**
 * 基础邮件服务
 * 
 * @author liaozhi 2018年11月18日下午11:56:46
 */
@Service
public class BaseMailService implements IMailService {
	
	/**
	 * 邮件配置
	 */
	private MailConfigBean mailConfig;
	/**
	 * 邮件发送器
	 */
	private JavaMailSenderImpl mailSender;

	public void sendMail2(MailVO newMail) throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper =null;
		
		messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF_8");   
		messageHelper.setFrom(mailConfig.getFromAddress(),mailConfig.getFromName());
		 messageHelper.getMimeMessage();
		// 发送邮件
		try {
			mailSender.send(mimeMessage);
		} catch (MailException e) {
			e.printStackTrace();
		}

	}

	
	private void setSubjectAndText(MimeMessageHelper messageHelper, MailVO newMail) throws Exception{		
		if(StringUtil.isNullOrEmpty(newMail.getTemplateName())){
			//无模板发送，直接设置
			messageHelper.setText(newMail.getContent(),true);
			messageHelper.setSubject( insertEnvTag(newMail.getSubject()));			
			return;
		} 
	}
	
	/**
	 * 附加环境标识，在测试环境的邮件中自动追加类似于[Test][From SIT]之类的前缀，以便于用户识别
	 * @param subject 原始标题
	 * @return
	 */
	private String insertEnvTag(String subject) {
//		if("production".equals(EnvHolder.getEnv())){
//			return subject;
//		}
		return MessageFormat.format("[Test][{0}]{1}", "SSM_MAIL",subject);
	}
	
	private MimeMessageHelper getMimeMessageHelper(MailVO newMail)throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper =null;
		
		messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF_8");   
		messageHelper.setFrom(mailConfig.getFromAddress(),mailConfig.getFromName());
		//  设置主题
		setSubjectAndText(messageHelper, newMail);
		//	setAttachments(messageHelper, newMail);
		// 设置邮件重要性[1=High|0=Normal|5=Low]
		//  messageHelper.setPriority(newMail.getPriority());
        
		return messageHelper;
	}
	
	
	
	public  void sendMail(MailVO newMail)throws Exception{
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 设置收件人，寄件人 用数组发送多个邮件
		 String[] to = new String[] {"18682146878@qq.com","liaozhiiphone@icloud.com"};
		mailMessage.setTo(to);
		mailMessage.setFrom("18682146878@qq.com");
		mailMessage.setSubject(newMail.getSubject());
		mailMessage.setText(newMail.getContent());
		// 发送邮件
		try {
			mailSender.send(mailMessage);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}
	
	public void setMailConfig(MailConfigBean mailConfig) {
		this.mailConfig = mailConfig;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

}
