package util;

import entity.Point;
import entity.Shape;

import java.util.List;

public interface ShapeUtil<T extends Shape> extends Creator<T> {
    double getArea(T item);
    double getPerimeter(T item);
}
