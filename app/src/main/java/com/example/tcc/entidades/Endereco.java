package com.example.tcc.entidades;

import java.io.Serializable;

public class Endereco implements Serializable {

    private String rua;
    private String cidade;
    private String estado;
    private String CEP;
    private String pais;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
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

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


    @Override
    public String toString() {
        return "Endereço{" +
                " Rua=" + rua + '\'' +
                ", Cidade='" + cidade + '\'' +
                ", Estado='" + estado + '\'' +
                ", CEP='" + CEP + '\'' +
                ", Pais='" + pais + '\'' +
                '}';
    }
}
