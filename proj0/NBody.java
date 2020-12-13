public class NBody {
    public static double readRadius( String filename) {
        In file = new In(filename);
        int numberOfPlanets = file.readInt();
        double Radius = file.readDouble();
        return Radius;
    }
    public static Planet[] readPlanets (String filename) {
        Planet[] PlanetSet = new Planet[5];
        In file = new In(filename);
        int numberOfPlanets = file.readInt();
        double Radius = file.readDouble();
        for (int i =0; i < 5; i++) {
            double xPos = file.readDouble();
            double yPos = file.readDouble();
            double xVel = file.readDouble();
            double yVel = file.readDouble();
            double mass = file.readDouble();
            String imgFileName = file.readString();
            PlanetSet [i] = new Planet(xPos,yPos,xVel,yVel,mass,imgFileName);
        }
        return  PlanetSet;//writing experience
    }

    public static void main(String[] args) {
        args = new String[]{"157788000.0", "25000.0","planet.txt"};
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]); //remember how to change
        String filename = args[2];
        Planet[] allPlanets = readPlanets(filename);
        double Radius = readRadius(filename); //get data
        StdDraw.setScale(- Radius, Radius);
        StdDraw.clear();
        String background = "starfield.png";
        StdDraw.picture (0,0,background);
        for (int i = 0; i < allPlanets.length; i++ ) {
            allPlanets[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        for (double time = 0; time <= T - dt; time += dt) {
            double [] xForces = new double[5];
            double [] yForces = new double[5];
            for (int j = 0; j < 5; j++) {
                xForces[j] = allPlanets[j].calcNetForceExertedByX(allPlanets);
                yForces[j] = allPlanets[j].calcNetForceExertedByY(allPlanets);
                allPlanets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture (0,0,background);
            for (int i = 0; i < allPlanets.length; i++ ) {
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(1);
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}
