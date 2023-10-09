package persistence.converters;

import domain.notificaciones.notificador.CorreoElectronico;
import domain.notificaciones.notificador.Notificador;
import domain.notificaciones.notificador.WhatsApp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConverterNotificador implements AttributeConverter<Notificador, String> {

  @Override
  public String convertToDatabaseColumn(Notificador notificador) {
    String notificadorString = null;

    if (notificador == CorreoElectronico.obtenerInstancia()) notificadorString = "EMAIL";
    else if (notificador == WhatsApp.obtenerInstancia()) notificadorString = "WHATSAPP";

    return notificadorString;
  }

  @Override
  public Notificador convertToEntityAttribute(String s) {
    Notificador notificador = null;

    if(s != null) {
      switch (s) {
        case "WHATSAPP": notificador = WhatsApp.obtenerInstancia(); break;
        case "EMAIL": notificador = CorreoElectronico.obtenerInstancia(); break;
        default: throw new IllegalArgumentException(s + " no se puede convertir a un Notificador");
      }
    }
    return notificador;
  }

}
