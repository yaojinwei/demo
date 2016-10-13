import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Yao.Jinwei
 * @date 2016/10/13 13:50
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class CommandManager {
    private BlockingDeque undoQueue = new LinkedBlockingDeque();
    private BlockingDeque redoQueue = new LinkedBlockingDeque();

    public void executeCommand(ICommand command){
        undoQueue.addFirst(command);
        command.execute();
    }

    public void undo(){
        if(undoQueue.size() > 0){
            ICommand command = (ICommand)undoQueue.pollFirst();
            command.undoExecute();
            redoQueue.addFirst(command);
        }
    }

    public void redo(){
        if(redoQueue.size() > 0){
            ICommand command = (ICommand)redoQueue.pollFirst();
            command.execute();
            undoQueue.addFirst(command);
        }
    }
}
