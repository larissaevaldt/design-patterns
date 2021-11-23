package com.larissaevaldt.designpatterns.structural;

/**
 * GoF - Design Patterns with Java - Decorator (Wrapper) (1)
 * ACRESCENTE funcionalidades com DECORATOR ao inves de usar HERANCA
 * A ideia dele e conseguir incrementar, adicionar funcionalidade ao redor/em cima de uma classe que voce ja tem
 * sem precisar usar heranca, ou seja, tornando isso um pouco mais flexivel
 * Veremos 4 exemplos / 4 formas de usar o Decorator
 * Porem so as 2 ultimas sao de fato Decorator conforme o livro do GoF
 */
public class Decorator_1 {

    public static void main(String[] args) {
        Endereco endereco = new Endereco("Rua 4 casa 123", "Centro", "Sao Jose", "RN", "50930-123");

        EnderecadorSimples enderecador = new EnderecadorSimples();
        EnderecadorCaixaAlta enderecadorCaixaAlta = new EnderecadorCaixaAlta(enderecador);

        String enderecoFormatado = enderecadorCaixaAlta.preparaEndereco(endereco);

        System.out.println(enderecoFormatado);
    }
}

// --------------------------------------------------------------------
class Endereco {
    final String logradouro;
    final String bairro;
    final String cidade;
    final String uf;
    final String cep;

    public Endereco(String logradouro, String bairro, String cidade, String uf, String cep) {
        super();
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
}

//  ------------------------------------------------------------------
// Classe usada para formatar o endereco
class EnderecadorSimples {

    public String preparaEndereco(Endereco endereco) {
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
// Decorator
// Tem dentro dele uma instancia do nosso EnderecadorSimples
// E ele tem o metodo preparaEndereco tambem
// Mas perceba que eles nao estao compartilhando nenhuma interface
// Ela faz tudo que a de dentro faz (formatar o endereco) e mais alguma coisa (transformar em UpperCase, nesse caso)
class EnderecadorCaixaAlta {
    EnderecadorSimples enderecador;

    public EnderecadorCaixaAlta(EnderecadorSimples enderecador) {
        super();
        this.enderecador = enderecador;
    }

    public String preparaEndereco(Endereco endereco) {
        return enderecador.preparaEndereco(endereco).toUpperCase();
    }
}

/** PROBLEMA DESSA IMPLEMENTACAO: Essas duas classes (EnderecadorSimples e EnderecadorCaixaAlta) NAO sao intercambiaveis
 * eu nao posso usar um EnderecadoCaixaAlta onde eu ja estou usando um EnderecadorSimples
 * Sao dois tipos diferentes que nao compartilham uma hierarquia, uma interface, uma mesma classe sob heranca,
 * entao pro Java essas duas classes sao totalmente independentes, totalmente diferentes
 * se voce tem um cliente que espera um EnderecadorSimples, voce nao consegue passar pra ele um EnderecadorCaixaAlta
 */
