package projetografos;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javax.swing.JOptionPane;
import projetografos.ClassesAuxiliares.Arestas;
import projetografos.ClassesAuxiliares.GerarMA;
import projetografos.ClassesAuxiliares.GerarMI;


public class FXMLDocumentController implements Initializable 
{
    @FXML
    private AnchorPane pnPrincipal;
    private List<Circle> Lista;
    private int ultimo;
    private List<Arestas> LisAre;
    @FXML
    private AnchorPane pnextra;
    @FXML
    private CheckBox cbValorado;
    @FXML
    private CheckBox cbDire;
    @FXML
    private JFXComboBox<String> cbLista;
    @FXML
    private Label labelUltimo;
    private int ultimaAresta;
    private List<Label> ListLabel;
    @FXML
    private Label labelUltimoAresta;
    private boolean flag;
    private boolean direcionado;
    private boolean valorado;
    private List<Label> valoresArestas;
    //private int numVertice = 1;
    private boolean isLaco;
    @FXML
    private TextArea tablista;
    @FXML
    private Label labelTipos;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Lista = new ArrayList();
        LisAre = new ArrayList();
        ListLabel = new ArrayList();
        valoresArestas = new ArrayList();
        flag = false;
        direcionado = false;
        valorado = false;
        
        this.ultimo=-1;
        this.ultimaAresta = -1;
        labelUltimoAresta.setText("Aresta selecionada entre: "+ultimaAresta);
        labelUltimo.setText("Vértice selecionado: "+ultimo);
        
