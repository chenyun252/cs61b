public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        in.readDouble();
        Body planets[] = new Body[num];
        for(int i = 0; i < num; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Body(xP,yP,xV,yV,m,img);
        }
        return planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] allbodys = NBody.readBodies(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for(Body x : allbodys){
            x.draw();
        }

        StdDraw.enableDoubleBuffering();

        for(double t = 0; t <= T; t += dt){
            double xForces[] = new double[allbodys.length];
            double yForces[] = new double[allbodys.length];
            for(int i = 0; i < allbodys.length; i++){
                xForces[i] = allbodys[i].calcNetForceExertedByX(allbodys);
                yForces[i] = allbodys[i].calcNetForceExertedByY(allbodys);
                }
            for(int i = 0; i < allbodys.length; i++){
                allbodys[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(Body x : allbodys){
                x.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", allbodys.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allbodys.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allbodys[i].xxPos, allbodys[i].yyPos, allbodys[i].xxVel,
                    allbodys[i].yyVel, allbodys[i].mass, allbodys[i].imgFileName);
        }
    }

}
