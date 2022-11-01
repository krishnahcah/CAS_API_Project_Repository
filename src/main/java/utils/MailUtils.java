package utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailUtils {
    public static String sendSimpleMail(MailProp mailProp) {
        // Recipient's email ID needs to be mentioned.
        String to = "";
        String cc = "";

        if (mailProp.getTo() != null)
            if (mailProp.getTo() != null) {
                to = mailProp.getTo();
            } else {
                return "to can't be empty..!";
            }

        if (mailProp.getCc() != null)
            if (mailProp.getCc() != null) {
                cc = mailProp.getCc();
            }

        // Sender's email ID needs to be mentioned

        Session session = null;
        String from = "colombia.sanityjenkinsadmin@timesinternet.in";
        if (mailProp.getFrom() == null)
            return "username is not present...!";
        if (mailProp.getFromPassWord() == null)
            return "password is not present...!";

        session = getMailSessionObject(mailProp);
        from = "colombia.sanityjenkinsadmin@timesinternet.in";

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            //   message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));

            // Set Subject: header field
            message.setSubject(mailProp.getSubject());

            message.setText(mailProp.getContent());

            // Send message
            Transport.send(message);

            return "Mail Sent Successfully";
        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }

    }


    public static String sendEmailWithTable(MailProp mailProp) {
        // Recipient's email ID needs to be mentioned.
        String to = "";
        String cc = "";

        if (mailProp.getTo() != null)
            to = mailProp.getTo();

        if (mailProp.getCc() != null)
            cc = mailProp.getCc();

        // Sender's email ID needs to be mentioned

        Session session = null;
        String from = "";
        if (mailProp.getFrom() == null)
            return "username is not present...!";
        if (mailProp.getFromPassWord() == null)
            return "password is not present...!";

        session = getMailSessionObject(mailProp);

        from = "colombia.sanityjenkinsadmin@timesinternet.in";

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));


            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));

            // Set Subject: header field
            message.setSubject(mailProp.getSubject());
            // Send the actual HTML message, as big as you like
            if (mailProp.getHtmlTable() != null) {
                if (mailProp.getMailTextForHtmlTable() != null) {
                    message.setContent(mailProp.getMailTextForHtmlTable() + mailProp.getHtmlTable() + "<br><br>Regards,<br>Antriksh Shrivastava<br><br>",
                            "text/html");
                } else
                    message.setContent(mailProp.getHtmlTable(), "text/html");
            } else {
                message.setText("No Content or Table...!\n\n");
            }
            // Send message
            Transport.send(message);

            return "Mail Sent Successfully";
        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }
    }


    public static String sendEmailWithAttachMent2(MailProp mailProp, String filename ) {
        String to = "";
        String cc = "";

        if (mailProp.getTo() != null)
            to = mailProp.getTo();

        if (mailProp.getCc() != null)
            cc = mailProp.getCc();


        Session session = null;
        String from = "";
        if (mailProp.getFrom() == null)
            return "username is not present...!";
        if (mailProp.getFromPassWord() == null)
            return "password is not present...!";

        session = getMailSessionObject(mailProp);


        System.out.println(" session  is = "+ session);

        from = "colombia.sanityjenkinsadmin@timesinternet.in";

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            message.setSubject(mailProp.getSubject());

            BodyPart messageBodyPart1 = new MimeBodyPart();
            // messageBodyPart1.setText("This is message body");
            messageBodyPart1.setText(mailProp.getHtmlTable());

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);

            message.setContent(multipart);

            Transport.send(message);

            return "Mail Sent Successfully";
        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }
    }



    private static Session getMailSessionObject(MailProp mailProp) {

        final String username = "muuekkez";
        final String password = "84yfcjjcz023";
        // Assuming you are sending email through relay.jangosmtp.net
        // String host = "smtp.gmail.com";
        //String host = "192.168.24.21";

        String host = "mum-relay.tilc.in";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

}
