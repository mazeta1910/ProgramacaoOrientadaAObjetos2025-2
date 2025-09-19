import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Exercício 1: Encontrar o maior valor em uma lista de inteiros
        List<Integer> numeros = Arrays.asList(10, 25, 8, 35, 12, 40, 5);
        int maiorValor = numeros.stream()
                               .max(Comparator.naturalOrder())
                               .orElse(0);
        System.out.println("Maior valor: " + maiorValor);
        
        // Exercício 2: Criar nova lista com números maiores que 15
        List<Integer> maioresQue15 = numeros.stream()
                                           .filter(n -> n > 15)
                                           .collect(Collectors.toList());
        System.out.println("Números maiores que 15: " + maioresQue15);
        
        // Exercício 3: Criar nova lista com nomes em maiúsculas
        List<String> nomes = Arrays.asList("ana", "joão", "maria", "pedro", "carlos");
        List<String> nomesMaiusculos = nomes.stream()
                                           .map(String::toUpperCase)
                                           .collect(Collectors.toList());
        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);
        
        // Exercício 4: Contar quantos números são pares
        long quantidadePares = numeros.stream()
                                     .filter(n -> n % 2 == 0)
                                     .count();
        System.out.println("Quantidade de números pares: " + quantidadePares);
        
        // Exercício 5: Criar nova lista com notas sem repetição
        List<Double> notas = Arrays.asList(7.5, 8.0, 6.5, 7.5, 9.0, 8.0, 7.0);
        List<Double> notasUnicas = notas.stream()
                                       .distinct()
                                       .collect(Collectors.toList());
        System.out.println("Notas sem repetição: " + notasUnicas);
    }
}