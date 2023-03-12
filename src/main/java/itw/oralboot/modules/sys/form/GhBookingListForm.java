package itw.oralboot.modules.sys.form;

import lombok.Data;

@Data
public class GhBookingListForm {
    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 患者名
     */
    private String patientName;

}
