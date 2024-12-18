import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

// Classe que representa um Pokémon
class Pokemon {
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
    private Date captureDate; 

    // Construtor padrão
    public Pokemon() { }

    // Construtor com todos os parâmetros
    public Pokemon(int id, int generation, String name, String description,
                   List<String> types, List<String> abilities, double weight, double height,
                   int captureRate, boolean isLegendary, Date captureDate) {
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

    // Métodos getters
    public int getId() { return id; }
    public int getGeneration() { return generation; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public int getCaptureRate() { return captureRate; }
    public boolean getIsLegendary() { return isLegendary; }
    public Date getCaptureDate() { return captureDate; }

    // Retorna a data de captura como string formatada
    public String getCaptureDateAsString() {
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        return sfd.format(captureDate);
    }

    // Converte uma lista de strings para uma representação formatada
    private String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("'%s'", list.get(i)));
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Métodos para obter tipos e habilidades como strings
    public List<String> getTypes() { return types; }
    public String getTypesAsString() { return listToString(types); }
    public List<String> getAbilities() { return abilities; }
    public String getAbilitiesAsString() { return listToString(abilities); }

    // Método para ler um Pokémon a partir de uma linha de texto
    public static Pokemon read(String line) {
        Pokemon pokemon = new Pokemon();
        String[] values = line.split("(?<!'),");
        
        // Limpa espaços em branco em cada valor
        for (int i = 0; i < values.length; i++) values[i] = values[i].trim();

        // Atribui valores aos atributos do Pokémon
        pokemon.setId(Integer.parseInt(values[0]));
        pokemon.setGeneration(Integer.parseInt(values[1]));
        pokemon.setName(values[2]);
        pokemon.setDescription(values[3]);

        // Adiciona tipos
        ArrayList<String> types = new ArrayList<>();
        if (values[4].length() > 0) types.add(values[4]);
        if (values[5].length() > 0) types.add(values[5]);
        pokemon.setTypes(types);

        // Adiciona habilidades
        ArrayList<String> abilities = new ArrayList<>();
        String a = values[6].substring(2, values[6].length() - 2); 
        String[] as = a.split(",");
        for (String ability : as) {
            ability = ability.trim();
            abilities.add(ability.substring(1, ability.length() - 1)); 
        }
        pokemon.setAbilities(abilities);

        // Converte e atribui peso e altura, com tratamento de exceção
        pokemon.setWeight(parseDouble(values[7]));
        pokemon.setHeight(parseDouble(values[8]));
        pokemon.setCaptureRate(parseInt(values[9]));
        pokemon.setIsLegendary("1".equals(values[10])); 

        // Converte a data de captura para o formato Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            pokemon.setCaptureDate(sdf.parse(values[11]));
        } catch (ParseException ex) {
            ex.printStackTrace(); 
        }

        return pokemon;
    }

    // Métodos auxiliares para conversão
    private static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return 0.0; 
        }
    }

    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0; 
        }
    }

    // Método para imprimir detalhes do Pokémon
    public void print() {
        System.out.println(String.format(
            "[#%d -> %s: %s - %s - %s - %.1fkg - %.1fm - %d%% - %s - %d gen] - %s",
            getId(),
            getName(),
            getDescription(),
            getTypesAsString(),
            getAbilitiesAsString(),
            getWeight(),
            getHeight(),
            getCaptureRate(),
            getIsLegendary(),
            getGeneration(),
            getCaptureDateAsString()));
    }

    // Método clone para criar uma cópia do Pokémon
    @Override
    public Pokemon clone() {
        Pokemon clone = new Pokemon();
        clone.setId(getId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setTypes(new ArrayList<>(getTypes())); 
        clone.setAbilities(new ArrayList<>(getAbilities())); 
        clone.setWeight(getWeight());
        clone.setHeight(getHeight());
        clone.setCaptureRate(getCaptureRate());
        clone.setIsLegendary(getIsLegendary());
        clone.setGeneration(getGeneration());
        clone.setCaptureDate(getCaptureDate()); 
        return clone;
    }

    // Métodos setters
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
    public void setCaptureDate(Date captureDate) { this.captureDate = captureDate; }
}

// Classe principal para execução do programa
public class TP02Q01 {
    public static final String PATH = "/tmp/pokemon.csv"; 

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] lines = init(); 
        String line = in.nextLine();

        // Loop para leitura de entradas do usuário
        while (!line.equals("FIM")) {
            try {
                int index = Integer.parseInt(line) - 1; 
                if (index >= 0 && index < lines.length) {
                    Pokemon.read(lines[index]).print(); 
                } else {
                    System.out.println("Índice fora dos limites."); 
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número."); 
            }
            line = in.nextLine();
        }

        in.close();
    }

    // Método para inicializar e ler linhas do arquivo CSV
    public static String[] init() {
        String[] lines = new String[801]; 
        try (BufferedReader bReader = new BufferedReader(new FileReader(PATH))) {
            bReader.readLine(); 
            String line;
            int i = 0;
            while ((line = bReader.readLine()) != null) {
                lines[i++] = line; 
            }
        } catch (IOException ex) {
            ex.printStackTrace(); 
        }
        return lines; 
    }
}
