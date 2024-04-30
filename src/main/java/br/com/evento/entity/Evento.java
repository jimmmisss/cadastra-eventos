package br.com.evento.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENTO_GEN")
    @SequenceGenerator(name = "EVENTO_GEN", sequenceName = "EVENTO_SEQ", allocationSize = 1)
    @Column(name = "id", unique = true, updatable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

   public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
}
