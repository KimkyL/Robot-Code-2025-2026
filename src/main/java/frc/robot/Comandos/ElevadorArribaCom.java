package frc.robot.Comandos;

import frc.robot.Constantes.ElevadorConstantes;
import frc.robot.Subsistemas.ElevadorSub;

import edu.wpi.first.wpilibj2.command.Command;
/** El comando que se usa para subir el elevador */
public class ElevadorArribaCom extends Command {

    private final ElevadorSub m_elevador;

    /**
     * Le da poder al elevador hacia abajo
     * 
     * En mi sincera opiñion no usen comandos que es esto hermano solo es codigo de mas 
     * 
     * @param elevador siendo el subsistema que se usa para el elevador
     */

     public ElevadorArribaCom(ElevadorSub elevador){
        m_elevador = elevador;
        addRequirements(elevador);
     }

     @Override
     public void initialize() {}

     @Override
     public void execute() {
       m_elevador.ElevadorEncendido(ElevadorConstantes.Subir);
     }

     @Override
     public void end(boolean interrupted) {
      m_elevador.ElevadorEncendido(ElevadorConstantes.MantenerseArriba);
  }

    @Override
    public boolean isFinished() {
      return false;
   
}

}
