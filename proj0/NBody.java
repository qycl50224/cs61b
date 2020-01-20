public class NBody {
	public static double readRadius(String file) {
		In in = new In(file);
		int n = in.readInt();
		double r = in.readDouble();
		return r;
	}

	public static Body[] readBodies(String file) {
		In in = new In(file);
		int n = in.readInt();
		double r = in.readDouble();
		Body[] bodies = new Body[n];
		for(int i = 0; i < n; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double mass = in.readDouble();
			String imgName = in.readString();
			bodies[i] = new Body(xP, yP, xV, yV, mass, imgName);
		}
		return bodies;
	}

	public static void main(String[] args) {
		/*
		 * get data
		 */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] bodies = NBody.readBodies(filename);
		double radius = NBody.readRadius(filename);
		double[] xForces = new double[bodies.length];
		double[] yForces = new double[bodies.length];
		/*
		 * draw background of universe
		 */
		StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        
        /*
         * draw planets
         */
        for(double time = 0; time < T; time += dt) {
        	/*
        	 * calc NetForces
        	 */
        	for(int i = 0; i < bodies.length; i++) {
        		xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        		yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
        	}
        	/*
        	 * update position
        	 */
        	for(int i = 0; i < bodies.length; i++) {
        		bodies[i].update(dt, xForces[i], yForces[i]);
        	}
        	StdDraw.clear();
        	StdDraw.picture(0, 0, "images/starfield.jpg");

        	for(int i = 0; i < bodies.length; i++) {
        		bodies[i].draw();
        	}
        	StdDraw.show();
        	StdDraw.pause(10);

        }
        
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
		                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}
}

