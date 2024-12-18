#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>

// Definindo um tamanho máximo para strings
#define strSize 80

// Função para imprimir uma mensagem de flag
void flag(){
	printf("FLAG\n");
}

// Função para encontrar o índice da vírgula em uma string
int commaIndex(char* s){
	bool found = false;
	int i=0;
	// Percorre a string até encontrar o caractere '.'
	for(; i<strlen(s) && !found; i++){
		if(s[i]=='.') found = true;
	}
	return i-1; // Retorna o índice da vírgula
}

// Função para converter uma string em um número decimal
double parseDouble(char* s){
	int i = 0;
	double result = 0.0;
	int leng = strlen(s);
	int comma = commaIndex(s),
		amtLeft = leng-comma+2, // Quantidade de casas à esquerda da vírgula
		j = comma-1;
	
	// Processa a parte inteira
	for(i=0; i<comma; i++){
		result += (double)((s[i]-48)*pow(10, j));
		j--;
	}
	
	j = 1; // Resetando j para processar a parte decimal
	// Processa a parte decimal
	for(i=i+1; i<leng; i++){
		result += (double)((s[i]-48)/pow(10, j));
		j++;
	}
	return result; // Retorna o resultado como um double
}

// Função para converter uma string em um ano
int parseYear(char* s){
    int result = 0;
	int exp = 3; // Exponencial inicial
	int i = 0;
    // Converte os quatro caracteres da string para um inteiro
    while(i<4){
        result += (int)(s[i]-48) * (int)pow(10.0, (double)exp);
        i++;
		exp--;
    }
    return result; // Retorna o ano
}

// Função para converter uma string em um inteiro
int parseInt(char* s){
    int result = 0;
    int i = strlen(s)-1,
        exp = 0;
    // Converte a string para um inteiro
    while(i>=0){
        result += (int)(s[i]-48) * (int)pow(10.0, (double)exp);
        i--;
		exp++;
    }
    return result; // Retorna o resultado como um inteiro
}

// Estrutura para armazenar uma data
typedef struct {
	int day;
	int month;
	int year;
} Date;

// Função para converter uma string em uma data
Date strToDate(char* s){
	char* d = strtok(s, "/");
	char* m = strtok(NULL, "/");
	char* y = strtok(NULL, "/");
	Date result;
	// Converte os componentes da data
	result.day = parseInt(d);
	result.month = parseInt(m);
	result.year = parseYear(y);
	return result; // Retorna a data
}

// Função para converter uma data em uma string
char* dateToStr(Date i){
	char* result = malloc(40*sizeof(char)); // Aloca memória para a string resultante
	sprintf(result, "%02d/%02d/%04d", i.day, i.month, i.year); // Formata a data
	return result; // Retorna a string da data
}

// Estrutura para armazenar os tipos de Pokémon
typedef struct {
	int n; // Número de tipos
	char type1[strSize]; // Primeiro tipo
	char type2[strSize]; // Segundo tipo (opcional)
} Types;

// Estrutura para armazenar as habilidades de Pokémon
typedef struct {
	int n; // Número de habilidades
	char ab1[strSize]; // Primeira habilidade
	char ab2[strSize]; // Segunda habilidade
	char ab3[strSize]; // Terceira habilidade
	char ab4[strSize]; // Quarta habilidade
	char ab5[strSize]; // Quinta habilidade
	char ab6[strSize]; // Sexta habilidade
} Abilities;

// Estrutura para armazenar informações sobre um Pokémon
typedef struct {
	int id; // ID do Pokémon
	int generation; // Geração do Pokémon
	char name[strSize]; // Nome do Pokémon
	char description[strSize]; // Descrição do Pokémon
	Types types; // Tipos do Pokémon
	Abilities abilities; // Habilidades do Pokémon
	double weight; // Peso do Pokémon
	double height; // Altura do Pokémon
	int captureRate; // Taxa de captura
	bool legendary; // Se é lendário
	Date captureDate; // Data de captura
} Pokemon;

