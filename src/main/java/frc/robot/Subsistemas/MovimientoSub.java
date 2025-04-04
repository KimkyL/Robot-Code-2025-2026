//Este es el subsistema del movimiento es el movimiento del robot o basicamente la base
package frc.robot.Subsistemas;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.Constantes;
import frc.robot.Constantes.Movimiento;
import frc.robot.Subsistemas.MovimientoSub;
//Dejo imports sin usar para saber que codigo es el correcto LOL :S

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/* Bueno este es el movimiento. es basicamente todo el movimiento que se le hace al robot desde girar hasta moverse hacia adelante y atras. 
 * Aqui usamos CIM's son motores que no tienen controladores pero aun asi son buenos para el trabajo.
*/

public class MovimientoSub extends SubsystemBase{


   private final SparkMax MotorIzq1; //MotorIzquierdo LB Lider
   private final SparkMax MotorIzq2; //MotorIzquierdo LA Seguidor
   private final SparkMax MotorDer1; //MotorDerecho RB Lider 
   private final SparkMax MotorDer2; //MotorDerecho RA Seguidor
   private final DifferentialDrive Drive; // Usado para los movimientos estilo tank

    /**
    * El subsistema para el movimiento del robot
    */
    public MovimientoSub(){

    MotorIzq1 = new SparkMax(Movimiento.MotorIzq1ID, MotorType.kBrushed); // LB Lider
    MotorIzq2 = new SparkMax(Movimiento.MotorIzq2ID, MotorType.kBrushed); // LA Seguidor
    MotorDer1 = new SparkMax(Movimiento.MotorDer1ID, MotorType.kBrushed); // RB Lider
    MotorDer2 = new SparkMax(Movimiento.MotorDer2ID, MotorType.kBrushed); // RA Seguidor
    
    Drive = new DifferentialDrive(MotorIzq1, MotorDer1);

    SparkMaxConfig configuracion = new SparkMaxConfig();
    /*
     * Estos son los reguladores y compensadores de energia de los sparks, estos en si nos ayudan a que podamos aumentar y bajar la potencia que consumen
     * el Voltaje de compesacion ("No se escribir ok") hace que el spark si detecta que esta en 10V en la bateria igualmente usa 12V a costa de descargar la bateria
     * basicamente da todo lo posible antes de descargarse o hacer corto.
     * el current limit ayuda a evitar los burnouts. 
     */
    configuracion.voltageCompensation(Constantes.Movimiento.Limite_Voltaje_Motor);
    configuracion.smartCurrentLimit(Constantes.Movimiento.Limite_Motor_Actual);


    // Este es el follower para que cuando se use para seguir el codigo copiando cada detalle, inclusive movimiento, velocidad, giro, etc.
    configuracion.follow(MotorIzq1);
    MotorIzq2.configure(configuracion, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    configuracion.follow(MotorDer1);
    MotorDer2.configure(configuracion, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


    //Este desabilita el follower, lo que hace que durante el deploy de estos no se ejecuten o sean lideres al CANID 1

    
    //Este desabilita la configuracion del MotorIzq2 evitando asi que se duplique y no sean dos can ID 1
    configuracion.disableFollowerMode();
    MotorIzq2.configure(configuracion, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    configuracion.disableFollowerMode();
    MotorDer2.configure(configuracion, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    configuracion.inverted(true);

    // Nota si no queremos usar el follow podemos usar estas lineas de codigo

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

    //Se sobrescribe, el codigo haciendo que cada que se ejecute se mantenga, o se mantiene su ciclo
    @Override
    public void periodic(){
        //Aqui podemos usar para declarar variables o cualquier cosa
        //Tambien podemos enviar mensajes al smashdashboard
        //Si esto lo voy a poner en cada periodic
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
