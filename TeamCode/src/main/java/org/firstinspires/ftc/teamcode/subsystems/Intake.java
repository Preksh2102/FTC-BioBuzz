package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    DcMotor intakeMotor;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void IntakeON() {
        intakeMotor.setPower(1.0);
    }

    public void IntakeOFF() {
        intakeMotor.setPower(0.0);
    }

    public void IntakeReverse() {
        intakeMotor.setPower(-1.0);
    }
}
