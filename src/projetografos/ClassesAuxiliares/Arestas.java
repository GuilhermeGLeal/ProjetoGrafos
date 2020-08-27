package projetografos.ClassesAuxiliares;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Arestas {
    
    private Line aresta;
    private Line direc;
    private int verticeIni;
    private int verticeFim;
    private int valor;
    private boolean direcioanado;
    private Circle laco;
  
    
    public Arestas(){
        
    }
    
    public Arestas(Line aresta, int verticeIni, int verticeFim, int valor, boolean direcioanado,Line direc, Circle laco) {
        
        this.aresta = aresta;
        this.verticeIni = verticeIni;
        this.verticeFim = verticeFim;
        this.valor = valor;
        this.direcioanado=direcioanado;
        this.direc=direc;
        this.laco = laco;
    }

    public Line getDirec() {
        return direc;
    }

    public void setDirec(Line direc) {
        this.direc = direc;
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
    
     public boolean isDirecioanado() {
        return direcioanado;
    }

    public void setDirecioanado(boolean direcioanado) {
        this.direcioanado = direcioanado;
    }
    
     public Circle getLaco() {
        return laco;
    }

    public void setLaco(Circle laco) {
        this.laco = laco;
    }
}
