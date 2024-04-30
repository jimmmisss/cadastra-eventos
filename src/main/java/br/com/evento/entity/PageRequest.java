package br.com.evento.entity;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class PageRequest {

    @QueryParam("pageNum")
    @DefaultValue("0")
    private int page;

    @QueryParam("pageSize")
    @DefaultValue("10")
    private int size;

    public PageRequest() {
    }

    public PageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}