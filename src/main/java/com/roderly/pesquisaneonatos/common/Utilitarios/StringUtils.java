package com.roderly.pesquisaneonatos.common.Utilitarios;


public class StringUtils {

    public static String removerMascara(String valor) {
        if (valor == null) {
            return null;
        }
        // Remove qualquer caractere que não seja número
        return valor.replaceAll("\\D", "");
    }

    public static String getPrimeiroEUltimoNome(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.isBlank()) return "";

        String[] partes = nomeCompleto.trim().split("\\s+");
        if (partes.length == 1) return partes[0];

        return partes[0] + " " + partes[partes.length - 1];
    }

}