package frc.robot

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj2.command.Command
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.wpilibj2.command.CommandScheduler
import kotlin.math.abs

/**
 * The VM is configured to automatically run this object (which basically functions as a singleton class),
 * and to call the functions corresponding to each mode, as described in the TimedRobot documentation.
 * This is written as an object rather than a class since there should only ever be a single instance, and
 * it cannot take any constructor arguments. This makes it a natural fit to be an object in Kotlin.
 *
 * If you change the name of this object or its package after creating this project, you must also update
 * the `Main.kt` file in the project. (If you use the IDE's Rename or Move refactorings when renaming the
 * object or package, it will get changed everywhere.)
 */
object Robot : TimedRobot()
{
    /** The autonomous command to run. It is set in [autonomousInit]. */
    private var autonomousCommand: Command?  = null
    private var controller0: XboxController  = XboxController(0)

    private var leftSide: CANSparkMax = CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless) // idk about this
    private var rightSide: CANSparkMax = CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless) // idk about this
    var NTTable: NetworkTable = NetworkTableInstance.getDefault().getTable("randomshit")



    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    override fun robotInit()
    {
        // Access the RobotContainer object so that it is initialized
        RobotContainer

    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * This runs after the mode specific periodic methods, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    override fun robotPeriodic()
    {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run()
    }

    /** This method is called once each time the robot enters Disabled mode.  */
    override fun disabledInit()
    {

    }

    override fun disabledPeriodic()
    {

    }

    /** This autonomous runs the autonomous command selected by your [RobotContainer] class.  */
    override fun autonomousInit()
    {
        // We store the command as a Robot property in the rare event that the selector on the dashboard
        // is modified while the command is running since we need to access it again in teleopInit()
        autonomousCommand = RobotContainer.selectedAutonomousCommand
        autonomousCommand?.schedule()
    }

    /** This method is called periodically during autonomous.  */
    override fun autonomousPeriodic()
    {
    }

    override fun teleopInit()
    {
        // This makes sure that the autonomous stops running when teleop starts running. If you want the
        // autonomous to continue until interrupted by another command, remove this line or comment it out.
        autonomousCommand?.cancel()
    }

    /** This method is called periodically during operator control.  */
    override fun teleopPeriodic()
    {
        val x = controller0.leftX
        val y = controller0.leftY

        var rightVoltage = 0.0
        var leftVoltage = 0.0

        val difference = 2 - abs(x)

        if (x > -0.9 && x < 0.9){
            rightVoltage = -y * abs(y)
            leftVoltage = -y * abs(y)
        } else if (y > -0.9 && y < 0.9) {
            rightVoltage = x * abs(x)
            leftVoltage = -x * abs(x)
        } else if (y < 0 && x > 0) {
            rightVoltage = y * difference
            leftVoltage = y * abs(x)
        }
        else if (y < 0 && x < 0) {
            rightVoltage = y * abs(x)
            leftVoltage = y * difference
        }
        else if (y > 0 && x > 0) {
            rightVoltage = -y * difference
            leftVoltage = -y * abs(x)
        }
        else if (y > 0 && x < 0) {
            rightVoltage = -y * abs(x)
            leftVoltage = -y * difference
        }
        // var forward = controller0.leftY * abs(controller.leftY) * 5
        // var rotation = -controller0.leftX * abs(controller.leftX) * 1

        rightSide.setVoltage(leftVoltage * 6)
        leftSide.setVoltage(rightVoltage * 6)
    }



    override fun testInit()
    {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll()
    }

    /** This method is called periodically during test mode.  */
    override fun testPeriodic()
    {

    }
}