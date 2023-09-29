package com.example.listacolorida;

public class Texto {
    private String texto;
    private int cor;

    public Texto(String texto, int cor) {
        this.texto = texto;
        this.cor = cor;
    }

    public String getTexto() {
        return texto;
    }

    public int getCor() {
        return cor;
    }
}
