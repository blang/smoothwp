smoothwp for java
======

smoothwp is a java library to generate waypoints between to gps positions.

Build & Usage
-----

```bash
$ ./gradlew build
```

Include `./build/libs/smoothwp-*.jar` in your project.

```java
import io.blang.smoothwp.Transition;

Transition t = new Transition(48.5598421, 13.4392791, 48.5604988, 13.4408795);
while(t.hasNext()) {
    double[] wp = t.next(1 / METER_TO_GEO);
    System.out.println("<trkpt lat=\""+wp[0]+"\" lon=\""+wp[1]+"\"/>");
}
```

Methods
-----

`Transition(double wp1latitude, double wp1longitude, double wp2latitude, double wp2longitude)` constructs the transition between to waypoints.

`double[] next(double distance)` will generate the next waypoint, including the target waypoint, excluding the first waypoint. Distance is relative to the geo system, you might need to calculate the conversion to meters.

`boolean hasNext()` True iff a next waypoint is available, last waypoint will always be the target waypoint wp2.

Why should I use this lib?
-----

- Simple
- Fast
- Only Stdlib


Example
-----

Have a look at full examples in [TransitionTest.java](src/test/java/io/blang/smoothwp/TransitionTest.java)

Contribution
-----

Feel free to make a pull request. For bigger changes create a issue first to discuss about it.


License
-----

See [LICENSE](LICENSE) file.
