package p2;

class OtherPackage
{
	OtherPackage() {
		p1.Protection p = new p1.Protection();
		System.out.println("Constructer of other package");
		
		// System.out.println("n = " + p.n); // Только класс или пакет
		// System.out.println("n_pri = " + p.n_pri); // Только класс
		// System.out.println("n_pro = " + p.n_pro); // Только класс, подкласс или пакет
		System.out.println("n_pub = " + p.n_pub);
	}
}