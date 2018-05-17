import java.io.*;
import java.util.*;

public class JuegoAsteroides2 {
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
	
	File fichero = new File("fjugadores");

	Set<Jugador> jugadores = new HashSet<Jugador>();
	
		 EscenarioAsteroides m = new EscenarioAsteroides(500,500);       // Creamos un escenario. 
	     Coordenada e1 = new Coordenada(0,250);     					 // Creamos tres nuevas coordenadas.
	     Coordenada e2 = new Coordenada(500,100);
	     Coordenada e3 = new Coordenada(200,350);
	     Coordenada e4 = new Coordenada(0,400);// para la segunda nave
	     
	     Rectangulo r1 = new Rectangulo(e1,40,40);  					 // Creamos tres nuevos rectangulos con las coordenadas que hemos creado antes.
	     Rectangulo r2 = new Rectangulo(e2,40,40);
	     Rectangulo r3 = new Rectangulo(e3,40,40);
	     Rectangulo r4 = new Rectangulo(e4,60,60); 
	     
	     boolean masPartidas = true;
	     String si = "si";
	     int nivel=1;
	     
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
	    	  finally{ File f = new File("fjugadores");
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
	  
	    	// Creamos los Actores, una nave y dos asteroides 
	      Nave nave = new Nave(r1, "nave3.png", m,1);
	      Asteroide asteroide1 = new Asteroide(r2, "asteroide3.png",m,-1);
	      Asteroide asteroide2 = new Asteroide(r3, "asteroide3.png",m,-1);
	      
	      try{
	    	  m.add(asteroide1);          // Añadimos los actores al escenario.
	    	  m.add(asteroide2);
	    	  m.add(nave);
	     
	      }catch(ExcepcionEscenarioLleno e){ }
     		while (!salir) {
     			salir = m.ejecutarCicloJuego();	 // Ejecutamos el ciclo de Juego.
     			try{
     			m.dibujar();	                 // Dibujamos los actores en el escenario.
     			}catch(ExcepcionJuego e){}
		}	
     		
     		System.out.println("*  Su puntuacion ha sido de "+ m.getPuntuacion()+"              *");
     		System.out.println("*                                            *");
     		System.out.println("*  Introduzca nombre para su puntuación      *");
     		
     		String nombre = sc.nextLine();
 			Jugador nuevo = new Jugador(nombre);
 			
     		if(!jugadores.contains(nuevo)){
     			jugadores.add(nuevo);
     		}
     		for(Jugador j : jugadores){
     			if(j.equals(nuevo)){
     				j.getPuntuaciones().add(m.getPuntuacion());
     			}
     			System.out.println(j.getNombre());
     			for(Integer i : j.getPuntuaciones()){
     				System.out.println(i);
     			}
     		}
     		System.out.println("*                                            *");
     		System.out.println("*  ¿Quiere actualizar las puntuaciones?      *");
     		System.out.println("*                                            *");
     		String respuesta = sc.nextLine();
     		
     		if(respuesta.equals(si)){
     			for(Jugador j : jugadores){
     				int contador = 1;
     				Iterator<Integer> i;
     				i=j.getPuntuaciones().iterator();
     				
     				System.out.println("*                                            *");
					System.out.println("* Puntuaciones guardadas correctamente       *");
					System.out.println("*                                            *");
     		
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
     		System.out.println("*  ¿Desea continuar jugando?                 *");
     		System.out.println("*                                            *");
     		String respuesta2 = sc.nextLine();
     		if(respuesta2.equals(si)){
     			masPartidas = true;
     			m.getGUI().borrar();
     			m.reinicializar();
     		}
     		else {
     			System.out.println("**********************************************");
     		    System.out.println("*                                            *");
     			System.out.println("*  ¡¡ Ahora el universo estará tranquilo !!  *");
     			System.out.println("*                                            *");
     			System.out.println("**********************************************");
     			
     			System.exit(0);
     		}
     		if(masPartidas) {
     			nivel++;
     			System.out.println("*                                            *");
     			System.out.println("*  Iniciando nivel->"+nivel+"                        *");
     		}
     		
     		if (nivel==2) {
     			System.out.println("*                                            *");
     			System.out.println("*  ¿Desea cambiar de nave?                   *");
     			String respuesta3 = sc.nextLine();
     			
         		if(respuesta3.equals(si)){
         			System.out.println("*                                            *");
         			System.out.println("*             Cambiando de nave...           *");
         			
         			Nave nave2 = new Nave(r4, "nave4.png", m,1);
         			
         			
         		
         			try{
         				m.reinicializar();
         				m.getGUI().borrar();
         		    	m.add(nave2);
         		     
         		      }catch(ExcepcionEscenarioLleno e){ }
         	     		
     		} //if
}//if nivel 2
     		
     		
	  System.out.println("Desea exportar fichero de jugadores?");
	  
	  
	  	if(sc.nextLine().equals(si)){
	  		try{
	  			guardarJugadores("fjugadores", jugadores);
	  		}catch(IOException e){e.getMessage();}
	  	}
	  	else 
	  		System.out.println("* cargando el juego... *");	  
	 }
	sc.close();     
  }
}