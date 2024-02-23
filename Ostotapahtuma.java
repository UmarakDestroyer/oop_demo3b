public class Ostotapahtuma {
    private Asiakas asiakas;
    private Myyja myyja;
    private Tuote tuote;
    private int maara;
    private double hinta;

    public Ostotapahtuma(Asiakas asiakas, Myyja myyja,
                         Tuote tuote, int maara) {
        this.asiakas = asiakas;
        this.myyja = myyja;
        this.tuote = tuote;
        this.maara = maara;
        hinta = maara * tuote.getHinta();
	if (asiakas instanceof KantaAsiakas){
        	this.hinta = hinta - hinta * (((KantaAsiakas) asiakas).getAlennusprosentti() / 100.0);
	}
	else{
        	this.hinta = hinta - hinta * (asiakas.getAlennusprosentti() / 100.0);
	}
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public Myyja getMyyja() {
        return myyja;
    }

    public Tuote getTuote() {
        return tuote;
    }

    public int getMaara() {
        return maara;
    }

    public double getHinta() {
        return hinta;
    }
}
