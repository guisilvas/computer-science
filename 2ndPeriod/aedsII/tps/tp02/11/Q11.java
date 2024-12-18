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
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
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
        while (csv.hasNext()) {
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

    // Print
    public void print() {
        System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

        int reps = types.size();
        for (int i = 0; i < reps - 1; i++) {
            System.out.print("'" + types.get(i) + "', ");
        }
        System.out.print("'" + types.get(reps - 1) + "'] - [");

        reps = abilities.size();
        for (int i = 0; i < reps - 1; i++) {
            System.out.print(abilities.get(i) + ", ");
        }
        System.out.print(abilities.get(reps - 1) + "] - ");
        System.out.print(weight + "kg - " + height + "m - " + captureRate + "% - ");

        System.out.print(getIsLegendary() ? "true " : "false ");
        System.out.print("- " + generation + " gen] - ");

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(formater.format(captureDate));
    }

    // Read
    public void read(String s) throws Exception {
        String format = "";
        boolean control = true;
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if (x == '"') control = !control;
            else if (x == ',' && control) format += ';';
            else if (x != '[' && x != ']') format += x;
        }
        String[] aux = format.split(";");

        setId(Integer.parseInt(aux[0]));
        setGeneration(Integer.parseInt(aux[1]));
        setName(aux[2]);
        setDescription(aux[3]);

        String type1 = aux[4];
        String type2 = aux[5];
        ArrayList<String> tipos = new ArrayList<>();
        tipos.add(type1);
        if (!type2.equals("")) tipos.add(type2);
        setTypes(tipos);

        boolean hasComma = false;
        ArrayList<String> abis = new ArrayList<>();
        for (int i = 0; i < aux[6].length() && !hasComma; i++)
            if (aux[6].charAt(i) == ',') hasComma = true;
        if (!hasComma) abis.add(aux[6]);
        else {
            String[] abs = aux[6].split(", ");
            Collections.addAll(abis, abs);
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
    public Pokemon clone() {
        return new Pokemon(id, generation, name, description, types, abilities, weight, height, captureRate, legendary, captureDate);
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
}

public class Q11 {
    static void swapPoke(Pokemon[] arr, int a, int b) {
        Pokemon tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static void sortRate(Pokemon[] arr, TP tp) {
        int n = arr.length;
        int maior = arr[0].getCaptureRate();
        for (int i = 0; i < n; i++) {
            int x = arr[i].getCaptureRate();
            if (x > maior) maior = x;
        }

        int[] count = new int[maior + 1];
        Pokemon[] ordenado = new Pokemon[n];

        // Inicializa o contador
        for (int i = 0; i < count.length; count[i++] = 0);
        for (int i = 0; i < n; count[arr[i].getCaptureRate()]++, i++);
        for (int i = 1; i < count.length; count[i] += count[i - 1], i++);

        // Ordena pelo captureRate
        for (int i = n - 1; i >= 0; i--) {
            int rate = arr[i].getCaptureRate();
            ordenado[count[rate] - 1] = arr[i];
            count[rate]--;
            tp.move();
        }

        // Ordena os itens com o mesmo captureRate pelo nome
        Arrays.sort(ordenado, Comparator.comparingInt(Pokemon::getCaptureRate).thenComparing(Pokemon::getName));

        System.arraycopy(ordenado, 0, arr, 0, n);
        for (int i = 0; i < n; i++) tp.move();
    }

    static void sort(Pokemon[] pokes, TP tp) {
        sortRate(pokes, tp);
    }

    public static void main(String[] args) {
        try {
            Pokemon[] pokes = Pokemon.readFile("/tmp/pokemon.csv");
            Scanner scan = new Scanner(System.in);

            int[] usingIds = new int[100];
            int x = 0;
            String input = scan.nextLine();
            while (!input.equals("FIM")) {
                usingIds[x] = Integer.parseInt(input);
                x++;
                input = scan.nextLine();
            }

            Pokemon[] using = new Pokemon[x];
            for (int i = 0; i < x; i++) {
                using[i] = pokes[usingIds[i] - 1];
            }

            TP tp = new TP();
            sort(using, tp);
            tp.end();
            for (int i = 0; i < using.length; using[i++].print());
            tp.print("863485_countingsort.txt");
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
