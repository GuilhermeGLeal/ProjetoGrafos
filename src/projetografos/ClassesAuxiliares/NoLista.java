package projetografos.ClassesAuxiliares;

public class NoLista 
{
    private int valor;
    private int vert;
    private NoLista prox;
    private ListaAdj lis;

    public ListaAdj getLis() {
        return lis;
    }

    public void setLis(ListaAdj lis) {
        this.lis = lis;
    }

    public int getValor() 
    {
        return valor;
    }

    public void setValor(int valor)
    {
        this.valor = valor;
    }

    public int getVert() 
    {
        return vert;
    }

    public void setVert(int vert)
    {
        this.vert = vert;
    }

    public NoLista getProx() 
    {
        return prox;
    }

    public void setProx(NoLista prox) 
    {
        this.prox = prox;
    }
    
}
