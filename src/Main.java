import entity.Triangle;
import parser.impl.TriangleParser;
import util.impl.TriangleUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path ="triangle_sides.txt";
        List<Triangle> triangleList = TriangleParser.INSTANCE.parse(path);
        TriangleUtil triangleUtil = TriangleUtil.INSTANCE;
        for (Triangle triangle: triangleList){
            System.out.println(triangle.getTriangleTypes()+" : "+triangle.getPoints());
            System.out.printf("Area : %3.2f\n",triangleUtil.getArea(triangle));
            System.out.printf("Perimeter : %3.2f\n", triangleUtil.getPerimeter(triangle));
        }

    }
}
