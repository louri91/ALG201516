# Práctica 3. Problema de la Mochila 0/1
## Programación Dinámica

- **Datos del programa**:
  - n: número de objetos disponibles
  - M: capacidad de la mochila_voraz
  - p = (p<sub>1</sub>, p<sub>2</sub>, ..., p<sub>n</sub>) pesos de los objetos
  - b = (b<sub>1</sub>, b<sub>2</sub>, ..., b<sub>n</sub>) beneficio de los objetos

### Pasos a seguir para la resolución teórica del problema:

#### Paso 1) Obtener la descomposición recurrente.
Para realizar la descomposición recurrente del problema es necesario interpretarlo como un proceso de toma de decisiones, en el caso de este problema, debemos tomar la decisión de tomar o no tomar el objeto que se esté analizando en ese momento.

Una vez tomada una decisión particular sobre un objeto determinado, nos queda un problema de menor tamaño, es decir, con un objeto menos que analizar.

##### Decisión:

- **Coger el objeto k**: obtenemos un beneficio b<sub>k</sub>, pero a cambio, en nuestra mochila quedará menos espacio, p<sub>k</sub>
- **No coger el objeto k**: tendríamos el mismo problema pero con un objeto menos que analizar.

De este modo conseguimos descomponer el problema inicial en subproblemas de menor tamaño, en los cuales, únicamente tendremos un menor número de objetos por analizar y menos peso disponible en nuestra mochila.

##### Ecuación del problema:

Mochila(k, m:entero): entero.

Consideramos k los primeros k objetos de los n originales y m la capacidad de la mochila. Devolvería un entero con el beneficio total obtenido.

- ##### Definición de Mochila (k, m:entero): entero
  - **Si no cogemos el objeto k:**

    Mochila(k,m) = Mochila(k-1, m)

    > Si no elegimos llevar el objeto k, el beneficio que podemos obtener viene dado por los k-1 objetos restantes.
  - **Si cogemos el objeto k:**

    Mochila(k,m) = b<sub>k</sub> + Mochila(k-1, m-p<sub>k</sub>)

    > Si nos llevamos el objeto k, obtendremos un beneficio compuesto por el beneficio del objeto que nos hemos llevado b<sub>k</sub> MÁS el beneficio que obtendríamos de los k-1 objetos restantes. Sin olvidarnos que, en este caso, el peso de la mochila se ve reducido por p<sub>k</sub>
  - **Valor óptimo: (el que nos proporcione mayor beneficio)**

    Mochila(k,m) = max { Mochila(k-1, m), b<sub>k</sub> + Mochila(k-1, m-p<sub>k</sub>}

      > El valor óptimo que podremos obtener será el máximo entre tomar una decisión u otra.

- ##### Casos base de la ecuación de recurrencia:
  - Si **m = 0**, no se pueden incluir objetos: Mochila(k, 0) = 0
  > Si m = 0 significa que no tenemos un tamaño de mochila, o que la mochila tiene tamaño 0. No podemos llenarla en ningún caso.

  - Si **k = 0**, tampoco se pueden incluir objetos: Mochila(0, m) = 0
  > Si k = 0 significa que no tenemos objetos con los que llenar la mochila.

  - ¿Y si nos encontramos con **m < 0** ó **k < 0**? En este caso consideraremos que es -∞
  > Si m o k son negativos, el problema es irresoluble y por tanto, Mochila(k, m) = -∞

##### Resultado.
La siguiente ecuación obtiene la solución óptima del problema:

![alt text](https://github.com/louri91/ALG201516/raw/master/ecuacion.png "Resultado, ecuación de recurrencia")

Una vez tenemos la ecuación de recurrencia tenemos que pensar cómo vamos a aplicarla de forma ascendente. Para ello vamos a utilizar una tabla para guardar los resultados de los subproblemas. Comenzaremos a rellenar la tabla por los casos base.

#### Paso 2) Definición de las tablas y cómo rellenarlas.

##### Dimensiones y tamaño de la tabla

- Se define la tabla V para guardar los resultados de los subproblemas V[i,j] = Mochila(i,j)
- La solución del problema original es Mochila(n,M) y por tanto, la tabla debe ser:

~~~
  V: array[0..n, 0..M] de enteros
~~~
- Rellenamos la fila 0 y la columna 0 con los casos base de valor 0.
- Los valores que quedan fuera de la tabla son los casos base de valor -∞

##### Forma de rellenar las tablas.
- Inicializamos los casos base:
~~~
  V[i, 0] = 0;
  V[0, j] = 0;
~~~
- Rellenamos lo que resta:
~~~
  Para todo i desde 1 hasta n

    Para todo j desde 1 hasta M, aplicamos la ecuación:

      si j-pi < 0
        V[i,j] = V[i-1,j]
      sino
        V[i,j] = max(V[i-1,j], bi + V[i-1, j-pi])
      finsi
~~~
- El beneficio óptimo es V[n,M]

#### Paso 3) Recomponer la solución óptima.

- V[n, M] almacena el beneficio óptimo, pero tenemos que recomponer la solución para saber qué objetos son los que tenemos que llevar en la mochila. Necesitamos obtener la tupla solución (x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>n</sub>) utilizando V.

- Si partimos de la posición solución V[n, M], podemos analizar las decisiones que se han tomado para cada objeto i.

~~~

Si V[i,j] = V[i-1,j] -> xi = 0 // la solución no ha utilizado el objeto i

Si V[i,j] = V[i-1, j-pi] + bi -> xi = 1 // la solución sí ha utilizado el objeto i.

~~~

Podemos encontrarnos en el caso de que se cumplan las dos condiciones anteriores, esto quiere decir que hay más de una solución óptima y, por tanto, podemos elegir o no elegir el objeto i, la solución será correcta en ambos casos.

Si traducimos a pseudocódigo la recomposición de la solución:

~~~

j = P

para i = n, ..., 1 hacer
  si V[i,j] == V[i-1,j] entonces
    X[i] = 0
  sino
    X[i] = 1
    j = j-pi
  finsi
finpara

~~~
