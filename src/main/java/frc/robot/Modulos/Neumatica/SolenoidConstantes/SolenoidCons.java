package frc.robot.Modulos.Neumatica.SolenoidConstantes;

public final class SolenoidCons {
    /**Estas son las constantes del solenoide
     * @param SolenoidConf //Las configuraciones del Compressor y Solenoide
     */
    public static final class SolenoidConf{
        
        public static final int CompressorID = 0; //El CanID del Compressor (No podemos cambiarlo ya que este puede afectar y dar Hal Error si lo cambiamos)
        public static final int SolenoidID = 4; //El supuesto 'CanID' del Solenoide (Este puede estar en cualquier modulo)
        
    }
    
}
