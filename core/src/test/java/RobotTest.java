/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.dantai.mygdx.game.Robot;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author inman
 */
public class RobotTest {
    
    private Robot robot;
    private Robot robot2;
    private static final double DELTA = 1e-15;
    
    public RobotTest() {
        
    }
    
    @Before
    public void setUp(){
        robot = new Robot(100, 100);
        robot2 = new Robot(0, 0);
    }
    
    @Test
    public void correctUpdatePositionMove(){
        robot.move(robot.DIRECTION_LEFT);
        assertEquals(95, robot.getPosition().x, DELTA);
        robot.move(robot.DIRECTION_RIGHT);
        assertEquals(100, robot.getPosition().x, DELTA);
    }
    
   /* @Test
    public void onGroundTest(){
        assertFalse(robot.onGround());
        assertTrue(robot2.onGround());
    } */
}
    
