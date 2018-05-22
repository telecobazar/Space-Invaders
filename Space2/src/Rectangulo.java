

public class Rectangulo {
	/**
	 * Declaramos todas las variables variables.
	 */
	private int ancho;     //Ancho del rectangulo.
	private Coordenada e;  // Coordenada origen del rectangulo.
	private int alto;      // Alto del rectangulo
	
	/**
	 * Construye un nuevo rectangulo con una coordenada, un ancho y un alto dados.
	 * @param e
	 * @param ancho
	 * @param alto
	 */
	public Rectangulo (Coordenada e, int ancho, int alto){
		
		if(e == null||ancho<0||alto<0){
			throw new IllegalArgumentException("La variable e esta vacia o el ancho o el alto son negativos.");
		}
		
		this.e=e;
		this.ancho=ancho;
		this.alto=alto;
	}
	/**
	 * Devuelve el ancho del rectangulo.
	 * @return
	 */
	public int getAncho(){
		return ancho;
	}
	/**
	 * Devuelve el alto del rectangulo.
	 * @return
	 */
	public int getAlto(){
		return alto;
	}
	
	/**
	 * Permite establecer una nueva coordenada para el rectangulo.
	 * @param x
	 * @param y
	 */
	public void setCoordenada(int x, int y){
		Coordenada e2 = new Coordenada(x,y);
		e=e2;
	}
	
	/**
	 * Devuelve la coordenada del rectangulo.
	 * @return
	 */
	public Coordenada getCoordenada(){
		return e;
	}
	
	/**
	 * Comprueba si hay colisiones entre dos rectangulos.
	 * @param r rectangulo con el que comprobar que hay colision
	 * @return
	 */
	public boolean hayColision(Rectangulo r){
		
		if(r == null){
			throw new IllegalArgumentException("La variable esta vacia.");
		}
		
		boolean salida = false;
		if((e.getX()+ancho)>=r.e.getX()){
			if(((e.getY()+alto)>=r.e.getY())&&e.getX()<(r.e.getX()+r.ancho)){
				if(e.getY()<(r.e.getY()+r.alto)){
				
				salida = true;
				}
			}
		}
		if(((e.getY()+alto)>=r.e.getY())){
			if(((e.getX()+ancho)>=r.e.getX())&&e.getY()<(r.e.getY()+r.alto)){
			salida=true;
				
			}
		}
		
			
		return salida;
	}
	
	/**
	 * Cambia la coordenada del rectangulo por otra.
	 * @param nueva
	 */
	public void setCoordenada(Coordenada nueva){
		
		if(nueva == null){
			throw new IllegalArgumentException("La variable nueva esta vacia.");
		}
		
		this.e = nueva;
	}
	
	/**
	 * Translada el origen.
	 * @param x
	 * @param y
	 */
	public void transladar(int x, int y){
		int x2 = (e.getX()+x);
		int y2 = (e.getY()+y);
		Coordenada e3 = new Coordenada(x2,y2);
		e=e3;
	}
	/**
	 * Transforma el rectangulo a un String.
	 */
	public String toString(){
		
		String s2 =("Coordenada: "+ e.toString() +" ancho: "+ ancho + " alto: "+ alto);
		return s2;
	}
}
