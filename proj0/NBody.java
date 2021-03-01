public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int number = in.readInt();
        double radius = in.readDouble();
        Planet [] allPlanets = new Planet[number];
        for(int i = 0; i < number; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xP, yP , xV, yV, m, img);
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(Planet p: planets){
            p.draw();
        }

        StdDraw.enableDoubleBuffering();
        for(double t = 0.0; t <= T; t += dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int j = 0; j < xForces.length; j++){
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for(int j = 0; j < xForces.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet p: planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            StdOut.printf("%d\n", planets.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
            }

        }
    }
}


