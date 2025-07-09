package frc.robot.Modulos.Elevador;
import frc.robot.Modulos.Elevador.ElevadorConstantes.ElevadorCons.*;

import edu.wpi.first.wpilibj2.command.Command;
/** El comando que se usa para bajar el elevador */



public class ElevadorArribaCom extends Command {

    private final ElevadorSub m_elevador;

    /**
     * Le da poder al elevador hacia abajo
     * 
     * @param ElevadorAbajo Haciendo que el elevador baje cuando se presione el boton selecionado en Controles.java
     * @param MantenerseAbajo Haciendo que cuando el elevador termine el tiempo de competencia baje por su propia cuenta.
     */

     public ElevadorArribaCom(ElevadorSub elevador){
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



    

