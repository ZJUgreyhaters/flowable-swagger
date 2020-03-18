package pub.cwb.workflow.workflowcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.OAuth2Definition;
import liquibase.pro.packaged.E;
import org.flowable.idm.api.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.cwb.auth.constants.ReturnCode;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.auth.pojo.UserBase;
import pub.cwb.workflow.pojo.view.EngineUserVO;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author athena
 */
@Api(tags = {"User Info Process"})
@RestController
@RequestMapping("pub/wf/user")
public class UserController {

    @GetMapping("/listAll")
    @ApiOperation(value = "list all user", notes = "listAll()")
    public ResponseBase listAllUser() {

        ResponseBase<List<EngineUserVO>> re = new ResponseBase<>();
        List<User> users = new ArrayList<>();
        try {
            users = FlowableEngine.getEngine().getIdentityService().createUserQuery().list();
        } catch (Exception e) {
            re.setMsg("获取用户失败");
            return re;
        }

        List<EngineUserVO> datas = new ArrayList<>();

        for (User user: users
             ) {
            EngineUserVO tmp = new EngineUserVO(user);

            datas.add(tmp);
        }

        re.setData(datas);
        return re;
    }

}
