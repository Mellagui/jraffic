# 🚦 Jraffic

## 🎯 Objectives
Traffic congestion can be a major problem in urban areas. Your task is to create a traffic control strategy and visualize it with a simulation. The choice of library and file system is up to you. However, you might find the JavaFX library useful for creating GUI applications.

## 📝 Instructions

### 🏙️ Environment and Rules
You should create an environment that includes the objects specified in this section. The representation of the objects is entirely up to you.

---

## 🛣️ 1. Roads
Create two intersecting roads, each with a single lane in both directions. Traffic approaching the intersection can choose between:

- going straight
- turning left
- turning right

Representation:

```console
                        North
                    |  ↓  |  ↑  |
                    |  ↓  |  ↑  |
                    |     |     |
                    |     |     |
                    |     |     |
                    |     |     |
     _______________|     |     |_______________
     ← ←                                     ← ←
East ---------------             --------------- West
     → →                                     → →
     _______________             _______________
                    |     |     |
                    |     |     |
                    |     |     |
                    |     |     |
                    |     |     |
                    |  ↓  |  ↑  |
                    |  ↓  |  ↑  |
                        South
```

---

## 🚦 2. Traffic Lights
Position traffic lights at the points where each lane enters the intersection. Your traffic lights should only have two colors: red and green.

### **Dynamic Congestion Rule**
```
capacity = floor(lane_length / (vehicle_length + safety_gap))
```

Where:
- `lane_length`: Distance from the stop line to the vehicle spawn point  
- `vehicle_length`: Approximate car length  
- `safety_gap`: Minimum safe distance  

The logic must prevent overflow and avoid collisions.

---

## 🚗 3. Vehicles

```
  ______
 /|_||_\`.__
=`-(_)--(_)-'
```

Rules for vehicles:

- Must be painted according to their route.
- Must keep fixed velocity.
- Must maintain safety distance.
- Must obey traffic lights.
- No special vehicle types.

---

## ⌨️ Commands
Spawn vehicles via keyboard:

- **↑ Up:** from south  
- **↓ Down:** from north  
- **→ Right:** from west  
- **← Left:** from east  
- **r:** random direction  
- **Esc:** end simulation  

Vehicles must spawn with safe distance — no spamming.

---

## 📺 Example
Example: [road_intersection](https://www.youtube.com/watch?v=6B0-ZBET6mo).

---

## ⭐ Bonus Features
Optional additions:

- Vehicle and traffic light animations, and image rendering. You can find some cool assets here:
  - [limezu](https://limezu.itch.io/)
  - [finalbossblue](http://finalbossblues.com/timefantasy/free-graphics/).
  - [mobilegamegraphics](https://mobilegamegraphics.com/product-category/all_products/freestuff/).
  - [spriters-resource](https://www.spriters-resource.com/).

---

## 📚 Notions
- [JavaFX](https://openjfx.io/openjfx-docs/)
- [Java KeyEvents](https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html)
