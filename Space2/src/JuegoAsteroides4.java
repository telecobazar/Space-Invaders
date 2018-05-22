import java.io.*;
import java.util.*;

public class JuegoAsteroides4 {
	  
	public static int nivel=1;
	
	private static Iterator<Integer> i;

	/**
	 * salva cada puntuacion de cada jugador de la partida en un fichero
	 * @param nombref nombre que queremos dar al fichero
	 * @param jugadores set de jugadores que se van a almacenar
	 * @throws IOException excepcion de tipo entrada-salida
	 */
	private static void guardarJugadores(String nombref, Set<Jugador>jugadores) throws IOException {
		boolean r=false;
		DataOutputStream salida = null;
		
		try{
			salida = new DataOutputStream (new FileOutputStream(nombref));
			
			for(Jugador j: jugadores){
				salida.writeUTF(j.getNombre());
				Iterator<Integer> i;
 				i=j.getPuntuaciones().iterator();
 				int aux2;
 				aux2=j.getPuntuaciones().size();
 				salida.writeByte(aux2);
 				int aux;
 				aux = (int) i.next();
 				while(i.hasNext()){
						
				salida.writeInt(aux);
 				}
				}
				r=true;
				
			}catch(IOException e){e.getMessage();}
			
		finally{
				if(salida!=null)
					try{
						salida.close();
					}catch(IOException e){}
			}	
	}
	
