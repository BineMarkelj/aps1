import java.io.*;
import java.util.Scanner;

public class Naloga3 {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		BufferedReader tok = new BufferedReader(new FileReader(args[0]));
		
		//OutputStream izhod = new FileOutputStream(args[1]);
		PrintWriter pw = new PrintWriter(args[1] , "UTF-8");
		
		String vrstica;
		int N;
		int V;
		int K;
		int P;
		
		vrstica = tok.readLine();
		int[] vrednosti = new int[4];
		int count = 0;
		for (String podatki : vrstica.split(",")) {
			vrednosti[count] = Integer.parseInt(podatki);
			count++;
		}
		
		N = vrednosti[0];
		V = vrednosti[1];
		K = vrednosti[2];
		P = vrednosti[3];
		
	//	LinkedList p = new LinkedList();
		
		LinkedList[] seznami = new LinkedList[N];
		
		for (int i = 0; i < seznami.length; i++) {
			vrstica = tok.readLine();
		//	System.out.println(vrstica);
		//	count = 0;
			LinkedList list = new LinkedList();
			seznami[i] = list;
			if (vrstica.equals("")) {
					//System.out.println("BREAK");
					//seznami[i].addLast(null);
					continue;
				}
			
			for (String podatki : vrstica.split(",")) {
				//System.out.print(podatki);
				 
				seznami[i].addLast(Integer.parseInt(podatki));					
				

			//	count++
			}
			//System.out.println();
		}
		
		// TEST PREBRANIH PODATKOV
	//	System.out.println(N + " " + V + " " + K + " " + P + " ");
	//	System.out.println();
	//	for (int i = 0; i < seznami.length; i++) {
	//		seznami[i].write();
	//	}
	//	System.out.println();
	//	System.out.println();
	//	System.out.println();
		
		
		int indexVrsticeKjerJemljemo = V;
		int indexVrsticeKamorDajemo;
		int element;
		int dolzinaSeznama;
		
		
		for (int i = 0; i < K; i++) {
			
		element = seznami[indexVrsticeKjerJemljemo].readLast();
		dolzinaSeznama = seznami[indexVrsticeKjerJemljemo].length();
		seznami[indexVrsticeKjerJemljemo].deleteNth(dolzinaSeznama-1);
	
		indexVrsticeKamorDajemo = vrniIndeks(indexVrsticeKjerJemljemo, element, N);
		seznami[indexVrsticeKamorDajemo].addFirst(element);
		
		indexVrsticeKjerJemljemo = vrniIndeks(indexVrsticeKamorDajemo, P, N);	
		
	//	for (int j = 0; j < seznami.length; j++) {
	//		seznami[j].write();
	//	}
	}
	
	
	//for (int i = 0; i < seznami.length; i++) {
		//	seznami[i].write();
		//}
		
		
		
	String izpis = "";
	String a = "";
	int b = 0;
	
	int obhod = seznami[0].length();
	
	for (int j = 0; j < obhod; j++) {
	for (int i = 0; i < seznami.length; i++) {
		
			b = seznami[i].readFirst();
			seznami[i].deleteNth(0);
			a = dekoder(b);
		
			izpis = izpis + a;
		
	}}
	
		//System.out.println(izpis);
		pw.println(izpis);
		pw.close();
	}
	
	public static int vrniIndeks(int x, int y, int N) {
		int indeks = ((((x-y) % N) + N) % N);
		return indeks;
	}
	
	public static String dekoder(int b) {
		String vrni = "";
		int stevilo = b;
		
		switch (stevilo) {
			case 0: vrni = "A";
			break;
			
			case 1: vrni = "B";
			break;
			
			case 2: vrni = "C";
			break;
			
			case 3: vrni = "Č";
			break;
			
			case 4: vrni = "D";
			break;
			
			case 5: vrni = "E";
			break;
			
			case 6: vrni = "F";
			break;
			
			case 7: vrni = "G";
			break;
			
			case 8: vrni = "H";
			break;
			
			case 9: vrni = "I";
			break;
			
			case 10: vrni = "J";
			break;
			
			case 11: vrni = "K";
			break;
			
			case 12: vrni = "L";
			break;
			
			case 13: vrni = "M";
			break;
			
			case 14: vrni = "N";
			break;
			
			case 15: vrni = "O";
			break;
			
			case 16: vrni = "P";
			break;
			
			case 17: vrni = "R";
			break;
			
			case 18: vrni = "S";
			break;
			
			case 19: vrni = "Š";
			break;
			
			case 20: vrni = "T";
			break;
			
			case 21: vrni = "U";
			break;
			
			case 22: vrni = "V";
			break;
			
			case 23: vrni = "Z";
			break;
			
			case 24: vrni = "Ž";
			break;
			
			case 25: vrni = "a";
			break;
			
			case 26: vrni = "b";
			break;
			
			case 27: vrni = "c";
			break;
			
			case 28: vrni = "č";
			break;
			
			case 29: vrni = "d";
			break;
			
			case 30: vrni = "e";
			break;
			
			case 31: vrni = "f";
			break;
			
			case 32: vrni = "g";
			break;
			
			case 33: vrni = "h";
			break;
			
			case 34: vrni = "i";
			break;
			
			case 35: vrni = "j";
			break;
			
			case 36: vrni = "k";
			break;
			
			case 37: vrni = "l";
			break;
			
			case 38: vrni = "m";
			break;
			
			case 39: vrni = "n";
			break;
			
			case 40: vrni = "o";
			break;
			
			case 41: vrni = "p";
			break;
			
			case 42: vrni = "r";
			break;
			
			case 43: vrni = "s";
			break;
			
			case 44: vrni = "š";
			break;
			
			case 45: vrni = "t";
			break;
			
			case 46: vrni = "u";
			break;
			
			case 47: vrni = "v";
			break;
			
			case 48: vrni = "z";
			break;
			
			case 49: vrni = "ž";
			break;
			
			case 50: vrni = " ";
			break;
			
			case 51: vrni = "";
		}
		return vrni;
	}
}





