import os
import csv
import sys
from collections import defaultdict

def procesar_archivos(directorio):
    # Diccionario para almacenar los tiempos agrupados por tamaño
    tiempos_por_size = defaultdict(list)

    # Recorre todos los archivos en el directorio dado
    for filename in os.listdir(directorio):
        if filename.endswith("_tiempos.csv"):  # Verificar formato del archivo
            ruta_archivo = os.path.join(directorio, filename)
            
            # Extraer el tamaño de datos desde el nombre del archivo
            try:
                size = int(filename.split('_size')[1].split('_')[0])
            except IndexError:
                print(f"Formato de archivo incorrecto: {filename}")
                continue

            # Leer el archivo CSV y eliminar la segunda columna
            filas_modificadas = []
            with open(ruta_archivo, 'r') as file:
                reader = csv.reader(file)
                for row in reader:
                    if row:  # Si la fila no está vacía
                        tiempo = row[0].replace("ms", "")  # Elimina 'ms' si está presente
                        filas_modificadas.append([tiempo])  # Guardar solo la primera columna
                        tiempos_por_size[size].append(float(tiempo))  # Almacena para calcular media

            # Sobrescribir el archivo CSV sin la segunda columna
            with open(ruta_archivo, 'w', newline='') as file:
                writer = csv.writer(file)
                writer.writerows(filas_modificadas)  # Escribe las filas sin la segunda columna

    # Calcular y mostrar las medias para cada tamaño
    for size, tiempos in tiempos_por_size.items():
        if tiempos:
            media = sum(tiempos) / len(tiempos)
            print(f"Media de tiempos para tamaño {size}: {media:.2f} ms")

if __name__ == "__main__":
    # Asegurarse de que se pase el directorio como argumento
    if len(sys.argv) != 2:
        print("Uso: python script.py <directorio>")
    else:
        directorio = sys.argv[1]
        procesar_archivos(directorio)
