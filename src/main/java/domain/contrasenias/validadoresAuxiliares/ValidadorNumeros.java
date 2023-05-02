package domain.contrasenias.validadoresAuxiliares;

public class ValidadorNumeros implements EstrategiaValidacion {
    @Override
    public Boolean superaValidacion(String contrasenia) {
        return contrasenia.matches(".*\\d.*");
    }
}
