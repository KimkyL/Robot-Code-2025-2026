package frc.robot.Comandos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsistemas.SolenoidSub;

public class ApagarCompressorCom extends Command {
    
    private final SolenoidSub SolenoideSUB;

    /**
     * Commando para desactivar el Compressor
     * 
     * @param Subsistema //El subsistema del solenoide
     */

    public ApagarCompressorCom(SolenoidSub Subsistema){
        SolenoideSUB = Subsistema;
        addRequirements(Subsistema);
    }

    @Override
    public void initialize(){
        SolenoideSUB.CompressorApagado();
    }

    @Override 
    public boolean isFinished(){
        return true; //Que el commando sea instaneo
    }


}
