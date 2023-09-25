package domain.notificaciones.notificador;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import domain.usuarios.Persona;

public class WhatsApp extends Notificador{
  private static WhatsApp instancia = null;

  private WhatsApp(){}

  public static WhatsApp obtenerInstancia() {
    if (instancia == null) {
      Twilio.init(ConfiguracionWhatsapp.ACCOUNT_SID, ConfiguracionWhatsapp.AUTH_TOKEN); // Esto debería ocurrir solo una vez
      instancia = new WhatsApp();
    }
    return instancia;
  }

  @Override
  protected void enviar(Persona persona, String mensaje) {
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber("whatsapp:" + persona.getUsuario().getTelefono()), // Número de teléfono destinatario. En formato E164 (Ejemplo: +5491141690058)
            new com.twilio.type.PhoneNumber(ConfiguracionWhatsapp.FROM), // Número de teléfono origen
            mensaje)
        .create();
  }
}
