package ut3_ta1;

import java.util.Arrays;

public class THash implements IHash {
    int capacidad;
    Integer[] tablaHash;
    public THash(int capacidad)  {
        if(capacidad <= 0) {
            throw new IllegalArgumentException("El tamaño de la tabla debe de ser mayor a 0");
        }
        this.capacidad = capacidad;
        this.tablaHash = new Integer[capacidad];
        Arrays.fill(this.tablaHash, null); //Incializamos todas las posiciónes de la tabla hash en null
    }

    //Encadenamiento abierto
    @Override
    public int buscar(int unaClave) {
        int i = 0;
        int comparaciones = 0;
        do {
            int j = funcionHashing((unaClave) + i) % capacidad; //Me aseguro que J siempre esté dentro de los límites de la capacidad
            comparaciones++;
            if(tablaHash[j] == null) { //Si encontramos un lugar vacío en la tabla hash, entonces el elemento que queremos encontrar no está presente
                return -1; //Retorno -1 para indicar que la comparación no fue exitosa
            } else  if(tablaHash[j] == unaClave){
                return comparaciones;
            } else {
                i = i + 1;
                comparaciones++;
            }
        } while ( i  < capacidad);

        return -1; //Cada vez que llega acá, quiere decir que la búsqueda fue sin éxito
    }

    @Override
    public int insertar(int unaClave) {
        int i = 0;
        int comparaciones = 0;
        do {
            int j = funcionHashing((unaClave) + i )% capacidad; //Me aseguro que J siempre esté dentro de los límites de la capacidad
            comparaciones++;
            if(tablaHash[j] == null) {
                this.tablaHash[j] = unaClave;
                return comparaciones; //Si encontramos un lugar vacío, insertamos la clave, y devolvemos las comparaciónes realizadas.
            } else {
                i = i + 1;
            }
        }
        while(i < capacidad);
        if( i > capacidad) {
            System.out.println("Sobrecarga de tabla de hash");
        }
        return comparaciones; //Si no hay espacio disponible, igual retornamos las comparaciónes que hicimos
    }


    /*
        Función esquema de división
        La función hashing va a ser igual a unaClave % un número primo mayor que  unaClave, para eso primero debemos corroborar
        si unaClave es primo, y después calcular el siguiente primo
     */
    @Override
    public int funcionHashing(int unaClave) {
        return unaClave % siguientePrimoMayorQueN(unaClave);
    }

    private boolean isPrime(int unaClave) {
        if(unaClave <= 1) {
            return false;
        }
        for(int i = 2; i < unaClave; i++) {
            if(unaClave % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int siguientePrimoMayorQueN(int unaClave) {
        unaClave++;
        if(unaClave % 2 == 0){ //No es primo, sigo buscando
            unaClave++;
        }
        for(; !isPrime(unaClave); unaClave += 2) {

        }
        return unaClave;
    }

}