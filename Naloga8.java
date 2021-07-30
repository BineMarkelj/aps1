import java.util.*;
import java.io.*;
import java.lang.*;

public class Naloga8 {
	//public static boolean[] obiskani = new boolean[1];
	public static int countFINAL = 0;
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		long start = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		
		BufferedReader tok = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new FileWriter(args[1]));
		
		//za hranjenje števila nodov drevesa
		String vrstica;
		int nP = 0;
		int nT = 0;
		
		//preveremo st nodov Pja
		vrstica = tok.readLine();
		nP = Integer.parseInt(vrstica);
		int stOtrok = 0;
		
		//naredimo prazen arraylist, zapolnimo ga z elementi 1 - N, ki imajo samo id, brez imena in sinov
		ArrayList<DrevoVozlisce> drevoP = new ArrayList<DrevoVozlisce>();
		for (int i = 0; i < nP; i++) {
			drevoP.add(new DrevoVozlisce(i+1));
			//System.out.println((drevo.get(i)).toString());
			//System.out.println("34a245ab".hashCode());
		}
		//elementom dodamo še imena (char) in sezname sinov
		for (int i = 0; i < nP; i++) {
			//System.out.println("1");
			vrstica = tok.readLine();
			
			//razclenimo vhod
			String[] vrstica1 = new String[nP+1];
			//System.out.println("LEN: " + vrstica1.length);
			vrstica1 = razcleni(vrstica, nP);
			
			//dodamo nov element v arraylist, zgradimo objekt
			drevoP.get(i).dodajIme(vrstica1[1].charAt(0));
			stOtrok = vrstica1.length - 2;
			
			//char ch;
			for (int j = 2; j < vrstica1.length; j++) {
				//ch = vrstica1[j];
				//System.out.print("j: "+j + " i: "+i );
				if (vrstica1[j] == null) {
					break;
				}
				drevoP.get(i).dodajSina(drevoP.get((Integer.valueOf(vrstica1[j]))-1));
			}
			
			//System.out.println(drevo.get(i).id);
			//System.out.println(drevo.get(i).ime);
		}
		
		//PREVERJANJE BRANJA IN BUILDANJA DREVESA
	/*	for (int k = 0; k < nP; k++) {
			System.out.println("id: " +(drevoP.get(k)).id);
			System.out.println("ime: " +(drevoP.get(k)).ime);
			System.out.println("otroci: " + (drevoP.get(k)).otroci);
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(); */
////////////////////////////////////////////////////////////    BRANJE ŠE DRUGEGA DREVESA IN BUILDANJE //////////////////////////////////////////////////

//preveremo st nodov Pja
		vrstica = tok.readLine();
		nT = Integer.parseInt(vrstica);
		//int stOtrok = 0;
		
		//naredimo prazen arraylist, zapolnimo ga z elementi 1 - N, ki imajo samo id, brez imena in sinov
		ArrayList<DrevoVozlisce> drevoT = new ArrayList<DrevoVozlisce>();
		for (int i = 0; i < nT; i++) {
			drevoT.add(new DrevoVozlisce(i+1));
			//System.out.println((drevo.get(i)).toString());
			//System.out.println("34a245ab".hashCode());
		}
		//elementom dodamo še imena (char) in sezname sinov
		for (int i = 0; i < nT; i++) {
			//System.out.println("1");
			vrstica = tok.readLine();
			
			//razclenimo vhod
			String[] vrstica2 = new String[nT+1];
			//System.out.println("LEN: " + vrstica2.length);
			vrstica2 = razcleni(vrstica, nT);
			
			//dodamo nov element v arraylist, zgradimo objekt
			drevoT.get(i).dodajIme(vrstica2[1].charAt(0));
			//stOtrok = vrstica1.length - 2;
			
			//char ch;
			for (int j = 2; j < vrstica2.length; j++) {
				//System.out.println("1");
				//ch = vrstica1[j];
				//System.out.print("j: "+j + " i: "+i );
				if (vrstica2[j] == null) {
					break;
				}
				drevoT.get(i).dodajSina(drevoT.get((Integer.valueOf(vrstica2[j]))-1));
			}
			
			//System.out.println(drevo.get(i).id);
			//System.out.println(drevo.get(i).ime);
		}
		
		//PREVERJANJE BRANJA IN BUILDANJA DREVESA
	/*	for (int k = 0; k < nT; k++) {
			System.out.println("id: " +(drevoT.get(k)).id);
			System.out.println("ime: " +(drevoT.get(k)).ime);
			System.out.println("otroci: " + (drevoT.get(k)).otroci);
			System.out.println();
		}*/
		
		
