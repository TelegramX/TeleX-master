package sections.shamsi;

public class ShamsiDateIllegalFormatException extends Exception {
    private static final long serialVersionUID = -8438467705121770819L;

    public ShamsiDateIllegalFormatException(String str) {
        super(str);
    }

    public ShamsiDateIllegalFormatException(String str, Throwable th) {
        super(str, th);
    }

    public ShamsiDateIllegalFormatException(Throwable th) {
        super(th);
    }
}
