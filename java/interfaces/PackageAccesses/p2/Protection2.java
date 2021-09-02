package p2;

class Protection2 extends p1.Protection
{
	Protection2()
    {
		System.out.println("Derived constructer of other package");
		
		// System.out.println("n = " + n); // только класс или пакет
		// System.out.println("n_pri = " + n_pri);	// только класс
		System.out.println("n_pro = " + n_pro);
		System.out.println("n_pub = " + n_pub);
	}
}