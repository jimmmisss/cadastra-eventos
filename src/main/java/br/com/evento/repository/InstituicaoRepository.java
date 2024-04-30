package br.com.evento.repository;

import br.com.evento.entity.Instituicao;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class InstituicaoRepository implements PanacheRepositoryBase<Instituicao, Integer> {

    public List<Instituicao> pageOf(int page, int size) {
        var list = find("FROM Instituicao");
        return list.page(Page.of(page, size)).list();
    }
}
