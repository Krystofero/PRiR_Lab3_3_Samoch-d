Programowanie równolegle i rozproszone - projekt 3 labolatorium 3 Samochód

Projekt przedstawia wykorzystanie klasy Samochód i pracę na wielu wątkach aby zobrazować zużycie paliwa w czasie. Co 1 s programu zużywany jest 1 litr paliwa.
Jeżeli liczba tankowań osiągnie 15 lub liczba paliwa osiągnie 0 samochód jest zatrzymywany ,
Przy 50 L paliwa samochod jest tankowany o jego losową ilość a także czeka 2,5 s tankując.

Składa się z 2 plików: TestSamochod.java oraz Samochod.java

W pliku TestSamochod.java tworzonych jest kilka obiektów klasy Samochod , a praca każdego z nich jest uruchamiana na innym wątku:

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
    
Klasa Samochod rozszerza klasę Thread.    
Konstruktor klasy Samodchod przyjmuje dwa argumenty(numer rejestracji samochodu oraz pojemność zbiornika paliwa)
przy okazji losowo ustawiana jest ilość początkowego paliwa samochodu(nie większą od pojemności zbiornika paliwa), oraz ustawiana zmienna czy_jedzie na false, 
a także zerowany jest licznik tankowań:

  public Samochod (String nr, int _pojZbiornika){
        this.nrRej = nr;
        this.pojZbiornika = _pojZbiornika;
        this.paliwo = rand.nextInt(pojZbiornika);;
        this.czy_jedzie = false;
        this.ile_tankowal = 0;
    }
    
Metoda tankowanie(int paliwo) odpowiada za tankowanie auta o okreśłoną ilość paliwa i zwiększa licznik tankowań o 1:

  public void tankowanie (int _paliwo){
        System.out.println("Auto " + this.nrRej + " tankuje " + _paliwo + "L paliwa.");
        this.paliwo += _paliwo;
        ile_tankowal += 1;
    }
    
Metoda start samochodu, uruchamia wątek zużycia paliwa ii ustawia zmienną czy_jedzie na true:

    @Override
    public void start() {
        System.out.println("Auto " + nrRej + " ruszyło.");
        this.czy_jedzie = true;
        super.start();
    }
    
Metoda stop1() odpowiada za zatrzymanie samochodu(czy_jedzie = false) - zatrzymujemy wątek zużycia paliwa:

    public void stop1() {
        System.out.println("Auto " + nrRej + " zatrzymało się.");
        this.czy_jedzie = false;
    }

Metoda run() wykonuje się w odrębnym wątku, co 1 s programu zużywany jest 1 litr paliwa
Jeżeli liczba tankowań osiągnie 15 lub liczba paliwa osiągnie 0 samochód jest zatrzymywany ,
Przy 50 L paliwa samochod jest tankowany o jego losową ilość a także czeka 2,5 s tankując:

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
