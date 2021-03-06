// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

public class RobotChallanges extends CommandBase {
  /**
   * Creates a new AutoCommand1.
   */
  // private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  private final DriveSubsystem m_driveSubsystem;

  private double startTime; 
  
  public RobotChallanges(DriveSubsystem _drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveSubsystem = _drive;
    addRequirements(_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double time = Timer.getFPGATimestamp();

    if (time - startTime < 3) {
     m_driveSubsystem.manualDrive2(0.5 , .3);
    } 
    else if (time - startTime < 9) {
      m_driveSubsystem.manualDrive2(0.5 , -.3);
      } else {
      m_driveSubsystem.manualDrive2(0 , 0);
      
     }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
