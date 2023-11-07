public interface Queue{
    void put(RunnableTask r);
    RunnableTask take();
}