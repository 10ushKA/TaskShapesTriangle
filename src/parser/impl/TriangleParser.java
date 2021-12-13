package parser.impl;

import entity.Point;
import entity.Triangle;
import parser.Parser;
import util.impl.TriangleUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TriangleParser implements Parser<Triangle, String> {

    public static final TriangleParser INSTANCE  = new TriangleParser();
    private static final TriangleUtil TRIANGLE_UTIL = TriangleUtil.INSTANCE;
    private static final String TRIANGLE_POINTS_REGEX = "([0-9]*;([0-9]+\\.?[0-9]*)\\s?){3}";
    private TriangleParser(){}
    // N;N N;N N;N
    @Override
    public List<Triangle> parse(String path) throws FileNotFoundException {
        List<Triangle> triangles;
        try (Stream<String> lineStream = Files.lines(Paths.get(path))) {

            triangles = lineStream.filter(p->p.matches(TRIANGLE_POINTS_REGEX)).map(p->{
                String [] points = p.split(" ");
                List<Point> pointList = new ArrayList<>();
                for(String point : points){
                    String [] pointCoords = point.split(";");
                    double pointX = Double.parseDouble(pointCoords[0]);
                    double pointY = Double.parseDouble(pointCoords[1]);
                    pointList.add(new Point(pointX, pointY));
                }
                Triangle triangle = TRIANGLE_UTIL.create("Triangle", pointList);
                triangle.setTriangleTypes(TRIANGLE_UTIL.getTriangleTypes(triangle));
                return triangle;
            }).collect(Collectors.toList());

        } catch (IOException ignored) {
            throw new FileNotFoundException("File not found:(");
        }
        return triangles;
    }
}
