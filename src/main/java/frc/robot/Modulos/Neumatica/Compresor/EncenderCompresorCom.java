package frc.robot.Modulos.Neumatica.Compresor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Modulos.Neumatica.SolenoidSubsistema;
//Odio los copys igual que yo pero es la forma mas facil de reutilizar codigo y evitar que estar poniendo tal y tal y ahhhh
public class EncenderCompresorCom extends Command {
    
    private final SolenoidSubsistema SolenoideSUB;

    /**
     * Commando para desactivar el Compressor
     * 
     * @param Subsistema //El subsistema del solenoide
     */

    public EncenderCompresorCom(SolenoidSubsistema Subsistema){
        SolenoideSUB = Subsistema;
        addRequirements(Subsistema);
    }

    @Override
    public void initialize(){
        SolenoideSUB.CompressorPrendido();
    }

    @Override 
    public boolean isFinished(){
        return true; //Que el commando sea instantaneo

        
    }
    /**
     * Si pero.... que es instantaneo.....
     * @param true Explicitamente se mantendra activo hasta que haya otro donde diga ApagarCompressor en los botones
     * @param false Explicitamente le estamos diciendo que hey tu te mantendras activo indefinidamente pero aun asi puedes ser apagado. pero como estas retornando el valor se mantendra activo
     * si basicamente no se apaga.
     */


}
