package projetografos.ClassesAuxiliares;


public class NoGrau {
    
    private int vertice;
    private int grau;
    private NoGrau prox;

    public NoGrau(int vertice, int grau, NoGrau prox) {
        this.vertice = vertice;
        this.grau = grau;
        this.prox = prox;
    }

    public NoGrau() {
    }

    public int getVertice() {
        return vertice;
    }

    public void setVertice(int vertice) {
        this.vertice = vertice;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public NoGrau getProx() {
        return prox;
    }

    public void setProx(NoGrau prox) {
        this.prox = prox;
    }

   
}
