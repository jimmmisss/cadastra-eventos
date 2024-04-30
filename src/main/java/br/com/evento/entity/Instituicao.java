package br.com.evento.entity;

import javax.persistence.*;


@Entity
@Table(name = "instituicoes")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INST_GEN")
    @SequenceGenerator(name = "INST_GEN", sequenceName = "INST_SEQ", allocationSize = 1)
    @Column(name = "id", unique = true, updatable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoInstituicao tipo;

    public Instituicao() {
    }

    public Instituicao(String nome, TipoInstituicao tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoInstituicao getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstituicao tipo) {
        this.tipo = tipo;
    }
}
