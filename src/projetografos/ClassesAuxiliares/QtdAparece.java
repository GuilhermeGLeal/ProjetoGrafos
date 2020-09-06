 package projetografos.ClassesAuxiliares;


public class QtdAparece {
    
    private int verticeInicio;
    private int verticeFim;
    private int qtd;

    public QtdAparece(int verticeInicio, int verticeFim) {
        this.verticeInicio = verticeInicio;
        this.verticeFim = verticeFim;
        this.qtd = 1;
    }

    public int getVerticeInicio() {
        return verticeInicio;
    }

    public void setVerticeInicio(int verticeInicio) {
        this.verticeInicio = verticeInicio;
    }

    public int getVerticeFim() {
        return verticeFim;
    }

    public void setVerticeFim(int verticeFim) {
        this.verticeFim = verticeFim;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    
}
