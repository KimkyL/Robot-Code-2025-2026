package frc.robot.Modulos.Movimiento;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;
//Comando para la ejecucion del movimiento del robot.

public class MovimientoCom extends Command{

    private final DoubleSupplier m_VelocidadX;
    private final DoubleSupplier m_ZRotacion;
    private final MovimientoSubsistema m_drive;

    /**
     * Usado para el movimiento del robot, usamos ArcadeDrive por defecto pero si queremos cambiarlo a Tank simplemente copiamos las
     * variables y las pegamos 
     * 
     * @param movimiento
     * @param VelocidadX La velocidad hacia atras y delante
     * @param ZRotacion La velocidad de giro
     * @param botones los botones que usaremos(no creo.)
     * 
     */

    /** Trae las variables del subsistema para hacer el movimiento del robot */
    public MovimientoCom( DoubleSupplier VelocidadX, DoubleSupplier ZRotacion, MovimientoSubsistema movimiento){
        this.m_VelocidadX = VelocidadX;
        this.m_ZRotacion = ZRotacion;
        this.m_drive = movimiento;


        addRequirements(this.m_drive);
    }


    // Se ejecuta cada vez que es ejecutado desde el deploy
    @Override
    public void initialize() {}


    //Se ejecuta cada ciclo mientras se mantenga en ejecucion mas de 50 veces por segundo
    @Override
    public void execute() {
      m_drive.Drive(m_VelocidadX.getAsDouble(), m_ZRotacion.getAsDouble());
    }

    // Runea el comando cada vez que el Interrumpido es finalizado o interumpido
    @Override
    public void end(boolean isInterrupted){
        
    }

    //Se ejecuta cada ciclo, y comprueba si ha sido finalizado o no 
    @Override
    public boolean isFinished(){
        //Retorna un falso haciendo que el comando se ejecuta infinitamente o que sea cambiado.
        return false;
    }


    
}
