package domain.notificaciones.notificador;

import domain.usuarios.Persona;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CorreoElectronico extends Notificador{
  // Singleton
  private static CorreoElectronico instancia = null;

  private CorreoElectronico() {}

  public static CorreoElectronico obtenerInstancia() {
    if (instancia == null) {
      instancia = new CorreoElectronico();
    }
    return instancia;
  }


  // Lamentablemente la función de "contraseñas de aplicaciones" en Gmail no está disponible para cuentas sin autenticación de dos factores.
  // Para probar la funcionalidad usé mi cuenta personal. Funciona :)
  @Override
  protected void notificar(Persona persona, String mensaje) {
    Email email = this.construirEmail(persona, mensaje);
    try{
      email.send();
    }
    catch (EmailException e){
      throw new RuntimeException("Error al enviar mail: " + e.getMessage());
    }
  }

  private Email construirEmail(Persona persona, String mensaje){
    Email email = new EmailBuilder()
        .withHostName(ConfiguracionCorreoElectronico.host)
        .withSmtpPort(ConfiguracionCorreoElectronico.puerto)
        .withAuthenticator(ConfiguracionCorreoElectronico.remitente, ConfiguracionCorreoElectronico.password)
        .withSSLOnConnect(true)
        .withSender(ConfiguracionCorreoElectronico.remitente)
        .withSubject("Notificacion de incidente - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
        .withMessage(mensaje)
        .withRecipient(persona.getUsuario().getMail())
        .build();
    return email;
  }
}