// Função para criar um Pokémon a partir de uma linha de dados
Pokemon* makeMon(char* line){
	Pokemon* result = calloc(1, sizeof(Pokemon)); // Aloca memória para o Pokémon
	int leng = strlen(line);
	char* format = malloc(leng * sizeof(char)); // Aloca memória para formatar a linha
	bool control = true;
	int j = 0;

	// Formata a linha, substituindo vírgulas por ponto e vírgula
	for(int i=0; i<leng; i++){
		if(line[i] == '"') control = !control;
		else if(line[i] == ',' && control) format[j++] = ';';
		else if(line[i] != '[' && line[i] != ']') format[j++] = line[i];
	}

	char** aux = calloc(12, sizeof(char*)); // Aloca memória para armazenar os dados do Pokémon
	int nSplits=0;
	// Divide a linha formatada em componentes
	for(aux[nSplits++]=strtok(format,";"); aux[nSplits]=strtok(NULL,";"); nSplits++);

	// Preenche os dados do Pokémon
	result->id = parseInt(aux[0]);
	result->generation = parseInt(aux[1]);
	strcpy(result->name, aux[2]);
	strcpy(result->description, aux[3]);

	result->types.n = nSplits==11 ? 1 : 2; // Define o número de tipos
	strcpy(result->types.type1, aux[4]); // Copia o primeiro tipo
	if(result->types.n == 2) strcpy(result->types.type2, aux[5]); // Copia o segundo tipo

	// Processa as habilidades
	bool commaInAbilities = false;
	int posAbs = nSplits==10 ? 6 : nSplits==11 ? 5 : 6;
	for(int j=0; j<strlen(aux[posAbs]); j++)
		if(aux[posAbs][j]==',') commaInAbilities = true;

	if(!commaInAbilities){
		result->abilities.n = 1; // Apenas uma habilidade
		strcpy(result->abilities.ab1, aux[posAbs]);
	} else {
		char* formt = malloc(strSize * sizeof(char));
		strcpy(formt, aux[posAbs]);
		for(int x=0; x<strlen(formt); x++)
			if(formt[x]==' ' && formt[x-1]==',') formt[x] = ',';

		char* buffer;
		// Divide as habilidades
		buffer = strtok(formt, ",");
		result->abilities.n = 1;
		strcpy(result->abilities.ab1, buffer);

		if(buffer = strtok(NULL, ",")){
			result->abilities.n = 2;
			strcpy(result->abilities.ab2, buffer);

			if(buffer = strtok(NULL, ",")){
				result->abilities.n = 3;
				strcpy(result->abilities.ab3, buffer);

				if(buffer = strtok(NULL, ",")){
					result->abilities.n = 4;
					strcpy(result->abilities.ab4, buffer);

					if(buffer = strtok(NULL, ",")){
						result->abilities.n = 5;
						strcpy(result->abilities.ab5, buffer);

						if(buffer = strtok(NULL, ",")){
							result->abilities.n = 6;
							strcpy(result->abilities.ab6, buffer);
						}
					}
				}
			}
		}
	}

	// Define o peso e a altura
	if(nSplits>10){
		result->weight = parseDouble(aux[7-(12-nSplits)]);
		result->height = parseDouble(aux[8-(12-nSplits)]);
	} else {
		result->weight = 0.0;
		result->height = 0.0;
	}

	// Define a taxa de captura
	result->captureRate = parseInt(aux[9-(12-nSplits)]);

	// Verifica se o Pokémon é lendário
	if(strcmp(aux[10-(12-nSplits)], "1") == 0) result->legendary = true;
	else result->legendary = false;

	// Converte a data de captura
	result->captureDate = strToDate(aux[11-(12-nSplits)]);

	return result; // Retorna o Pokémon criado
}

