package pub.cwb.workflow.pojo.view;

import lombok.Data;
import org.flowable.idm.api.User;

@Data
public class EngineUserVO {
    
    private String id;

    private String firstName;

    private String lastName;

    private String displayName;
    
    private String email;

    private String password;

    private String tenantId;
    
    public EngineUserVO(User user) {
        this.id = user.getId();

        this.firstName = user.getFirstName();

        this.lastName = user.getLastName();

        this.displayName = user.getDisplayName();

        this.email = user.getEmail();

        this.password = user.getPassword();

        this.tenantId = user.getTenantId();
    }
}
