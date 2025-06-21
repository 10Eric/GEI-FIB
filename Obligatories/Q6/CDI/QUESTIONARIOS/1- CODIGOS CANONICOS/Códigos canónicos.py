def codificar_mensaje(mensaje, codificacion):
    # Crear un diccionario para mapear cada carácter a su código
    codigo_dict = {caracter: str(codigo) for caracter, codigo in codificacion}
    
    # Codificar el mensaje
    mensaje_codificado = ''.join([codigo_dict.get(caracter, '') for caracter in mensaje])
    
    return mensaje_codificado

# Mensaje a codificar
mensaje = "The Island of Doctor Moreau, by H. G. Wells [...] Well? said he in the doorway. You were just beginning to tell me. I told him my name, Edward Prendick, and how I had taken to Natural History as a relief from the dulness of my comfortable independence. He seemed interested in this. I've done some science myself. I did my Biology at University College,--getting out the ovary of the earthworm and the radula of the snail, and all that. Lord! It's ten years ago. But go on! go on! tell me about the boat. He was evidently satisfied with the frankness of my story, which I told in concise sentences enough, for I felt horribly weak; and when it was finished he reverted at once to the topic of Natural History and his own biological studies. He began to question me closely about Tottenham Court Road and Gower Street."
# Codificación proporcionada
codificacion = [(' ', 2), ('!', 8), ("'", 9), (',', 7), ('-', 9), ('.', 6), (';', 10), ('?', 10), ('B', 9), ('C', 9), ('D', 10), ('E', 10), ('G', 9), ('H', 8), ('I', 7), ('L', 10), ('M', 10), ('N', 9), ('P', 10), ('R', 10), ('S', 10), ('T', 9), ('U', 10), ('W', 9), ('Y', 10), ('[', 10), (']', 11), ('a', 4), ('b', 7), ('c', 6), ('d', 5), ('e', 3), ('f', 6), ('g', 7), ('h', 5), ('i', 5), ('j', 11), ('k', 8), ('l', 5), ('m', 6), ('n', 4), ('o', 4), ('p', 9), ('q', 10), ('r', 5), ('s', 5), ('t', 4), ('u', 6), ('v', 8), ('w', 6), ('y', 6)]

# Codificar el mensaje
mensaje_codificado = codificar_mensaje(mensaje, codificacion)

print("Mensaje codificado:", mensaje_codificado)