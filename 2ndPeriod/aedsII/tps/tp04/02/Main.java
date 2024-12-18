import java.time.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Pokemon {

    private int pokemonId;
    private int generation;
    private String pokemonName;
    private String pokemonDescription;
    private ArrayList<String> pokemonTypes;
    private ArrayList<String> pokemonAbilities;
    private double pokemonWeight;
    private double pokemonHeight;
    private int captureRate;
    private boolean isLegendary;
    private Date captureDate;

    Pokemon(int pokemonId, int generation, String pokemonName, String pokemonDescription, ArrayList<String> pokemonTypes, ArrayList<String> pokemonAbilities, double pokemonWeight, double pokemonHeight, int captureRate, boolean isLegendary, Date captureDate) {
        this.pokemonId = pokemonId;
        this.generation = generation;
        this.pokemonName = pokemonName;
        this.pokemonDescription = pokemonDescription;
        this.pokemonTypes = pokemonTypes;
        this.pokemonAbilities = pokemonAbilities;
        this.pokemonWeight = pokemonWeight;
        this.pokemonHeight = pokemonHeight;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
    }

    Pokemon() {
        this.pokemonId = 0;
        this.generation = 0;
        this.pokemonName = null;
        this.pokemonDescription = null;
        this.pokemonTypes = null;
        this.pokemonAbilities = null;
        this.pokemonWeight = 0.0;
        this.pokemonHeight = 0.0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate = new Date();
    }

    public Pokemon clonePokemon() {
        return new Pokemon(pokemonId, generation, pokemonName, pokemonDescription, pokemonTypes, pokemonAbilities, pokemonWeight, pokemonHeight, captureRate, isLegendary, captureDate);
    }

    public static Pokemon createPokemon(String data) throws Exception {
        Pokemon newPokemon = new Pokemon();
        newPokemon.parseData(data);
        return newPokemon;
    }

    public static Pokemon[] loadPokemonData(String filePath) throws Exception {
        Pokemon[] pokemonArray = new Pokemon[801];
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        int index = 0;
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            pokemonArray[index] = createPokemon(line);
            index++;
        }
        scanner.close();
        return pokemonArray;
    }

    // Getters
    public int getPokemonId() {
        return pokemonId;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public String getPokemonDescription() {
        return pokemonDescription;
    }

    public ArrayList<String> getPokemonTypes() {
        return pokemonTypes;
    }

    public ArrayList<String> getPokemonAbilities() {
        return pokemonAbilities;
    }

    public double getPokemonWeight() {
        return pokemonWeight;
    }

    public double getPokemonHeight() {
        return pokemonHeight;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    // Setters
    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public void setPokemonDescription(String pokemonDescription) {
        this.pokemonDescription = pokemonDescription;
    }

    public void setPokemonTypes(ArrayList<String> pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
    }

    public void setPokemonAbilities(ArrayList<String> pokemonAbilities) {
        this.pokemonAbilities = pokemonAbilities;
    }

    public void setPokemonWeight(double pokemonWeight) {
        this.pokemonWeight = pokemonWeight;
    }

    public void setPokemonHeight(double pokemonHeight) {
        this.pokemonHeight = pokemonHeight;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public void setLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    // Print and Read methods
    public void displayPokemon() {
        System.out.print("[#" + pokemonId + " -> " + pokemonName + ": " + pokemonDescription + " - [");

        int typesCount = pokemonTypes.size();
        for (int i = 0; i < typesCount - 1; i++) {
            System.out.print("'" + pokemonTypes.get(i) + "', ");
        }
        System.out.print("'" + pokemonTypes.get(typesCount - 1) + "'] - [");

        int abilitiesCount = pokemonAbilities.size();
        for (int i = 0; i < abilitiesCount - 1; i++) {
            System.out.print(pokemonAbilities.get(i) + ", ");
        }
        System.out.print(pokemonAbilities.get(abilitiesCount - 1) + "] - ");

        System.out.print(pokemonWeight + "kg - " + pokemonHeight + "m - " + captureRate + "% - ");

        if (isLegendary) System.out.print("true ");
        else System.out.print("false ");

        System.out.print("- " + generation + " gen] - ");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormatter.format(captureDate));
    }

    public void parseData(String data) throws Exception {
        String formattedData = "";
        boolean isInsideQuotes = true;
        for (int i = 0; i < data.length(); i++) {
            char character = data.charAt(i);
            if (character == '"') isInsideQuotes = !isInsideQuotes;
            else if (character == ',' && isInsideQuotes) formattedData += ';';
            else if (character != '[' && character != ']') formattedData += character;
        }
        String[] parsedData = formattedData.split(";");

        setPokemonId(Integer.parseInt(parsedData[0]));
        setGeneration(Integer.parseInt(parsedData[1]));
        setPokemonName(parsedData[2]);
        setPokemonDescription(parsedData[3]);

        String type1 = parsedData[4];
        String type2 = parsedData[5];
        ArrayList<String> types = new ArrayList<>();
        types.add(type1);
        if (!type2.equals("")) types.add(type2);
        setPokemonTypes(types);

        boolean hasComma = false;
        ArrayList<String> abilities = new ArrayList<>();
        for (int i = 0; i < parsedData[6].length() && !hasComma; i++)
            if (parsedData[6].charAt(i) == ',') hasComma = true;
        if (!hasComma)
            abilities.add(parsedData[6]);
        else {
            String[] abilityArray = parsedData[6].split(", ");
            for (int i = 0; i < abilityArray.length; i++) abilities.add(abilityArray[i]);
        }
        setPokemonAbilities(abilities);

        if (!parsedData[7].equals("")) setPokemonWeight(Double.parseDouble(parsedData[7]));
        if (!parsedData[8].equals("")) setPokemonHeight(Double.parseDouble(parsedData[8]));
        if (!parsedData[9].equals("")) setCaptureRate(Integer.parseInt(parsedData[9]));

        if (parsedData[10].equals("1")) setLegendary(true);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        setCaptureDate(dateFormatter.parse(parsedData[11]));
    }
}

class TP {
    private Instant start;
    private Instant end;
    private int comparisons;
    private int movements;

    TP() {
        this.start = Instant.now();
        this.comparisons = 0;
        this.movements = 0;
    }

    public void comp() {
        this.comparisons++;
    }

    public void comp(int x) {
        this.comparisons += x;
    }

    public void move() {
        this.movements++;
    }

    public void move(int x) {
        this.movements += x;
    }

    void end() {
        this.end = Instant.now();
    }

    double diff() {
        return Duration.between(this.start, this.end).getNano() / 1000000000.0;
    }

    void print(String fileName) throws Exception {
        PrintWriter write = new PrintWriter(new FileWriter(fileName));
        write.printf("Matrícula: 863485\t");
        write.printf("Tempo de execução: " + diff() + "\t");
        write.printf("Comparações: " + comparisons + "\t");
        write.printf("Movimentações: " + movements);
        write.close();
    }

    void printSearch(String fileName) throws Exception {
        PrintWriter write = new PrintWriter(new FileWriter(fileName));
        write.printf("Matrícula: 863485\t");
        write.printf("Tempo de execução: " + diff() + "\t");
        write.printf("Comparações: " + comparisons);
        write.close();
    }
}

class noSec {
    public String data;
    public noSec esq;
    public noSec dir;

    noSec(String x) {
        data = x;
        esq = null;
        dir = null;
    }
}

class treeSec {
    public noSec root;

    treeSec() {
        root = null;
    }

    public void add(String x, TP tp) {
        root = add(x, root, tp);
    }

    public noSec add(String x, noSec i, TP tp) {
        if (i == null) {
            tp.comp();
            i = new noSec(x);
        } else if (x.compareTo(i.data) < 0) {
            tp.comp();
            i.esq = add(x, i.esq, tp);
        } else if (x.compareTo(i.data) > 0) {
            tp.comp();
            i.dir = add(x, i.dir, tp);
        }
        return i;
    }

    public boolean search(String x, TP tp) {
        return search(x, root, tp);
    }

    public boolean search(String x, noSec i, TP tp) {
        if (i == null) return false;
        tp.comp();
        if (x.equals(i.data)) return true;
        else if (x.compareTo(i.data) < 0) return search(x, i.esq, tp);
        else return search(x, i.dir, tp);
    }
}

class Node {
    public Pokemon pokemon;
    public Node next;

    Node(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.next = null;
    }
}

class Tree {
    public Node head;

    Tree() {
        head = null;
    }

    public boolean walk(String str, TP tp) {
		System.out.println("=> " + str);
		System.out.print("raiz ");
		boolean[] found = new boolean[1];
		found[0] = false;
		walk(root, str, found, tp);
		return found[0];
	}

	public void walk(Node i, String str, boolean[] found, TP tp) {
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

	public void addStart(int x) {
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

	 public void add(int x, String name, TP tp) {
		 root = add(x, root, name, tp);
	 }

	 public Node add(int x, Node i, String name, TP tp) {
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

public class TP04Q02{

	static void swapPokemon(Pokemon[] arr, int a, int b){
		Pokemon tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public static void main(String[] args){
		try {
			Pokemon[] pokemons = Pokemon.readFile("/tmp/pokemon.csv");
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
				t.add(pokemons[x-1].getCaptureRate()%15, pokemons[x-1].getName(), tp);
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

		} catch(FileNotFoundException e) {
			System.out.println(e);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
