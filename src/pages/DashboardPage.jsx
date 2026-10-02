import IndicadorCard from "../components/IndicadorCard";
import CandidatoCard from "../components/CandidatoCard";
import SolicitudesPanel from "../components/SolicitudesPanel";

function DashboardPage() {
    
    const indicadores = [
        { id: 1, Valor: 12, Titulo: "Candidatos" },
        { id: 2, Valor: 5, Titulo: "Pendientes" },
        { id: 3, Valor: 4, Titulo: "En Proceso" },
        { id: 4, Valor: 3, Titulo: "Finalizadas" },
    ];

    const candidatos = [
        {   id: 1, 
            nombre: "Juan Pérez", 
            cargo: "Desarrollador Frontend", 
            estado: "Pendiente" 
        },

        {   id: 2, 
            nombre: "María López", 
            cargo: "Diseñadora UX/UI", 
            estado: "En Proceso" 
        },
        {   id: 3, 
            nombre: "Carlos García", 
            cargo: "Analista de Datos", 
            estado: "Finalizada" 
        },
        {   id: 4, 
            nombre: "Ana Torres", 
            cargo: "Project Manager", 
            estado: "Pendiente" 
        },
    ];

    const solicitudes = [
        {
            id: 1,
            candidato: "Ana Torres",
            cargo: "Analista",
            estado: "Pendiente",
            responsable: "Laura Pérez"
        },

        {
            id: 2,
            candidato: "Diego Soto",
            cargo: "Supervisor",
            estado: "En proceso",
            responsable: "Carlos Díaz"
        },
        
        {
            id: 3,
            candidato: "Camila Rojas",
            cargo: "Operador",
            estado: "Finalizada",
            responsable: "Laura Pérez"
        }
    ]

    return (
        <main className="container py-4">

            <section className="mb-4">
                <p className="text-secondary mb-1">
                    Proyecto VcM . Full Stack II

                </p>
                <h1 className="h3">Dashboard</h1>
            </section>

            <section className="row g-4 mb-5">
                {indicadores.map((indicador) => (
                    <IndicadorCard
                        key={indicador.id}
                        Valor={indicador.Valor}
                        Titulo={indicador.Titulo}
                    />
                ))}
            </section>

            <section className="mb-4">

                <h2 className="h4 mb-3">
                    Candidatos Recientes
                </h2>

                <div className="row g-3">
                    {candidatos.map((candidato) => (
                        <CandidatoCard
                            key={candidato.id}
                            nombre={candidato.nombre}
                            cargo={candidato.cargo}
                            estado={candidato.estado}
                        />
                    ))}
                </div>

            </section>

            <SolicitudesPanel solicitudes={solicitudes} />
            
        </main>
    );
        
}         

export default DashboardPage;   
