import React from 'react'
import { Libros } from './Libros'

export const ListaLibros = ({ libros,mensaje }) => {
  return (
    <>
        {libros.length === 0 ? (
            <p>{mensaje}</p>
        ) : (
            <ul>
                {libros.map((libro) => (
                    <Libros 
                    key={libro.id} 
                    titulo={libro.titulo} 
                    genero={libro.genero} 
                    disponible={libro.disponible}
                     />
                ))}
            </ul>
        )}
    </>
  )
}
