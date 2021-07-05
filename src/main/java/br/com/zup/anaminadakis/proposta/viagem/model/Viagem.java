package br.com.zup.anaminadakis.proposta.viagem.model;

import br.com.zup.anaminadakis.proposta.cartao.model.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destino;

    @NotNull @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate terminoViagem;

    @NotNull
    private LocalDateTime avisoViagem = LocalDateTime.now();

    @NotBlank
    private String ipClient;

    @NotBlank
    private String userAgent;

    @ManyToOne @NotNull
    private Cartao cartao;

    @Deprecated
    public Viagem() {
    }

    public Viagem(String destino,
                  LocalDate terminoViagem,
                  String ipClient,
                  String userAgent,
                  Cartao cartao) {
        this.destino = destino;
        this.terminoViagem = terminoViagem;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }
}
