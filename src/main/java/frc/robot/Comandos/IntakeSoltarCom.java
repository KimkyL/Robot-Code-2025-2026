package frc.robot.Comandos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constantes;
import frc.robot.Subsistemas.IntakeSub;

public class IntakeSoltarCom extends Command {

    private final IntakeSub Intake_m;

    /**
     * Le da poder al intake cuando Suelta. siento el parametro.
     * @param Intake // Para soltar
     * @param Holamundo! //No se apenas se me ocurrrio.
     */


    public IntakeSoltarCom(IntakeSub Intake){

        Intake_m = Intake;
        /* Recoge todos las variables que tiene el Intake osea del subsistema que creamos para que si podamos usarlos aqui mismo */
        addRequirements(Intake);
    }

    @Override
    public void initialize(){  
        /*Inicia el commando pero aqui no se pone ninguna variable. */
    }

    @Override
    public void execute(){
        Intake_m.IntakeEncendido(Constantes.IntakeCons.Soltar);
    }

    @Override
    public void end(boolean interrupted){
        Intake_m.IntakeEncendido(0.0);

    }

    public boolean isFinished(){
        return false;
    }


    
}
