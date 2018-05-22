
public class Bala extends Actor{

	private int velocidadBala ;
	private int velocidadBala2;
	char tecla;
	
	/**
	 * Construye una Bala.
	 * @param velocidad Velocidad a la que se mueve la Bala.
	 * @param rectangulo Rectangulo donde esta la bala
	 * @param imagen imagen que la representa
	 * @param escenario escenario donde esta ubicada
	 */
	public Bala(Rectangulo rectangulo, String imagen, Escenario escenario, int velocidadBala){
		
		super(rectangulo, imagen, escenario);
		if(velocidadBala <= 0){
			throw new IllegalArgumentException("La velocidad es menor o igual a 0.");
		}
		this.velocidadBala = velocidadBala;
		this.velocidadBala2 = -velocidadBala;
	}

	/**
	 * Realiza sus acciones.
	 */
	
	public void actuar1(){
		Rectangulo rectangulo =this.getRectangulo();
		Coordenada coordenada =rectangulo.getCoordenada();
		int topeY = super.getEscenario().getAlto()-this.getRectangulo().getAlto();
		int topeX = super.getEscenario().getAncho()-this.getRectangulo().getAncho();
	
		if(coordenada.getY()<topeY&&coordenada.getY()>0){

			rectangulo.setCoordenada(coordenada.getX(), coordenada.getY()+velocidadBala);
		}
		else{ 
			if(velocidadBala>0&&coordenada.getY()==0){
			rectangulo.setCoordenada(coordenada.getX(), coordenada.getY()+velocidadBala);
			}
		
			if(velocidadBala<0&&coordenada.getY()==topeY){
				rectangulo.setCoordenada(coordenada.getX(), topeY-1);
			}
		}
		
		coordenada =rectangulo.getCoordenada();
		
		if(coordenada.getX()<topeX&&coordenada.getX()>0){

			rectangulo.setCoordenada(coordenada.getX()+velocidadBala2, coordenada.getY());
		}
		else{ 
			if(velocidadBala2>0&&coordenada.getX()==0){
			rectangulo.setCoordenada(coordenada.getX()+velocidadBala2, coordenada.getY());
			}
		
			if(velocidadBala2<0&&coordenada.getX()==topeX){
				rectangulo.setCoordenada(topeX-1, coordenada.getY());
			}
		}
	}
	
	/**
	 * Procesa la tecla
	 * @param tecla Tecla a procesar
	 */
	public void procesarTecla1(char tecla){
		
		this.tecla = tecla;
		
		//mover en el eje Y
		if(tecla =='x'&&velocidadBala<0){
			velocidadBala=-velocidadBala;
		}
		if(tecla =='w'&&velocidadBala>0){
			velocidadBala=-velocidadBala;
		}
			
		//mover en el eje X
		if (tecla== 'd'&&velocidadBala2<0) {
			velocidadBala2=-velocidadBala2;
		}
		if (tecla== 'a'&&velocidadBala2>0) {
			velocidadBala2=-velocidadBala2;
		}
		if (tecla== 'c') {
		velocidadBala2=-velocidadBala2*4;
		}
	}
	
}