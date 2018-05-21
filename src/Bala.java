
public class Bala extends Actor{
	private int velocidadBalax;
	private int velocidadBalay;
	char tecla;
	
	/**
	 * Construye una Bala.
	 * @param velocidad Velocidad a la que se mueve la bala.
	 * @param rectangulo Rectangulo donde esta la bala
	 * @param imagen imagen que la representa
	 * @param escenario escenario donde esta ubicada
	 */

	public Bala(Rectangulo rectangulo, String ficheroImagen, Escenario escenario) {
		super(rectangulo, ficheroImagen, escenario);
		
		if(velocidadBalax <= 0){
			throw new IllegalArgumentException("La velocidad es menor o igual a 0.");
		}
		this.velocidadBalax = velocidadBalax;
		this.velocidadBalay = -velocidadBalax;
	}

//Realiza sus acciones
	public void actuar(){
		Rectangulo rectangulo =this.getRectangulo();
		Coordenada coordenada =rectangulo.getCoordenada();
		int topeY = super.getEscenario().getAlto()-this.getRectangulo().getAlto();
		int topeX = super.getEscenario().getAncho()-this.getRectangulo().getAncho();

		if(coordenada.getY()<topeY&&coordenada.getY()>0){

			rectangulo.setCoordenada(coordenada.getX(), coordenada.getY()+velocidadBalay);
	}
		else{ 
			if(velocidadBalay>0&&coordenada.getY()==0){
				rectangulo.setCoordenada(coordenada.getX(), coordenada.getY()+velocidadBalay);
		}
	
			if(velocidadBalay<0&&coordenada.getY()==topeY){
				rectangulo.setCoordenada(coordenada.getX(), topeY-1);
		}
	}
coordenada =rectangulo.getCoordenada();
		
		if(coordenada.getX()<topeX&&coordenada.getX()>0){

			rectangulo.setCoordenada(coordenada.getX()+velocidadBalax, coordenada.getY());
		}
		else{ 
			if(velocidadBalax>0&&coordenada.getX()==0){
			rectangulo.setCoordenada(coordenada.getX()+velocidadBalax, coordenada.getY());
			}
		
			if(velocidadBalax<0&&coordenada.getX()==topeX){
				rectangulo.setCoordenada(topeX-1, coordenada.getY());
			}
		}
}
	public void procesarTecla(char tecla){
		
		this.tecla = tecla;
		
		if( tecla == 'z' ) {
			velocidadBalax=-velocidadBalax;
			velocidadBalay=-velocidadBalay;
			
		}
	}		
}