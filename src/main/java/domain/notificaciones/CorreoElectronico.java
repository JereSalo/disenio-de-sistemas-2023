package domain.notificaciones;

import domain.incidentes.Incidente;
import domain.usuarios.Persona;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CorreoElectronico extends Notificador{
  // Lamentablemente la función de "contraseñas de aplicaciones" en Gmail no está disponible para cuentas sin autenticación de dos factores.
  // Para probar la funcionalidad usé mi cuenta personal. Funciona :)
  @Override
  protected void notificar(Persona persona, String mensaje) {
    try{
      Email email = new SimpleEmail();
      email.setHostName("smtp.googlemail.com");
      email.setSmtpPort(465);
      email.setAuthenticator(new DefaultAuthenticator("remitente@mail.com", "contraseña"));
      email.setSSLOnConnect(true);
      email.setFrom("remitente@mail.com");

      DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
      String fechaFormateada = LocalDateTime.now().format(formato);

      email.setSubject("Notificacion de incidente - " + fechaFormateada);
      email.setMsg(mensaje);
      email.addTo(persona.getUsuario().getMail());

      email.send();
    }
    catch (EmailException e){
      throw new RuntimeException("Error al enviar mail: " + e.getMessage());
    }
  }
}
