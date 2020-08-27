package projetografos.ClassesAuxiliares;

import java.util.List;


public class GerarMI {
    
    private int matriz[][];

    public GerarMI() {

        this.matriz = new int[10][10];
    }

    public void geraMatriz(List<Arestas> listaArestas) {

        int vertiIni, vertiFim;
        int valor;

        for (int i = 0; i < listaArestas.size(); i++) {

            vertiIni = listaArestas.get(i).getVerticeIni();
            vertiFim = listaArestas.get(i).getVerticeFim();
            valor = listaArestas.get(i).getValor();
            
            if(listaArestas.get(i).isDirecioanado()){
                
                
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

    public void verificaSimples() {

        // basta olhar diagonal principal
    }

    public void verificaRegular() {

        // verificar se todos os nos tem o mesmo valor de grau
    }

    public void verificaCompleto() {

        // verificar se todos acima e abaixo da diagonal principal tem um ou valor
    }

}
