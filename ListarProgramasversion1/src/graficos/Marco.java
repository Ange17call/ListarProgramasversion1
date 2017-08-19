package graficos;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Marco extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Marco(){
		Toolkit miPantalla=Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla=miPantalla.getScreenSize();
		int alturaPantalla=tamanoPantalla.height;
		int anchoPantalla=tamanoPantalla.width;
		setSize(anchoPantalla/2,alturaPantalla/2);
		setLocation(anchoPantalla/4, alturaPantalla/4);
		setTitle("Verificación de Programas");
		Image miIcono=miPantalla.getImage("icono.jpg");
		setIconImage(miIcono);
		setVisible(true);
		Lamina lamina1= new Lamina();
		add(lamina1);
		
	
	}
}
