package frc.robot.subsystems

//import com.revrobotics.CANSparkMax
//import edu.wpi.first.wpilibj.XboxController
//import edu.wpi.first.wpilibj2.command.SubsystemBase
//import kotlin.math.abs
//
//class DriveTrainSubsystem(left: CANSparkMax, right: CANSparkMax, controller: XboxController): SubsystemBase() {
//    private var controller: XboxController
//    private var left: CANSparkMax
//    private var right: CANSparkMax
//
//    init {
//        this.controller = controller
//        this.left = left
//        this.right = right
//    }
//
//    override fun periodic() {
//        val x = controller.leftX
//        val y = controller.leftY
//
//        var rightVoltage = 0.0
//        var leftVoltage = 0.0
//
//        val difference = 2 - abs(x)
//
//        if (x > -0.9 && x < 0.9){
//            rightVoltage = y
//            leftVoltage = y
//        } else if (y > -0.9 && y < 0.9) {
//            rightVoltage = -x
//            leftVoltage = x
//        } else if (y < 0 && x > 0) {
//            rightVoltage = y * difference
//            leftVoltage = y * abs(x)
//        }
//        else if (y < 0 && x < 0) {
//            rightVoltage = y * abs(x)
//            leftVoltage = y * difference
//        }
//        else if (y > 0 && x > 0) {
//            rightVoltage = -y * difference
//            leftVoltage = -y * abs(x)
//        }
//        else if (y > 0 && x < 0) {
//            rightVoltage = -y * abs(x)
//            leftVoltage = -y * difference
//
//        }
//
//        right.setVoltage(leftVoltage * 6)
//        left.setVoltage(rightVoltage * 6)
//    }
//}