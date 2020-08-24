package projetografos;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    
    private void handleButtonAction(ActionEvent event) 
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Lista = new ArrayList();
        LisAre = new ArrayList();
        this.ultimo=-1;
    }   
    
    @FXML
    private void evtCriaCirculo(MouseEvent event) 
    {          
        boolean ok=true;
        double x,y,aux;      
        int i;
        
        x=event.getSceneX();
        y=event.getSceneY();
        for (i = 0; i < Lista.size() && ok; i++)
        {
           aux=Math.sqrt(Math.pow((x-Lista.get(i).getCenterX()),2)+Math.pow((y-Lista.get(i).getCenterY()),2));
           if(Lista.get(i).getRadius()>=aux)
           {
               ok=false;
           }
        }
        if(ok)
            criaCirculo(event);
        else if(ultimo!=-1)
            criaLinha(i);
        else
            ultimo=i;
        
    }
    private void criaLinha(int i)
    {
        double xi,yi,xf,yf;
        xi=Lista.get(ultimo-1).getCenterX();
        yi=Lista.get(ultimo-1).getCenterY();
        xf=Lista.get(i-1).getCenterX();
        yf=Lista.get(i-1).getCenterY();
        Line l= new Line(xi,yi,xf,yf);
        pnPrincipal.getChildren().add(l);
        LisAre.add(new Arestas(l,ultimo-1,i-1,0));
        this.ultimo=-1;
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
            pnPrincipal.getChildren().addAll(c,b); 
        }
    }
    @FXML
    private void evtCria(MouseEvent event) {
    }

    @FXML
    private void evtCriaLinha(MouseEvent event) 
    {
        
    }
    
}
