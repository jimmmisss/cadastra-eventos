package br.com.evento.repository;

import br.com.evento.entity.Evento;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EventoRepository implements PanacheRepositoryBase<Evento, Integer> {

    public List<Evento> pageOf(int page, int size) {
        var list = find("FROM Evento");
        return list.page(Page.of(page, size)).list();
    }

    @Transactional
    @Scheduled(every = "30s")
    public void buscarEventosVigentesEAtualizaComoAtivo() {
        find("dataInicio <= now() and dataFim >= now() and ativo = false").list()
                .forEach(evento -> evento.setAtivo(true));
    }

    @Transactional
    @Scheduled(every = "30s")
    public void buscarEventosExpiradosEInativar() {
        find("dataFim < now() and ativo = true").list()
                .forEach(evento -> evento.setAtivo(false));
    }
}
