import os
import struct
from PIL import Image
import numpy as np
import bitstring
import math

def quantize_block(block, bits=4):
    # Definir el número de niveles de cuantización
    levels = 2 ** bits  # 16 niveles para 4 bits
    
    # Encontrar el valor mínimo y máximo en el bloque
    min_val = min(min(row) for row in block)
    max_val = max(max(row) for row in block)
    
    # Evitar divisiones por cero
    if max_val == min_val:
        return [[min_val, max_val], [[0] * len(row) for row in block]]
    
    # Definir el tamaño del intervalo de cuantización
    step_size = (max_val - min_val +1) / (levels)
    
    # Aplicar la cuantización con redondeo correcto
    quantized_block = [[math.floor((value - min_val) / step_size) for value in row] for row in block]
    
    return [[min_val, max_val], quantized_block]


def calcular_bits_por_pixel(n=3584, m=4608, niveles_gris=256, 
                            tamaño_bloque=8, niveles_cuant_esc=2, entradas_diccionario=128):
    total_pixeles = n * m
    pixeles_por_bloque = tamaño_bloque * tamaño_bloque
    total_bloques = total_pixeles // pixeles_por_bloque

    # ----- Cuantización Escalar Adaptativa -----
    # Por bloque: 8 bits mínimo + 8 bits máximo + (b * número de píxeles)
    b_esc = int(np.log2(niveles_cuant_esc))
    bits_por_bloque_esc = 8 + 8 + b_esc * pixeles_por_bloque
    bits_totales_esc = total_bloques * bits_por_bloque_esc
    bpp_esc = bits_totales_esc / total_pixeles

    # ----- Cuantización Vectorial -----
    # Diccionario ocupa: entradas * píxeles por bloque * 8 bits
    bits_diccionario = entradas_diccionario * pixeles_por_bloque * 8
    b_vec = int(np.ceil(np.log2(entradas_diccionario)))
    bits_indices = total_bloques * b_vec
    bits_totales_vec = bits_diccionario + bits_indices
    bpp_vec = bits_totales_vec / total_pixeles

    return round(bpp_esc, 5), round(bpp_vec, 5)


