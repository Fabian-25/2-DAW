import React from 'react'
import { ListaPedidos } from './components/ListaPedidos'
import { pedidos } from './js/Pedidos'

export const App = () => {
  return (
    <>
      <h1>Gestion de Pedidos</h1>

      <h2>Listado general de pedidos</h2>
      <ListaPedidos pedidos={pedidos} /> 

      <h2>Listado de pedidos entregados</h2>
      <ListaPedidos pedidos={pedidos.filter(pedido => pedido.estado === "entregado")} />

      <h2>Resumen economico</h2>
      <p>Pedidos entregados: {pedidos.filter(pedido => pedido.estado === "entregado").length}</p>
      <p>Importe pedidos entregados: {pedidos.filter(pedido => pedido.estado === "entregado").reduce((total, pedido) => total + pedido.importe, 0).toFixed(2)}€</p>
      <p>Importe medio de pedidos entregados: {pedidos.filter(pedido => pedido.estado === "entregado")
                                                        .reduce((total, pedido) => total + pedido.importe, 0).toFixed(2) / pedidos.filter(pedido => pedido.estado === "entregado").length || 0}€</p>

      

      

    </>
  )
}
