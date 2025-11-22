import java.awt.*;
import java.util.*;
import java.util.List;

public class Roads extends JrafficCanvas {
    public void roads(Graphics g) {
        
        List<List<Point[]>> roads_directions = new ArrayList<>();

        final int middle_w = super.width / 2;
        final int middle_h = super.heigth / 2;
        final int road_w = (super.width * 12)/100;
        final int road_h = (super.width * 12)/100;
        
        List<Point[]> north = new ArrayList<>();
        Point[] n1 = {new Point(middle_w - road_w, 0), new Point(middle_w - road_w, middle_h - road_h)}; north.add(n1); //
        Point[] n2 = {new Point(middle_w, 0), new Point(middle_w, middle_h - road_h)}; north.add(n2);
        Point[] n3 = {new Point(middle_w + road_w, 0), new Point(middle_w + road_w, middle_h - road_h)}; north.add(n3); //
        roads_directions.add(north);
        super.road_center.put("north", Arrays.asList(n1[1], n3[1]));
        
        List<Point[]> south = new ArrayList<>();
        Point[] s1 = {new Point(middle_w - road_w, middle_h * 2), new Point(middle_w - road_w, middle_h + road_h)}; south.add(s1); //
        Point[] s2 = {new Point(middle_w, middle_h * 2), new Point(middle_w, middle_h + road_h)}; south.add(s2);
        Point[] s3 = {new Point(middle_w + road_w, middle_h * 2), new Point(middle_w + road_w, middle_h + road_h)}; south.add(s3); //
        roads_directions.add(south);
        road_center.put("south", Arrays.asList(s1[1], s3[1]));
        
        List<Point[]> east = new ArrayList<>();
        Point[] e1 = {new Point(0, middle_h - road_h), new Point(middle_w - road_w, middle_h - road_h)}; north.add(e1); //
        Point[] e2 = {new Point(0, middle_h), new Point(middle_w - road_w, middle_h)}; east.add(e2);
        Point[] e3 = {new Point(0, middle_h + road_h), new Point(middle_w - road_w, middle_h + road_h)}; east.add(e3); //
        roads_directions.add(east);
        road_center.put("east", Arrays.asList(e1[1], e3[1]));

        List<Point[]> west = new ArrayList<>();
        Point[] w1 = {new Point(middle_w * 2, middle_h - road_h), new Point(middle_w + road_w, middle_h - road_h)}; west.add(w1); //
        Point[] w2 = {new Point(middle_w * 2, middle_h), new Point(middle_w + road_w, middle_h)}; west.add(w2);
        Point[] w3 = {new Point(middle_w * 2, middle_h + road_h), new Point(middle_w + road_w, middle_h + road_h)}; west.add(w3); //
        roads_directions.add(west);
        road_center.put("west", Arrays.asList(w1[1], w3[1]));

        for (List<Point[]> dir : roads_directions) {
            for (Point[] line : dir) {
                Point p1 = line[0];
                Point p2 = line[1];
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}
