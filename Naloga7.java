import java.util.*;
import java.io.*;
import java.lang.*;

public class Naloga7 {
	
	//public static int fillArray = -1;
	public static int[] obhod_OG = new int[1];
	public static int[] min_OG = new int[1];
	public static int[] prej_OG = new int[1];
	public static boolean[] obiskani_OG = new boolean[1];
	public static Integer[] pot_OG = new Integer[1];
	
//	public static int[] stPovez_OG = new int[1];
//	public static int[] prejVez_OG = new int[1];
	
	public static int prvic = 0;
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		long start = System.currentTimeMillis();
		//pripravimo tokove za branje in pisanje v datoteko 
		Scanner sc = new Scanner(System.in);
		BufferedReader tok = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new FileWriter(args[1]));
		

		//vsi glavni podatki vhoda 
		String vrstica;
		int stVozlisc = 0;
		int stPovezav = 0;
		
		int zacetek = 0;
		int konec = 0;
		
		//shranimo prvo vrstico podatkov G(E, V)
		vrstica = tok.readLine();
		
		int[] vrednosti = new int[2];
		int stevec = 0;
		for (String podatki : vrstica.split(" ")) {
			vrednosti[stevec] = Integer.parseInt(podatki);
			stevec++;
		}
		
		stVozlisc = vrednosti[0];
		stPovezav = vrednosti[1];
		
		//shranimo drugo vrstico podatkov: zacetek, konec
		vrstica = tok.readLine();
		
		stevec = 0;
		for (String podatki : vrstica.split(" ")) {
			vrednosti[stevec] = Integer.parseInt(podatki);
			stevec++;
		}
		
		zacetek = vrednosti[0];
		konec = vrednosti[1];
		
		//preberemo še vse ostale vrstice (V) in naredimo 2D tabelo povezav grafa
		
		int[][] graf = new int [stVozlisc][stVozlisc];
		GrafL g = new GrafL(stVozlisc);
		
		//OPTIMIZACIJA
//		int[] stPovez = new int[stVozlisc];
//		int[] prejVez = new int[stVozlisc];
//		System.arraycopy(stPovez_OG, 0, stPovez, 0, 1);
//		System.arraycopy(prejVez_OG, 0, prejVez, 0, 1);
			
//			stPovez_OG = stPovez;
//			prejVez_OG = prejVez;
		
