
export const ActividadesAbiertas = ({ nombre, categoria, plazas, abierta }) => {
    return (
        <>



            {abierta ? (

                <li> {nombre}  {categoria}  {plazas} plazas disponibles {abierta ? "Inscripcion Abierta" : "Inscripcion Cerrada"}
                </li>
            ) : (null)}

        </>
    );
};
