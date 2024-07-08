package fipeapi.desafio.alura.principal;

import fipeapi.desafio.alura.model.ModelosAnos;
import fipeapi.desafio.alura.model.Marcas;
import fipeapi.desafio.alura.model.Modelos;
import fipeapi.desafio.alura.model.Veiculo;
import fipeapi.desafio.alura.service.ConsumoAPI;
import fipeapi.desafio.alura.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitor = new Scanner(System.in);

    private final ConsumoAPI consumoAPI = new ConsumoAPI();

    private final ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    private final String MARCAS = "/marcas/";

    private final String MODELOS = "/modelos/";

    private final String ANOS = "/anos/";


    public void exibirMenu() {
        System.out.println("""
        Qual tipo de automóvel você deseja vizualizar?
        
        Carros
        Motos
        Caminhoes
        """);

        String auto = leitor.nextLine().toLowerCase();
        String json = consumoAPI.obterDados(ENDERECO + auto + MARCAS);
        System.out.println(json);
        List<Marcas> marcas = converteDados.obterLista(json, Marcas.class);
        marcas
            .stream()
            .sorted(Comparator.comparing(m -> Integer.valueOf(m.codigo())))
            .forEach(m -> System.out.println("Codigo: " + m.codigo() + ", Nome: " + m.nome()));

        System.out.println("Digite o código da marca: ");
        String codigo = leitor.nextLine().toLowerCase();
        json = consumoAPI.obterDados(ENDERECO + auto + MARCAS + codigo + MODELOS);

        Modelos modelos = converteDados.obterDados(json, Modelos.class);

        modelos
                .carros()
                .forEach(c -> System.out.println("Codigo: " + c.codigo() + ", Nome: " + c.nome()));

        System.out.println("Digite o código do veiculo para ver os valores: ");
        String modelo = leitor.nextLine().toLowerCase();
        json = consumoAPI.obterDados(ENDERECO + auto + MARCAS + codigo + MODELOS + modelo + ANOS);

        List<ModelosAnos> modelosPegos = converteDados.obterLista(json, ModelosAnos.class);

        List<String> codigos = modelosPegos
                .stream()
                .map(ModelosAnos::codigo).collect(Collectors.toList());

        List<Veiculo> veiculos = new ArrayList<>();

        codigos.forEach(c -> veiculos.add(converteDados.obterDados(consumoAPI.obterDados(ENDERECO + auto + MARCAS + codigo +
                MODELOS + modelo + ANOS + c), Veiculo.class)));

        veiculos.forEach(System.out::println);
    }
}
