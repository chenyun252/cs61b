public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = -this.xxPos + b.xxPos;
        double dy = -this.yyPos + b.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Body b) {
        final double G = 6.67e-11;
        return G * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b) {
        double dx = -this.xxPos + b.xxPos;
        return this.calcForceExertedBy(b) * dx / this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        double dy = -this.yyPos + b.yyPos;
        return this.calcForceExertedBy(b) * dy / this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] allBodys) {
        double Fnetx = 0;
        for (Body x : allBodys) {
            if (!this.equals(x)) {
                Fnetx = Fnetx + this.calcForceExertedByX(x);
            }
        }
        return Fnetx;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
        double Fnety = 0;
        for (Body y : allBodys) {
            if (!this.equals(y)) {
                Fnety = Fnety + this.calcForceExertedByY(y);
            }
        }
        return Fnety;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel += dt*ax;
        this.yyVel += dt*ay;
        this.xxPos += dt*this.xxVel;
        this.yyPos += dt*this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}

