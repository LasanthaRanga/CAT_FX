package modle;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class WSA {

    public WSA(int idWard, String Ward, int idStreet, String Street, int idAssesmant, String Assesmant) {
        this.idWard = idWard;
        this.Ward = new SimpleStringProperty(Ward);
        this.idStreet = idStreet;
        this.Street = new SimpleStringProperty(Street);
        this.idAssesmant = idAssesmant;
        this.Assesmant = new SimpleStringProperty(Assesmant);
    }

    /**
     * @return the idWard
     */
    public int getIdWard() {
        return idWard;
    }

    /**
     * @return the Ward
     */
    public String getWard() {
        return Ward.get();
    }

    /**
     * @return the idStreet
     */
    public int getIdStreet() {
        return idStreet;
    }

    /**
     * @return the Street
     */
    public String getStreet() {
        return Street.get();
    }

    /**
     * @return the idAssesmant
     */
    public int getIdAssesmant() {
        return idAssesmant;
    }

    /**
     * @return the Assesmant
     */
    public String getAssesmant() {
        return Assesmant.get();
    }

    private final int idWard;
    private final SimpleStringProperty Ward;

    private final int idStreet;
    private final SimpleStringProperty Street;

    private final int idAssesmant;
    private final SimpleStringProperty Assesmant;

}
