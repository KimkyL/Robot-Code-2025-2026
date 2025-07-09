package frc.robot.Modulos.Neumatica.Solenoid;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Modulos.Neumatica.SolenoidSubsistema;

public class SolenoidExtenderCom extends Command {
    
    private final SolenoidSubsistema SolenoideSUB;

    /**
     * Commando para desactivar el Compressor
     * 
     * @param Subsistema //El subsistema del solenoide
     */

    public SolenoidExtenderCom(SolenoidSubsistema Subsistema){
        SolenoideSUB = Subsistema;
        addRequirements(Subsistema);
    }

    @Override
    public void initialize(){
        SolenoideSUB.extender();
    }

    @Override 
    public boolean isFinished(){
        return true; //Que el commando sea instaneo
    }


}
