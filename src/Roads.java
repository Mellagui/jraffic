import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Roads {
    private final Map<String, List<Point>> r_center = new HashMap<>();
    private final Map<String, Point> t_pos = new HashMap<>();

    public Map<String, List<Point>> getRoadCenter() {
        return r_center;
    }

    public Map<String, Point> getTrafficPlace() {
        return t_pos;
    }

    public void draw(Graphics g, int w, int h) {
        r_center.clear();
        t_pos.clear();

        final List<List<Point[]>> r_lines = new ArrayList<>();

        final int mid_w = w / 2;
        final int mid_h = h / 2;
        final int road_w = (w * 12) / 100;
        final int road_h = (w * 12) / 100;

        final List<Point[]> north = new ArrayList<>();
        final Point[] n1 = {new Point(mid_w - road_w, 0), new Point(mid_w - road_w, mid_h - road_h)}; north.add(n1);
        final Point[] n2 = {new Point(mid_w, 0), new Point(mid_w, mid_h - road_h)}; north.add(n2);
        final Point[] n3 = {new Point(mid_w + road_w, 0), new Point(mid_w + road_w, mid_h - road_h)}; north.add(n3);
        r_lines.add(north);

        final List<Point[]> south = new ArrayList<>();
        final Point[] s1 = {new Point(mid_w - road_w, mid_h * 2), new Point(mid_w - road_w, mid_h + road_h)}; south.add(s1);
        final Point[] s2 = {new Point(mid_w, mid_h * 2), new Point(mid_w, mid_h + road_h)}; south.add(s2);
        final Point[] s3 = {new Point(mid_w + road_w, mid_h * 2), new Point(mid_w + road_w, mid_h + road_h)}; south.add(s3);
        r_lines.add(south);

        final List<Point[]> east = new ArrayList<>();
        final Point[] e1 = {new Point(0, mid_h - road_h), new Point(mid_w - road_w, mid_h - road_h)}; east.add(e1);
        final Point[] e2 = {new Point(0, mid_h), new Point(mid_w - road_w, mid_h)}; east.add(e2);
        final Point[] e3 = {new Point(0, mid_h + road_h), new Point(mid_w - road_w, mid_h + road_h)}; east.add(e3);
        r_lines.add(east);

        final List<Point[]> west = new ArrayList<>();
        final Point[] w1 = {new Point(mid_w * 2, mid_h - road_h), new Point(mid_w + road_w, mid_h - road_h)}; west.add(w1);
        final Point[] w2 = {new Point(mid_w * 2, mid_h), new Point(mid_w + road_w, mid_h)}; west.add(w2);
        final Point[] w3 = {new Point(mid_w * 2, mid_h + road_h), new Point(mid_w + road_w, mid_h + road_h)}; west.add(w3);
        r_lines.add(west);

        // draw road lines
        for (final List<Point[]> lines : r_lines) {
            for (final Point[] line : lines) {
                final Point p1 = line[0];
                final Point p2 = line[1];
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        // target : get center road lines (rectangle)
        r_center.put("north", Arrays.asList(n1[1], n3[1]));
        r_center.put("south", Arrays.asList(s1[1], s3[1]));
        r_center.put("east",  Arrays.asList(e1[1], e3[1]));
        r_center.put("west",  Arrays.asList(w1[1], w3[1]));

        // target : get traffic position
        final int t_gap = (w * 3) / 100;
        t_pos.put("north", new Point(n1[1].x - (t_gap * 3), n1[1].y - (t_gap * 3)));
        t_pos.put("south", new Point(s3[1].x + t_gap,       s3[1].y + t_gap));
        t_pos.put("east",  new Point(s1[1].x - (t_gap * 3), s1[1].y + t_gap));
        t_pos.put("west",  new Point(n3[1].x + t_gap,       n3[1].y - (t_gap * 3)));
    }
}