package p1;

class SamePackage
{
	SamePackage()
    {
		Protection p = new Protection();
		System.out.println("The same package constructer");
		System.out.println("n = " + p.n);
		
		// System.out.println("n_pri = " + p.n_pri); // Только класс
		
		System.out.println("n_pro = " + p.n_pro);	// ???
		System.out.println("n_pub = " + p.n_pub);
	}
}