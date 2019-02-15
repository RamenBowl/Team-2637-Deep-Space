/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Enumeration;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.Autonomous.CatzDriveStraight;
import frc.Autonomous.CatzTurn;
import frc.Vision.UDPServerThread;
import frc.Vision.VisionObjContainer;
import frc.Vision.VisionObject;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  public static AHRS navx;
  XboxController xboxDrv;
  XboxController xboxAux;
  int DRV_XBOX_PORT;
  int AUX_XBOX_PORT;
  int t = 0;
  private UDPServerThread server;
  
  private boolean m_firstRP = true;

  double distance;
  double heading;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    navx = new AHRS(SPI.Port.kMXP,(byte)200);  
    
    server = new UDPServerThread();
   }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() 
  {
   
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() 
  {
      
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    if (m_firstRP)
    {
      //starting UDP Server
      server.start();

      m_firstRP = false;
    }

    /*
    VisionObject vo = VisionObjContainer.get();

    if (vo != null)
    {
      System.out.println(vo);
    }
    */

    /* For print out
    Enumeration<VisionObject> vobjs = VisionObjContainer.getElements();
  
    boolean newLine = false;

    if (vobjs != null)
    {
      //System.out.print("vobjs is not null");
      while (vobjs.hasMoreElements())
      {
        String str = vobjs.nextElement().toString();

        System.out.print(str + '\t');          

        newLine = true;
      }
    }

    if (newLine)
    {
      System.out.println();
    }

    /*/
    

    if(xboxDrv.getAButton())
    {
      distance = VisionObjContainer.get("vis").getDistance();
      heading = VisionObjContainer.get("vis").getHeading();
      
      System.out.println(distance + " " + heading);
      
      CatzDriveStraight.PIDDriveNoTrig(0.0, distance, 5);

      //CatzTurn.PIDturn(Double.parseDouble(heading), 5);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }
}
