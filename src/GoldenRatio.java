public class GoldenRatio {

    public static double Ffunc(double x){ //процедура обчислення ЦФ F
        return x*x - 7*x + 6;   //x^2 -7x + 6;

    }

    public static double Gfunc(double x){ //процедура обчислення ЦФ G
        return Math.pow(x-1,2)*Math.pow(x+1,3);  //(x-1)^2 * (x+1)^3
    }

    public static int funcCalc = 0;

    public static double goldenRatioMethod(double aValue, double bValue, double eps){
        System.out.println("\nGolden ratio: ");
        double a = aValue, b = bValue;
        double l = b-a;
        double xOut;
        double x1, x2, f1, f2;

        x1 = a + 0.382 * l;
        x2 = a + 0.618 * l;
        f1 = Gfunc(x1);
        f2 = Gfunc(x2);
        funcCalc+=2;
        //відображення таблиці
        System.out.println("i\tx1\t\t\t\tx2\t\t\t\tf1\t\t\t\tf2\t\t\t\tL");

        int i = 1;
        while ((b-a) > eps){
            //вивід значень змінних на екран
            System.out.printf(i + "\t%.5f",x1);
            System.out.printf(i + "\t\t%.5f",x2);
            System.out.printf(i + "\t\t%.5f",f1);
            System.out.printf(i + "\t\t%.5f",f2);
            System.out.printf(i + "\t\t%.5f",l);
            System.out.println();

            if(f1 > f2){    //Виключаємо лівий інтервал
                a = x1;
                l = b-a;
                x1 = x2;        //х2 використовується повторно, тільки вже як нова х1
                f1 = f2;
                x2 = (a+0.618*l);   //обчислюємо нову х2
                f2 = Gfunc(x2);
                funcCalc++;
            }
            else {          //виключаємо правий інтервал
                b = x2;
                l = b-a;
                x2 = x1;    //х1 використовується повторно, тільки вже як нова х2
                f2 = f1;
                x1 = (a + 0.382*l); //обчислюємо нову х1
                f1 = Gfunc(x1);
                funcCalc++;
            }
            i++;
        }

        xOut = (a + b)/2;   //х мінімальне
        return xOut;
    }

    public static void main(String[] args) {
        double h = 0.1;
        double[] koeff = Sven.DSK(0.5, h);
        System.out.printf("\nx minimum = %.3f; function calculations = %d\n", goldenRatioMethod(koeff[0],koeff[1], h), funcCalc);
        System.out.println("Total number of calculations: " + (funcCalc + Sven.funcCalc));
    }
}
