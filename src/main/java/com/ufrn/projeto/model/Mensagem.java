package com.ufrn.projeto.model;

import javax.xml.bind.annotation.XmlElement;

public class Mensagem {

    private long id;
    private String conteudo;
    public Mensagem() {
    }

    public Mensagem(long id, String conteudo) {
        this.id = id;
        this.conteudo = conteudo;
    }

    public long getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }
}
