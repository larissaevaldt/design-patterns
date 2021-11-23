package com.larissaevaldt.designpatterns.structural;

import java.math.BigDecimal;

/**
 * JAVA Design Patterns - Adapter (3)
 * Muito parecido com o segundo exemplo, temos as mesmas 4 entidades, os mesmos 4 conceitos
 * Usando heranca
 */
public class Adapter_3 {

    public static void main(String[] args) {
        ProcessorPagamento3 credito = new MeuPagamentoCredito3();
        credito.debitar(new BigDecimal("100"));
    }
}

// ------------------------------------------------------------------------------
interface ProcessorPagamento3 {

    void debitar(BigDecimal valor);
    void creditar(BigDecimal valor);
}

// ------------------------------------------------------------------------------
// Um adapter que adapta uma implementacao que ja esta criada (SdkPagamento)
// para simplesmente se encaixar na nossa API, na nossa interface
// E nesse exemplo ela e so faz a adaptacao mesmo mas nao precisa ser,
// essa classe pode fazer mais coisas tbm, isso e importante lembrar!
class MeuPagamentoCredito3 extends SdkPagamentoCredito3
        implements ProcessorPagamento3 {

    //aqui o nosso adapter nao e simplesmente um atributo, como no outro exemplo
    // nos estamos extendendo a sdk
    //na pratica, nesse exemplo aqui, o funcionamento vai ser muito parecido
    @Override
    public void debitar(BigDecimal valor) {
        // ao usar o .super, estamos chamando o metodo da classe mae
        super.autorizar(valor);
        super.capturar(valor);
    }

    @Override
    public void creditar(BigDecimal valor) {
        super.creditar(valor);
    }

    @Override
    public void autorizar(BigDecimal valor) {
        // se mudou a comportamento - nao e mais two-way
        // quem antes usava a sdk original, tem que continuar usando a original
        // nao da pra usar a sua implementacao
    }
}

// ------------------------------------------------------------------------------
// exatamente igual a do exemplo anterior
class SdkPagamentoCredito3 { // .jar / lib no maven

    //sdk fechada, voce nao tem acesso, nao pode alterar essa classe aqui
    //posso ate ter acesso, mas nao convem alterar
    //ou seja, pode ser que a classe ja tem outras responsabilidades, o foco dela e outro, e nao queremos alterar ela pra isso

    public void autorizar(BigDecimal valor) {
        // autoriza
    }
    public void capturar(BigDecimal valor) {
        // captura
    }
    public void creditar(BigDecimal valor) {
        // credita
    }
}

/** Vantagens e desvantagens:
 * Vantagem de usar heranca:
 * 1) Nao precisa criar um novo objeto, a chamada nao esta sendo redirecionada a um proximo objeto
 * nao temos 2 instancias em tempo de execucao
 * 2) Consegue fazer facilmente a adaptacao de mao dupla - two way adapter:
 * Imagine que voce tem um outro cliente que utiliza a classe SdkPagamento diretamente
 * pode ser no seu codigo, ou num codigo de um outro projeto, de um outro time
 * se eles quiserem passar a usar o seu adapter, eles podem passar a usar ele tranquilamente
 * digamos que a sua implementacao tenha mais coisas uteis ali dentro, ela faz algum tipo de
 * auditoria, log, ou envia essa informacao pra algum outro lugar, etc.
 * seu adapter nao so funciona para o seu caso, o seu negocio, como ele tambem
 * funciona pra quem usava originalmente a sdk de pagamento, pq nos nao alteramos os metodos
 * A desvantagem de heranca:
 * 1) Falta de flexibilidade
 * No exemplo 2, como a sdk e um atributo, nos poderiamos ter um adapter que adapta 2, 3, 4 classes diferentes
 * Como no Java nos nao temos heranca multipla, nao consiguimos estender varias classes e adaptar varias classes ao mesmo tempo
 * Ou seja, o exemplo 2 e mais flexivel, ate pra fazer alteracoes no futuro, pq
 * quando eu crio uma hierarquia, ai eu comeco a criar amarras um pouco maiores, por exemplo,
 * com o outro time que poderia estar usando a sua classe pra substituir a sdk
 * nos nao poderiamos tirar o extends do nosso adapter sem quebrar o codigo deles
 *
 * Vantagens e desvantagens do adapter:
 * A principal e o REUSO
 * e SRP -  Single Responsability Principal (SOLID)
 * ajuda a manter apenas uma responsabilidade dentro da nossa classe
 * a gente separa e deixa cada uma com a sua responsabilidade
 * Desvantagem: Fica mais robusto, mas fica maior, fica mais complexo, mais dificil de entender
 * Entao sempre que voce vai usar um padrao desse daqui, veja se faz sentido,
 * Faz sentido eu criar um adapter que so vai repassar chamada?
 * Ou faz mais sentido eu so usar a classe sdk ali e pronto?
 * Isso aqui de fato vai ser usado em mais locais?
 * Tem logica suficiente pra eu criar uma classe so pra ela?
 * E importante colocar na balanca pra entender se faz sentido aplicar
 */