
import math

def closest_power_of_two(n):
    # Encuentra el exponente más cercano usando logaritmo en base 2
    exponent = math.floor(math.log2(n))  # Parte entera del logaritmo
    lower_power = 2 ** exponent          # Potencia de 2 inferior más cercana
    higher_power = 2 ** (exponent + 1)   # Potencia de 2 superior más cercana

    if lower_power >= n:
        return exponent
    if higher_power >= n:
        return exponent + 1
    # Decide cuál es la más cercana
    if abs(n - lower_power) <= abs(n - higher_power):
        return exponent
    else:
        return exponent + 1

def closest_power_of_two2(n):
    # Encuentra el exponente más cercano usando logaritmo en base 2
    exponent = math.floor(math.log2(n))  # Parte entera del logaritmo
    lower_power = 2 ** exponent          # Potencia de 2 inferior más cercana
    higher_power = 2 ** (exponent + 1)   # Potencia de 2 superior más cercana

    if lower_power >= n:
        return pow(2, exponent)
    if higher_power >= n:
        return pow(2, exponent + 1)
    # Decide cuál es la más cercana
    if abs(n - lower_power) <= abs(n - higher_power):
        return pow(2, exponent)
    else:
        return pow(2, exponent+1)

def int_to_binary(n: int, bits: int) -> str:
    """
    Convierte un número entero a su representación binaria con un número específico de bits.
    Si el número es negativo, se usa la representación en complemento a dos.
    
    :param n: Número entero a convertir.
    :param bits: Número de bits de la representación binaria.
    :return: Cadena con la representación binaria del número.
    """
    if n >= 0:
        binary = format(n, f'0{bits}b')
    else:
        binary = format((1 << bits) + n, f'0{bits}b')
    
    # Aseguramos que el resultado tenga exactamente 'bits' bits
    return binary[-bits:]

def int_to_binary_no_bits(n: int) -> str:
    """
    Convierte un número entero a su representación binaria sin especificar un número de bits.
    
    :param n: Número entero a convertir.
    :return: Cadena con la representación binaria del número.
    """
    return bin(n)[2:] if n >= 0 else bin(n & (2**(n.bit_length() + 1) - 1))[2:]

def arithmetic_encoding(message, symbols, probabilities):
    # Crear los intervalos acumulativos
    intervals = {}
    low = 0.0
    for symbol, prob in zip(symbols, probabilities):
        intervals[symbol] = (low, low + prob)
        low += prob
    
    # Calcular el intervalo final
    low, high = 0.0, 1.0
    for symbol in message:
        range_width = high - low
        high = low + range_width * intervals[symbol][1]
        low = low + range_width * intervals[symbol][0]
    
    return low, high

def arithmetic_decoding(value, length, symbols, probabilities):
    # Crear los intervalos acumulativos
    intervals = {}
    low = 0.0
    for symbol, prob in zip(symbols, probabilities):
        intervals[symbol] = (low, low + prob)
        low += prob
    
    # Decodificar el mensaje
    message = ""
    for _ in range(length):
        for symbol, (s_low, s_high) in intervals.items():
            if s_low <= value < s_high:
                message += symbol
                range_width = s_high - s_low
                value = (value - s_low) / range_width
                break
    
    return message

