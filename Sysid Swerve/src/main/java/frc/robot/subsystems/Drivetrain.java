// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.sysid.SysIdRoutineLog;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

  private CANSparkMax FRMotor = new CANSparkMax(8, MotorType.kBrushless);
  private CANSparkMax FLMotor = new CANSparkMax(5, MotorType.kBrushless);
  private CANSparkMax BRMotor = new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax BLMotor = new CANSparkMax(3, MotorType.kBrushless);

  private double gearRatio = 6.12/1;
  private double metersPerWheelRotation = 2 * Math.PI * Units.inchesToMeters(4);
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    BRMotor.follow(FRMotor);
    BLMotor.follow(FLMotor);
    
    FRMotor.getEncoder().setPositionConversionFactor(gearRatio/metersPerWheelRotation); // rotations per meter
    FLMotor.getEncoder().setPositionConversionFactor(gearRatio/metersPerWheelRotation);

    FRMotor.getEncoder().setVelocityConversionFactor(gearRatio/metersPerWheelRotation * 60);
    FLMotor.getEncoder().setVelocityConversionFactor(gearRatio/metersPerWheelRotation * 60);
  }

  @Override
  public void periodic() {


    // This method will be called once per scheduler run
  }

  public void setSpeedFR(double speed) {
    FRMotor.setVoltage(speed);
  }
  public void setSpeedFL(double speed) {
    FLMotor.setVoltage(speed);
  }

  public void setSpeed(double speed) {
    FRMotor.setVoltage(speed);
    FLMotor.setVoltage(speed);
  }

  public double getVoltageFR() {
    return FRMotor.getBusVoltage();
  }
  public double getVoltageFL() {
    return FLMotor.getBusVoltage();
  }

  public double getDistanceFR() {
    return FRMotor.getEncoder().getPosition();
  }
  public double getDistanceFL() {
    return FLMotor.getEncoder().getPosition();
  }

  public double getVelocityFR() {
    return FRMotor.getEncoder().getVelocity();
  }
  public double getVelocityFL() {
    return FLMotor.getEncoder().getVelocity();
  }
}
