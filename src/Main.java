/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut3_ta1;

public class Main {

    public static void main(String[] args) {

        // Crear una tabla de tipo THash e insertar las claves del archivo "claves_insertar.txt"

        // Buscar en la tabla creada anteriormente las claves indicadas en el archivo "claves_buscar.txt"
        THash tablaHash = new THash(250);
        String[] leerInsertar = ManejadorArchivosGenerico.leerArchivo("src/claves_insertar.txt");
        String[] leerBuscar = ManejadorArchivosGenerico.leerArchivo("src/claves_buscar.txt");
        int busquedasExitosas = 0;
        int busquedasFracasadas = 0;
        int comparacionesTotalesExitosas= 0;
        int comparacionesTotalesFracasadas= 0;

        for(String str : leerInsertar) {
            tablaHash.insertar(Integer.parseInt(str));
        }
        for(String str : leerBuscar) {
            int comparaciones = tablaHash.buscar(Integer.parseInt(str));
            if(comparaciones != 1) { //Comparaciónes exitosas
                comparacionesTotalesExitosas += comparaciones;
                busquedasExitosas++;
            } else {
                comparacionesTotalesFracasadas += comparaciones;
                busquedasFracasadas++;
            }
        }
        System.out.println(busquedasExitosas);
        System.out.println(busquedasFracasadas);

        double promedioComparacionesExitosas = 0;

        if(busquedasExitosas != 0) {
            promedioComparacionesExitosas = (double) comparacionesTotalesExitosas / busquedasExitosas;
        }

        double promedioComparacionesFallidas = 0;
        if(busquedasFracasadas != 0) {
            promedioComparacionesFallidas = (double) comparacionesTotalesFracasadas / busquedasFracasadas;
        }
        System.out.println("Comparaciónes buscar promedio Exitosas" + promedioComparacionesExitosas);
        System.out.println("Comparaciónes buscar promedio Fallidas" + promedioComparacionesFallidas);

    }

}