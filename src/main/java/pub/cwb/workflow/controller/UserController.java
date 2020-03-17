package pub.cwb.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.cwb.auth.constants.ReturnCode;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.auth.pojo.UserBase;

import java.util.LinkedList;
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
        ResponseBase<List<UserBase>> re = new ResponseBase<>();

        List<UserBase> data = new LinkedList<>();

        UserBase a = new UserBase(11,"alice", "aa", "123@zju.edu.cn");
        UserBase b = new UserBase(22, "bob", "bb", "456@zju.edu.cn");

        data.add(a);
        data.add(b);

        re.setData(data);
        re.setCode(ReturnCode.SUCCESS.getCode());
        re.setMsg("Success.");
        return re;
    }

}
