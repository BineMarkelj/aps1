import java.io.*;
import java.util.Scanner;

public class Naloga2 {
	
	public static int[] row = {0, 1, 0, -1};
	public static int[] col = {1, 0, -1, 0};
	public static String[] navodilaGLOBAL = {"DESNO", "DOL", "LEVO", "GOR"};
	
	public static int stVrstic;
	public static int stStolpcev;
	
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		BufferedReader tok = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new FileWriter (args[1]));
		
		//pripravimo vse za shranjevanje vhodnih podatkov
		String vrstica;
		stVrstic = 0;
		stStolpcev = 0;
		
		//preberemo in sprocesiramo prvo vrstico vhoda: vrstice, stolpci
		vrstica = tok.readLine();
		int[] vrednosti = new int[2];
		int count = 0;
		for (String podatki : vrstica.split(",")) {
			vrednosti[count] = Integer.parseInt(podatki);
			count++;
		}
		
		stVrstic = vrednosti[0];
		stStolpcev = vrednosti[1];
		
		//naredimo array v katerega bomo dali labirint
		char[][] matrika = new char[stVrstic][stStolpcev];
		//char[] prebranaVrstica = new char[stStolpcev];
		count = 0;
		
		
		for (int i = 0; i < matrika.length; i++) {
			vrstica = tok.readLine();
			count = 0;
			
			for (String podatki : vrstica.split(",")) {
			//	System.out.print(podatki + " " + vrstica);
				matrika[i][count] = podatki.charAt(0);
				count++;
			}
		//	System.out.println();
		}
		
		
		/*TEST PREBIRANJA PODATKOV
		System.out.println(stVrstic + " " + stStolpcev);
	
	
		for (int i = 0; i < stVrstic; i++) {
			for (int j = 0; j < stStolpcev; j++) {
				System.out.print(matrika[i][j]);
			}
			System.out.println();
		}  */
		
		int najPot = 0;
		int naj_X = 0;
		int naj_Y = 0;
		
		for (int i = 0; i < stVrstic; i++) {
			for (int j = 0; j < stStolpcev; j++) {
				
				//	char[][] tempArr = new char[stVrstic][stStolpcev];
				//	for (int kopija = 0; kopija < stVrstic; kopija++) {
				//		System.arraycopy(matrika[kopija], 0, tempArr[kopija], 0, stStolpcev);
				//	}
				
					//System.out.print(i + " " + j + "   ");
					int pot = najdiMaxPot(matrika, i, j, matrika[i][j]);
					//System.out.println(pot);
					
					if (najPot < pot) {
						naj_X = i;
						naj_Y = j;
					}
					najPot = Math.max(najPot, pot);
			}
		} 
		//dobili smo podatke o najboljši poti in njenemu začeku
		//še enkrat moramo v podobno metoda, le da sproti gradimo string 
		String[] najNavodila = new String[najPot+1];
		String[] tempNavodila= new String[najPot+1];
		
		//samo klic metode, da dobimo vrednosti v tabelo
		izpisUkazov(matrika, naj_X, naj_Y, matrika[naj_X][naj_Y], tempNavodila, najNavodila, 0, 0);
		
	//	for (int i = 0; i < najNavodila.length; i++) {
	//		System.out.print(najNavodila[i]);
	//		System.out.print(",");
	//	}
	//	System.out.println();
		
		//System.out.println(naj_X + " " + naj_Y);
		//System.out.println(najPot);
		
	//	for (int i = 0; i < najNavodila.length; i++) {
	//		System.out.print(najNavodila[i] + " ");
	//	}
		
		
		//dobili smo rešitev, to še pravilno parsamo in zapišemo v datoteko na izhodu
		pw.println(naj_X + "," + naj_Y);
		
		for (int i = 0; i < najNavodila.length; i++) {
			if (najNavodila[i] == null) {
				continue;
			}
			pw.print(najNavodila[i]);
			if (i != (najNavodila.length-3)) {
				pw.print(",");
			}
			
		}
		pw.println();
		pw.close();
		
	}
	
	
	//preverjanje ali je polje x,y še v podani tabeli
	public static boolean jeZnotraj(int x, int y) {
        return (x >= 0 && x < stVrstic && y >= 0 && y < stStolpcev);
    }
 
	//originalna - prva metoda kjer dobimo le največjo dolžino poti
    public static int najdiMaxPot(char[][] labirint, int x, int y, char iskaniZnak) {	
		//System.out.println(iskaniZnak);
        if (!jeZnotraj(x, y) || iskaniZnak != labirint[x][y]) {
            return 0;
        }

        int najPot = 0;

		//uporabimo zunanji vektor 
        for (int smer = 0; smer < 4; smer++) {
			labirint[x][y] = '.';
            int pot = najdiMaxPot(labirint, x + row[smer], y + col[smer], iskaniZnak)+1;
			//System.out.println(pot);
            
            najPot = Math.max(najPot, pot);
			labirint[x][y] = iskaniZnak;
        }	
	//	System.out.println(najPot);
        return najPot;
    }
	


	//metoda skoraj ista kot prej le da so zdaj pomembna navodila iz pozicije x,y
	
	public static int izpisUkazov(char[][] labirint, int x, int y, char iskaniZnak, String[] temp, String[] najNavodila, int pot, int najPot) {
		 if (!jeZnotraj(x, y) || iskaniZnak != labirint[x][y]) {
            return 0;
        }
		
		if (najPot < pot) {
			najPot = pot;
			System.arraycopy(temp, 0, najNavodila, 0, temp.length);
		} 

		
		for (int smer = 0; smer < 4; smer++) {
			labirint[x][y] = '.';
			
			if ((jeZnotraj(x+row[smer], y+col[smer])) && (labirint[(x+row[smer])][(y+col[smer])] == iskaniZnak)) {
				temp[pot] = navodilaGLOBAL[smer];
				najPot = izpisUkazov(labirint, x+row[smer], y+col[smer], iskaniZnak, temp, najNavodila, pot+1, najPot);				
			}
			

			//najPot = Math.max(najPot, pot);
			
			//System.out.println(pot + " " + najPot + " " + temp + " ");
			//for (int i = 0 ; i < najNavodila.length; i++)System.out.print(najNavodila[i] + " ");
			//System.out.println();
			//if (najPot == pot) {
			
			//		for (int i = 0; i < najNavodila.length; i++) {
			//			najNavodila[i] = temp[i];
			//		}
				
			}
			
			labirint[x][y] = iskaniZnak;
		
		return najPot;
	}


	
	
}