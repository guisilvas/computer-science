import java.time.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class Pokemon
{
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

	Pokemon(int id, int generation, String name, String description, ArrayList<String>  types, ArrayList<String>  abilities, double weight, double height, int captureRate, boolean legendary, Date captureDate)
	{
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

	Pokemon()
	{
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
    public int getId() { return id; }
    public int getGeneration() { return generation; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getTypes() { return types; }
    public List<String> getAbilities() { return abilities; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public int getCaptureRate() { return captureRate; }
    public Boolean getIsLegendary() { return legendary; }
    public Date getCaptureDate() { return captureDate; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setGeneration(int generation) { this.generation = generation; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setTypes(ArrayList<String> types) { this.types = types; }
    public void setAbilities(ArrayList<String> abilities) { this.abilities = abilities; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setHeight(double height) { this.height = height; }
    public void setCaptureRate(int captureRate) { this.captureRate = captureRate; }
    public void setIsLegendary(boolean isLegendary) { this.legendary = isLegendary; }
    public void setCaptureDate(Date captureDate) { this.captureDate = captureDate; }
    // Add type
    public void addType(String type) { this.types.add(type); }
    // Add ability
    public void addAbility(String ability) { this.abilities.add(ability); }

	// Print
	public void print(){
		System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

		int reps = types.size();
		for (int i=0; i<reps-1; i++)
		{
			System.out.print("'" + types.get(i) + "', ");
		}
		System.out.print("'" + types.get(reps-1) + "'] - [");

		reps = abilities.size();
		for (int i=0; i<reps-1; i++)
		{
			System.out.print(abilities.get(i) + ", ");
		}
		System.out.print(abilities.get(reps-1) + "] - ");
		System.out.print(weight + "kg - " + height + "m - " + captureRate + "% - ");

		if (getIsLegendary()) System.out.print("true ");
		else System.out.print("false ");

		System.out.print("- " + generation + " gen] - ");

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(formater.format(captureDate));
	}

	// Read
	public void read(String s) throws Exception {
		String format = "";
		boolean control = true;
		for (int i=0; i<s.length(); i++)
		{
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
		if (!type2.equals("")) tipos.add(type2);
		setTypes(tipos);

		boolean hasComma = false;
		ArrayList<String> abis = new ArrayList<String>();
		for (int i=0; i<aux[6].length() && !hasComma; i++)
			if (aux[6].charAt(i)==',') hasComma = true;
		if (!hasComma) abis.add(aux[6]);
		else
		{
			String[] abs = aux[6].split(", ");
			for(int i=0; i<abs.length; i++) abis.add(abs[i]);
		}
		setAbilities(abis);

		if (!aux[7].equals("")) setWeight(Double.parseDouble(aux[7]));
		if (!aux[8].equals("")) setHeight(Double.parseDouble(aux[8]));
		if (!aux[9].equals("")) setCaptureRate(Integer.parseInt(aux[9]));
		
		if (aux[10].equals("1")) setIsLegendary(true);

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		setCaptureDate(formater.parse(aux[11]));
	}

	// Clone
	public Pokemon clone()
	{
		return new Pokemon(id, generation, name, description, types, abilities, weight, height, captureRate, legendary, captureDate);
	}
}

// File generator
class FileTp{
	private Instant start;
	private Instant end;
	private int comparisons;
	private int movements;

	FileTp(){
		this.start = Instant.now();
		this.comparisons = 0;
		this.movements = 0;
	}

	public void comp(){
		this.comparisons++;
	}

	public void move(){
		this.movements++;
	}

	void end(){
		this.end = Instant.now();
	}

	double diff(){
		return Duration.between(this.start, this.end).getNano() / 1000000000.0;
	}

	void print(String file) throws Exception{
		PrintWriter write = new PrintWriter(new FileWriter(file));
		write.printf("Matrícula: 863485\t");
		write.printf("Tempo de execução: " + diff() + "\t");
		write.printf("Comparações: " + comparisons + "\t");
		write.printf("Movimentações: " + movements);
		write.close();
	}

	void printSearch(String file) throws Exception{
		PrintWriter write = new PrintWriter(new FileWriter(file));
		write.printf("Matrícula: 863485\t");
		write.printf("Tempo de execução: " + diff() + "\t");
		write.printf("Comparações: " + comparisons);
		write.close();
	}
}

public class Q03
{
	public static boolean search(ArrayList<Pokemon> pokemons, String key, FileTp tp){
		int reps = pokemons.size();
		boolean found = false;
		for (int i=0; i<reps && !found; i++)
		{
			if(key.equals(pokemons.get(i).getName())) found = true;
			tp.comp();
		}
		return found;
	}

	public static void main(String[] args)
	{
		try
		{
			Pokemon[] pokemons = Pokemon.readFile("/tmp/pokemon.csv");
			Scanner scan = new Scanner(System.in);

			ArrayList<Pokemon> using = new ArrayList<Pokemon>();
			String input = scan.nextLine();
			while(!input.equals("FIM")){
				int n = Integer.parseInt(input);
				using.add(pokemons[n-1].clone());
				input = scan.nextLine();
			}
			
			FileTp tp = new FileTp();
			input = scan.nextLine();
			while(!input.equals("FIM")){
				if(search(using, input, tp)) System.out.println("SIM");
				else System.out.println("NAO");
				input = scan.nextLine();
			}
			tp.end();
			tp.printSearch("863485_sequencial.txt");
			scan.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		} catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
