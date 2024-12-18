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

	public static Pokemon create(String line) throws Exception {
		Pokemon result = new Pokemon();
		result.parse(line);
		return result;
	}

	public static Pokemon[] loadFromFile(String path) throws Exception {
		Pokemon[] result = new Pokemon[801];
		File file = new File(path);
		Scanner scanner = new Scanner(file);
		scanner.nextLine(); 
		int index = 0;
		while(scanner.hasNext()) {
			result[index] = create(scanner.nextLine());
			index++;
		}
		scanner.close();
		return result;
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
	public void display() {
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

	public void parse(String line) throws Exception {
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
}

class TP {
	private Instant startTime;
	private Instant endTime;
	private int comparisons;
	private int movements;

	TP() {
		this.startTime = Instant.now();
		this.comparisons = 0;
		this.movements = 0;
	}

	public void countComparison() {
		this.comparisons++;
	}

	public void countComparison(int count) {
		this.comparisons += count;
	}

	public void countMovement() {
		this.movements++;
	}

	public void countMovement(int count) {
		this.movements += count;
	}

	void stop() {
		this.endTime = Instant.now();
	}

	double getDuration() {
		return Duration.between(this.startTime, this.endTime).getNano() / 1000000000.0;
	}

	void printResult(String fileName) throws Exception {
		PrintWriter writer = new PrintWriter(new FileWriter(fileName));
		writer.printf("Matrícula: 863485\t");
		writer.printf("Tempo de execução: " + getDuration() + "\t");
		writer.printf("Comparações: " + comparisons + "\t");
		writer.printf("Movimentações: " + movements);
		writer.close();
	}

	void printSearchResult(String fileName) throws Exception {
		PrintWriter writer = new PrintWriter(new FileWriter(fileName));
		writer.printf("Matrícula: 863485\t");
		writer.printf("Tempo de execução: " + getDuration() + "\t");
		writer.printf("Comparações: " + comparisons);
		writer.close();
	}
}

class Node {
	public Pokemon pokemon;
	public Node left;
	public Node right;

	Node(Pokemon pokemon) {
		this.pokemon = pokemon;
		left = null;
		right = null;
	}
}

class Tree {
	public Node root;

	Tree() {
		root = null;
	}

	public void printInOrder() {
		printInOrder(root);
	}

	private void printInOrder(Node node) {
		if(node.left != null) printInOrder(node.left);
		System.out.print(node.pokemon.getName() + " ");
		if(node.right != null) printInOrder(node.right);
	}

	public void insert(Pokemon pokemon) {
		if(root == null) root = new Node(pokemon);
		else insert(pokemon, root);
	}

	private void insert(Pokemon pokemon, Node currentNode) {
		if(pokemon.getName().compareTo(currentNode.pokemon.getName()) < 0) {
			if(currentNode.left == null) currentNode.left = new Node(pokemon);
			else insert(pokemon, currentNode.left);
		} else if(pokemon.getName().compareTo(currentNode.pokemon.getName()) > 0) {
			if(currentNode.right == null) currentNode.right = new Node(pokemon);
			else insert(pokemon, currentNode.right);
		}
	}

	public void search(String pokemonName, TP tp) {
		System.out.println(pokemonName);
		System.out.print("=>raiz ");
		if(root == null) {
			System.out.println("NAO");
		} else {
			if(pokemonName.compareTo(root.pokemon.getName()) == 0) {
				System.out.println("SIM");
				tp.countComparison();
			} else if(pokemonName.compareTo(root.pokemon.getName()) < 0) {
				System.out.print("esq ");
				tp.countComparison();
				search(pokemonName, root.left, tp);
			} else {
				System.out.print("dir ");
				tp.countComparison();
				search(pokemonName, root.right, tp);
			}
		}
	}

	private void search(String pokemonName, Node currentNode, TP tp) {
		if(pokemonName.compareTo(currentNode.pokemon.getName()) == 0) {
			System.out.println("SIM");
			tp.countComparison();
		} else if(pokemonName.compareTo(currentNode.pokemon.getName()) < 0) {
			tp.countComparison();
			if(currentNode.left == null) System.out.println("esq NAO");
			else {
				System.out.print("esq ");
				search(pokemonName, currentNode.left, tp);
			}
		} else {
			if(currentNode.right == null) System.out.println("dir NAO");
			else {
				System.out.print("dir ");
				search(pokemonName, currentNode.right, tp);
			}
		}
	}
}

public class TP04Q01 {

	static void swapPokemons(Pokemon[] pokemons, int indexA, int indexB) {
		Pokemon temp = pokemons[indexA];
		pokemons[indexA] = pokemons[indexB];
		pokemons[indexB] = temp;
	}

	public static void main(String[] args) {
		try {
			Pokemon[] pokemons = Pokemon.loadFromFile("/tmp/pokemon.csv");
			Scanner scanner = new Scanner(System.in);

			int pokemonIndex = 0;
			Tree pokemonTree = new Tree();
			String input = scanner.nextLine();
			while(!input.equals("FIM")) {
				pokemonIndex = Integer.parseInt(input);
				pokemonTree.insert(pokemons[pokemonIndex - 1]);
				input = scanner.nextLine();
			}

			TP tp = new TP();
			input = scanner.nextLine();
			while(!input.equals("FIM")) {
				pokemonTree.search(input, tp);
				input = scanner.nextLine();
			}
			tp.stop();
			tp.printSearchResult("863485_arvoreBinaria.txt");

		} catch(FileNotFoundException e) {
			System.out.println(e);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
