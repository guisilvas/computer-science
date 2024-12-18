import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

// Default class
public class Main {
    public static void main(String args[]) throws IOException {
        File file = new File("pokemon.csv");
        Hashtable<Integer, Pokemon> htPokemon = new Hashtable<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // Ignora o cabeçalho
            while ((line = br.readLine()) != null && !line.equals("FIM")) {
                String[] values = line.split(",");
                Pokemon pokemon = new Pokemon();
                int i = 0;
                pokemon.setId(Integer.parseInt(values[i++]));
                pokemon.setGeneration(Integer.parseInt(values[i++]));
                pokemon.setName(values[i++]);
                pokemon.setDescription(values[i++]);
                pokemon.addType(values[i++]);
                pokemon.addType(values[i++]);

                String abilitiesString = values[i++].replaceAll("[\\[\\]'\\s]", "");
                if (!abilitiesString.isEmpty()) {
                    for (String ability : abilitiesString.split(",")) {
                        if (!ability.isEmpty()) {
                            pokemon.addAbility(ability.trim());
                        }
                    }
                }

                pokemon.print();

                pokemon.setWeight(Double.parseDouble(values[i++]));
                pokemon.setHeight(Double.parseDouble(values[i++]));
                pokemon.setCaptureRate(Integer.parseInt(values[i++]));
                pokemon.setIsLegendary(Boolean.parseBoolean(values[i++]));
                pokemon.setCaptureDate(LocalDate.parse(values[i], formatter));

                htPokemon.put(pokemon.getId(), pokemon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        Pokemon pokemon = htPokemon.get(id);
        if (pokemon != null) {
            pokemon.print();
        } 
        
        scanner.close();
    }
}

// Pokemon data
class Pokemon
{
    // Atributes
    private int id;
    private int generation;
    private String name;
    private String description;
    private List<String> types;
    private List<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;

    // Default constructor
    public Pokemon()
    {
        this.id = 0;
        this.generation = 0;
        this.name = null;
        this.description = null;
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.weight = 0.0;
        this.height = 0.0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate = LocalDate.now();
    }

    // Constructor
    public Pokemon(int id, int generation, String name, String description, List<String> types, List<String> abilities, double weight, double height, int captureRate, boolean isLegendary, LocalDate captureDate)
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
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
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
    public Boolean getIsLegendary() { return isLegendary; }
    public LocalDate getCaptureDate() { return captureDate; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setGeneration(int generation) { this.generation = generation; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setTypes(List<String> types) { this.types = types; }
    public void setAbilities(List<String> abilities) { this.abilities = abilities; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setHeight(double height) { this.height = height; }
    public void setCaptureRate(int captureRate) { this.captureRate = captureRate; }
    public void setIsLegendary(boolean isLegendary) { this.isLegendary = isLegendary; }
    public void setCaptureDate(LocalDate captureDate) { this.captureDate = captureDate; }
    // Add type
    public void addType(String type) { this.types.add(type); }
    // Add ability
    public void addAbility(String ability) { this.abilities.add(ability); }

    // Print
    public void print() {
        System.out.printf("[#%d -> %s: %s - [%s] - [%s] - %.2fkg - %.2fm - %d%% - %b - %d gen] - %s%n",
                this.id,
                this.name,
                this.description,
                String.join(", ", this.types),
                String.join(", ", this.abilities),
                this.weight,
                this.height,
                this.captureRate,
                this.isLegendary,
                this.generation,
                this.captureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    // Clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Read
    
}