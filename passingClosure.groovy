//Closure defination 

def clos = {param->println "Hello ${param}"};

//map defination
def map = [a:1,b:3]


//calling method which works on map & closure
m1(map,clos)
m2(map)

//method defination 

def m1(map,clos) {
  clos(map)
}

def m2(map1){
    map1.c = "8"
}

println map.getClass()


/*DOCUMENTATION:  https://www.informit.com/articles/article.aspx?p=174371&seqNum=4#:~:text=A%20reference%20is%20an%20address,using%20copies%20of%20the%20objects.



References to Objects
As you work with objects, it's important to understand references.

A reference is an address that indicates where an object's variables and methods are stored.

You aren't actually using objects when you assign an object to a variable or pass an object to a method as an argument. You aren't even using copies of the objects. Instead, you're using references to those objects.

To better illustrate the difference, Listing 3.4 shows how references work.

Listing 3.4 The Full Text of ReferencesTest.java
 1: import java.awt.Point;
 2:
 3: class ReferencesTest {
 4:   public static void main(String[] arguments) {
 5:     Point pt1, pt2;
 6:     pt1 = new Point(100, 100);
 7:     pt2 = pt1;
 8:
 9:     pt1.x = 200;
10:     pt1.y = 200;
11:     System.out.println("Point1: " + pt1.x + ", " + pt1.y);
12:     System.out.println("Point2: " + pt2.x + ", " + pt2.y);
13:   }
14: }
Here is this program's output:

Point1: 200, 200
Point2: 200, 200
The following takes place in the first part of this program:

Line 5—Two Point variables are created.

Line 6—A new Point object is assigned to pt1.

Line 7—The value of pt1 is assigned to pt2.

Lines 9–12 are the tricky part. The x and y variables of pt1 are both set to 200, and then all variables of pt1 and pt2 are displayed onscreen.

You might expect pt1 and pt2 to have different values. However, the output shows this is not the case. As you can see, the x and y variables of pt2 also were changed, even though nothing in the program explicitly changes them. This happens because line 7 creates a reference from pt2 to pt1, instead of creating pt2 as a new object copied from pt1.

pt2 is a reference to the same object as pt1; this is shown in Figure 3.1. Either variable can be used to refer to the object or to change its variables.

If you wanted pt1 and pt2 to refer to separate objects, separate new Point() statements could be used on lines 6 and 7 to create separate objects, as shown in the following:

pt1 = new Point(100, 100);
pt2 = new Point(100, 100);
Figure 3.1Figure 
  3.1 References to objects.

References in Java become particularly important when arguments are passed to methods. You learn more about this later today. */
