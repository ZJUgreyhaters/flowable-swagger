package pub.cwb.workflow.pojo;

import lombok.Data;

@Data
public class HolidayReq {
    private String userId;

    private int days;

    private String startDate;

    private String endDate;

    private String reason;

    private String status;
}
