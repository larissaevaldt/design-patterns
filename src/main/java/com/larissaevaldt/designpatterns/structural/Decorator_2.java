package com.larissaevaldt.designpatterns.structural;

/** Java Design Patterns Decorator (2)
 * Decorator com Interface
 * Como eles compartilham uma interface, qualquer lugar que esteja esperando um Enderecador
 * vai poder receber tanto um EnderecadorSimples, como um EnderecadorCaixaAlta
 * Mantendo a ideia do decorator que e pegar um objeto que ja tem uma funcionalidade
 * e adicionar mais alguma funcionalidade a ele, sem precisar mexer no objeto de dentro
 */
public class Decorator_2 {

    public static void main(String[] args) {
        Endereco2 endereco = new Endereco2("Rua 4 casa 123", "Centro", "Sao Jose", "RN", "50930-123");

        Enderecador2 enderecador = new EnderecadorSimples2();
        // agora conseguimos reutilizar a mesma variavel
        enderecador = new EnderecadorCaixaAlta2(enderecador);

        String enderecoFormatado = enderecador.preparaEndereco(endereco);

        System.out.println(enderecoFormatado);
    }
}

// --------------------------------------------------------------------
class Endereco2 {
    final String logradouro;
    final String bairro;
    final String cidade;
    final String uf;
    final String cep;

    public Endereco2(String logradouro, String bairro, String cidade, String uf, String cep) {
        super();
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
}

// --------------------------------------------------------------------
// Aqui agora nos temos uma interface com o metodo preparaEndereco
// os dois enderecadores estavam implementando ja esse metodo antes
// so que agora eu implemento uma interface nos dois casos
interface Enderecador2 {
    String preparaEndereco(Endereco2 endereco);
}

//  ------------------------------------------------------------------
// Classe usada para formatar o endereco, implementando a interface
class EnderecadorSimples2 implements Enderecador2 {

    public String preparaEndereco(Endereco2 endereco) {
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
// Implementando a mesma interface que o EnderecadorSimples
class EnderecadorCaixaAlta2 implements Enderecador2 {
    Enderecador2 enderecador;

    public EnderecadorCaixaAlta2(Enderecador2 enderecador) {
        super();
        this.enderecador = enderecador;
    }

    public String preparaEndereco(Endereco2 endereco) {
        return enderecador.preparaEndereco(endereco).toUpperCase();
    }
}

