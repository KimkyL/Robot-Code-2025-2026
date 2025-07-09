package frc.robot.Modulos.Movimiento.MovimientoConstantes;

public final class MovimientoCons {
    public static final class Movimiento {
        /** La Asignacion de Sparks que se le dan al robot en su caso 4 */
        public static final int MotorDer1ID = 3; //Derecha Lider LB
        public static final int MotorDer2ID = 4; //Derecha Seguidor LA
        public static final int MotorIzq1ID = 1; //Lqzuierda Lider RB
        public static final int MotorIzq2ID = 2; //Lqzuirda Seguidor RA

        
       /** Establece los limites de velocidad y rotacion para saber cuanta velocidad se le da */
        public static final double VelocidadX = 0.5; 
        public static final double ZRotacion = 0.5; 
        //Velocidad Arcade

        
        /** Establece los limites de velocidad izquierda y derecha al estilo tank (usamos Drive ntp =) */
        public static final double VelocidadDerecha = -0.9;
        public static final double VelocidadIzquierda = -0.9;
        //Velocidad Tank

        
        /** El limite de Voltaje y compesacion de estas asi garantizando su uso al 100% */
        public static final int Limite_Motor_Actual = 60; //Limite de Amperes en el motor si el motor soporta 120A usara esto pero con esto capeamos a 60 para evitar sobrecargas y daños en los sparks
        public static final double Limite_Voltaje_Motor = 12; //limite de voltios que da la bateria si la bateria baja a 10V el spark va usar 12V igualmente para usar el maximo posible antes de descargarse
    }
}
