package edu.eci.cvds.library.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.library.model.Carga;
import edu.eci.cvds.library.model.Categoria;
import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.model.Subcategoria;
import edu.eci.cvds.library.repository.LibroRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Servicio para manejar la carga de datos desde un archivo Excel al sistema.
 */
@Service
public class CargaService {

    private final CategoriaService categoriaService;
    private final SubcategoriaService subcategoriaService;
    private final EjemplarService ejemplarService;
    private final LibroService libroService;
    private final LibroRepository libroRepository;

    /**
     * Constructor que inicializa los servicios y repositorios necesarios.
     *
     * @param categoriaService    Servicio para manejar categorías.
     * @param subcategoriaService Servicio para manejar subcategorías.
     * @param ejemplarService     Servicio para manejar ejemplares.
     * @param libroService        Servicio para manejar libros.
     * @param libroRepository     Repositorio para acceso a libros.
     */
    @Autowired
    public CargaService(CategoriaService categoriaService, SubcategoriaService subcategoriaService,
            EjemplarService ejemplarService, LibroService libroService,
            LibroRepository libroRepository) {
        this.categoriaService = categoriaService;
        this.subcategoriaService = subcategoriaService;
        this.ejemplarService = ejemplarService;
        this.libroService = libroService;
        this.libroRepository = libroRepository;
    }

    /**
     * Carga los datos de un archivo Excel a partir de una hoja proporcionada.
     *
     * @param sheet Hoja de Excel que contiene los datos.
     * @param carga Configuración que indica las columnas relevantes en el archivo.
     * @throws IOException Si ocurre un error al procesar la hoja.
     */
    public void cargarExcel(Sheet sheet, Carga carga) throws IOException {
        Iterator<Row> rowIterator = sheet.iterator();

        if (rowIterator.hasNext()) {
            rowIterator.next(); // Saltar encabezado
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            procesarFila(row, carga);
        }
    }

    /**
     * Procesa una fila del archivo Excel para extraer y almacenar los datos.
     *
     * @param row   Fila de la hoja de Excel.
     * @param carga Configuración de las columnas.
     * @throws IOException Si ocurre un error al procesar la fila.
     */
    public void procesarFila(Row row, Carga carga) throws IOException {
        String nombreLibro = getCellValue(row, carga.getNombreLibro());
        String autor = getCellValue(row, carga.getAutor());
        String editorial = getCellValue(row, carga.getEditorial());
        String edicion = getCellValue(row, carga.getEdicion());
        String isbn = getCellValue(row, carga.getIsbn());
        String sinopsis = getCellValue(row, carga.getSinopsis());
        String anioPublicacion = getCellValue(row, carga.getAnioPublicacion());
        String estadoFisico = getCellValue(row, carga.getEstadoFisico());
        boolean disponible = determinarDisponibilidad(row, carga);

        Libro libro = obtenerOActualizarLibro(nombreLibro, autor, editorial, edicion, isbn, sinopsis, anioPublicacion);
        Subcategoria subcategoria = manejarSubcategoria(getCellValue(row, carga.getSubcategoria()));
        manejarCategoria(getCellValue(row, carga.getCategoria()), subcategoria);
        asociarSubcategoria(libro, subcategoria);
        agregarEjemplar(libro, estadoFisico, disponible);
    }

    /**
     * Determina la disponibilidad del ejemplar basado en los datos de la fila.
     *
     * @param row   Fila de la hoja de Excel.
     * @param carga Configuración de las columnas.
     * @return `true` si el ejemplar está disponible; de lo contrario, `false`.
     */
    public boolean determinarDisponibilidad(Row row, Carga carga) {
        String disponibilidadStr = getCellValue(row, carga.getDisponibilidad());
        return disponibilidadStr != null && !disponibilidadStr.trim().isEmpty();
    }

