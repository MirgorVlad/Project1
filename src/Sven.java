public class Sven {

    public static double Ffunc(double x){ //процедура обчислення ЦФ F
        return x*x - 7*x + 6;   //x^2 -7x + 6;
    }

    public static double Gfunc(double x){ //процедура обчислення ЦФ G
        return Math.pow(x-1,2)*Math.pow(x+1,3);  //(x-1)^2 * (x+1)^3
    }

    public static int funcCalc = 0;

    public static double[] DSK(double x0, double h){
        System.out.println("DSK:");
        double a,b, x3, f3;
        double x1 = x0 - h;
        double x2 = x0 + h;
        double f0 = Gfunc(x0);
        double f1 = Gfunc(x1);
        double f2 = Gfunc(x2);
        funcCalc+=3;

        System.out.println("h = " + h);
        if((f1 > f0) && (f2 > f0)){     //границі інтервалу знайдено
            a = x1;
            b = x2;
        } else if((f1 < f0) && (f2 < f0)){
            System.out.println("Функція не є унімодальою на початковому інтервалі");
        }

        if(f1 <= f2) h = -h;
        else {
            x1 =x2;
            f1=f2;
        }
        System.out.println("i\t\tx0\t\tx1\t\tx2\t\tf1\t\tf2");
        for(int i = 1; i < 10; i++){        //обмежуємо максимальну к-сть кроків, щоб уникнути надмірного збільшення інтервалу
            h = 2*h;
            x2 = x1+h;
            f2 = Gfunc(x2);
            funcCalc++;
            System.out.printf(i + "\t %.3f",x0);
            System.out.printf("\t %.3f",x1);
            System.out.printf("\t %.3f",x2);
            System.out.printf("\t %.3f",f1);
            System.out.printf("\t %.3f\n",f2);
            if(f2 <= f1){                   //продовжуємо пошук
                x0 = x1;
                x1 = x2;
                f1 = f2;
            } else {
                x3 = x2 - h/2;              //півкроку назад
                f3 = Gfunc(x3);
                funcCalc++;
                if(f1 > f3){                //виключити гіший підінтервал
                    a = x1;
                    b = x2;
                } else {
                    a = x0;
                    b = x3;
                }
                if(a > b){
                 double c = a;
                 a = b;
                 b = c;
                }
                System.out.printf("a = %.3f; b = %.3f; function calculations = %d;", a,b, funcCalc);
                return new double[] {a,b};  //нормальне завершення алгоритму
            }
        }
        System.out.println("Початкова точка задана далеко від точки мінімуму");     //інтервал не знайдено за n кроків
        return null;
    }

    public static void main(String[] args) {
        //DSK(0.5, 0.1);
    }
}
