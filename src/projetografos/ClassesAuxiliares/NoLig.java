
package projetografos.ClassesAuxiliares;

public class NoLig
{
    private NoLig prox;
    private int vertice;
    private int valor;

    public NoLig() 
    {
        this.prox=null;
        this.vertice=this.valor=0;
    }
    
    public NoLig(int vert,int valor)
    {
        this.vertice=vert;
        this.valor=valor;
        this.prox=null;
    }
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public NoLig getProx() {
        return prox;
    }

    public void setProx(NoLig prox) {
        this.prox = prox;
    }

    public int getVertice() {
        return vertice;
    }

    public void setVertice(int vertice) {
        this.vertice = vertice;
    }
    
}
