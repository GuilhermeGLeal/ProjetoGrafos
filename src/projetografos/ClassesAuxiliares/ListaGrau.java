package projetografos.ClassesAuxiliares;


 
public class ListaGrau {

   
    private NoGrau inicio;

    public ListaGrau() {
        this.inicio = null;
    }
    
   
     public NoGrau getInicio() {
        return inicio;
    }

    public void setInicio(NoGrau inicio) {
        this.inicio = inicio;
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
    
    public boolean verifcaTodosIguais(boolean direcionado){
        
        boolean verifica = true;
        
        NoGrau aux = inicio;
        int primeiro = aux.getGrau();
        
        if(direcionado){
            
            while(aux != null && verifica){

               if(aux.getGrau() != primeiro )
                   verifica = false;

               aux = aux.getProx();
           }
        }
        else{

            while(aux != null && verifica){

               if(aux.getGrau()/2 != primeiro /2)
                   verifica = false;

               aux = aux.getProx();
           }
        }
       
        
        return verifica;
    }
    
    
    public boolean verifcaTodosIguais() {

        boolean verifica = true;

        NoGrau aux = inicio;
        int primeiro = aux.getGrau();

        while (aux != null && verifica) {

            if (aux.getGrau() != primeiro) {
                verifica = false;
            }

            aux = aux.getProx();
        }

        return verifica;
    }
    
  
}
