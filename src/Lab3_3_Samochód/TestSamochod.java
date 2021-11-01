package Lab3_3_Samochód;

public class TestSamochod {
    // symulacja działania klasy Samochod dla 1,2,3, … samochodów
    public static void main(String[] args) {
        Samochod S1 = new Samochod("GG0303", 100);
        Samochod S2 = new Samochod("LOL998", 300);
        Samochod S3 = new Samochod("XD007", 500);
        Samochod S4 = new Samochod("DX2222", 75);
        Samochod S5 = new Samochod("RX6367", 500);

        S1.start();
        S2.start();
        S3.start();
        S4.start();
        S5.start();
    }

}
