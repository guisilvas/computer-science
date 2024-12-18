import matplotlib.pyplot as plt
import pandas as pd

# Lê os dados do arquivo CSV
data = pd.read_csv('desempenho_quicksort.csv')

# Extraindo os dados
tamanhos = data['Tamanho'].unique()  # Obtém tamanhos únicos
tipos = data['Tipo'].unique()  # Obtém tipos únicos

# Configurando o gráfico
plt.figure(figsize=(10, 6))

# Plota os tempos para cada tipo de pivô
for tipo in tipos:
    subset = data[data['Tipo'] == tipo]
    plt.plot(subset['Tamanho'], subset['Primeiro Pivô'], marker='o', label=f'{tipo} - Primeiro Pivô')
    plt.plot(subset['Tamanho'], subset['Último Pivô'], marker='x', label=f'{tipo} - Último Pivô')
    plt.plot(subset['Tamanho'], subset['Pivô Aleatório'], marker='s', label=f'{tipo} - Pivô Aleatório')
    plt.plot(subset['Tamanho'], subset['Mediana de Três'], marker='^', label=f'{tipo} - Mediana de Três')

# Configurando o gráfico
plt.title('Desempenho do Quicksort com Diferentes Estratégias de Pivô')
plt.xlabel('Tamanho do Array')
plt.ylabel('Tempo de Execução (ns)')
plt.xscale('log')
plt.yscale('log')
plt.xticks(tamanhos)
plt.legend()
plt.grid(True)

# Salva o gráfico como imagem
plt.savefig('desempenho_quicksort.png', bbox_inches='tight')
plt.close()

print("Gráfico salvo como 'desempenho_quicksort.png'")
