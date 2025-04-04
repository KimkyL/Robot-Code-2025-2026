package frc.robot.Comandos;

import frc.robot.Constantes.ElevadorConstantes;
import frc.robot.Subsistemas.ElevadorSub;

import edu.wpi.first.wpilibj2.command.Command;
/** El comando que se usa para bajar el elevador */
public class ElevadorAbajoCom extends Command {

    private final ElevadorSub m_elevador;

    /**
     * Le da poder al elevador hacia abajo
     * 
     * Lol solo es copy y paste porque al usar un private final en lugar de public hace que esta parte sea imutable ante cualquier cambio
     * 
     * @param elevador siendo el subsistema que se usa para el elevador
     * @param tusmuertos siendo este nada porque solo ando comentando XD
     */

     public ElevadorAbajoCom(ElevadorSub elevador){
        m_elevador = elevador;
        addRequirements(elevador);
     }

     @Override
     public void initialize() {}

     @Override
     public void execute() {
       m_elevador.ElevadorEncendido(ElevadorConstantes.bajar);
     }

     @Override
     public void end(boolean interrupted) {
      m_elevador.ElevadorEncendido(ElevadorConstantes.MantenerseAbajo);
  }

    @Override
    public boolean isFinished() {
      return false;
   
}

}
