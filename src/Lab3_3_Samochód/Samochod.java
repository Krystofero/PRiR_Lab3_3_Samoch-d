package Lab3_3_Samochód;

import java.util.Random;

public class Samochod extends Thread {
    private String nrRej;
    private int pojZbiornika;
    private int paliwo;
    private int ile_tankowal;
    private boolean czy_jedzie;
    Random rand= new Random();

    public Samochod (String nr, int _pojZbiornika){
        this.nrRej = nr;
        this.pojZbiornika = _pojZbiornika;
        this.paliwo = rand.nextInt(pojZbiornika);;
        this.czy_jedzie = false;
        this.ile_tankowal = 0;
    }
    public void tankowanie (int _paliwo){
        System.out.println("Auto " + this.nrRej + " tankuje " + _paliwo + "L paliwa.");
        this.paliwo += _paliwo;
        ile_tankowal += 1;
    }

    //start samochodu, uruchamiamy wątek zużycia paliwa
    @Override
    public void start() {
        System.out.println("Auto " + nrRej + " ruszyło.");
        this.czy_jedzie = true;
        super.start();
    }
    //zatrzymanie samochodu, zatrzymujemy wątek zużycia paliwa
    public void stop1() {
        System.out.println("Auto " + nrRej + " zatrzymało się.");
        this.czy_jedzie = false;
    }
    //kod, który wykonuje się w odrębnym wątku, co 1 s programu zużywany jest 1 litr paliwa
    @Override
    public void run() {
        while(czy_jedzie){
            if(this.ile_tankowal >= 15 || this.paliwo <= 0) {
                this.stop1();
            }
            else {
                try {
                    if (this.paliwo <= 50) {
                        this.tankowanie(rand.nextInt(pojZbiornika - 15));
                        Thread.sleep(rand.nextInt(2500));
                    } else {
                        System.out.println("Auto " + this.nrRej + " jedzie : paliwo = " + this.paliwo + ", pojemność zbiornika = " + this.pojZbiornika);
                        this.paliwo -= 1;
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {

                }
            }
        }
    }


}
