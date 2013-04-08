/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 *
 * @author Nikhil
 */
public class Smtp {

    String  d_email = "icopskerala@gmail.com",
            d_password = "jayaprabha",
            d_host = "smtp.gmail.com",
            d_port  = "465",
            m_to = "",
            m_subject = "",
            m_text = "";

    public void sendmail(String sub,String msgtext,String to)
    {

        m_subject=sub;
        m_text=msgtext;
        m_to=to;
                
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();

        try
        {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            //session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(m_text);
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            Transport.send(msg);
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        }
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        @Override
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(d_email, d_password);
        }
    }

    public static void main(String args[])
    {
        Smtp p=new Smtp();
        p.sendmail("TEST", "HELLO", "nikhilgv9@gmail.com");
    }

}


