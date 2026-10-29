import React from 'react'

export const Pedido = ({id, cliente, importe, estado}) => {
  return (
    <div>
    <p> Pedido {id} - {cliente} - {importe.toFixed(2)}€ - {estado}</p>
      
    </div>
  )
}
