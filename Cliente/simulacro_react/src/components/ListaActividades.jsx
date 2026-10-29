import React from "react";
import { Actividad } from "./Actividades";

export const ListaActividades = ({ actividades }) => {
  return (
    <>
      {actividades.length === 0 ? (
        <p>No hay actividades en esta lista.</p>
      ) : (
        <ul>
          {actividades.map((actividad) => (
            <Actividad
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