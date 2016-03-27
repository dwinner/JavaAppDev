// Вложенные классы могут обращаться ко всем членам всех
// классов, в которых они находятся

class MNA
{
    private void f()
    {
    }

    class A
    {
        private void g()
        {
        }

        public class B
        {
            void h()
            {
                g();
                f();
            }
        }
    }
}

public class MultiNestingAccess
{
    public static void main(String[] args)
    {
        MNA mna = new MNA();
        MNA.A mnna = mna.new A();
        MNA.A.B mnaab = mnna.new B();
        mnaab.h();
    }
}
