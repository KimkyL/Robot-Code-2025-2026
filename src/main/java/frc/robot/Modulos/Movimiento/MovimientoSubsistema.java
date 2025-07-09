//Este es el subsistema del movimiento es el movimiento del robot o basicamente la base
package frc.robot.Modulos.Movimiento;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.Modulos.Movimiento.MovimientoConstantes.MovimientoCons.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
/* Bueno este es el movimiento. es basicamente todo el movimiento que se le hace al robot desde girar hasta moverse hacia adelante y atras. 
 * Aqui usamos CIM's son motores que no tienen controladores pero aun asi son buenos para el trabajo.
*/

public class MovimientoSubsistema extends SubsystemBase{


   private final SparkMax MotorIzq1; //MotorIzquierdo LB Lider
   private final SparkMax MotorIzq2; //MotorIzquierdo LA Seguidor
   private final SparkMax MotorDer1; //MotorDerecho RB Lider 
   private final SparkMax MotorDer2; //MotorDerecho RA Seguidor
   private final DifferentialDrive Drive; // Usado para los movimientos estilo tank
   //En honor a la Miss Mirasol, Es Giroescopio, no Girometro, Espera existe esa palabra?
   private final ADXRS450_Gyro Gironometro;

   private final double Angulo_Inclinacion_Maximo = 15.0; //Grados se puede modificar despues.
   private final double Angulo_Inclinacion_Critico = 30.0; //Grados cuando llega a su maximo grado de inclinacion.
   

