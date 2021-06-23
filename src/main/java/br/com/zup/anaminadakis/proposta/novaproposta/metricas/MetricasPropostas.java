package br.com.zup.anaminadakis.proposta.novaproposta.metricas;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

//Criando métrica para contabilizar o número de propostas criadas.
@Component
public class MetricasPropostas {

    private final MeterRegistry meterRegistry;
    private Counter contadorPropostaCriada;

    public MetricasPropostas(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.meuContador();
    }

    //Criando o contador
    private void meuContador() {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora", "Mastercard"));
        tags.add(Tag.of("banco", "Itaú"));

        this.contadorPropostaCriada = this.meterRegistry.counter("proposta_criada", tags);
    }

    //para incrementar o contador
    public void incrementa() {
        this.contadorPropostaCriada.increment();
    }
}
