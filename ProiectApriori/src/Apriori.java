import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Apriori {

	private int minSuport;
	private double minConfidenta;
	private int nrReguli;
	private ArrayList<Itemset> itemseturiFrecvente;
	private ArrayList<Itemset> stangaReguli;
	private ArrayList<Itemset> dreaptaReguli;
	private ArrayList<Double> confReguli;
	private Data data;

	public Apriori(File numeFisier) {
		itemseturiFrecvente = new ArrayList<Itemset>();
		stangaReguli = new ArrayList<Itemset>();
		dreaptaReguli = new ArrayList<Itemset>();
		confReguli = new ArrayList<Double>();
		
		data = new Data(numeFisier);
		data.citesteFisier();
	}

	
	public ArrayList<Itemset> getItemseturiFrecvente() {
		return itemseturiFrecvente;
	}

	public ArrayList<Double> getConfReguli() {
		return confReguli;
	}

	public ArrayList<Itemset> getStangaReguli() {
		return stangaReguli;
	}

	public ArrayList<Itemset> getDreaptaReguli() {
		return dreaptaReguli;
	}

	public void setMinSuport(int minSuport) {
		this.minSuport = minSuport;
	}

	public void setMinConfidenta(double minConfidenta) {
		this.minConfidenta = minConfidenta;
	}

	public int getNrReguli() {
		return nrReguli;
	}

	public void setNrReguli(int nrReguli) {
		this.nrReguli = nrReguli;
	}

	public ArrayList<Itemset> initC() {
		ArrayList<Itemset> C = new ArrayList<Itemset>();
		Iterator<String> it = data.getListaAtribute().iterator();
		Itemset item;

		while (it.hasNext()) {
			item = new Itemset();
			item.setItemNume(it.next());
			C.add(item);
		}

		return C;
	}

	public void aprioriRun() {

		ArrayList<Itemset> LCurent = new ArrayList<Itemset>();
		ArrayList<Itemset> C = initC();
		int k = 1;

		System.out.println("Lista initiala: ");
		afisareLista(C);

		while (!C.isEmpty()) {
			Iterator<String> itTranz = data.getListaTranzactii().iterator();

			while (itTranz.hasNext()) {

				String line = itTranz.next();

				Iterator<Itemset> itC = C.iterator();

				while (itC.hasNext()) {
					Itemset itemset = itC.next();
					String[] nume = itemset.getNume();

					int ok = 1;

					String[] words = line.split(", ");
					for (int i = 0; i < nume.length - 1; i++) {
						boolean apare = false;
						for (String word : words) {

							if (nume[i].equals(word)) {
								apare = true;
							}
						}
						if (!apare) {
							ok = 0;
						}
					}
					if (ok == 1) {
						int currentCount = itemset.getCount();
						currentCount++;
						itemset.setCount(currentCount);
					}
				}
			}

			Iterator<Itemset> itc = C.iterator();
			while (itc.hasNext()) {
				Itemset itSet = itc.next();

				if (itSet.getCount() >= minSuport) {
					LCurent.add(itSet);
					itemseturiFrecvente.add(itSet);
				}
			}

			System.out.println("L(" + k + "): " + LCurent.size());
			k++;
			afisareLista(LCurent);

			C = aprioriGen(LCurent);
			LCurent.removeAll(LCurent);// ?????
		}

		System.out.println("Multimea itemseturior frecvente: ");
		afisareLista(itemseturiFrecvente);
	}

	public ArrayList<Itemset> aprioriGen(ArrayList<Itemset> L) {

		ArrayList<Itemset> C = new ArrayList<Itemset>();
		String[] itemset1;
		String[] itemset2;
		String[] rez;
		for (int i = 0; i < L.size() - 1; i++) {
			itemset1 = L.get(i).getNume();

//			System.out.println("primul nume");
//			for (int p = 0; p < itemset1.length - 1; p++) {
//				System.out.println(itemset1[p]);
//			}

			for (int j = i + 1; j < L.size(); j++) {
				itemset2 = L.get(j).getNume();

//				System.out.println("al doilea nume");
//				for (int q = 0; q < itemset2.length - 1; q++) {
//					System.out.println(itemset2[q]);
//				}

				if (validare(itemset1, itemset2) == 1) {

					rez = creareItemset(itemset1, itemset2);
//					System.out.println("rezultatul este: ");
//					for (int p = 0; p < rez.length - 1; p++) {
//						System.out.println(rez[p]);
//					}
					// daca are subset infrecvent

					Itemset itemSET = new Itemset();
					itemSET.setNume(rez);
					C.add(itemSET);

				}
			}
		}
		System.out.println("Urmatoarea multime generata: ");
		afisareLista(C);

		return C;
	}

	private int validare(String[] it1, String[] it2) {
		int ok = 1;
		int l = it1.length;// lungime

		if (l == 2 && !it1[0].equals(it2[0])) {
			return 1;
		}

		for (int i = 0; i < l - 2; i++) {

			if (!it1[i].equals(it2[i])) {
				ok = 0;
			}
		}
		if (it1[l - 2].equals(it2[l - 2]) || it1[l - 2].compareTo(it2[l - 2]) > 0) {
			ok = 0;
		}

		return ok;
	}

	private String[] creareItemset(String[] it1, String[] it2) {
		int l = it1.length + 1, i;
		String rez[] = new String[l];

		for (i = 0; i < it1.length - 1; i++) {
			rez[i] = it1[i];
		}
		rez[i] = it2[it2.length - 2];

		return rez;
	}

	private void afisareLista(ArrayList<Itemset> L) {

		for (int i = 0; i < L.size(); i++) {
			String[] s = L.get(i).getNume();
			for (int j = 0; j < s.length - 1; j++) {
				System.out.print(s[j] + " ");
			}
			System.out.println(L.get(i).getCount() + " ");
			System.out.println("\n");
		}
	}

	public void generareReguli() {

		Iterator<Itemset> it = itemseturiFrecvente.iterator();
		int i = 0, j = 0;

		while (it.hasNext()) {
			Itemset item = it.next();

			if (item.getNume().length > 2) {
				String[] nume = item.getNume();

				System.out.println("Generate subsets for:");
				for (int h = 0; h < nume.length - 1; h++) {
					System.out.println(nume[h]);
				}

				if (item.getNume().length == 3) {
					String[] stanga = new String[1];
					String[] dreapta = new String[1];
					stanga[0] = nume[0];
					dreapta[0] = nume[1];

					addRegConf(nume, stanga, dreapta);
					addRegConf(nume, dreapta, stanga);
				} else {
					List<Itemset> subSets = generareSubsets(nume);

					Iterator<Itemset> itSubSets = subSets.iterator();

					int d = 0;
					while (itSubSets.hasNext()) {
						Itemset itemSetNume = itSubSets.next();

						String[] stanga = itemSetNume.getNume();
						System.out.println("set " + d);
						d++;

//						System.out.println("Partea stanga ");
//						for (int g = 0; g < stanga.length; g++) {
//							System.out.print(stanga[g]);
//						}
//						System.out.print("\n");
						
						String[] dreapta = getDr(nume, stanga);
//						System.out.println("Partea dreapta ");
//						for (int g = 0; g < dreapta.length; g++) {
//							System.out.print(dreapta[g]);
//						}
//						System.out.println("\n");
						
						addRegConf(nume, stanga, dreapta);
					}
				}
			}
		}
		
		afisReguli();
	}

	private void addRegConf(String[] nume, String[] stanga, String[] dreapta) {

		int frecvTotal = calcFrecv(nume);
		int frecvSt = calcFrecv(stanga);
		int frecvDrp = calcFrecv(dreapta);
		double currFrecv = (double) frecvTotal / frecvSt;
		
		System.out.println("\nFRECV "+currFrecv);
		
		int rez = Double.compare(currFrecv, minConfidenta);
		if (rez >= 0) {
			Itemset i1 = new Itemset(stanga);
			i1.setConf(currFrecv);
			stangaReguli.add(i1);

			Itemset i2 = new Itemset(dreapta);
			i2.setConf(currFrecv);
			dreaptaReguli.add(i2);
			
			confReguli.add(currFrecv);
			
		}
	}
	
	private void afisReguli() {
		
		for(int i=0; i<stangaReguli.size(); i++) {
			Itemset it1 = stangaReguli.get(i);
			String[] n1 = it1.getNume();

			System.out.print(Arrays.toString(n1)+" -> ");
			
			Itemset it2 = dreaptaReguli.get(i);
			String[] n2 = it2.getNume();
			System.out.print(Arrays.toString(n2)+" ");
			System.out.println(" conf "+confReguli.get(i));
			System.out.println("\n");
		}
	}

	private List<Itemset> generareSubsets(String[] set) {
		int n = set.length - 1;
		String a = "";
		List<Itemset> lista = new ArrayList<Itemset>();

		for (int i = 0; i < (1 << n); i++) {
			a = "";

			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {

					a += set[j];
					a += ", ";
				}
			}
			System.out.println(a + " ");

			String[] itemSpl = a.split(", ");

			Itemset itSetP = new Itemset(itemSpl);

			lista.add(itSetP);
		}

		 lista.remove(0);
		 int index = lista.size()-1;
		 lista.remove(index);

		return lista;

	}

	private String[] getDr(String nume[], String[] st) {

		String total = StringUtils.join(nume);
		System.out.print("total: " + total);
		String toremove = StringUtils.join(st);

		String drp = StringUtils.remove(total, toremove);
		System.out.print("after remove: " + drp + "\n");
		String[] dr = StringUtils.split(drp);
		
//		String[] dr = nume;
//		
//		for(int i=0; i<dr.length; i++) {
//			for(int j=0; j<st.length; j++) {
//				if(dr[i].equals(st[j])) {
//					//dr = ArraysUtils
//				}
//			}
//		}

		return dr;
	}

	private int calcFrecv(String[] item) {
		Iterator<String> itTranz = data.getListaTranzactii().iterator();
		int contor = 0, i = 0;

		while (itTranz.hasNext()) {
			String line = itTranz.next();
			String[] words = line.split(", ");
			int ok = 1;

			for (i = 0; i < item.length - 1; i++) {
				boolean apare = false;
				for (String word : words) {

					if (item[i].equals(word)) {
						apare = true;
					}
				}
				if (!apare) {
					ok = 0;
				}
			}
			if (ok == 1) {
				contor++;
			}
		}

		return contor;
	}
}
