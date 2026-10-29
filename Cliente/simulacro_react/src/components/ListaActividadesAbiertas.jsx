import React from "react";
import { ActividadesAbiertas } from "./ActividadesAbiertas";

export const ListaActividadesAbiertas = ({ actividades }) => {
  return (
    <>
      {actividades.length === 0 ? (
        <p>No hay actividades en esta lista.</p>
      ) : (
        <ul>
          {actividades.map((actividad) => (
            <ActividadesAbiertas
              key={actividad.id}
              nombre={actividad.nombre}
              categoria={actividad.categoria}
              plazas={actividad.plazas}
              abierta={actividad.abierta}
            />
          ))}
        </ul>
      )}
    </>
  );
}