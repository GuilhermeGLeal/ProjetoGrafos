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
            fim.setProx(caixa);
            fim=caixa;
        }
    }
    public void remover()
    {
        if(this.inicio!=null)
          this.inicio=this.inicio.getProx();
    }
    private void quick(NoCor ini,NoCor fi)
    {
        NoCor i=ini,j=fi;
        int aux;
        while(i!=null && i!=j)
        {
            while(i!=j && i.getVert().getGrau()<=j.getVert().getGrau())
            {
                i=i.getProx();
            }
            if(j.getVert().getGrau()!=i.getVert().getGrau())
            {
                aux=i.getVert().getGrau();
                i.getVert().setGrau(j.getVert().getGrau());
                j.getVert().setGrau(aux);
                j=j.getAnt();
            }
            
            while(i!=j && j.getVert().getGrau()>=i.getVert().getGrau())
            {
                j=j.getAnt();
            }
            if(j.getVert().getGrau()!=i.getVert().getGrau())
            {
                aux=i.getVert().getGrau();
                i.getVert().setGrau(j.getVert().getGrau());
                j.getVert().setGrau(aux);
                i=i.getProx();
            }
            
        }
        if(ini!=i)
            quick(ini,i.getAnt());
        if(fi!=j)
            quick(j.getProx(),fim);        
    }
    public void quickspivo()
    {
        this.quick(inicio, fim);
    }
}
