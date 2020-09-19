package projetografos.ClassesAuxiliares;

public class NoLista 
{
    private int grau;
    private int vert;
    private NoLista prox;
    private ListaAdjLig lis;

    public NoLista() 
    {
        this.grau=this.vert=0;
        this.prox=null;
        this.lis=new ListaAdjLig();
    }
    
    public ListaAdjLig getLis() {
        return lis;
    }

    public void setLis(ListaAdjLig lis) {
        this.lis = lis;
    }

    public int getGrau() 
    {
        return grau;
    }

    public void setGrau(int grau)
    {
        this.grau = grau;
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
