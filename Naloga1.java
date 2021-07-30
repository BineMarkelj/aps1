import java.io.*;
import java.util.Scanner;
 
 public class Naloga1 {
	 public static void main(String[] args) throws IOException, FileNotFoundException {

		 //InputStream vhod = new FileInputStream(args[0]);
		 //InputStream izhod = new FileInputStream(args[1]);
		 
		 
		 //Scanner sc = new Scanner(new File(args[0]));
		 BufferedReader tok = new BufferedReader(new FileReader(args[0]));
		 PrintWriter pw = new PrintWriter(new FileWriter (args[1]));
		 
		 String vrstica;
		 int dolzinaPoti = 0;
		 int kapaciteta = 0;
		 int stPostaj = 0;
		 
		 vrstica = tok.readLine();
		 //System.out.println(vrstica);
		 
		 
		 //pridobimo 3 začetne vrednosti D, K, N
		 int[] vrednosti = new int[3];
		 int count = 0;
		 for (String podatki : vrstica.split(",")) {
			 vrednosti[count] = Integer.parseInt(podatki);
			 count++;
		 }
		 
		 dolzinaPoti = vrednosti[0];
		 kapaciteta = vrednosti[1];
		 stPostaj = vrednosti[2];
		 
		 //naredimo tabieli d, pa
		 int[] id = new int[stPostaj];
		 int[] d = new int[stPostaj];
		 int[] p = new int[stPostaj];
		 int[] optimizacija = new int[stPostaj];
		 
		 //preberemo N postaj in vstavljamo podatke v obe tabieli
		 for (int i = 0; i < stPostaj; i++) {
			vrstica = tok.readLine(); 
			int j = 0;
			for (String podatki1 : vrstica.split(":|,")) {
				vrednosti[j] = Integer.parseInt(podatki1);
				j++;
			}
			d[i] = vrednosti[1];
			p[i] = vrednosti[2];
		 }
		 
		 //TEST ČE DELA BRANJE DATOTEKE IN ZAPISOVANJE V TABELO 
		 
	//	 System.out.println(dolzinaPoti + " " + kapaciteta+ " " +stPostaj);
		 
		// for (int i = 0; i < d.length; i++) {
			// System.out.println(d[i] + "," + p[i]);
		// }
		 
		 
		 //ustvarimo novo vrsto z začentnimi podatki
		 Queue queue = new Queue();
		 Crpalka c = new Crpalka(0, 0, 0, "0");
		 queue.enqueue(c);
		 
		 int cenaMin = 0;
		 String naj = "";
		 
		 //dokler ni vrsta prazna se premiramo naprej in beremo front podatek 
		 while (!queue.empty()) {
			c = (Crpalka)queue.front();
			queue.dequeue();
			
		//	System.out.println(c.crpalkeDoSedaj);
		//	System.out.println(c.cenaDoSedaj);
			
			//če je možno priti iz te postaje do konca, izračunaj ali je to najboljša pot
			if (dolzinaPoti - c.potDoSedaj <= kapaciteta) {			
			//	System.out.println(c.cenaDoSedaj + " " + cenaMin);
			//	System.out.println(c.crpalkeDoSedaj + " " + naj);
				
				if((cenaMin>c.cenaDoSedaj) || cenaMin == 0) {
					naj = c.crpalkeDoSedaj;
					cenaMin = c.cenaDoSedaj;
				}
			}

 			
			//če so naslednje postaje jim dodaj v vrsto postaje v dosegu
			if (c.idCrpalke != stPostaj) {
				//dodajDoseg(queue, c, d, p, kapaciteta);
				
				
				
				int doSedaj = 0;
				for(int i = c.idCrpalke; i < d.length; i++) {
				if ((/*c.potDoSedaj +*/ d[i] + doSedaj)<= kapaciteta) {
					if ((c.cenaDoSedaj + (d[i]+doSedaj )*p[i]) < cenaMin || cenaMin == 0) {
						if (optimizacija[i] == 0 || (optimizacija[i] > (c.cenaDoSedaj + (d[i]+doSedaj )*p[i]))) {
							optimizacija[i] = (c.cenaDoSedaj + (d[i]+doSedaj )*p[i]);
						
						queue.enqueue(new Crpalka(i+1, c.potDoSedaj+d[i]+doSedaj, (c.cenaDoSedaj + (d[i]+doSedaj )*p[i]), c.crpalkeDoSedaj +"," + String.format("%d",i+1) ));
						}
					}	
				}
				doSedaj = doSedaj + d[i];
			} 
				
				doSedaj = 0;
				
				
				
				
				
				
			}	
			}
					pw.println(naj.substring(2));					
					pw.close();
		 }
	 }
	 
	 //METODE
	 /*
	 public static void dodajDoseg(Queue queue, Crpalka c, int[] razdalje, int[] cene, int maxGorivo) {
		 int doSedaj = 0;
			for(int i = c.idCrpalke; i < razdalje.length; i++) {
				if ((c.idCrpalke + razdalje[i] + doSedaj)<= maxGorivo) {
					queue.enqueue(new Crpalka(i+1, c.potDoSedaj+razdalje[i], (c.cenaDoSedaj + (c.potDoSedaj+razdalje[i] - c.potDoSedaj)*cene[i]), c.crpalkeDoSedaj +"," + String.format("%d",i+1) ));
				}
				doSedaj = doSedaj + razdalje[i];
			} 
		 }
		*/ 
		 
		 
		 ////////////////////////           VSE ZA VRSTO                ////////////////////////
	
	
	//implementacija vrste iz vaj
class QueueElement
{
	Object element;
	QueueElement next;

	QueueElement()
	{
		element = null;
		next = null;
	}
}

class Queue
{
	
	private QueueElement front;
	private QueueElement rear;
	
	public Queue()
	{
		makenull();
	}
	
	public void makenull()
	{
		front = null;
		rear = null;
	}
	
	public boolean empty()
	{
		return (front == null);
	}
	
	public Object front()
	{
		if (!empty())
			return front.element;
		else
			return null;
	}
	
	public void enqueue(Object obj)
	{
		QueueElement el = new QueueElement();
		el.element = obj;
		el.next = null;
		
		if (empty())
		{
			front = el;
		}
		else
		{
			rear.next = el;
		}
		
		rear = el;
	}
	
	public void dequeue()
	{
		if (!empty())
		{
			front = front.next;
			
			if (front == null)
				rear = null;
		}
	}
}

//razred črpalke, ki jih nalagam v vrsto
class Crpalka
{
	int idCrpalke;
	int potDoSedaj;
	int cenaDoSedaj;
	String crpalkeDoSedaj;
	
	Crpalka(int id, int pot, int cena, String crpalke)
	{
		this.idCrpalke = id;
		this.potDoSedaj = pot;
		this.cenaDoSedaj = cena;
		this.crpalkeDoSedaj = crpalke; //+ " -> [" + x + "," + y + "]"; // TO SPREMENI
	}
}

 