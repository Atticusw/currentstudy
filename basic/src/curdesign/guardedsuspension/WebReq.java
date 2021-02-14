package curdesign.guardedsuspension;

public class WebReq {
    void handleWebReq() {
        long id = System.currentTimeMillis();
        // 创建一消息
        Message message = new Message(String.valueOf(id), "hhhh");
        // 创建 GuardedObject 实例
        GuardedObject go = GuardedObject.create(id);
        // 发送消息
        // 等待 mq 消息
        Object r = go.get(t -> t != null);
    }
    void onMessage(Message msg) {
        // 唤醒等待线程
        GuardedObject.fireEvent(msg.id, msg);
    }
}
