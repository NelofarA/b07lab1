import java.io.File;
import java.io.IOException;
public class Driver {
public static void main(String[] args) throws IOException {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,0,0,5};
        int[] e1 = {3, 2, 1, 0};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {0,-2,0,0,-9};
        int[] e2 = {4, 3, 2, 1, 0};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
        System.out.println("1 is a root of s");
        else
        System.out.println("1 is not a root of s");
        Polynomial pFile = new Polynomial(new File("poly.txt"));
        System.out.println("pFile(2) = " + pFile.evaluate(2));
        pFile.saveToFile("outputPoly.txt");
        }
        }