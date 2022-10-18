public class CylinderShape extends Shape {

    private double r;
    private double h;
    
    public CylinderShape() {
    }

    public CylinderShape(double r, double h) {
        this.r = r;
        this.h = h;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    @Override
    public double getVolume() {
        return Math.PI * Math.pow(r, 2) * h;  
    }
}
