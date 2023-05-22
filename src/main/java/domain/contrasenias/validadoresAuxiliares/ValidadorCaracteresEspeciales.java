package domain.contrasenias.validadoresAuxiliares;

public class ValidadorCaracteresEspeciales implements EstrategiaValidacion {
    @Override
    public Boolean esValida(String contrasenia) {
        return contrasenia.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*");
    }
}