////////////////////////         KONEC BRANJA PODATKOV IN BUILDANJA DREVESA //////////////////////////

		DrevoVozlisce pRoot = drevoP.get(0);
		DrevoVozlisce tRoot = drevoT.get(0);
		
		boolean[] obiskani/*Temp*/ = new boolean[nT];
		/*System.arraycopy(obiskani, 0, obiskaniTemp, 0, 1);
		obiskani = obiskaniTemp;*/
		Arrays.fill(obiskani, false);
		
		/*System.out.println(pRoot.id);
		System.out.println(pRoot.ime);
		System.out.println(tRoot.otroci);
		System.out.println(tRoot.id);
		System.out.println(tRoot.ime);
		System.out.println(tRoot.otroci);
		*/
		
		/*for (int i = 0; i < nT; i++) {
			drevoT.add(new DrevoVozlisce(0));
		}*/
		
		

		//System.out.println(pRoot.poisci());
		//POISCEMO VSA VOZLISCA KI SE SKLADAJO Z KORENOM VZORCNEGA VOZLISCA IN SI JIH SHRANIMO, DA JIH NATO RAZISCEMO
		DrevoVozlisce[] koreni = new DrevoVozlisce[nT];
		
		int j = 0;
		for (int i = 0; i < nT; i++) {
			if (drevoT.get(i).ime == pRoot.ime) {
				koreni[j] = (drevoT.get(i));
				j++;
			}
		}
		
	/*	
		for (int k = 0; k < nP; k++) {
			System.out.println("id: " +(drevoP.get(k)).id);
			System.out.println("ime: " +(drevoP.get(k)).ime);
			System.out.println("otroci: " + (drevoP.get(k)).otroci);
			System.out.println();
		}*/
		
		//ZA VSE MOZNE KORENE POISCEMO CE USTREZAJO VZORCU
		//System.out.println(Arrays.toString(koreni));
		for (int i = 0; i < koreni.length; i++) {
			if (koreni[i] == null) {
				break;
			}
		//	System.out.println("i: "+i);
			if (koreni[i].vsebujePoddrevo(pRoot, obiskani) != false) {
				//System.out.println("G: " + koreni[i]);
				countFINAL++;
			}
		}
		
		System.out.println(countFINAL);
		pw.println(countFINAL);
		pw.close();
		
		long rez = System.currentTimeMillis() - start;
		System.out.println("Time in millis: " + rez);
	}
	

	
	
	
	
	
	
	
	
	
	public static String[] razcleni(String vrstica, int dolzina) {
		String[] arr = new String[dolzina+1];
		int count = 0;
		
		for (String vhod : vrstica.split(",")) {
			arr[count] = vhod;
		//	System.out.print(arr[count] + " ");
			count++;
		}
	//	System.out.println();
		return arr;
	}
	
	
	
	
}


class DrevoVozlisce {
	public int id;
	public char ime;
	
	LinkedList<DrevoVozlisce> otroci;

	DrevoVozlisce(int id /*,char ime*/) {
		this.id = id;
		//this.ime = ime;
		
		this.otroci = new LinkedList<DrevoVozlisce>();
		//System.out.println(this.id);
		//System.out.println(this.ime);
	}
	
	public void dodajSina(DrevoVozlisce sin) {
		//System.out.println("oce: " + this.id + " sin: " + sin.id);
		this.otroci.add(sin);
		//System.out.println(this.otroci);
	}
	
	public void dodajIme(char ime) {
		this.ime = ime;
	}
	
	public void izpis(int id) {
		
	}
	
	
	
	public DrevoVozlisce poisci (DrevoVozlisce tRoot) {
		/*for (int ) {
			test
		}*/
		return this;
	}
	
	public boolean poisciB () {
		return true;
	}
	
	
	
	public boolean vsebujePoddrevo(DrevoVozlisce pRoot, boolean[] obiskani) {
		if (pRoot == null && this == null) {
			return false;
		}
		
		if (pRoot.ime != this.ime) {
			return false;
		}
		if (((pRoot.otroci).size() == 0)/* && ((this.otroci).size() == 0)*/) {
		//	System.out.println(this);
			return true;
		} else if (primerjajList(pRoot, this) == false) {
			//System.out.println("FLS");
			//break;
			return false;
		} 
			for (int i = 0; i < pRoot.otroci.size(); i++) {
				if (((this.otroci).get(i)).vsebujePoddrevo(pRoot.otroci.get(i), obiskani) == false) {
					return false;
				}
			}
		
		//System.out.println("K");
		return true;
	}
	
	public boolean primerjajList(DrevoVozlisce x, DrevoVozlisce y) {
		//System.out.println("P:" + x + "OTROCI P:" + x.otroci + "         T" + y + "OTROCI T:" + y.otroci);
		if ((x.otroci).size() != (y.otroci).size()) {
		//	System.out.println("b");
			return false;
		}
		for (int i = 0; i < (x.otroci).size(); i++) {
			//System.out.println("SIZE" + (x.otroci).size() + "   " + (y.otroci).size());
			if (((x.otroci).get(i).equals(null)) || ((y.otroci).get(i).equals(null)) || (((x.otroci).get(i)).ime != ((y.otroci).get(i).ime))){
		//		System.out.println("c" + ((x.otroci).get(i)) + " " + ((y.otroci).get(i)));
				
				return false;
			} else if ((x.otroci).size() != (y.otroci).size()) {
		//	System.out.println("b");
			return false;
		}
		}
		//System.out.println("M2");
		return true;
	}
	
	
	/*iskanje roota P v drevesu T    USELESS NOČE DELAT AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	public int poisci(char imeIskano, boolean[] obiskani) {
		//System.out.println(this.id + " " + this.ime + " obiskani:" +obiskani[this.id-1] + " imeIskano " + imeIskano);
		if (this.ime == imeIskano && (obiskani[this.id-1] != true)) {
			//System.out.println("LOOP: " +this.id);
			obiskani[this.id-1] = true;
			System.out.println("vrnjeni: " + this.id + "    " + this);
			System.out.println(this == null);
			return this.id;
		} else {
			for (DrevoVozlisce otrok : this.otroci) {
				if (otrok.poisci(imeIskano, obiskani) != -1) {
					DrevoVozlisce zdaj = otrok;
					
					if (zdaj != null && (obiskani[zdaj.id-1] != true)) {
					obiskani[zdaj.id-1] = true;
					return zdaj.id;
				}
				}
			//	DrevoVozlisce zdaj = otrok.poisci(imeIskano, obiskani);				
				
				
			}
		}
		return -1;
	}*/
	
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id: "+this.id + " ime: " + this.ime);
		return sb.toString();
	}
}