	/**
	 * Carga los jugadores de un fichero
	 * @param f fichero que contiene los jugadores con sus puntuaciones
	 * @return devuelve un set con los jugadores
	 * @throws FileNotFoundException Si no encuentra el archivo saltara esta excepcion
	 * @throws IOException excepcion tipo io
	 */
	public static Set<Jugador> cargarJugadores(File f)throws FileNotFoundException, IOException{
		boolean eof=false;
		Set<Jugador> set = new HashSet<Jugador>();
		DataInputStream entrada = null;
		try{
			entrada = new DataInputStream(new FileInputStream (f));
			while(!eof){
				try{
					Jugador j = new Jugador(entrada.readUTF());
					for(int i=0;i<entrada.readByte();i++){
					j.getPuntuaciones().add(entrada.readInt());
					}
					set.add(j);
					}catch(EOFException s){eof=true;}
			}
		}catch(IOException e){e.getMessage();}
		finally{
			if(entrada!=null)
				try{
					entrada.close();
				}catch(IOException e){}
		}
		return set;
	}
		
public static void main (String[] args) {
	
	File fichero = new File("fjugadores.txt");
	SuperSimpleGUI ssgui;
	Set<Jugador> jugadores = new HashSet<Jugador>();
	
		 EscenarioAsteroides m = new EscenarioAsteroides(500,500);       // Creamos el escenario. 
	     Coordenada c1 = new Coordenada(0,250);     					 // Coordenadas para la nave 1
	     Coordenada c2 = new Coordenada(200,350);						 // Coordenadas para el asteroide 1 
	     Coordenada c3 = new Coordenada(500,100);						 // Coordenadas para el asteroide 2
	     Coordenada c4 = new Coordenada(150,100);						 // Coordenadas para la nave extraterrestre
	     Coordenada c5 = new Coordenada(375,147);						 // Coordenadas para Roger el extraterrestre
	     
	     Rectangulo r1 = new Rectangulo(c1,60,50);  					 // Rectangulo donde dibujar la nave 1
	     Rectangulo r2 = new Rectangulo(c1,80,90);						 // Rectangulo donde dibujar la nave 2 (ancho, alto)
	     Rectangulo r3 = new Rectangulo(c2,40,40);						 // Rectangulo para pintar los asteroides  
	     Rectangulo r4 = new Rectangulo(c3,40,40); 						 // Rectangulo para pintar los asteroides  
	     Rectangulo r5 = new Rectangulo(c4,60,60);						 // Rectangulo para pintar una nave extraterrestre 
	     Rectangulo r6 = new Rectangulo(c5,100,100);					 // Rectangulo para pintar a Roger el extraterrestre
	     
	  // Creamos las naves y asteroides 
   	  
	     Nave nave1 = new Nave(r1, "n1.png", m,1);
	     Nave nave2 = new Nave(r2, "n2.png", m,1); 
   	  	 Asteroide a1 = new Asteroide(r3, "a3.png",m,-1);
	     Asteroide a2 = new Asteroide(r4, "a3.png",m,-1);
	     Asteroide a3 = new Asteroide(r5, "a4.png",m,-1); // nave extraterrestre
	     Asteroide a4 = new Asteroide(r6, "a5.png",m,-1); // Roger el extraterrestre 
	      
	     boolean masPartidas = true;
	     String si = "si";
	     
	     System.out.println("**********************************************");
	     System.out.println("*                                            *");
	     System.out.println("*        ·--<<  SPACE INVADERS  >>--·        *");
	     System.out.println("*                                            *");
	     System.out.println("**********************************************");
	     System.out.println("*                                            *");
	     System.out.println("*           Iniciando el NIVEL -> "+nivel+"          *");
	     System.out.println("*                                            *");
	     System.out.println("**********************************************");
	     System.out.println("*                                            *");
	     System.out.println("*¿Desea cargar historial de jugadores? si/no *");
	     System.out.println("*                                            *");
	     Scanner sc = new Scanner(System.in);
	     String res = sc.nextLine();
	     
	     if(res.equals(si)){
	    	  try{
	    	  
	    	  jugadores = cargarJugadores(fichero);
	    	  }catch(FileNotFoundException z){z.getMessage();
	    	  }catch(IOException e){e.getMessage();}
	    	  finally{ File f = new File("fjugadores.txt");
	    	  System.out.println("**********************************************");
	    	  System.out.println("*    ¡Historial vacío, debe jugar primero!   *");
	    	  System.out.println("*                                            *");
	    	  System.out.println("* La partida se iniciará de forma automática *");
	    	  System.out.println("*                                            *");
	    	  }
	      }
	   
	      while(masPartidas){
	    	  boolean salir = false;
	    	  masPartidas = false;
    	  
	    if (nivel==1) {   
	    	  try{
		    	  m.add(a1);          // Añadimos dos asteroides y una nave al escenario.
		    	  m.add(a2);
		    	  m.add(nave1);
		      }catch(ExcepcionEscenarioLleno e){ } 	  
	    }
	    	  
	    if (nivel==2) {
	    	  try{
	    		  m.borrarActor(nave1); // Borramos todos los actores anteriores
	    		  m.getGUI().borrar();
	    		  m.add(a1);          // Añadimos los actores al escenario.
	    		  m.add(a2);
	    		  m.add(nave2);
	    	  }catch(ExcepcionEscenarioLleno e){ }
	    	   catch(ExcepcionEscenarioVacio e){ }
	    }
	    	  
	    if (nivel==3) {
	    	  try{
	    		  m.getGUI().borrar();// Borramos todos los actores anteriores
	     		  m.add(a1);          // Añadimos los actores al escenario.
		    	  m.add(a2);
		    	  m.add(a3);
		    	  m.add(nave2);
		      }catch(ExcepcionEscenarioLleno e){ }
	    }
	    	  
	    if (nivel==4) {
	    	  try{
	    		  m.getGUI().borrar();		  // Borramos todos los actores anteriores
	    		  m.add(a3); 
	    		  m.add(a4);
	    		  m.add(nave2);
	    	  }catch(ExcepcionEscenarioLleno e){ }	  
	    }
 	    	  
     		while (!salir) {
     			salir = m.ejecutarCicloJuego();	 // Ejecutamos el ciclo de Juego.
     			try{
     			m.dibujar();	                 // Dibujamos los actores en el escenario.
     			}catch(ExcepcionJuego e){}
		}	
     		
     		System.out.println("*  Su puntuacion ha sido de "+ m.getPuntuacion());
     		System.out.println("*                                            *");
     		System.out.println("*  Introduzca nombre para su puntuación      *");
     		
     		String nombre = sc.nextLine();
 			Jugador nuevo = new Jugador(nombre);
 			
     		if(!jugadores.contains(nuevo)){
     			jugadores.add(nuevo);
     		}
     		System.out.println("**********************************************");
 			System.out.println("*                                            *");
 			System.out.println("*         Ranking de puntuaciones:           *");
 			System.out.println("*                                            *");
     		for(Jugador j : jugadores){
     			if(j.equals(nuevo))
     				j.getPuntuaciones().add(m.getPuntuacion());
     			for(Integer i : j.getPuntuaciones()){
     					System.out.println("* ->  Jugador: "+j.getNombre()+ " con puntuacion: "+i);
     			}
     		}
     		System.out.println("*                                            *");
     		System.out.println("*    ¿Quiere actualizar las puntuaciones?    *");
     		System.out.println("*                                            *");
     		String respuesta = sc.nextLine();
     		
     		if(respuesta.equals(si)){
     			System.out.println("*                                            *");
				System.out.println("*     Puntuaciones guardadas correctamente   *");
				System.out.println("*                                            *");
     			for(Jugador j : jugadores){
     				int contador = 1;
     				i=j.getPuntuaciones().iterator();
     		
					if(respuesta.equals("no")){
         				while(i.hasNext()) {
         					if(contador>3) {
         						if(i!=null)
         							j.getPuntuaciones().remove(i);
         					}
         				}contador=contador+1;
					}			
     			}
     		}
     		System.out.println("*                                            *");
     		System.out.println("*         ¿Desea continuar jugando?          *");
     		System.out.println("*                                            *");
     		String respuesta2 = sc.nextLine();
     		if(respuesta2.equals(si)){
     			
     			masPartidas = true;
     			m.getGUI().borrar();
     			m.reinicializar();
     		}
     		else {
     			System.out.println("*                                            *");
     			System.out.println("*   ¿Desea exportar fichero de jugadores?    *");
     			System.out.println("*                                            *");  
     			  
     			  	if(sc.nextLine().equals(si)){
     			  		try{
     			  			guardarJugadores("fjugadores.txt", jugadores);
     			  		}catch(IOException e){e.getMessage();}
     			  		System.out.println("*                                            *");  
     			  		System.out.println("*   Fichero exportado satisfactoriamente     *");
     			  		System.out.println("*                                            *");
     			  		
     			  	}
     			  	System.out.println("**********************************************");
     			  	System.out.println("*                                            *");
     			  	System.out.println("*  ¡¡ Ahora el universo estará tranquilo !!  *");
     			  	System.out.println("*                                            *");
     			  	System.out.println("**********************************************");
     			
     			System.exit(0);
     			
     		}
     		if(masPartidas) {
     		
     			nivel++;
     			
     			System.out.println("**********************************************");
     			System.out.println("*                                            *");
     			System.out.println("*         Iniciando el NIVEL->"+nivel+"              *");
     			System.out.println("*                                            *");

     		}
     		
     		if (nivel==2) {
     			System.out.println("*                                            *");
     			System.out.println("*     Se ha desbloqueado una nueva nave      *");
     			System.out.println("*                                            *");
     			System.out.println("**********************************************");
     		} //if nivel 2 
     		
     		
     		if (nivel==3) {
         		System.out.println("*                                            *");
         		System.out.println("*   Se ha desbloqueado una nueva galaxia     *");
         		System.out.println("*  ¡Cuidado que ahora hay mas asteroides!    *");
         		System.out.println("*                                            *"); 
         		System.out.println("**********************************************");
     		}//if nivel 3

    		if (nivel==4) {
         		System.out.println("*                                            *");
         		System.out.println("*   Se ha desbloqueado una nueva galaxia     *");
         		System.out.println("*  ten cuidado con Roger el extraterrestre   *");
         		System.out.println("*                                            *");
         		System.out.println("**********************************************");
     		}//if nivel 4
	      }// while mas partidas
	}

public static int getNivel() {
	return nivel;
}
// esta parte será valida para añadir un acceso directo al nivel que desea jugar el usuario
public static void setNivel(int nivel) {
	JuegoAsteroides4.nivel = nivel;
}

public static Iterator<Integer> getI() {
	return i;
}

public static void setI(Iterator<Integer> i) {
	JuegoAsteroides4.i = i;
}
}