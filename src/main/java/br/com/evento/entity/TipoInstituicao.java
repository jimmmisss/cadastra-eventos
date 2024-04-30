package br.com.evento.entity;

public enum TipoInstituicao {

    CONFEDERACAO("Confederação"),
    SINGULAR("Singular"),
    CENTRAL("Central"),
    COOPERATIVA("Cooperativa");

    private String descricao;

    TipoInstituicao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
