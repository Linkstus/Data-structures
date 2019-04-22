public class lab3 {

    public static int gcd(int m, int n) {
        if (m % n == 0)
            return n;
        return gcd(n, m % n);
    }

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        int k = gcd(m, n);

        System.out.printf("GCD is %d", k);
    }
}