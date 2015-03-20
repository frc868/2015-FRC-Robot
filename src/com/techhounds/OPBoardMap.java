package com.techhounds;

import edu.wpi.first.wpilibj.Joystick;

public class OPBoardMap extends ControllerMap {

	public static final int OPEN_ALL = 0, RC_TO_PASSIVE = 1, ADD_TOTE = 2, CLOSE_TAKE = 3, CLOSE_SPIT = 4,
			WINK = 5, FISHING = 6, LIFT_OPEN = 7, LIFT_CLOSE = 8, PASSIVE_OPEN = 9, PASSIVE_CLOSE = 10, 
			FEEDER_OPEN = 11, FEEDER_CLOSE = 12, FEEDER_TAKE = 13, FEEDER_SPIT = 14, PASSIVE_BREAK = 15,
			DOWN_TOTE_LEVEL = 16, UP_TOTE_LEVEL = 17, SLIDER = 18;
	
	protected static final int [] opBoard = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 8};
	
	private static int OP_BOARD = 5;
	
	public OPBoardMap(Joystick joystick) {
		super(joystick);
		buttonSet = opBoard;
		type = OP_BOARD;
	}
	
	public double getSliderAxis() {
		return joystick.getRawAxis(0);
	}
	
	@Deprecated
	public double getLeftStickX(){
		return 0;
	}
    
	@Deprecated
    public double getLeftStickY(){
    	return 0;
    }
    
    @Deprecated
    public double getRightStickX(){
    	return 0;
    }
    
    @Deprecated
    public double getRightStickY(){
    	return 0;
    }
}