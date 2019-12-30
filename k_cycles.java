import java.util.*;
import java.io.*;
import java.lang.*;

class k_cycles {
  public static void main(String[] args) throws IOException {
    set_init_amount();
  }
  public static void users_run() {
    Scanner input = new Scanner(System.in);
    double a, b, f, r;
    while(true) {
      //get A, B, kf, kr
      System.out.print("A: ");
      a = input.nextDouble();
      System.out.print("B: ");
      b = input.nextDouble();
      System.out.print("K forward: ");
      f = input.nextDouble();
      System.out.print("K reverse: ");
      r = input.nextDouble();
      f(a, b, f, r);
      System.out.println();
    }
  }
  public static void set_init_amount() throws IOException {
    Scanner input = new Scanner(System.in);
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("out.csv")));
    double a, b, f;
    int K[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}; 
    ArrayList< ArrayList<String> > output = new ArrayList< ArrayList<String> >();
    for (int i = 0; i < K.length; i++) {
      output.add(new ArrayList<String>());
    }
    for (int i = 0; i < K.length; i++) {
      output.get(i).add(Integer.toString(K[i]));
    }
    int co = 1; 
    while(true) {
      System.out.print("A: ");
      a = input.nextDouble();
      if (a == -1) break;
      co+=2;
      System.out.print("B: ");
      b = input.nextDouble();
      System.out.print("K forward: ");
      f = input.nextDouble();
      output.get(0).add(Double.toString(a));
      output.get(1).add(Double.toString(b));
      output.get(2).add(Double.toString(f));
      for (int i = 3; i < K.length; i++) {
        output.get(i).add("");
      }
      for (int i = 0; i < K.length; i++) {
        double k = K[i];
        double r = f/k;
        int ret = f(a, b, f, r);
        output.get(i).add(Integer.toString(ret));
      }
      System.out.println();
    }
    //format in csv
    for (int i = 0; i < K.length; i++) {
      for (int j = 0; j < co; j++) {
        out.print(output.get(i).get(j));
        if (j != co-1) out.print(",");
      }
      out.println();
    }
    out.close();
  }
  public static int f(double a, double b, double f, double r) {
    double temp_a, p_error;
    int n; String str_A, str_B;
    n = 0; 
    p_error = .02; 
    boolean invalid = false;
    str_A = String.format("%.2f", a); 
    str_B = String.format("%.2f", b); 
    //System.out.println(n+": "+str_A+" "+str_B);
    while(Math.abs(a*f/(b*r)-1)>p_error) { 
      if (a < 0 || b < 0) {
        //System.out.println("hi");
        invalid = true;
        break;
      }
      n++;
      temp_a = a;
      a = a-a*f+b*r;
      b = b+temp_a*f-b*r;
      str_A = String.format("%.2f", a);
      str_B = String.format("%.2f", b);
      //System.out.println(n+": "+str_A+" "+str_B);
    }
    if (invalid == true) {
      System.out.println("invalid input");
      n = -1;
    }
    if (invalid == false) {
      System.out.println(n); 
    }
    return n;
  }
}