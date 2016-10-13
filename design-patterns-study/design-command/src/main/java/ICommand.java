/**
 * 抽象命令类
 * @author Yao.Jinwei
 * @date 2016/10/13 13:44
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public interface ICommand {
    void execute();

    void undoExecute();
}
