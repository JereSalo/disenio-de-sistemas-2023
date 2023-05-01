package domain.validadoresAuxiliares;

import domain.algoritmo.EstrategiaValidacion;

public class ValidadorNumeros implements EstrategiaValidacion {
    @Override
    public Boolean superaValidacion(String contrasenia) {
        return contrasenia.matches(".*\\d.*");
    }
}
