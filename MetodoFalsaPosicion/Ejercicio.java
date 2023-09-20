package MetodoFalsaPosicion;

import com.singularsys.jep.JepException;
import javax.swing.JOptionPane;
import org.nfunk.jep.JEP;

//Desarrlloar un problema que encuentre raices mediante el metodo de biseccion 
public class Ejercicio {
    
    //declaramos las variables que vamos a utilizar
    JEP jep;
    //la funcion que va a recibir
    private String entrada="";
    private double resultado = 0.0; 
    private String error;
    private double errorOperacion;
    private double error1;
    
    //variables para el metodo de falsa posicion
    private double xi ;
    private double xu ;
    private double xr;
    private double Fxi;
    private double Fxu;
    private double Fxr;
    private double test;
    private double xrAnterior;
    private double testAnterior;
    
public Ejercicio(){
        
    }


//recibe los valores del intervalo a
    public void setIntervaloA(double xi){
        this.xi= xi;
    }
    
//recibe los valores del intervalo a
    public void setIntervaloB(double xu){
        this.xu= xu;
    }
    
    
    public void setFuncion(String entrada){
        this.entrada = entrada;
    }

    
    public double getResultadoFuncion(){

        return this.resultado;
    }
    
    public String getError(){
        return this.error;
    }
    
    
    public double getErrorOperacion(){
        return this.errorOperacion;
    }
    

    @SuppressWarnings("empty-statement")
    public void evaluar() throws JepException{
    jep = new JEP();
    //añade los valores de pi, euler, funciones trigonometricas, etc.
        this.jep.addStandardFunctions();
        this.jep.addStandardConstants();
        
     //obtiene la funcion digitada por el usuario
        this.jep.parseExpression(this.entrada);
        
        
        // evaluamos los intervalos xi y xu en la funcion para obtener Fxi y Fxu 
        this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        this.jep.addVariable("x", this.xu);
        Fxu = this.jep.getValue();
        
        xr= xu-((Fxu)*(xi-xu))/(Fxi-Fxu);
        
        this.jep.addVariable("x", xr);
        Fxr = this.jep.getValue();
        
     
       test = xi*xr;
       
       //inicio del do while 
       
        int iterador = Integer.parseInt(JOptionPane.showInputDialog(" Escribe el numero de repeticiones"));
       for(int i=0;i<=iterador;i++){
       //do {
           xrAnterior=xr;
       
       if(test < 0){
         xi=xr;
         
         this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        this.jep.addVariable("x", this.xu);
        Fxu = this.jep.getValue();
        
        xr= xu-((Fxu)*(xi-xu))/(Fxi-Fxu);
        
        this.jep.addVariable("x", xr);
        Fxr = this.jep.getValue();
       
        test = xi*xr;
 
        if(test > testAnterior){
          xu=xr;
         
         this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        this.jep.addVariable("x", this.xu);
        Fxu = this.jep.getValue();
        
        xr= xu-((Fxu)*(xi-xu))/(Fxi-Fxu);
        
        this.jep.addVariable("x", xr);
        Fxr = this.jep.getValue();
        
        test = xi*xr;
        }
        
       }
               else{
         xu=xr;
         
         this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        this.jep.addVariable("x", this.xu);
        Fxu = this.jep.getValue();
        
        xr= xu-((Fxu)*(xi-xu))/(Fxi-Fxu);
        
        this.jep.addVariable("x", xr);
        Fxr = this.jep.getValue();
        
        test = xi*xr;
        
        if(test < testAnterior){
          xi=xr;
         
         this.jep.addVariable("x", this.xi);
        Fxi= this.jep.getValue();
        this.jep.addVariable("x", this.xu);
        Fxu = this.jep.getValue();
        
        xr= xu-((Fxu)*(xi-xu))/(Fxi-Fxu);
        
        this.jep.addVariable("x", xr);
        Fxr = this.jep.getValue();
        
        test = xi*xr;
        
        }
        
       }
       error1=((xr-xrAnterior)/xr) * 100;
       Math.abs(error1);
        System.out.println(error1);
       
}//while(error1==0.0001); //fin del while 
       
       
        this.errorOperacion=error1;
            
             this.resultado = xr;
        
        //captura el error
        this.error = (this.jep.hasError())? "Existe un error, revisa tus entradas de datos.":"Solucion exitosa.";
    }

            
          
}
