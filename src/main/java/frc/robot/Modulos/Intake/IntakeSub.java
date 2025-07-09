package frc.robot.Modulos.Intake;
/*Subsistema del intake o Garra. */
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Modulos.Intake.IntakeConstantes.IntakeConstantes.*;   

public class IntakeSub extends SubsystemBase{

    /** La clase del SparkMax del Intake */
    private final SparkMax IntakeMotor ;

    /* Pero que es un Intake???
     * Bueno un Intake es basicament una garra que recoge objetos, es decir puede ser un tubo, unas ruedas o un sistema flexible para agarrar un objeto.
     * generalmente hay tres tipos el de ruedas para agarrar objetos grandes sin requerir mucho
     * otros de menor tamaño para objetos chicos como cubos o pelotas.
     * y ojo no es la combustion de un vehiculo. si revise el google.
     */

    public IntakeSub(){

        IntakeMotor = new SparkMax(IntakeCons.IntakeMotor,MotorType.kBrushed);

        SparkMaxConfig configuracionintake = new SparkMaxConfig();

        IntakeMotor.configure(configuracionintake, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
     /**
     * Este es el metodo que hace el elevador se mueva a tus ordenes.
     * @param Velocidad siendo de -1.0 a 1 y 0 si esta apagado.
     */
    public void IntakeEncendido(Double Velocidad){
        IntakeMotor.set(Velocidad);

    }

    @Override
    public void periodic(){
        /* Hello World! (Dios pierdo vida haciendo esto. ME ENCANTA!) */
        
    }



    
}
