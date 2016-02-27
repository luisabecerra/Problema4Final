/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema.pkg4.pkgfinal;

/**
 *
 * @author Luisa Paola Becerra Pleáez--Juan Camilo Mora Tinoco
 */
public class Problema4FINAL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.util.Scanner leer=
                new java.util.Scanner(System.in);
        
        int PrecioUnitario=0;
        int codigo,id;
        int total;
        String fecha,hora;
        String nombre="";
        String descripcion="";
        int FormaPago,cantidad, numvendedores, numproductos;
        int ventastotal=0;
        double totalvendedor=0;
        int totalmonto=0;
        String [] Vendedores= new String [1000];
        int [][] Credito = new int [10][1000];
        int [][]Debito = new int [10][1000];
        int [][] Efectivo = new int [10][1000];
        int [][] Totalmonto=new int[10][1000];
        double [] Precioventa=new double[1000];
        double [] ID=new double [1000];
        String [] Descripcion=new String [1000];
        double [] CodProducto=new double [1000];
        double [] PrecioporUnidad=new double [1000];
        
        for(int i=0;i<10;i++){
            for(int j=0;j<1000;j++){
                Credito[i][j]=-1;
                Debito[i][j]=-1;
                Efectivo[i][j]=-1;
                Totalmonto[i][j]=-1;
            }
        }
        System.out.println("Ingrese la fecha (ddmmaaa sin espacios)");
        fecha=leer.next();
        System.out.println("Ingrese numero de vendedores para el dia de hoy ");
        numvendedores=leer.nextInt();
        
        for (int a=0;a<numvendedores;a++){
            System.out.println("DATOS DEL VENDEDOR "+a);
            System.out.println("Ingrese el ID del vendedor "+a);
            id=leer.nextInt();
            ID[a]=id;   
            
            System.out.println("Ingrese nombre y apellido del vendedor "+a);
            nombre=leer.next();
            Vendedores[a]=nombre;   
        }
        
        while(true) { 
            System.out.println("1=Datos Producto, 2=Realizar una venta, 3=Administrador");
            int eleccion=leer.nextInt();
            switch(eleccion) {          
                case 1:
                    System.out.println("DATOS DEL PRODUCTO");
                    System.out.println ("Cuantos productos va a ingresar?");
                    numproductos=leer.nextInt();
                    for (int i=0;i<numproductos;i++){
                        System.out.println("Ingrese el codigo del producto");
                        codigo=leer.nextInt();
                        CodProducto[i]=codigo;    
                        
                        System.out.println("Ingrese breve descripcion");
                        leer.nextLine();
                        descripcion=leer.nextLine();
                        Descripcion[i]=descripcion;   
                        
                        System.out.println("Ingrese el precio unitario");
                        PrecioUnitario=leer.nextInt();
                        PrecioporUnidad[i]=PrecioUnitario;   
                    }
                    break;
            
                case 2 :
                    System.out.println("REALIZAR UNA VENTA");
                    System.out.println("ingrese el ID del vendedor");
                    id=leer.nextInt();
                    System.out.println("Ingrese hora (h:min)");
                    hora=leer.next();

                    System.out.println("Codigo del producto");
                    codigo=leer.nextInt();

                    System.out.println("Cantidad ");
                    cantidad=leer.nextInt();

                    System.out.println("0=Efectivo, 1=Debito, 2= Tarjeta");
                    FormaPago=leer.nextInt();

                    total=PrecioUnitario*cantidad;
                    totalmonto+=total;
                    
                    for(int i = 0; i< 10;i++){
                        if(ID[i]==id){
                            if (FormaPago==0){
                               for(int j=0;j<1000;j++) {
                                   if(Efectivo[i][j]==-1){
                                      Efectivo[i][j]=cantidad; 
                                      break;
                                    }
                                }   
                            }    
                            if (FormaPago==1){
                                for(int j=0;j<1000;j++) {
                                    if(Debito[i][j]==-1){
                                      Debito[i][j]=cantidad;  
                                      break;
                                    }
                                }   
                            }
                            if (FormaPago==2){
                                for(int j=0;j<1000;j++) {
                                    if(Credito[i][j]==-1){
                                        Credito[i][j]=cantidad;
                                        break;
                                    }
                                }      
                            }                            
                        }
                    }
                   
                    System.out.println("SU FACTURA ES:");
                    System.out.println("Vendedor: "+nombre+""+" Fecha: " +fecha+hora);
                    System.out.println("Codigo: "+codigo+" Producto: "+descripcion+ " Cantidad: "+ cantidad+" TOTAL: "+total+" Forma de pago"+FormaPago);
                    
                    if(eleccion==2) {
                        ventastotal++;
                    } 
                    System.out.println("el numero de ventas es "+ventastotal);
                    for(int i=0;i<10;i++){
                            Precioventa[i]=total;   
                        totalvendedor=total+totalvendedor;
                    } 
                    break;

                case 3:
                    int mayor=0;                        
                    int best=0;
                    int idx=0;

                    for(int i=0;i<10;i++){
                        int suma=0;
                        for(int j=0;j<10;j++){
                            if(Efectivo[i][j]!=-1){
                                suma=Efectivo[i][j]+suma;
                            } 
                            if(Credito[i][j]!=-1){
                                suma=Credito[i][j]+suma;
                            } 
                            if(Debito[i][j]!=-1){
                                suma=Debito[i][j]+suma;
                            } 
                        }
                        if(best<suma){
                            best=suma;
                            idx=i;
                        }
                    }
                    int suma=0;
                    for(int i=0;i<10;i++){
                        for(int j=0;j<1000;j++){
                            if(Debito[i][j]!=-1){
                                suma++;
                            }
                        }                            
                    }
                    System.out.println("el total de plata recogida es "+totalmonto); 
                    System.out.println("el total de ventas debito de todos los vendedores es "+suma);      
                    System.out.println("el mejor vendedor fue "+(idx+1));
                    break;
            }
        }
    }    
}
