package utilities;

import java.util.ArrayList;

/**
 * @author ET
 * @version 01/10/2019 12:22
 */
public class Errors extends ArrayList<String> {
    private Errors(String error) {
        super();
        if (error != null && !error.equals("")) {
            this.add(error);
        }
    }

    private Errors() {
        super();
    }

    @Override
    public boolean add(String error) {
        if (error != null && !error.equals("")) {
            return super.add(error);
        }
        return false;
    }

    public static Errors create() {
        return new Errors();
    }

    public static Errors create(String error) {
        return new Errors(error);
    }
}