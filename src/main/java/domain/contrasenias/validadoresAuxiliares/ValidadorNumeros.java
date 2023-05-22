package domain.contrasenias.validadoresAuxiliares;

public class ValidadorNumeros implements EstrategiaValidacion {
    @Override
    public Boolean esValida(String contrasenia) {
        return contrasenia.matches(".*\\d.*");
    }
}
