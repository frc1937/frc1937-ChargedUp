// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class ToggleCommand {
    private Command firstCommand;
    private Command secondCommand;
    private boolean pressed = false;

    public ToggleCommand(Command firstCommand, Command secondCommand) {
        this.firstCommand = firstCommand;
        this.secondCommand = secondCommand;
    }

    /* Change the value of pressed to the opposite value and return a command that should be activated 
     * @return the command that should be activated in the next press of the button
     */
    public Command toggle()
    {
        Command doIt = pressed ? secondCommand : firstCommand;
        pressed = !pressed;
        return doIt;
    }
}