/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eejercicioaleatorio1;


import static eejercicioaleatorio1.Congruenciales.*;
import static eejercicioaleatorio1.Entrada.*;
import static eejercicioaleatorio1.EntradaMidSquare.*;
import static eejercicioaleatorio1.TestPruebas.*;
import java.text.DecimalFormat;
import java.util.Vector;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class Test {
    
    public static String aleatorio[],aleatorioX[];
    public static Double media;
    DecimalFormat df = new DecimalFormat("#.####");
    
    
    public void MidSquare(){
        resultadoMid.setText("");
        String c, x;
        int i, ext, n=0, cant;
        
        c=semillaMid.getText();
        cant=Integer.parseInt(iteracionesMid.getText());
        aleatorio=new String[cant];
        
        if (c.length()%2==0){
            while(n<cant){
            i=(int) Math.pow(Integer.parseInt(c),2); 
            ext=c.length();
            c=String.valueOf(i);
            if(c.length()%2!=0){
                c="0"+c;
            }
            ext=c.length()-ext;
            c=c.substring(ext/2,(ext/2)+ext);
            x="0."+c;
            aleatorio[n]=x;                        
            resultadoMid.setText(resultadoMid.getText()+aleatorio[n]+"\n");
            n++;
            }           
            
        }else {
            JOptionPane.showMessageDialog(null,"Solo ingrese numeros pares"); 
            semillaMid.setText("");
        }
        
        
    }
    
    public void congruencial(){
        int n=1, cant, i;
        salida.setText("");
        
        i=Integer.parseInt(semillacon.getText());
        cant=Integer.parseInt(iteracioncon.getText());
        aleatorio=new String[cant];
        aleatorio[0]=String.valueOf(df.format(i));
        salida.setText(salida.getText()+aleatorio[0]+"\n");
        
        while(n<cant){
             i=((5*i)+1)%220;             
             aleatorio[n]=String.valueOf(df.format(i));
             salida.setText(salida.getText()+aleatorio[n]+"\n");
             n++;
        }
    }
    
    public void Pseudoaletorio(){
        resultado1.setText("");
        resultado2.setText("");
        int n = 0, cant,i=2;
        String c;
        Object result, result2;
        String expression, expression2, remplazo, remplazo2;
        //JLabel etiqueta = new JLabel(); 
        //JTextField nombre;
        /*
        double U[] = new double[10];        
        U[0] = 9.0;  */   
        /*        
        List<String> X2 = new ArrayList<>();
        List<String> U2 = new ArrayList<>();*/
        //Scanner leer = new Scanner (System.in);
        ScriptEngineManager manager = new ScriptEngineManager(); 
        ScriptEngine engine = manager.getEngineByName("js"); 
        
        c=semillaPseudo.getText();
        cant=Integer.parseInt(cantidadPseudo.getText());
        expression =ecuacion1.getText(); 
        expression2 =ecuacion2.getText(); 
        aleatorio=new String[cant];
        aleatorioX=new String[cant];
        
        aleatorioX[0]=c;
        
        try { 
         
        remplazo=expression.replace("x",c);
        result = engine.eval(remplazo);
        //c=Integer.parseInt(result.toString());
        remplazo2=expression2.replace("x",result.toString());
        result2 = engine.eval(remplazo2);  
        aleatorio[0]=(result2.toString());
        
        //etiqueta.setText(X.toString()); 
        //resultado.setViewportView(etiqueta);
        /*
        System.out.println(remplazo+" = "+result);  
        System.out.println(remplazo2+" = "+result2); */
        while(n<cant){
            if(n!=0){
            remplazo=expression.replace("x",result.toString());
            result = engine.eval(remplazo);
            remplazo2=expression2.replace("x",result.toString());
            result2 = engine.eval(remplazo2);
            aleatorioX[n]=(result.toString());  
            aleatorio[n]=(result2.toString());
            }
            resultado1.setText(resultado1.getText()+aleatorioX[n]+"\n");
            resultado2.setText(resultado2.getText()+aleatorio[n]+"\n");
            n++;
            //U2.add(X2.get(n)/33);
        }
         
        } catch(ScriptException se) { 
        System.out.println(se); 
        }
    }
    
    public void PruebaPromedios(){
        double H=0.5, suma=0;
        double LS,LI;
        Vector X=new Vector();
        for(int i=0;i<aleatorio.length;i++){
            suma+=Double.parseDouble(aleatorio[i]);
        }
        media=suma/aleatorio.length;
        X.add("La media de los numeros es: "+media);
        
        LS=H+(1.96/(12*Math.sqrt(aleatorio.length)));
        LI=H-(1.96/(12*Math.sqrt(aleatorio.length)));
        X.add("\nLimite superior: "+LS);
        X.add("\nLimite inferior: "+LI);
        if(media<LS && media>LI)
            X.add("\nLa Hipotesis se cumple, dentro de los limites");
        else
            X.add("\nLa Hipotesis no se cumple, fuera del rango");
            
        SalidaPromedio.setText(String.valueOf(X));
    }
    
    public void PruebaVarianzas(){
        double H=1/12,suma=0,varianza;
        double LS,LI;
        Vector X=new Vector();
        for(int i=0;i<aleatorio.length;i++)
            suma+=(Math.pow((Double.parseDouble(aleatorio[i])-media),2));
        
        varianza=suma/(aleatorio.length-1);
        X.add("La Varianza de los numeros es: "+varianza);
        
        LS=(0.025*(aleatorio.length-1))/(12*(aleatorio.length-1));
        LI=(0.975*(aleatorio.length-1))/(12*(aleatorio.length-1));
        X.add("\nLimite superior: "+LS);
        X.add("\nLimite inferior: "+LI);
        SalidaVarianza.setText(String.valueOf(X));
    }
    public void ChiCuadrado(){
        
    }
    
}
