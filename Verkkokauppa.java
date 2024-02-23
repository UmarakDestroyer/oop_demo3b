import java.util.ArrayList;
import java.util.HashMap;

public class Verkkokauppa {
    private HashMap<String, Asiakas> asiakkaat;
    private HashMap<String, Tuote> tuotteet;
    private HashMap<String, Myyja> myyjat;
    private ArrayList<Ostotapahtuma> tapahtumat;

    public Verkkokauppa() {
        asiakkaat = new HashMap<>();
        tuotteet = new HashMap<>();
        myyjat = new HashMap<>();
        tapahtumat = new ArrayList<>();
    }

    public void lisaaAsiakas(Asiakas asiakas) {
	    this.asiakkaat.put(asiakas.getAsiakasNumero(), asiakas);
    }

    public void lisaaTuote(Tuote tuote) { 
	    this.tuotteet.put(tuote.getNimi(), tuote);
    }

    public void lisaaMyyja(Myyja myyja) {
	    this.myyjat.put(myyja.getTunniste(), myyja);
    }

    public void lisaaTapahtuma(Ostotapahtuma tapahtuma) {
	    this.tapahtumat.add(tapahtuma);
    }

    /**
     * Yrittää poistaa annetun asiakkaan asiakalistasta.
     *
     * @param asiakas asiakas, jonka poistoa yritetään
     * @return true, jos asiakas löytyi ja poistettiin, muuten false
     */
    public boolean poistaAsiakas(Asiakas asiakas) {
        if (this.asiakkaat.containsKey(asiakas.getAsiakasNumero())) {
            this.asiakkaat.remove(asiakas.getAsiakasNumero())
            return true;
        }
        return false;
    }

    /**
     * Yrittää poistaa annetun tuotteen tuotelistasta.
     *
     * @param tuote tuote, jonka poistoa yritetään
     * @return true, jos tuote löytyi ja poistettiin, muuten false
     */
    public boolean poistaTuote(Tuote tuote) {
        if (this.tuotteet.containsKey(tuote.getNimi())) {
            this.tuotteet.remove(tuote.getNimi());
            return true;
        }
        return false;
    }

    /**
     * Yrittää poistaa annetun myyjän myyjalistasta.
     *
     * @param myyja myyja, jonka poistoa yritetään
     * @return true, jos myyjä löytyi ja poistettiin, muuten false
     */
    public boolean poistaMyyja(Myyja myyja) {
        if (this.myyjat.containsKey(myyja.getTunniste())) {
            this.myyjat.remove(myyja.getTunniste());
            return true;
        }
        return false;
    }

    /**
     * Yrittää poistaa annetun tapahtuman ostotapahtumalistasta.
     *
     * @param tapahtuma ostotapahtuma, jonka poistoa yritetään
     * @return true, jos ostotapahtuma löytyi ja poistettiin, muuten false
     */
    public boolean poistaOstotapahtuma(Ostotapahtuma tapahtuma) {
        if (tapahtumat.contains(tapahtuma)) {
            tapahtumat.remove(tapahtuma);
            return true;
        }
        return false;
    }

    /**
     * Palauttaa annetun asiakasnumeron mukaisen asiakkaan tai
     * <code>null</code>, jos asiakasta ei löydy
     *
     * @param asiakasnumero halutun asiakkaan asiakasnumero
     * @return pyydetyn asiakkaan tai null, jos asiakasta ei löydy
     */
    public Asiakas annaAsiakas(String asiakasnumero) {
	if(asiakkaat.containsKey(asiakasnumero)){
		return asiakkaat.get(asiakasnumero);
	}
        return null;
    }

    /**
     * Palauttaa annetun nimen mukaisen tuotteen tai
     * <code>null</code>, jos tuotetta ei löydy
     *
     * @param nimi halutun tuotteen nimi
     * @return pyydetyn tuotteen tai null, jos tuotetta ei löydy
     */
    public Tuote annaTuote(String nimi) {
	if(tuotteet.containsKey(nimi)){
		return tuotteet.get(nimi);
	}
        return null;
    }

