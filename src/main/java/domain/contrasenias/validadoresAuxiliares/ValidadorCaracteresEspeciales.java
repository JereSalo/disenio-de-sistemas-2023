package domain.contrasenias.validadoresAuxiliares;

public class ValidadorCaracteresEspeciales implements EstrategiaValidacion {
    @Override
    public Boolean superaValidacion(String contrasenia) {
        return contrasenia.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*");
    }
}
