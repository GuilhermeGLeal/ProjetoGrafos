package projetografos.ClassesAuxiliares;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;


public class GerarMI {

     
    private int matriz[][];
    private boolean direcionado;
    private ListaGrau list;
    private int qtd_vertices;
    private List<QtdAparece> list_qtd;

    public GerarMI(boolean direcioando, List<Circle> vertices) {

        this.matriz = new int[20][20];
        this.direcionado = direcioando;
        this.list = new ListaGrau();
        this.list_qtd = new ArrayList();
        ListaGrau list = new ListaGrau();

        for (int i = 0; i < vertices.size(); i++) {

            list.inserirVertice(i);
        }
        
       
        this.qtd_vertices = vertices.size();
        aumentaGrau();
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }
    
    public void geraMatriz(List<Arestas> listaArestas) {

        int vertiIni, vertiFim;
        int valor;

        for (int i = 0; i < listaArestas.size(); i++) {

            vertiIni = listaArestas.get(i).getVerticeIni();
            vertiFim = listaArestas.get(i).getVerticeFim();
            valor = listaArestas.get(i).getValor();
            
            if(listaArestas.get(i).isDirecioanado()){
                
                
                if(valor == 0){
                                         
                    this.matriz[vertiIni][i] = -1;
                    this.matriz[vertiFim][i] = 1;
                }
                else{
                    
                    this.matriz[vertiIni][i] = -valor;
                    this.matriz[vertiFim][i] = valor;
                }
            }
            else{
                
                if(valor == 0){
                                         
                    this.matriz[vertiIni][i] = 1;
                    this.matriz[vertiFim][i] = 1;
                }
                else{
                    
                    this.matriz[vertiIni][i] = valor;
                    this.matriz[vertiFim][i] = valor;
                }
            }
            
            
        }

    }

    public boolean verificaSimples(List<Arestas> list) {

        boolean isSimples = true;

        for (int i = 0; i < list.size() && isSimples; i++) {

            if (list.get(i).getVerticeIni() == list.get(i).getVerticeFim()) {
                isSimples = false;
            }
        }

        return isSimples;
    }

    public void aumentaGrau(){
        
         for (int i = 0; i < matriz.length; i++) {
            
            for (int j = 0; j < matriz[i].length; j++) {
             
                if (matriz[i][j] == 1) {
                    
                    list.aumentaGrau(i);
                }
            }
        }
    }
    
    public boolean verificaRegular() {
          
        return list.verifcaTodosIguais();
    }

    public boolean verificaCompleto() {
              
        return list.verificaCompleto(qtd_vertices);
    }

    
    public int posLista(QtdAparece aux){
        
        int pos = -1;
        
        for (int i = 0; i < list_qtd.size(); i++) {
            
            if(aux.getVerticeInicio() == list_qtd.get(i).getVerticeInicio() && aux.getVerticeFim()== list_qtd.get(i).getVerticeFim()){
                pos = i;
                i = list_qtd.size();
            }
        }
        
        return pos;
    }
    
    public String verificaMultigrafo(List<Arestas> arest) {

        QtdAparece aux;
        int pos;
        boolean isMultigrafo = true;
        int inicio = 0, fim = 0;
        
        for (int i = 0; i < arest.size(); i++) {
            
            aux = new QtdAparece(arest.get(i).getVerticeIni(), arest.get(i).getVerticeFim());
            pos = posLista(aux);
            
            if(pos== -1)
                list_qtd.add(aux);
            else
                list_qtd.get(pos).setQtd(list_qtd.get(pos).getQtd()+1);
           
        }
        
        for (int i = 0; i < list_qtd.size() && !isMultigrafo; i++) {
            
            if(list_qtd.get(i).getQtd() > 1){
                isMultigrafo = true;
                inicio = list_qtd.get(i).getVerticeInicio();
                fim = list_qtd.get(i).getVerticeInicio();
            }
                
        }
        
        
        if(isMultigrafo)
            return "o grafo é MULTIGRAFO pois há uma ocorrencia dupla com os vertices"+inicio+" e "+fim;
        return "";
    }

}
