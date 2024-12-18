import java.time.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Pokemon{
	private int id;
	private int generation;
	private String name;
	private String description;
	private ArrayList<String> types;
	private ArrayList<String> abilities;
	private double weight;
	private double height;
	private int captureRate;
	private boolean legendary;
	private Date captureDate;

	Pokemon(int id, int generation, String name, String description, ArrayList<String>  types, ArrayList<String>  abilities, double weight, double height, int captureRate, boolean legendary, Date captureDate){
		this.id = id;
		this.generation = generation;
		this.name = name;
		this.description = description;
		this.types = types;
		this.abilities = abilities;
		this.weight = weight;
		this.height = height;
		this.captureRate = captureRate;
		this.legendary = legendary;
		this.captureDate = captureDate;
	}

	Pokemon(){
		this.id = 0;
		this.generation = 0;
		this.name = null;
		this.description = null;
		this.types = null;
		this.abilities = null;
		this.weight = 0.0;
		this.height = 0.0;
		this.captureRate = 0;
		this.legendary = false;
		this.captureDate = new Date();
	}

	public Pokemon clon(){
		return new Pokemon(id, generation, name, description, types, abilities, weight, height, captureRate, legendary, captureDate);
	}

	public static Pokemon make(String s) throws Exception {
		Pokemon result = new Pokemon();
		result.read(s);
		return result;
	}

	public static Pokemon[] readFile(String path) throws Exception {
		Pokemon[] result = new Pokemon[801];
		File fil = new File(path);
		Scanner csv = new Scanner(fil);
		String line = csv.nextLine();
		int i = 0;
		while(csv.hasNext()){
			line = csv.nextLine();
			result[i] = make(line);
			i++;
		}
		csv.close();
		return result;
	}

	// Getters
	public int getId(){
		return id;
	}
	public int getGeneration(){
		return generation;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public ArrayList<String> getTypes(){
		return types;
	}
	public ArrayList<String> getAbilities(){
		return abilities;
	}
	public double getWeight(){
		return weight;
	}
	public double getHeight(){
		return height;
	}
	public int getCaptureRate(){
		return captureRate;
	}
	public boolean isLegendary(){
		return legendary;
	}
	public Date getCaptureDate(){
		return captureDate;
	}

	// Setters 
	public void setId (int id){
		this.id = id;
	}
	public void setGeneration (int generation){
		this.generation = generation;
	}
	public void setName (String name){
		this.name = name;
	}
	public void setDescription (String description){
		this.description = description;
	}
	public void setTypes (ArrayList<String> types){
		this.types = types;
	}
	public void setAbilities (ArrayList<String> abilities){
		this.abilities = abilities;
	}
	public void setWeight (double weight){
		this.weight = weight;
	}
	public void setHeight (double height){
		this.height = height;
	}
	public void setCaptureRate (int captureRate){
		this.captureRate = captureRate;
	}
	public void setLegendary (boolean legendary){
		this.legendary = legendary;
	}
	public void setCaptureDate (Date captureDate){
		this.captureDate = captureDate;
	}

	// Print
	public void print(){
		System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

		int reps = types.size();
		for(int i=0; i<reps-1; i++){
			System.out.print("'" + types.get(i) + "', ");
		}
		System.out.print("'" + types.get(reps-1) + "'] - [");

		reps = abilities.size();
		for(int i=0; i<reps-1; i++){
			System.out.print(abilities.get(i) + ", ");
		}
		System.out.print(abilities.get(reps-1) + "] - ");

		System.out.print(weight + "kg - " + height + "m - " + captureRate + "% - ");

		if(isLegendary()) System.out.print("true ");
		else System.out.print("false ");

		System.out.print("- " + generation + " gen] - ");

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(formater.format(captureDate));
	}
    // Read
	public void read(String s) throws Exception {
		String format = "";
		boolean control = true;
		for(int i=0; i<s.length(); i++){
			char x = s.charAt(i);
			if(x == '"') control = !control;
			else if(x == ',' && control) format += ';';
			else if(x!='[' && x!=']') format += x;
		}
		String[] aux = format.split(";");

		setId(Integer.parseInt(aux[0]));
		setGeneration(Integer.parseInt(aux[1]));
		setName(aux[2]);
		setDescription(aux[3]);

		String type1 = aux[4];
		String type2 = aux[5];
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add(type1);
		if(!type2.equals("")) tipos.add(type2);
		setTypes(tipos);
	

		boolean hasComma = false;
		ArrayList<String> abis = new ArrayList<String>();
		for(int i=0; i<aux[6].length() && !hasComma; i++)
			if(aux[6].charAt(i)==',') hasComma = true;
		if(!hasComma)
			abis.add(aux[6]);
		else{
			String[] abs = aux[6].split(", ");
			for(int i=0; i<abs.length; i++) abis.add(abs[i]);
		}
		setAbilities(abis);

		if(!aux[7].equals("")) setWeight(Double.parseDouble(aux[7]));
		if(!aux[8].equals("")) setHeight(Double.parseDouble(aux[8]));
		if(!aux[9].equals("")) setCaptureRate(Integer.parseInt(aux[9]));
		
		if(aux[10].equals("1")) setLegendary(true);

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		setCaptureDate(formater.parse(aux[11]));
	}

}

class TP{

	private Instant start;
	private Instant end;
	private int comparisons;
	private int movements;

	TP(){
		this.start = Instant.now();
		this.comparisons = 0;
		this.movements = 0;
	}

	public void comp(){
		this.comparisons++;
	}

	public void comp(int x){
		this.comparisons += x;
	}

	public void move(){
		this.movements++;
	}

	public void move(int x){
		this.movements += x;
	}

	void end(){
		this.end = Instant.now();
	}

	double diff(){
		return Duration.between(this.start, this.end).getNano() / 1000000000.0;
	}

	void print(String fileName) throws Exception{
		PrintWriter write = new PrintWriter(new FileWriter(fileName));
		write.printf("Matrícula: 863485\t");
		write.printf("Tempo de execução: " + diff() + "\t");
		write.printf("Comparações: " + comparisons + "\t");
		write.printf("Movimentações: " + movements);
		write.close();
	}

	void printSearch(String fileName) throws Exception{
		PrintWriter write = new PrintWriter(new FileWriter(fileName));
		write.printf("Matrícula: 863485\t");
		write.printf("Tempo de execução: " + diff() + "\t");
		write.printf("Comparações: " + comparisons);
		write.close();
	}
}

class noSec{
	public String data;
	public noSec esq;
	public noSec dir;

	noSec(String x){
		data = x;
		esq = null;
		dir = null;
	}
}

class treeSec{
	public noSec root;
	
	treeSec(){
		root = null;
	}

	public void add(String x, TP tp){
		root = add(x, root, tp);
	}

	public noSec add(String x, noSec i, TP tp){
		if(i==null){
			tp.comp();
			i = new noSec(x);
		}
		else if(x.compareTo(i.data) < 0){
			tp.comp();
			i.esq = add(x, i.esq, tp);
		}
		else{
			i.dir = add(x, i.dir, tp);
		}
		return i;
	}

	public boolean search(String x, TP tp){
		return search(x, root, tp);
	}

	public boolean search(String x, noSec i, TP tp){
		while(i!=null){
			if(i.data.equals(x)){
				tp.comp();
				return true;
			}
			else if(x.compareTo(i.data) > 0){
				tp.comp();
				System.out.print("dir ");
				i = i.dir;
			}
			else{
				System.out.print("esq ");
				i = i.esq;
			}
		}
		return false;
	}
}

class Node{
	public int data;
	public treeSec sRoot;
	public Node esq;
	public Node dir;

	public Node(int x){
		sRoot = new treeSec();
		data = x;
		esq = null;
		dir = null;
	}
}

class Tree{
	public Node root;

	public Tree(){
		root = null;
	}

	public boolean walk(String str, TP tp){
		System.out.println("=> " + str);
		System.out.print("raiz ");
		boolean[] found = new boolean[1];
		found[0] = false;
		walk(root, str, found, tp);
		return found[0];
	}

	public void walk(Node i, String str, boolean[] found, TP tp){
		if(i!=null){
			found[0] = i.sRoot.search(str, tp);
			if(!found[0]){
				System.out.print(" ESQ ");
				walk(i.esq, str, found, tp);
			}
			if(!found[0]){
				System.out.print(" DIR ");
				walk(i.dir, str, found, tp);
			}
		}
	}

	public void addStart(int x){
		root = addStart(x, root);
	}

	 public Node addStart(int x, Node i){
		 if(i==null){
			 i = new Node(x);
		 }
		 else if(x < i.data){
			 i.esq = addStart(x, i.esq);
		 }
		 else if(x > i.data){
			 i.dir = addStart(x, i.dir);
		 }
		 return i;
	 }

	 public void add(int x, String name, TP tp){
		 root = add(x, root, name, tp);
	 }

	 public Node add(int x, Node i, String name, TP tp){
		 if(i==null){
			 tp.comp();
			 i = new Node(x);
			 i.sRoot.add(name, tp);
		 }
		 else if(x < i.data){
			 tp.comp();
			 i.esq = add(x, i.esq, name, tp);
		 }
		 else if(x > i.data){
			 tp.comp();
			 i.dir = add(x, i.dir, name, tp);
		 }
		 else i.sRoot.add(name, tp);
		 return i;
	 }

}

public class Main2{

	static void swapPoke(Pokemon[] arr, int a, int b){
		Pokemon tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public static void main(String[] args){
		try{

			Pokemon[] pokes = Pokemon.readFile("/tmp/pokemon.csv");
			Scanner sc = new Scanner(System.in);

			int x = 0;
			Tree t = new Tree();
			t.addStart(7);
			t.addStart(3);
			t.addStart(11);
			t.addStart(1);
			t.addStart(5);
			t.addStart(9);
			t.addStart(13);
			t.addStart(0);
			t.addStart(2);
			t.addStart(4);
			t.addStart(6);
			t.addStart(8);
			t.addStart(10);
			t.addStart(12);
			t.addStart(14);

			TP tp = new TP();

			String input = sc.nextLine();
			while(!input.equals("FIM")){
				x = Integer.parseInt(input);
				t.add(pokes[x-1].getCaptureRate()%15, pokes[x-1].getName(), tp);
				input = sc.nextLine();
			}
			
			input = sc.nextLine();
			while(!input.equals("FIM")){
				if(t.walk(input, tp)) System.out.println(" SIM");
				else System.out.println(" NAO");
				input = sc.nextLine();
			}
			tp.end();
			tp.printSearch("863485_arvoreArvore.txt");

		} catch(FileNotFoundException e){
			System.out.println(e);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}