
export const Actividad = ({ nombre, categoria, plazas, abierta }) => {
    return (
        <li>
            {nombre}  {categoria}  {plazas} plazas disponibles {abierta ? "Inscripcion Abierta" : "Inscripcion Cerrada"}
        </li>
    );
};
