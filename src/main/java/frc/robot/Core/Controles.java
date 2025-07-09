package frc.robot.Core;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Config.ControlesConstantes.JoystickCons.DriversPort;
import frc.robot.Modulos.Elevador.*;
import frc.robot.Modulos.Intake.*;
import frc.robot.Modulos.Movimiento.*;
import frc.robot.Modulos.Neumatica.*;
import frc.robot.Modulos.Neumatica.Compresor.*;
import frc.robot.Modulos.Neumatica.Solenoid.*;



//El robot container o basicamente todo lo que hace que haga magia nuestro robot, le puse controles ya que suena mejor que ponerle un robotcontainer.java
//Muy..... Generico.
/**
 * La clase donde puedes controlar absoultamente todo el movimiento el numero de asignaciones TODO
 * Espera.... porque salio en cyan el todo?
 */
public class Controles {

    //El control principal ante todo, es el que movera y manejara el robot o basicamente movimiento unicamente.
    private final XboxController m_Control_Driver = 
    
    new XboxController(DriversPort.Control_Driver);
    //El control del operador, sip usamos 2 (Espero que me dejen) este es el encargado de controlar todo lo que es Conos, cubos, cirulos O NO SE QUE SEA DIOS SOLO RECOJE Y HACE LAS TAREAS SECUNDARIAS
    private final XboxController m_Control_Operador =

    new XboxController(DriversPort.Control_Operador);
    //Se puede remover el operador para solo driver, pero igual por default vamos a dejarlo en solo Driver y no operador

    public final MovimientoSubsistema m_drive = new MovimientoSubsistema();
    public final ElevadorSub m_elevador = new ElevadorSub();
    public final IntakeSub m_Intakesub = new IntakeSub();
    public final SolenoidSubsistema m_SolenoidSub = new SolenoidSubsistema();
    
    /** Esta linea trae todo el codigo dentro de si para hacer la magia */
    public Controles(){
        Configcontrolesxbx();
        //Configcontroleslogitech();
    }

    /**
     * Configura la clase Configcontrolesxbx
     */
    public void Configcontrolesxbx(){

        m_drive.setDefaultCommand(new MovimientoCom(
            () -> m_Control_Driver.getLeftY(),
            () -> m_Control_Driver.getRightX(),
            m_drive));

        //Sube el elevador usando el Bumper Izquierdo
        new JoystickButton(m_Control_Driver, XboxController.Button.kLeftBumper.value)
        .whileTrue(new ElevadorArribaCom(m_elevador));
        //Sube el elevador usando el Bumper Derecho
        new JoystickButton(m_Control_Driver, XboxController.Button.kRightBumper.value)
        .whileTrue(new ElevadorAbajoCom(m_elevador));
        
        new JoystickButton(m_Control_Driver, XboxController.Button.kStart.value)
        .onTrue(new EncenderCompresorCom(m_SolenoidSub));

        new JoystickButton(m_Control_Driver, XboxController.Button.kBack.value)
        .onTrue(new ApagarCompresorCom(m_SolenoidSub));

        new JoystickButton(m_Control_Driver, XboxController.Button.kA.value)
        .onTrue(new SolenoidExtenderCom(m_SolenoidSub));

        new JoystickButton(m_Control_Driver, XboxController.Button.kB.value)
        .onTrue(new SolenoidRetraerCom(m_SolenoidSub));

        new JoystickButton(m_Control_Driver, XboxController.Button.kY.value)
        .onTrue(new SolenoidInvertirCom(m_SolenoidSub));

        new JoystickButton(m_Control_Operador, XboxController.Button.kA.value)
        .whileTrue(new IntakeAgarrarCom(m_Intakesub));

        new JoystickButton(m_Control_Operador, XboxController.Button.kB.value)
        .whileTrue(new IntakeSoltarCom(m_Intakesub));

        //si te lo preguntas tengo el codigo de hace 3 meses mas feo y muy desorganizado. este es el nuevo y suculento gestion de controles.


    }

    /*public void Configcontroleslogitech(){
        System.out.println("Oye esta madre no tiene configuracion de control XDDDDDDDDDDD");
        
    }
    */


    public Command getAutonomousCommand(){
        return null;
    }

   

    


   
   
    













}
