package entity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Triangle extends Shape {
    private final List<Point> points;
    private List<TriangleType> triangleTypes;

    public Triangle(String name, List<Point> points) {
        super(name);
        this.points = points;
    }

    public List<TriangleType> getTriangleTypes() {
        return triangleTypes;
    }

    public void setTriangleTypes(List<TriangleType> triangleTypes) {
        this.triangleTypes = triangleTypes;
    }

    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Triangle triangle = (Triangle) o;
        return triangle.points.equals(points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), points);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "points=" + points +
                '}';
    }


    public enum TriangleType{
        SCALENE,
        EQUILATERAL,
        ISOSCELES,
        RECTANGULAR,
        OBTUSE,
        ACUTE_ANGLED,
        NOT_VALID;


        @Override
        public String toString() {
            return this.name().charAt(0) + this.name().toLowerCase().substring(1).replace("_","-");
        }
    }
}
