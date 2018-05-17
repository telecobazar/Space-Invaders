public class Escenario {

	public static int MAXIMOACTORES = 1000; // Se definde una variable que sera el maximo de actores de las tablas
	private int ancho;
	private SuperSimpleGUI ssgui;
	private int alto;
	private Actor actores[] = new Actor [MAXIMOACTORES];
	protected int valor = 1;
	
	/**
	 * Construye un escenario con un ancho y un alto.
	 * @param ancho Ancho del escenario.
	 * @param alto Alto del escenario.
	 */
	public Escenario(int ancho, int alto) {
		
		if(ancho<0||alto<0){
			throw new IllegalArgumentException("El ancho o el alto son negativos");
		}
		ssgui = new SuperSimpleGUI(ancho,alto);
		this.ancho=ancho;
		this.alto=alto;
	}
		
	public boolean add(Actor actor) throws ExcepcionEscenarioLleno {
		
		if(actor == null){
			throw new IllegalArgumentException("La variable Actor esta vacia.");
		}
		if(actores[MAXIMOACTORES-1]!=null){
			throw new ExcepcionEscenarioLleno("No caben mas actores en el escenario.");
		}
		boolean salida = false;
			for(int i =0;i<MAXIMOACTORES;i++){
				if(actores[i]==null && !salida){
					actores[i] = actor;
					salida = true;
				}
			}
		return salida;
	}
	
	public void dibujar() throws ExcepcionJuego{
		
		boolean salido = false;
		for(int i=0;i<MAXIMOACTORES;i++){
			if(actores[i]!=null){
				if(actores[i].getCoordenada().getX()+actores[i].getRectangulo().getAncho()>this.ancho||actores[i].getCoordenada().getY()+actores[i].getRectangulo().getAlto()>this.alto){
					salido = true;
				}
			}
		}
		if(salido){
		
			throw new ExcepcionJuego("Algun actor se ha salido de los limites del escenario.");
		}
		
		ssgui.borrar();
		ssgui.addImagen("estrellas3.jpg", new Rectangulo(new Coordenada (0,0),500,500));
			
		for(int i=0;i<MAXIMOACTORES;i++){
			if(actores[i]!=null){
				ssgui.addImagen(actores[i].getFicheroImagen(), actores[i].getRectangulo());
					
			}
		}
		ssgui.dibujar();
	}
	
	public boolean ejecutarCicloJuego(){
		boolean salida=false;
		while(ssgui.hayCaracter()){
			 char tecla = ssgui.leerCaracter();
			 if (tecla=='v'){
				 valor = valor*2;
			 }
			if(tecla=='s'){
				salida = true;
				
				
				
			}
				else{
					for(int i=0;i<MAXIMOACTORES;i++){
						if(actores[i]!=null){
							actores[i].procesarTecla(tecla);
						}
					}
				}
		}
			if(!salida){
				for(int i=0;i<MAXIMOACTORES;i++){
						if(actores[i]!=null){
							actores[i].actuar();
						}
					}
			}
		
		return salida;
	}
	
	public Actor[] getActores(){
		return actores;
	}
	
	public int getAlto(){
		return alto;
	}
	
	public int getAncho(){
		return ancho;
	}
	
	public SuperSimpleGUI getGUI(){
		return ssgui;
	}
	
}
