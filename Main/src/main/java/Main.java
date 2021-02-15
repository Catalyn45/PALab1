class Worker {
    String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

    // Afiseaza hello world
    void print_hello() {
        System.out.println("Hello world");
    }

    // genereaza un numar random intre 0 si 1.000.000
    int generate() {
        return (int)(Math.random() * 1000000);
    }

    // se efectueaza urmatoarele operatii pe numarul n
    int compute(int n) {
        return (n * 3 + 0b10101 + 0xFF) * 6;
    }


    // insumeaza cifrele lui n pana cand rezultatul este un numar de o singura cifra
    int add_digits(int n) {
        while(n > 9)
        {
            int new_result = 0;
            while(n > 0)
            {
                new_result = new_result + n % 10;
                n = n / 10;
            }

            n = new_result;
        }

        return n;
    }

    // se afiseaza mesajul urmat de valoarea de la index-ul n din vectorul languages
    void display(int n) {
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }

    void run()
    {
        int random = generate();
        int computed = compute(random);
        int result = add_digits(computed);
        display(result);
    }
}

public class Main {
    public static void main(String[] args){
        Worker worker = new Worker();
        worker.print_hello();
        worker.run();
    }
}
