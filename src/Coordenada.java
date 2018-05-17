
/** Clase que crea una nueva Coordenada con dos puntos.
 * @author Eric Martin
 */
public class Coordenada {
private int x;        //La variable de tipo privado que contiene la coordenada del eje de abcisas. 
private int y;        //La variable de tipo privado que contiene la coordenada del eje de ordenadas.

/** Construye una nueva Coordenada con un valor del eje de abscisas y otro del eje de coordenadas.
 * @param x valor del eje X
 * @param y valor del eje Y
 */
public Coordenada(int x, int y){
	this.x=x;
	this.y=y;
	if(x<0){
		this.x=0;}
	if(y<0){
		this.y=0;}	
}

/** Obtiene el valor X de la coordenada.
 * @return valor de X
 */
int getX(){
	return x;
}
/** Obtiene el valor Y de la coordenada.
 * @return valor de Y
 */
int getY(){
	return y;
}

/** Transforma la Coordenada a un String.
 */
public String toString(){	
	return new String  ("x: "+x+" y: "+y);
}
}
