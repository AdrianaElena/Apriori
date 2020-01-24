
public class Itemset {

	private String[] nume;
	private int count;
	private double conf;

	public Itemset() {
		count = 0;
		conf = 0;
	}

	public Itemset(String[] nume) {
		this.nume = new String[nume.length];

		for (int i = 0; i < nume.length ; i++) {
			this.nume[i] = nume[i];
		}
	}

	public String[] getNume() {
		return nume;
	}

	public void setNume(String[] nume) {
		this.nume = new String[nume.length];

		for (int i = 0; i < nume.length ; i++) {
			this.nume[i] = nume[i];
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setItemNume(String item) {
		nume = new String[2];
		nume[0] = item;
	}

	public double getConf() {
		return conf;
	}

	public void setConf(double conf) {
		this.conf = conf;
	}

	public void afisNume() {
		for (int i = 0; i < this.nume.length - 1; i++) {
			System.out.print(this.nume[i] + " ");
		}
	}
}
