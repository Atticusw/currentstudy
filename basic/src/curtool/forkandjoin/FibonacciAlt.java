package curtool.forkandjoin;

public class FibonacciAlt {
    public static void main(String[] args) {
       System.out.println( fi(8) );
    }

    static Integer fi(Integer n) {
        if (n <= 1){
            return n;
        }
        return fi(n-1) + fi(n-2);
    }
}
