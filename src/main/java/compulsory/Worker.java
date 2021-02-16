package compulsory;

public class Worker {
    String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

    // Afiseaza hello world
    private void printHello() {
        System.out.println("Hello world");
    }

    // genereaza un numar random intre 0 si 1.000.000
    private int generate() {
        return (int)(Math.random() * 1000000);
    }

    // se efectueaza urmatoarele operatii pe numarul n
    private int compute(int n) {
        return (n * 3 + 0b10101 + 0xFF) * 6;
    }


    // insumeaza cifrele lui n pana cand rezultatul este un numar de o singura cifra
    private int addDigits(int n) {
        while(n > 9)
        {
            int newResult = 0;
            while(n > 0)
            {
                newResult = newResult + n % 10;
                n = n / 10;
            }

            n = newResult;
        }

        return n;
    }

    // se afiseaza mesajul urmat de valoarea de la index-ul n din vectorul languages
    private void display(int n) {
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }

    public void run()
    {
        int random = generate();
        int computed = compute(random);
        int result = addDigits(computed);
        display(result);
    }
}
