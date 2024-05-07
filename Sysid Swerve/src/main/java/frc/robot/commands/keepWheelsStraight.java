// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurnWheelDrivetrain;

public class keepWheelsStraight extends Command {

  private TurnWheelDrivetrain turnWheels;

  private double kp = 0.1;
  private double ki = 0.00001;
  private double kd = 0; // 1.1;

  private PIDController turnPIDBL = new PIDController(kp, ki, kd);
  private PIDController turnPIDBR = new PIDController(kp, ki, kd);
  private PIDController turnPIDFL = new PIDController(kp, ki, kd);
  private PIDController turnPIDFR = new PIDController(kp, ki, kd);

  private double counter = 0;

  /** Creates a new keepWheelsStraight. */
  public keepWheelsStraight(TurnWheelDrivetrain turnWheelDrivetrain) {
    addRequirements(turnWheelDrivetrain);

    turnWheels = turnWheelDrivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turnPIDBL.enableContinuousInput(-180, 180);
    turnPIDBR.enableContinuousInput(-180, 180);
    turnPIDFL.enableContinuousInput(-180, 180);
    turnPIDFR.enableContinuousInput(-180, 180);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double angleBL = turnPIDBL.calculate(turnWheels.getBLEncoderAngle(), 0);
    double angleBR = turnPIDBR.calculate(turnWheels.getBREncoderAngle(), 0);
    double angleFL = turnPIDFL.calculate(turnWheels.getFLEncoderAngle(), 0);
    double angleFR = turnPIDFR.calculate(turnWheels.getFREncoderAngle(), 0);

    turnWheels.setBLSpeed(angleBL);
    turnWheels.setBRSpeed(angleBR);
    turnWheels.setFLSpeed(angleFL);
    turnWheels.setFRSpeed(angleFR);

    SmartDashboard.putNumber("UpdateRate", counter);

    counter += 0.04;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