def arithmetic_decoding_integer(binary_code, length, symbols, frequencies):
    total_freq = sum(frequencies)
    intervals = {}
    low = 0
    lo = 0
    pote = closest_power_of_two2(total_freq * 4)
    fr = 0
    for symbol, freq in zip(symbols, frequencies):
        fr += freq
        adjusted_size = int((fr / total_freq) * pote)
        high = lo + adjusted_size

        if high > pote:
            high = pote  # Evitar que sobrepase

        intervals[symbol] = (low, high)
        low = high
    
    message = ""
    code = int(binary_code[:closest_power_of_two(total_freq * 4)], 2)  # Tomar los bits necesarios
    min_val, max_val = 0, pote
    pending_bits = 0
    
    for _ in range(length):
        for symbol, (s_low, s_high) in intervals.items():
            if s_low <= code < s_high:
                message += symbol
                min_val, max_val = s_low, s_high
                break
        
        # Rescalado si es necesario
        while True:
            if max_val <= pote // 2:  # Caso E1: intervalo en [0, 1/2)
                min_val *= 2
                max_val *= 2
                if binary_code:
                    binary_code = binary_code[1:]
                    code = int(binary_code[:closest_power_of_two(total_freq * 4)], 2)
            
            elif min_val >= pote // 2:  # Caso E2: intervalo en [1/2, 1)
                min_val = 2 * (min_val - pote // 2)
                max_val = 2 * (max_val - pote // 2)
                if binary_code:
                   binary_code = binary_code[1:]
                   code = int(binary_code[:closest_power_of_two(total_freq * 4)], 2)
            
            elif pote // 4 <= min_val and max_val <= 3 * (pote // 4):  # Caso E3: esperar
                pending_bits += 1
                min_val = 2 * (min_val - pote // 4)
                max_val = 2 * (max_val - pote // 4)
                if binary_code:
                   binary_code = binary_code[1:]
                   r = binary_code[0]
                   if r == '1':
                       code = int(binary_code[:closest_power_of_two(total_freq * 4)], 2) - pote // 2
                   else:
                       code = int(binary_code[:closest_power_of_two(total_freq * 4)], 2) + pote // 2
            
            else:
                low = min_val
                lo = min_val
                fr = 0
                for symbol, freq in zip(symbols, frequencies):
                    fr += freq
                    adjusted_size = int((fr / total_freq) * (max_val - min_val))
                    high = lo + adjusted_size

                    if high > pote:
                        high = pote  # Evitar que sobrepase

                    intervals[symbol] = (low, high)
                    low = high
                break  # No se necesita más reescalado
    
    return message

def arithmetic_encoding_integer(message, symbols, frequencies):
    total_freq = sum(frequencies)
    pote = closest_power_of_two2(total_freq*4)
    bit = closest_power_of_two(total_freq*4)
    intervals = {}
    low = 0
    fr = 0
    lo = 0
    for symbol, freq in zip(symbols, frequencies):
        fr += freq
        adjusted_size = int((fr / total_freq) * pote)
        high = lo + adjusted_size

        if high > pote:
            high = pote  # Evitar que sobrepase

        intervals[symbol] = (low, high)
        low = high
    
    min_val, max_val = 0, pote
    pending_bits = 0
    binary_code = ""
    
    for symbol in message:
        s_low, s_high = intervals[symbol]
        max_val = s_high
        min_val = s_low
        
        while True:
            if max_val <= pote // 2:
                binary_code += "0"
                binary_code += "1" * pending_bits
                pending_bits = 0
                min_val *= 2
                max_val *= 2
            elif min_val >= pote // 2:
                binary_code += "1"
                binary_code += "0" * pending_bits
                pending_bits = 0
                min_val = 2 * (min_val - pote // 2)
                max_val = 2 * (max_val - pote // 2)
            elif pote // 4 <= min_val and max_val <= 3 * (pote // 4):
                pending_bits += 1
                min_val = 2 * (min_val - pote // 4)
                max_val = 2 * (max_val - pote // 4)
            else:
                low = min_val
                lo = min_val
                fr = 0
                for symbol, freq in zip(symbols, frequencies):
                    fr += freq
                    adjusted_size = int((fr / total_freq) * (max_val - min_val))
                    high = lo + adjusted_size

                    if high > pote:
                        high = pote  # Evitar que sobrepase

                    intervals[symbol] = (low, high)
                    low = high
                break
    
    if min_val <= pote // 4:
        binary_code += "01" + int_to_binary_no_bits(pending_bits)
    else :
        binary_code += "10" + int_to_binary_no_bits(pending_bits)
    
    e = int_to_binary(min_val, bit)
    binary_code += e
    return binary_code

#PREGUNTA 1 !!!!!
symbols1 = ['a', 'b', 'c', 'd']
probabilities1 = [0.3, 0.2, 0.3, 0.2]
message1 = "dcba"
low1, high1 = arithmetic_encoding(message1, symbols1, probabilities1)
print(f"El rango del mensaje '{message1}' es: [{low1}, {high1}]")

#PREGUNTA 2 !!!!!!
symbols2 = ['a', 'b', 'c', 'd']
probabilities2 = [0.15, 0.15, 0.1, 0.6]
value2 = 0.21015188
length2 = 5
decoded_message = arithmetic_decoding(value2, length2, symbols2, probabilities2)
print(f"El mensaje decodificado es: {decoded_message}")

#PREGUNTA 3 !!!!!!
binary_code = "00111111010010011010101111100000101000101111110010100001110011111110111010010110010001111000101101001110000001001101100100011101000010101100101011110011100000100111001011010001110011001011011001010000100111101010010010100111111001010011010110100110110100110000110010101011101110101110001011000011111101100000011010111010110101011000110101011010111011101010110001100111101100010111110101001100111111010111100100101101101001100101101111000011001011000101111000011100110110111011000111110100111011000101011100111100000101010000010110100011001101001000111100100101011010100100111011100010100011001010100000101000100101101001000001000000110010010001010100101000101011010010101011100011010111110101010111100011010000101101100111111001001111111101011000001011101011110100001111001010111001010100011110101000001011101001001001100011000011111111100010100010111111000000110110111000000001101000011110100001001111011101010010111110111000010011100011011100011001100010110010100100011101110111110111001010001010010000111011011111000110101000011001100100111110011000001111011001011011100011011001100000100010000101101101010110011101011100111001011111110011111010011101011001100010110101011111010101011001001001101100000011010100001000100100001010101100100110010001110011111000001001100011000110010010011101000011010001000101101001100100101000011000100110010010011001111100111001111001000001001001111100110110010101000111111110000000110000100000110111101110100000010110100100010011111000000001101111000011000100100101111000011111001010101000111111110111001001110011010111100101101001001011110111101011001100110010110111110001100111010110011011000110000010000110111011100010111111011111011101010111101011100001101110110001010011111111001110001100101010101110100100010101101110000011010101101111101100001000001100111010001110000100100101111111110000000111001000001001001111110101101110101101110000100010111001011010000100011010010101111101111101011011111110111001111000011101110000101001110010111110011111001011011010000000000110110011111110101010100100111011000000011110111110110000001001101101010010010001010001001001011010101011011111011110000001010001010100010111010101000100100111110000010101000000100111101010111100001001100100010100011111000001000101001111101010101111100000111000100101111010101011001111010111110011111001000011100111000011011111100001100101101100100010100011110100110010101101110111001010111101011001010010100000110010010010110000011111001001100010100110101110111010100100101111111100001111101111111111001110010100100011110011101111110011000100100000111011110100101010001110111010010000001110000011010010101101010001011100011011100011100101000011001010110110110010110110001010110101100000000001010000000110001010100110010110100011100001000111111001100111100010111010000000101011000001000011111111010001100110110010011110111000011001000000001100101100001001110111011110111001010000000"
symbols = [' ', "'", ',', '-', '.', 'B', 'D', 'G', 'H', 'I', 'L', 'M', 'T', 'W', '[', ']', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'y']
frequencies = [116, 2, 9, 3, 13, 1, 1, 1, 1, 8, 2, 4, 3, 2, 1, 1, 38, 3, 11, 21, 53, 13, 16, 25, 32, 3, 24, 11, 45, 40, 9, 28, 30, 42, 25, 3, 12, 5]
# Longitud del mensaje
length = 657
# Decodificar el mensaje
decoded_message = arithmetic_decoding_integer(binary_code, length, symbols, frequencies)
print(f"El mensaje decodificado es: \n{decoded_message}\n")

#PREGUNTA 4 !!!!!!
binary_code = "001111010011111111011101010010111011001111101111111101000010011110010001100101001101110010110000110010100000011000111111100000100101111111010111011100010100111010111100110101110011001001011100110111010011111000101111111001101001100111001011110010010011000001000100000011100001011100010101101010001000000010000111101100000011011111101010001011101011001110001110010101100101000110111011110111010101101011111101111111010010111100010011100101110100100101101001011101011100010010011101101101000001010101101011001011111000100101101111010000010100000000000110111000111101110110111111001000000001011101001100010000000111010001000001011110100100011010001101111011100111101111111010010011100010010111101000000001110000010000100111000001111111111101010111111000001100001000101111100011111101101000010101001011011010000101000000111111000100011010000000110000100000100111100111011010001111110110110010011010110101101100111101000011001101101000011001100011010110101001011010111011101110001111101001011000100000000011001000100011000111110101000001101111011111000001100001010000101111011100110110111101111011011011111100000110100000101101111011101111001110100100110000110010011000110010110111110110011000100101001011011111010111001001011100111000010010100010011100101000011001100111011110101100010101101110111011010000011100101111010100110011111100101000010001101111111111101101110011100010101001001101011001111010001001110100100001100110001100011011101101100111100001011110100010111001010010000001101101011011111000001110111010101011000110000111101111100100011110001011100111100010110000001111010100011010011011110111100100101101110101101100001010011011101011010110000001000110111100110000011100101011111000001001101111010000011011101101011001001011100100000111011000011101101100000010101111110110100010110100100011010010100100011000100100010001110000011011110001111010100000001101001010111111000110111110101111000101011100100110101111110000110110010000001101100100001011011101011000011100011011111111011111101010000101101000111100000011000001000011011010010101011011111101110111000111000000110100110111000011010000000010000010111111010011111001000110010100000111101010011111101010011000111100011101100100101011000111000110110011000111100000100110101110110001011011100110110110110011000001111111010010100000110001110000011101000000101000011111100100011111011101100100011010100101011011100110010010000110011011101111000110000111110110001001000000101101101011110100101000000011011111001100000101011010111001001010101000010001001001100001011110010010011011100000001010101110010010111010011000101111001110011100110000010000100100001010000111010011001110011111011000111100101011000110001101011010110010100111111110000110001100010010000000011011010111010000011111001100100110100000001001011010101101001000100010011000111101001101111000001001000010011111001001101100111101101001011011111101000110100010111101101100111011010111111111110011000011111111100111011100101111100111111101111011000010110000001100100111011011011010010010111101111000000110110001011010111110000010010000000"
symbols =  [' ', "'", ',', '-', '.', 'A', 'B', 'D', 'G', 'H', 'I', 'M', 'N', 'P', 'T', 'W', '[', ']', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
frequencies = [125, 1, 10, 1, 11, 1, 1, 1, 1, 4, 5, 3, 1, 1, 2, 1, 1, 1, 45, 10, 14, 34, 70, 9, 12, 39, 31, 2, 19, 8, 34, 35, 14, 1, 27, 33, 45, 12, 1, 14, 1, 16, 1]
# Longitud del mensaje
length = 698
# Decodificar el mensaje
decoded_message = arithmetic_decoding_integer(binary_code, length, symbols, frequencies)
print(f"El mensaje decodificado es: \n{decoded_message}\n")


#PREGUNTA 5 !!!!!!
message = "The Island of Doctor Moreau, by H. G. Wells. [...] The next most obvious deformity was in their faces, almost all of which were prognathous, malformed about the ears, with large and protuberant noses, very furry or very bristly hair, and often strangely-coloured or strangely-placed eyes. None could laugh, though the Ape-man had a chattering titter."
alphabet = [' ', ',', '-', '.', 'A', 'D', 'G', 'H', 'I', 'M', 'N', 'T', 'W', '[', ']', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y']
frequencies = [55, 7, 3, 8, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 23, 5, 7, 9, 30, 7, 7, 15, 10, 15, 6, 14, 24, 4, 24, 15, 23, 10, 3, 4, 1, 9]

encoded_message = arithmetic_encoding_integer(message, alphabet, frequencies)
print(f"Código binario (1000 bits): \n{encoded_message[:1000]}\n")



#PREGUNTA 6 !!!!!!
message2 = "The Island of Doctor Moreau, by H. G. Wells. [...] Walk by me, said I, nerving myself; and side by side we walked down the narrow way, taking little heed of the dim Things that peered at us out of the huts. None about the fire attempted to salute me. Most of them disregarded me, ostentatiously. I looked round for the Hyena-swine, but he was not there. Altogether, perhaps twenty of the Beast Folk squatted, staring into the fire or talking to one another."
alphabet2 = [' ', ',', '-', '.', ';', 'A', 'B', 'D', 'F', 'G', 'H', 'I', 'M', 'N', 'T', 'W', '[', ']', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'y']
frequencies2 = [83, 8, 1, 11, 1, 1, 1, 1, 1, 1, 2, 3, 2, 1, 2, 2, 1, 1, 24, 5, 1, 17, 49, 9, 7, 18, 16, 6, 14, 7, 20, 29, 4, 1, 18, 20, 42, 10, 1, 8, 8]

encoded_message2 = arithmetic_encoding_integer(message2, alphabet2, frequencies2)
print(f"Código binario (1000 bits): \n{encoded_message2[:1000]}\n")