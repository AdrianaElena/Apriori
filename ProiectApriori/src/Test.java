import java.io.File;

public class Test {

	public static void main(String[] args) {

		
		File f = new File("data");
		Apriori apriori = new Apriori(f);
		apriori.setMinSuport(3);
		apriori.aprioriRun();
		apriori.setMinConfidenta(0.60);
		apriori.generareReguli();
        
	}

}

