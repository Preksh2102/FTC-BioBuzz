
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;

@TeleOp(name = "Main TeleOP", group = "Linear OpMode")
public class Teleop extends LinearOpMode {

    Drivetrain drivetrain;
    Intake intake;
    Shooter shooter;
    Transfer transfer;

    boolean shooterOn = false;
    boolean shooterButtonWasPressed = false;

    @Override
    public void runOpMode() {

        // Initialize hardware
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
        transfer = new Transfer(hardwareMap);

        // Ready message
        telemetry.addLine("==============================");
        telemetry.addLine("     ROBOT INITIALIZING");
        telemetry.addLine("==============================");
        telemetry.addData("Drivetrain", "READY");
        telemetry.addData("Intake", "READY");
        telemetry.addData("Shooter", "READY");
        telemetry.addData("Transfer", "READY");
        telemetry.addLine("Press PLAY to start");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        while (opModeIsActive()) {

            // DRIVETRAIN
            drivetrain.drive(
                    gamepad1.left_stick_x,
                    gamepad1.left_stick_y,
                    gamepad1.right_stick_x
            );

            // INTAKE
            if (gamepad1.right_trigger > 0.1) {
                intake.IntakeON();
            } else if (gamepad1.right_bumper) {
                intake.IntakeReverse();
            } else {
                intake.IntakeOFF();
            }

            // TRANSFER
            if (gamepad1.left_trigger > 0.1) {
                transfer.transferON();
            } else if (gamepad1.left_bumper) {
                transfer.transferReverse();
            } else {
                transfer.transferOFF();
            }

            // SHOOTER: X toggles ON/OFF
            if (gamepad1.a && !shooterButtonWasPressed) {
                shooterOn = !shooterOn;
            }

            shooterButtonWasPressed = gamepad1.a;

            if (shooterOn) {
                // Continuously regulate speed toward 3500 RPM
                shooter.updateShooterSpeed();
            } else {
                shooter.ShooterOFF();
            }

            // TELEMETRY
            telemetry.addLine("TELEOP RUNNING");

            telemetry.addData(
                    "Shooter Status",
                    shooterOn ? "ON" : "OFF"
            );

            telemetry.addData(
                    "Target RPM",
                    "%.0f RPM",
                    shooter.getTargetRPM()
            );

            telemetry.addData(
                    "Actual RPM",
                    "%.0f RPM",
                    shooter.getShooterRPM()
            );

            telemetry.addData(
                    "Shooter Power",
                    "%.2f",
                    shooter.getShooterPower()
            );

            telemetry.update();
        }

        // Stop motors when OpMode ends
        shooter.ShooterOFF();
        intake.IntakeOFF();
        transfer.transferOFF();
    }
}
