package utilities;

import java.util.Optional;

/**
 * This class is designed handling the results of data validation. If the object is
 * valid. we want to return the object. If it is not valid then we want to return
 * messages that say why it is not valid.
 *
 * In this, class, we are following a functional approach so the class using an Optional
 * object.
 *
 * @author ET
 * @version 27/08/2019 15:51
 */
public class Validation<T> {
    private final T object;
    private final utilities.Errors errorMessage;

    /**
     * This constructor is used when we have a valid object to return.
     *
     * @param object a valid object object
     */
    public Validation(T object) {
        this.object = object;
        errorMessage = utilities.Errors.create();
    }

    /**
     * This constructor is used when we want to report error messages.
     *
     * @param errorMessage The error messages
     */
    public Validation(utilities.Errors errorMessage) {
        this.errorMessage = errorMessage;
        this.object = null;
    }

    /**
     * Returns the error messages or an empty string. Potentially, this could be an
     * optional String.
     *
     * @return error messages if there are some and an empty string otherwise.
     */
    public utilities.Errors getErrorMessage() {
        return errorMessage;
    }

    public boolean noMessages() {
        return errorMessage.size() == 0;
    }

    /**
     * This returns an Optional<T> object in order to conform with functional
     * style thinking.
     *
     * @return An Optional<T> object
     */
    public Optional<T> get() {
        return Optional.ofNullable(object);
    }

    /**
     * This returns the object or null if no object exists. <b>Note: </b> this is added just to
     * reduce coding when you want the object reference rather than use the optional features.
     *
     * @return a T object
     */
    public T getObject() {
        return object;
    }

    /**
     * If a Person object is present, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if a Person object is present, otherwise {@code false}
     */
    public boolean isPresent() {
        return object != null;
    }

    /**
     * If a Person object is not present, returns {@code true}, otherwise
     * {@code false}.
     *
     * @return  {@code true} if a Person object is not present, otherwise {@code false}
     */
    public boolean isEmpty() {
        return object == null;
    }

    /**
     * Function to verify whether a string is present and return an error message if it is not.
     *
     * @param string the string that should be present
     * @param errorMessage the error message to be used if it is not present
     * @return return and empty string if the string being validated exists and the error message
     * if it does not.
     */
    public static String isPresent(String string, String errorMessage) {
        if (string == null || string.trim().length() == 0) { return errorMessage; }
        return null;
    }

}
