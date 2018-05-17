import simplegui.*;
import java.util.*;
public class SuperSimpleGUI implements KeyboardListener {
	private static final int SLEEP = 5;  	// Retardo en milisegundos entre la ejecución de cada ciclo
	private SimpleGUI gui;					// Ventana gráfica de la aplicación
	private ArrayList<Character> entrada;  	// Carácteres  pendientes de procesar
	private HashMap<String,DrwImage> mapa; 	// Para acelerar el dibujo de las imagenes

	/** Crea una ventana con el ancho y el alto especificados, su origen de coordenadas (0,0) es el vertice superior izquierdo 
	 * @param ancho ancho en puntos de la ventana
	 * @param alto alto en puntos de la ventana
	 */
	public SuperSimpleGUI(int ancho, int alto) {
		gui = new SimpleGUI(ancho, alto,false);
		gui.setAutoRepaint(false);
		gui.registerToKeyboard(this);
		entrada = new ArrayList<Character>();
		mapa = new HashMap<String,DrwImage>();
	}
	/** Obtiene el ancho de la ventana
	 * @return ancho en puntos de la ventana
	 */
	public int getAncho(){
		return gui.getWidth();
	}
	/** Obtiene el alto 
	 * @return alto en puntos de la ventana
	 */
	public int getAlto() {
		return gui.getHeight();
	}
	/** Consulta si hay algún caracter escrito por el usuario que no ha sido procesado todavia. No espera a que el usuario teclee uno, si no lo hay devuelve false. 
	 * @return si hay algún caracter sin procesar
	 */
	public boolean hayCaracter() {
		return !entrada.isEmpty();
	}
	/** Obtiene el caracter escrito por el usuario que no ha sido procesado, previamente hay que consultar que existe 
	 * @return un caracter pendiente de procesar
	 */
	public char leerCaracter() {
		return entrada.remove(0);
	}
	/** Escribe una linea de texto en la ventana 
	 * @param s linea de texto a mostrar
	 */
	public void escribirLinea(String s){
		gui.print(s);
	}
	/** Lee una linea de texto  desde la ventana gráfica, esperando hasta que el usuario la escriba 
	 * @return linea de texto leida
	 */
	public String leerLinea() {
		return gui.keyReadString(false);	
	}
	/** Añade una imagen a la ventana, que no será visible hasta que se llame al metodo dibujar() 
	 * @param ficheroImagen  ruta de la imagen
	 * @param r	rectangulo donde mostrar la imagen (origen de coordenas en el vertice superior izquierdo)
	 */
	public void addImagen(String ficheroImagen, Rectangulo r) {
		DrwImage imagen;
		String clave = ficheroImagen+"#"+r.getCoordenada().getX()+"#"+r.getCoordenada().getY()+"#"+r.getAncho()+"#"+r.getAlto();
		imagen = mapa.get(clave);
		if (imagen == null) {
			imagen = new DrwImage(ficheroImagen);
			mapa.put(clave, imagen);
		}
		gui.drawImage(imagen,r.getCoordenada().getX(),r.getCoordenada().getY(),r.getAncho(),r.getAlto(), ficheroImagen);
	}
	/** Dibuja en la ventana las imágenes que contiene */
	public void dibujar() {
		gui.repaintPanel();
		try {
			Thread.sleep(SLEEP);  // Da un tiempo al resto de tareas
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	/** Borra todas las imágenes que contiene la ventana  */
	public void borrar() {
		gui.eraseAllDrawables();

	}
	/** Método de gestion interna */
	@Override
	public void reactToKeyboardSingleKey(String arg0) {
		// TODO Auto-generated method stub
		gui.print(arg0);
		entrada.add(arg0.charAt(0));
	}
	/** Método de gestion interna */
	@Override
	public void reactToKeyboardEntry(String arg0) {
	}

}
