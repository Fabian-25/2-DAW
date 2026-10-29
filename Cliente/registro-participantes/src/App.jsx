
import { Cabecera } from './components/Cabecera'
import { FormularioRegistro } from './components/FormularioRegistro'
import { ListaParticipantes } from './components/ListaParticipantes'
import { Contador } from './components/Contador'
import { Participante } from './components/Participantes'
import { participantes } from './js/participantes'

export const App = () => {
    const listaParticipantes = participantes;

    listaParticipantes.map((participante) =>
        console.log(participante.nombre)
    );

    return (
        <>
            <Cabecera />
            <FormularioRegistro />
            <Contador total={4} />

            <ListaParticipantes>
                {
                    listaParticipantes.map(participante =>
                        <Participante
                            key={participante.email}
                            nombre={participante.nombre}
                            email={participante.email}
                        />
                    )
                }
            </ListaParticipantes>
        </>
    )
}
