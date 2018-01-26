package logger;


import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AppenderEMAIL extends AppenderSkeleton {


    @Override
    protected void append(LoggingEvent event) {
        final String username = "onl.ont88@gmail.com";
        final String password = "hjvfirf88";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }

        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("onl.ont88@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("fox.ontour@yandex.ru"));
            message.setSubject("Testing Subject");
            message.setText(layout.format(event));

            System.out.println("получили");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
