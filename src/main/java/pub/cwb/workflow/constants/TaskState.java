package pub.cwb.workflow.constants;


public enum TaskState {
    isAssigned("1"),
    notAssigined("0"),
    Finished("1")

    ;

    private String flag;

    TaskState(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return this.flag;
    }
}
