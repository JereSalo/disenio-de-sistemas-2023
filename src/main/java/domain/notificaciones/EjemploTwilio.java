package domain.notificaciones;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class EjemploTwilio {
  // Find your Account SID and Auth Token at twilio.com/console
  // and set the environment variables. See http://twil.io/secure

  // Hay que configurar estas 2 como variables de entorno en IntelliJ: Run -> Edit Configurations -> Environment variables
  public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");


  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber("whatsapp:+5491141690058"), // Número de teléfono destinatario
            new com.twilio.type.PhoneNumber(ConfiguracionWhatsapp.FROM), // Número de teléfono origen
            "Este es un mensaje de prueba!")
        .create();

    System.out.println(message.getSid());
  }
}
