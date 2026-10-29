

import { ListaLibros } from './components/ListaLibros';
import { libros } from './js/Libros'
import { Contador } from './components/Contador';



export const App = () => {
  const listaLibros = libros;

    
    return (
        <>
        <h1>IES Sotero Hernandez</h1>
        <ListaLibros libros={listaLibros} mensaje={listaLibros.filter(libro => libro.disponible).length > 0 ? "No hay libros disponibles para prestamo" : "No hay libros registrados"} />
        <h2>Libros Disponibles</h2>
        <ListaLibros libros={listaLibros.filter(libro => libro.disponible)} mensaje={listaLibros.filter(libro => libro.disponible).length > 0 ? "No hay libros disponibles disponibles para prestamo" 
        : "No hay libros registrados"} />

        <h2>Resumen de Libros</h2>
        <p>Libros Registrados: <Contador total={listaLibros.length} /></p>
        <p>Libros Disponibles: <Contador total={listaLibros.filter(libro => libro.disponible).length}l /></p>
        </>
    )
}
