package projetografos.ClassesAuxiliares;

public class NoCor 
{
    private NoLista vert;
    private NoCor prox;
    private NoCor ant;

    public NoCor(NoLista vert) 
    {
        this.vert = vert;
        this.prox=this.ant=null;
    }
    
    public NoCor getAnt() {
        return ant;
    }

    public void setAnt(NoCor ant) {
        this.ant = ant;
    }

    public NoLista getVert() {
        return vert;
    }

    public void setVert(NoLista vert) {
        this.vert = vert;
    }

    public NoCor getProx() 
    {
        return prox;
    }

    public void setProx(NoCor prox) 
    {
        this.prox = prox;
    }
}
