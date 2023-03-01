package itw.oralboot.modules.sys.form;

import com.baomidou.mybatisplus.core.metadata.IPage;
import itw.oralboot.modules.sys.entity.PatientEntity;
import itw.oralboot.modules.sys.entity.SysGhEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GhPatientForm extends SysGhEntity{

    private PatientEntity patientEntity = new PatientEntity();


}
