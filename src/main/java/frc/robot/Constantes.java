package frc.robot;

public final class Constantes {
    /** Las constantes del movimiento */
    public static final class Movimiento {
        /** La Asignacion de Sparks que se le dan al robot en su caso 4 */
        public static final int MotorDer1ID = 1; //Derecha Lider LB
        public static final int MotorDer2ID = 2; //Derecha Seguidor LA
        public static final int MotorIzq1ID = 3; //Lqzuierda Lider RB
        public static final int MotorIzq2ID = 4; //Lqzuirda Seguidor RA
       /** Establece los limites de velocidad y rotacion para saber cuanta velocidad se le da */
        public static final double VelocidadX = 0.9; 
        public static final double ZRotacion = 0.9;  
        //Velocidad Arcade
        /** Establece los limites de velocidad izquierda y derecha al estilo tank (usamos Drive ntp =) */
        public static final double VelocidadDerecha = 0.9;
        public static final double VelocidadIzquierda = 0.9;
        //Velocidad Tank

        /** El limite de Voltaje y compesacion de estas asi garantizando su uso al 100% */
        public static final int Limite_Motor_Actual = 60; //Limite de Amperes en el motor si el motor soporta 120A usara esto pero con esto capeamos a 60 para evitar sobrecargas y daños en los sparks
        public static final double Limite_Voltaje_Motor = 12; //limite de voltios que da la bateria si la bateria baja a 10V el spark va usar 12V igualmente para usar el maximo posible antes de descargarse
    }

    public static final class ElevadorConstantes{
        public static final int ElevadorID = 5; //El CanID del motor del elevador

        /** El Limite de Voltaje y Compensacion de la bateria asi estamos garantizando el uso al 100% */
        public static final int Limite_Ampers_Elevador = 60; //Limite del elevador de Ampers, Esto hace que evite sobrecargas y si evitando posibles daños inreparables
        public static final double Limite_Voltaje_Elevador = 10; //Limite de Volts que da la bateria si la bateria baja a 10v el motor va a seguir los 10 Hasta su ultimo respiro.

        public static final double Subir = -0.5; //La velocidad con la que sube
        public static final double bajar = 0.5; //La velocidad con la que baja
        
        public static final double MantenerseAbajo = 0; //La velocidad con la que se mantiene Abajo (Por defalut la puse en 0)
        public static final double MantenerseArriba = 0; //La velocidad con la que se mantiene Arriba

    } 
    /**
     * Estas son las constantes del Intake
     * @param IntakeMotor //El Can ID
     * @param Soltar //La velocidad con la que suelta.
     * @param Agarrar //La velocidad con la que agarra.
     */

    public static final class IntakeCons{
        public static final int IntakeMotor = 6; // El CanID del Motor del Intake (Garra)

        public static final Double Soltar = -0.5; // La velocidad con la que Suelta
        public static final Double Agarrar = 0.5; // La velocidad con la que Agarra
    }
    
    /**Estas son las constantes del solenoide
     * @param SolenoidConf //Las configuraciones del Compressor y Solenoide
     */
    public static final class SolenoidConf{
        
        public static final int CompressorID = 0; //El CanID del Compressor (No podemos cambiarlo ya que este puede afectar y dar Hal Error si lo cambiamos)
        public static final int SolenoidID = 4; //El supuesto 'CanID' del Solenoide (Este puede estar en cualquier modulo)
    }

    /** Las constantes de los puertos de los controles del driver y del operador 
     * @param Control_Driver //El Control del lider
     * @param Control_Operador //El Control del operador
     * Nota podemos cambiar y solo usar Lider, o Driver.
    */
    public static final class DriversPort{
        /** Puerto del driver, ojo es el lider */
        public static final int Control_Driver = 0;
        /** Puerto del Operador ojo este es del seguidor */
        public static final int Control_Operador = 1;
    }
    
}
