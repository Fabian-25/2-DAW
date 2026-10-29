import React from 'react'

export const Libros = ({ titulo, genero, disponible }) => {
  return (
    <li>
      <h3>{titulo}</h3>
      <p>Género: {genero}</p>
      <p>Disponible: {disponible ? "Disponible" : "Prestado"}</p>
    </li>
  )
}


