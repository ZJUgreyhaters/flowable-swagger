package pub.cwb.workflow.pojo.view;

import lombok.Data;
import org.flowable.idm.api.User;

@Data
public class EngineUserVO {
    
    private String Id;

    private String FirstName;

    private String LastName;

    private String DisplayName;
    
    private String Email;

    private String Password;

    private String TenantId;
    
    public EngineUserVO(User user) {
        this.Id = user.getId();

        this.FirstName = user.getFirstName();

        this.LastName = user.getLastName();

        this.DisplayName = user.getDisplayName();

        this.Email = user.getEmail();

        this.Password = user.getPassword();

        this.TenantId = user.getTenantId();
    }
}
