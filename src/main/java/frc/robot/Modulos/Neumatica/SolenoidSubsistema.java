package frc.robot.Modulos.Neumatica;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Modulos.Neumatica.SolenoidConstantes.SolenoidCons.*;

/*Bienvenido al mundo donde sufren todos los que hacen solenoides. Bueno es el codigo del solenoide. 
 * Que hace bueno la funcion del solenoide que tenemos es basicamente ser de garra...? Si para colgarnos
 * Funcionaba, Absolutamente
 */
public class SolenoidSubsistema extends SubsystemBase{
    //Creamos la clase Solenoide
    private final Solenoid Solenoide;
    private final Compressor Compressor;

   /**
   * Creamos otra clase donde tendremos basicamente todo.
   * 
   * @param moduleType El tipo de modulo neumatico (CTREPCM o REVPH) Somos pobres asi que usamos humildemente CTREPCM
   * @param channel El canal donde esta conectado el solenoide
   */
    
   public SolenoidSubsistema(){

    Compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    Solenoide = new Solenoid(PneumaticsModuleType.CTREPCM, SolenoidConf.SolenoidID);
    //Compressor = new Compressor(PneumaticsModuleType.CTREPCM);

   }

   /**
    * Activa el Solenoide
    */
   public void extender(){
    Solenoide.set(true);
   }

   /**
    * Desactiva el Solenoide
    */
   public void retraer(){
    Solenoide.set(false);
   }
   /**
    * 
    * Invierte el solenoide
    * @param retraer En lugar de retraer extiende.
    * @param extender En lugar de extender retrae.
    * Basicamente es para evitar poner el extender y retraer.
    */
   public void invertir(){
    Solenoide.toggle();
   }

    /**
   * Devuelve el estado actual del solenoide.
   * 
   * @return true si esta activado, false si esta desactivado.
   */
   public boolean isExtended() {
    return Solenoide.get();
  }

  /**
   * Enciende el compressor
   */

   public void CompressorApagado(){
    Compressor.disable();
   }


  public void CompressorPrendido(){
    Compressor.enableDigital();
  }

}

  /**
   * Apaga el Compressor
   */

