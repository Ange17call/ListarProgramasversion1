package graficos;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MarcoTexto extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public MarcoTexto(){

		Toolkit miPantalla=Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla=miPantalla.getScreenSize();
		int alturaPantalla=tamanoPantalla.height;
		int anchoPantalla=tamanoPantalla.width;
		//setSize(850,alturaPantalla/2);
		setSize(850,460);
		setLocation(anchoPantalla/4, alturaPantalla/4);
		setTitle("Verificación de Programas");
		Image miIcono=miPantalla.getImage("icono.jpg");
		setIconImage(miIcono);
		LaminaTexto milamina=new LaminaTexto();
		add(milamina);
		setVisible(true);
				
	}

}
