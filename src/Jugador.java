import java.util.*;


	public class Jugador {
	
		private String nombre;
		LinkedList<Integer> puntuaciones;
		
		
		public Jugador(String nombre){
		
			this.nombre=nombre;
			puntuaciones = new LinkedList<Integer>();
		}
		
		public String getNombre(){
			return nombre;
		}
		
		public LinkedList<Integer> getPuntuaciones(){
			return puntuaciones;
		}
		
		public void añadirPuntuacion( int puntuacion){
			puntuaciones.add(puntuacion);
		}
		public boolean equals(Object o){
			boolean salida = false;
			String nombrej="";
			
			if(o instanceof Jugador){
				Jugador j = (Jugador) o;
				 nombrej = j.getNombre();
			}
			
			if(this.getNombre().equals(nombrej)){
				salida = true;
			}
			
			return salida;
		}
		
		public int hashCode(){
			int salida = nombre.hashCode();
			return salida;
		}
	}
