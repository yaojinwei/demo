import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 表单处理
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class FormHandler {

    /**
     * 构造表单组件
     * @param request
     * @return
     */
    Component[] build(Map request){
        return new Component[]{
                new TextInput("firstName", request.get("firstName").toString()),
                new TextInput("lastName", request.get("lastName").toString()),
                new TextInput("email", request.get("email").toString())
        };
    }

    /**
     * 对组件数据的有效性进行判断
     * @param request
     * @param components
     * @return
     */
    Boolean validate(Map request, Component[] components){
        Boolean valid = true;

        if(request.get("firstName") == null || "".equals(request.get("firstName").toString())){
            components[0] = new DecoratorInvalid(components[0]);
            valid = false;
        }

        if(request.get("lastName") == null || "".equals(request.get("lastName").toString())){
            components[1] = new DecoratorInvalid(components[1]);
            valid = false;
        }
        //
        if(request.get("email") != null &&  !"".equals(request.get("email").toString())){
            Pattern p =  Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+\\w*$");//email匹配
            Matcher m = p.matcher(request.get("email").toString());
            if(!m.matches()){
                components[2] = new DecoratorInvalid(components[2]);
                valid = false;
            }
        }
        else{
            components[2] = new DecoratorInvalid(components[2]);
            valid = false;
        }
        return valid;

    }
}
