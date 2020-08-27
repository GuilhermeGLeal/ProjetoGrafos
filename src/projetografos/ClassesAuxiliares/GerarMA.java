package projetografos.ClassesAuxiliares;

import java.util.List;


public class GerarMA {

    private int matriz[][];

    public GerarMA() {
        
        this.matriz = new int[10][10];
    }
    
    public void geraMatriz(List<Arestas> listaArestas){
        
        int vertIni, vertFim;
        int valor;
        
        for (int i = 0; i < listaArestas.size(); i++) {
            
            vertIni = listaArestas.get(i).getVerticeIni();
            vertFim = listaArestas.get(i).getVerticeFim();
            valor = listaArestas.get(i).getValor();
            
            if(listaArestas.get(i).isDirecioanado()){
                
                if(valor == 0){
                 
                    this.matriz[vertIni][vertFim] = 1;
                    this.matriz[vertFim][vertIni] = -1;

                }
                else{
                    
                    this.matriz[vertIni][vertFim] = valor;
                    this.matriz[vertFim][vertIni] = -valor;
                }
            }
            else{
                
                if(valor == 0){
                    
                    this.matriz[vertIni][vertFim] = 1;
                    this.matriz[vertFim][vertIni] = 1;
                }
                else{
                    
                    this.matriz[vertIni][vertFim] = valor;
                    this.matriz[vertFim][vertIni] = valor;
                }
                
            }
        }
    }
    
    public boolean verificaSimples(){
        
        boolean isSimples = false;
       
        
        return isSimples;
    }
    
    public boolean verificaRegular(){
        
        boolean isRegular = false;
       
        
        return isRegular;
    }
    
    public boolean verificaCompleto(){
        
        boolean isCompleto = false;
       
        
        return isCompleto;
    }
    
   
}
