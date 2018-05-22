public class Asteroide extends Actor {

	private int velocidadAsteroide = 0;
	char tecla;
	int a;
	
	/**
	 * Constructor de la clase Asteroide
	 * @param velocidad Velocidad a la que ira el asteroide.
	 * @param rectangulo Rectangulo en el que esta contenido el asteroide.
	 * @param ficheroImagen Imagen que tendra el asteroide
	 * @param escenario Escenario en el que se ubicara el asteroide.
	 */
	public Asteroide(Rectangulo rectangulo, String ficheroImagen, Escenario escenario, int velocidadAsteroide){
		super(rectangulo, ficheroImagen, escenario);
		if(velocidadAsteroide >= 0){
			throw new IllegalArgumentException("La velocidad es menor o igual a 0.");
		}
		this.velocidadAsteroide = velocidadAsteroide;
	}
	
	/**
	 * Procesa la tecla.
	 * @param tecla tecla que se ha pulsado.
	 */
	public void procesarTecla(char tecla){
		this.tecla=tecla;
			if(tecla =='v'){
				velocidadAsteroide=velocidadAsteroide*2;
			}		
	}
	
	/**
	 * Realiza sus acciones.
	 */
	public void actuar(){
		Rectangulo r =this.getRectangulo();
		Coordenada c =r.getCoordenada();
		int aleatorio =(int)(Math.random()*460);
		if(c.getX()==0){
			r.setCoordenada(460, aleatorio);
		}
		else
		r.setCoordenada(c.getX()+velocidadAsteroide, c.getY());
	}
}