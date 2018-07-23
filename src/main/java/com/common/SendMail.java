package com.common;


/**
 * @author acer
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMail implements Runnable {

    private String to; // 接收方

    private String subject; // 标题

    private String content; // 邮件内容
    private Thread thread; // 定义线程

    private String sname; // 邮件署名

    private List attach;

    private String type = "text/html"; // or text/plain 发送邮件的格式

    private String smtpHost; // 邮件服务器 smtp.163.com等
    private String emailFrom; // 发送方邮箱
    private String fromPwd; // 密码

    private boolean isattachment = false;
    private String attachmentPath;

    // 带模板的邮件
    // public SendMail(String to, String subject, String content, String
    // fileName) {
    // try {
    // if (to != null && !"".equals(to)) {
    // thread = new Thread(this);
    // this.to = to;
    // this.subject = subject;
    // attach = new ArrayList();
    // // 读取文件模板
    // File file = new File(fileName);
    // BufferedReader br = new BufferedReader(new FileReader(file));
    //
    // StringBuffer sb = new StringBuffer();
    // while (br.ready()) {
    // sb.append(br.readLine());
    // sb.append("\r\n");
    // }
    // // 賛换模块中的##content变量内容为content
    // this.content = sb.toString().replaceAll("##content", content);
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    // 带上署名和模块的邮件
    public SendMail(String sname, String to, String subject, String content,
                    String fileName) {
        try {
            thread = new Thread(this);
            this.to = to;
            this.subject = subject;
            this.sname = sname;
            attach = new ArrayList();

            // 读取文件模板

            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuffer sb = new StringBuffer();
            while (br.ready()) {
                sb.append(br.readLine());
            }
            this.content = sb.toString().replaceAll("##caicainet_content",
                    content);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public SendMail(String sname, String to, String subject, String content) {
        thread = new Thread(this);
        this.to = to;
        this.subject = subject;
        this.sname = sname;
        attach = new ArrayList();
        this.content = content;
        //this.type = "text/plain";
    }

    public SendMail(String sname, String to, String subject, String content,
                    boolean isattachment, String attachmentPath) {
        thread = new Thread(this);
        this.to = to;
        this.subject = subject;
        this.sname = sname;
        attach = new ArrayList();
        this.content = content;
        this.type = "text/plain";
        this.isattachment = isattachment;
        this.attachmentPath = attachmentPath;
    }

    public void run() {
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        thread.start();
    }

    public boolean send() throws Exception {
        if (isattachment) {
            return send(to, subject, content, attachmentPath);
        } else {
            return send(to, subject, content);
        }
    }

    private boolean send(String to, String subject, String content, String attachmentPath) {
        boolean blnResult = false;
        InternetAddress[] address = null;
        // mailserver邮件服务器 Form 发送邮件的邮箱 pwd密码
        // 此处三个参数可能通过数据库或.properties等方式来获取，方便后期的管理与设置
        String mailserver = BookingConfig.getInstance().getValue("mailserver");
        String From = BookingConfig.getInstance().getValue("from");// 发送方的邮箱
        String pwd = BookingConfig.getInstance().getValue("pwd");// 密码

//		String mailserver = "smtp.163.com";
//		String From = "schtdy@163.com";// 发送方的邮箱
//		String pwd = "wsnschtdy";// 密码


        if (smtpHost != null && !"".equals(smtpHost))
            mailserver = smtpHost;
        if (emailFrom != null && !"".equals(emailFrom))
            From = emailFrom;
        if (fromPwd != null && !"".equals(fromPwd))
            pwd = fromPwd;

        if (smtpHost != null && "nopass".equals(smtpHost)) {
            smtpHost = "";
            System.out.println("发送给" + to + "失败！原因是smtp地址不正确");
            return false;
        }

        String messageText = content;
        boolean sessionDebug = false;
        Date d1 = new Date();
        try {
            // 设定所要用的Mail 服务器和所使用的传输协议
            java.util.Properties props = System.getProperties();
            props.put("mail.host", mailserver);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");// 指定是否需要SMTP验证
            // 产生新的Session 服务
            javax.mail.Session mailSession = javax.mail.Session
                    .getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            if (sname != null && !"".equals(sname)) {
                // 设定发邮件的人
                msg.setFrom(new InternetAddress(From, sname));
            } else {
                // 带署名的邮件
                msg.setFrom(new InternetAddress(From, "刘海洋"));
            }

            if (sname != null && "mail".equals(sname)) {
                msg.setFrom(new InternetAddress(From, ""));
            }

            // 设定收信人的信箱
            address = InternetAddress.parse(to, false);
            msg.setRecipients(Message.RecipientType.TO, address);
            // 设定信中的主题
            msg.setSubject(subject);
            // 设定送信的时间
            msg.setSentDate(new Date());
            //内容
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            if ("text/html".equals(this.type)) {
                messageBodyPart.setText(messageText, type + ";charset=GB2312");
            } else {
                messageBodyPart.setText(messageText);
            }
            multipart.addBodyPart(messageBodyPart);
            //附件
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(MimeUtility.encodeText(source.getName()));
            multipart.addBodyPart(messageBodyPart);

            msg.setContent(multipart);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(mailserver, From, pwd);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            Date d2 = new Date();
            System.out.println("发送给" + to + "成功！耗时"
                    + (d2.getTime() - d1.getTime()) + "毫秒,发送方：" + From);
            blnResult = true;

        } catch (MessagingException mex) {
            mex.printStackTrace();
            blnResult = false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return blnResult;
    }

    private boolean send(String to, String subject, String content) {
        boolean blnResult = false;
        InternetAddress[] address = null;
        // mailserver邮件服务器 Form 发送邮件的邮箱 pwd密码
        // 此处三个参数可能通过数据库或.properties等方式来获取，方便后期的管理与设置
        String mailserver = BookingConfig.getInstance().getValue("mailserver");
        String From = BookingConfig.getInstance().getValue("from");// 发送方的邮箱
        String pwd = BookingConfig.getInstance().getValue("pwd");// 密码

//		String mailserver = "smtp.163.com";
//		String From = "kjksic1@163.com";// 发送方的邮箱
//		String pwd = "msmzHc_821012";// 密码

        if (smtpHost != null && !"".equals(smtpHost))
            mailserver = smtpHost;
        if (emailFrom != null && !"".equals(emailFrom))
            From = emailFrom;
        if (fromPwd != null && !"".equals(fromPwd))
            pwd = fromPwd;

        if (smtpHost != null && "nopass".equals(smtpHost)) {
            smtpHost = "";
            System.out.println("发送给" + to + "失败！原因是smtp地址不正确");
            return false;
        }

        String messageText = content;
        boolean sessionDebug = false;
        Date d1 = new Date();
        try {
            // 设定所要用的Mail 服务器和所使用的传输协议
            java.util.Properties props = System.getProperties();
            props.put("mail.host", mailserver);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");// 指定是否需要SMTP验证
            // 产生新的Session 服务
            javax.mail.Session mailSession = javax.mail.Session
                    .getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            if (sname != null && !"".equals(sname)) {
                // 设定发邮件的人
                msg.setFrom(new InternetAddress(From, sname));
            } else {
                // 带署名的邮件
                msg.setFrom(new InternetAddress(From, "刘海洋"));
            }

            if (sname != null && "mail".equals(sname)) {
                msg.setFrom(new InternetAddress(From, ""));
            }

            // 设定收信人的信箱
            address = InternetAddress.parse(to, false);
            msg.setRecipients(Message.RecipientType.TO, address);
            // 设定信中的主题
            msg.setSubject(subject);
            // 设定送信的时间
            msg.setSentDate(new Date());
            if ("text/html".equals(this.type)) {
                msg.setContent(messageText, type + ";charset=GB2312");
            } else {
                msg.setText(messageText);
            }

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(mailserver, From, pwd);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            Date d2 = new Date();
            System.out.println("发送给" + to + "成功！耗时"
                    + (d2.getTime() - d1.getTime()) + "毫秒,发送方：" + From);
            blnResult = true;

        } catch (MessagingException mex) {
            mex.printStackTrace();
            blnResult = false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return blnResult;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void addAttach(String file) {
        attach.add(file);
    }

    public boolean isAttach() {
        return attach != null && attach.size() > 0;
    }

    public static void main(String[] args) {
        try {
            // 普通的文字邮件
            SendMail sendMail = new SendMail("王大河", "hc7624736@163.com", "试试",
                    "没时间那", true, "D://susfuse.sql");

            // 普通的文字邮件
//			SendMail sendMail = new SendMail("王大河", "hc7624736@163.com", "试试",
//					"没时间那");

            sendMail.start();

            // //带模版的邮件
            // String path = "D:/project/tongji/WebRoot/template/email.html";
            // //获取存放路径
            //
            // String chtml="";
            // SendMail sendMail1=new
            // SendMail("hc7624736@163.com","测试",chtml,path);
            // sendMail.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}