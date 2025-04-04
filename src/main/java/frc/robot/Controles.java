package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constantes.DriversPort;
import frc.robot.Comandos.ApagarCompressorCom;
import frc.robot.Comandos.ElevadorAbajoCom;
import frc.robot.Comandos.ElevadorArribaCom;
import frc.robot.Comandos.EncenderCompressorCom;
import frc.robot.Comandos.MovimientoCom;
import frc.robot.Comandos.SolenoideExtenderCom;
import frc.robot.Comandos.SolenoideInvertirCom;
import frc.robot.Comandos.SolenoideRetraerCom;
import frc.robot.Comandos.IntakeAgarrarCom;
import frc.robot.Comandos.IntakeSoltarCom;
import frc.robot.Subsistemas.ElevadorSub;
import frc.robot.Subsistemas.IntakeSub;
import frc.robot.Subsistemas.MovimientoSub;
import frc.robot.Subsistemas.SolenoidSub;

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

    public final MovimientoSub m_drive = new MovimientoSub();
    public final ElevadorSub m_elevador = new ElevadorSub();
    public final IntakeSub m_Intakesub = new IntakeSub();
    public final SolenoidSub m_SolenoidSub = new SolenoidSub();
    
    /** Esta linea trae todo el codigo dentro de si para hacer la magia */
    public Controles(){
        Configcontrolesxbx();
        //Configcontroleslogitech();
    }

    /**
     * Configura la clase Configcontrolesxbx
     */
    public void Configcontrolesxbx(){

        m_drive.setDefaultCommand(new MovimientoCom(m_drive,
        () -> -m_Control_Driver.getRightX(),
        () -> -m_Control_Driver.getLeftY()
        ));


        new JoystickButton(m_Control_Driver, XboxController.Button.kLeftBumper.value)
        .whileTrue(new ElevadorArribaCom(m_elevador));
    
        new JoystickButton(m_Control_Driver, XboxController.Button.kRightBumper.value)
        .whileTrue(new ElevadorAbajoCom(m_elevador));

        new JoystickButton(m_Control_Driver, XboxController.Button.kStart.value)
        .onTrue(new EncenderCompressorCom(m_SolenoidSub));

        new JoystickButton(m_Control_Driver, XboxController.Button.kBack.value)
        .onTrue(new ApagarCompressorCom(m_SolenoidSub));

        new JoystickButton(m_Control_Driver, XboxController.Button.kA.value)
        .onTrue(new SolenoideExtenderCom(m_SolenoidSub));

        new JoystickButton(m_Control_Driver, XboxController.Button.kB.value)
        .onTrue(new SolenoideRetraerCom(m_SolenoidSub));

        new JoystickButton(m_Control_Driver, XboxController.Button.kY.value)
        .onTrue(new SolenoideInvertirCom(m_SolenoidSub));

        new JoystickButton(m_Control_Operador, XboxController.Button.kA.value)
        .whileTrue(new IntakeAgarrarCom(m_Intakesub));

        new JoystickButton(m_Control_Operador, XboxController.Button.kB.value)
        .whileTrue(new IntakeSoltarCom(m_Intakesub));


    }

    /*public void Configcontroleslogitech(){
        System.out.println("Oye esta madre no tiene configuracion de control XDDDDDDDDDDD");
        
    }
    */


    public Command getAutonomousCommand(){
        return null;
    }

   

    


   
   
    













}
