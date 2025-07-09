package frc.robot.Modulos.Elevador.ElevadorConstantes;

public final class ElevadorCons {
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
    
}
