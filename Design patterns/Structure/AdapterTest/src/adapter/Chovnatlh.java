package adapter;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.08.12
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
public interface Chovnatlh
{
   String tlhapWa$DIchPong();
   String tlhapQavPong();
   String tlhapPatlh();
   String tlhapGhom();

   void cherWa$DIchPong(String chu$wa$DIchPong);
   void cherQavPong(String chu$QavPong);
   void cherPatlh(String chu$patlh);
   void cherGhom(String chu$ghom);
}
