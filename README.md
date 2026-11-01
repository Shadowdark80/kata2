# Kata 2 - Entrada, Salida y Transformación de Datos

## Objetivo de la entrega
El objetivo de esta segunda kata es dominar las operaciones de entrada/salida (I/O) en Java y asentar la **Arquitectura Hexagonal**. El propósito principal es cargar datos estructurados (CSV) desde distintos orígenes (`InputStream`), transformarlos mediante un cálculo significativo (promediar salarios por cada punto anotado de jugadores de la NBA) y generar un reporte de salida reproducible (`OutputStream`), todo ello sin acoplar la lógica de negocio al origen o destino concreto de los datos.

## ⚙️ Cómo compilar y ejecutar
Para probar este proyecto desde cero en cualquier máquina:

1. Clona el repositorio:
   - Abre la terminal (Alt + F12) y escribe esto: git clone https://github.com/Shadowdark80/kata2
   
   **Instrucciones de ejecución**
  1. Abre la carpeta raíz del proyecto en IntelliJ IDEA.
  2. Ve a File -> Project Structure y asegúrate de que el Project SDK y el Language Level están configurados en Java 26.
  3. Haz clic derecho sobre la clase Main.java y selecciona Run ‘Main.main()’.

Nota importante: El archivo nba_players_full_stats.csv debe estar situado directamente en la raíz del proyecto para que se detecte como recurso local correctamente.

**Dependencias y versión de JDK**
Lenguaje y versión: Java Development Kit (JDK) 26.

Gestor de dependencias: Maven (pom.xml ajustado explícitamente a <maven.compiler.source>26</maven.compiler.source>).

Dependencias externas: Ninguna. Se emplean exclusivamente las bibliotecas nativas de Java (I/O, Util y Streams API) para garantizar control y ligereza en los adaptadores.

**Estructura de la entrega y clases principales**

El proyecto implementa la abstracción de Puertos y Adaptadores (Arquitectura Hexagonal):

Dominio (domain.model):
Player -> Record inmutable que representa la entidad del jugador de la NBA.

Casos de Uso (usecases):
SalaryPerPoint -> Realiza la lógica matemática, devolviendo un Map<String, Double> con las ganancias por punto. No conoce ficheros, formatos ni impresoras.

Puertos de Entrada/Salida (ports.in / ports.out):
PlayerDeserializer -> Contrato para convertir cualquier Reader en una lista de entidades.

DataSerializer: Contrato genérico para empujar el cálculo final hacia un OutputStream.

Adaptadores (infrastructure.adapters):

PlayerLoader -> Implementación de la entrada que parsea el CSV saltándose la cabecera y controlando errores como líneas en blanco.

StreamSerializer y CsvStreamSerializer -> Convierten el resultado matemático a texto o formato CSV y lo transforman al OutputStream delegando el formateo en métodos privados auxiliares (Separation of Concerns).

ReaderFactory -> Orquesta y abstrae la generación de flujos.

Main: Conecta las implementaciones y demuestra la capacidad multiplataforma (lee memoria/archivo e imprime consola/fichero).

**Repetición de la kata y variaciones introducidas**

Para ganar soltura técnica, la kata se ha repetido tres veces con el mismo alcance principal, practicar la arquitectura hexagonal y familiarizarse y coger soltura con las InputStreams y OutputStreams 

Variación introducida: Se implementó una clase ReaderFactory que permite instanciar el flujo de entrada desde un archivo físico (origen 1) o desde un String en memoria (origen 2), enviando posteriormente los datos tanto por consola como persistiendo los resultados a disco duro en un nuevo archivo local nba_salary_per_point.csv.

**Evidencias de verificación o pruebas realizadas**

El programa fue verificado directamente inyectando los distintos Streams desde la clase Main. Se comprobó:

Resistencia a datos sucios: Al leer la cabecera inicial o una línea vacía desde el Stream en memoria, el sistema no crashea (evita el NumberFormatException), sino que lanza un error controlado por consola ("Fila ignorada...") y continúa procesando el resto de jugadores válidos.
Reutilización del cálculo: El mapa earnings procesado una sola vez por el caso de uso es consumido con éxito por dos serializadores diferentes de forma consecutiva (demostrando que el flujo no se ha cerrado ni acoplado).
Generación de archivo exitosa: Al finalizar la ejecución, se crea un fichero válido nba_salary_per_point.csv con cabecera y el resultado mapeado.

**Vídeo explicativo**
