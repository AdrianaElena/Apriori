import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Data {
	
	private List<String> listaAtribute;
	private ArrayList<String> listaTranzactii;
	private int nrTranzactii;
	private File numeFisier;
	
	public Data(File numeFisier) {
		this.numeFisier = numeFisier;
		nrTranzactii = 0;
		listaAtribute = new ArrayList<String>();
		listaTranzactii = new ArrayList<String>();
	}
	
	public void citesteFisier() {
		
		try {
			Scanner in  = new Scanner(numeFisier);
			
			String line;
			String[] words;
			
			while(in.hasNextLine()) {
				
				line = in.nextLine();
				listaTranzactii.add(line);
				
				words = line.split(", ");
				for(String word: words) {
					if(!listaAtribute.contains(word)) {
						listaAtribute.add(word);
					}
				}
				
				nrTranzactii++;
			}
			in.close();
			Collections.sort(listaAtribute);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public File getNumeFisier() {
		return numeFisier;
	}

	public void setNumeFisier(File numeFisier) {
		this.numeFisier = numeFisier;
	}

	public List<String> getListaAtribute() {
		return listaAtribute;
	}

	public List<String> getListaTranzactii() {
		return listaTranzactii;
	}

	public int getNrTranzactii() {
		return nrTranzactii;
	}
	
}
