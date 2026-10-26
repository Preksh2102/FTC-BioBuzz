
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private DcMotorEx shooterMotor;

    private static final double TICKS_PER_REV = 28.0;
    private static final double TARGET_RPM = 2800.0;

    private double shooterPower = 0.0;

    public Shooter(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(
                DcMotorEx.class, "shooter"
        );

        shooterMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    // Start shooter and regulate its speed
    public void ShooterON() {
        updateShooterSpeed();
    }

    public void ShooterOFF() {
        shooterPower = 0.0;
        shooterMotor.setPower(0.0);
    }

    public void ShooterReverse() {
        shooterPower = -1.0;
        shooterMotor.setPower(-1.0);
    }

    // Update motor power to maintain 3500 RPM
    public void updateShooterSpeed() {
        double currentRPM = getShooterRPM();

        // Initial power estimate
        if (shooterPower <= 0.0) {
            shooterPower = 0.60;
        }

        // Adjust power according to speed error
        double error = TARGET_RPM - currentRPM;
        double adjustment = error * 0.0001;

        shooterPower += adjustment;

        // Keep power within safe motor command limits
        shooterPower = Math.max(0.0,
                Math.min(1.0, shooterPower));

        shooterMotor.setPower(shooterPower);
    }

    public double getShooterPower() {
        return shooterMotor.getPower();
    }

    public double getShooterRPM() {
        return shooterMotor.getVelocity()
                * 60.0 / TICKS_PER_REV;
    }

    public double getTargetRPM() {
        return TARGET_RPM;
    }
}
