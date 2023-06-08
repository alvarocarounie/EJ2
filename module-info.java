
	import java.io.*;
	import java.util.*;


     class Biblioteca {
	    private List<Libro> libros;
	    private List<Usuario> usuarios;

	    public Biblioteca() {
	        libros = new ArrayList<>();
	        usuarios = new ArrayList<>();
	    }


	    public void agregarLibro(Libro libro) {
	        libros.add(libro);
	    }

	    public void eliminarLibro(Libro libro) {
	        libros.remove(libro);
	    }

	    
	    public void agregarUsuario(Usuario usuario) {
	        usuarios.add(usuario);
	    }

	    public void eliminarUsuario(Usuario usuario) {
	        usuarios.remove(usuario);
	    }

	   
	    public void prestarLibro(Libro libro, Usuario usuario) throws PrestamoException {
	        if (libro.isPrestado()) {
	            throw new PrestamoException("El libro ya está prestado.");
	        }

	        if (usuario.getEdad() < libro.getEdadRecomendada()) {
	            throw new PrestamoException("El usuario no cumple con la edad recomendada para este libro.");
	        }

	        libro.setPrestado(true);
	        libro.setUsuarioPrestamo(usuario);
	    }

	    
	    public void devolverLibro(Libro libro) throws PrestamoException {
	        if (!libro.isPrestado()) {
	            throw new PrestamoException("El libro no está prestado.");
	        }

	        libro.setPrestado(false);
	        libro.setUsuarioPrestamo(null);
	    }

	    public List<Libro> listarLibrosPorTitulo(String titulo) {
	        List<Libro> librosEncontrados = new ArrayList<>();
	        for (Libro libro : libros) {
	            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
	                librosEncontrados.add(libro);
	            }
	        }
	        return librosEncontrados;
	    }

	  
	    public List<Libro> listarLibrosPorCategoria(String categoria) {
	        List<Libro> librosEncontrados = new ArrayList<>();
	        for (Libro libro : libros) {
	            if (libro.getCategoria().equalsIgnoreCase(categoria)) {
	                librosEncontrados.add(libro);
	            }
	        }
	        return librosEncontrados;
	    }


	    public List<Libro> listarLibrosPrestados() {
	        List<Libro> librosPrestados = new ArrayList<>();
	        for (Libro libro : libros) {
	            if (libro.isPrestado()) {
	                librosPrestados.add(libro);
	            }
	        }
	        return librosPrestados;
	    }


	    public List<Libro> listarLibrosDisponibles() {
	        List<Libro> librosDisponibles = new ArrayList<>();
	        for (Libro libro : libros) {
	            if (!libro.isPrestado()) {
	                librosDisponibles.add(libro);
	            }
	        }
	        return librosDisponibles;
	    }


	    public List<Libro> listarLibrosPorUsuario(Usuario usuario) {
	        List<Libro> librosUsuario = new ArrayList<>();
	        for (Libro libro : libros) {
	            if (libro.isPrestado() && libro.getUsuarioPrestamo().equals(usuario)) {
	                librosUsuario.add(libro);
	            }
	        }
	        return librosUsuario;
	    }


	    public void guardarEstado(String archivo) {
	        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
	            salida.writeObject(this);
	            System.out.println("Estado de la biblioteca guardado correctamente en el archivo: " + archivo);
	        } catch (IOException e) {
	            System.out.println("Error al guardar el estado de la biblioteca: " + e.getMessage());
	        }
	    }


	    public static Biblioteca cargarEstado(String archivo) {
	        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
	            Biblioteca biblioteca = (Biblioteca) entrada.readObject();
	            System.out.println("Estado de la biblioteca cargado correctamente desde el archivo: " + archivo);
	            return biblioteca;
	        } catch (IOException | ClassNotFoundException e) {
	            System.out.println("Error al cargar el estado de la biblioteca: " + e.getMessage());
	            return null;
	        }
	    }
	}


	class Libro implements Serializable {
	    private String titulo;
	    private String autor;
	    private String identificador;
	    private String categoria;
	    private int edadRecomendada;
	    private boolean prestado;
	    private Usuario usuarioPrestamo;

	    public Libro(String titulo, String autor, String identificador, String categoria, int edadRecomendada) {
	        this.titulo = titulo;
	        this.autor = autor;
	        this.identificador = identificador;
	        this.categoria = categoria;
	        this.edadRecomendada = edadRecomendada;
	        this.prestado = false;
	        this.usuarioPrestamo = null;
	    }



	    public String getTitulo() {
	        return titulo;
	    }

	    public void setTitulo(String titulo) {
	        this.titulo = titulo;
	    }

	    public String getAutor() {
	        return autor;
	    }

	    public void setAutor(String autor) {
	        this.autor = autor;
	    }

	    public String getIdentificador() {
	        return identificador;
	    }

	    public void setIdentificador(String identificador) {
	        this.identificador = identificador;
	    }

	    public String getCategoria() {
	        return categoria;
	    }

	    public void setCategoria(String categoria) {
	        this.categoria = categoria;
	    }

	    public int getEdadRecomendada() {
	        return edadRecomendada;
	    }

	    public void setEdadRecomendada(int edadRecomendada) {
	        this.edadRecomendada = edadRecomendada;
	    }

	    public boolean isPrestado() {
	        return prestado;
	    }

	    public void setPrestado(boolean prestado) {
	        this.prestado = prestado;
	    }

	    public Usuario getUsuarioPrestamo() {
	        return usuarioPrestamo;
	    }

	    public void setUsuarioPrestamo(Usuario usuarioPrestamo) {
	        this.usuarioPrestamo = usuarioPrestamo;
	    }
	}


	class Usuario implements Serializable {
	    private String nombre;
	    private String apellido1;
	    private String apellido2;
	    private Date fechaNacimiento;
	    private String dni;

	    public Usuario(String nombre, String apellido1, String apellido2, Date fechaNacimiento, String dni) {
	        this.nombre = nombre;
	        this.apellido1 = apellido1;
	        this.apellido2 = apellido2;
	        this.fechaNacimiento = fechaNacimiento;
	        this.dni = dni;
	    }

	

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getApellido1() {
	        return apellido1;
	    }

	    public void setApellido1(String apellido1) {
	        this.apellido1 = apellido1;
	    }

	    public String getApellido2() {
	        return apellido2;
	    }

	    public void setApellido2(String apellido2) {
	        this.apellido2 = apellido2;
	    }

	    public Date getFechaNacimiento() {
	        return fechaNacimiento;
	    }

	    public void setFechaNacimiento(Date fechaNacimiento) {
	        this.fechaNacimiento = fechaNacimiento;
	    }

	    public String getDni() {
	        return dni;
	    }

	    public void setDni(String dni) {
	        this.dni = dni;
	    }

	    public int getEdad() {
	   
	        Calendar fechaActual = Calendar.getInstance();
	        Calendar fechaNac = Calendar.getInstance();
	        fechaNac.setTime(fechaNacimiento);

	        int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

	        if (fechaActual.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH)) {
	            edad--;
	        } else if (fechaActual.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH)
	                && fechaActual.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH)) {
	            edad--;
	        }

	        return edad;
	    }
	}


	class PrestamoException extends Exception {
	    public PrestamoException(String mensaje) {
	        super(mensaje);
	    }
	}


        class Main {
	    public static void main(String[] args) {
	
	        Biblioteca biblioteca = new Biblioteca();


	        Libro libro1 = new Libro("El señor de los anillos", "J.R.R. Tolkien", "L001", "Aventuras", 12);
	        Libro libro2 = new Libro("1984", "George Orwell", "L002", "Ciencia Ficción", 16);
	        Libro libro3 = new Libro("Romeo y Julieta", "William Shakespeare", "L003", "Romántica", 14);

	        biblioteca.agregarLibro(libro1);
	        biblioteca.agregarLibro(libro2);
	        biblioteca.agregarLibro(libro3);


	        Usuario usuario1 = new Usuario("Juan", "Perez", "Gomez", new Date(), "12345678A");
	        Usuario usuario2 = new Usuario("Maria", "Gonzalez", "Lopez", new Date(), "87654321B");

	        biblioteca.agregarUsuario(usuario1);
	        biblioteca.agregarUsuario(usuario2);


	        try {
	            biblioteca.prestarLibro(libro1, usuario1);
	            System.out.println("Libro prestado correctamente.");
	        } catch (PrestamoException e) {
	            System.out.println("Error al prestar el libro: " + e.getMessage());
	        }

	
	        try {
	            biblioteca.devolverLibro(libro1);
	            System.out.println("Libro devuelto correctamente.");
	        } catch (PrestamoException e) {
	            System.out.println("Error al devolver el libro: " + e.getMessage());
	        }

	
	        List<Libro> librosPorTitulo = biblioteca.listarLibrosPorTitulo("1984");
	        for (Libro libro : librosPorTitulo) {
	            System.out.println(libro.getTitulo());
	        }

	        
	        List<Libro> librosDisponibles = biblioteca.listarLibrosDisponibles();
	        for (Libro libro : librosDisponibles) {
	            System.out.println(libro.getTitulo());
	        }

	
	        biblioteca.guardarEstado("estado_biblioteca.dat");

	  


	        
	           
	        }
	    	}

