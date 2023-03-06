package itw.oralboot.modules.sys.form;

import itw.oralboot.modules.sys.entity.PatientEntity;
import itw.oralboot.modules.sys.entity.SysGhEntity;
import lombok.Data;

@Data
public class GhPatientForm extends SysGhEntity{

    private PatientEntity patientEntity = new PatientEntity();


}
