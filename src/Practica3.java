

/**public class Practica3  {
	public static void main (String[] args) {
	   
	   Escenario m = new Escenario(500,200);
	   Actor nave, asteroide;	
	   Actor actores[] = new Actor[1000];
       boolean salir = false;
       	
       Coordenada e1 = new Coordenada(50,50);
       Coordenada e2 = new Coordenada(100,100);
       
       Rectangulo r1 = new Rectangulo(e1,40,40);
       Rectangulo r2 = new Rectangulo(e2,40,40);
       
       nave = new Actor(r1, "nave.jpg", m);
       asteroide = new Actor(r2, "asteroide.jpg",m);
   
       m.add(asteroide);
       m.add(nave);
       
      actores = m.obtenerActoresEnZona(40, 40, 200, 200);
      
      for(int i=0;i<1000;i++){
    	if(actores[i]!=null){  
    		System.out.println("h");
    	}
      }
		
		while (!salir) {
			salir = m.ejecutarCicloJuego();	
			m.dibujar();	
		}	
	}
}

*/