package br.com.desafioextra.entities;

public class Cliente {

    private int id;
    private String nome;
    private String cep;
    private String cidade;
    private String estado;


    public Cliente(){

    }

    public Cliente(String nome, String cep, String cidade, String estado) {
        this.nome = nome;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
