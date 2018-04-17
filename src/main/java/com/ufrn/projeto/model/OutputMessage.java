package com.ufrn.projeto.model;

public class OutputMessage {

    int codigo;
    String mensagem;

    public OutputMessage(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public OutputMessage() {
    }
    
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public int getCodigo() { return codigo; }
    public String getMensagem() { return mensagem; }
}
