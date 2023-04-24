package domain;

import domain.algoritmo.BibliotecaAuxiliar;
import domain.algoritmo.GestorContrasenias;

public class Main {

    public static void main(String[] argc) {
        System.out.println("Hola");


        GestorContrasenias aux = new GestorContrasenias();
        BibliotecaAuxiliar biblioteca = new BibliotecaAuxiliar();
        String contrasenia = "mayonesaa78312+3abababaaaaaaaaaaaaaaaa";
        
        //System.out.println(aux.cumpleCondicionesDeLongitud(contrasenia));

        //System.out.println(aux.tieneNumeros(contrasenia));
        //System.out.println(aux.tieneCaracteresEspeciales(contrasenia));
        //System.out.println(biblioteca.noTieneSecuencias(contrasenia));
        System.out.println(aux.tieneRepetidosSeguidos(contrasenia));
        
        //System.out.println(aux.noEsFrecuente(contrasenia));


    }
}
