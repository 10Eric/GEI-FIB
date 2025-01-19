// (c) FIBerHub, https://fiberhub.tk

#include "CacheSim.h"
//#include <stdio.h>

/* Posa aqui les teves estructures de dades globals
 * per mantenir la informacio necesaria de la cache
 * */

struct conj {
    int tag[2];
    int last_used;
};

struct conj cache[64];

//int hit_num = 0;
//int miss_num = 0;

/* La rutina init_cache es cridada pel programa principal per
 * inicialitzar la cache.
 * La cache es inicialitzada al comen�ar cada un dels tests.
 * */
void init_cache ()
{
    totaltime=0.0;
	/* Escriu aqui el teu codi */
    for (int i = 0; i < 64; i++) {
        cache[i].tag[0] = -1;
        cache[i].tag[1] = -1;
        cache[i].last_used = 1;
    }
}

/* La rutina reference es cridada per cada referencia a simular */
void reference (unsigned int address)
{
	unsigned int byte;
	unsigned int bloque_m;
	unsigned int conj_mc;
	unsigned int via_mc;
	unsigned int tag;
	unsigned int miss;	   // boolea que ens indica si es miss
	unsigned int replacement;  // boolea que indica si es reempla�a una linia valida
	unsigned int tag_out;	   // TAG de la linia reempla�ada
	float t1,t2;		// Variables per mesurar el temps (NO modificar)

	t1=GetTime();
	/* Escriu aqui el teu codi */
    byte = address%32;
	bloque_m = address/32;
	conj_mc = bloque_m%64;
	tag = bloque_m/64;
	miss = 0;
	replacement = 0;
	tag_out = 0;

    if (cache[conj_mc].tag[0] == tag) {
        //++hit_num;
        cache[conj_mc].last_used = via_mc = 0;
	} else if (cache[conj_mc].tag[1] == tag) {
        //++hit_num;
        cache[conj_mc].last_used = via_mc = 1;
    } else {
        //++miss_num;
        miss = 1;
		if (cache[conj_mc].last_used == 0) {
			if (cache[conj_mc].tag[1] != -1) {
				replacement = true;
				tag_out = cache[conj_mc].tag[1];
			}
			cache[conj_mc].tag[1] = tag;
			cache[conj_mc].last_used = via_mc = 1;
		} else {
			if (cache[conj_mc].tag[0] != -1) {
				replacement = true;
				tag_out = cache[conj_mc].tag[0];
			}
			cache[conj_mc].tag[0] = tag;
			cache[conj_mc].last_used = via_mc = 0;
		}
	}

	/* La funcio test_and_print escriu el resultat de la teva simulacio
	 * per pantalla (si s'escau) i comproba si hi ha algun error
	 * per la referencia actual. Tamb� mesurem el temps d'execuci�
	 * */
	t2=GetTime();
	totaltime+=t2-t1;
	test_and_print2 (address, byte, bloque_m, conj_mc, via_mc, tag,
			miss, replacement, tag_out);
}

/* La rutina final es cridada al final de la simulacio */
void final ()
{
 	/* Escriu aqui el teu codi */
    //printf("Aciertos: %i\nFallos: %i\n", hit_num, miss_num);
}
