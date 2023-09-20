
package IO;
import javax.swing.JOptionPane;
public class Matrices {
        
public static void main(String[] args){
     double numeroMenor=0;
     double division;
     double divisionAnterior=1000000;
     int posicionpiboteFila = 0;
     int posicionpiboteColumna = 0;
     double NumeroPibote = 0;
     
     boolean verificar=false;
     
     //se necesita saber el numero de variables de holgura y artificiales a utilizar
                 
    int VArtificialesHolgura=Integer.parseInt(JOptionPane.showInputDialog("Número de variables de holgura y artificiales a utilizar"));
     
    //se necesita saberlenumero de variables 
    int Nvariables=Integer.parseInt(JOptionPane.showInputDialog("Cuantas variables necesitas"));
    
    // se necesita saber el numero de restricciones

    int NdeRestricciones=Integer.parseInt(JOptionPane.showInputDialog("Cual es el numero de restricciones"));
    
    //necesitamos especificar si se va a minimizar o maximizar 
    
    int MoM=Integer.parseInt(JOptionPane.showInputDialog("Deseas minimizar o maximizar \n "

            + "1- Maximizar \n"
            + "2- minimizar "));
    
    int FILAS=NdeRestricciones+1;
    int COLUMNAS=Nvariables+2+NdeRestricciones;

    double[][] matriz= new double [FILAS][COLUMNAS];
    
    switch (MoM) {
    //ESCOGISTE MAXIMIZAR
    //metodo de bisexion

        
        case 1:

            double saber = 0;
            
            for (int i = 0; i <  FILAS; i++) {
            for (int j = 0; j <  COLUMNAS; j++) {

            matriz[i][j]=Double.parseDouble(JOptionPane.showInputDialog("Escribe los valores de la matriz"));
            }
                
        }
             while(verificar!=true){
                 saber=0;
                 //validacion del bucle
                        for(int h=0;h<COLUMNAS;h++){
                        if(matriz[0][h]<0){
                            saber=1;
                        }
                        
                        if(saber==1){
                                               verificar=false;
                        }else if(saber!=1){
                        verificar=true;
                        }
                        
                        }

            for (int i = 0; i <  FILAS; i++) {
            for (int j = 0; j <  COLUMNAS; j++) {

                System.out.print(matriz[i][j] + " ");
            }
                System.out.println("");
        } 
            //La matriz avanza en las columnas de la primera fila para verificar si el resultado ya se encontro
            for(int h=0;h<COLUMNAS;h++){
                if (matriz[0][h]>=0){
            
                    JOptionPane.showMessageDialog(null,"El resultado ya se encontro");
                   
            }else{
                //busca el mayor negativo de la fila 0 para seleccionar la columna pibote
                
                if(matriz[0][h]<numeroMenor){
                numeroMenor=matriz[0][h];
                }
                }
            }
            
            System.out.println("el numero menor es "+numeroMenor);
            
            
            
                for(int j=0;j<COLUMNAS;j++){
                    if(matriz[0][j]==numeroMenor){
                        System.out.println("la posicion del menor negativo es: 0  " +j);
                    
                        
                        //divide todos los numeros positivos de la columna pibote por el resultado para encontrar 
                        //la fila pibote 
                        
                        for(int i=0;i<FILAS;i++){

                            if(matriz[i][j]>0){
                                
                                division=matriz[i][COLUMNAS-1]/matriz[i][j];
                               
                            System.out.println(division);
                            System.out.println("se dividio la columna de resultado: " + matriz[i][COLUMNAS-1] + " por:  " + matriz[i][j]);
                             //se encuentra el numero menor tra dividir la columna resultado por la columna pibote
                            if(division<divisionAnterior){
                                divisionAnterior=division;
                                System.out.println("el menor divididio es: " + divisionAnterior +" y el numero pibote seria: " + matriz[i][j]);
                                //almacena elvalor del numeropibote
                                NumeroPibote=matriz[i][j];
                                
                                //almacenamos en dos variables la posicion del numero pibote dentro de la fila y la columna 
                                posicionpiboteFila=i;
                                posicionpiboteColumna=j;
                            }
                            }   
                            
                    }

                        
                }
                }
                
                System.out.println("el numero pibote es " + NumeroPibote);
                System.out.println("y esta en la posicion " + posicionpiboteFila + " " + posicionpiboteColumna);
                        
                        if(matriz[posicionpiboteFila][posicionpiboteColumna]!=1){
                            System.out.println("el numero pibote necesita cambiar a 1 ");

                            //divide los numeros en la fila que esten delante del numero pibote
                               
                           for(int f=posicionpiboteColumna+1;f<COLUMNAS;f++){
                               
                               matriz[posicionpiboteFila][f]/=matriz[posicionpiboteFila][posicionpiboteColumna];
  
                           }
                                
                           
                            
                           for(int i=0;i<FILAS;i++){
                           for(int f=0;f<COLUMNAS;f++){
                               //dividimos toda la fila donde se encuentra el pibote para transformar el pibote a 1 
                           matriz[posicionpiboteFila][f]/=matriz[posicionpiboteFila][posicionpiboteColumna];   
                                   
                               
                           }
                           }
                            
                            System.out.println("La nueva matriz seria: ");
                           
                           for(int i=0;i<FILAS;i++){
                           for(int f=0;f<COLUMNAS;f++){
                           
                               System.out.print(matriz[i][f] + " ");
                            }
                               System.out.println("");

                           }
                            
 
                        }else{
                        
                            System.out.println("el numero pibote ya es 1");
                        }
                       
                        //tenemos que transformar la columna donte esta el numero pibote en una matriz de identidad
                        
                        
                        //convierte en ceros antes del numero pibote
                        for(int i=0;i<posicionpiboteFila;i++){
                        for(int h=posicionpiboteColumna+1;h<COLUMNAS;h++){
                               double numero=matriz[i][posicionpiboteColumna]*-1;
                               matriz[i][h]=matriz[posicionpiboteFila][h]*numero + matriz[i][h];
                               
                           }
                        }
                        
                        for(int i=0;i<posicionpiboteFila;i++){
                        for(int f=0;f<COLUMNAS;f++){

                            double numero=matriz[i][posicionpiboteColumna]*-1;
                            if(matriz[i][posicionpiboteColumna]!=0){
                              matriz[i][f]=matriz[posicionpiboteFila][f]*numero + matriz[i][f];

 
                            }

                            
                        }
                        }

                        //convierte en ceros depues de el numero pibote
                        for(int i=posicionpiboteFila+1;i<FILAS;i++){
                        for(int f=posicionpiboteColumna+1;f<COLUMNAS;f++){
                            
                               double numero=matriz[i][posicionpiboteColumna]*-1;
                               matriz[i][f]=matriz[posicionpiboteFila][f]*numero + matriz[i][f];
                               
                           }
                        }
                        
                        for(int i=posicionpiboteFila+1;i<FILAS;i++){
                        for(int f=0;f<COLUMNAS;f++){

                            double numero=matriz[i][posicionpiboteColumna]*-1;
                            if(matriz[i][posicionpiboteColumna]!=0){
                              matriz[i][f]=matriz[posicionpiboteFila][f]*numero + matriz[i][f];

 
                            }

                              
                            

                        }
                        }
                        
                        
                                                
                        
                        System.out.println("La nueva matriz seria con los ceros: ");
                           
                           for(int i=0;i<FILAS;i++){
                           for(int f=0;f<COLUMNAS;f++){
                           
                               System.out.print(matriz[i][f] + " ");
                            }
                               System.out.println("");

                           }
            }
            break;
    //ESCOGISTE MINIMIZAR
    //metodo de la gran M
            
            case 2:        
                COLUMNAS=Nvariables+2+NdeRestricciones;
            
            for (int i = 0; i <  FILAS; i++) {
            for (int j = 0; j <  COLUMNAS; j++) {

            matriz[i][j]=Double.parseDouble(JOptionPane.showInputDialog("Escribe los valores de la matriz"));
            }
                
        }
            for (int i = 0; i <  FILAS; i++) {
            for (int j = 0; j <  COLUMNAS; j++) {

                System.out.print(matriz[i][j] + " ");
            }
                System.out.println("");
        }

                        for(int f=0;f<COLUMNAS;f++){

                            
                              matriz[0][f]=matriz[0][f]+ matriz[1][f]*100;

                            }

                        for(int f=0;f<COLUMNAS;f++){

                            
                              matriz[0][f]=matriz[0][f]+ matriz[2][f]*100;

                            }

                    
                    System.out.println("la matriz arreglada a una matriz de identidad seria: ");

            for (int i = 0; i <  FILAS; i++) {
            for (int j = 0; j <  COLUMNAS; j++) {

                System.out.print(matriz[i][j] + " ");
            }
                System.out.println("");
        }
            

            for(int r=0;r<2;r++){
//busca el mayor positivo de la fila 0 para seleccionar la columna pibote
            numeroMenor=0;

            for(int h=0;h<COLUMNAS-1;h++){
    
                if(matriz[0][h]>numeroMenor){
                numeroMenor=matriz[0][h];
                }
                
            }
            
            System.out.println("el numero mayor es "+numeroMenor);

                for(int j=0;j<COLUMNAS;j++){
                    if(matriz[0][j]==numeroMenor){
                        System.out.println("la posicion del mayor positivo es: 0  " +j);
                    
                        
                        //divide todos los numeros positivos de la columna pibote por el resultado para encontrar 
                        //la fila pibote 
                        
                        for(int i=0;i<FILAS;i++){

                            if(matriz[i][j]>0){
                                
                                division=matriz[i][COLUMNAS-1]/matriz[i][j];
                               
                            System.out.println(division);
                            System.out.println("se dividio la columna de resultado: " + matriz[i][COLUMNAS-1] + " por:  " + matriz[i][j]);
                             //se encuentra el numero menor tras dividir la columna resultado por la columna pibote
                            if(division<divisionAnterior){
                                divisionAnterior=division;
                                System.out.println("el menor divididio es: " + divisionAnterior +" y el numero pibote seria: " + matriz[i][j]);
                                //almacena elvalor del numeropibote
                                NumeroPibote=matriz[i][j];
                                
                                //almacenamos en dos variables la posicion del numero pibote dentro de la fila y la columna 
                                posicionpiboteFila=i;
                                posicionpiboteColumna=j;
                            }
                            }   
                            
                    }

                        
                }
                }
                
           
                
                System.out.println("el numero pibote es " + NumeroPibote);
                System.out.println("y esta en la posicion " + posicionpiboteFila + " " + posicionpiboteColumna);
                        
                        if(matriz[posicionpiboteFila][posicionpiboteColumna]!=1){
                            System.out.println("el numero pibote necesita cambiar a 1 ");

                            //divide los numeros en la fila que esten delante del numero pibote
                               
                           for(int f=posicionpiboteColumna+1;f<COLUMNAS;f++){
                               
                               matriz[posicionpiboteFila][f]/=matriz[posicionpiboteFila][posicionpiboteColumna];
  
                           }
                                
                           
                            
                           for(int i=0;i<FILAS;i++){
                           for(int f=0;f<COLUMNAS;f++){
                               //dividimos toda la fila donde se encuentra el pibote para transformar el pibote a 1 
                           matriz[posicionpiboteFila][f]/=matriz[posicionpiboteFila][posicionpiboteColumna];   
                                   
                               
                           }
                           }
                            
                            System.out.println("La nueva matriz seria: ");
                           
                           for(int i=0;i<FILAS;i++){
                           for(int f=0;f<COLUMNAS;f++){
                           
                               System.out.print(matriz[i][f] + " ");
                            }
                               System.out.println("");

                           }
                            
 
                        }else{
                        
                            System.out.println("el numero pibote ya es 1");
                        }
                       
                        //tenemos que transformar la columna donte esta el numero pibote en una matriz de identidad
                        
                        
                        //convierte en ceros antes del numero pibote
                        for(int i=0;i<posicionpiboteFila;i++){
                        for(int h=posicionpiboteColumna+1;h<COLUMNAS;h++){
                               double numero=matriz[i][posicionpiboteColumna]*-1;
                               matriz[i][h]=matriz[posicionpiboteFila][h]*numero + matriz[i][h];
                               
                           }
                        }
                        
                        for(int i=0;i<posicionpiboteFila;i++){
                        for(int f=0;f<COLUMNAS;f++){

                            double numero=matriz[i][posicionpiboteColumna]*-1;
                            if(matriz[i][posicionpiboteColumna]!=0){
                              matriz[i][f]=matriz[posicionpiboteFila][f]*numero + matriz[i][f];

 
                            }

                            
                        }
                        }

                        //convierte en ceros depues de el numero pibote
                        for(int i=posicionpiboteFila+1;i<FILAS;i++){
                        for(int f=posicionpiboteColumna+1;f<COLUMNAS;f++){
                            
                               double numero=matriz[i][posicionpiboteColumna]*-1;
                               matriz[i][f]=matriz[posicionpiboteFila][f]*numero + matriz[i][f];
                               
                           }
                        }
                        
                        for(int i=posicionpiboteFila+1;i<FILAS;i++){
                        for(int f=0;f<COLUMNAS;f++){

                            double numero=matriz[i][posicionpiboteColumna]*-1;
                            if(matriz[i][posicionpiboteColumna]!=0){
                              matriz[i][f]=matriz[posicionpiboteFila][f]*numero + matriz[i][f];

 
                            }

                        }
                        }

                        
                        System.out.println("La nueva matriz con los ceros: ");
                           
                           for(int i=0;i<FILAS;i++){
                           for(int f=0;f<COLUMNAS;f++){
                           
                               System.out.print(matriz[i][f] + " ");
                            }
                               System.out.println("");

                           }
                           
            }     
            break;
    }
    
}   
}
