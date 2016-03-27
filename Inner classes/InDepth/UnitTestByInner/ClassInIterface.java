
public interface ClassInIterface
{
    void howdy();

    class Test implements ClassInIterface
    {
        public void howdy()
        {
            System.out.println("Hello!");
        }

        public static void main(String[] args)
        {
            new Test().howdy();
        }
    }
}