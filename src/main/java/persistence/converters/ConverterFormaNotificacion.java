package persistence.converters;

import domain.notificaciones.formaNotificacion;

import javax.persistence.*;

@Converter(autoApply = true)
public class ConverterFormaNotificacion implements AttributeConverter<FormaNotificacion, String>{
    
    @Override
    public String convertToDatabaseColumn(FormaNotificacion formaNotificacion) {
        String formaNotificacionString = null;

        switch (dayOfWeek) {
            case MONDAY: dia = "Lunes"; break;
            case TUESDAY: dia = "Martes"; break;
        }
        
        return dia;
    }

    @Override
    public DayOfWeek convertToEntityAttribute(String s) {
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
