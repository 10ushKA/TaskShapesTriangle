package util;

import entity.Point;
import entity.Shape;

import java.util.List;

public interface Creator<T extends Shape> {
    T create(String name, List<Point> points);
}
