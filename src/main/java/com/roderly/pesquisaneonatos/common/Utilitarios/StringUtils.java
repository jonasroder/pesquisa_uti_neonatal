package com.roderly.pesquisaneonatos.common.Utilitarios;


public class StringUtils {

    public static String removerMascara(String valor) {
        if (valor == null) {
            return null;
        }
        // Remove qualquer caractere que não seja número
        return valor.replaceAll("\\D", "");
    }
}