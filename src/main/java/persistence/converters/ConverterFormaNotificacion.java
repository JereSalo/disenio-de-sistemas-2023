package persistence.converters;

import domain.notificaciones.formaNotificacion;

import javax.persistence.*;

@Converter(autoApply = true)
public class ConverterFormaNotificacion implements AttributeConverter<FormaNotificacion, String>{
    
    @Override
    public String convertToDatabaseColumn(FormaNotificacion formaNotificacion) {
        String formaNotificacionString = null;

        switch (formaNotificacionString) {
            case MONDAY: formaNotificacionString = "CUANDO_SUCEDEN"; break;
            case TUESDAY: formaNotificacionString = "SIN_APUROS"; break;
        }

        return dia;
    }

    @Override
    public FormaNotificacion convertToEntityAttribute(String s) {
        DayOfWeek dia = null;

        if(s != null) {
            switch (s) {
                case "Lunes": dia = DayOfWeek.MONDAY; break;
                case "Sabado": dia = DayOfWeek.SATURDAY; break;
                default: throw new IllegalArgumentException(s + " no se puede convertir a un DayOfWeek");
            }
        }
        return dia;
    }


}
