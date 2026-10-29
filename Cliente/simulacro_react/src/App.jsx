
import { Cabecera } from './components/Cabecera'
import { Actividad } from './components/Actividades'
import { ListaActividades } from './components/ListaActividades'
import { actividades } from './js/Actividades'
import { ListaActividadesAbiertas } from './components/ListaActividadesAbiertas'


export const App = () => {
  const listaActividades = actividades;
  const listaActividadesAbiertas = actividades;
  
    
    return (
        <>
        <div>
            <Cabecera />
        </div>
            
            <div>
            <h2>Catalogo de Actividades</h2>
                <ListaActividades actividades={listaActividades} />  
            </div>
            <div>
            <h2>Catalogo de Actividades Abiertas</h2>
                <ListaActividadesAbiertas actividades={listaActividadesAbiertas} />  
            </div>
            
          
        </>
    )
}
