public class Nave extends Actor{

	private int velocidadNave;
	private int velocidadNave2;
	char tecla;
	
	/**
	 * Construye una Nave.
	 * @param velocidad Velocidad a la que se mueve la nave.
	 * @param rectangulo Rectangulo donde esta la nave
	 * @param imagen imagen que la representa
	 * @param escenario escenario donde esta ubicada
	 */
	public Nave(Rectangulo rectangulo, String imagen, Escenario escenario, int velocidadNave){
		
		super(rectangulo, imagen, escenario);
		if(velocidadNave <= 0){
			throw new IllegalArgumentException("La velocidad es menor o igual a 0.");
		}
		this.velocidadNave = velocidadNave;
		this.velocidadNave2 = -velocidadNave;
	}

	/**
	 * Realiza sus acciones.
	 */
	
	public void actuar(){
		Rectangulo rectangulo =this.getRectangulo();
		Coordenada coordenada =rectangulo.getCoordenada();
		int topeY = super.getEscenario().getAlto()-this.getRectangulo().getAlto();
		int topeX = super.getEscenario().getAncho()-this.getRectangulo().getAncho();
	
		if(coordenada.getY()<topeY&&coordenada.getY()>0){

			rectangulo.setCoordenada(coordenada.getX(), coordenada.getY()+velocidadNave);
		}
		else{ 
			if(velocidadNave>0&&coordenada.getY()==0){
			rectangulo.setCoordenada(coordenada.getX(), coordenada.getY()+velocidadNave);
			}
		
			if(velocidadNave<0&&coordenada.getY()==topeY){
				rectangulo.setCoordenada(coordenada.getX(), topeY-1);
			}
		}
		
		coordenada =rectangulo.getCoordenada();
		
		if(coordenada.getX()<topeX&&coordenada.getX()>0){

			rectangulo.setCoordenada(coordenada.getX()+velocidadNave2, coordenada.getY());
		}
		else{ 
			if(velocidadNave2>0&&coordenada.getX()==0){
			rectangulo.setCoordenada(coordenada.getX()+velocidadNave2, coordenada.getY());
			}
		
			if(velocidadNave2<0&&coordenada.getX()==topeX){
				rectangulo.setCoordenada(topeX-1, coordenada.getY());
			}
		}
	}
	
	/**
	 * Procesa la tecla
	 * @param tecla Tecla a procesar
	 */
	public void procesarTecla(char tecla){
		
		this.tecla = tecla;
		
		//mover en el eje Y
		if(tecla =='x'&&velocidadNave<0){
			velocidadNave=-velocidadNave;
		}
		if(tecla =='w'&&velocidadNave>0){
			velocidadNave=-velocidadNave;
		}
			
		//mover en el eje X
		if (tecla== 'd'&&velocidadNave2<0) {
			velocidadNave2=-velocidadNave2;
		}
		if (tecla== 'a'&&velocidadNave2>0) {
			velocidadNave2=-velocidadNave2;
		}
	}
	
}