        List<String> opcoes = new ArrayList();
        opcoes.add("Matriz de adjacência (MA)");
        opcoes.add("Matriz de incidência (MI)");
        opcoes.add("Lista adjacência");
        ObservableList<String> list = FXCollections.observableArrayList(opcoes);        
        cbLista.setItems(list);
        cbLista.getSelectionModel().select("Matriz de adjacência (MA)");
        
    }   
    
    @FXML
    private void evtCriaCirculo(MouseEvent event) 
    {
        double x, y;

        x = event.getSceneX();
        y = event.getSceneY();

        if (x < pnextra.getLayoutX() - 15 || y < pnextra.getLayoutY() - 15 && (!flag || ultimaAresta == -1)) 
        {
            boolean ok = true;
            double aux;
            int i;

            for (i = 0; i < Lista.size() && ok; i++) 
            {
                aux = Math.sqrt(Math.pow((x - Lista.get(i).getCenterX()), 2) + Math.pow((y - Lista.get(i).getCenterY()), 2));
                if (Lista.get(i).getRadius() >= aux) 
                {
                    ok = false;
                }
            }

            if (ok) 
            {
                criaCirculo(event);
            } 
            else if (ultimo != -1) 
            {
                criaFormas(i);              
            } 
            else 
            {
                ultimo = i;
                labelUltimo.setText("Vértice selecionado: "+ultimo);
                Lista.get(i-1).setFill(Paint.valueOf("#FF0000"));
            }         
        }
        else
            flag = false;
    }
    
    private int pegaValor() {

        boolean aux = true;
        int num = 0;

        try {

            num = Integer.valueOf(JOptionPane.showInputDialog(null, " Entre com o valor da aresta: "));
            aux = false;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Número inserido inválido!!!");
            aux = true;
        }

        if(!aux)
            return num;
        return 0;
    }
    
    private void criarValor(int valor, Line l) {

        double finalX, finalY;
        finalX = l.getEndX() + l.getStartX();
        finalY = l.getEndY() + l.getStartY();
        Label valorado = new Label();
        valorado.setText(""+valor);
        valorado.setTranslateX(finalX/2);
        valorado.setTranslateY(finalY/2);
        valorado.setTextFill(Paint.valueOf("#e53242"));
        valoresArestas.add(valorado);
        pnPrincipal.getChildren().add(valorado);
       
    }
    
    private Line criarDirecao(int i){
        
        double xi, yi, xf, yf;
        double difX, difY;
        double maiorX, maiorY, menorX, menorY;
        xi = Lista.get(ultimo - 1).getCenterX() - 5;
        yi = Lista.get(ultimo - 1).getCenterY() -5 ;
        xf = Lista.get(i - 1).getCenterX() - 5;
        yf = Lista.get(i - 1).getCenterY() - 5;
        
        maiorX = xi > xf ? xi: xf;
        maiorY = yi > yf ? yf : yi;
        menorX = xi < xf ? xi : xf;
        menorY = yi > yf ? yf : yi;
        
        difX = maiorX - menorX;
        difY = maiorY - menorY;
        
        difX = difX / 100;
        difY = difY / 100;
        Polygon poligoon = new Polygon();
        poligoon.getPoints().addAll(new Double[]{
            xf, yf,
            
        });
        Line aux = new Line(xi,yi, xf, yf);
        aux.setStrokeWidth(5);
        aux.setFill(Paint.valueOf("#0050c1"));
 
        return aux;
    }
    
    
    private void CriaCirculo(int i) {

        double centerX, centerY, radius;
        Arestas auxA = new Arestas();
        Circle cir = new Circle();
        centerX = Lista.get(i - 1).getCenterX() - 20;
        centerY = Lista.get(i - 1).getCenterY() - 20;
        radius = 20;
        cir.setCenterX(centerX);
        cir.setCenterY(centerY);
        cir.setRadius(radius);
        cir.setFill(Paint.valueOf("#f6f6f6"));
        cir.setStroke(Paint.valueOf("#000000"));

        int valor = 0;

        if (valorado) {

            valor = pegaValor();
            auxA.setValor(valor);
        }

        if (!valorado || valor != 0) {

            auxA.setLaco(cir);
            auxA.setVerticeIni(ultimo - 1);
            auxA.setVerticeFim(i - 1);

            if (!direcionado) {

                auxA.setDirecioanado(false);
            } else {

                auxA.setDirecioanado(true);
            }

                      
            LisAre.add(auxA);
            

            cir.setOnMouseEntered((event) -> {
                cir.setCursor(Cursor.HAND);
                flag = true;

            });

            cir.setOnMouseClicked((event) -> {

                int aux, aux2;
                for (int j = 0; j < LisAre.size(); j++) {
                    if (LisAre.get(j).getLaco() != null && LisAre.get(j).getLaco().equals(cir)) {
                        this.ultimaAresta = j;
                    }
                }

                isLaco = true;
                aux = LisAre.get(ultimaAresta).getVerticeIni() + 1;
                LisAre.get(ultimaAresta).getLaco().setStroke(Paint.valueOf("#1842bf"));
                labelUltimoAresta.setText("Aresta selecionada entre: " + i + " e " + i);

            });

            pnPrincipal.getChildren().add(cir);

            Lista.get(ultimo - 1).setFill(Paint.valueOf("#FFFFFF"));
            this.ultimo = -1;
            labelUltimo.setText("Vértice selecionado: " + ultimo);
            flag = false;
        }

    }

    private void criaLinha(int i) {

        double xi, yi, xf, yf;
        Arestas auxA = new Arestas();
        xi = Lista.get(ultimo - 1).getCenterX() - 5;
        yi = Lista.get(ultimo - 1).getCenterY() - 5;
        xf = Lista.get(i - 1).getCenterX() - 5;
        yf = Lista.get(i - 1).getCenterY() - 5;
        Line l = new Line(xi, yi, xf, yf), lAux = null;
        l.setStrokeWidth(5);
        int valor = 0;
        
        auxA.setAresta(l);
        auxA.setVerticeIni(ultimo - 1);
        auxA.setVerticeFim(i - 1);

        if (!direcionado) {

            auxA.setDirec(null);
            auxA.setDirecioanado(false);

            if (valorado) {

                valor = pegaValor();
                auxA.setValor(valor);

            }
        } else {

          
            auxA.setDirecioanado(true);

            if (valorado) {

                valor = pegaValor();
                auxA.setValor(valor);

            }
        }

        if (!valorado || valor != 0) {

            LisAre.add(auxA);
            if (valorado) {
                criarValor(valor, l);
            }

            l.setOnMouseEntered((event) -> {
                l.setCursor(Cursor.HAND);
                flag = true;

            });
            l.setOnMouseClicked((event) -> {

                int aux, aux2;
                for (int j = 0; j < LisAre.size(); j++) {
                    if (LisAre.get(j).getAresta() != null && LisAre.get(j).getAresta().equals(l)) {
                        this.ultimaAresta = j;
                    }
                }

                isLaco = false;
                aux = LisAre.get(ultimaAresta).getVerticeIni() + 1;
                aux2 = LisAre.get(ultimaAresta).getVerticeFim() + 1;
                LisAre.get(ultimaAresta).getAresta().setStroke(Paint.valueOf("#1842bf"));
                labelUltimoAresta.setText("Aresta selecionada entre: " + aux + " e " + aux2);

            });
            if (lAux != null) {
                pnPrincipal.getChildren().add(lAux);
            }

            pnPrincipal.getChildren().add(l);

            Lista.get(ultimo - 1).setFill(Paint.valueOf("#FFFFFF"));
            this.ultimo = -1;
            labelUltimo.setText("Vértice selecionado: " + ultimo);
            flag = false;
        }

       
    }

    private void criaFormas(int i) {

        if (i != ultimo) {

            criaLinha(i);
            
        } else {
            CriaCirculo(i);
        }

        evtMostraTabli(null);
    }

    private void criaCirculo(MouseEvent event)
    {
        double x,y;
        x=event.getSceneX();
        y=event.getSceneY();
        
        if(Lista.size()<10)
        {
            Circle c = new Circle();
            Label b = new Label();

            c.setCenterX(x);
            c.setCenterY(y);
            c.setRadius(20);
            c.setFill(Paint.valueOf("#FFFFFF"));
            c.setStroke(Paint.valueOf("#000000"));
            Lista.add(c);
            b.setText("v"+Lista.size());
            b.setTranslateX(c.getCenterX()-6);
            b.setTranslateY(c.getCenterY()-6);
            ListLabel.add(b);
            if(ultimo != -1){
                 Lista.get(ultimo-1).setFill(Paint.valueOf("#FFFFFF"));
                 this.ultimo = -1;
                 labelUltimo.setText("Vértice selecionado: "+ultimo);   
            }
               
            pnPrincipal.getChildren().addAll(c,b); 
            flag = false;
            
            evtMostraTabli(null);
        }
    }

    @FXML
    private void evtLimpaUltimo(ActionEvent event) {
        
        if(ultimo != -1){
            
            Lista.get(ultimo-1).setFill(Paint.valueOf("#FFFFFF"));
            this.ultimo = -1;
            labelUltimo.setText("Vértice selecionado: "+ultimo);
        }
        
    }

    @FXML
    private void evtExcluiVertice(KeyEvent event) {
        
        boolean aux = false;
        if(event.getCode() == KeyCode.DELETE){
         
            if(ultimo != -1){
                
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja excluir esse vertice?",ButtonType.YES, ButtonType.NO);
         
                if (a.showAndWait().get() == ButtonType.YES) {

                    pnPrincipal.getChildren().remove(Lista.get(ultimo - 1));
                    Lista.remove(ultimo - 1);
                    pnPrincipal.getChildren().remove(ListLabel.get(ultimo - 1));
                    ListLabel.remove(ultimo - 1);

                    for (int i = 0; i < LisAre.size(); i++) {

                        if (LisAre.get(i).getVerticeIni() == ultimo - 1 || LisAre.get(i).getVerticeFim() == ultimo - 1) {

                            if (LisAre.get(i).getLaco() != null) {
                                pnPrincipal.getChildren().remove(LisAre.get(i).getLaco());
                                LisAre.get(i).setLaco(null);
                            }

                            if (LisAre.get(i).getAresta() != null) {
                                pnPrincipal.getChildren().remove(LisAre.get(i).getAresta());
                                LisAre.get(i).setAresta(null);
                            }

                        }

                    }

                    for (int i = 0; i < LisAre.size(); i++) {
                        
                        if(aux){
                              i = 0;
                              aux = false;
                        }
                          
                        
                          if(LisAre.get(i).getAresta() == null && LisAre.get(i).getLaco() == null){
                                LisAre.remove(i);
                                aux = true;
                          }
                              
                    }
                    
                    this.ultimo = -1;
                    labelUltimo.setText("Vértice selecionado: " + ultimo);

                    evtMostraTabli(null);
                }
            }

            
            if(ultimaAresta != -1){
                
                 Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja excluir essa aresta?",ButtonType.YES, ButtonType.NO);
                
                if(a.showAndWait().get() == ButtonType.YES){
                                        
                    if(isLaco){
                        
                        pnPrincipal.getChildren().remove(LisAre.get(ultimaAresta).getLaco());
                        LisAre.get(ultimaAresta).setLaco(null);
                    }                         
                    else{
                         pnPrincipal.getChildren().remove(LisAre.get(ultimaAresta).getAresta());
                          LisAre.get(ultimaAresta).setAresta(null);
                    }
                     
                   
                    if(LisAre.get(ultimaAresta).getAresta() == null && LisAre.get(ultimaAresta).getLaco()== null)
                        LisAre.remove(ultimaAresta);
                    this.ultimaAresta = -1;
                    labelUltimoAresta.setText("Aresta selecionada entre: "+ultimaAresta);
                    flag = false;
                    isLaco = false;
                    
                    evtMostraTabli(null);
                }

            }
        }
    }

    @FXML
    private void evtLimparAresta(ActionEvent event) {
        
        if(ultimaAresta != -1){
            
            if( LisAre.get(ultimaAresta).getAresta() != null)
             LisAre.get(ultimaAresta).getAresta().setStroke(Paint.valueOf("#000000"));
            else
                  LisAre.get(ultimaAresta).getLaco().setStroke(Paint.valueOf("#000000"));
            this.ultimaAresta = -1;
             labelUltimoAresta.setText("Aresta selecionada entre: "+ultimaAresta);
         }
       
        
    }

    public void apagaTudo(ActionEvent event){
        
        evtLimpaUltimo(event);
        evtLimparAresta(event);
        
        for (int i = 0; i < Lista.size(); i++) {
            
            pnPrincipal.getChildren().remove(Lista.get(i));
            pnPrincipal.getChildren().remove(ListLabel.get(i));
        }
        
        for (int i = 0; i < LisAre.size(); i++) {
            
            pnPrincipal.getChildren().remove(LisAre.get(i).getAresta());
            pnPrincipal.getChildren().remove(LisAre.get(i).getLaco());
        }
        
        for (int i = 0; i < valoresArestas.size(); i++) {
        
            pnPrincipal.getChildren().remove(valoresArestas.get(i));
        }
        
        tablista.clear();
        Lista.clear();
        ListLabel.clear();
        LisAre.clear();
        valoresArestas.clear();
        //numVertice = 1;
        
       
    }
    
    @FXML
    private void evtDire(ActionEvent event) {
              
        apagaTudo(event);
        
        if(cbDire.isSelected()){
            
            direcionado = true;
        }
        else
            direcionado = false;
    }

    @FXML
    private void evtValorado(ActionEvent event) {
        
        apagaTudo(event);
        
        if(cbValorado.isSelected()){
            
            valorado = true;
        }
        else
            valorado = false;
    }

    @FXML
    private void evtMostraTabli(ActionEvent event) 
    {
        int auxespaco;
        int gambiarradois;
        labelTipos.setText("Simples                                        Multigrafo                                        Regular                                        Completo");
        if(cbLista.getSelectionModel().getSelectedItem().equals("Matriz de adjacência (MA)"))
        {
            String aux="                        ";
            GerarMA ma=new GerarMA();
            ma.geraMatriz(LisAre);
            for(int i=0;i<Lista.size();i++)
            {
                aux+=i+1+"                   ";
            }
            aux+="\n";
            for(int i=0;i<Lista.size();i++)
            {
                if(i!=9)
                  aux+=i+1+"                      ";
                else
                  aux+=i+1+"                    ";
                for(int j=0;j<Lista.size();j++)
                {
                    String gambiarra=""+ma.getMatriz()[i][j];                  
                    aux+=ma.getMatriz()[i][j];
                    if(ma.getMatriz()[i][j]>10)
                        auxespaco=gambiarra.length()+gambiarra.length()-1;
                    else
                        auxespaco=gambiarra.length();
                    for (int k = auxespaco; k < 20; k++)
                    {
                        aux+=" ";
                    }
                }
                aux+="\n";
                System.out.println("");
            }          
            tablista.setText(aux);
            
        }
        else if(cbLista.getSelectionModel().getSelectedItem().equals("Matriz de incidência (MI)"))
        {
            String aux="                        ";
            GerarMI mi=new GerarMI();
            mi.geraMatriz(LisAre);
            System.out.print(" ");
            
            for (int i = 0; i < LisAre.size(); i++) 
            {                
                aux+=(LisAre.get(i).getVerticeIni()+1)+","+(LisAre.get(i).getVerticeFim()+1)+"                ";
            }
            aux+="\n";
            for (int i = 0; i < Lista.size(); i++)
            {
                if(i!=9)
                  aux+=i+1+"                      ";
                else
                  aux+=i+1+"                    ";
                for (int j = 0; j < LisAre.size(); j++) 
                {              
                    String gambiarra="";
                    
                    if(LisAre.get(j).getVerticeIni()==i)
                    {
                        if(LisAre.get(j).getValor()==0)
                            gambiarra+=1;
                        else if(LisAre.get(j).isDirecioanado())
                            gambiarra+=-1*(LisAre.get(j).getValor());
                        else
                            gambiarra+=LisAre.get(j).getValor();   
                    }                       
                    else if(LisAre.get(j).getVerticeFim()==i)
                    {
                        if(LisAre.get(j).getValor()==0)
                            gambiarra+=1;
                        else
                            gambiarra+=LisAre.get(j).getValor();                       
                    }
                    else
                        gambiarra+="0";
                    
                    aux+=gambiarra;
                    if(LisAre.get(j).getValor()>10)
                        auxespaco=gambiarra.length()+gambiarra.length()-1;
                    else
                        auxespaco=gambiarra.length();
                    for (int k = auxespaco; k < 20; k++)
                        aux+=" ";
                }
                aux+="\n";                        
            }
            tablista.setText(aux);
        }
        else if(cbLista.getSelectionModel().getSelectedItem().equals("Lista adjacência"))
        {
            String aux="";
            GerarMA lisadj = new GerarMA();
            lisadj.geraMatriz(LisAre);
            for (int i = 0; i < Lista.size(); i++) 
            {
                  aux+=i+1;
                for (int j = 0; j < 10; j++) 
                {
                   if(lisadj.getMatriz()[i][j]!=0)
                   {
                      aux+=" -> ";
                      if(!cbValorado.isSelected())
                        aux+=j+1;
                      else
                      {
                          aux+="("+lisadj.getMatriz()[i][j]+")"+" , ";
                          aux+=j+1;
                      }
                   }     
                }
                aux+="\n";
            }
            tablista.setText(aux);
        }
    }
    
}
