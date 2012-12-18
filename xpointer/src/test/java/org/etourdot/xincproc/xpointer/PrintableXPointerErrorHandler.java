package org.etourdot.xincproc.xpointer;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 18/11/12
 * Time: 18:33
 */
public class PrintableXPointerErrorHandler implements XPointerErrorHandler {
    private final StringBuilder stringBuilder;

    public PrintableXPointerErrorHandler() {
        stringBuilder = new StringBuilder();
    }

    @Override
    public void reportError(String error) {
        stringBuilder.append(error);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
