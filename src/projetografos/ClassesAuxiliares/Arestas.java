package projetografos.ClassesAuxiliares;

import javafx.scene.shape.Line;

public class Arestas {
    
    private Line aresta;
    private int verticeIni;
    private int verticeFim;
    private int valor;

    public Arestas(Line aresta, int verticeIni, int verticeFim, int valor) {
        this.aresta = aresta;
        this.verticeIni = verticeIni;
        this.verticeFim = verticeFim;
        this.valor = valor;
    }

    public Line getAresta() {
        return aresta;
    }

    public void setAresta(Line aresta) {
        this.aresta = aresta;
    }

    public int getVerticeIni() {
        return verticeIni;
    }

    public void setVerticeIni(int verticeIni) {
        this.verticeIni = verticeIni;
    }

    public int getVerticeFim() {
        return verticeFim;
    }

    public void setVerticeFim(int verticeFim) {
        this.verticeFim = verticeFim;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
