// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.controls.VoltageOut;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Unit;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.sysid.SysIdRoutineLog;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.RobotContainer;

public class SysidDrivetrain extends SubsystemBase {

  

  private Drivetrain drivetrain;
  
  private final MutableMeasure<Voltage> maxVoltage = MutableMeasure.mutable(Units.Volts.of(12));


  private final MutableMeasure<Voltage> appliedVoltage = MutableMeasure.mutable(Units.Volts.of(0));

  private final MutableMeasure<Distance> distance = MutableMeasure.mutable(Units.Meters.of(0));

  private final MutableMeasure<Velocity<Distance>> velocity = MutableMeasure.mutable(Units.MetersPerSecond.of(0));

  private SysIdRoutine sysId = new SysIdRoutine(
                        new SysIdRoutine.Config(null, maxVoltage, null),
                        new SysIdRoutine.Mechanism(
                                    (Measure<Voltage> volts) -> {
                                      drivetrain.setSpeedFL(volts.in(Units.Volts));
                                      drivetrain.setSpeedFR(volts.in(Units.Volts));
                                    }, 
                                    log -> {
                                      log.motor("drive-left")
                                      .voltage(appliedVoltage.mut_replace(drivetrain.getVoltageFL(), Units.Volts))
                                      .linearPosition(distance.mut_replace(drivetrain.getDistanceFL(), Units.Meters))
                                      .linearVelocity(velocity.mut_replace(drivetrain.getVelocityFL(), Units.MetersPerSecond));

                                      // log.motor("drive-right")
                                      // .voltage(appliedVoltage.mut_replace(drivetrain.getVoltageFR(), Units.Volts))
                                      // .linearPosition(distance.mut_replace(drivetrain.getDistanceFR(), Units.Meters))
                                      // .linearVelocity(velocity.mut_replace(drivetrain.getVelocityFR(), Units.MetersPerSecond));
                                    },
                                    this));

  /** Creates a new SysidDrivetrain. */
  public SysidDrivetrain(Drivetrain _drivetrain) {
    drivetrain = _drivetrain; 
  }

  @Override
  public void periodic() {

    


    // This method will be called once per scheduler run
  }
  // slow
  public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
    return sysId.quasistatic(direction);
  }

  // fast
  public Command sysIdDynamic(SysIdRoutine.Direction direction) {
    return sysId.dynamic(direction);
  }
}
