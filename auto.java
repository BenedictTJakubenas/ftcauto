package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class auto extends LinearOpMode {
    private static final String vuforiakey = "Ab67bSb/////AAABmU/UuH7MrEcRuBHsH0xX4GZc9trNnRkb1UN0doFI9RHF/P7Pnv7ahctkrLQMVC/maDISH9b7D3AgAJtPaoRGEIF5yCNNHN9fp7xZq+JvuK3l8+twFj0/+el1eebfHEDyzgqZCALNX6wpCLhiumfM+/Ssxl87SQ2Fnbin/gVOoQ6IF7HDR9mfJs+jEN081M0dGN1xciUAi/sWNchbpjIybOdgFp/1AmnzHXBsD7Mv+4Cl7/jlpYlavVONnZfXJm1aMPlmuvUpQ3gSAI1iG59eF3AO4AKBlaXiKGNSitvdcpKIVMd4NmBnUIk1ncIdnCZxMJtxUE6h7JVfAqK1T+lU/BfI/Rg6AOiin2RHf9A4nbD6";
    private OpenGLMatrix lastLocation   = null;
    private VuforiaLocalizer vuforia    = null;
    private VuforiaTrackables targets   = null ;
    private WebcamName webcamName       = null;

    private boolean targetVisible       = false;



    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        webcamName = hardwareMap.get(WebcamName.class, "webcam1" );
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
     

        parameters.vuforiaLicenseKey = vuforiakey;

        parameters.cameraName = webcamName;


        parameters.useExtendedTracking = false;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        targets = this.vuforia.loadTrackablesFromAsset("Cone");


        VuforiaTrackableDefaultListener star = (VuforiaTrackableDefaultListener) targets.get(0).getListener();
        VuforiaTrackableDefaultListener triangle = (VuforiaTrackableDefaultListener) targets.get(1).getListener();
        VuforiaTrackableDefaultListener checker = (VuforiaTrackableDefaultListener) targets.get(2).getListener();

        while (opModeIsActive()) {
            if (triangle.isVisible()) {
                telemetry.addLine("triangle");
            } else if (checker.isVisible()) {
                telemetry.addLine("checker");

            } else if (star.isVisible()) {
                telemetry.addLine("star");
            } else {
                telemetry.addLine("noshape");
            }
            telemetry.update();
        }
    }
}
