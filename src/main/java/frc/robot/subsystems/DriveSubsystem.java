/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;


public class DriveSubsystem extends SubsystemBase {
  //
  // Creates a new DriveSubsystem.
  //
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);

  private static final double kCountsPerRevolution = 1440.0;
  private static final double kWheelDiameterInch = 2.75591; // 70 mm

    private final WPI_VictorSPX leftMaster = new WPI_VictorSPX(Constants.kleftMasterPort); 
    private final WPI_VictorSPX leftSlave = new WPI_VictorSPX(Constants.kleftSlavePort); 
    private final WPI_VictorSPX rightMaster = new WPI_VictorSPX(Constants.krightMasterPort); 
    private final WPI_VictorSPX rightSlave = new WPI_VictorSPX(Constants.krightSlavePort); 

    SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMaster, leftSlave);
    SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMaster, rightSlave);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
  
    public void manualDrive(double move, double turn) {
      if (Math.abs(move) < 0.1) move = 0;
      if (Math.abs(turn) < 0.1) turn = 0;
      drive.arcadeDrive(move, turn);
      m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
      m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
      resetEncoders();
    
    } 

    public void manualDrive2(double move, double turn) {
      drive.arcadeDrive(move, turn);   
     }

  public DriveSubsystem() {
    drive.setSafetyEnabled(false);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }
  public int getLeftEncoderCount() {
    return m_leftEncoder.get();
  }
  
  public int getRightEncoderCount() {
    return m_rightEncoder.get();
  }
  public double getLeftDistanceInch() {
    return m_leftEncoder.getDistance();
  }

  public double getRightDistanceInch() {
    return m_rightEncoder.getDistance();
  }

  public double getAverageDistanceInch() {
    return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
  }

}
