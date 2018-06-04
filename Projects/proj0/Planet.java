/*
* @Author: caizhe
* @Date:   2018-06-04 11:22:49
* @Last Modified by:   czahie
* @Last Modified time: 2018-06-04 14:22:42
*/

public class Planet {
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet P) {
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }

    /** Calculate the distance between two planets. */
    public double calcDistance(Planet P) {
        double dx = P.xxPos - this.xxPos;
        double dy = P.yyPos - this.yyPos;
        double r;
        r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    /** Returns a double describing the force exerted on this planet by the given planet. */
    public double calcForceExertedBy(Planet P) {
        double r = this.calcDistance(P);
        double force = G * this.mass * P.mass / (r * r);
        return force;
    }
    
    /** Returns the force exerted in X direction. */
    public double calcForceExertedByX(Planet P) {
        double force = this.calcForceExertedBy(P);
        double r = this.calcDistance(P);
        double dx = P.xxPos - xxPos;
        double forceX = force * dx / r;
        return forceX;
    }

    /** Returns the force exerted in Y direction. */
    public double calcForceExertedByY(Planet P) {
        double force = this.calcForceExertedBy(P);
        double r = this.calcDistance(P);
        double dy = P.yyPos - yyPos;
        double forceY = force * dy / r;
        return forceY;
    }

    /** Returns the net force exerted by the given Planet arrays in X direction */
    public double calcNetForceExertedByX(Planet[] Ps) {
        double total = 0;
        for (Planet P: Ps) {
            if (this.equals(P)) {   //  Planets cannot exert gravitational forces on themselves!
                continue;
            }
            total += this.calcForceExertedByX(P);
        }
        return total;
    }


    /** Returns the net force exerted by the given Planet arrays in Y direction */
    public double calcNetForceExertedByY(Planet[] Ps) {
        double total = 0;
        for (Planet P: Ps) {
            if (this.equals(P)) {
                continue;
            }
            total += this.calcForceExertedByY(P);
        }
        return total;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + aX * dt;
        this.yyVel = this.yyVel + aY * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }






}