/*
* @Author: czahie
* @Date:   2018-06-04 22:12:44
* @Last Modified by:   czahie
* @Last Modified time: 2018-06-06 22:00:06
*/
public class NBody {
    /** Reads a file and returns the radius. */
    public static double readRadius(String filename) {
        In in = new In(filename);
        int num_of_planets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** Reads a file and returns an array of planets. */
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int num_of_planets = in.readInt();
        double radius = in.readDouble();
        Planet[] allPlanets = new Planet[num_of_planets];
        for(int i = 0; i < num_of_planets; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        /* Draws the Backgroud. */
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        
        /* Draws all the planets. */
        for(Planet P: allPlanets) {
            P.draw();
        }

        /* Enables double buffering. */
        StdDraw.enableDoubleBuffering();
    
        /* Creates an Animation. */
        double time = 0;
        while(time < T) {
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];
            for(int i = 0; i < allPlanets.length; i++){
                double xForce = allPlanets[i].calcNetForceExertedByX(allPlanets);
                double yForce = allPlanets[i].calcNetForceExertedByY(allPlanets);
                xForces[i] = xForce;
                yForces[i] = yForce;
            }
            for(int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet P: allPlanets) {
                P.draw();
            }
            StdDraw.show();
            StdDraw.pause(10); 
            time += dt;    
        }
       
        /* Prints the final state of the universe. */
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }



    }
}