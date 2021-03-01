public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double grav = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet m){
        double distance = Math.sqrt(Math.pow((m.xxPos - xxPos),2) + Math.pow((m.yyPos - yyPos),2));
        return distance;
    }

    public double calcForceExertedBy(Planet m){
        double force = grav * mass * m.mass / Math.pow(this.calcDistance(m),2);
        return force;
    }

    public double calcForceExertedByX(Planet m){
        double forceX = this.calcForceExertedBy(m) * (m.xxPos - xxPos) / this.calcDistance(m);
        return forceX;
    }

    public double calcForceExertedByY(Planet m){
        double forceY = this.calcForceExertedBy(m) * (m.yyPos - yyPos) / this.calcDistance(m);
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] a){
        double netForceX = 0;
        for (Planet planet : a) {
            if (!this.equals(planet)) {
                netForceX += this.calcForceExertedByX(planet);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] a){
        double netForceY = 0;
        for (Planet planet : a) {
            if (!this.equals(planet)) {
                netForceY += this.calcForceExertedByY(planet);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
