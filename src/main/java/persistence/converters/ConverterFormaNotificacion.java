package persistence.converters;

import domain.notificaciones.formaNotificacion.CuandoSuceden;
import domain.notificaciones.formaNotificacion.FormaNotificacion;
import domain.notificaciones.formaNotificacion.SinApuros;

import javax.persistence.*;

@Converter(autoApply = true)
public class ConverterFormaNotificacion implements AttributeConverter<FormaNotificacion, String>{
    
    @Override
    public String convertToDatabaseColumn(FormaNotificacion formaNotificacion) {
        String formaNotificacionString = null;

        if (formaNotificacion == CuandoSuceden.obtenerInstancia())
            formaNotificacionString = "CUANDO_SUCEDEN";
        else formaNotificacionString = "SIN_APUROS";

        return formaNotificacionString;

    }

    @Override
    public FormaNotificacion convertToEntityAttribute(String formaNotificacionString) {
        FormaNotificacion formaNotificacion = null;

        if(formaNotificacionString != null) {
            switch (formaNotificacionString) {
                case "CUANDO_SUCEDEN": formaNotificacion = CuandoSuceden.obtenerInstancia(); break;
                case "SIN_APUROS": formaNotificacion = SinApuros.obtenerInstancia(); break;
                default: throw new IllegalArgumentException(formaNotificacionString +
                                                            " no se puede convertir a una forma de notificacion");
            }
        }
        return formaNotificacion;
    }
}
