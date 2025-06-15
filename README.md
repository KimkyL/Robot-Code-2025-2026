# Documentación del Robot - Raccoons [8741]!

## Información General
- **Nombre del Robot:** [La Criatura]
- **Temporada:** 2025
- **Juego:** [Reefscape]
- **Fecha de última actualización:** Junio 2025

## Tabla de Contenidos
1. [Descripción General](#descripción-general)
2. [Arquitectura del Código](#arquitectura-del-código)
3. [Subsistemas](#subsistemas)
4. [Comandos](#controles)
5. [Configuración](#configuración)
6. [Troubleshooting](#troubleshooting)

## Descripción General
Breve descripción de las funciones principales del robot y su estrategia de juego.
Nuestro robot, teniendo las carateristicas de: Intake,Elevador, y Neumatica.

## Arquitectura del Código

### Estructura de Carpetas
```
src/
├── main/
│   └── java/
│       └── frc/
│           └── robot/
│               ├── Autos/
│               ├── Subsistemas/
│               │   ├── ElevadorSub.java
│               │   ├── IntakeSub.java
│               │   ├── MovimientoSub.java
│               │   └── SolenoidSub.java
│               ├── comandos/
│               │   ├── ApagarCompressorCom.java
│               │   ├── EncenderCompressorCom.java
│               │   ├── ElevadorAbajoCom.java
│               │   ├── ElevadorArribaCom.java
│               │   ├── IntakeAgarrarCom.java
│               │   ├── IntakeSoltarCom.java
│               │   ├── MovimientoCom.java
│               │   ├── SolenoideExtenderCom.java
│               │   ├── SolenoideRetraerCom.java
│               │   └── SolenoidelnvertirCom.java
│               ├── Constantes.java
│               ├── Controles.java
│               ├── Main.java
│               └── Robot.java
│
├── deploy/
    └── example.txt

```

## Subsistemas

### Intake (Sistema de Recolección)
**Archivo:** `Subsistemas/IntakeSub.java`

**Descripción:** Sistema encargado de recoger y manipular los Algaes del juego.

#### Motores y Sensores
- **Motor/Sensor Principal:** SparkMax (CAN ID: 6)
- **Encoder:** Sin Encoder.

#### Métodos Principales

##### `IntakeAgarrarCom()`
**Función:** Agarra el mecanismo para Recoger Objetos.

**Comportamiento:**
- Activa el motor del intake en dirección positiva
- Velocidad configurada: 0.5 (50% de potencia)
- Se detiene automáticamente al alcanzar el sensor de límite superior

**Ejemplo de uso:**
```java
// En un comando o método de control
public void execute(){
        Intake_m.IntakeEncendido(Constantes.IntakeCons.Agarrar);
    }
```

##### `IntakeSoltarCom ()`
**Función:** Suelta el mecanismo para dejar el objeto.

**Comportamiento:**
- Activa el motor del intake en dirección negativa
- Velocidad configurada: -0.5 (50% de potencia en reversa)

##### `IntakeEncendido()`
**Función:** Detiene completamente el movimiento del intake.

**Uso típico:** Comando de emergencia o al finalizar operaciones.

#### Estados del Intake
- **Agarrar:** lista para recoger elementos
- **Soltar:** Lista Para transporte o liberación
- **Parar:** Motor detenido

#### Configuración
```java
// Constantes.java
public static final class IntakeCons{
    public static final int IntakeMotor = 6; // El CanID del Motor del Intake (Garra)
    public static final Double Soltar = -0.5; // La velocidad con la que Suelta
    public static final Double Agarrar = 0.5; // La velocidad con la que Agarra
}
    
```

### Elevador
**Archivo:** `Subsistemas/ElevadorSub.java`

**Descripción:** Controla el sistema de elevador, junto con el sistema de Intake.

#### Motores y Sensores
- **Motor/Sensor Principal:** SparkMax, REV NEO Brushless Motor V1.1 (CAN ID: 6)
- **Encoder:** Incluido con el NEO de REV Robotics.

#### Métodos Principales

##### `ElevadorAbajoCom()`
**Función:** Eleva el mecanismo junto con el Intake para ajustar la altura.

**Comportamiento:**
- Baja el elevador para poder dejarlo en el Human Player.
- Velocidad Configurada: -0.5 (50% de potencia en reversa)
- Se baja automaticamente cuando el contador llega a cero.

**Ejemplo de uso:**
```java
     public void execute() {
       m_elevador.ElevadorEncendido(ElevadorConstantes.bajar);
     }
```

##### `ElevadorArribaCom()`
**Función:** Sube el mecanismo junto con el intake para dejarlo en su altura original.

**Comportamiento:**
- Sube el elevador para tirar los Algaes del coral rack.
- Velocidad Configurada: 0.5 (50% de potencia)
- Se baja automaticamente cuando el contador llega a cero.

**Ejemplo de uso:**
```java
    public void execute() {
       m_elevador.ElevadorEncendido(ElevadorConstantes.Subir);
     }
```

#### Configuración
```java
// Constantes.java
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

```
### Solenoid (Sistema Neumatico)
**Archivo:** `Subsistemas/SolenoidSub.java`

**Descripción:** Controla el sistema neumático del robot, incluyendo compresor.

#### Componentes
- **Compresor:** PCM (Pneumatic Control Module)
- **Solenoide:** Soleonide para solo subir y bajar.
- **Sensor de Presión:** Incluido en el sistema de tuberias

#### Métodos Principales

##### `EncenderCompressor()`
**Función:** Activa el compresor para generar presión en el sistema neumático.

**Comportamiento:**
- Enciende el compresor automaticamente
- Mantiene la presion a 120PSI 
- Se apaga automaticamente cuando llega a su presion maxima o es apagado.

**Ejemplo de uso:**
```java
     public void CompressorPrendido(){
        Compressor.enableDigital();
  }
```

##### `ApagarCompressor()`
**Función:** Desactiva manualmente el compressor.

**Comportamiento:**
- Apaga el compressor llamando su funcion

**Uso Tipico:**
- Mantenimiento - Ahorro de energia
- Situaciones de emergencia

**Ejemplo de uso:**
```java
    public void CompressorApagado(){
        Compressor.disable();
   }
```

##### `Extender()`

**Funcion:** Extiende el piston neumatico.

**Estados:** Extender/Retraer/Invertir

##### `Retraer()`

**Funcion:** Retrae el piston neumatico.

##### `Invertir()`

**Funcion:** Invierte el piston a Retraer/Invertir.


#### Configuración
```java
    // Constantes.java
    public static final class SolenoidConf{
        public static final int CompressorID = 0; //El CanID del Compressor (No podemos cambiarlo ya que este puede afectar y dar Hal Error si lo cambiamos)
        public static final int SolenoidID = 4; //El supuesto 'CanID' del Solenoide (Este puede estar en cualquier modulo)
    }
```

### Componentes del Robot

| Parte         | Modelo                     | CAN ID | Detalles Adicionales       |
|---------------|----------------------------|--------|-----------------------------|
| Intake Motor  | SparkMax + 775pro          | 6      | Sin encoder                 |
| Elevador      | SparkMax + NEO Brushless   | 5      | Encoder integrado           |
| Solenoide     | Single Solenoid            | 7      | Controlado vía PCM ID 0     |
| Compressor    | PCM                        | 0      | Activación digital automática |



## Controles

### IntakeSoltarCom
**Función:** Comando para soltar el intake.

**Subsistema:** IntakeSub

**Trigger:** Botón A del control

**Condiciones:**
- No debe de ver doble configuracion o misma configuracion del intake

### IntakeAgarrarCom
**Función:** Comando para agarrar el intake.

**Subsistema:** IntakeSub

**Trigger:** Botón B del control

### ElevadorArribaCom
**Función:** Commando para subir el elevador.

**Subsistema:** ElevadorSub

**Trigger:** Bumper Derecho del control.


### ElevadorBajarCom
**Función:** Comando para bajar el elevador.

**Subsistema:** ElevadorSub

**Trigger:** Bumper Izquierdo del control.


### EncenderCompressorCom
**Función:** Comando para activar el sistema neumático.

**Subsistema:** Solenoid

**Trigger:** Botón Start del control.

**Método usado:** `SolenoideSUB.CompressorPrendido();`

**Condiciones:** Mantener el PCM en 0 para evitar errores fatales.

### CompressorApagadoCom
**Función:** Comando para desactivar el compresor manualmente.

**Subsistema:** Solenoid  

**Trigger:** Botón Back del control.

**Método usado:** `SolenoideSUB.CompressorApagado();`


### Calibración
1. **Límites de Seguridad:** Configurados por sensores físicos
3. **Velocidades:** Ajustadas para movimiento suave y preciso

## Troubleshooting

### Problemas Comunes

#### El intake no se mueve
**Posibles causas:**
- Motor desconectado (verificar CAN Bus)
- Cables quemados/Puenteados Accidentalmente.
- Código no deployado correctamente

**Solución:**
1. Verificar conexiones físicas
2. Revisar Driver Station para errores CAN (Generalmente es Can No TOKEN)
3. Re-deployar código

#### El intake se mueve muy lento
**Posibles causas:**
- Batería baja
- Valores de velocidad incorrectos
- Resistencia mecánica

**Solución:**
1. Verificar voltaje de batería (Puedes Revisarlo en tu Driver Station)
2. Ajustar constantes de velocidad
3. Revisar mecanismo por obstrucciones


### Autónomo
Actualemte desactivado, se planea proximamente usar FRC PathPlanner.

## Historial de Cambios

### Versión 1.0 (Enero 2025)
- Arreglo de Legacy (Timed-Robot)
- Importaciones de Rev y arreglos en las librerias

### Versión 1.1 (Enero - Febrero 2025)
- Añadido Hibrid Structure Dejando obsoleto Timed-Robot.
- Añadido Subsistema Intake.java
- Añadido Subsistema Movimiento.java
- Añadido Subsistema Elevador.java
- Añadido Subsistema Aire.java
- Añadido Autonomo.

### Versión 1.2 (Abril 2025)
- Cambio de Hibrid Structure a Command-Based dejando Obsoleto Hibird Structure with No commands.
- Arreglos en MovimientoSub.java Por que el Can Id 3 copiaba al 1 en lugar del 4
- Cambio de Nombre de Aire.java -> SolenoidSub.java 
- Arreglado problema en Solenoid.java. Que salia HalError por poner un numero que no sea 0 al PCM.
- Removido Autonomo, Para posteriormente en proximas versiones ser remplazado por Path-Planning.
- Restructurado Subsistemas para Intake, Movimiento y Elevador.
- Añadido la Estructura Command-Based.

### Version 1.3 (Julio 2025)
- **Proximamente**

## Notas para el Equipo
- Testear por individual el codigo y entender y comprender su estructura
- Documentar cualquier cambio en este archivo
- Realizar backup del código antes de modificaciones importantes

## Contacto
**Programadores principales:** [Angel David Morales Marrufo, Cesar Augusto Ramirez]
**Mentores de programación:** [Cesar Augusto Ramirez]
**Última revisión:** [15/06/2025]
