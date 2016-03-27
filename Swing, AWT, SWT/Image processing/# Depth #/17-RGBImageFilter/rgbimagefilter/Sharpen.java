package rgbimagefilter;

public class Sharpen extends Convolver {

    @SuppressWarnings("FinalPrivateMethod")
    private final int clamp(int c) {
        return (c > 255 ? 255 : (c < 0 ? 0 : c));
    }

    public void convolve() {
        int ro=0, go=0, bo=0;
        for (int y=1; y<height-1; y++) {
            for (int x=1; x<width-1; x++) {
                int rs = 0;
                int gs = 0;
                int bs = 0;

                for (int k=-1; k<=1; k++) {
                    for (int j=-1; j<=1; j++) {
                        int rgb = imgpixels[(y+k)*width+x+j];
                        int r = (rgb >> 16) & 0xff;
                        int g = (rgb >> 8) & 0xff;
                        int b = rgb & 0xff;
                        if (j == 0 && k == 0) {
                            ro = r;
                            go = g;
                            bo = b;
                        }
                        else {
                            rs += r;
                            gs += g;
                            bs += b;
                        }
                    }
                }

                rs >>= 3;
                gs >>= 3;
                bs >>= 3;
                newimgpixels[y*width+x] = (0xff000000 | clamp(ro+ro-rs) << 16 | clamp(go+go-gs) << 8 | clamp(bo+bo-bs));
            }
        }
    }

}