package projetografos.ClassesAuxiliares;

public class ListaAdjLig 
{
    private NoLig inicio;
    
    private void insere(int vert,int valor)
    {
        NoLig caixa= new NoLig(vert, valor);
        
        if(inicio==null)
            inicio=caixa;
        else
        {
            NoLig aux=inicio;
            while(aux.getProx()!=null)
                aux=aux.getProx();
            aux.setProx(caixa);
        }
    }

    public NoLig getInicio() {
        return inicio;
    }
    
}
