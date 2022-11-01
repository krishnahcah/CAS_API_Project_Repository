package utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class AlertUtils {

    public static void writeAllContentTofile(String FileName, String Content) {
        FileWriter fileWriter = null;
        try {
            File test = new File(FileName);
            fileWriter = new FileWriter(test);
//            fileWriter.append(FileHeader + System.lineSeparator());
            fileWriter.append(Content);
//			System.out.println("CSV file was created successfully !!!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!" + e);
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
            }
        }
    }

    public static void sendSimpleMail( String content,String mailSubject,String toMail) {
        MailProp mp = new MailProp();
        mp.setFrom("muuekkez");
        mp.setFromPassWord("84yfcjjcz023");
        mp.setSubject(mailSubject);
        mp.setTo(toMail);
        mp.setContent(content);
        System.out.println(MailUtils.sendSimpleMail(mp));
    }

    public static void sendTabularMail(String content,String subject,String mailTo) {
        MailProp mp = new MailProp();
        mp.setFrom("muuekkez");
        mp.setFromPassWord("84yfcjjcz023");

        mp.setSubject(subject);
        mp.setTo(mailTo);
        // mp.setTo("raghvendra.r@timesinternet.in");
        //mp.setTo("raghvendra.r@timesinternet.in,vineet.gupta@timesinternet.in,amit.kumar6@timesinternet.in,diganta.das@timesinternet.in,deeksha.aneja@timesinternet.in,mohd.sufiyan@timesinternet.in,Colombia.Analytics@timesinternet.in,atishaya.jain@timesinternet.in,adtech.colombiaproducts@timesinternet.in,adtech.colombialeads@timesinternet.in");
        mp.setHtmlTable(content);
        System.out.println(MailUtils.sendEmailWithTable(mp));
    }


    public static void sendTabularMail(String content,String subject,String mailFrom,String mailTo) {
        MailProp mp = new MailProp();
        mp.setFrom(mailFrom);
        mp.setFromPassWord("");
        mp.setSubject(subject);
        mp.setTo(mailTo);
        // mp.setTo("raghvendra.r@timesinternet.in");
        //mp.setTo("raghvendra.r@timesinternet.in,vineet.gupta@timesinternet.in,amit.kumar6@timesinternet.in,diganta.das@timesinternet.in,deeksha.aneja@timesinternet.in,mohd.sufiyan@timesinternet.in,Colombia.Analytics@timesinternet.in,atishaya.jain@timesinternet.in,adtech.colombiaproducts@timesinternet.in,adtech.colombialeads@timesinternet.in");
        mp.setHtmlTable(content);
        System.out.println(MailUtils.sendEmailWithTable(mp));
    }

    private static Session getMailSessionObject(MailProp mailProp) {
        final String username = mailProp.getFrom();
        final String password = mailProp.getFromPassWord();
        // Assuming you are sending email through relay.jangosmtp.net
        // String host = "smtp.gmail.com";
        String host = "192.168.24.21";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
        // Get the Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    public static void simplemail(String content,String subject,String mailTo) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("akshaysaini1317@gmail.com", "sainiboy@123"));
            email.setSSLOnConnect(true);
            email.setFrom("akshaysaini1317@gmail.com");
            email.setSubject(subject);
            email.setMsg(content);
            email.addTo(mailTo);
            email.send();
        }catch (EmailException e){

        };
    }

    public static String getSystemTimeStamp() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public static void main(String[] args) throws EmailException {
        try {


            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("akshaysaini1317@gmail.com", "sainiboy@123"));
            email.setSSLOnConnect(true);
            email.setFrom("akshaysaini1317@gmail.com");
            email.setSubject("subject");
            email.setMsg("content");
            email.addTo("ravizigzak@gmail.com");
            email.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }
}
