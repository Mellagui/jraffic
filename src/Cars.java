
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Cars {
    
    private Map<String, List<Car>> mapCar = new HashMap<>();
     
    final Random random = new Random();

    public Cars() {
        
        mapCar.put("south", new ArrayList<>());
        mapCar.put("north", new ArrayList<>());
        mapCar.put("west", new ArrayList<>());
        mapCar.put("east", new ArrayList<>());
    }

public void add(String direction, Map<String, List<Point>>  center,int w) {
     
        final int gap = (w*3)/100;
    if (!canadd(direction, center, w)) return ;
    Car car = new Car();
    car.setDir(direction);
    car.RandomColor();
 final int size = (w * 6) / 100;
    switch (direction) {
        case "north" -> car.setPosition(new Point(center.get("north").get(0).x+ gap, 0));      
        case "south" -> car.setPosition(new Point(center.get("south").get(1).x -(gap+size), w));  
        case "west"  -> car.setPosition(new Point(w, center.get("west").get(0).y+ gap));   
        default -> car.setPosition(new Point(0, center.get("west").get(1).y-(gap+size)));         
    }

    List<Car> carsInDirection = mapCar.get(direction);
    if (carsInDirection == null) {
        carsInDirection = new ArrayList<>();
        mapCar.put(direction, carsInDirection);
    }

    carsInDirection.add(car);
}

    public void random(Map<String, List<Point>>  center,int w) {
        int n = random.nextInt(4);

        switch (n) {
            case 1 ->
                add("south", center,w);
            case 2 ->
                add("north", center,w);
            case 3 ->
                add("west", center,w);
            default ->
                add("east", center,w);
        }
    }

    // Optional: get cars by direction
    public List<Car> getCars(String direction) {
        return mapCar.getOrDefault(direction, new ArrayList<>());
    }

    public void update( Map<String, List<Point>>  center, int w) {
        System.out.println(mapCar.get("west").toString());
     
       
        for (Map.Entry<String, List<Car>> en : mapCar.entrySet()) {
           
           
            for (Car car : en.getValue()) {
                car.move();
                
                
            }

          
             
        }

        
       

   
    }
    public boolean canadd(String deriction, Map<String, List<Point>>  center, int w){
 List<Point> line = center.get("north");
        int distinse  = line.get(0).y  ;
          final int size = (w * 6) / 100;
        final int gap = (w*3)/100;
        if (mapCar.get(deriction).isEmpty() ) return true;
        Point last = mapCar.get(deriction).get( mapCar.get(deriction).size()-1).getPosition();
        //    Boolean can =  true ;
     Boolean can =  switch (deriction) {
            case "north" ->  last.y>= size + gap   ;
            case "south" -> last.y + (size*2) + gap <=  w ;
            case "east"  -> last.x>= size + gap  ;
           default  -> last.x + (size*2) + gap <=  w  ;
        };
        if (getCars(deriction).size()*(size+ gap)  >= distinse - (size + gap) ){
            return false;

        }
        return   can && true;

    }

    public void draw(Graphics g, int w) {
      final int size = (w * 6) / 100;
        for (Map.Entry<String, List<Car>> en : mapCar.entrySet()) {
           
           
            for (Car car : en.getValue()) {
                g.setColor(car.getColor());
               g.fillRect(car.getPosition().x, car.getPosition().y, size, size);
                
                
            }

          
             
        }

    }
}