//		Arrays.fill(prejVez, -1);
		
		for (int i = 0; i < stPovezav; i++) {
			vrstica = tok.readLine();
			int j = 0; 
			for (String podatki1 : vrstica.split(" ")) {
				vrednosti[j] = Integer.parseInt(podatki1);
				j++;
			}
			graf[vrednosti[0]][vrednosti[1]] = graf[vrednosti[1]][vrednosti[0]] = 1;
			
			g.novaP(vrednosti[0], vrednosti[1]);
			
			/*
			if (stPovez[vrednosti[0]] == 0) {
				prejVez[vrednosti[0]] = vrednosti[1];
				stPovez[vrednosti[0]]++;
			} else {
				prejVez[vrednosti[0]] = -1;
			}
			if (stPovez[vrednosti[1]] == 0) {
				prejVez[vrednosti[1]] = vrednosti[0];
				stPovez[vrednosti[1]]++;
			} else {
				prejVez[vrednosti[1]] = -1;
			}*/
			
				
			
			
		}
		
		
		/*TEST BRANJA
		for (int i = 0; i < stVozlisc; i++) {
			for (int j = 0; j < stVozlisc; j++) {
				System.out.print(graf[i][j]);
			}
			System.out.println();
		}
		*/
		
		
		//POVEČANJE TABEL
			int[] obhod = new int[stVozlisc];
			System.arraycopy(obhod_OG, 0, obhod, 0, 1);
			int[] min = new int[stVozlisc];
			System.arraycopy(min_OG, 0, min, 0, 1);
			int[] prej = new int[stVozlisc];
			System.arraycopy(prej_OG, 0, prej, 0, 1);
			boolean[] obiskani = new boolean[stVozlisc];
			System.arraycopy(obiskani_OG, 0, obiskani, 0, 1);
			Integer[] pot = new Integer[stVozlisc];
			System.arraycopy(pot_OG, 0, pot, 0, 1);
			
			obhod_OG = obhod;
			min_OG = min;
			prej_OG = prej;
			obiskani_OG = obiskani;
			pot_OG = pot;
			
			Arrays.fill(obhod_OG, 0);
			Arrays.fill(min_OG, 0);
			Arrays.fill(prej_OG, -1);
			Arrays.fill(obiskani_OG, false);
			Arrays.fill(pot_OG, -1);
		
		//naredimo tabeli PREJ in OBISKANI
		int[] PREJ = new int[stVozlisc];
		boolean[] OBISKANI = new boolean[stVozlisc];
		
		int[] sosedi = new int [stVozlisc];
		
		Arrays.fill(PREJ, -2);
		PREJ[zacetek] = -1;
		//naredimo vrsto s katero rešimo problem
		Queue<Integer> vrsta = new LinkedList<>();
		vrsta.add(zacetek);
		OBISKANI[zacetek] = true;
		int element = -1;
		int c = 0;
		
		while (element != konec) {
			element = vrsta.remove();
			for (int i = 0; i < stVozlisc; i++) {
				if (graf[element][i] != 0) {
					if (!OBISKANI[i]) {
						pot_OG[c] = i;
						c++;
						vrsta.add(i);
						OBISKANI[i] = true;
						PREJ[i] = element;
					}
				}
			}
									
					//	if (element == konec) {
					//		break;
					//	}
						
		}
		
		//TEST VRSTE IN BFS
		/*
		for (int i = 0; i < stVozlisc; i++) {
			System.out.println(PREJ[i]);
		}
		*/
		StringBuilder sb = new StringBuilder();
		
		int index = konec;
		sb.append(index + "-");
		for (int i = 0; i < stVozlisc; i++) {
			index = PREJ[index];
			if (index == -1) {
				break;
			} 
			sb.append(index + "-");
		}
		
		//naredimo string vozlišč od začetka do konca
		System.out.println();
	//	System.out.println(sb);
		String sb_S = sb.toString();
		String[] AB_pot = new String[stVozlisc];
		int m = 0;
		
		for (String AB : sb_S.split("-")) {
			AB_pot[m] = AB;
			m++;
		}
		
		//String pot = sb_S.replaceAll("-", "");
		//System.out.println(pot);
		
		String a;
		String b;
		int a1 = 0;
		int b1 = 0;
		String aK;
		String bK;
		int temp = 0;
		
		//elemente moramo dati v množico
		//String[] rez = new String[2];
		
		//MNOŽICA POIT
		Set<String> potAB = new HashSet<String>();
		
		int k = 0;
		while (AB_pot[k+1] != null && k < AB_pot.length-1) {
	//		System.out.println("k " + k);
	//		System.out.println("k+1 " + (k+1));
			a = AB_pot[k];
			b = AB_pot[k+1];
			k++;
		//	System.out.println(a + " " + b);
			
			a1=Integer.parseInt(a);  
			b1=Integer.parseInt(b);  
			temp = a1;
			a1 = Math.min(a1, b1);
			b1 = Math.max(temp, b1);
			temp = 0;
		//	System.out.println(a1 + " " + b1);
			
			aK = Integer.toString(a1);
			bK = Integer.toString(b1);
		//	System.out.println(aK);
		//	System.out.println(bK);
			potAB.add(aK + " " + bK);
		}
			
			
		//ČASOVNI TEST
		//System.out.println();
		//System.out.println();
		//System.out.println();
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//MOSTOVI 
	/*	for (int i = 0; i < prejVez.length; i++) {
			if (prejVez[i] != -1) {
				obiskani[i] = true;
				System.out.println("MOSTOVI_1:" + i + " " + prejVez[i]);
			}
		}*/
		
		g.most();
		Set<String> mostAB = g.vrniMnozico();
		
		Set<String> presekAB = new HashSet<String>(potAB);
		presekAB.retainAll(mostAB);
		
		//System.out.println(potAB);
		//System.out.println(mostAB);
		
		//System.out.println(presekAB);
		
		List<String> resitev = new ArrayList<String>(presekAB);
		Collections.sort(resitev, new Comparator<String>() {
			public int compare(String x, String y) {
				int x1 = Integer.parseInt(x.split(" ")[0]);
				int y1 = Integer.parseInt(y.split(" ")[0]);
				
				if (x1 == y1) {
					int x2 = Integer.parseInt(x.split(" ")[1]);
					int y2 = Integer.parseInt(y.split(" ")[1]);
					return x2-y2;
				}
				//System.out.println(x1 + " " +y1);
				/*if (x1 == y1 && prvic == 0) {
					compare(x, y);
					prvic = 1;
				} */
					
				//	prvic = 0;
					return x1 - y1;
												
			}
		}
		);
		
		//System.out.println("delaJA");
		
		for (String rezK : resitev) {
			//System.out.println(rezK);
			pw.println(rezK);
		}
		pw.close();
		
		
		long rez = System.currentTimeMillis() - start;
		System.out.println("Time in millis: " + rez);

		/* MNOŽICE TEST
		Scanner sc = new Scanner(System.in);
		
		Set<String> A = new HashSet<String>();
		A.add("2-3");
		A.add("3-0");
		
		Set<String> B = new HashSet<String>();
		B.add("0-1");
		B.add("3-0");
		B.add("2-3");
		
		Set<String> I = new HashSet<String>(A);
		I.retainAll(B);
		
		System.out.println(I);
		*/
	}

	
	
			public static class GrafL {
			public int VozliscaN;
			int count = 0; 
			public ArrayList<Integer> tab[];
			public Set<String> testAB = new HashSet<String>();
			
			public GrafL(int stVozlisc) {
				//System.out.println("done");
				this.VozliscaN = stVozlisc;
				this.tab = new ArrayList[this.VozliscaN];
				
				for (int i = 0; i < this.VozliscaN; i++) {
					tab[i] = new ArrayList<Integer>(); 
				}
			}
			
			public void novaP(int x, int y) {
				//System.out.print(x + "-" + y + ", ");
				tab[x].add(y);
				tab[y].add(x);
			}
			
			/*
			public void izbisiP(int x, int y) {
				System.out.println("MOSTOVI_1:" + x + " " + y);
				tab[x].remove(y);
				tab[y].remove(x);
			}
			*/
			public void testABf(int i, int j) {
				if (i < j) {
                    testAB.add(Integer.toString(i) + " " + Integer.toString(j));
                } else {
                    testAB.add(Integer.toString(j) + " " + Integer.toString(i));;
                    }
			}
			
			public Set<String> vrniMnozico() {
				return this.testAB;
			}
			
			////MOST METODA - DFS
			public void most () {
			

			
			//Arrays.fill(obiskani, false);
			//Arrays.fill(prej, -1);
			
			
				
					
					mostRek(pot_OG[0], -1);
				
			}
		
			
			
			//MOST REKURZIJA ZA PREGLEDOVANJE
void mostRek(int i, int p) { 
		//System.out.print("rek");
		min_OG[i] = obhod_OG[i] = count++;
		obiskani_OG[i] = true;
		
		for (int j: tab[i]) {
			
		//	if (!listArr.contains(j)) {
		//		continue;
		//	}
			if (j == p) {
				continue;
			}
			
	//		if (prejVez_OG[i] != -1) {
	//			if (i < prejVez_OG[i]) {
	//					mostAB.add(Integer.toString(i) + " " + Integer.toString(prejVez_OG[i]));
	//				} else {
	//					mostAB.add(Integer.toString(prejVez_OG[i]) + " " + Integer.toString(i));;	
	//				}
	//				
		//		continue;
		//	}
			
			if (obiskani_OG[j] == false) {
					prej_OG[j] = i; 
					mostRek(j, i);
					min_OG[i] = Math.min(min_OG[i], min_OG[j]);
				
				if (min_OG[j] > obhod_OG[i]) {
					if (i < j) {
						testABf(i,j);
					} else {
						testABf(j,i);
					}
			}

			} else if (j != prej_OG[i]) {
				min_OG[i] = Math.min(min_OG[i], obhod_OG[j]);
			}
			
		}
	} 
			
			
			
			
		}

}