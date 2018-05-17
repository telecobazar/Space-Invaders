public class Actor {
	
	/**
	 * Inicializamos las variables.
	 */
	private Rectangulo rectangulo;
	private Escenario escenario;
	private String ficheroImagen;
	
	/**
	 * Constructor de Actor.
	 * @param rectangulo
	 * @param ficheroImagen
	 * @param escenario
	 */
	public Actor (Rectangulo rectangulo, String ficheroImagen, Escenario escenario){
		if(rectangulo == null||ficheroImagen==null||escenario==null){
			throw new IllegalArgumentException("Alguna variable es nula.");
		}
		this.rectangulo = rectangulo;
		this.escenario = escenario;
		this.ficheroImagen = ficheroImagen;
	}
	
	/**
	 *  El actor hace sus acciones, se codifica despues
	 *  no devuelve nada
	 */
	public void actuar(){   }
	
	/**
	 * Devuelve la coordenada del rectangulo.
	 * @return
	 */
	public Coordenada getCoordenada(){
		return rectangulo.getCoordenada();
	}
	
	/**
	 * Devuelve el rectangulo donde esta el actor.
	 * @return
	 */
	public Rectangulo getRectangulo(){
		return rectangulo;
	}
	
	/**
	 * Devuelve el escenario donde esta el actor.
	 * @return
	 */
	public Escenario getEscenario(){
		return escenario;
	}
	
	/**
	 * Devuelve la imagen que caracteriza al actor.
	 * @return
	 */
	public String getFicheroImagen(){
		return ficheroImagen;
	}

	/**
	 * Comprueba si dos actores colisionan.
	 * @param p2
	 * @return
	 */
	public boolean hayColision (Actor p2){
		
		if(p2 == null){
			throw new IllegalArgumentException("La variable p2 esta vacia.");
		}
		boolean choque = false;
		if(rectangulo.hayColision(p2.getRectangulo())){
		choque = true;	
		}
		return choque;
	}
	
	/**
	 * Translada la coordenada origen un cierto ancho y un cierto alto.
	 * @param dx
	 * @param dy
	 */
	public void transladar(int dx, int dy){
		rectangulo.transladar(dx, dy);
	}
	
	/**
	 * Procesa la tecla pulsada por teclado.
	 * @param tecla
	 */
	public void procesarTecla(char tecla){	}
	
	/**
	 * Establece la coordenada origen del actor.
	 * @param c
	 */
	public void setCoordenada(Coordenada c){
		if(c == null){
			throw new IllegalArgumentException("La variable c esta vacia.");
		}
		rectangulo.setCoordenada(c);
	}
}
