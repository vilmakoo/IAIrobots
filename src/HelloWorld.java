package org.lejos.example;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * Write your code in main()
 */
public class HelloWorld {
    static DifferentialPilot pilot = new DifferentialPilot(5.6, 17.5f, Motor.A, Motor.B);
    static UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
    static LightSensor light = new LightSensor(SensorPort.S1);

    public static void main(String[] args) {
        System.out.println("I'll be back!");
        Button.waitForAnyPress();

        tehtava1();

//        tehtava2();
    }

    public void tehtava1() {
        pilot.setTravelSpeed(0.5);

        while (true) {
            int lightness = light.readValue();
            System.out.println("Light: " + lightness);

            if (lightness > 50) { // if lightness is high enough
                pilot.travel(5);

            } else {
                // if lightness is low turn left?? or right???
                pilot.rotate(30); // maybe not this much??
            }

            System.out.println(pilot.getMovement().getDistanceTraveled());

            //stop?
        }
    }

    public void tehtava2() {
        pilot.setTravelSpeed(0.5);

        int d1 = ultra.getDistance();
        pilot.travel(2);
        int d2 = ultra.getDistance();
        boolean found = false;

        while (!found) {
            int travelDistFirst = pilot.getMovement().getDistanceTraveled();

            while (d2 - d1 > 40) {
                pilot.travel(40, true);
                d2 = ultra.getDistance();
            }

            if (pilot.isMoving()) {
                pilot.stop();
            }

            if (pilot.getMovement().getDistanceTraveled() - travelDistFirst > 40) {
                found = true;
            }

            d1 = d2;
        }

        // parkkeeraa
        // ts. aja 40cm taakse, käänny vasemmalle, aja d2 verran eteenpäin?

        pilot.travel(-50);
        pilot.rotate(90);
        pilot.travel(d2);
    }
}
