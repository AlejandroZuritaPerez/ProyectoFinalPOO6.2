/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblioteca;

import java.io.*;

class Libro {
    private String titulo;
    private String autor;
    private int anoPublicacion;

    public Libro(String titulo, String autor, int anoPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }
}

public class Biblioteca {
    private Libro[] libros;
    private int cantidadLibros;

    public Biblioteca() {
        libros = new Libro[100];
        cantidadLibros = 0;
    }

    public void agregarLibro(Libro libro) {
        if (cantidadLibros < libros.length) {
            libros[cantidadLibros] = libro;
            cantidadLibros++;
            System.out.println("Libro agregado correctamente.");
        } else {
            System.out.println("La biblioteca está llena, no se pueden agregar más libros.");
        }
    }

    public void eliminarLibro(String titulo) {
        boolean encontrado = false;
        int indice = -1;
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros[i].getTitulo().equalsIgnoreCase(titulo)) {
                encontrado = true;
                indice = i;
                break;
            }
        }

        if (encontrado) {
            for (int i = indice; i < cantidadLibros - 1; i++) {
                libros[i] = libros[i + 1];
            }
            libros[cantidadLibros - 1] = null;
            cantidadLibros--;
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún libro con ese título.");
        }
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros[i].getTitulo().equalsIgnoreCase(titulo)) {
                return libros[i];
            }
        }
        return null;
    }

    public void mostrarInformacionLibros() {
        if (cantidadLibros == 0) {
            System.out.println("La biblioteca está vacía.");
        } else {
            System.out.println("Información de los libros en la biblioteca:");
            for (int i = 0; i < cantidadLibros; i++) {
                Libro libro = libros[i];
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Año de publicación: " + libro.getAnoPublicacion());
                System.out.println("--------------------");
            }
        }
    }

    public void ordenarLibrosPorTitulo() {
        if (cantidadLibros > 1) {
            for (int i = 0; i < cantidadLibros - 1; i++) {
                for (int j = 0; j < cantidadLibros - i - 1; j++) {
                    if (libros[j].getTitulo().compareToIgnoreCase(libros[j + 1].getTitulo()) > 0) {
                        Libro temp = libros[j];
                        libros[j] = libros[j + 1];
                        libros[j + 1] = temp;
                    }
                }
            }
            System.out.println("Libros ordenados por título.");
        } else {
            System.out.println("No hay suficientes libros para ordenar.");
        }
    }

    public void ordenarLibrosPorAnoPublicacion() {
        if (cantidadLibros > 1) {
            for (int i = 0; i < cantidadLibros - 1; i++) {
                for (int j = 0; j < cantidadLibros - i - 1; j++) {
                    if (libros[j].getAnoPublicacion() > libros[j + 1].getAnoPublicacion()) {
                        Libro temp = libros[j];
                        libros[j] = libros[j + 1];
                        libros[j + 1] = temp;
                    }
                }
            }
            System.out.println("Libros ordenados por año de publicación.");
        } else {
            System.out.println("No hay suficientes libros para ordenar.");
        }
    }

    public void guardarInformacionEnArchivo(String nombreArchivo) {
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < cantidadLibros; i++) {
                Libro libro = libros[i];
                bufferedWriter.write(libro.getTitulo() + "," + libro.getAutor() + "," + libro.getAnoPublicacion());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Información de la biblioteca guardada en el archivo correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarInformacionDesdeArchivo(String nombreArchivo) {
        try {
            FileReader fileReader = new FileReader(nombreArchivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                String titulo = datos[0];
                String autor = datos[1];
                int anoPublicacion = Integer.parseInt(datos[2]);

                Libro libro = new Libro(titulo, autor, anoPublicacion);
                agregarLibro(libro);
            }

            bufferedReader.close();
            System.out.println("Información de la biblioteca cargada desde el archivo correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Ejemplo de uso de las funcionalidades de la biblioteca
        Libro libro1 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605);
        biblioteca.agregarLibro(libro1);

        Libro libro2 = new Libro("1984", "George Orwell", 1949);
        biblioteca.agregarLibro(libro2);

        Libro libro3 = new Libro("Cien años de soledad", "Gabriel García Márquez", 1967);
        biblioteca.agregarLibro(libro3);

        biblioteca.mostrarInformacionLibros();

        biblioteca.ordenarLibrosPorAnoPublicacion();
        biblioteca.mostrarInformacionLibros();

        biblioteca.guardarInformacionEnArchivo("biblioteca.txt");

        Biblioteca bibliotecaCargada = new Biblioteca();
        bibliotecaCargada.cargarInformacionDesdeArchivo("biblioteca.txt");
        bibliotecaCargada.mostrarInformacionLibros();
    }
}
