package adapter;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.08.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class ChovnatlhImpl implements Chovnatlh
{
   public ChovnatlhImpl(String wa$DIchPong, String qavPong, String patlh, String ghom)
   {
      this.wa$DIchPong = wa$DIchPong;
      QavPong = qavPong;
      this.patlh = patlh;
      this.ghom = ghom;
   }

   public ChovnatlhImpl() { }

   @Override
   public String tlhapWa$DIchPong()
   {
      return wa$DIchPong;
   }

   @Override
   public String tlhapQavPong()
   {
      return QavPong;
   }

   @Override
   public String tlhapPatlh()
   {
      return patlh;
   }

   @Override
   public String tlhapGhom()
   {
      return ghom;
   }

   @Override
   public void cherWa$DIchPong(String chu$wa$DIchPong)
   {
      wa$DIchPong = chu$wa$DIchPong;
   }

   @Override
   public void cherQavPong(String chu$QavPong)
   {
      QavPong = chu$QavPong;
   }

   @Override
   public void cherPatlh(String chu$patlh)
   {
      patlh = chu$patlh;
   }

   @Override
   public void cherGhom(String chu$ghom)
   {
      ghom = chu$ghom;
   }

   @Override
   public String toString()
   {
      return "adapter.ChovnatlhImpl{" +
        "wa$DIchPong='" + wa$DIchPong + '\'' +
        ", QavPong='" + QavPong + '\'' +
        ", patlh='" + patlh + '\'' +
        ", ghom='" + ghom + '\'' +
        '}';
   }

   private String wa$DIchPong;
   private String QavPong;
   private String patlh;
   private String ghom;
}
