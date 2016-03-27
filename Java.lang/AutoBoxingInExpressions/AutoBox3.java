// јвтоупаковка/автораспаковка в выражени€х
public class AutoBox3
{
    public static void main(String args[])
    {
        Integer iOb, iOb2;
        int i;

        iOb = 100;
        System.out.println("Original value of iOb: " + iOb);

        /* —ледующее автоматически распаковывает iOb, выполн€ет его
        приращение, затем повторно упаковывает результат обратно в iOb */
        ++iOb;
        System.out.println("After ++iOb: " + iOb);

        /* «десь iOb распаковано, выражение вычисл€етс€, а результат снова упаковываетс€
        и присваиваетс€ iOb2. */
        iOb2 = iOb + (iOb / 3);
        System.out.println("iOb2 after expression: " + iOb2);

        // ¬ычисл€етс€ то же самое выражение, но результат не упаковываетс€.
        i = iOb + (iOb / 3);
        System.out.println("i after expression: " + i);
    }
}
