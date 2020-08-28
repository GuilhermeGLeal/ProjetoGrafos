package projetografos.ClassesAuxiliares;

import java.util.List;
import javafx.scene.shape.Circle;


public class GerarMA {

    private int matriz[][];
    private boolean direcionado;

    public GerarMA() {
        
        this.matriz = new int[10][10];
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public void geraMatriz(List<Arestas> listaArestas){
        
        int vertIni, vertFim;
        int valor;
        if(listaArestas.size()>0)
        {
            if(listaArestas.get(0).isDirecioanado())
                direcionado = true;
            else
                direcionado = false;

            for (int i = 0; i < listaArestas.size(); i++) {

                vertIni = listaArestas.get(i).getVerticeIni();
                vertFim = listaArestas.get(i).getVerticeFim();
                valor = listaArestas.get(i).getValor();

                if (direcionado) {

                    if (valor == 0) {

                        this.matriz[vertIni][vertFim] = 1;

                    } else {

                        this.matriz[vertIni][vertFim] = valor;
                    }
                } else {

                    if (valor == 0) {

                        this.matriz[vertIni][vertFim] = 1;
                        this.matriz[vertFim][vertIni] = 1;
                    } else {

                        this.matriz[vertIni][vertFim] = valor;
                        this.matriz[vertFim][vertIni] = valor;
                    }

                }
            }
        }      
    }
    
    public boolean verificaSimples(){
        
        boolean isSimples = true;
        
        for (int i = 0; i < matriz.length && isSimples; i++) {
            
            if(matriz[i][i] == 1)
                   isSimples = false;
        }
        
        return isSimples;
    }
    
    public boolean verificaRegular(List<Circle> vertices) {

        ListaGrau list = new ListaGrau();

        for (int i = 0; i < vertices.size(); i++) {

            list.inserirVertice(i);
        }

        if (direcionado) {

            for (int i = 0; i < matriz.length; i++) {

                for (int j = 0; j < matriz[i].length; j++) {

                    if (matriz[i][j] == 1) {
                        list.aumentaGrau(j);
                    }
                }
            }

        } else {

            for (int i = 0; i < matriz.length; i++) {

                for (int j = 0; j < matriz[i].length; j++) {

                    if (matriz[i][j] == 1) {
                        list.aumentaGrau(i);
                        list.aumentaGrau(j);
                    }
                }
            }
        }

        return list.verifcaTodosIguais();
    }

    public boolean verificaCompleto(){
        
        boolean isCompleto = true;
       
        for (int i = 0; i < matriz.length && isCompleto; i++) {
            
            for (int j = 0; j < matriz[i].length; j++) {
                
                if(i != j){
                    
                    if(matriz[i][j] != 1)
                        isCompleto = false;
                }
            }
        }
        
        return isCompleto;
    }
    
    public String gerarTipo(List<Circle> vertices){
        
        boolean isSimples = verificaSimples();
        boolean isReguar = verificaRegular(vertices);
        boolean isCompleto = verificaCompleto();
        
        
        if(isSimples)
            return "O grafo é SIMPLES pois a diagonal principal está zerada!!";
        else if(isReguar)
            return "O grafo é REGULAR pois todas as linhas tem a mesma quantidade de ligações!";
        else if(isCompleto)
            return "O grafo é COMPLETO pois todas as posições da matriz sem ser a diagonal principal, possuem valores";
           
        
        return "";
    }
    
   
}
