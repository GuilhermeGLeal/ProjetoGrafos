package projetografos.ClassesAuxiliares;


 
public class ListaGrau {
    
    private NoGrau inicio;

    public ListaGrau() {
        this.inicio = null;
    }
    
    public void inserirVertice(int vertice){
        
        NoGrau novo = new NoGrau(vertice, 0, inicio);
        inicio = novo;
    }
    
    public void aumentaGrau(int vertice){
        
        NoGrau aux = inicio;
        
        while(aux.getVertice() != vertice)
            aux = aux.getProx();
        
        aux.setGrau(aux.getGrau()+1);
    }
    
    public boolean verifcaTodosIguais(){
        
        boolean verifica = true;
        
        NoGrau aux = inicio;
        int primeiro = aux.getGrau();
        
        while(aux != null && verifica){
            
            if(aux.getGrau() != primeiro)
                verifica = false;
        }
        
        return verifica;
    }
    
    
}
