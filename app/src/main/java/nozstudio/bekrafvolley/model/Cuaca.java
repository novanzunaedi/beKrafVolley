package nozstudio.bekrafvolley.model;

/**
 * Created by CLient-Pc on 29/06/2016.
 */
public class Cuaca {

    private String sdatetime;

    public Cuaca(String sdatetime) {
        this.sdatetime = sdatetime;
    }

    public String getSdatetime() {
        return sdatetime;
    }

    public Cuaca setSdatetime(String sdatetime) {
        this.sdatetime = sdatetime;
        return this;
    }
}
