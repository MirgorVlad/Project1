import static java.lang.Math.abs;
import static java.lang.Math.cosh;

public class shootMethod {
    static int N = 20;
   static double ax = 0, bx = 1;
    static double h = (bx-ax)/N;

    static double x[] = new double[N];
    static double y[] = new double[N];
    static double z[] = new double[N];
    static double A[] = new double[N];
    static double B[] = new double[N];
    static double C[] = new double[N];
    static double F[] = new double[N];
    static double aa[] = new double[N];
    static double bb[] = new double[N];

   public static double p(double x){
       return cosh(x);
   }

    public static double q(double x){
        return 1;
    }

    public static double f1(double x){
        return x;
    }


    public static void main(String[] args) {
        double xx, xx1, d, h = (bx-ax)/N;
        for(int i = 0; i < N; i++)
            x[i] = ax+h*i;
        for(int i = 0; i < N-1; i++){
            C[i]=1/(h*h) - p(x[i])/(2*h);
            A[i]=1/(h*h) + p(x[i])/(2*h);
            B[i]=-2/(h*h)+q(x[i]);
            F[i]=f1(x[i]);
        }
        B[0]= -h-1; A[0]=1; F[0]=-h;
        B[N-1]=1+h; C[N-1]= -1;F[N-1]=h;
        aa[0]= -A[0]/B[0]; bb[0]= F[0]/B[0];
        for(int i = 1; i < N; i++){
            aa[i]= -A[i]/(C[i]*aa[i-1] + B[i]);
            bb[i]= (F[i] - C[i]*bb[i-1])/(C[i]*aa[i-1] + B[i]);

        }
        y[N-1]= (F[N-1] - bb[N-1-1]*C[N-1])/(B[N-1] + aa[N-1-1]*C[N-1]);
        System.out.println(y[N-1]);
        for(int i = N-2; i > 0; i--) {y[i] = aa[i]*y[i+1] + bb[i];
            System.out.println(aa[i] + " " + y[i+1]+" " + bb[i] + " " + y[i]);}
       // for(int i = 0; i < N; i++) System.out.println(x[i] + "\t" + y[i]);
        }
    }

