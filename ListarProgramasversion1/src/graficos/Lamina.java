package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

public class Lamina extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("ITALIC", Font.BOLD, 14));
		g.setColor(Color.BLACK);
		g.drawString("Iniciando Escaneo ", 5, 10);
		int contador = 0;
		String texto = null;
		String textoActual = null;
		String textoSiguiente = null;
		String textoBuscado = null;
		String[] arreglo;
		String[] arreglo1;
		String textoPrueba = null;
		String displayName;
		String displayVersion;
		String programa = null;
		String version = null;
		Programa prog;
		String linea;
		Process p;
		boolean bandera1 = false;
		boolean bandera2 = false;
		boolean bandera3 = false;
		boolean bandera4 = false;
		boolean bandera5 = false;
		boolean bandera6 = false;
		boolean bandera7 = false;
		boolean bandera8 = false;
		boolean bandera9 = false;
		boolean bandera10 = false;
		boolean bandera11 = false;
		boolean bandera12 = false;
		boolean bandera13 = false;
		boolean bandera14 = false;
		boolean bandera15 = false;
		boolean bandera16 = false;
		boolean bandera17 = false;
		Set<Programa> programas = new HashSet<>();
		ProcessBuilder pb = new ProcessBuilder("cmd.exe",
				"/C REG QUERY HKLM\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\ /s");
		Process process;
		try {
			process = pb.start();
			Hilo outputHandler = new Hilo(process.getInputStream());
			outputHandler.start();
			process.waitFor();

			texto = outputHandler.getOutput().toString();
			textoActual = texto;
			textoBuscado = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (texto.indexOf(textoBuscado) > -1) {
			texto = texto.substring(texto.indexOf(textoBuscado) + textoBuscado.length(), texto.length());
			contador++;
		}
		arreglo = new String[contador + 1];
		for (int i = 0; i <= contador; i++) {
			if (i < contador - 1) {
				textoActual = textoActual.substring(textoActual.indexOf(textoBuscado) + textoBuscado.length(),
						textoActual.length());
				textoSiguiente = textoActual.substring(0, textoActual.indexOf(textoBuscado));
				arreglo[i] = textoSiguiente;
			} else {
				arreglo[i] = texto;
			}

		}
		arreglo1 = arreglo;
		for (int i = 0; i < arreglo.length - 1; i++) {
			
			
			if (arreglo[i].indexOf("DisplayName ") != -1 || arreglo1[i].indexOf("DisplayVersion ") != -1) {
				if (arreglo[i].indexOf("DisplayName ") != -1) {

					displayName = arreglo[i].substring(arreglo[i].indexOf("DisplayName "));
					textoPrueba = displayName.substring(0, displayName.indexOf('\n') - 1);
					programa = textoPrueba.substring(25);

				} else
					programa = "No definido";
				if (arreglo1[i].indexOf("DisplayVersion ") != -1) {
					displayVersion = arreglo1[i].substring(arreglo1[i].indexOf("DisplayVersion "));
					textoPrueba = displayVersion.substring(0, displayVersion.indexOf('\n') - 1);
					version = textoPrueba.substring(28);
				} else
					version = "No definido";
				prog = new Programa(programa, version);
				programas.add(prog);

			}

		}
		////////////////////////////////////////////////////////////////////
		////////// version de java///////////////////////////////////////////
		///////////////////////////////////////////////////////////////////

		
		try {
			p = Runtime.getRuntime().exec("cmd /c java -version");
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			linea = error.readLine().substring(16, 23);
			prog = new Programa("java", linea);
			programas.add(prog);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/////////////////////////////////////////////////////////////////////
		List<Programa> listaProgramas = new ArrayList<Programa>(programas);
		List<String> listaDesplegable = new ArrayList<String>();
		Collections.sort(listaProgramas);
		///////////////////////////// validaciones///////////////////////////////////////////////////////////////

		for (int i = 0; i <= listaProgramas.size() - 1; i++) {
			if (listaProgramas.get(i).getNombre().equals("java")&& listaProgramas.get(i).getVersion().equals("1.7.0_55")) {
				listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
			} else if (listaProgramas.get(i).getNombre().equals("java")) {
				listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion()+"  version instalada 7u55 no es la instalada");
			}
			if (listaProgramas.get(i).getNombre().contains("java")) {
				bandera1 = true;
			}
			
	     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 15 && listaProgramas.get(i).getNombre().length() >= 4) {
				if (listaProgramas.get(i).getNombre().substring(0, 15).equals("Mozilla Firefox")&& listaProgramas.get(i).getVersion().substring(0, 4).equals("36.0")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 15).equals("Mozilla Firefox")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion()+"  version instalada version 36 no es la instalada");
				}
				if (listaProgramas.get(i).getNombre().contains("Mozilla Firefox")) {
					bandera2 = true;
				}
			}
		     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			if (listaProgramas.get(i).getNombre().length() >= 22 && listaProgramas.get(i).getNombre().length() >= 14) {
				if (listaProgramas.get(i).getNombre().substring(0, 22).equals("Microsoft Office Excel")	&& listaProgramas.get(i).getVersion().substring(0, 14).equals("12.0.4518.1014")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 22).equals("Microsoft Office Excel")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("Microsoft Office Excel")) {
					bandera3 = true;
				}
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 21 && listaProgramas.get(i).getNombre().length() >= 14) {

				if (listaProgramas.get(i).getNombre().substring(0, 21).equals("Microsoft Office Word")&& listaProgramas.get(i).getVersion().substring(0, 14).equals("12.0.4518.1014")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 21).equals("Microsoft Office Word")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("Microsoft Office Word")) {
					bandera4 = true;
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 27 && listaProgramas.get(i).getNombre().length() >= 14) {

				if (listaProgramas.get(i).getNombre().substring(0, 27).equals("Microsoft Office PowerPoint")&& listaProgramas.get(i).getVersion().substring(0, 14).equals("12.0.4518.1014")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 27).equals("Microsoft Office PowerPoint")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("Microsoft Office PowerPoint")) {
					bandera5 = true;
				}
			}
			//////////////////////////////////////////////////////////////////////////////////////////////////////////
				if (listaProgramas.get(i).getNombre().length() >= 18 &&listaProgramas.get(i).getNombre().substring(0, 18).equals("Adobe Flash Player")) {
				String palabra=listaProgramas.get(i).getNombre();
				String palabrarecor=palabra.substring(18).trim();
				int  indice =palabrarecor.indexOf("ActiveX");
				if(indice!=-1){
					String palabraactu=palabrarecor.substring(indice, palabrarecor.length());
					if (palabra.substring(0,18).equals("Adobe Flash Player")&&palabraactu.equals("ActiveX")&& listaProgramas.get(i).getVersion().substring(0, 10).equals("26.0.0.151")) {
						listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
						//System.out.println(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
					} else if (palabra.substring(0,18).equals("Adobe Flash Player")&&palabraactu.equals("ActiveX")) {
						listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
					}
					if (palabra.substring(0,18).equals("Adobe Flash Player")&&palabraactu.equals("ActiveX")) {
						bandera6 = true;
					}
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if (listaProgramas.get(i).getNombre().length() >= 18 &&listaProgramas.get(i).getNombre().substring(0, 18).equals("Adobe Flash Player")) {
				String palabra=listaProgramas.get(i).getNombre();
				String palabrarecor=palabra.substring(18).trim();
				int  indice =palabrarecor.indexOf("NPAPI");
				if(indice!=-1){
					String palabraactu=palabrarecor.substring(indice, palabrarecor.length());
					if (palabra.substring(0,18).equals("Adobe Flash Player")&&palabraactu.equals("NPAPI")&& listaProgramas.get(i).getVersion().substring(0, 10).equals("26.0.0.137")) {
						listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
						//System.out.println(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
					} else if (palabra.substring(0,18).equals("Adobe Flash Player")&&palabraactu.equals("NPAPI")) {
						listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
					}
					if (palabra.substring(0,18).equals("Adobe Flash Player")&&palabraactu.equals("NPAPI")) {
						bandera7 = true;
					}
				}
			}
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 15 && listaProgramas.get(i).getVersion().length() >= 7) {
				
				if (listaProgramas.get(i).getNombre().substring(0, 15).equals("Adobe Reader XI")&& listaProgramas.get(i).getVersion().substring(0, 7).equals("11.0.00")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 15).equals("Adobe Reader XI")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
					
				}
				
				if (listaProgramas.get(i).getNombre().contains("Adobe Reader XI")) {
					bandera8 = true;
				}
				
			}
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getVersion().length() >= 5 && listaProgramas.get(i).getNombre().length() >= 13) {
					
				if (listaProgramas.get(i).getNombre().substring(0, 13).equals("PDF24 Creator")&& listaProgramas.get(i).getVersion().substring(0, 5).equals("3.7.0")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 13).equals("PDF24 Creator")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("PDF24 Creator")) {
					bandera9 = true;
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 42 && listaProgramas.get(i).getVersion().length() >= 11) {
				if (listaProgramas.get(i).getNombre().substring(0, 42).equals("Kaspersky Endpoint Security 10 para Window")&& listaProgramas.get(i).getVersion().substring(0, 11).equals("10.2.5.3201")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " --> " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 42).equals("Kaspersky Endpoint Security 10 para Window")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "---> "+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("Kaspersky Endpoint Security 10 para Window")) {
					bandera10 = true;
				}
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 45  && listaProgramas.get(i).getVersion().length() >= 8) {
				if (listaProgramas.get(i).getNombre().substring(0, 45).equals("Agente de red de Kaspersky Security Center 10")&& listaProgramas.get(i).getVersion().substring(0, 8).equals("10.3.407")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " -->-- " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 45).equals("Agente de red de Kaspersky Security Center 10")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "--->++"+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("Agente de red de Kaspersky Security Center 10")) {
					bandera11 = true;
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 13  && listaProgramas.get(i).getVersion().length() >= 5) {
				if (listaProgramas.get(i).getNombre().substring(0, 13).equals("Intisign v2.0")&& listaProgramas.get(i).getVersion().substring(0, 5).equals("2.0.1")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " -->-- " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 13).equals("Intisign v2.0")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "--->++"+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("Intisign v2.0")) {
					bandera12 = true;
				}
			}
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 31  && listaProgramas.get(i).getVersion().length() >= 10) {
				if (listaProgramas.get(i).getNombre().substring(0, 31).equals("IBM Notes 9.0 Social Edition es")&& listaProgramas.get(i).getVersion().substring(0, 10).equals("9.00.13086")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " -->-- " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 31).equals("IBM Notes 9.0 Social Edition es")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "--->++"+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("IBM Notes 9.0 Social Edition es")) {
					bandera13 = true;
				}
			}
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 9  && listaProgramas.get(i).getVersion().length() >= 3) {
				if (listaProgramas.get(i).getNombre().substring(0, 9).equals("filezilla")&& listaProgramas.get(i).getVersion().substring(0, 3).equals("1.0")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " -->-- " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 9).equals("filezilla")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "--->++"+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("filezilla")) {
					bandera14 = true;
				}
			}
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 18  && listaProgramas.get(i).getVersion().length() >= 5) {
				if (listaProgramas.get(i).getNombre().substring(0, 18).equals("Roxio Creator Copy")&& listaProgramas.get(i).getVersion().substring(0, 5).equals("3.7.0")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " -->-- " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 18).equals("Roxio Creator Copy")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "--->++"+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("Roxio Creator Copy")) {
					bandera15 = true;
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 6  && listaProgramas.get(i).getVersion().length() >= 11) {
				if (listaProgramas.get(i).getNombre().substring(0, 6).equals("WinZip")&& listaProgramas.get(i).getVersion().substring(0,11).equals("No definido")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " -->-- " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 6).equals("WinZip")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "--->++"+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("WinZip")) {
					bandera16 = true;
				}
			}
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (listaProgramas.get(i).getNombre().length() >= 16  && listaProgramas.get(i).getVersion().length() >= 11) {
				if (listaProgramas.get(i).getNombre().substring(0, 16).equals("Compresor WinRAR")&& listaProgramas.get(i).getVersion().substring(0,11).equals("No definido")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + " -->-- " + listaProgramas.get(i).getVersion());
				} else if (listaProgramas.get(i).getNombre().substring(0, 16).equals("Compresor WinRAR")) {
					listaDesplegable.add(listaProgramas.get(i).getNombre() + "--->++"+ listaProgramas.get(i).getVersion());
				}
				if (listaProgramas.get(i).getNombre().contains("Compresor WinRAR")) {
					bandera17 = true;
				}
			}
		}
		
		//////////////////////////////si no existen////////////////////////////////////////////////////////////////////////////////
		if (bandera1 == false) {
			listaDesplegable.add("java NO EXISTE");
		}
		if (bandera2 == false) {
			listaDesplegable.add("Mozilla Firefox NO EXISTE");
		}
		if (bandera3 == false) {
			listaDesplegable.add("Microsoft Office Excel NO EXISTE");
		}
		if (bandera4 == false) {
			listaDesplegable.add("Microsoft Office Word NO EXISTE");
		}
		if (bandera5 == false) {
			listaDesplegable.add("Microsoft Office PowerPoint NO EXISTE");
		}
		if (bandera6 == false) {
			listaDesplegable.add("Adobe Flash Player -- ActiveX NO EXISTE");
		}
		if (bandera7 == false) {
			listaDesplegable.add("Adobe Flash Player -- NPAPI NO EXISTE");
		}
		if (bandera8 == false) {
			
			listaDesplegable.add("Adobe Reader XI -- NO EXISTE");
		}
		if (bandera9 == false) {
			listaDesplegable.add("PDF24 Creator -- NO EXISTE");
		}
		if (bandera10 == false) {
			listaDesplegable.add("Kaspersky Endpoint Security 10 Maintenance Release 1 para complemento de consola de Windows -- NO EXISTE");
		}
		if (bandera11 == false) {
			listaDesplegable.add("Agente de red de Kaspersky Security Center 10 -- NO EXISTE");
		}
		if (bandera12 == false) {
			listaDesplegable.add("Intisign v2.0 -- NO EXISTE");
		}
		if (bandera13 == false) {
			listaDesplegable.add("IBM Notes 9.0 Social Edition es -- NO EXISTE");
		}
		if (bandera14 == false) {
			listaDesplegable.add("filezilla -- NO EXISTE");
		}
		if (bandera15 == false) {
			listaDesplegable.add("Roxio Creator Copy -- NO EXISTE");
		}
		if (bandera16 == false) {
			listaDesplegable.add("WinZip -- NO EXISTE");
		}
		if (bandera17 == false) {
			listaDesplegable.add("Compresor WinRAR -- NO EXISTE");
		}
		for (Programa programa2 : listaProgramas) {
			System.out.println(programa2.getNombre()+"-->"+programa2.getVersion());
		}
		int i=40;
		for (String string : listaDesplegable) {
			if(string.substring(string.length()-9,string.length()).equals("NO EXISTE")){
				g.setColor(Color.RED);
				g.drawString(string, 5, i);
			}else if(string.substring(string.length()-9,string.length()).equals("instalada")){
				g.setColor(Color.ORANGE);
				g.drawString(string, 5, i);
			}else{
				g.setColor(Color.BLUE);
				g.drawString(string, 5, i);
			}
				
			i+=15;
		}
		g.setColor(Color.BLACK);
		i=i+10;
		g.drawString("fin del Escaneo", 5, i);

	}

}
