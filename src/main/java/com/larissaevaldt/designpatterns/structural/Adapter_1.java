package com.larissaevaldt.designpatterns.structural;

import java.math.BigDecimal;

/**
 *  GoF - Design Patters - Adapter (Wrapper) (1)
 *  Uma classe que implementa uma interface, e reutiliza outra que já existe para adaptar pra essa mesma interface.
 *  O padrao sera apresentado em 3 exemplos diferentes:
 *  Simples + Conceitos
 *  Composicao + Conceitos
 *  Heranca
 *  Esse primeiro exemplo, da forma mais simplifcada, ele nao esta descrito no livro do GoF
 *  Entao, nao seria uma alternativa oficial a implementacao desse padrao mas
 *  pode ser que seja o suficiente, e ate mais simples e mais pratico de usar no seu projeto
 *  Como funciona? Temos 3 classes, a classe principal com o metodo main
 */
public class Adapter_1 {
    // Adapter patter tem basicamente 3 conceitos importantes:
    // Client - a classe que esta usando o que voce implementou (main)
    // Adapter - quem ta fazendo a adaptacao (MeuPagamentoCredito)
    // Adaptee - quem ta sendo adaptado (SdkPagamentoCredito)

    // Tente reconhecer esse padrao sempre que voce ver que tem uma classe que esta
    // simplesmente adaptando chamadas para outras classes.
    public static void main(String[] args) {
        MeuPagamentoCredito credito = new MeuPagamentoCredito();
        credito.debitar(new BigDecimal("100"));
    }
}

// ---------------------------------------------------------------------------------

/**
 * A classe MeuPagamentoCredito, que e uma classe que esta dentro do seu projeto
 * uma classe que voce tem acesso
 * Wrapper significa embrulho, e e mais ou menos isso que essa classe e
 * Ela so pega a classe SdkPagamento, que e uma classe que ja existe,
 * e implementa metodos que traduzem a chamada que o seu negocio entende,
 * seu negocio so entende a operacao de debitar (autorizar e capturar ao mesmo tempo)
 */
class MeuPagamentoCredito {

    SdkPagamentoCredito sdkPagamentoCredito = new SdkPagamentoCredito();

    public void debitar(BigDecimal valor) {
        //perceba que a classe sdkPagamentoCredito so tem essas duas operacoes
        //como metodos separados, porem o nosso sistema precisa fazer as 2 coisas juntas
        sdkPagamentoCredito.autorizar(valor);
        sdkPagamentoCredito.capturar(valor);
    }

    public void creditar(BigDecimal valor) {
        sdkPagamentoCredito.creditar(valor);
    }
}

// -----------------------------------------------------------------------

/**
 * Voce esta implementando no seu sistema um pagamento com cartao de credito
 * E voce ta utilizando uma sdk que esta sendo fornecida pra voce
 * por algum meio de pagamento que voce ta utilizando pra fazer essa cobranca no cartao de credito
 * Essa classe ja vem com metodos pre definidos e voce nao consegue mexer
 * Elas fazem parte de uma sdk que voce so baixa um .jar, adiciona no seu projeto e usa
 * Ou adiciona no seu maven, no seu pom file
 * Ou seja, ela faz parte de uma biblioteca separada
 */
class SdkPagamentoCredito { //sdk fechada! Voce nao tem acesso, nao pode alterar essa classe aqui!

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
