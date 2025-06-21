# CompressCDI

Herramienta de compresión sin pérdidas de ficheros de alturas, diseñada para la asignatura CDI.

## Archivos

- `compress.cdi` — Script bash principal. Invoca la compresión o descompresión mediante `compress.src`.
- `compress.src` — Script Python que implementa la lógica de compresión y descompresión.

## Requisitos

- Python 3.8 o superior
- Dependencias estándar de Python (no requiere bibliotecas externas)
- Bash (para ejecutar el script `compress.cdi` en sistemas Unix-like)

## Instalación y permisos

1. Asegúrate de tener Python 3 instalado:
   ```bash
   python3 --version
   ```

2. Da permisos de ejecución al script principal:
   ```bash
   chmod +x compress.cdi
   ```

## Uso

### Comprimir un fichero:

```bash
./compress.cdi -c <archivo_entrada> <archivo_salida>
```

Ejemplo:
```bash
./compress.cdi -c alturas.txt alturas.comp
```

### Descomprimir un fichero:

```bash
./compress.cdi -d <archivo_entrada> <archivo_salida>
```

Ejemplo:
```bash
./compress.cdi -d alturas.comp alturas_recuperadas.txt
```

## Validación

Tras descomprimir, se recomienda comparar el fichero original y el recuperado con `diff` o `cmp` para validar la integridad:

```bash
diff alturas.txt alturas_recuperadas.txt
```

---
