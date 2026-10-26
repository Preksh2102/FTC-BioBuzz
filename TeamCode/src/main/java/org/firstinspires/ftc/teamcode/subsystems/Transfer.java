package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer {
    DcMotor transferMotor;

    public Transfer(HardwareMap hardwareMap){
        transferMotor = hardwareMap.get(DcMotor.class, "transfer");
        transferMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void transferON(){
        transferMotor.setPower(1.0);
    }
    public void transferOFF(){
        transferMotor.setPower(0.0);
    }
    public void transferReverse(){
        transferMotor.setPower(-1.0);
    }
}