def binario_a_imagen(data_binaria, output_path):
    # Paso 1: Leer los primeros 48 bits de cabecera (n=16b, m=16b, n_b=8b, b=8b)
    bits = bitstring.BitStream(data_binaria)
    
    n = bits.read('uint:16')
    m = bits.read('uint:16')
    n_b = bits.read('uint:8')
    b = bits.read('uint:8')
    
    bloques_por_fila = m // n_b
    bloques_por_columna = n // n_b
    total_bloques = bloques_por_fila * bloques_por_columna

    imagen = np.zeros((n, m), dtype=np.uint8)

    for bloque_idx in range(total_bloques):
        # Leer mínimo y máximo
        minimo = bits.read('uint:8')
        maximo = bits.read('uint:8')

        # Obtener los niveles de reconstrucción (centros de cuantización)
        niveles = np.linspace(minimo, maximo, num=(2**b)+1)
        centros = ((niveles[:-1] + niveles[1:]) / 2).astype(np.uint8)

        bloque = np.zeros((n_b, n_b), dtype=np.uint8)
        for i in range(n_b):
            for j in range(n_b):
                if bits.pos + b > bits.len:
                    indice = 0  # Rellenar con 0 si faltan bits
                else:
                    indice = bits.read(f'uint:{b}')
                bloque[i, j] = centros[indice]

        # Insertar el bloque reconstruido en la imagen
        fila = (bloque_idx // bloques_por_fila) * n_b
        columna = (bloque_idx % bloques_por_fila) * n_b
        imagen[fila:fila+n_b, columna:columna+n_b] = bloque

    # Crear imagen y guardarla como PNG
    imagen_pil = Image.fromarray(imagen)
    imagen_pil.save(output_path)
    print(f"Imagen guardada como '{output_path}'.")

    return imagen

def binario_a_imagen_vectorial(data_binaria, output_path):
    bits = bitstring.BitStream(data_binaria)

    # Leer cabecera
    n = bits.read('uint:16')
    m = bits.read('uint:16')
    n_b = bits.read('uint:8')
    e_diccionario = bits.read('uint:16')

    bloques_por_fila = m // n_b
    bloques_por_columna = n // n_b
    total_bloques = bloques_por_fila * bloques_por_columna

    # Leer diccionario
    diccionario = []
    for _ in range(e_diccionario):
        bloque = np.zeros((n_b, n_b), dtype=np.uint8)
        for i in range(n_b):
            for j in range(n_b):
                bloque[i, j] = bits.read('uint:8')
        diccionario.append(bloque)

    # Calcular número de bits necesarios para representar los índices
    bits_por_indice = int(np.ceil(np.log2(e_diccionario)))

    # Leer índices
    indices = []
    for _ in range(total_bloques):
        if bits.pos + bits_por_indice > bits.len:
            break
        idx = bits.read(f'uint:{bits_por_indice}')
        indices.append(idx)

    # Reconstruir imagen
    imagen = np.zeros((n, m), dtype=np.uint8)
    for idx_bloque, indice_dic in enumerate(indices):
        fila = (idx_bloque // bloques_por_fila) * n_b
        columna = (idx_bloque % bloques_por_fila) * n_b
        imagen[fila:fila+n_b, columna:columna+n_b] = diccionario[indice_dic]

    imagen_pil = Image.fromarray(imagen)
    imagen_pil.save(output_path)
    print(f"Imagen reconstruida guardada en: {output_path}")

    return imagen




#PREGUNTA 1!!!

# Bloque de la imagen
gray_block = [[208, 207, 181, 153, 150, 169, 205, 223], [225, 226, 222, 186, 150, 184, 218, 225], [226, 226, 225, 220, 207, 219, 225, 224], [228, 223, 225, 225, 225, 225, 223, 225], [227, 222, 224, 227, 226, 226, 225, 226], [225, 224, 226, 225, 226, 226, 225, 225], [225, 224, 225, 225, 225, 225, 225, 224], [224, 225, 225, 225, 224, 226, 227, 224]]

# Cuantizar el bloque
quantized_result = quantize_block(gray_block)

# Mostrar resultado con formato claro
print("Resultado de cuantización:")
print(quantized_result)
print("")

#PREGUNTA 2!!!
bpp_escalar, bpp_vectorial = calcular_bits_por_pixel()
print("Bits por píxel (cuantización escalar):", bpp_escalar)
print("Bits por píxel (cuantización vectorial):", bpp_vectorial)
print("")


#PREGUNTA 3!!!
# Ejemplo de uso:
with open("/home/ericdiezapolo/Escritorio/CDI/descarga", "rb") as f:
    data = f.read()

output_path = "/home/ericdiezapolo/Escritorio/CDI/Imágenes para responder los  cuestionarios-20250401/encoded/imagen_reconstruida.png"
binario_a_imagen(data, output_path)


#PREGUNTA 4!!!
with open("/home/ericdiezapolo/Escritorio/CDI/descarga (1)", "rb") as f:
    data = f.read()

output_path = "/home/ericdiezapolo/Escritorio/CDI/Imágenes para responder los  cuestionarios-20250401/encoded/imagen_reconstruida2.png"
binario_a_imagen(data, output_path)

#PREGUNTA 5!!!
archivo_binario = "/home/ericdiezapolo/Escritorio/CDI/descarga (2)"
ruta_salida = "/home/ericdiezapolo/Escritorio/CDI/Imágenes para responder los  cuestionarios-20250401/encoded/imagen_vectorial.png"

# Leer binario y reconstruir imagen
with open(archivo_binario, "rb") as f:
    data = f.read()

binario_a_imagen_vectorial(data, ruta_salida)

#PREGUNTA 6!!!
archivo_binario = "/home/ericdiezapolo/Escritorio/CDI/descarga (3)"
ruta_salida = "/home/ericdiezapolo/Escritorio/CDI/Imágenes para responder los  cuestionarios-20250401/encoded/imagen_vectorial2.png"

# Leer binario y reconstruir imagen
with open(archivo_binario, "rb") as f:
    data = f.read()

binario_a_imagen_vectorial(data, ruta_salida)