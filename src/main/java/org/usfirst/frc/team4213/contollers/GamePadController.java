package org.usfirst.frc.team4213.contollers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Generic Game Pad controller
 * Thread-Safe singleton instance created at start-up accessible through GamePadController.INSTANCE
 */
public class GamePadController {

    public final static GamePadController INSTANCE = new GamePadController();

    private final Joystick turnStick;

    private final Joystick throttleStick;

    private GamePadController() {
        turnStick = new Joystick(Integer.parseInt(System.getProperty("turn.stick")));
        throttleStick = new Joystick(Integer.parseInt(System.getProperty("throttle.stick")));
    }

    public double getThrottle() {
        return this.throttleStick.getY();
    }
}
