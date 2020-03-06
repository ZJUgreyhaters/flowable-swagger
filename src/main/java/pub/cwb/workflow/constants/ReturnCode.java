package pub.cwb.workflow.constants;

public enum ReturnCode {

    SUCCESS("200", "nice work."),
    EXCEPTION("300", "Code problem"),
    SYSERROE("400", "Bad And Sad."),
    FAIL("600", "What hapenned?"),

    ;

    private String code;
    private String desc;
    ReturnCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * If the field of a class is a object, it's dangerous to return the ref of the
     * var[code], because it may be modified by the caller.
     * @return
     */
    public String getCode() {
        String tmp = new String(this.code);
        return tmp;
    }
}