class LinkedListElement 
{
	Object element;
	LinkedListElement next;
	
	LinkedListElement(Object obj)
	{
		element = obj;
		next = null;
	}
	
	LinkedListElement(Object obj, LinkedListElement nxt)
	{
		element = obj;
		next = nxt;
	}
}



class LinkedList 
{
	private LinkedListElement first;
	private LinkedListElement last;
	
	public LinkedList()
	{
		makenull();
	}
	
	//Funkcija makenull inicializira seznam
	public void makenull()
	{
		//drzimo se implementacije iz ucbenika:
		//po dogovoru je na zacetku glava seznama (header)
		first = new LinkedListElement(null, null);
		last = null;
	}
	
	
	
	//Funkcija addFirst doda nov element na prvo mesto v seznamu (takoj za glavo seznama)
	public void addFirst(Object obj)
	{
		//najprej naredimo nov element
		LinkedListElement newEl = new LinkedListElement(obj);
		
		//novi element postavimo za glavo seznama
		newEl.next = first.next;
		first.next = newEl;
		
		if (last == null)//preverimo, ali je to edini element v seznamu
			last = first;
		else if (last == first)//preverimo, ali je seznam vseboval en sam element
			last = newEl;
	}
	
	//Funkcija addLast doda nov element na konec seznama
	public void addLast(Object obj)
	{
		//najprej naredimo nov element
		LinkedListElement newEl = new LinkedListElement(obj, null);
		
		//ali je seznam prazen?
		// po dogovoru velja: ce je seznam prazen, potem kazalec "last" ne kaze nikamor
		if (last == null)
		{
			//ce seznam vsebuje samo en element, kazalca "first" in "last" kazeta na glavo seznama
			first.next = newEl;
			last = first;
		}
		else
		{
			last.next.next = newEl;
			last = last.next;
		}
	}
	
	//Funkcija length() vrne dolzino seznama (pri tem ne uposteva glave seznama)
	public int length()
	{
		int counter;
		LinkedListElement el;
		
		counter = 0;
		
		//zacnemo pri elementu za glavo seznama
		el = first.next;
		while (el != null)
		{
			counter++;
			el = el.next;
		}
		
		return counter;
	}
	
	
	//Funkcija deleteNth izbrise element na n-tem mestu v seznamu
	//(prvi element seznama, ki se nahaja takoj za glavo seznama, je na indeksu 0)
	public boolean deleteNth(int n)
	{
		LinkedListElement el, prev_el;
		
		//zacnemo pri glavi seznama
		prev_el = null;
		el = first;
		
		//premaknemo se n-krat
		for (int i = 0; i < n; i++)
		{
			prev_el = el;
			el = el.next;
			if (el == null)
				return false;
		}
		
		if (el.next != null)
		{
			//preden izlocimo element preverimo, ali je potrebno popraviti kazalec "last"
			if (last == el.next) //ce brisemo predzadnji element
				last = el;
			else if (last == el) //ce brišemo zadnji element
				last = prev_el;
				
			el.next = el.next.next;
			
			return true;
		}
		else
			return false;
	}
	
	public void write()
	{
		LinkedListElement el;
		
		//zacnemo pri elementu za glavo seznama
		el = first.next;
		while (el != null)
		{
			System.out.print(el.element + ", ");
			el = el.next;
		}
		
		System.out.println();
		
		/*
		//za kontrolo lahko izpisemo tudi vrednosti prvega in zadnjega elementa
		if (first.next != null)
			System.out.println("Prvi element: " + first.next.element);
		else
			System.out.println("Ni prvega elementa");
		
		if (last != null)
			System.out.println("Zadnji element: " + last.next.element);
		else
			System.out.println("Ni zadnjega elementa");
		*/
	}
	
	public int readLast() {
		return (int)((last.next).element);
	}
	
	public int readFirst() {
		if (first.next == null) {
			return 51;
		} else {
			//System.out.println((first.next).element);
		return (int)((first.next).element);
		}
		
	}
}