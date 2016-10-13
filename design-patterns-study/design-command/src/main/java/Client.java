/**
 *  命令模式(Command Pattern)
 *    将一个请求封装为一个对象，从而使我们可用不同的请求对客户进行参数化；对请求排队或者记录请求日志，以及支持可撤销的操作。
 *  Command模式优点：
 *    1) 降低系统的耦合度:Command模式将调用操作的对象与知道如何实现该操作的对象解耦。
 *    2) Command是头等的对象。它们可像其他的对象一样被操纵和扩展。
 *    3) 组合命令:你可将多个命令装配成一个组合命令，即可以比较容易地设计一个命令队列和宏命令。一般说来，组合命令是Composite模式的一个实例。
 *    4) 增加新的Command很容易，因为这无需改变已有的类。
 *    5）可以方便地实现对请求的Undo和Redo。
 *  命令模式的缺点：
 *    使用命令模式可能会导致某些系统有过多的具体命令类。因为针对每一个命令都需要设计一个具体命令类，因此某些系统可能需要大量具体命令类，这将影响命令模式的使用。
 * @author Yao.Jinwei
 * @date 2016/10/13 12:03
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Client {
    public static void main(String[] args) {
        Document document = new Document("演示文本");
        CommandManager commandManager = new CommandManager();

        //加粗
        ICommand command = new BoldCommand(document);
        commandManager.executeCommand(command);

        //斜体
        ICommand command1 = new ItalicCommand(document);
        commandManager.executeCommand(command1);

        //撤销
        commandManager.undo();

        commandManager.undo();

        //重做
        commandManager.redo();

        commandManager.redo();
    }
}
