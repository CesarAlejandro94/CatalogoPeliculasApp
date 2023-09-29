package servicio;

import dominio.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasLista implements IServicioPeliculas{
    private final List<Pelicula> peliculas;
    public ServicioPeliculasLista(){
        this.peliculas = new ArrayList<>();
    }
    @Override
    public void listarPeliculas() {
        System.out.println("Listado de peliculas");
        peliculas.forEach(System.out::println);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        System.out.println("Se agrego la pelicula: " + pelicula);

    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        //Regresa el indice de la pelicula encontrada
        var indice = peliculas.indexOf(pelicula);
        if (indice == -1){
            System.out.println("La pelicula no fue encontrada");
        }else {
            System.out.println("Pelicula encontrada en el indice: " + indice);
        }
    }

    public static void main(String[] args) {
        //Creamos algunos objetos del tipo pelicula
        var pelicula = new Pelicula("barman");
        var pelicula2 = new Pelicula("superman");
        //Creamos el servicio(Patron de diseño Service)
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        //Agregamos las peliculas a la lista
        servicioPeliculas.agregarPelicula(pelicula);
        servicioPeliculas.agregarPelicula(pelicula2);
        //Listamos peliculas
        servicioPeliculas.listarPeliculas();
        //Buscamos una pelicula
        //Se debe implementar el metodo equals y hashcode
        servicioPeliculas.buscarPelicula(pelicula2);
    }
}
