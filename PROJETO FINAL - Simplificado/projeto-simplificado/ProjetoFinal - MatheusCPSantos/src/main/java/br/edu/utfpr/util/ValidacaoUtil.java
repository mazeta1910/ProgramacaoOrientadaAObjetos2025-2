package br.edu.utfpr.util;

import java.util.Arrays;
import java.util.List;

public class ValidacaoUtil {

    private static final List<String> FUNCOES_REQUER_CREA = Arrays.asList(
        "Engenheiro Civil",
        "Engenheiro de Segurança",
        "Engenheiro Eletricista",
        "Arquiteto"
    );

    private static final List<String> FUNCOES_REQUER_REGISTRO = Arrays.asList(
        "Pedreiro",
        "Eletricista",
        "Encanador",
        "Carpinteiro",
        "Pintor"
    );

    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int[] numeros = new int[11];
            for (int i = 0; i < 11; i++) {
                numeros[i] = Character.getNumericValue(cpf.charAt(i));
            }

            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += numeros[i] * (10 - i);
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) primeiroDigito = 0;

            if (numeros[9] != primeiroDigito) {
                return false;
            }

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += numeros[i] * (11 - i);
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) segundoDigito = 0;

            return numeros[10] == segundoDigito;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFuncaoRequerCREA(String funcao) {
        return FUNCOES_REQUER_CREA.stream()
            .anyMatch(f -> f.equalsIgnoreCase(funcao));
    }

    public static boolean isFuncaoRequerRegistro(String funcao) {
        return FUNCOES_REQUER_REGISTRO.stream()
            .anyMatch(f -> f.equalsIgnoreCase(funcao));
    }

    public static boolean validarCREA(String crea) {
        if (crea == null || crea.isEmpty()) {
            return false;
        }
        return crea.matches("\\d{6,10}");
    }

    public static boolean validarRegistroProfissional(String registro) {
        if (registro == null || registro.isEmpty()) {
            return false;
        }
        return registro.matches("\\d{4,8}");
    }
}
