import java.util.*;
import java.io.*;
import java.lang.*;

public class Naloga10 {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		
		long start = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		BufferedReader tok = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new FileWriter(args[1]));
		
		String vrstica;
		int stPovezav = 0;
		
		vrstica = tok.readLine();
		stPovezav = Integer.parseInt(vrstica);
		
		ArrayList<Povezava> povezave = new ArrayList<Povezava>();
		int[] buffer =  new int[3];
		int max = -1;
		
		for (int i = 0; i < stPovezav; i++) {
			vrstica = tok.readLine();
			
			int j = 0;
			for (String podatki : vrstica.split(",")) {
				buffer[j] = Integer.parseInt(podatki);
				if (j != 2) {max = Math.max(max, buffer[j]);}				
				j++;
			}
			
			povezave.add(new Povezava(buffer[0], buffer[1], buffer[2]));
			
		}
		
		int[][] graf = new int[max][max];
		
		for (int i = 0; i < povezave.size(); i++) {
			graf[(povezave.get(i)).V1-1][(povezave.get(i)).V2-1] = (povezave.get(i)).cena;
		}
		
	/* TEST 2D TABELE IN ARRAYLISTA	
		for (int i = 0; i < povezave.size(); i++) {
			System.out.println(povezave.get(i));
		}
		
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				System.out.print(graf[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}*/
		
		vrstica = tok.readLine();
		int j = 0;
		for (String podatki : vrstica.split(",")) {
			buffer[j] = Integer.parseInt(podatki);
			j++;
		}
		
		int startV = buffer[0];
		int finishV =  buffer[1];
		
		int[] h = new int[max];
		int[] h_spremembe = new int[max];
		
		/// ALGORITEM
		boolean[] obiskani = new boolean[max];
		int min = Integer.MAX_VALUE;
		int trenutniIndeks = startV-1;
		int naslednjiIndeks = 0;
		obiskani[trenutniIndeks] = true;
		int prej = 0;
	//	boolean obstajaPot = false;
		//int h1 = 0;
		boolean konec = false;
		String konecS = "";
		//boolean konecRES = false;
		
		while (!konec) {
			
		
		StringBuilder sb = new StringBuilder();
		sb.append((naslednjiIndeks+1));
		sb.append(",");
		
		while (trenutniIndeks != (finishV-1)) {
			
			for (int i = 0; i < max; i++) {
				if ((graf[trenutniIndeks][i] != 0) && (obiskani[i] == false)) {
			//		obstajaPot = true;
			//	System.out.println(min + " "+ graf[trenutniIndeks][i]);
			//	min = Math.min(min, graf[trenutniIndeks][i]);
				if (min > (graf[trenutniIndeks][i] + h[i])) {
					min = (graf[trenutniIndeks][i] + h[i]);
					naslednjiIndeks = i;				
				}
			}			
		}	
		//	obstajaPot = false;
		//	System.out.println(prej + "      " + naslednjiIndeks);
			if (prej == naslednjiIndeks) {
			//	h[prej] = Integer.MAX_VALUE;
				break;
			}
			
			sb.append((naslednjiIndeks+1));
			sb.append(",");
			//System.out.println("Štartali smo iz: "+ (trenutniIndeks+1) + "    naj i: " + (naslednjiIndeks+1) + "  cena: " + min);
			h[trenutniIndeks] = min;
			prej = naslednjiIndeks;
			trenutniIndeks = naslednjiIndeks;
			
			min = Integer.MAX_VALUE;
			obiskani[trenutniIndeks] = true;
			
		}
	//	sb.append(finishV);
		pw.println((sb.toString()).substring(0, sb.length() -1));
		//System.out.println(Arrays.toString(h));
		
	//	System.out.println(Arrays.toString(h) + "    " + Arrays.toString(h_spremembe));
		
		if (Arrays.equals(h, h_spremembe)) {
			if (konecS.equals(sb.toString())) {
				konec = true;
			}		
		}
		 
		
		
		min = Integer.MAX_VALUE;
		trenutniIndeks = startV-1;
		naslednjiIndeks = 0;
		Arrays.fill(obiskani, false);
		obiskani[trenutniIndeks] = true;
		prej = 0;
		System.arraycopy(h, 0, h_spremembe, 0, max);
		konecS = sb.toString();
		}
		pw.close();
		long rez = System.currentTimeMillis() - start;
		System.out.println("Time in millis: " + rez);
	}
	
	
	
	
	public static class Povezava {
		public int V1;
		public int V2;
		
		public int cena;
		
		public Povezava(int v1, int v2, int c) {
			this.V1 = v1;
			this.V2 = v2;
			
			this.cena = c;
		}
		
		@Override 
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("V1: "+ this.V1 + "    V2: " + this.V2 + "   CENA: " + this.cena);
			return sb.toString();
		}
	}
	
	
}