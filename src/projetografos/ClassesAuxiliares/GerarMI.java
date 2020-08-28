package projetografos.ClassesAuxiliares;

import java.util.List;


public class GerarMI {

   
    
    private int matriz[][];

    public GerarMI() {

        this.matriz = new int[10][10];
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

    public void verificaRegular() {

        // verificar se todos os nos tem o mesmo valor de grau
    }

    public void verificaCompleto() {

        // verificar se todos acima e abaixo da diagonal principal tem um ou valor
    }

    public void verificaMultigrafo() {

        
    }

}
