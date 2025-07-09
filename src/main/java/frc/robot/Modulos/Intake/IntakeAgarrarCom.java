package frc.robot.Modulos.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Modulos.Intake.IntakeConstantes.IntakeConstantes.*;


public class IntakeAgarrarCom extends Command {

    private final IntakeSub Intake_m;

    /**
     * Le da poder al intake cuando agarra. siento el parametro.
     * @param Intake // Para soltar
     * @param Holamundo! //No se apenas se me ocurrrio.
     * @param Estonoesuncopy? //Sip si te diste cuenta es lo mismo pero al reves.
     */

    //Sensillamente lo mismo pero alreves.
    public IntakeAgarrarCom(IntakeSub Intake){

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
        Intake_m.IntakeEncendido(IntakeCons.Agarrar);
    }

    @Override
    public void end(boolean interrupted){
        Intake_m.IntakeEncendido(0.0);

    }

    public boolean isFinished(){
        return false;
    }


    
}