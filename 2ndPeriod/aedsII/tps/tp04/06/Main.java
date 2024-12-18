import java.time.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Pokemon {
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

	Pokemon(int id, int generation, String name, String description, ArrayList<String> types, ArrayList<String> abilities, double weight, double height, int captureRate, boolean legendary, Date captureDate) {
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

	Pokemon() {
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

	public Pokemon clonePokemon() {
		return new Pokemon(id, generation, name, description, types, abilities, weight, height, captureRate, legendary, captureDate);
	}

	// Getters
	public int getId() {
		return id;
	}

	public int getGeneration() {
		return generation;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public ArrayList<String> getAbilities() {
		return abilities;
	}

	public double getWeight() {
		return weight;
	}

	public double getHeight() {
		return height;
	}

	public int getCaptureRate() {
		return captureRate;
	}

	public boolean isLegendary() {
		return legendary;
	}

	public Date getCaptureDate() {
		return captureDate;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}

	public void setAbilities(ArrayList<String> abilities) {
		this.abilities = abilities;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setCaptureRate(int captureRate) {
		this.captureRate = captureRate;
	}

	public void setLegendary(boolean legendary) {
		this.legendary = legendary;
	}

	public void setCaptureDate(Date captureDate) {
		this.captureDate = captureDate;
	}

	// Print and Read
	public void print() {
		System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

		int repetitions = types.size();
		for(int i = 0; i < repetitions - 1; i++) {
			System.out.print("'" + types.get(i) + "', ");
		}
		System.out.print("'" + types.get(repetitions - 1) + "'] - [");

		repetitions = abilities.size();
		for(int i = 0; i < repetitions - 1; i++) {
			System.out.print(abilities.get(i) + ", ");
		}
		System.out.print(abilities.get(repetitions - 1) + "] - ");

		System.out.print(weight + "kg - " + height + "m - " + captureRate + "% - ");

		if(isLegendary()) System.out.print("true ");
		else System.out.print("false ");

		System.out.print("- " + generation + " gen] - ");

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(formatter.format(captureDate));
	}

	public void read(String line) throws Exception {
		String formattedLine = "";
		boolean insideQuotes = true;
		for(int i = 0; i < line.length(); i++) {
			char charAt = line.charAt(i);
			if(charAt == '"') insideQuotes = !insideQuotes;
			else if(charAt == ',' && insideQuotes) formattedLine += ';';
			else if(charAt != '[' && charAt != ']') formattedLine += charAt;
		}
		String[] parts = formattedLine.split(";");

		setId(Integer.parseInt(parts[0]));
		setGeneration(Integer.parseInt(parts[1]));
		setName(parts[2]);
		setDescription(parts[3]);

		String type1 = parts[4];
		String type2 = parts[5];
		ArrayList<String> typesList = new ArrayList<String>();
		typesList.add(type1);
		if(!type2.equals("")) typesList.add(type2);
		setTypes(typesList);

		boolean commaPresent = false;
		ArrayList<String> abilitiesList = new ArrayList<String>();
		for(int i = 0; i < parts[6].length() && !commaPresent; i++) {
			if(parts[6].charAt(i) == ',') commaPresent = true;
		}
		if(!commaPresent)
			abilitiesList.add(parts[6]);
		else {
			String[] abilities = parts[6].split(", ");
			for(int i = 0; i < abilities.length; i++) abilitiesList.add(abilities[i]);
		}
		setAbilities(abilitiesList);

		if(!parts[7].equals("")) setWeight(Double.parseDouble(parts[7]));
		if(!parts[8].equals("")) setHeight(Double.parseDouble(parts[8]));
		if(!parts[9].equals("")) setCaptureRate(Integer.parseInt(parts[9]));

		if(parts[10].equals("1")) setLegendary(true);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		setCaptureDate(formatter.parse(parts[11]));
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

    public static Pokemon make(String s) throws Exception {
		Pokemon result = new Pokemon();
		result.read(s);
		return result;
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

	public void compare(){
		this.comparisons++;
	}

	public void compare(int x){
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

class Table{
	private Pokemon[] arr;
	private int size;

	Table(){
		arr = new Pokemon[30];
		size = 21;
	}

	public int hash(Pokemon p){
		String name = p.getName();
		int reps = name.length();
		int result = 0;
		for(int i=0; i<reps; i++){
			result += (int)name.charAt(i);
		}
		return result % size;
	}

	public int reHash(Pokemon p){
		String name = p.getName();
		int reps = name.length();
		int result = 0;
		for(int i=0; i<reps; i++){
			result += (int)name.charAt(i);
		}
		return (result+1) % size;
	}

	public int hash(String p){
		String name = p;
		int reps = name.length();
		int result = 0;
		for(int i=0; i<reps; i++){
			result += (int)name.charAt(i);
		}
		return result % size;
	}

	public int reHash(String p){
		String name = p;
		int reps = name.length();
		int result = 0;
		for(int i=0; i<reps; i++){
			result += (int)name.charAt(i);
		}
		return (result+1) % size;
	}

	public void insert(Pokemon p, TP tp){
		int pos = hash(p);
		tp.compare();
		if(arr[pos] == null) arr[pos] = p;
		else{
			pos = reHash(p);
			tp.compare();
			if(arr[pos] == null) arr[pos] = p;
		}
	}

	public void search(String p, TP tp){
		int pos = hash(p);
		tp.compare();
		if(arr[pos]!=null && p.equals(arr[pos].getName())) System.out.println("=> " + p + ": (Posicao: " + pos + ") SIM");
		else{
			pos = reHash(p);
			tp.compare();
			if(arr[pos]!=null && p.equals(arr[pos].getName())) System.out.println("=> " + p + ": (Posicao: " + pos + ") SIM");
			else System.out.println("=> " + p + ": NAO");
		}
	}

}

public class Main{

	static void swapPokemon(Pokemon[] arr, int a, int b){
		Pokemon tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public static void main(String[] args){
		try{

			Pokemon[] pokemons = Pokemon.readFile("/tmp/pokemon.csv");
			Scanner scan = new Scanner(System.in);

			int x = 0;

			TP tp = new TP();

			Table table = new Table();

			String input = scan.nextLine();
			while(!input.equals("FIM")){
				x = Integer.parseInt(input);
				table.insert(pokemons[x-1], tp);
				input = scan.nextLine();
			}

			
			input = scan.nextLine();
			while(!input.equals("FIM")){
				table.search(input, tp);
				input = scan.nextLine();
			}
			tp.end();
			tp.printSearch("863485_hashRehash.txt");
            scan.close();
		} catch(FileNotFoundException e){
			System.out.println(e);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
