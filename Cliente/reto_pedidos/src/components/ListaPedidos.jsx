import React from 'react'
import { Pedido } from './Pedido'

export const ListaPedidos = ({ pedidos }) => {
  return (
    <div>
      <h2>Pedidos</h2>
      {pedidos.map(pedido => (
        <Pedido
          key={pedido.id}
          id={pedido.id}
          cliente={pedido.cliente}
          importe={pedido.importe}
          estado={pedido.estado}
        />
      ))}
    </div>
  )
}
