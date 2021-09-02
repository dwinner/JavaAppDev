package rgbimagefilter;

interface PlugInFilter {
    java.awt.Image filter(java.applet.Applet a, java.awt.Image in);
}