package com.larissaevaldt.designpatterns.structural;

import java.math.BigDecimal;

/**
 * Java Design Patterns - Adapter (2)
 * Exemplo: Composicao + conceitos
 * Esse exemplo ja e como mostra no livro do GoF
 * Aqui eu tenho uma interface, um contrato formal, que eu quero adaptar
 */
public class Adapter_2 {
    // Agora temos um conceito a mais:
    // Client -  quem ta usando isso tudo
    // Adapter - a classe que esta adaptando
    // Adaptee - o que esta sendo adaptado
    // Target (alvo) - a implementacao que estamos querendo fazer
    public static void main(String[] args) {

        // O cliente quer uma instacia da interface ProcessadorPagamento
        // Nesse caso aqui estamos instanciando direto a classe que a gente criou mas
        // poderia estar usando um Factory pra decidir qual implemntacao usar
        // O cliente espera uma implementacao de ProcessadorPagamento, por isso ela e o target
        ProcessorPagamento2 credito = new MeuPagamentoCredito2();
        credito.debitar(new BigDecimal("100"));
    }
}

// ------------------------------------------------------------------------------
interface ProcessorPagamento2 {

    void debitar(BigDecimal valor);
    void creditar(BigDecimal valor);
}

// ------------------------------------------------------------------------------
// Um adapter que adapta uma implementacao que ja esta criada (SdkPagamento)
// para simplesmente se encaixar na nossa API, na nossa interface que tem 2 metodos
class MeuPagamentoCredito2 implements ProcessorPagamento2 {

    SdkPagamentoCredito2 sdkPagamentoCredito = new SdkPagamentoCredito2(); //usa composicao, temos uma classe dentro de outra/como atributo de outra
    @Override
    public void debitar(BigDecimal valor) {
        sdkPagamentoCredito.autorizar(valor);
        sdkPagamentoCredito.capturar(valor);
    }

    @Override
    public void creditar(BigDecimal valor) {
        sdkPagamentoCredito.creditar(valor);
    }
}

// ------------------------------------------------------------------------------
// essa classe e exatamente igual a do exemplo anterior
class SdkPagamentoCredito2 { // .jar / lib no maven

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

