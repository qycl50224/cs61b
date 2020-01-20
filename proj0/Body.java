public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body a) {
		double dx = this.xxPos - a.xxPos; 
		double dy = this.yyPos - a.yyPos;
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); 
	}
	public static double G = 6.67e-11;
	public double calcForceExertedBy(Body a) {
		double r = this.calcDistance(a);
		double m1 = this.mass;
		double m2 = a.mass;
		return G * m1 * m2 / Math.pow(r, 2);
	}
	public double calcForceExertedByX(Body a) {
		double f = this.calcForceExertedBy(a);
		double r = this.calcDistance(a);
		double dx = a.xxPos - this.xxPos;
		double cos = dx / r;
		return f * cos;
	}
	public double calcForceExertedByY(Body a) {
		double f = this.calcForceExertedBy(a);
		double r = this.calcDistance(a);
		double dy = a.yyPos - this.yyPos;
		double sin = dy / r;
		return f * sin;
	}
	public double calcNetForceExertedByX(Body[] bodies) {
		double l = bodies.length;
		double sum = 0;
		for(int i = 0; i < l; i++) {
			if(bodies[i].equals(this)) {
				continue;
			}
			sum += this.calcForceExertedByX(bodies[i]);
		}
		return sum;	
	}
	public double calcNetForceExertedByY(Body[] bodies) {
		double l = bodies.length;
		double sum = 0;
		for(int i = 0; i < l; i++) {
			if(bodies[i].equals(this)) {
				continue;
			}
			sum += this.calcForceExertedByY(bodies[i]);
		}
		return sum;	
	}
	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		double vXnew = this.xxVel + dt * aX;
		double vYnew = this.yyVel + dt * aY;
		this.xxVel = vXnew;
		this.yyVel = vYnew;
		this.xxPos += vXnew * dt;
		this.yyPos += vYnew * dt;
	}
	public void draw() {
		String path = "images\\";
		path += this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, path);
	}
}