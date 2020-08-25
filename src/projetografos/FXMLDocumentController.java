package projetografos;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import projetografos.ClassesAuxiliares.Arestas;


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
    private boolean flag = false;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Lista = new ArrayList();
        LisAre = new ArrayList();
        ListLabel = new ArrayList();
        
        this.ultimo=-1;
        this.ultimaAresta = -1;
        labelUltimoAresta.setText("Última Aresta Selecionado: "+ultimaAresta);
        labelUltimo.setText("Último Vertice Selecionado: "+ultimo);
    }   
    
    @FXML
    private void evtCriaCirculo(MouseEvent event) {
        double x, y;

        x = event.getSceneX();
        y = event.getSceneY();

        if (x < pnextra.getLayoutX() - 15 || y < pnextra.getLayoutY() - 15 && !flag) {

                boolean ok = true;
                double aux;
                int i;

                for (i = 0; i < Lista.size() && ok; i++) {
                    aux = Math.sqrt(Math.pow((x - Lista.get(i).getCenterX()), 2) + Math.pow((y - Lista.get(i).getCenterY()), 2));

                    if (Lista.get(i).getRadius() >= aux) {
                        ok = false;
                    }
                }

                if (ok) {
                    criaCirculo(event);
                } else if (ultimo != -1) {
                    criaLinha(i);
                } else {

                    ultimo = i;
                    labelUltimo.setText("Último Vertice Selecionado: " + ultimo);
                }
          

        }
        else
              flag = false;
    }
    
    
    private void criaLinha(int i)
    {
        double xi,yi,xf,yf;
        xi=Lista.get(ultimo-1).getCenterX();
        yi=Lista.get(ultimo-1).getCenterY();
        xf=Lista.get(i-1).getCenterX();
        yf=Lista.get(i-1).getCenterY();
        Line l= new Line(xi,yi,xf,yf);
       
        l.setStrokeWidth(5);
        pnPrincipal.getChildren().add(l);
        LisAre.add(new Arestas(l,ultimo-1,i-1,0));
        l.setOnMouseEntered((event) -> {
                l.setCursor(Cursor.HAND);
                flag = true;
                
        });
        l.setOnMouseClicked((event) -> {
            
            for (int j = 0; j < LisAre.size(); j++) {
                if(LisAre.get(j).getAresta().equals(l))
                    this.ultimaAresta = j;
            }
            labelUltimoAresta.setText("Última Aresta Selecionado: "+ultimaAresta);
            
        });
        this.ultimo=-1;
        labelUltimo.setText("Último Vertice Selecionado: "+ultimo);
          flag = false;

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
            
            pnPrincipal.getChildren().addAll(c,b); 
            flag = false;
        }
    }

    @FXML
    private void evtLimpaUltimo(ActionEvent event) {
        
        this.ultimo = -1;
        labelUltimo.setText("Último Vertice Selecionado: "+ultimo);
    }

    @FXML
    private void evtExcluiVertice(KeyEvent event) {
        
        if(event.getCode() == KeyCode.DELETE){
         
            if(ultimo != -1){
                
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja excluir esse vertice?",ButtonType.YES, ButtonType.NO);
                
                if(a.showAndWait().get() == ButtonType.YES){
                                        
                    pnPrincipal.getChildren().remove(Lista.get(ultimo-1));
                    Lista.remove(ultimo-1);
                    pnPrincipal.getChildren().remove(ListLabel.get(ultimo-1));
                    ListLabel.remove(ultimo-1);
                    this.ultimo = -1;
                    labelUltimo.setText("Último Vertice Selecionado: "+ultimo);
                }
            }
            
            
            if(ultimaAresta != -1){
                
                 Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja excluir essa aresta?",ButtonType.YES, ButtonType.NO);
                
                if(a.showAndWait().get() == ButtonType.YES){
                                        
                    pnPrincipal.getChildren().remove(LisAre.get(ultimaAresta).getAresta());
                    LisAre.remove(ultimaAresta);
                    this.ultimaAresta = -1;
                    labelUltimoAresta.setText("Última Aresta Selecionado: "+ultimaAresta);
                    flag = false;
                }

            }
        }
    }

    @FXML
    private void evtLimparAresta(ActionEvent event) {
        
        this.ultimaAresta = -1;
        labelUltimoAresta.setText("Última Aresta Selecionado: "+ultimaAresta);
    }
    
}