// Função para imprimir as informações de um Pokémon
void printMon(Pokemon* poke){
	printf("[#%d -> %s: %s - [", poke->id, poke->name, poke->description);

	if(poke->types.n == 1) printf("'%s'] - [", poke->types.type1);
	else printf("'%s', '%s'] - [", poke->types.type1, poke->types.type2);

	// Imprime as habilidades
	if(poke->abilities.n == 1)
		printf("%s] - ", poke->abilities.ab1);
	else if(poke->abilities.n == 2)
		printf("%s, %s] - ", poke->abilities.ab1, poke->abilities.ab2);
	else if(poke->abilities.n == 3)
		printf("%s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3);
	else if(poke->abilities.n == 4)
		printf("%s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4);
	else if(poke->abilities.n == 5)
		printf("%s, %s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4, poke->abilities.ab5);
	else if(poke->abilities.n == 6)
		printf("%s, %s, %s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4, poke->abilities.ab5, poke->abilities.ab6);

	printf("%.1lfkg - %.1lfm - %d%c - ", poke->weight, poke->height, poke->captureRate, '%');

	// Imprime se é lendário
	if(poke->legendary) printf("true ");
	else printf("false ");

	printf("- %d gen] - ", poke->generation);

	// Converte e imprime a data de captura
	char* capDate = dateToStr(poke->captureDate);
	printf("%s\n", capDate);
}

// Função para ler o arquivo CSV e retornar um array de Pokémon
Pokemon** readFile(const char* path){
	Pokemon** result = calloc(801, sizeof(Pokemon*)); // Aloca espaço para 801 Pokémon
	FILE* csv = fopen(path, "rt"); // Abre o arquivo CSV
	char* trash = malloc(1000 * sizeof(char));
	fgets(trash, 999, csv); // Lê a primeira linha (cabeçalho) e descarta
	free(trash);
	for(int i=0; i<801; i++){
		char* buffer = malloc(1000 * sizeof(char)); // Aloca espaço para ler cada linha
		fgets(buffer, 999, csv);
		result[i] = makeMon(buffer); // Cria um Pokémon a partir da linha
		free(buffer); // Libera memória
	}
	fclose(csv); // Fecha o arquivo
	return result; // Retorna o array de Pokémon
}

// Função para calcular a diferença de tempo
double diff(clock_t start, clock_t end){
	return ((double)(end - start)) / CLOCKS_PER_SEC; // Retorna a diferença em segundos
}

// Função para registrar dados de execução
void logTP(const char* fileName, double time, int comps, int moves){
	FILE* fil = fopen(fileName, "w"); // Abre o arquivo para escrita
	if(fil){
		fprintf(fil, "Matrícula: 863485\t");
		fprintf(fil, "Tempo de Execução: %lf\t", time);
		fprintf(fil, "Comparações: %d\t", comps);
		fprintf(fil, "Movimentações: %d", moves);
		fclose(fil); // Fecha o arquivo
	}
	else printf("Erro ao abrir o arquivo"); // Mensagem de erro
}

// Função para registrar dados de busca
void logSearch(const char* fileName, double time, int comps){
	FILE* fil = fopen(fileName, "w"); // Abre o arquivo para escrita
	if(fil){
		fprintf(fil, "Matrícula: 863485\t");
		fprintf(fil, "Tempo de Execução: %lf\t", time);
		fprintf(fil, "Comparações: %d", comps);
		fclose(fil); // Fecha o arquivo
	}
	else printf("Erro ao abrir o arquivo"); // Mensagem de erro
}

// Variáveis globais para contagem de comparações e movimentações
int comps = 0;
int moves = 0;

// Função para comparar duas strings
bool equals(const char* s1, const char* s2){
	return 0==strcmp(s1, s2); // Retorna verdadeiro se as strings forem iguais
}

// Função para trocar dois inteiros em um array
void swapInt(int* arr, int a, int b){
	int tmp = arr[a];
	arr[a] = arr[b];
	arr[b] = tmp;
}

// Função para trocar dois Pokémon em um array
void swapPoke(Pokemon** arr, int a, int b){
	Pokemon* tmp = arr[a];
	arr[a] = arr[b];
	arr[b] = tmp;
	moves += 3; // Conta movimentações
}

// Função para determinar se deve trocar dois elementos
bool shouldSwap(char* a, char* b){ 
	bool result = false;
	int i=-1;
	do{
		i++;
		if(a[i] > b[i]) result = true; // Troca se a string 'a' for maior que 'b'
	} while(a[i] == b[i]);
	return result; // Retorna resultado da comparação
}

// Função para criar a estrutura de heap
void makeHeap(Pokemon** arr, int max){
	for(int i=max; max>0 && arr[i]->height>arr[(i-1)/2]->height; i=(i-1)/2){
		swapPoke(arr, i, (i-1)/2); // Troca se a altura for maior
		moves+=3; // Conta movimentações
		comps++; // Conta comparações
	}
}

// Função para verificar se um nó tem filho
bool hasSon(int aux, int endIndex){
	bool ver = false;
	if(((aux*2)+1) <= endIndex) ver = true; // Verifica se o filho existe
	return ver;
}

// Função para obter o filho maior
int getBiggerSon(Pokemon** arr, int aux, int endIndex){
	int son = 0;
	if(((2*aux)+1) == endIndex)
		son = endIndex; // Se houver apenas um filho
	else if(arr[(2*aux)+1]->height > arr[(2*aux)+2]->height){
		son = ((2*aux)+1); // O primeiro filho é maior
		comps++; // Conta comparação
	}
	else son = ((2*aux)+2); // O segundo filho é maior
	return son; // Retorna o filho maior
}

// Função para restaurar a estrutura de heap
void redoHeap(Pokemon** arr, int endIndex){
	int aux = 0;
	bool ctrl = true;
	while(hasSon(aux, endIndex) && ctrl){
		int son = getBiggerSon(arr, aux, endIndex); // Obtém o filho maior
		if(arr[aux]->height < arr[son]->height){
			swapPoke(arr, aux, son); // Troca se a altura do pai for menor
			comps++; // Conta comparação
			moves+=3; // Conta movimentações
			aux = son; // Move o índice para o filho
		}
		else ctrl = false; // Se não trocar, sai do loop
	}
}

// Função de ordenação HeapSort
void heapSort(Pokemon** arr, int n){
	int endIndex = n-1; // Último índice
	for(int max=1; max<n; max++){
		makeHeap(arr, max); // Cria a estrutura de heap
	}
	while(endIndex>0){
		swapPoke(arr, 0, endIndex); // Troca o primeiro com o último
		moves+=3; // Conta movimentações
		endIndex--;
		redoHeap(arr, endIndex); // Restaura o heap
	}
}

// Função de ordenação por inserção
void insertion(Pokemon** arr, int n){
	for(int i=1; i<n; i++){
		Pokemon* tmp = arr[i]; // Armazena o elemento
		int j = i-1;
		// Move os elementos maiores para a direita
		while(j>=0 && arr[j]->height==tmp->height && shouldSwap(arr[j]->name, tmp->name)){
			comps++; // Conta comparações
			arr[j+1] = arr[j];
			j--;
			moves++; // Conta movimentações
		}
		arr[j+1] = tmp; // Insere o elemento na posição correta
		moves++; // Conta movimentações
	}
}

// Função que combina HeapSort e Insertion Sort
void sort(Pokemon** pokes, int n, int k){
	heapSort(pokes, k); // Ordena os primeiros k elementos com HeapSort
	insertion(pokes, n); // Ordena o restante com Insertion Sort
}

// Função principal
int main(){
	Pokemon** pokes = readFile("/tmp/pokemon.csv"); // Lê o arquivo CSV com os dados dos Pokémon

	int i=0;
	int* usingIds = malloc(100*sizeof(int)); // Aloca espaço para IDs a serem usados
	
	char* input = malloc(20*sizeof(char)); // Aloca espaço para entrada do usuário
	scanf(" %s", input); // Lê a entrada do usuário
	while(!equals(input, "FIM")){ // Continua até que o usuário digite "FIM"
		usingIds[i] = parseInt(input); // Converte a entrada para inteiro
		i++;
		free(input);
		input = malloc(20*sizeof(char)); // Aloca espaço para nova entrada
		scanf(" %s", input); // Lê a nova entrada
	}
	free(input); // Libera a memória da última entrada

	Pokemon** using = calloc(i, sizeof(Pokemon*)); // Aloca espaço para os Pokémon selecionados
	for(int j=0; j<i; j++){
		using[j] = pokes[usingIds[j]-1]; // Seleciona os Pokémon com base nos IDs
	}
	free(usingIds); // Libera a memória dos IDs

	clock_t start = clock(); // Inicia a contagem do tempo
	sort(using, i, i); // Ordena os Pokémon
	for(int j=0; j<10; printMon(using[j++])); // Imprime os 10 primeiros Pokémon
	clock_t end = clock(); // Finaliza a contagem do tempo

	free(using); // Libera a memória dos Pokémon
	free(pokes); // Libera a memória do array de Pokémon
	logTP("863485_heapsort.txt", diff(start, end), comps, moves); // Registra os dados de execução

	return 0; // Retorna 0 para indicar sucesso
}
