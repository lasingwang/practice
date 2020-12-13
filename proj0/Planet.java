public class Planet {
    public double mass;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public String imgFileName;
    public final double G = 6.67 * 1.0e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyPos = p.yyPos;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet Target) {
        double distanceX = this.xxPos - Target.xxPos;
        double distanceY = this.yyPos - Target.yyPos;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY );
        return  distance;
    }
    public  double calcForceExertedBy (Planet Target) {
        double Force = G *(this.mass) * (Target.mass) / (this.calcDistance(Target) * this.calcDistance(Target) );
        return Force;
    }
    public  double calcForceExertedByX (Planet Target) {
        double Force = this.calcForceExertedBy(Target) * (Target.xxPos-this.xxPos) /this.calcDistance(Target);
        return Force;
    }
    public  double calcForceExertedByY (Planet Target) {
        double Force = this.calcForceExertedBy(Target) * (Target.yyPos-this.yyPos) /this.calcDistance(Target);
        return Force;
    }
    public double calcNetForceExertedByX (Planet[] allTarget) {
        double netForceX = 0;
        for (int i = 0; i < allTarget.length; i++) {
            if ( allTarget[i] == this) {
                continue;
            } else {
                netForceX = netForceX + this.calcForceExertedByX( allTarget[i] );
            }
        }
        return netForceX;
    }
    public double calcNetForceExertedByY (Planet[] allTarget) {
        double netForceY = 0;
        for (int i = 0; i < allTarget.length; i++) {
            if ( allTarget[i] == this) {
                continue;
            } else {
                netForceY = netForceY + this.calcForceExertedByY( allTarget[i] );
            }
        }
        return netForceY;
    }
    public void update(double dt, double fX, double fY) {
        double a_net_x = fX / this.mass;
        double a_net_y = fY / this.mass;
        this.xxVel = this.xxVel + dt * a_net_x;
        this.yyVel = this.yyVel + dt * a_net_y;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "/images/" + this.imgFileName);
    }
}
