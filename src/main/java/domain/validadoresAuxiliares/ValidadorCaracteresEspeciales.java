package domain.validadoresAuxiliares;

import domain.algoritmo.EstrategiaValidacion;

public class ValidadorCaracteresEspeciales implements EstrategiaValidacion {
    @Override
    public Boolean superaValidacion(String contrasenia) {
        return contrasenia.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*");
    }
}
