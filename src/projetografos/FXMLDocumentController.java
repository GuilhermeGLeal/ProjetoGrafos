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
import javax.swing.JOptionPane;
import projetografos.ClassesAuxiliares.Arestas;
import projetografos.ClassesAuxiliares.GerarMA;
import projetografos.ClassesAuxiliares.GerarMI;
import projetografos.ClassesAuxiliares.ListaAdj;
import projetografos.ClassesAuxiliares.ListaCor;
import projetografos.ClassesAuxiliares.NoCor;
import projetografos.ClassesAuxiliares.NoLig;
import projetografos.ClassesAuxiliares.NoLista;


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
    private boolean isLaco;
    @FXML
    private TextArea tablista;
    @FXML
    private Label labelTipos;
    private char matcor[][];
    private String l;
    private boolean multigrafo;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        l="";
        Lista = new ArrayList();
        LisAre = new ArrayList();
        ListLabel = new ArrayList();
        valoresArestas = new ArrayList();
        flag = false;
        direcionado = false;
        valorado = false;
        
        multigrafo = false;
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
                for (int j = 0; j < this.Lista.size(); j++)
                {
                    Lista.get(j).setFill(Paint.valueOf("#FFFFFF"));
                }
                preenchecor();
                evtMostraTabli(null);
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
        labelTipos.setText("");
        multigrafo = false; 
       
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
    
    public void exibeResultadoMILista(GerarMI mi, String multigrafo){
        
        if(LisAre.size() > 0){
            
            String simples = "";
            String regular = "";
            String completo = "";
            
            int qtd;
            
             multigrafo = mi.verificaMultigrafo(LisAre);
                      
            if(multigrafo.isEmpty()){
                  simples = "Não Simples";
                    multigrafo = "Não multigrafo";
            }
              
            else
                simples = "Simples";
            
                        
              
            if(mi.verificaRegular()){
                
                qtd = mi.getList().getInicio().getGrau();
               
                regular = qtd+"regular";
            }
                
            else
                regular = "Não regular";
            
            if(mi.verificaCompleto(LisAre))
                completo = "Completo";
            else
                completo = "Não completo";           
         
               

            labelTipos.setText("           "+"Simples: "+simples+"   Regular:   "+regular+"   Completo: "+completo+" Multigrafo:    "+multigrafo+"");
        }
    }
    
    public void exibeResultadoMA(GerarMA ma){
        
        if(LisAre.size() > 0){
            
            String simples = "";
            String regular = "";
            String completo = "";
            int qtd;
            
            if(ma.verificaSimples())
                simples = "Simples";
            else
                simples = "Não simples";
            
            if(ma.verificaRegular(Lista)){
                
                if(direcionado)
                    qtd = ma.getList().getInicio().getGrau();
                else
                 qtd = ma.getList().getInicio().getGrau()/2;
                regular = qtd+" regular";
            }
                
            else
                regular = "Não regular";
            
            if(ma.verificaCompleto(Lista.size(), LisAre.size()))
                completo = "Completo";
            else
                completo = "Não completo";

            labelTipos.setText("                                "+"Simples: "+simples+"   Regular:    "+regular+"    Completo:   "+completo+"");
        }
       
    }
    
    public String verificaMulti(GerarMI mi){
      
        String result = "";
              
        if(LisAre.size() > 0){
         
            result = mi.verificaMultigrafo(LisAre);
            if(!result.isEmpty()){
                
                if(cbLista.getSelectionModel().getSelectedIndex() == 0 && !multigrafo)
                    cbLista.getSelectionModel().clearAndSelect(1);
                
               multigrafo = true;
            }
            else
                multigrafo = false;
      
        }
       return result;
    }
    
    @FXML
    private void evtMostraTabli(ActionEvent event) 
    {
        String multi;
        int auxespaco;
        GerarMI mi = new GerarMI(direcionado,Lista);    
        GerarMA ma = new GerarMA();
        multi = verificaMulti(mi);
        mi.geraMatriz(LisAre);
        ma.geraMatriz(LisAre);
        
        if(cbLista.getSelectionModel().getSelectedItem().equals("Matriz de adjacência (MA)") && !multigrafo)
        {
           
            exibeResultadoMA(ma);
            String aux="                        ";
            
            
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
         
            exibeResultadoMILista(mi, multi);
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
            String aux = "";
            boolean pri = true;

            exibeResultadoMILista(mi, multi);

            for (int i = 0; i < Lista.size(); i++) {
                aux += i + 1;

                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < Lista.size(); k++) {

                        if (k != i && mi.getMatriz()[i][j] != 0) {

                            if (mi.getMatriz()[k][j] != 0) {
                                aux += " -> ";
                                if (!cbValorado.isSelected()) {
                                    aux += k + 1;
                                } else {
                                    aux += "(" + mi.getMatriz()[k][j] + ")" + " , ";
                                    aux += k + 1;
                                }
                            }
                        }

                    }
                }
                aux += "\n";
            }

            tablista.setText(aux);
        }
        if(matcor!=null)
        {           
            String matco="\n";
            for (int i = 0; i < 10; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    matco+=matcor[i][j]; 
                }
                matco+="\n";
            }
            tablista.setText(tablista.getText()+"\n"+l+matco);
        }
        
    }
    
     private void preenchecor()
    {
        l="";
        ListaCor lis=new ListaCor();
        NoLig auxins;
        NoCor perc;
        boolean existe, verifica;
        matcor = new char[10][11];
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 1; j < 11; j++)
            {
                matcor[i][j]='V';
            }
        }
        for (int i = 0; i < Lista.size(); i++)
        {
           matcor[i][0]=(char)(i+48);
        }   
        ListaAdj listaadj = new ListaAdj();
        listaadj.montarLista(Lista, LisAre);
        listaadj.printar();
        NoLista maior=listaadj.maior();
        lis.insFim(maior);
        auxins=maior.getLis().getInicio();
        while(auxins!=null)
        {           
            lis.insFim(listaadj.perc(auxins.getVertice()));
            auxins=auxins.getProx();
        }
        perc=lis.getInicio();
        lis.insercaodireta();
        
        while(perc!=null && lis.getInicio()!=null)
        {
           prma();
            existe=false;
            for (int i = 1; i < 11 && !existe; i++) 
            {
                if(matcor[perc.getVert().getVert()][i]!='V' && matcor[perc.getVert().getVert()][i]!='X')
                    existe=true;
            }
            if(!existe)
            {
                existe=false;
                for (int i = 1; i < 11 && !existe; i++)
                {                   
                    if(matcor[perc.getVert().getVert()][i]!='X')
                    {
                        
                        matcor[perc.getVert().getVert()][i]=(char)(i+47);
                        cor(perc.getVert().getVert(),matcor[perc.getVert().getVert()][i]);
                        existe=true;
                        auxins=perc.getVert().getLis().getInicio();
                        prma();
                        for (int j = 0; j < 10 && auxins!=null; j++) 
                        {
                            verifica = true;
                            if(lis.vere(auxins.getVertice())==null)
                            {
                                
                                for (int k = 1; k < 11; k++) {
                                    
                                    if(matcor[auxins.getVertice()][k] != 'X' && matcor[auxins.getVertice()][k] != 'V')
                                        verifica = false;
                                }
                             
                                if(verifica)
                                     lis.insFim(listaadj.perc(auxins.getVertice()));
                            }
                            matcor[auxins.getVertice()][i]='X';
                            auxins=auxins.getProx();
                            
                        }
                        lis.insercaodireta();
                        prma();
                    }
                }
                perc=perc.getProx();
            }
            perclis(lis);
            lis.remover();
            
        }  
        System.out.println(l);
    }
    private void cor(int i,int j)
    {
        j-=48;
        if(j==0)
        {
            Lista.get(i).setFill(Paint.valueOf("#f58c22"));
        }
        else if(j==1)
        {
            Lista.get(i).setFill(Paint.valueOf("#f8fc03"));
        }
        else if(j==2)
        {
            Lista.get(i).setFill(Paint.valueOf("#20fc03"));
        }
        else if(j==3)
        {
            Lista.get(i).setFill(Paint.valueOf("#03fce7"));
        }
        else if(j==4)
        {
            Lista.get(i).setFill(Paint.valueOf("#0352fc"));
        }
        else if(j==5)
        {
            Lista.get(i).setFill(Paint.valueOf("#df03fc"));
        }
        else if(j==6)
        {
            Lista.get(i).setFill(Paint.valueOf("#cdcacf"));
        }
        else if(j==7)
        {
            Lista.get(i).setFill(Paint.valueOf("#fc0303"));
        }
        else if(j==8)
        {
            Lista.get(i).setFill(Paint.valueOf("#ffffff"));
        }
        else if(j==9)
        {
            Lista.get(i).setFill(Paint.valueOf("#006e23"));
        }
    }
    
    private void prma()
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                System.out.print(matcor[i][j]); 
            }
            System.out.println("");
        }
        System.out.println("");
    }
    private void perclis(ListaCor lis)
    {
        NoCor aux=lis.getInicio();
        while(aux!=null)
        {
            l+=aux.getVert().getVert()+1;
            if(aux.getProx()!=null)
                l+=" -> ";
            aux=aux.getProx();
        }
        l+="\n";
    }
}