    /**
    * El subsistema para el movimiento del robot
    */
    public MovimientoSubsistema(){

    MotorIzq1 = new SparkMax(Movimiento.MotorIzq1ID, MotorType.kBrushed); // LB Lider
    MotorIzq2 = new SparkMax(Movimiento.MotorIzq2ID, MotorType.kBrushed); // LA Seguidor
    MotorDer1 = new SparkMax(Movimiento.MotorDer1ID, MotorType.kBrushed); // RB Lider
    MotorDer2 = new SparkMax(Movimiento.MotorDer2ID, MotorType.kBrushed); // RA Seguidor
    Gironometro = new ADXRS450_Gyro();
    
    // Configuramos los diferential drives.
    Drive = new DifferentialDrive(MotorIzq1, MotorDer1);



    SparkMaxConfig configuracion = new SparkMaxConfig();

    MotorIzq1.setCANTimeout(250);
    MotorIzq2.setCANTimeout(250);
    MotorDer1.setCANTimeout(250);
    MotorDer2.setCANTimeout(250);
    /*
     * Estos son los reguladores y compensadores de energia de los sparks, estos en si nos ayudan a que podamos aumentar y bajar la potencia que consumen
     * el Voltaje de compesacion ("No se escribir ok") hace que el spark si detecta que esta en 10V en la bateria igualmente usa 12V a costa de descargar la bateria
     * basicamente da todo lo posible antes de descargarse o hacer corto.
     * el current limit ayuda a evitar los burnouts. 
     * 
     * 
     */
    
    configuracion.voltageCompensation(Movimiento.Limite_Voltaje_Motor);
    configuracion.smartCurrentLimit(Movimiento.Limite_Motor_Actual);


    // Este es el follower para que cuando se use para seguir el codigo copiando cada detalle, inclusive movimiento, velocidad, giro, etc.
    configuracion.follow(MotorIzq1);
    MotorIzq2.configure(configuracion, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    configuracion.follow(MotorDer1);
    MotorDer2.configure(configuracion, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


    //Este desabilita el follower, lo que hace que durante el deploy de estos no se ejecuten o sean lideres al CANID 1

    
    //Este desabilita la configuracion del MotorIzq2 evitando asi que se duplique y no sean dos can ID 1
    configuracion.disableFollowerMode();
    MotorIzq1.configure(configuracion, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    configuracion.inverted(true);
    MotorDer1.configure(configuracion, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
   

    // Esto es lo que hace que evite el problema de que el can 3 use el 1 como copy en lugar del 4

    }

    /**
     * A si este es un java doc ahora puedo poner un {@Holamundo} sin ningun error
     * 
     * Usa este Drive si nuestro control es de un eje por asi decirlo del de logitech extreme.
     * 
     * @param velocidadx La velocidad que va desde adelante hacia atras.
     * @param Rotaciony La Velocidad de giro
     * @param botones Entradas de aumento de velocidad(Espera porque lo tendiramos que poner?)
     * 
     */
    public void Drive(double VelocidadX, double ZRotacion){

        if (Angulo_Inclinacion_Critico() ){
            Drive.arcadeDrive(0, 0);
        }

        if (Angulo_Maximo()){
            Drive.arcadeDrive(0.5, 0.3);
        }

        Drive.arcadeDrive(VelocidadX, ZRotacion);
        
    }

    /**
     * 
     * Usa tank si nuestro drive es de dos ejes x y en el control por asi decirlo el de xbox.
     * porque se llama tank? bueno es porque se mueve como si fuera un tanque
     * 
     * @param VelocidadIzquierda La velocidad que hace cuando gira
     * @param VelocidadDerecha La velocidad que hace cuando se mueve hacia atras o delante
     * 
     * Por norma usaremos ArcadeDrive no vamos a usar Tank pero almenos tenemos ahi las variables.
     * 
     */

    public void Tank(double VelocidadIzquierda, double VelocidadDerecha){
        Drive.tankDrive(VelocidadIzquierda, VelocidadDerecha);
        
    }

    public void ResetGyro(){
        Gironometro.reset();

    }

    public void CalibracionGyro(){
        Gironometro.calibrate();
    }

    public boolean Angulo_Maximo(){
        Double campo = Math.abs(Gironometro.getAngle());
        return campo > Angulo_Inclinacion_Maximo;

    }

    
    public boolean Angulo_Inclinacion_Critico(){
        Double campo = Math.abs(Gironometro.getAngle());
        return campo > Angulo_Inclinacion_Critico;

    }

    //Se sobrescribe, el codigo haciendo que cada que se ejecute se mantenga, o se mantiene su ciclo
    @Override
    public void periodic(){
        //Aqui podemos usar para declarar variables o cualquier cosas
        //Tambien podemos enviar mensajes al smashdashboard
        //Si esto lo voy a poner en cada periodic

        SmartDashboard.putNumber("Velocidad Can 1:", MotorDer1.get());
        SmartDashboard.putNumber("Velocidad Can 2:", MotorDer2.get());
        SmartDashboard.putNumber("Velocidad Can 3:", MotorIzq1.get());
        SmartDashboard.putNumber("Velocidad Can 4:", MotorIzq2.get());
        SmartDashboard.putNumber("Gyro Ratio", Gironometro.getRate());
        SmartDashboard.putNumber("Gyro Angulo", Gironometro.getAngle());
        SmartDashboard.getBoolean("Precaucion de caida", Angulo_Maximo());
        SmartDashboard.getBoolean("Peligro de Volcadura", Angulo_Inclinacion_Critico());
        SmartDashboard.getBoolean("GiroConectado", Gironometro.isConnected());
    }

    

   }

   /*                                        
                                        
            +++                         
          ++:::++      ++++             
        ++:::::::+    +::::++           
       +::::::::::+  +:::::::+          
      +:::::::::::+ +:::::::::+         
     +:::::::::::::+:::::::::::+        
     +::--::::::::::::----::::::+       
    +::--:::-++++::::-++++--:::::+      
   +:::-:::-+----+::+++---++-::::+      
   +::--::-+------++---+++--+-::::+     
  +::--::-+--++++++++++...+--+-:::+     
  +:---::-+-+......++....-.++-+::::+    
  +:--:::-++.--.........-.--.++-::::+   
  +:--::-++.-..-..###..-....-++-::::+   
  +:--::-+.-...-.#####.-....-.+--:::+   
  +:-+::-+.-.@....###......@@.+--:::+   
  +:-+::-+...@@....#......@@..++-:::+   
  +:-+::-+....@@........@@@....+--:::+  
  +:-+::-+.....@@.......@@@....+--:::+  
  +:-+::-+...@@@..@@@@....@@...++-:::+  
  +:-+::-+..@@...@####@@@@..@...+-:::+  
  +:-+::-+.....@@#....####@@@..@+-:::+  
   +-+::-+@.@@@#..........###@@#+-:::+  
   +-+::-+#@@##....++++++....##.+-:::+  
   +-++:-+.##....++      +......+-:::+  
   +--+:-++.....+       ++......+-::+   
   +--+::-++....++     ++++....+-:::+   
   +:-+::-++....+.+   +...+...++-:::+   
   +:-+::-+-+..+...+ +....+..+++-:::+   
    +-++:-+--+.=....++.....=++-+-::++   
    +:-+:-+---=....++++....=+-+-:::+    
    +:-+:-++-#=....=...+=...=#+-::+     
    +::-+:-+#+=...=++++++=...=+-:++     
    +::--+-++=....=+##+++=...=+-:+      
    +::--++-+=....=####+++=..+--+       
    +::---+-+=...=++##++++=..+-++       
    +::----+++...=++..+++++=.+-+#       
     +:----#++=..=++.......+=+++#       
     +:----#++===++.........++++#       
     +:-----#+#-#+.........++#+#        
     +:------#--##.........#  #         
     ++---------###........#            
      +:--------#++######.#             
      +:--------#+++++#++##             
      +:-------+#++++##+++#             
      +:------+ #++++##+++#             
       +:----+  #++++##++**             
       +:----+  #++++##+*..*            
       ++:--+   #++++##*....*           
        +:-+    ****+#*.....*           
        ++-+   *....*#*......*          
         ++   *......**......*          
          +   *.......*******           
          +  *........*                 
             *........*                 
              ********      
              
              Ummmm.... Este codigo esta mal pero no se en que......
              A, Si el movimiento.
              Created Originally for: Doctor Shinobi
 */
