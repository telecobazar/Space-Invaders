public class EscenarioAsteroides extends Escenario {

		private SuperSimpleGUI ssgui = getGUI();
		private int puntuacion=0;
		//public static int puntuacion = 0;
		boolean salida;
	public EscenarioAsteroides(int ancho, int alto){
		super(ancho,alto);
	}
		
	public void reinicializar() {
		Actor actores[]= new Actor[MAXIMOACTORES];
		this.actores = actores;
		puntuacion=0;
		salida=true;
	}
	
	/**
	 * los actores realizan sus funciones y el juego funciona.
	 */
	public boolean ejecutarCicloJuego(){
		//boolean 
		salida=super.ejecutarCicloJuego();
		
		if(!salida){
			puntuacion=puntuacion+valor;
			for(int i=0;i<MAXIMOACTORES;i++){
				if(actores[i]!=null){
					if(actores[i] instanceof Nave){
						for(int e=0;e<MAXIMOACTORES;e++){
							if(actores[e]!=null){
								if(i!=e&&actores[i].hayColision(actores[e])){
								ssgui.addImagen("boom.png", new Rectangulo(actores[2].getCoordenada(),500,500));  // implememtar para que salga la imagen a tamaño completo 
								salida = true;
								}
							}
						}
					}
				}
			}
		}
		return salida;
	}
	
	public int getPuntuacion(){
		return puntuacion;
	}
}