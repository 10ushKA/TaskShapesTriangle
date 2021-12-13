package util.impl;

import entity.Point;
import entity.Triangle;
import util.ShapeUtil;

import java.util.ArrayList;
import java.util.List;

public class TriangleUtil implements ShapeUtil<Triangle> {

    public static final TriangleUtil INSTANCE  = new TriangleUtil();

    private TriangleUtil(){

    }

    @Override
    public Triangle create(String name, List<Point> points) {
        if(points == null || points.size() != 3){
            throw new IllegalArgumentException("Invalid points");
        }
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Invalid name");
        }
        return new Triangle(name, points);
    }

    public List<Double> getLines(Triangle triangle){
        List<Point> points = triangle.getPoints();
        List<Double> lines = new ArrayList<>();
        lines.add(getLineLength(points.get(0), points.get(1)));
        lines.add(getLineLength(points.get(1), points.get(2)));
        lines.add(getLineLength(points.get(2), points.get(0)));
        return lines;
    }

    public List<Triangle.TriangleType> getTriangleTypes(Triangle triangle){
        List<Double> lines = getLines(triangle);
        double line1 = lines.get(0);
        double line2 = lines.get(1);
        double line3 = lines.get(2);
        List<Triangle.TriangleType> triangleTypes = new ArrayList<>();
        if(isNotValid(line1, line2, line3)) {
            triangleTypes.add(Triangle.TriangleType.NOT_VALID);
            return triangleTypes;
        }

        if(isEquilateral(line1, line2, line3)){
            triangleTypes.add(Triangle.TriangleType.EQUILATERAL);
        }

        if(isIsosceles(line1, line2, line3)){
            triangleTypes.add(Triangle.TriangleType.ISOSCELES);
        }

        if(isRectangular(line1, line2, line3)){
            triangleTypes.add(Triangle.TriangleType.RECTANGULAR);
        }

        if(isObtuse(line1, line2, line3)){
            triangleTypes.add(Triangle.TriangleType.OBTUSE);
        }

        if(isAcuteAngled(line1, line2, line3)){
            triangleTypes.add(Triangle.TriangleType.ACUTE_ANGLED);
        }

        if(triangleTypes.isEmpty()){
            triangleTypes.add(Triangle.TriangleType.SCALENE);
        }

        return triangleTypes;
    }

    private boolean isAcuteAngled(double line1, double line2, double line3){
        return Math.pow (line1, 2) < Math.pow(line2, 2) + Math.pow(line3, 2) || Math.pow(line2, 2) < Math.pow(line1, 2) + Math.pow(line3, 2) || Math.pow (line3, 2) < Math.pow(line1, 2) + Math.pow(line2, 2);
    }

    private boolean isObtuse(double line1, double line2, double line3){
        return Math.pow(line1, 2) > Math.pow(line2,2) + Math.pow(line3, 2) || Math.pow(line2, 2) > Math.pow(line1, 2) + Math.pow(line3, 2) || Math.pow(line3,2) > Math.pow(line1, 2) + Math.pow(line2, 2);
    }

    private boolean isRectangular(double line1, double line2, double line3){
        return Math.pow(line1, 2) == Math.pow(line2, 2) + Math.pow(line3, 2) || Math.pow(line2, 2) == Math.pow(line1, 2) + Math.pow(line3,2) || Math.pow(line3, 2) == Math.pow(line1, 2) + Math.pow(line2, 2);
    }

    private boolean isIsosceles(double line1, double line2, double line3){
        return line1 == line2 || line2 == line3 || line1 == line3;
    }

    private boolean isNotValid(double line1, double line2, double line3){
        return !(line1 + line2 >= line3 && line2 + line3 >= line1 && line1 + line3 >= line2);
    }

    private boolean isEquilateral(double line1, double line2, double line3){
        return line1==line2 && line2==line3;
    }

    private double getLineLength(Point a, Point b){
        return Math.sqrt(Math.pow(b.getX() - a.getX(),2)+ Math.pow(b.getY()-a.getY(), 2));
    }

    @Override
    public double getArea(Triangle item) {
        List<Double> lines = getLines(item);
        double halfMeter = (lines.get(0)+ lines.get(1) + lines.get(2))/2;
        return Math.sqrt(halfMeter*(halfMeter - lines.get(0))*(halfMeter - lines.get(1))*(halfMeter - lines.get(2)));
    }

    @Override
    public double getPerimeter(Triangle item) {
        List<Double> lines = getLines(item);
        return lines.get(0)+ lines.get(1) + lines.get(2);
    }
}
