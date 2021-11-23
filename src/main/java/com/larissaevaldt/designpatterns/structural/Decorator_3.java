package com.larissaevaldt.designpatterns.structural;

/** Java Design Patterns - Decorator (3) - Varios Decorators
 * Esse e um dos conceitos que o Decorator traz, que e o encadeamento de Decorator
 * Aqui ja e de fato usando o padrao de Decorator como mostra no livro GoF
 * Temos mais de uma implementacao de decorator, que estao usando uma mesma interface
 * mostra a ideia de poder ir colocando um dentro do outro e ir adicionando mais funcionalidades conforme for necessario
 */
public class Decorator_3 {

    public static void main(String[] args) {
        Endereco3 endereco = new Endereco3("Rua 4 casa 123", "Centro", "Sao Jose", "RN", "50930-123");

        Enderecador3 enderecador = new EnderecadorSimples3();
        enderecador = new EnderecadorCaixaAlta3(enderecador); //passando o simples
        enderecador = new EnderecadorComBorda3(enderecador); //passando o caixa alta
        // perceba que gente vai encapsulando um dentro do do outro, temos um encadeamento de enderecadores

        String enderecoFormatado = enderecador.preparaEndereco(endereco);

        System.out.println(enderecoFormatado);
    }
}

// --------------------------------------------------------------------
class Endereco3 {
    final String logradouro;
    final String bairro;
    final String cidade;
    final String uf;
    final String cep;

    public Endereco3(String logradouro, String bairro, String cidade, String uf, String cep) {
        super();
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
}

interface Enderecador3 {
    String preparaEndereco(Endereco3 endereco);
}

//  ------------------------------------------------------------------
// Classe usada para formatar o endereco, implementando a interface
class EnderecadorSimples3 implements Enderecador3 {

    public String preparaEndereco(Endereco3 endereco) {
        StringBuilder sb = new StringBuilder();
        sb.append(endereco.logradouro);
        sb.append("\n");
        sb.append(endereco.bairro);
        sb.append("\n");
        sb.append(endereco.cidade);
        sb.append('/');
        sb.append(endereco.uf);
        sb.append("\n");
        sb.append(endereco.cep);
        return sb.toString();
    }
}

// -------------------------------------------------------------------------
// Decorator que transforma em upper case
// Implementando a mesma interface que o EnderecadorSimples
class EnderecadorCaixaAlta3 implements Enderecador3 {
    Enderecador3 enderecador;

    public EnderecadorCaixaAlta3(Enderecador3 enderecador) {
        super();
        this.enderecador = enderecador;
    }

    public String preparaEndereco(Endereco3 endereco) {
        return enderecador.preparaEndereco(endereco).toUpperCase();
    }
}

//--------------------------------------------------------------------------
// Decorator que adiciona a funcionalidade de borda
class EnderecadorComBorda3 implements Enderecador3 {
    Enderecador3 enderecador;

    public EnderecadorComBorda3(Enderecador3 enderecador) {
        super();
        this.enderecador = enderecador;
    }
    @Override
    public String preparaEndereco(Endereco3 endereco) {
        String preparaEndereco = enderecador.preparaEndereco(endereco);
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------\n");
        preparaEndereco.lines().forEach(l -> sb.append("| " + l + "\n"));
        sb.append("-------------------------------\n");
        return sb.toString();
    }
}