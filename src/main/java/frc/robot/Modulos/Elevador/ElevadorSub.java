package frc.robot.Modulos.Elevador;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;


import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Modulos.Elevador.ElevadorConstantes.ElevadorCons.*;

/*Esto es un elevador. bueno que funcion tiene esto?
 * Para empezar el elevador es el que sube y baja dependiendo si nuestro elevador es multinivel. (Voy a amar hacer un elevador multi.)
 * Usamos Un NEO. y no los CIM. Y a diferencia de los CIM estos no usan Kbrushed si no KBrushless para poder incluso PID.
 * y con eso podemos crear un motor de freno o que se mantenga en su posicion. (Aunque eso lo hace los CIM.)
 */

public class ElevadorSub extends SubsystemBase{

    private final SparkMax Elevador;
    
    /**El subsistema del elevador */
    public ElevadorSub(){
        //Configuracion principal del elevador
        Elevador = new SparkMax(ElevadorConstantes.ElevadorID, MotorType.kBrushless);
        //Otro Sparkmaxconfig para la configuracion del elevador
        SparkMaxConfig ConfiguracionElevador = new SparkMaxConfig();

        //Esta configuracion del elevador, hace que se mantenga frenado si su posicion es alterada.
        ConfiguracionElevador.idleMode(IdleMode.kBrake);
        ConfiguracionElevador.voltageCompensation(ElevadorConstantes.Limite_Voltaje_Elevador);
        ConfiguracionElevador.smartCurrentLimit(ElevadorConstantes.Limite_Ampers_Elevador);

        Elevador.configure(ConfiguracionElevador,com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    @Override
    public void periodic(){

    }
    /**
     * Este es el metodo que hace el elevador se mueva a tus ordenes.
     * @param velocidad siendo de -1.0 a 1 y 0 si esta apagado.
     */
    public void ElevadorEncendido(Double velocidad){
        Elevador.set(velocidad);
    }


    
}
