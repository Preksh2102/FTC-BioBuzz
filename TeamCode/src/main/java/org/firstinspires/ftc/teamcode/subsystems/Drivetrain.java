package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {

    DcMotor leftBack;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor rightFront;

    public Drivetrain(HardwareMap hardwareMap) {

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        leftBack.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.FORWARD);

        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void drive(double leftX, double leftY, double rightX) {

        double forward = -leftY;
        double strafe = -leftX;
        double rotate = rightX;

        double leftFrontPower =
                forward + strafe + rotate;

        double leftBackPower =
                forward - strafe + rotate;

        double rightFrontPower =
                forward - strafe - rotate;

        double rightBackPower =
                forward + strafe - rotate;

        // Normalize so powers stay between -1 and 1
        double max = Math.max(
                1.0,
                Math.max(
                        Math.abs(leftFrontPower),
                        Math.max(
                                Math.abs(leftBackPower),
                                Math.max(
                                        Math.abs(rightFrontPower),
                                        Math.abs(rightBackPower)
                                )
                        )
                )
        );

        leftFrontPower /= max;
        leftBackPower /= max;
        rightFrontPower /= max;
        rightBackPower /= max;

        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
    }
}
