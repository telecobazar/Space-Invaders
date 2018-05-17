import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Persona{
	private String nombre;
	private int edad;
	
	public Persona(String nombre){
	this.nombre=nombre;	
	}
	public String getNombre(){return nombre;}
	
	public int getEdad(){return edad;}
	
	public void setEdad(int edad){
		this.edad=edad;}
	
	public boolean guardarEnBinario(String nombreFichero, List<Persona> lista){
		boolean r=false;
		DataOutputStream salida = null;
		
		try{
			salida = new DataOutputStream (new FileOutputStream(nombreFichero));
			
			for(Persona o: lista){
				salida.writeUTF(o.getNombre());
				salida.writeInt(o.getEdad());	
				}
				r=true;
				
			}catch(IOException e){e.getMessage();}
			
		finally{
				if(salida!=null)
					try{
						salida.close();
					}catch(IOException e){}
			}
			
		return r;
		
	}
	
	public List<Persona> recuperarBinario(String nombreFichero){
		boolean eof=false;
		List<Persona> lista = new ArrayList<Persona>();
		DataInputStream entrada = null;
		try{
			entrada = new DataInputStream(new FileInputStream (nombreFichero));
			while(!eof){
				try{
					Persona p = new Persona(entrada.readUTF());
					p.setEdad(entrada.readInt());
					lista.add(p);		
		
		
			}catch(EOFException s){eof=true;}
		}
		}catch(IOException e){e.getMessage();}
		finally{
			if(entrada!=null)
				try{
					entrada.close();
				}catch(IOException e){}
		}
		
		return lista;
	}
	public boolean guardarEnBinarioObject(String nombreFichero, List<Persona> lista){
		boolean r=false;
		ObjectOutputStream impresor=null;
		try{
			impresor= new ObjectOutputStream(new FileOutputStream(nombreFichero));
			for(Persona o: lista)
				impresor.writeObject(o);
			r=true;
			
		}catch(IOException e){e.getMessage();}
		
		finally{if(impresor!=null)
			try{
				impresor.close();
			}catch(IOException e){}
		}
		return r;
	}
		

	public List<Persona> recuperarBinarioObject(String nombreFichero){
		boolean eof=false;
		List<Persona> lista = new ArrayList<Persona>();
		ObjectInputStream lector = null;
		try{
			lector = new ObjectInputStream(new FileInputStream (nombreFichero));
			while(!eof){
				try{
					Persona p = (Persona) lector.readObject();
					lista.add(p);		
			}catch(EOFException s){eof=true;} catch (ClassNotFoundException e) {e.getMessage();}
		}
		}catch(IOException e){e.getMessage();}
		finally{
			if(lector!=null)
				try{
					lector.close();
				}catch(IOException e){}
		}
		
		return lista;
	}
		
		
		
	
	
	
	
}//close