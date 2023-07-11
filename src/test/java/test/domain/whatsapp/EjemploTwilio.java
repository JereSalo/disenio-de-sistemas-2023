package test.domain.whatsapp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import domain.notificaciones.notificador.ConfiguracionWhatsapp;

public class EjemploTwilio {
  // Find your Account SID and Auth Token at twilio.com/console
  // and set the environment variables. See http://twil.io/secure
  public static void main(String[] args) {
    Twilio.init(ConfiguracionWhatsapp.ACCOUNT_SID, ConfiguracionWhatsapp.AUTH_TOKEN);

    Message message = Message.creator(
            new com.twilio.type.PhoneNumber("whatsapp:+5491141690058"), // Número de teléfono destinatario
            new com.twilio.type.PhoneNumber(ConfiguracionWhatsapp.FROM), // Número de teléfono origen
            "Este es un mensaje de prueba!")
        .create();

    System.out.println(message.getSid());
  }
}
