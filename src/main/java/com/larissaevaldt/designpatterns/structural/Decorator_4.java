package com.larissaevaldt.designpatterns.structural;

/** Java Design Patterns - Decorator (4)
 * Exemplo completo
 */
public class Decorator_4 {
    public static void main(String[] args) {
        Endereco4 endereco = new Endereco4("Rua 4 casa 123", "Centro", "Sao Jose", "RN", "50930-123");

        Enderecador4 enderecador = new EnderecadorSimples4();
        enderecador = new EnderecadorCaixaAlta4(enderecador); //passando o simples
        enderecador = new EnderecadorComBorda4(enderecador); //passando o caixa alta
        // perceba que gente vai encapsulando um dentro do do outro, temos um encadeamento de enderecadores

        String enderecoFormatado = enderecador.preparaEndereco(endereco);

        System.out.println(enderecoFormatado);
    }
}

//-----------------------------------------------------------------------------------
class Endereco4 {
    final String logradouro;
    final String bairro;
    final String cidade;
    final String uf;
    final String cep;

    public Endereco4(String logradouro, String bairro, String cidade, String uf, String cep) {
        super();
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }
}
//------------------------------------------------------------------------------------
interface Enderecador4 {
    String preparaEndereco(Endereco4 endereco);
}

//-------------------------------------------------------------------------------------
// Classe usada para formatar o endereco, implementando a interface
class EnderecadorSimples4 implements Enderecador4 {

    public String preparaEndereco(Endereco4 endereco) {
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

//----------------------------------------------------------------------------------
// Ate aqui o exemplo estava exatamente igual o Exemplo 3, a partir daqui e que muda
// Agora temos uma classe abstrata, que e pra armazenar nela a parte que e comum dos meus decorators
// como vamos ter varios decorators, e quando voce usa decorator, uma das ideias do padrao e que voce tenha mais de um,
// e comum no padrao voce ter primeiro um decorator abstrato que implementa a interface
// e todos os nossos decorators podem so extender essa classe abstrata
// Nao e necessario, e caso voce so tenha um decorator, voce nao precisa dessa classe abstrata
abstract class EnderecadorDecorator implements Enderecador4 {

    // perceba que tudo isso dentro dessa classe, no exemplo anterior, cada um dos decorators tinham esse codigo dentro
    Enderecador4 enderecador;

    public EnderecadorDecorator(Enderecador4 enderecador) {
        super();
        this.enderecador = enderecador;
    }
}

// -------------------------------------------------------------------------
// Decorator que transforma em upper case
// Implementando a mesma interface que o EnderecadorSimples
class EnderecadorCaixaAlta4 extends EnderecadorDecorator {

    public EnderecadorCaixaAlta4(Enderecador4 enderecador) {
        super(enderecador);
    }

    public String preparaEndereco(Endereco4 endereco) {
        return enderecador.preparaEndereco(endereco).toUpperCase();
    }
}

//--------------------------------------------------------------------------
// Decorator que adiciona a funcionalidade de borda
class EnderecadorComBorda4 extends EnderecadorDecorator {

    public EnderecadorComBorda4(Enderecador4 enderecador) {
        super(enderecador);
    }

    @Override
    public String preparaEndereco(Endereco4 endereco) {
        String preparaEndereco = enderecador.preparaEndereco(endereco);
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------\n");
        preparaEndereco.lines().forEach(l -> sb.append("| " + l + "\n"));
        sb.append("-------------------------------\n");
        return sb.toString();
    }
}

/**
 * Diferenca entre o Decorator e o Adapter
 * No Adapter o que gente faz e uma adaptacao de uma interface sobre uma classe que ja existe
 * Lembrando do nosso exemplo, em algum momento a gente precisou de uma interface ProcessorPagemento
 * e que a gente tinha ja feito pra gente, era uma classe, a SdkPagamentoCredito
 * essa classe nao era compativel com a interface, e ai criamos uma classe ali no meio que e um adaptador
 * ela acaba utilizando o mesmo apelido de wrapper, pq ela tambem empacota a sdk dento dela
 * ela tambem carrega um apontamento pra sdk mas
 * Na pratica a ideia dela e totalmente diferente, a ideia e servir como uma traducao,
 * entre a nossa implementacao e uma interface que a gente precisa, e essa ponte quem vai fazer vai ser o Adapter.
 */


