import heapq
from heapq import heappush, heappop, heapify
from collections import defaultdict

def calcular_frecuencia(texto):
    freq_dict = {}
    for caracter in texto:
        freq_dict[caracter] = freq_dict.get(caracter, 0) + 1
    return freq_dict

class NodoHuffman:
    def __init__(self, simbolo, probabilidad):
        self.simbolo = simbolo
        self.probabilidad = probabilidad
        self.izquierda = None
        self.derecha = None

    
    def __lt__(self, otro):
        return self.probabilidad < otro.probabilidad
    
    def __eq__(self, otro):
        return self.probabilidad == otro.probabilidad

def construir_arbol_huffman(probabilidades, valor):
    heap = []
    if valor:
        heap = [NodoHuffman(byte, frequency) for byte, frequency in probabilidades.items()]

    else:
        heap = [NodoHuffman(byte, frequency) for byte, frequency in enumerate(probabilidades)]
    
    heapify(heap)

    while len(heap) > 1:
        nodo1 = heapq.heappop(heap)
        nodo2 = heapq.heappop(heap)
        nodo_fusionado = NodoHuffman(None, nodo1.probabilidad + nodo2.probabilidad)
        nodo_fusionado.izquierda = nodo1
        nodo_fusionado.derecha = nodo2
        heapq.heappush(heap, nodo_fusionado)
    return heap[0]

def caracter(texto):
    freq_dict = set()
    for caracter in texto:
        freq_dict.add(caracter)
    
    freq = sorted(freq_dict)


    return freq

def longitud_medias(codigos,caracteres,texto):
    longitud_media = 0
    frecuencia = calcular_frecuencia(texto)
    total_simbolos = sum(frecuencia.values())
    probabilidades = {simbolo: frecuencia[simbolo] / total_simbolos for simbolo in frecuencia}
    for i in range(len(caracteres)):
        longitud_media += (len(codigos[i]) * probabilidades[caracteres[i]])
    return longitud_media

def generar_codigos(nodo, prefijo="", codigos={}):
    if nodo is not None:
        if nodo.simbolo is not None:
            codigos[nodo.simbolo] = prefijo
            return
        generar_codigos(nodo.izquierda, prefijo + "0", codigos)
        generar_codigos(nodo.derecha, prefijo + "1", codigos)
    else: return
    return codigos

def calcular_longitud_media(probabilidades, codigos, valor):
        if (valor): return sum(probabilidades[simbolo] * len(codigos[simbolo]) for simbolo in probabilidades)
        return sum(probabilidades[i] * len(codigos[i]) for i in range(len(probabilidades)))

def calcular_longitud_media_texto(texto):
    frecuencia = calcular_frecuencia(texto)
    total_simbolos = sum(frecuencia.values())
    probabilidades = {simbolo: frecuencia[simbolo] / total_simbolos for simbolo in frecuencia}
    arbol_huffman = construir_arbol_huffman(probabilidades,True)
    codigos = generar_codigos(arbol_huffman)
    return calcular_longitud_media(probabilidades, codigos,True), codigos

#PREGUNTA 2 !!!!!
texto = ("The Invisible Man, by H. G. Wells. [...] One wonders what his state of mind may have been during that time, and what plans he devised. No doubt he was almost ecstatically exasperated by Kemp's treachery, and though we may be able to understand the motives that led to that deceit, we may still imagine and even sympathise a little with the fury the attempted surprise must have occasioned. Perhaps something of the stunned astonishment of his Oxford Street experiences may have returned to him, for he had evidently counted on Kemp's co-operation in his brutal dream of a terrorised world. At any rate he vanished from human ken about midday, and no living witness can tell what he did until about half-past two. It was a fortunate thing, perhaps, for humanity, but for him it was a fatal inaction.")  
longitud_media,codigos_texto = calcular_longitud_media_texto(texto)    
print("Longitud media del código de Huffman:", longitud_media)

#PREGUNTA 4 !!!!!
probabilidades = [3/55, 1/11, 12/55, 3/11, 4/11]
arbol_huffman = construir_arbol_huffman(probabilidades, False)
codigos = generar_codigos(arbol_huffman)
longitud_media_probabilidades = calcular_longitud_media(probabilidades, codigos,False)
print("Longitud media del código de Huffman basado en probabilidades dadas:", longitud_media_probabilidades)

#PREGUNTA 3 !!!!!
texto = ("The Island of Doctor Moreau, by H. G. Wells. [...] The sky was clear, the sun midway down the western sky; long waves, capped by the breeze with froth, were running with us. We went past the steersman to the taffrail, and saw the water come foaming under the stern and the bubbles go dancing and vanishing in her wake. I turned and surveyed the unsavoury length of the ship. Is this an ocean menagerie? said I. Looks like it, said Montgomery. What are these beasts for? Merchandise, curios? Does the captain think he is going to sell them somewhere in the South Seas? It looks like it, doesn't it? said Montgomery, and turned towards the wake again.")  
longitud_media,codigos_texto = calcular_longitud_media_texto(texto)  
cara = caracter(texto)
cod = ['000', '11111010', '1101110', '10100', '1101111', '1110000', '1110001', '1110010', '11111011', '1110011', '111111110', '1110100', '1110101', '11111100', '1110110', '1110111', '1111000', '0100', '11111101', '1111001', '10101', '001', '110010', '110011', '0101', '10110', '110100', '110101', '1111010', '0110', '0111', '111111111', '10111', '1000', '1001', '110110', '11111110', '11000', '1111011', '1111100']
long = longitud_medias(cod,cara,texto)
print(longitud_media == long)
cod = ['000', '111111000', '110000', '110001', '111111001', '1110110', '11111000', '111111010', '111111011', '1110111', '111111100', '1111000', '11111001', '11111010', '11111011', '111111101', '111111110', '0100', '110010', '110011', '10110', '001', '1111001', '110100', '0101', '0110', '110101', '110110', '110111', '0111', '1000', '1111010', '10111', '1001', '1010', '111000', '1111011', '111001', '111010', '111111111']
long2 = longitud_medias(cod,cara,texto)
print(longitud_media == long2)
cod = ['00', '1110010', '1110011', '110010', '1110100', '11111000', '1111111110', '111111010', '111111011', '1110101', '111111100', '1110110', '11111001', '1111111111', '111111101', '111111110', '11111010', '0110', '1110111', '110011', '10100', '010', '1111000', '110100', '10101', '10110', '1111001', '110101', '110110', '0111', '10111', '1111010', '11000', '1000', '1001', '110111', '11111011', '111000', '1111011', '11111100']
long3 = longitud_medias(cod,cara,texto)
print(longitud_media == long3)



#PREGUNTA 5 !!!!!
texto = ("The Invisible Man, by H. G. Wells. [...] They're boots, anyhow, said the Voice. They are--charity boots, said Mr. Thomas Marvel, with his head on one side regarding them distastefully; and which is the ugliest pair in the whole blessed universe, I'm darned if I know! H'm, said the Voice. I've worn worse--in fact, I've worn none. But none so owdacious ugly--if you'll allow the expression. I've been cadging boots--in particular--for days. Because I was sick of _them_. They're sound enough, of course. But a gentleman on tramp sees such a thundering lot of his boots. And if you'll believe me, I've raised nothing in the whole blessed country, try as I would, but _them_. Look at 'em! And a good country for boots, too, in a general way. But it's just my promiscuous luck. I've got my boots in this country ten years or more. And then they treat you like this.")  
longitud_media,codigos_texto = calcular_longitud_media_texto(texto)    
print("Longitud media del código de Huffman:", longitud_media)

