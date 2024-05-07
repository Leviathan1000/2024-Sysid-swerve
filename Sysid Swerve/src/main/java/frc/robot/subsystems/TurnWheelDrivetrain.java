// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurnWheelDrivetrain extends SubsystemBase {

  private CANSparkMax FRMotor = new CANSparkMax(4, MotorType.kBrushless);
  private CANSparkMax FLMotor = new CANSparkMax(2, MotorType.kBrushless);
  private CANSparkMax BLMotor = new CANSparkMax(7, MotorType.kBrushless);
  private CANSparkMax BRMotor = new CANSparkMax(6, MotorType.kBrushless);

  private CANcoder BREncoder = new CANcoder(3);
  private CANcoder BLEncoder = new CANcoder(4);
  private CANcoder FLEncoder = new CANcoder(1);
  private CANcoder FREncoder = new CANcoder(2);

  /** Creates a new TurnWheelDrivetrain. */
  public TurnWheelDrivetrain() {

  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("FrontLeftAngle", getBREncoderAngle());
    SmartDashboard.putNumber("FrontRightAngle", getBLEncoderAngle());
    SmartDashboard.putNumber("BackLeftAngle", getFREncoderAngle());
    SmartDashboard.putNumber("BackRightAngle", getFLEncoderAngle());

    // This method will be called once per scheduler run
  }


  public double getBREncoderAngle() {
    return (BREncoder.getAbsolutePosition().getValueAsDouble()*360)-310.34+180; // angle in degrees minus the offset
  }
  public double getBLEncoderAngle() {
    return (BLEncoder.getAbsolutePosition().getValueAsDouble()*360)-183.63+180; // angle in degrees minus the offset
  }
  public double getFREncoderAngle() {
    return (FREncoder.getAbsolutePosition().getValueAsDouble()*360)-241.56+180; // angle in degrees minus the offset
  }
  public double getFLEncoderAngle() {
    return (FLEncoder.getAbsolutePosition().getValueAsDouble()*360)-249.52+180; // angle in degrees minus the offset
  }

  public void setBRSpeed(double speed) {
    BRMotor.setVoltage(speed);
  }
  public void setBLSpeed(double speed) {
    BLMotor.setVoltage(speed);
  }
  public void setFLSpeed(double speed) {
    FLMotor.setVoltage(speed);
  }
  public void setFRSpeed(double speed) {
    FRMotor.setVoltage(speed);
  }
}
