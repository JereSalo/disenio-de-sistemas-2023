package persistence.converters;
import javax.persistence.*;
import java.time.LocalTime;

@Converter(autoApply = true)
public class ConverterHorarios implements AttributeConverter<LocalTime, String>{

    @Override
    public String convertToDatabaseColumn(LocalTime horario) {
        return horario.toString();
    }

    @Override
    public LocalTime convertToEntityAttribute(String horarioString) {
        LocalTime horario = null;
        if(horarioString != null) {
            horario = LocalTime.parse(horarioString);
        }
        return horario;
    }
}