    /**
     * Obtiene o crea un libro basado en los datos proporcionados.
     *
     * @param nombre          Nombre del libro.
     * @param autor           Autor del libro.
     * @param editorial       Editorial del libro.
     * @param edicion         Edición del libro.
     * @param isbn            ISBN del libro.
     * @param sinopsis        Sinopsis del libro.
     * @param anioPublicacion Año de publicación del libro.
     * @return Objeto `Libro` existente o recién creado.
     */
    public Libro obtenerOActualizarLibro(String nombre, String autor, String editorial, String edicion,
            String isbn, String sinopsis, String anioPublicacion) {
        List<Libro> libros = libroRepository.buscarPorCualquierCampo(nombre, autor, edicion);

        if (libros != null && !libros.isEmpty()) {
            return libros.get(0);
        }

        Libro nuevoLibro = new Libro(nombre, autor, editorial, edicion, isbn, sinopsis, anioPublicacion);
        libroService.crearLibro(nuevoLibro);
        return nuevoLibro;
    }

    /**
     * Maneja la obtención o creación de una categoría.
     *
     * @param categoriaNombre Nombre de la categoría.
     * @return Objeto `Categoria` existente o recién creado.
     */
    public Subcategoria manejarSubcategoria(String subcategoriaNombre) {
        if (subcategoriaNombre == null || subcategoriaNombre.trim().isEmpty()) {
            return null;
        }

        Subcategoria subcategoria = subcategoriaService.obtenerSubcategoriaPorNombre(subcategoriaNombre);
        if (subcategoria == null) {
            subcategoria = new Subcategoria(subcategoriaNombre);
            subcategoriaService.crearOActualizarSubcategoria(subcategoria);
        }
        return subcategoria;
    }

    /**
     * Maneja la obtención o creación de una subcategoría y la asocia con su
     * categoría.
     *
     * @param subcategoriaNombre Nombre de la subcategoría.
     * @param categoria          Categoría asociada.
     * @return Objeto `Subcategoria` existente o recién creado.
     */
    public void  manejarCategoria(String categoriaNombre, Subcategoria subcategoria) {
        Categoria categoria = categoriaService.obtenerCategoriaPorNombre(categoriaNombre);
        if (categoria == null) {
            categoria = new Categoria(categoriaNombre);
            categoriaService.crearOActualizarCategoria(categoria);
        }

        if (subcategoria != null) {
            if (!subcategoria.findCategoria(categoria.getNombre())) {
                subcategoria.addCategoria(categoria);
                subcategoriaService.crearOActualizarSubcategoria(subcategoria);
            }
        }
    }

    /**
     * Asocia una categoría y subcategoría con un libro.
     *
     * @param libro        Objeto `Libro` al que se asociarán.
     * @param categoria    Categoría a asociar.
     * @param subcategoria Subcategoría a asociar.
     */
    public void asociarSubcategoria(Libro libro, Subcategoria subcategoria) {
        if (!libro.haveSubcategoria(subcategoria.getNombre())) {
            libro.addSubcategoria(subcategoria);
            libroService.actualizarLibro(libro);
        }
    }

    /**
     * Agrega un ejemplar a un libro.
     *
     * @param libro        Objeto `Libro` al que se agregará el ejemplar.
     * @param estadoFisico Estado físico del ejemplar.
     * @param disponible   Disponibilidad del ejemplar.
     * @throws IOException Si ocurre un error al guardar el ejemplar.
     */
    public void agregarEjemplar(Libro libro, String estadoFisico, boolean disponible) throws IOException {
        Ejemplar ejemplar = new Ejemplar(estadoFisico, disponible);
        ejemplar.setLibro(libro);
        ejemplarService.crearOActualizarEjemplar(ejemplar);
    }

    /**
     * Obtiene el valor de una celda basada en su índice.
     *
     * @param row          Fila de la hoja de Excel.
     * @param columnLetter Letra de la columna correspondiente.
     * @return Valor de la celda como cadena de texto.
     */
    public String getCellValue(Row row, String columnLetter) {
        Cell cell = row.getCell(Carga.letraAIndice(columnLetter));
        return cell != null ? cell.toString().trim() : null;
    }
}
