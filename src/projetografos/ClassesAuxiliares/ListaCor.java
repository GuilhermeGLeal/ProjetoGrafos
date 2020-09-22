package projetografos.ClassesAuxiliares;

public class ListaCor 
{
    private NoCor inicio;
    private NoCor fim;

    public ListaCor()
    {
        this.inicio=null;
    }

    public NoCor getInicio() {
        return inicio;
    }

    public void setInicio(NoCor inicio) {
        this.inicio = inicio;
    }

    public NoCor getFim() {
        return fim;
    }

    public void setFim(NoCor fim) {
        this.fim = fim;
    }
    public NoCor vere(int vert)
    {
        NoCor aux=inicio;
        while(aux!=null && aux.getVert().getVert()!=vert)
        {
            aux=aux.getProx();
        }
        return aux;
    }
    public void insFim(NoLista info)
    {
        NoCor caixa = new NoCor(info);
        if(fim==null)
            inicio=fim=caixa;
        else
        {
            caixa.setAnt(fim);
            fim.setProx(caixa);
            fim=caixa;
        }
    }
    public void remover()
    {
        if(this.inicio!=null)
          this.inicio=this.inicio.getProx();
        
        if(this.inicio == null)
            this.fim = null;
        
    }
    public void insercaodireta()
    {
        NoCor ppos,pi=inicio.getProx();
        int aux;
        NoLista aux2;
        while(pi!=null)
        {
            aux=pi.getVert().getGrau();
            aux2=pi.getVert();
            ppos=pi;
            while(ppos!=inicio && aux>ppos.getAnt().getVert().getGrau())
            {
                ppos.setVert(ppos.getAnt().getVert());
                ppos=ppos.getAnt();
            }
            ppos.setVert(aux2);
            pi=pi.getProx();
        }
    }
}
