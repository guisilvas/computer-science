import java.time.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

// Classe que representa um Pokémon
class Pokemon {
    private int id; // ID do Pokémon
    private int generation; // Geração do Pokémon
    private String name; // Nome do Pokémon
    private String description; // Descrição do Pokémon
    private ArrayList<String> types; // Tipos do Pokémon
    private ArrayList<String> abilities; // Habilidades do Pokémon
    private double weight; // Peso do Pokémon
    private double height; // Altura do Pokémon
    private int captureRate; // Taxa de captura
    private boolean legendary; // Se é um Pokémon lendário
    private Date captureDate; // Data de captura

    // Construtor com parâmetros
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

    // Construtor padrão
    Pokemon() {
        this.id = 0;
        this.generation = 0;
        this.name = null;
        this.description = null;
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.weight = 0.0;
        this.height = 0.0;
        this.captureRate = 0;
        this.legendary = false;
        this.captureDate = new Date();
    }

    // Método para criar um Pokémon a partir de uma string
    public static Pokemon make(String s) throws Exception {
        Pokemon result = new Pokemon();
        result.read(s);
        return result;
    }

    // Método para ler um arquivo CSV e criar um array de Pokémon
    public static Pokemon[] readFile(String path) throws Exception {
        Pokemon[] result = new Pokemon[801];
        File fil = new File(path);
        Scanner csv = new Scanner(fil);
        String line = csv.nextLine(); // Lê o cabeçalho do CSV
        int i = 0;
        while (csv.hasNext()) {
            line = csv.nextLine();
            result[i] = make(line); // Cria Pokémon a partir da linha
            i++;
        }
        csv.close();
        return result;
    }

    // Métodos getters
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

    // Métodos setters
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

    // Método para imprimir os detalhes do Pokémon
    public void print() {
        System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

        int reps = types.size(); // Número de tipos
        for (int i = 0; i < reps - 1; i++) {
            System.out.print("'" + types.get(i) + "', ");
        }
        System.out.print("'" + types.get(reps - 1) + "'] - [");

        reps = abilities.size(); // Número de habilidades
        for (int i = 0; i < reps - 1; i++) {
            System.out.print(abilities.get(i) + ", ");
        }
        System.out.print(abilities.get(reps - 1) + "] - ");
        System.out.print(weight + "kg - " + height + "m - " + captureRate + "% - ");

        System.out.print(getIsLegendary() ? "true " : "false ");
        System.out.print("- " + generation + " gen] - ");

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(formater.format(captureDate)); // Formata a data de captura
    }

    // Método para ler e processar uma linha de entrada
    public void read(String s) throws Exception {
        String format = "";
        boolean control = true; // Controle para ignorar aspas
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if (x == '"') control = !control; // Inverte controle ao encontrar aspas
            else if (x == ',' && control) format += ';'; // Substitui vírgulas por ponto e vírgula
            else if (x != '[' && x != ']') format += x; // Ignora colchetes
        }
        String[] aux = format.split(";"); // Divide a string formatada

        // Configura os atributos do Pokémon
        setId(Integer.parseInt(aux[0]));
        setGeneration(Integer.parseInt(aux[1]));
        setName(aux[2]);
        setDescription(aux[3]);

        String type1 = aux[4];
        String type2 = aux[5];
        ArrayList<String> tipos = new ArrayList<>();
        tipos.add(type1);
        if (!type2.equals("")) tipos.add(type2); // Adiciona o segundo tipo se existir
        setTypes(tipos);

        // Processa habilidades
        boolean hasComma = false;
        ArrayList<String> abis = new ArrayList<>();
        for (int i = 0; i < aux[6].length() && !hasComma; i++)
            if (aux[6].charAt(i) == ',') hasComma = true;
        if (!hasComma) abis.add(aux[6]);
        else {
            String[] abs = aux[6].split(", ");
            Collections.addAll(abis, abs); // Adiciona habilidades separadas por vírgula
        }
        setAbilities(abis);

        // Configura peso, altura, taxa de captura e se é lendário
        if (!aux[7].equals("")) setWeight(Double.parseDouble(aux[7]));
        if (!aux[8].equals("")) setHeight(Double.parseDouble(aux[8]));
        if (!aux[9].equals("")) setCaptureRate(Integer.parseInt(aux[9]));
        if (aux[10].equals("1")) setIsLegendary(true);

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        setCaptureDate(formater.parse(aux[11])); // Configura a data de captura
    }

    // Método para clonar o Pokémon
    public Pokemon clone() {
        return new Pokemon(id, generation, name, description, types, abilities, weight, height, captureRate, legendary, captureDate);
    }
}

// Classe para registrar informações de desempenho
class Question {
    private Instant start; // Início do registro
    private Instant end; // Fim do registro
    private int comparisons; // Contador de comparações
    private int movements; // Contador de movimentos

    Question() {
        this.start = Instant.now(); // Registra o início
        this.comparisons = 0;
        this.movements = 0;
    }

    public void comp() {
        this.comparisons++; // Incrementa comparação
    }

    public void comp(int x) {
        this.comparisons += x; // Incrementa múltiplas comparações
    }

    public void move() {
        this.movements++; // Incrementa movimento
    }

    public void move(int x) {
        this.movements += x; // Incrementa múltiplos movimentos
    }

    void end() {
        this.end = Instant.now(); // Registra o fim
    }

    double diff() {
        return Duration.between(this.start, this.end).getNano() / 1000000000.0; // Calcula a diferença de tempo
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

public class Q15 {
    // Método para trocar dois Pokémon em um array
    static void swapPoke(Pokemon[] arr, int a, int b) {
        Pokemon tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    // Método de ordenação por seleção
    static void sort(Pokemon[] pokes, Question q, int k) {
        int n = pokes.length;
        for (int i = 0; i < k; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                // Compara os nomes dos Pokémon
                if (0 < pokes[menor].getName().compareTo(pokes[j].getName())) menor = j;
                q.comp(); // Registra a comparação
            }
            swapPoke(pokes, menor, i); // Troca os Pokémon
            q.move(3); // Registra os movimentos
        }
    }

    // Método principal
    public static void main(String[] args) {
        try {
            Pokemon[] pokes = Pokemon.readFile("/tmp/pokemon.csv"); // Lê os dados dos Pokémon
            Scanner sc = new Scanner(System.in);

            int[] usingIds = new int[100]; // Array para armazenar IDs de Pokémon selecionados
            int x = 0;
            String input = sc.nextLine();
            while (!input.equals("FIM")) {
                usingIds[x] = Integer.parseInt(input); // Armazena IDs
                x++;
                input = sc.nextLine();
            }

            Pokemon[] using = new Pokemon[x]; // Cria um array para os Pokémon selecionados
            for (int i = 0; i < x; i++) {
                using[i] = pokes[usingIds[i] - 1]; // Adiciona Pokémon ao array
            }

            Question q = new Question(); // Cria um objeto para registrar o desempenho
            sort(using, q, 10); // Ordena os Pokémon
            q.end(); // Finaliza o registro
            for (int i = 0; i < 10; using[i++].print()); // Imprime os Pokémon ordenados
            q.print("863485_selecao.txt"); // Registra os dados de desempenho
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e); // Exibe erro se o arquivo não for encontrado
        } catch (Exception e) {
            System.out.println(e); // Exibe erro geral
        }
    }
}
