import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class treemenu extends Applet
    implements Runnable, ImageObserver
{

    float a;
    Frame b;
    boolean c;
    final String d = "Applet by Anfy Team (www.anfyjava.com)";
    boolean e;
    Image f;
    Color g;
    int h;
    Color i;
    int j;
    String k;
    int l[];
    int m[];
    int n[];
    int o;
    int p;
    int q;
    int r;
    int s;
    int t[];
    int u;
    Image v;
    int w[];
    int x;
    int y;
    int z;
    int A;
    int B;
    int C;
    int D;
    private boolean E;
    int F;
    int G;
    int H;
    int I;
    long J;
    int K;
    boolean L;
    Color M;
    int N;
    Color O;
    int P;
    Color Q;
    int R;
    final int S = 1;
    final int T = 2;
    final int U = 3;
    final int V = 0;
    int W[][];
    int X[];
    int Y[];
    private Image Z;
    int ba[][];
    int bb[][];
    boolean bc[][];
    int bd[];
    private int be;
    int bf[];
    boolean bg[][];
    int bh[];
    boolean bi[];
    int bj[];
    boolean bk[];
    int bl;
    int bm;
    int bn;
    int bo;
    String bp[];
    String bq[];
    private int br;
    boolean bs;
    final int bt = 6;
    final int bu = 7;
    final int bv = 11;
    final int bw = 9;
    final int bx = 8;
    final int by = 12;
    final int bz = 10;
    URL bA;
    private boolean bB;
    final int bC = 5;
    String bD;
    final int bE = 4;
    int bF;
    Thread bG;
    int bH;
    Font bI;
    int bJ;
    boolean bK;
    int bL;
    int bM;
    int bN;
    int bO;
    boolean bP;
    boolean bQ;
    int bR;
    int bS;
    MediaTracker bT;
    int bU;
    boolean bV;
    boolean bW;
    int bX[];
    boolean bY[];
    short bZ[];
    int ca;
    int cb;
    int cc;
    int cd;
    int ce;
    int cf;
    int cg;
    final int ch = 13;
    final int ci = 5;
    private Graphics cj;
    private Image ck;
    boolean cl;
    int cm;
    int cn;
    int co;
    int cp;
    int cq;
    int cr;
    int cs;
    int ct[];
    boolean cu[];
    short cv[];
    int cw;
    private Image cx;
    int cy;
    int cz;
    int cA;
    anfy cB;
    MemoryImageSource cC;
    int cD;
    int cE;
    int cF;
    int cG;
    String cH;
    boolean cI;
    int cJ;
    int cK;
    final int cL = -4;
    int cM;
    final int cN = -3;
    final int cO = -2;
    int cP;
    final int cQ = -5;
    int cR;
    int cS;
    final int cT = -8;
    int cU;
    final int cV = -9;
    final int cW = -7;
    int cX;
    final int cY = -6;
    int cZ;
    int da;
    int db;
    int dc[];
    boolean dd[];
    int de;
    short df[];
    int dg[][];
    boolean dh[][];
    Color di[];
    int dj[];
    int dk;
    private Toolkit dl;
    Thread dm;
    int dn;
    int _flddo;
    float dp[];
    float dq[];
    int dr;
    int ds;
    int dt;
    int du;
    int dv;
    int dw;
    int dx;
    int dy;
    boolean dz;
    float dA[];
    float dB[];
    int dC;
    int dD;
    private int dE;
    Lware dF;

    final void a(int i1, int j1)
    {
        int ai[] = new int[bh[i1]];
        int i2 = bh[i1];
        int ai1[] = ba[i1];
        for(int k1 = 0; k1 < i2; k1++)
        {
            ai[k1] = ai1[k1];
        }

        bh[i1]++;
        i2 = bh[i1];
        ba[i1] = new int[i2];
        ai1 = ba[i1];
        i2--;
        for(int l1 = 0; l1 < i2; l1++)
        {
            ai1[l1] = ai[l1];
        }

        ai1[bh[i1] - 1] = j1;
    }

    private final void a(int i1)
    {
        H++;
        int l1 = bh[i1];
        if(bF <= H)
        {
            bF = H + 1;
        }
        for(int j1 = 0; j1 < l1; j1++)
        {
            int k1 = ba[i1][j1];
            if(j1 == l1 - 1)
            {
                a(k1, H, true);
            } else
            {
                a(k1, H, false);
            }
            if(bi[k1])
            {
                a(k1);
            }
        }

        H--;
    }

    private final void a(int i1, int j1, boolean flag)
    {
        int ai[] = new int[cg];
        short aword0[] = new short[cg];
        boolean aflag[] = new boolean[cg];
        for(int k1 = 0; k1 < cg; k1++)
        {
            ai[k1] = dc[k1];
            aword0[k1] = df[k1];
            aflag[k1] = dd[k1];
        }

        cg++;
        dc = new int[cg];
        df = new short[cg];
        dd = new boolean[cg];
        for(int l1 = 0; l1 < cg - 1; l1++)
        {
            dc[l1] = ai[l1];
            df[l1] = aword0[l1];
            dd[l1] = aflag[l1];
        }

        dc[cg - 1] = i1;
        df[cg - 1] = (short)j1;
        dd[cg - 1] = flag;
    }

    private final void a()
    {
        ce = cg;
        bX = new int[ce];
        bZ = new short[ce];
        bY = new boolean[ce];
        for(int i1 = 0; i1 < ce; i1++)
        {
            bX[i1] = dc[i1];
            bZ[i1] = df[i1];
            bY[i1] = dd[i1];
        }

    }

    private final void b()
    {
        do
        {
            showStatus("Don't remove www.anfyjava.com credits line in HTML!");
            try
            {
                Thread.sleep(500L);
            }
            catch(InterruptedException _ex) { }
        } while(true);
    }

    public synchronized boolean c()
    {
        prepareImage(cx, this);
        if(bs)
        {
            for(int i1 = 0; i1 < 3; i1++)
            {
                notifyAll();
                Thread.yield();
                try
                {
                    Thread.sleep(100L);
                }
                catch(InterruptedException _ex) { }
            }

            return cl;
        } else
        {
            return false;
        }
    }

    private final void d()
    {
        if((cf < 1) | (cf == cg))
        {
            return;
        }
        dE = 0;
        a();
        int i2 = -1;
        int j2 = -1;
        if(cf > cg)
        {
            i2 = cg - 1;
            for(int i1 = 0; i1 < cg; i1++)
            {
                if(dc[i1] != ct[i1])
                {
                    i2 = i1 - 1;
                    i1 = cg;
                }
            }

            j2 = cf - 1;
            short word0 = cv[i2];
            for(int j1 = i2 + 1; j1 < cf; j1++)
            {
                if(cv[j1] <= word0)
                {
                    j2 = j1 - 1;
                    j1 = cf;
                }
            }

            ca = i2;
            bU = i2;
            cw = i2;
            cm = j2;
            return;
        }
        i2 = cf - 1;
        for(int k1 = 0; k1 < cf; k1++)
        {
            if(dc[k1] != ct[k1])
            {
                i2 = k1 - 1;
                k1 = cf;
            }
        }

        j2 = cg - 1;
        short word1 = df[i2];
        for(int l1 = i2 + 1; l1 < cg; l1++)
        {
            if(df[l1] <= word1)
            {
                j2 = l1 - 1;
                l1 = cg;
            }
        }

        ca = i2;
        bU = j2;
        cw = i2;
        cm = i2;
    }

    private void e()
    {
        for(int i1 = 0; i1 < cd; i1++)
        {
            int j1 = bf[i1];
            if((bh[i1] < 1) & bk[j1])
            {
                showStatus("Menu Item: " + i1 + " contains a map Icon (32x16) instead of a normal Icon (16x16)");
                stop();
            }
        }

    }

    private void f()
    {
        if(e && (bR > -1) & (bR < x) & (bS > -1) & (bS < u))
        {
            G = bd[bR + bS * x];
        }
        if(dz)
        {
            if((bL > -1) & (bL < x) & (bN > -1) & (bN < u))
            {
                db = bd[bL + bN * x];
                K = db;
            }
            dz = false;
        }
        if(((bL != bM) | (bN != bO)) & bQ & ((K == -8) | (K == -4)))
        {
            if(K == -8)
            {
                B = (int)(-((double)(((cX + bN) - bO) * cE) / (double)(cJ - cR * 2)));
                bM = bL;
                bO = bN;
            }
            if(K == -4)
            {
                y = (int)(-((double)(((cP + bL) - bM) * cF) / (double)(cK - cR * 2)));
                bM = bL;
                bO = bN;
            }
            bK = true;
            return;
        }
        if(bK)
        {
            return;
        }
        bP = false;
        if(db > -1)
        {
            bi[db] = !bi[db];
            bK = true;
            G = db;
            if(bq[db] != null)
            {
                b(db);
            }
            return;
        }
        bK = true;
        switch(db)
        {
        case -6: 
            d(-1);
            bK = false;
            return;

        case -7: 
            d(0);
            bK = false;
            return;

        case -9: 
            d(1);
            bK = false;
            return;

        case -2: 
            c(-1);
            bK = false;
            return;

        case -3: 
            c(0);
            bK = false;
            return;

        case -5: 
            c(1);
            bK = false;
            return;

        case -8: 
        case -4: 
        default:
            return;
        }
    }

    public void g()
    {
        System.arraycopy(m, 0, t, 0, x * u);
        System.arraycopy(n, 0, bd, 0, x * u);
    }

    private final void h()
    {
        dc = new int[0];
        df = new short[0];
        cg = 0;
    }

    public boolean a(String s1, boolean flag)
    {
        if(s1 == null)
        {
            return flag;
        }
        if(s1.length() < 1)
        {
            return flag;
        }
        boolean flag1;
        if(s1.equalsIgnoreCase("YES"))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    public int a(String s1)
    {
        if(s1 == null)
        {
            return 0;
        }
        if(s1.length() < 1)
        {
            return 0;
        }
        int i1;
        try
        {
            i1 = Integer.parseInt(s1, 16);
        }
        catch(Exception _ex)
        {
            i1 = 0;
        }
        return i1;
    }

    public int a(Color color)
    {
        int i1 = 0xff000000 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
        return i1;
    }

    public int a(String s1, int i1)
    {
        if(s1 == null)
        {
            return i1;
        }
        if(s1.length() < 1)
        {
            return i1;
        }
        int j1;
        try
        {
            j1 = Integer.parseInt(s1);
        }
        catch(Exception _ex)
        {
            j1 = i1;
        }
        return j1;
    }

    private void a(String as[], int i1, int j1, int k1)
    {
        boolean aflag[] = dh[i1];
        int ai[] = dg[i1];
        for(int i2 = 0; i2 < k1; i2++)
        {
            for(int l1 = 0; l1 < j1; l1++)
            {
                int j2 = as[i2].charAt(l1) - 48;
                int k2 = i2 * j1 + l1;
                if(j2 > -1)
                {
                    ai[k2] = dj[j2];
                    aflag[k2] = true;
                } else
                {
                    aflag[k2] = false;
                }
            }

        }

    }

    public void a(int ai[], int i1, int j1, int k1, int l1, int ai1[], int i2, 
            int j2, int k2, int l2)
    {
        int i3 = k1;
        int j3 = l1;
        if(i2 < 0)
        {
            i1 -= i2;
            i3 += i2;
            i2 = 0;
        }
        if(j2 < 0)
        {
            j1 -= j2;
            j3 += j2;
            j2 = 0;
        }
        if(i2 + i3 > k2)
        {
            i3 = k2 - i2;
        }
        if(j2 + j3 > l2)
        {
            j3 = l2 - j2;
        }
        for(int k3 = 0; k3 < j3; k3++)
        {
            int l3 = (j1 + k3) * k1 + i1;
            int i4 = (j2 + k3) * k2 + i2;
            System.arraycopy(ai, l3, ai1, i4, i3);
        }

    }

    final void a(int i1, int j1, int k1, int l1)
    {
        int k2 = 0;
        int l2 = 0;
        int i2 = bn;
        int j2 = bm;
        int i3 = i1;
        int j3 = j1;
        if(i3 < 0)
        {
            k2 = -i3;
            i2 += i3;
            i3 = 0;
        }
        if(j3 < 0)
        {
            l2 = -j3;
            j2 += j3;
            j3 = 0;
        }
        if(i3 + i2 > x - 1)
        {
            i2 = x - 1 - i3;
        }
        if(j3 + j2 > u - 1)
        {
            j2 = u - 1 - j3;
        }
        if((i2 < 0) | (j2 < 0))
        {
            return;
        }
        b(dg[k1], dh[k1], k2, l2, bn, t, i3, j3, x, i2, j2);
        if(l1 != -1)
        {
            a(bd, i3, j3, x, i2, j2, l1);
        }
    }

    private final void a(int ai[], int i1, int j1, int k1, int ai1[], int l1, int i2, 
            int j2, int k2, int l2)
    {
        int j3 = j1 * k1 + i1;
        int k3 = i2 * j2 + l1;
        for(int i3 = 0; i3 < l2; i3++)
        {
            System.arraycopy(ai, j3, ai1, k3, k2);
            j3 += k1;
            k3 += j2;
        }

    }

    private final void a(int ai[], boolean aflag[], int i1, int j1, int k1, int ai1[], int l1, 
            int i2, int j2, int k2, int l2)
    {
        boolean flag = false;
        int k3 = j1 * k1 + i1;
        int l3 = i2 * j2 + l1;
        if((k2 < 1) | (l2 < 1))
        {
            return;
        }
        int l4 = j & 0xff0000;
        int i5 = j & 0xff00;
        int j5 = j & 0xff;
        for(int i3 = 0; i3 < l2; i3++)
        {
            for(int j3 = 0; j3 < k2; j3++)
            {
                if(aflag[k3 + j3])
                {
                    ai1[l3 + j3] = R;
                } else
                {
                    int i4 = (ai1[l3 + j3] & 0xff0000) + l4 >> 1 & 0xff0000;
                    int j4 = (ai1[l3 + j3] & 0xff00) + i5 >> 1 & 0xff00;
                    int k4 = (ai1[l3 + j3] & 0xff) + j5 >> 1 & 0xff;
                    ai1[l3 + j3] = 0xff000000 | i4 | j4 | k4;
                }
            }

            k3 += k1;
            l3 += j2;
        }

    }

    private final void b(int ai[], boolean aflag[], int i1, int j1, int k1, int ai1[], int l1, 
            int i2, int j2, int k2, int l2)
    {
        int k3 = j1 * k1 + i1;
        int l3 = i2 * j2 + l1;
        for(int i3 = 0; i3 < l2; i3++)
        {
            for(int j3 = 0; j3 < k2; j3++)
            {
                int i4 = k3 + j3;
                if(aflag[i4])
                {
                    ai1[l3 + j3] = ai[i4];
                }
            }

            k3 += k1;
            l3 += j2;
        }

    }

    private final void a(int ai[], int i1, int j1, int k1, int l1, int i2, int j2)
    {
        int i3 = j1 * k1;
        for(int l2 = 0; l2 < i2; l2++)
        {
            int j3 = i3 + i1;
            for(int k2 = 0; k2 < l1; k2++)
            {
                ai[j3++] = j2;
            }

            i3 += k1;
        }

    }

    final void a(int i1, int j1, int k1)
    {
        int j2 = k1;
        int k2 = 0;
        int l2 = 0;
        int l1 = Y[k1];
        int i2 = X[k1];
        int i3 = i1;
        int j3 = j1;
        if(i3 < 0)
        {
            k2 = -i3;
            l1 += i3;
            i3 = 0;
        }
        if(j3 < 0)
        {
            l2 = -j3;
            i2 += j3;
            j3 = 0;
        }
        if(i3 + l1 > x - 1)
        {
            l1 = x - 1 - i3;
        }
        if(j3 + i2 > u - 1)
        {
            i2 = u - 1 - j3;
        }
        if((l1 < 0) | (i2 < 0))
        {
            return;
        }
        if(G != k1)
        {
            b(W[k1], bg[k1], k2, l2, Y[k1], t, i3, j3, x, l1, i2);
        } else
        {
            a(W[k1], bg[k1], k2, l2, Y[k1], t, i3, j3, x, l1, i2);
        }
        a(bd, i3, j3, x, l1, i2, j2);
    }

    final void a(int i1, int j1, int k1, int l1, int i2)
    {
        byte byte0 = 0;
        if(i2 == 1)
        {
            byte0 = 16;
        }
        int l2 = 0;
        int i3 = 0;
        int j2 = bm;
        int k2 = bm;
        int j3 = i1;
        int k3 = j1;
        if(j3 < 0)
        {
            l2 = -j3;
            j2 += j3;
            j3 = 0;
        }
        if(k3 < 0)
        {
            i3 = -k3;
            k2 += k3;
            k3 = 0;
        }
        if(j3 + j2 > x - 1)
        {
            j2 = x - 1 - j3;
        }
        if(k3 + k2 > u - 1)
        {
            k2 = u - 1 - k3;
        }
        if((j2 < 0) | (k2 < 0))
        {
            return;
        }
        b(bb[k1], bc[k1], l2 + byte0, i3, 32, t, j3, k3, x, j2, k2);
        if(l1 != -1)
        {
            a(bd, j3, k3, x, j2, k2, l1);
        }
    }

    final void b(int i1, int j1, int k1, int l1)
    {
        int k2 = 0;
        int l2 = 0;
        int i2 = bm;
        int j2 = bm;
        int i3 = i1;
        int j3 = j1;
        if(i3 < 0)
        {
            k2 = -i3;
            i2 += i3;
            i3 = 0;
        }
        if(j3 < 0)
        {
            l2 = -j3;
            j2 += j3;
            j3 = 0;
        }
        if(i3 + i2 > x - 1)
        {
            i2 = x - 1 - i3;
        }
        if(j3 + j2 > u - 1)
        {
            j2 = u - 1 - j3;
        }
        if((i2 < 0) | (j2 < 0))
        {
            return;
        }
        b(bb[k1], bc[k1], k2, l2, bm, t, i3, j3, x, i2, j2);
        if(l1 != -1)
        {
            a(bd, i3, j3, x, i2, j2, l1);
        }
    }

    public void i()
    {
        if(k == null)
        {
            k = "";
        }
        if(k.length() > 2)
        {
            Image image = b(k);
            int i1 = 0;
            int j1 = 0;
            i1 = image.getWidth(this);
            j1 = image.getHeight(this);
            a(0, 0, i1, j1, image);
            int i2 = x / i1 + 2;
            int j2 = u / j1 + 2;
            int k2 = -(i2 * i1 - x) / 2;
            int l2 = -(j2 * j1 - u) / 2;
            for(int k1 = 0; k1 < i2; k1++)
            {
                for(int l1 = 0; l1 < j2; l1++)
                {
                    a(l, 0, 0, i1, j1, m, k2 + k1 * i1, l2 + l1 * j1, x, u);
                }

            }

            return;
        } else
        {
            a(m, 0, 0, x, x, u, h);
            return;
        }
    }

    public void j()
    {
        int i1 = 0;
        int j1 = 0;
        try
        {
            for(i1 = 1; i1 < cd; i1++)
            {
                j1 = bj[i1];
                a(j1, i1);
            }

            return;
        }
        catch(ArrayIndexOutOfBoundsException _ex)
        {
            showStatus("Error reading configuration ; itemId: " + i1 + " parentId : " + j1);
        }
        stop();
    }

    private final void k()
    {
        H = 0;
        I = 0;
        System.arraycopy(t, 0, w, 0, x * u);
        D = B;
        A = y;
        cf = cg;
        ct = new int[cf];
        cv = new short[cf];
        cu = new boolean[cf];
        for(int j1 = 0; j1 < cf; j1++)
        {
            ct[j1] = dc[j1];
            cv[j1] = df[j1];
            cu[j1] = dd[j1];
        }

        h();
        int i1 = 0;
        cn = bF;
        bF = 1;
        a(i1, H, true);
        if(bi[i1])
        {
            a(i1);
        }
        de = 0;
    }

    private void l()
    {
        dg = new int[13][];
        dh = new boolean[13][];
        for(int i1 = 0; i1 < 13; i1++)
        {
            dg[i1] = new int[bn * bm];
            dh[i1] = new boolean[bn * bm];
        }

        int j1 = cR * cR;
        dg[0] = new int[j1];
        dg[1] = new int[j1];
        dg[2] = new int[j1];
        dg[3] = new int[j1];
        int k1 = cR - 1;
        a(dg[0], 0, 0, k1, k1);
        a(dg[1], 0, 0, k1, k1);
        a(dg[2], 0, 0, k1, k1);
        a(dg[3], 0, 0, k1, k1);
        String as[] = new String[cR];
        int l1 = 0;
        l1 = 0;
        if(cR == 11)
        {
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "....00.....";
            as[l1++] = "...0000....";
            as[l1++] = "..000000...";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
        } else
        {
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = ".......0........";
            as[l1++] = "......000.......";
            as[l1++] = ".....00000......";
            as[l1++] = "....0000000.....";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
        }
        a(as, 0, cR, cR);
        l1 = 0;
        if(cR == 11)
        {
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "..000000...";
            as[l1++] = "...0000....";
            as[l1++] = "....00.....";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
        } else
        {
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "....0000000.....";
            as[l1++] = ".....00000......";
            as[l1++] = "......000.......";
            as[l1++] = ".......0........";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
        }
        a(as, 1, cR, cR);
        l1 = 0;
        if(cR == 11)
        {
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = ".....0.....";
            as[l1++] = "....00.....";
            as[l1++] = "...000.....";
            as[l1++] = "...000.....";
            as[l1++] = "....00.....";
            as[l1++] = ".....0.....";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
        } else
        {
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "........0.......";
            as[l1++] = ".......00.......";
            as[l1++] = "......000.......";
            as[l1++] = ".....0000.......";
            as[l1++] = "......000.......";
            as[l1++] = ".......00.......";
            as[l1++] = "........0.......";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
        }
        a(as, 2, cR, cR);
        l1 = 0;
        if(cR == 11)
        {
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "....0......";
            as[l1++] = "....00.....";
            as[l1++] = "....000....";
            as[l1++] = "....000....";
            as[l1++] = "....00.....";
            as[l1++] = "....0......";
            as[l1++] = "...........";
            as[l1++] = "...........";
            as[l1++] = "...........";
        } else
        {
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "......0.........";
            as[l1++] = "......00........";
            as[l1++] = "......000.......";
            as[l1++] = "......0000......";
            as[l1++] = "......000.......";
            as[l1++] = "......00........";
            as[l1++] = "......0.........";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
            as[l1++] = "................";
        }
        a(as, 3, cR, cR);
        int k2 = bn * bm;
        for(int j2 = 6; j2 < 13; j2++)
        {
            for(int i2 = 0; i2 < k2; i2++)
            {
                dh[j2][i2] = false;
            }

        }

        as = new String[bm];
        as[0] = "........3..........";
        as[1] = "...................";
        as[2] = "........3..........";
        as[3] = "...................";
        as[4] = "........3..........";
        as[5] = "...................";
        as[6] = "........3..........";
        as[7] = "...................";
        as[8] = "........3..........";
        as[9] = "...................";
        as[10] = "........3..........";
        as[11] = "...................";
        as[12] = "........3..........";
        as[13] = "...................";
        as[14] = "........3..........";
        as[15] = "...................";
        a(as, 6, bn, bm);
        as[0] = "........3..........";
        as[1] = "...................";
        as[2] = "........3..........";
        as[3] = "...................";
        as[4] = "........3..........";
        as[5] = "...................";
        as[6] = "........3..........";
        as[7] = "...................";
        as[8] = "........3.3.3.3.3..";
        as[9] = "...................";
        as[10] = "........3..........";
        as[11] = "...................";
        as[12] = "........3..........";
        as[13] = "...................";
        as[14] = "........3..........";
        as[15] = "...................";
        a(as, 7, bn, bm);
        as[0] = "........3..........";
        as[1] = "...................";
        as[2] = "........3..........";
        as[3] = "...................";
        as[4] = "........3..........";
        as[5] = "...................";
        as[6] = "........3..........";
        as[7] = "...................";
        as[8] = "........3.3.3.3.3..";
        as[9] = "...................";
        as[10] = "...................";
        as[11] = "...................";
        as[12] = "...................";
        as[13] = "...................";
        as[14] = "...................";
        as[15] = "...................";
        a(as, 8, bn, bm);
        as[0] = "........3..........";
        as[1] = "...................";
        as[2] = "........3..........";
        as[3] = "...................";
        as[4] = "....333333333......";
        as[5] = "....3.......3......";
        as[6] = "....3...0...3......";
        as[7] = "....3...0...3......";
        as[8] = "....3.00000.3.3.3.3";
        as[9] = "....3...0...3......";
        as[10] = "....3...0...3......";
        as[11] = "....3.......3......";
        as[12] = "....333333333......";
        as[13] = "...................";
        as[14] = "........3..........";
        as[15] = "...................";
        a(as, 9, bn, bm);
        as[0] = "........3..........";
        as[1] = "...................";
        as[2] = "........3..........";
        as[3] = "...................";
        as[4] = "....333333333......";
        as[5] = "....3.......3......";
        as[6] = "....3...0...3......";
        as[7] = "....3...0...3......";
        as[8] = "....3.00000.3.3.3.3";
        as[9] = "....3...0...3......";
        as[10] = "....3...0...3......";
        as[11] = "....3.......3......";
        as[12] = "....333333333......";
        as[13] = "...................";
        as[14] = "...................";
        as[15] = "...................";
        a(as, 10, bn, bm);
        as[0] = "........3..........";
        as[1] = "...................";
        as[2] = "........3..........";
        as[3] = "...................";
        as[4] = "....333333333......";
        as[5] = "....3.......3......";
        as[6] = "....3.......3......";
        as[7] = "....3.......3......";
        as[8] = "....3.00000.3.3.3.3";
        as[9] = "....3.......3......";
        as[10] = "....3.......3......";
        as[11] = "....3.......3......";
        as[12] = "....333333333......";
        as[13] = "...................";
        as[14] = "........3..........";
        as[15] = "...................";
        a(as, 11, bn, bm);
        as[0] = "........3..........";
        as[1] = "...................";
        as[2] = "........3..........";
        as[3] = "...................";
        as[4] = "....333333333......";
        as[5] = "....3.......3......";
        as[6] = "....3.......3......";
        as[7] = "....3.......3......";
        as[8] = "....3.00000.3.3.3.3";
        as[9] = "....3.......3......";
        as[10] = "....3.......3......";
        as[11] = "....3.......3......";
        as[12] = "....333333333......";
        as[13] = "...................";
        as[14] = "...................";
        as[15] = "...................";
        a(as, 12, bn, bm);
    }

    final void m()
    {
        int j1 = 20;
        int i2 = bm;
        Image image = createImage(j1, i2);
        Graphics g1 = image.getGraphics();
        g1.setFont(bI);
        FontMetrics fontmetrics = g1.getFontMetrics();
        j1 = 0;
        for(int k1 = 0; k1 < cd; k1++)
        {
            int j2 = fontmetrics.stringWidth(bp[k1]) + 4;
            if(j2 > j1)
            {
                j1 = j2;
            }
        }

        for(int l1 = 0; l1 < cd; l1++)
        {
            int i1 = l1;
            image = createImage(j1, i2);
            g1 = image.getGraphics();
            g1.setFont(bI);
            FontMetrics fontmetrics1 = g1.getFontMetrics();
            dn = fontmetrics1.getAscent() + (bm - bo) / 2;
            g1.drawString(bp[i1], 2, dn);
            Y[i1] = fontmetrics1.stringWidth(bp[i1]) + 4;
            X[i1] = i2;
            if(Y[i1] > j1)
            {
                Y[i1] = j1 + 4;
            }
            a(0, 0, image, i1);
            bg[i1] = new boolean[Y[i1] * X[i1]];
            boolean aflag[] = bg[i1];
            int ai[] = W[i1];
            int k2 = Y[i1] * X[i1];
            for(int l2 = 0; l2 < k2; l2++)
            {
                aflag[l2] = true;
            }

            int i3 = ai[0];
            for(int k3 = 0; k3 < i2; k3++)
            {
                int i4 = k3 * Y[i1];
                for(int j3 = 0; j3 < Y[i1]; j3++)
                {
                    int j4 = j3 + i4;
                    int l3 = ai[j4];
                    if((l3 == i3) | ((l3 & 0xff000000) == 0))
                    {
                        aflag[j4] = false;
                    } else
                    {
                        ai[j4] = P;
                    }
                }

            }

        }

        g1 = null;
        image = null;
    }

    private void n()
    {
        float f1 = (float)s / 100F;
        a = (float)r / 100F;
        dq = new float[dw];
        dq[0] = 1.0F;
        for(int i1 = 1; i1 < dw; i1++)
        {
            dq[i1] = dq[i1 - 1] * f1;
        }

        dp = new float[dv];
        dp[0] = 1.0F;
        for(int j1 = 1; j1 < dv; j1++)
        {
            dp[j1] = dp[j1 - 1] * f1;
        }

    }

    private void o()
    {
        dx = 1000 / cZ;
        dA = new float[dx * 2];
        dy = 1000 / da;
        dB = new float[dy * 2];
        double d1 = 1.5707963267948966D / (double)dx;
        for(int i1 = 0; i1 < dx; i1++)
        {
            dA[i1] = (float)Math.sin((double)i1 * d1 + 1.5707963267948966D);
        }

        d1 = 1.5707963267948966D / (double)dy;
        for(int j1 = 0; j1 < dy; j1++)
        {
            dB[j1] = (float)Math.sin((double)j1 * d1 + 1.5707963267948966D);
        }

        for(int k1 = 0; k1 < dx; k1++)
        {
            dA[dx + k1] = dA[dx - k1];
        }

        for(int l1 = 0; l1 < dy; l1++)
        {
            dB[dy + l1] = dB[dy - l1];
        }

    }

    private void p()
    {
        for(cb = 0; getParameter("Icon" + cb) != null; cb++) { }
        bb = new int[cb][];
        bc = new boolean[cb][];
        bk = new boolean[cb];
        for(int i1 = 0; i1 < cb; i1++)
        {
            String s1 = getParameter("Icon" + i1);
            Image image = b(s1);
            int j1 = 0;
            int k1 = 0;
            j1 = image.getWidth(this);
            k1 = image.getHeight(this);
            if((j1 != 32) & (j1 != 16) | (k1 != 16))
            {
                showStatus("Size of Icon" + i1 + " is incorrect." + j1 + "x" + k1);
                stop();
            }
            if(j1 == 32)
            {
                bk[i1] = true;
            } else
            {
                bk[i1] = false;
            }
            int l1 = j1 * k1;
            bb[i1] = new int[l1];
            bc[i1] = new boolean[l1];
            a(0, 0, j1, k1, image);
            boolean aflag[] = new boolean[l1];
            boolean flag = false;
            for(int j2 = 0; j2 < k1; j2++)
            {
                int k2 = j2 * j1;
                for(int i2 = 0; i2 < j1; i2++)
                {
                    int l2 = i2 + k2;
                    int i3 = l[l2];
                    if((i3 == N) | ((i3 & 0xff000000) == 0))
                    {
                        l[l2] = 0;
                        aflag[l2] = false;
                    } else
                    {
                        aflag[l2] = true;
                    }
                }

            }

            System.arraycopy(l, 0, bb[i1], 0, l1);
            System.arraycopy(aflag, 0, bc[i1], 0, l1);
        }

    }

    public void destroy()
    {
        cx = null;
        Z = null;
        if(cj != null)
        {
            cj.dispose();
        }
        cj = null;
        System.gc();
    }

    private void q()
    {
        bj = new int[bl];
        bf = new int[bl];
        bp = new String[bl];
        bq = new String[bl];
        ba = new int[bl][];
        bh = new int[bl];
        W = new int[bl][];
        bg = new boolean[bl][];
        Y = new int[bl];
        X = new int[bl];
        bi = new boolean[bl];
    }

    private void a(int ai[], int i1, int j1, int k1, int l1)
    {
        int i2 = dj[3];
        a(ai, i1, j1, cR, k1 - i1, 1, i2);
        a(ai, i1, j1, cR, 1, l1 - j1, i2);
        i2 = dj[4];
        a(ai, i1 + 1, j1 + 1, cR, k1 - 1 - (i1 + 1), 1, i2);
        a(ai, i1 + 1, j1 + 1, cR, 1, l1 - 1 - (j1 + 1), i2);
        i2 = dj[2];
        a(ai, i1 + 2, j1 + 2, cR, k1 - 1 - (i1 + 1), l1 - 1 - (j1 + 2), i2);
        i2 = dj[1];
        a(ai, i1 + 1, l1 - 1, cR, k1 - (i1 + 1), 1, i2);
        a(ai, k1 - 1, j1 + 1, cR, 1, l1 - j1 - 1, i2);
        i2 = dj[0];
        a(ai, i1, l1, cR, (k1 - i1) + 1, 1, i2);
        a(ai, k1, j1, cR, 1, l1 - j1, i2);
    }

    private final void r()
    {
        int ai[] = {
            dj[2], dj[4]
        };
        int i2 = 0;
        int j2 = cK;
        int k1 = cJ;
        int l1 = cJ + cR;
        for(int j1 = k1; j1 < l1; j1++)
        {
            int k2 = j1 * x;
            for(int i1 = i2; i1 < j2; i1++)
            {
                t[k2 + i1] = ai[i1 + j1 & 1];
                bd[k2 + i1] = -3;
            }

        }

        a(dg[2], 0, 0, cR, t, i2, k1, x, cR, l1 - k1);
        a(bd, i2, k1, x, cR, cR, -2);
        a(dg[3], 0, 0, cR, t, j2 - cR, k1, x, cR, cR);
        a(bd, j2 - cR, k1, x, cR, cR, -5);
        int l2 = (int)((double)(cK * (cK - cR * 2)) / (double)cF);
        if(l2 < 6)
        {
            l2 = 6;
        }
        cM = l2;
        cP = (int)((double)(-y * (cK - cR * 2)) / (double)cF);
        int i3 = cR + cP;
        int j3 = l1 - cR;
        int k3 = cR + cP + l2;
        int l3 = l1 - 1;
        c(i3, j3, k3, l3);
        a(bd, i3, j3, x, k3 - i3, l3 - j3, -4);
    }

    private void c(int i1, int j1, int k1, int l1)
    {
        int i2 = dj[3];
        a(t, i1, j1, x, k1 - i1, 1, i2);
        a(t, i1, j1, x, 1, l1 - j1, i2);
        i2 = dj[4];
        a(t, i1 + 1, j1 + 1, x, k1 - 1 - (i1 + 1), 1, i2);
        a(t, i1 + 1, j1 + 1, x, 1, l1 - 1 - (j1 + 1), i2);
        i2 = dj[2];
        a(t, i1 + 2, j1 + 2, x, k1 - 1 - (i1 + 1), l1 - 1 - (j1 + 2), i2);
        i2 = dj[1];
        a(t, i1 + 1, l1 - 1, x, k1 - (i1 + 1), 1, i2);
        a(t, k1 - 1, j1 + 1, x, 1, l1 - j1 - 1, i2);
        i2 = 0xff000000;
        a(t, i1, l1, x, (k1 - i1) + 1, 1, i2);
        a(t, k1, j1, x, 1, l1 - j1, i2);
    }

    private final void s()
    {
        a(t, cK, cJ, x, cR, cR, dj[2]);
    }

    private void t()
    {
        int ai[] = {
            dj[2], dj[4]
        };
        int i2 = cK;
        int j2 = cK + cR;
        int k1 = 0;
        int l1 = cJ;
        for(int j1 = k1; j1 < l1; j1++)
        {
            int k2 = j1 * x;
            for(int i1 = i2; i1 < j2; i1++)
            {
                t[k2 + i1] = ai[i1 + j1 & 1];
                bd[k2 + i1] = -7;
            }

        }

        a(dg[0], 0, 0, cR, t, i2, k1, x, cR, cR);
        a(bd, i2, k1, x, cR, cR, -6);
        a(dg[1], 0, 0, cR, t, i2, l1 - cR, x, cR, cR);
        a(bd, i2, l1 - cR, x, cR, cR, -9);
        int l2 = (int)((float)(cJ * (cJ - cR * 2)) / (float)cE);
        if(l2 < 6)
        {
            l2 = 6;
        }
        cU = l2;
        cX = (int)((float)(-B * (cJ - cR * 2)) / (float)cE);
        int i3 = i2;
        int j3 = cR + cX;
        int k3 = j2 - 1;
        int l3 = cR + cX + l2;
        c(i3, j3, k3, l3);
        a(bd, i3, j3, x, k3 - i3, l3 - j3, -8);
    }

    Image b(String s1)
    {
        try
        {
            return c(s1);
        }
        catch(NoSuchMethodError _ex)
        {
            return c(s1);
        }
    }

    synchronized Image c(String s1)
    {
        URL url = null;
        Image image = null;
        try
        {
            url = new URL(getDocumentBase(), s1);
        }
        catch(MalformedURLException _ex) { }
        try
        {
            try
            {
                InputStream inputstream = getClass().getResourceAsStream(url.toString());
                if(inputstream != null)
                {
                    ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(1024);
                    byte abyte0[] = new byte[512];
                    boolean flag = false;
                    byte abyte1[] = null;
                    try
                    {
                        while(!flag) 
                        {
                            int k1 = inputstream.read(abyte0, 0, 512);
                            if(k1 != -1)
                            {
                                bytearrayoutputstream.write(abyte0, 0, k1);
                                bytearrayoutputstream.flush();
                            } else
                            {
                                flag = true;
                            }
                        }
                        abyte1 = bytearrayoutputstream.toByteArray();
                        bytearrayoutputstream.close();
                        inputstream.close();
                    }
                    catch(IOException _ex)
                    {
                        abyte1 = null;
                    }
                    System.gc();
                    if(abyte1 != null)
                    {
                        image = getToolkit().createImage(abyte1);
                        prepareImage(image, this);
                    }
                }
            }
            catch(NoSuchMethodError _ex) { }
        }
        catch(SecurityException _ex) { }
        if(image == null)
        {
            for(int i1 = 0; i1 < 5;)
            {
                try
                {
                    if(i1 % 2 == 0)
                    {
                        image = Toolkit.getDefaultToolkit().getImage(url);
                    } else
                    {
                        image = getImage(url);
                    }
                    i1++;
                    MediaTracker mediatracker = new MediaTracker(this);
                    notifyAll();
                    Thread.currentThread();
                    Thread.yield();
                    try
                    {
                        mediatracker.addImage(image, 0);
                        mediatracker.waitForID(0);
                    }
                    catch(InterruptedException _ex)
                    {
                        image = null;
                    }
                    if(mediatracker.isErrorID(0))
                    {
                        image = null;
                    } else
                    {
                        i1 = 6;
                    }
                }
                catch(NullPointerException _ex)
                {
                    System.gc();
                }
            }

        }
        if(image == null)
        {
            for(int j1 = 0; j1 < 25; j1++)
            {
                showStatus("Image " + s1 + " not found!");
                try
                {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch(InterruptedException _ex) { }
            }

        } else
        {
            while(image.getWidth(this) < 0) 
            {
                notifyAll();
                Thread.currentThread();
                Thread.yield();
                try
                {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch(InterruptedException _ex) { }
            }
        }
        return image;
    }

    public void a(int i1, int j1, int k1, int l1, Image image)
    {
        l = new int[k1 * l1];
        PixelGrabber pixelgrabber = new PixelGrabber(image, i1, j1, k1, l1, l, 0, k1);
        image.getSource().addConsumer(pixelgrabber);
        boolean flag = false;
        try
        {
            flag = pixelgrabber.grabPixels();
        }
        catch(InterruptedException _ex)
        {
            return;
        }
        if((pixelgrabber.status() & 0x80) != 0)
        {
            return;
        } else
        {
            return;
        }
    }

    public void a(int i1, int j1, Image image, int k1)
    {
        int l1 = Y[k1];
        int i2 = X[k1];
        W[k1] = new int[l1 * i2];
        bg[k1] = new boolean[l1 * i2];
        int ai[] = W[k1];
        PixelGrabber pixelgrabber = new PixelGrabber(image, i1, j1, l1, i2, ai, 0, l1);
        image.getSource().addConsumer(pixelgrabber);
        boolean flag = false;
        try
        {
            flag = pixelgrabber.grabPixels();
        }
        catch(InterruptedException _ex)
        {
            return;
        }
        if((pixelgrabber.status() & 0x80) != 0)
        {
            return;
        } else
        {
            return;
        }
    }

    public boolean imageUpdate(Image image, int i1, int j1, int k1, int l1, int i2)
    {
        if(image == cx)
        {
            if(i1 == 16)
            {
                cl = true;
            }
            return true;
        } else
        {
            return true;
        }
    }

    public void init()
    {
        setLayout(null);
        addNotify();
        dl = getToolkit();
        String s1 = null;
        s1 = getParameter("credits");
        if(s1 != null)
        {
            if(!s1.equals("Applet by Anfy Team (www.anfyjava.com)"))
            {
                b();
            }
        } else
        {
            b();
        }
        String s2 = null;
        String s3 = null;
        try
        {
            s2 = getDocumentBase().getProtocol();
        }
        catch(SecurityException _ex)
        {
            s2 = "file";
        }
        try
        {
            s3 = getDocumentBase().getHost();
        }
        catch(SecurityException _ex)
        {
            s3 = "";
        }
        if(s2.equalsIgnoreCase("file") || s3.length() == 0 || s3.equalsIgnoreCase("localhost") || s3.equals("127.0.0.1"))
        {
            cI = true;
        } else
        {
            if(s3.startsWith("www."))
            {
                s3 = s3.substring(4);
            }
            String s4 = null;
            s4 = getParameter("regcode");
            if(s4 != null && !s4.equals("NO") && s4.length() > 10)
            {
                int i1 = 1;
                try
                {
                    for(int j1 = 0; j1 < s4.length(); j1++)
                    {
                        if(s4.charAt(j1) == '+')
                        {
                            i1++;
                        }
                    }

                }
                catch(StringIndexOutOfBoundsException _ex) { }
                int ai[] = new int[i1];
                if(i1 == 1)
                {
                    ai[0] = s4.length();
                } else
                {
                    int l1 = 0;
                    try
                    {
                        for(int j2 = 0; j2 < s4.length(); j2++)
                        {
                            if(s4.charAt(j2) == '+')
                            {
                                ai[l1] = j2;
                                l1++;
                            }
                        }

                    }
                    catch(StringIndexOutOfBoundsException _ex) { }
                    ai[l1] = s4.length();
                }
                String as[] = new String[i1];
                int k2 = 0;
                for(int i3 = 0; i3 < i1; i3++)
                {
                    try
                    {
                        as[i3] = s4.substring(k2, ai[i3]);
                    }
                    catch(StringIndexOutOfBoundsException _ex) { }
                    k2 = ai[i3] + 1;
                }

                for(int j3 = 0; j3 < i1; j3++)
                {
                    int k3 = as[j3].length() - 8;
                    byte abyte0[] = new byte[k3];
                    byte abyte1[] = new byte[8];
                    as[j3].getBytes(0, k3, abyte0, 0);
                    as[j3].getBytes(k3, k3 + 8, abyte1, 0);
                    int i4 = k3 % 7;
                    int k4 = k3 % 3;
                    for(int i5 = 0; i5 < k3; i5++)
                    {
                        byte byte0 = abyte0[i5];
                        if(byte0 >= 48 && byte0 <= 57)
                        {
                            abyte0[i5] = d(byte0, i4, 48, 57);
                        } else
                        if(byte0 >= 65 && byte0 <= 90)
                        {
                            abyte0[i5] = d(byte0, i4, 65, 90);
                        } else
                        if(byte0 >= 97 && byte0 <= 122)
                        {
                            abyte0[i5] = d(byte0, i4, 97, 122);
                        } else
                        if(byte0 == 45)
                        {
                            abyte0[i5] = 46;
                        } else
                        if(byte0 == 46)
                        {
                            abyte0[i5] = 45;
                        }
                        if((i4 = i4 + k4) > 7)
                        {
                            i4 = 1;
                        }
                    }

                    int j5 = 0;
                    int k5 = 0;
                    for(int l5 = 0; l5 < 4; l5++)
                    {
                        abyte1[l5] -= 52;
                    }

                    for(int i6 = 4; i6 < 8; i6++)
                    {
                        abyte1[i6] -= 55;
                    }

                    for(int j6 = 0; j6 < k3; j6 += 2)
                    {
                        j5 = j5 + abyte0[j6];
                    }

                    for(int k6 = 1; k6 < k3; k6 += 2)
                    {
                        k5 += abyte0[k6];
                    }

                    String s9 = String.valueOf(j5);
                    String s10 = String.valueOf(k5);
                    for(; s9.length() < 4; s9 = "0" + s9) { }
                    for(; s10.length() < 4; s10 = "0" + s10) { }
                    byte abyte2[] = new byte[8];
                    s9.getBytes(0, 4, abyte2, 0);
                    s10.getBytes(0, 4, abyte2, 4);
                    String s11 = new String(abyte2, 0);
                    if(s11.equals(new String(abyte1, 0)))
                    {
                        String s12 = new String(abyte0, 0);
                        String s13 = null;
                        if(s12.startsWith("www."))
                        {
                            s13 = s12.substring(4);
                        } else
                        {
                            s13 = s12;
                        }
                        if(s3.equalsIgnoreCase(s13))
                        {
                            cI = true;
                        }
                    }
                }

            }
        }
        String s5 = getParameter("overimg");
        if(s5 != null && !s5.equalsIgnoreCase("NO"))
        {
            cx = b(s5);
            if(cx != null)
            {
                String s6 = getParameter("overimgX");
                if(s6 == null)
                {
                    s6 = "0";
                }
                cy = Integer.valueOf(s6).intValue();
                String s7 = getParameter("overimgY");
                if(s7 == null)
                {
                    s7 = "0";
                }
                cz = Integer.valueOf(s7).intValue();
            }
        }
        cG = size().width;
        cD = size().height;
        br = size().width;
        be = size().height;
        Z = createImage(br, be);
        cj = Z.getGraphics();
        E = true;
        di = new Color[5];
        dj = new int[5];
        Color color = new Color(a(getParameter("systemColor")));
        int k1 = color.getRed();
        int i2 = color.getGreen();
        int l2 = color.getBlue();
        di[0] = new Color(0, 0, 0);
        double d1 = 0.49803921568627452D;
        di[1] = new Color((int)((double)k1 * d1), (int)((double)i2 * d1), (int)((double)l2 * d1));
        d1 = 0.74901960784313726D;
        di[2] = new Color((int)((double)k1 * d1), (int)((double)i2 * d1), (int)((double)l2 * d1));
        d1 = 0.4823529411764706D;
        di[3] = new Color((int)((double)k1 * d1), (int)((double)i2 * d1), (int)((double)l2 * d1));
        d1 = 1.0D;
        di[4] = new Color((int)((double)k1 * d1), (int)((double)i2 * d1), (int)((double)l2 * d1));
        for(int l3 = 0; l3 < 5; l3++)
        {
            dj[l3] = a(di[l3]);
        }

        M = new Color(a(getParameter("filterColor")));
        g = new Color(a(getParameter("backgroundColor")));
        O = new Color(a(getParameter("foregroundColor")));
        i = new Color(a(getParameter("backgroundColorSelection")));
        Q = new Color(a(getParameter("foregroundColorSelection")));
        N = a(M);
        h = a(g);
        P = a(O);
        j = a(i);
        R = a(Q);
        e = a(getParameter("autoHighLight"), false);
        bH = a(getParameter("memdelay"), 1000);
        cA = a(getParameter("priority"), 3);
        if(cA < 1)
        {
            cA = 1;
        }
        bJ = a(getParameter("MinSYNC"), 10);
        cH = getParameter("regframename");
        if(cH == null)
        {
            cH = "_self";
        }
        String s8 = getParameter("regnewframe");
        if(s8.equalsIgnoreCase("YES"))
        {
            bW = true;
        }
        cZ = a(getParameter("scrollSpeedInGoing"), 128);
        da = a(getParameter("scrollSpeedOutGoing"), 128);
        dv = a(getParameter("numberOfBouncesInGoing"), 0) + 1;
        dw = a(getParameter("numberOfBouncesOutGoing"), 0) + 1;
        s = a(getParameter("bounceGravity"), 5);
        r = a(getParameter("bounceAmplitude"), 100);
        q = a(getParameter("BorderStyle"), 1);
        switch(q)
        {
        case 0: // '\0'
            p = 0;
            o = 0;
            break;

        case 1: // '\001'
            p = 1;
            o = 1;
            break;

        case 2: // '\002'
            p = 4;
            o = 4;
            break;
        }
        cS = a(getParameter("scrollBarStyle"), 1);
        if(cS == 0)
        {
            cR = 11;
        } else
        {
            cR = 16;
        }
        k = getParameter("backimage");
        if(bH < 0)
        {
            bH = 0;
        }
        if(cA > 10)
        {
            cA = 10;
        } else
        if(cA < 1)
        {
            cA = 1;
        }
        int j4 = 0;
        if(getParameter("treeBold").equalsIgnoreCase("YES"))
        {
            j4 = 1;
        }
        bo = 11;
        bI = new Font(getParameter("treeFont"), j4, bo);
        bm = 16;
        bn = 19;
        l();
        p();
        x();
        j();
        m();
        e();
        o();
        n();
        x = br - 2 * p;
        u = be - 2 * o;
        w = new int[x * u];
        t = new int[x * u];
        bd = new int[x * u];
        m = new int[x * u];
        n = new int[x * u];
        for(int l4 = 0; l4 < x * u; l4++)
        {
            n[l4] = -1;
        }

        i();
        try
        {
            u();
        }
        catch(NoSuchMethodError _ex)
        {
            u();
        }
        if(!cI)
        {
            Label label = new Label("Tree Menu applet by Anfy Team 1998.");
            dF = new Lware(getAppletContext(), label);
            dF.setTitle("Tree Menu Applet by Anfy Team");
            dF.hide();
        }
    }

    void u()
    {
        cC = new MemoryImageSource(x, u, new DirectColorModel(24, 0xff0000, 65280, 255), t, 0, x);
        String s1;
        try
        {
            s1 = System.getProperty("java.version");
        }
        catch(SecurityException _ex)
        {
            s1 = "unk";
        }
        if(!s1.startsWith("1.0"))
        {
            try
            {
                cC.setAnimated(true);
                cC.setFullBufferUpdates(true);
                v = createImage(cC);
                cC.newPixels();
                bs = true;
            }
            catch(NoSuchMethodError _ex)
            {
                bs = false;
            }
        }
        if(!bs)
        {
            cC = null;
            cB = new anfy(x, u, new DirectColorModel(24, 0xff0000, 65280, 255), t, 0, x);
            v = createImage(cB);
        }
    }

    private void b(int i1)
    {
        if(bq[i1] != null && bq[i1].length() > 1)
        {
            try
            {
                URL url = new URL(getDocumentBase(), bq[i1]);
                AppletContext appletcontext = getAppletContext();
                if(bW)
                {
                    appletcontext.showDocument(url, cH);
                    return;
                } else
                {
                    appletcontext.showDocument(url);
                    return;
                }
            }
            catch(MalformedURLException _ex)
            {
                return;
            }
        } else
        {
            return;
        }
    }

    public final synchronized boolean mouseDown(Event event, int i1, int j1)
    {
        if(!cI)
        {
            dF.show();
            dF.toFront();
            dF.requestFocus();
        } else
        {
            bP = true;
            bK = false;
            bL = -1;
            bN = -1;
            boolean flag = false;
            if(!((i1 < p) | (i1 > p + x) | (j1 < o) | (j1 > o + u)))
            {
                bL = i1 - p;
                bN = j1 - o;
                bM = bL;
                bO = bN;
                dz = true;
                boolean flag1 = true;
            }
        }
        return true;
    }

    public final boolean mouseDrag(Event event, int i1, int j1)
    {
        bQ = true;
        bP = true;
        bL = -1;
        bN = -1;
        if(!((i1 < p) | (i1 > p + x) | (j1 < o) | (j1 > o + u)))
        {
            bL = i1 - p;
            bN = j1 - o;
        }
        return true;
    }

    public final synchronized boolean mouseMove(Event event, int i1, int j1)
    {
        bR = i1;
        bS = j1;
        return true;
    }

    public final synchronized boolean mouseUp(Event event, int i1, int j1)
    {
        if(cI)
        {
            bP = false;
            bQ = false;
            db = -1;
            K = -1;
            bL = -1;
            bN = -1;
            if(!((i1 < p) | (i1 > p + x) | (j1 < o) | (j1 > o + u)))
            {
                bL = i1 - p;
                bN = j1 - o;
                bM = bL;
                bO = bN;
            }
        }
        return true;
    }

    public void paint(Graphics g1)
    {
        if(E)
        {
            if(cx != null)
            {
                v();
            }
            g1.drawImage(Z, 0, 0, this);
        }
    }

    public synchronized void v()
    {
        if(c)
        {
            notifyAll();
            while(!cl) 
            {
                Thread.yield();
                try
                {
                    Thread.sleep(8L);
                }
                catch(InterruptedException _ex) { }
            }
            cl = false;
        }
        cj.drawImage(cx, cy, cz, this);
    }

    public final void w()
    {
        try
        {
            if(bs)
            {
                cC.newPixels();
                return;
            } else
            {
                cB.startProduction(cB.getConsumer());
                return;
            }
        }
        catch(NoSuchMethodError _ex)
        {
            return;
        }
    }

    private void b(int i1, int j1, boolean flag)
    {
        int ai[];
        short aword0[];
        boolean aflag[];
        if(flag)
        {
            ai = dc;
            aword0 = df;
            aflag = dd;
        } else
        {
            ai = ct;
            aword0 = cv;
            aflag = cu;
        }
        int k2 = bF;
        if(cn > bF)
        {
            k2 = cn;
        }
        boolean aflag1[] = new boolean[k2];
        for(int k1 = 0; k1 < k2; k1++)
        {
            aflag1[k1] = false;
        }

        de = i1;
        for(int l1 = 1; l1 < i1; l1++)
        {
            short word0 = aword0[l1];
            if(!aflag[l1])
            {
                aflag1[word0 - 1] = false;
            } else
            {
                aflag1[word0 - 1] = true;
            }
        }

        for(; de < j1; de++)
        {
            int j2 = ai[de];
            short word1 = aword0[de];
            H = (short)(word1 * bn);
            if(de != 0)
            {
                if(!aflag[de])
                {
                    aflag1[word1 - 1] = false;
                    if(bh[j2] < 1)
                    {
                        a(y + (word1 - 1) * bn, I, 7, -1);
                    } else
                    if(bi[j2])
                    {
                        a(y + (word1 - 1) * bn, I, 11, j2);
                    } else
                    {
                        a(y + (word1 - 1) * bn, I, 9, j2);
                    }
                } else
                {
                    aflag1[word1 - 1] = true;
                    if(bh[j2] < 1)
                    {
                        a(y + (word1 - 1) * bn, I, 8, -1);
                    } else
                    if(bi[j2])
                    {
                        a(y + (word1 - 1) * bn, I, 12, j2);
                    } else
                    {
                        a(y + (word1 - 1) * bn, I, 10, j2);
                    }
                }
                for(int i2 = 0; i2 < word1 - 1; i2++)
                {
                    if(!aflag1[i2])
                    {
                        a(y + i2 * bn, I, 6, -1);
                    }
                }

            }
            if(bk[bf[j2]])
            {
                if(bi[j2] & (bh[j2] > 0))
                {
                    a(H + y, I, bf[j2], j2, 0);
                } else
                {
                    a(H + y, I, bf[j2], j2, 1);
                }
            } else
            {
                b(H + y, I, bf[j2], j2);
            }
            a(H + y + bn, I, j2);
            I += bm;
            if(I > u)
            {
                return;
            }
        }

    }

    private void x()
    {
        bl = 0;
        int i1 = 0;
        boolean flag = false;
        for(; getParameter("config" + i1) != null; i1++) { }
        bl = i1;
        q();
        for(int j1 = 0; j1 < bl; j1++)
        {
            int k1 = 0;
            String s1 = getParameter("config" + j1);
            int l1 = 0;
            for(int i2 = 0; k1 != -1; i2++)
            {
                k1 = s1.indexOf(',', l1);
                int j2 = k1;
                if(j2 == -1)
                {
                    j2 = s1.length();
                }
                String s2 = s1.substring(l1, j2);
                l1 = k1 + 1;
                switch(i2)
                {
                case 0: // '\0'
                    bj[j1] = a(s2, 0);
                    break;

                case 1: // '\001'
                    bp[j1] = s2;
                    break;

                case 2: // '\002'
                    bf[j1] = a(s2, 0);
                    break;

                case 3: // '\003'
                    bq[j1] = s2;
                    break;
                }
            }

        }

        cd = bl;
    }

    final void y()
    {
        int i1;
        int j1;
        if(ca != bU)
        {
            i1 = ca * bm;
            j1 = (bU + 1) * bm;
        } else
        {
            i1 = cw * bm;
            j1 = (cm + 1) * bm;
        }
        if(j1 > cJ - B)
        {
            if(j1 - i1 > cJ)
            {
                B = -i1;
                return;
            }
            B = -(j1 - cJ);
        }
    }

    public void run()
    {
        Thread thread = Thread.currentThread();
        thread.setPriority(cA);
        Graphics g1 = getGraphics();
        if(cx != null && !c)
        {
            c = c();
        }
        System.gc();
        J = System.currentTimeMillis();
        dl = getToolkit();
        int i1 = 0;
        cj.setColor(g);
        cj.fillRect(0, 0, br, be);
        z();
        repaint();
        if(cd > 0)
        {
            bi[0] = true;
        }
        while(dm != null) 
        {
            if(dE == -1)
            {
                f();
                k();
            }
            g();
            A();
            if(dE != 1)
            {
                try
                {
                    w();
                }
                catch(NoSuchMethodError _ex) { }
            }
            cj.drawImage(v, p, o, this);
            if(cx != null)
            {
                v();
            }
            cj.setColor(O);
            if(E)
            {
                g1.drawImage(Z, 0, 0, this);
            }
            if(++i1 == bH)
            {
                System.gc();
                i1 = 0;
            }
            D();
        }
    }

    byte d(int i1, int j1, int k1, int l1)
    {
        int i2 = i1 - j1;
        if(i2 >= k1)
        {
            return (byte)i2;
        } else
        {
            int j2 = k1 - i2 - 1;
            return (byte)(l1 - j2);
        }
    }

    private final void c(int i1)
    {
        switch(i1)
        {
        case -1: 
            y += bm;
            return;

        case 0: // '\0'
            if(bL < cP + bm)
            {
                y += cK;
                return;
            } else
            {
                y -= cK;
                return;
            }

        case 1: // '\001'
            y -= bm;
            return;
        }
    }

    private final synchronized void d(int i1)
    {
        switch(i1)
        {
        case -1: 
            B += bm;
            return;

        case 0: // '\0'
            if(bN < cX + bm)
            {
                B += cJ;
            } else
            {
                B -= cJ;
            }
            db = -1;
            return;

        case 1: // '\001'
            B -= bm;
            return;
        }
    }

    private void z()
    {
        if(q == 0)
        {
            return;
        }
        cj.setColor(di[4]);
        cj.drawLine(0, 0, br, 0);
        if(q == 2)
        {
            cj.setColor(di[2]);
            cj.drawLine(1, 1, br - 1, 1);
            cj.setColor(di[1]);
            cj.drawLine(2, 2, br - 2, 2);
            cj.setColor(di[0]);
            cj.drawLine(3, 3, br - 3, 3);
        }
        cj.setColor(di[3]);
        cj.drawLine(0, 0, 0, be);
        if(q == 2)
        {
            cj.setColor(di[4]);
            cj.drawLine(1, 1, 1, be - 1);
            cj.setColor(di[1]);
            cj.drawLine(2, 2, 2, be - 2);
            cj.setColor(di[0]);
            cj.drawLine(3, 3, 3, be - 3);
        }
        cj.setColor(di[1]);
        cj.drawLine(0, be - 1, br, be - 1);
        if(q == 2)
        {
            cj.setColor(di[2]);
            cj.drawLine(1, be - 2, br - 1, be - 2);
            cj.setColor(di[4]);
            cj.drawLine(2, be - 3, br - 2, be - 3);
            cj.setColor(di[3]);
            cj.drawLine(3, be - 4, br - 3, be - 4);
        }
        cj.setColor(di[1]);
        cj.drawLine(br - 1, 0, br - 1, be - 1);
        if(q == 2)
        {
            cj.setColor(di[2]);
            cj.drawLine(br - 2, 1, br - 2, be - 2);
            cj.setColor(di[4]);
            cj.drawLine(br - 3, 2, br - 3, be - 3);
            cj.setColor(di[3]);
            cj.drawLine(br - 4, 3, br - 4, be - 4);
        }
    }

    final synchronized void A()
    {
        boolean flag = false;
        cK = x;
        cJ = u;
        co = cE;
        if(dE < 1)
        {
            cE = cg * bm;
        } else
        {
            cE = C();
        }
        cF = 0;
        for(int i1 = 0; i1 < cg; i1++)
        {
            int j1 = Y[dc[i1]] + df[i1] * bn + bm;
            if(j1 > cF)
            {
                cF = j1;
            }
        }

        boolean flag1 = false;
        if(cF > cK)
        {
            cJ -= cR;
            flag1 = true;
        }
        if(cE > cJ)
        {
            cK -= cR;
        }
        if((!flag1) & (cF > cK))
        {
            cJ -= cR;
        }
        if(dE == -1)
        {
            d();
        } else
        {
            y();
        }
        if(B > 0)
        {
            B = 0;
        }
        if(B + cE < cJ)
        {
            B = cJ - cE;
        }
        if(cE < cJ)
        {
            B = 0;
        }
        if(y > 0)
        {
            y = 0;
        }
        if(y + cF < cK)
        {
            y = cK - cF;
        }
        if(cF < cK)
        {
            y = 0;
        }
        if(dE == -1)
        {
            C = B;
            z = y;
        }
        I = B;
        if(dE == -1)
        {
            b(0, cg, true);
        } else
        {
            B();
        }
        if(cF > cK)
        {
            r();
        }
        if(cE > cJ)
        {
            t();
        }
        if((cF > cK) & (cE > cJ))
        {
            s();
        }
    }

    public synchronized void start()
    {
        if(dm == null)
        {
            dm = new Thread(this);
            dm.start();
        }
    }

    public synchronized void stop()
    {
        if(dm != null && dm.isAlive())
        {
            dm.stop();
        }
        dm = null;
    }

    public treemenu()
    {
        bs = false;
        E = false;
        bB = false;
        br = 600;
        be = 600;
        bW = false;
        cR = 11;
        p = 4;
        o = 4;
        e = false;
        q = 1;
        cS = 1;
        c = false;
        cI = false;
        bV = false;
        cl = false;
        a = 1.0F;
        L = false;
        dz = false;
        bK = true;
        G = -1;
        K = -1;
        dE = -1;
        bR = -1;
        bS = -1;
        db = -1;
        bP = false;
        bQ = false;
    }

    public final void update(Graphics g1)
    {
        paint(g1);
    }

    private void B()
    {
        if(dE == 0)
        {
            dr = 1;
            _flddo = 0;
            du = cm - cw;
            dt = bU - ca;
            if(dt != 0)
            {
                dC = dt * bm;
                dD = -dC;
            } else
            {
                dC = du * bm;
                dD = dC;
            }
        }
        dE++;
        int i1;
        if(dt != 0)
        {
            i1 = ca;
            int j1 = bU;
            I = B + (i1 + 1) * bm + dD;
            b(i1 + 1, j1 + 1, true);
        } else
        {
            i1 = cw;
            int k1 = cm;
            I = (B + (i1 + 1) * bm + dD) - du * bm;
            b(i1 + 1, k1 + 1, false);
        }
        int l1 = B;
        int i2 = B + (i1 + 1) * bm;
        if(l1 < 0)
        {
            l1 = 0;
        }
        if(i2 >= u)
        {
            i2 = u - 1;
        }
        System.arraycopy(m, l1 * x, t, l1 * x, (i2 - l1) * x);
        I = B;
        b(0, i1 + 1, true);
        I += dt * bm + dD;
        b(bU + 1, cg, true);
        if(dt != 0)
        {
            dD = (int)(-dB[dr] * (float)dC * dq[_flddo] * a);
            dr++;
            if((dr >= dy - 1) & (ds < dy - 1))
            {
                _flddo++;
            }
            if(dr >= dy * 2 - 1)
            {
                dr = 0;
            }
            ds = dr;
            if(_flddo >= dw)
            {
                dE = -1;
            }
        } else
        {
            dD = (int)(dA[dr] * (float)dC * dp[_flddo] * a);
            dr++;
            if((dr >= dx - 1) & (ds < dx - 1))
            {
                _flddo++;
            }
            if(dr >= dx * 2 - 1)
            {
                dr = 0;
            }
            ds = dr;
            if(_flddo >= dv)
            {
                dE = -1;
            }
        }
        dr++;
        if(dE == -1)
        {
            dD = 0;
        }
    }

    private int C()
    {
        int i1;
        if(dE > 0)
        {
            i1 = cg * bm + dD;
        } else
        {
            i1 = co;
        }
        return i1;
    }

    public synchronized void D()
    {
        Thread.yield();
        dl.sync();
        long l1 = 10L - (System.currentTimeMillis() - J);
        if(l1 > 0L)
        {
            try
            {
                Thread.sleep(l1);
            }
            catch(InterruptedException _ex) { }
        } else
        {
            try
            {
                Thread.sleep(1L);
            }
            catch(InterruptedException _ex) { }
        }
        J = System.currentTimeMillis();
        try
        {
            Thread.sleep(bJ);
            return;
        }
        catch(InterruptedException _ex)
        {
            return;
        }
    }
}