    /**
     * Palauttaa annetun tunnisteen mukaisen myyjän tai
     * <code>null</code>, jos myyjää ei löydy
     *
     * @param tunniste halutun myyjän tunniste
     * @return pyydetyn myyjän tai null, jos myyjää ei löydy
     */
    public Myyja annaMyyja(String tunniste) {
	if(myyjat.containsKey(tunniste)){
		return myyjat.get(tunniste);
	}
        return null;
    }

    /**
     * Palauttaa annetun indeksin mukaisen ostotapahtuman listasta.
     * Huomaa, että indeksointi alkaa nollasta, mutta tapahtumien listaamisessa
     * indeksit on ilmoitettu alkaen ykkösestä.
     *
     * @param indeksi indeksi jonka kohdalta tapahtuma palautetaan
     * @return annetun indeksin mukaisen ostotapahtuman
     */
    public Ostotapahtuma annaTapahtuma(int indeksi) {
        if (tapahtumat.size()>indeksi) {
            return tapahtumat.get(indeksi);
        }
        return null;
    }


    /**
     * Palauttaa kaikki asiakkaat yhtenä merkkijonona.
     *
     * @return asiakalistan merkkijonona
     */
    public String listaaAsiakkaat() {
        // Tekee otsikkorivin
        // \t on tabulaattori, joka tasaa tulosteen
        // sarakkeisiin; \n tekee rivinvaihdon
        StringBuilder s = new StringBuilder("Asiakasnumero\tNimi\t\t\tOstoja\tKanta-asiakas\n");
        for (String asiakasNumero: asiakkaat.keySet()) {
            s.append(asiakasNumero + "\t\t\t");
            s.append(asiakkaat.get(asiakasnumero).getNimi() + "\t");
            s.append(asiakkaat.get(asiakasnumero).getOstojaTehty() + "\t\t");
            if (asiakkaat.get(asiakasnumero) instanceof KantaAsiakas) {
                s.append("On\n");
            } else {
                s.append("Ei\n");
            }
        }
        return s.toString();
    }

    /**
     * Palauttaa kaikki myyjät yhtenä merkkijonona
     *
     * @return myyjät merkkijonona
     */
    public String listaaMyyjat() {
        // Tekee otsikkorivin
        // \t on tabulaattori, joka tasaa tulosteen
        // sarakkeisiin; \n tekee rivinvaihdon
        StringBuilder s = new StringBuilder("Tunniste\tNimi\t\t\tProvisiot\n");
        for (String tunniste : myyjat.keySet()) {
            s.append(tunniste + "\t\t");
            s.append(myyjat.get(tunniste).getNimi() + "\t\t");
            s.append(myyjat.get(tunniste).getProvisiot() + "\n");
        }
        return s.toString();
    }

    /**
     * Palautta kaikki tuotteet yhtenä merkkijonona.
     *
     * @return tuotteet merkkijonona
     */
    public String listaaTuotteet() {
        // Tekee otsikkorivin
        // \t on tabulaattori, joka tasaa tulosteen
        // sarakkeisiin; \n tekee rivinvaihdon
        StringBuilder s = new StringBuilder("Nimi\tSaldo\t\t\tHinta\tVirtuaalinen\n");
        for (String nimi : tuotteet.keySet()) {
            s.append(nimi + "\t");
            s.append(tuoteet.get(nimi).getSaldo() + "\t\t");
            s.append(tuoteet.get(nimi).getHinta() + "\t");
            if (tuotteet.get(nimi). instanceof VirtuaalinenTuote) {
                s.append("Kyllä\n");
            } else {
                s.append("Ei\n");
            }
        }
        return s.toString();
    }

    /**
     * Palauttaa kaikki tapahtumat yhtenä merkkijonona.
     *
     * @return tapahtumat merkkijonona
     */
    public String listaaTapahtumat() {
        StringBuilder s = new StringBuilder("Numero\tTuote\tAsiakas\tMyyjä\tKpl\tHinta\n");
        int n = 1;
        for (int i : tapahtumat.keySet()) {
            s.append(n++ + "\t");
            s.append(tapahtuma.get(i).getTuote().getNimi() + "\t");
            s.append(tapahtuma.get(i).getAsiakas().getNimi() + "\t");
            s.append(tapahtuma.get(i).getMyyja().getNimi() + "\t");
            s.append(tapahtuma.get(i).getMaara() + "\t");
            s.append(tapahtuma.get(i).getHinta() + "\n");
        }
        return s.toString();

    }
}
