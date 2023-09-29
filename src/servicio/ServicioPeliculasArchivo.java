package servicio;

import dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas {
    private final String NOMBRE_ARCHIVO = "peliculas.txt";
    public ServicioPeliculasArchivo(){
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //Si ya existe el archibo no se vuelve a crear
            if (archivo.exists()){
                System.out.println("El archivo ya existe");
            }else {
                //Si aun no existe el archivo entonces
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo");
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error" + e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {
        //Volvemos a abrir el archivo
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            System.out.println("Listado de peliculas");
            //Abrimos el archivo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            //Leemos la linea del archivo
            String linea;
            linea = entrada.readLine();
            //Leemos todas las lineas disponibles
            while (linea != null){
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                linea = entrada.readLine();
            }
            //Cerramos el archivo
            entrada.close();
        }catch (Exception e){
            System.out.println("Ocurrio un error: " + e.getMessage());
        }

    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //revisamos si existe el archivo
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            //Agregamos la pelicula (toString)
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agrego al archivo: " + pelicula);
        }catch (Exception e){
            System.out.println("Ocurrio un error al agregar peli" + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //Abrimos el archivo para lectura linea a linea
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var peliculaBuscar = pelicula.getNombre();
            //buscamos linea por linea
            while (lineaTexto != null){
                //buscamos sin importar minusculas o mayusculas
                if (peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                    encontrada = true;
                    break;
                }
                //leemos de nuevo la sig linea
                lineaTexto = entrada.readLine();
                indice++;
            }
            //Imprimimos los resultados de la busqueda
            if (encontrada){
                System.out.println("Pelicula " + lineaTexto + " encontrada - linea" + indice);
            }else{
                System.out.println("No se encontro la pelicula: "+pelicula.getNombre());
            }
            entrada.close();

        }catch(Exception e){
            System.out.println("Ocurrio un error: " + e.getMessage());
        }
    }
}
