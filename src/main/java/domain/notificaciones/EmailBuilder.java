package domain.notificaciones;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailBuilder {
  private final Email email;

  public EmailBuilder() {
    email = new SimpleEmail();
  }

  public EmailBuilder withHostName(String hostName) {
    email.setHostName(hostName);
    return this;
  }

  public EmailBuilder withSmtpPort(int smtpPort) {
    email.setSmtpPort(smtpPort);
    return this;
  }

  public EmailBuilder withAuthenticator(String userName, String password) {
    email.setAuthenticator(new DefaultAuthenticator(userName, password));
    return this;
  }

  public EmailBuilder withSSLOnConnect(boolean sslOnConnect) {
    email.setSSLOnConnect(sslOnConnect);
    return this;
  }

  public EmailBuilder withSender(String sender) {
    try {
      email.setFrom(sender);
    } catch (Exception e) {
      throw new RuntimeException("Error al establecer el remitente del mail: " + e.getMessage());
    }
    return this;
  }

  public EmailBuilder withSubject(String subject) {
    email.setSubject(subject);
    return this;
  }

  public EmailBuilder withMessage(String message) {
    try{
      email.setMsg(message);
    }
    catch (Exception e){
      throw new RuntimeException("Error al establecer el mensaje del mail: " + e.getMessage());
    }

    return this;
  }

  public EmailBuilder withRecipient(String recipient){
    try {
      email.addTo(recipient);
    } catch (EmailException e) {
      throw new RuntimeException("Error al agregar el destinatario al mail: " + e.getMessage());
    }
    return this;
  }

  public Email build() {
    return email;
  }
}
