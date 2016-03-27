package damagereporter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.beans.*;
import java.util.ArrayList;

/**
 * Данный класс описывает отчет о повреждениях транспортного средства, который можно сохранять и
 * загружать впоследствии. Механизм записи обеспечивает долговременное хранение.
 * <p/>
 * @version 1.21 2004-08-30
 * @author Cay Horstmann
 */
public class DamageReport
{
    public enum CarType
    {
        SEDAN,
        WAGON,
        SUV
    }
    private String rentalRecord;
    private CarType carType;
    private boolean removeMode;
    private ArrayList<Point2D> points = new ArrayList<>();
    private static final int MARK_SIZE = 5;

    static
    {   // Данный объект необходим, чтобы объявить свойство removeMode как временное.
        try
        {
            BeanInfo info = Introspector.getBeanInfo(DamageReport.class);
            for (PropertyDescriptor descriptor : info.getPropertyDescriptors())
            {
                if (descriptor.getName().equals("removeMode"))
                {
                    descriptor.setValue("transient", Boolean.TRUE);
                }
            }
        }
        catch (IntrospectionException e)
        {
            e.printStackTrace();
        }
    }
    
    public String getRentalRecord()
    {
        return rentalRecord;
    }
    
    public void setRentalRecord(String newValue)
    {
        rentalRecord = newValue;
    }

    // Данное свойство сохраняется автоматически.
    public void setCarType(CarType newValue)
    {
        carType = newValue;
    }

    public CarType getCarType()
    {
        return carType;
    }

    // Данное свойство объявлено как временное.
    public void setRemoveMode(boolean newValue)
    {
        removeMode = newValue;
    }

    public boolean getRemoveMode()
    {
        return removeMode;
    }

    public void click(Point2D p)
    {
        if (removeMode)
        {
            for (Point2D center : points)
            {
                Ellipse2D circle = new Ellipse2D.Double(
                    center.getX() - MARK_SIZE,
                    center.getY() - MARK_SIZE,
                    2 * MARK_SIZE,
                    2 * MARK_SIZE);
                if (circle.contains(center))
                {
                    points.remove(center);
                    return;
                }
            }
        }
        else
        {
            points.add(p);
        }
    }

    public void drawDamage(Graphics2D g2)
    {
        g2.setPaint(Color.RED);
        for (Point2D center : points)
        {
            Ellipse2D circle = new Ellipse2D.Double(
                center.getX() - MARK_SIZE,
                center.getY() - MARK_SIZE,
                2 * MARK_SIZE,
                2 * MARK_SIZE);
            g2.draw(circle);
        }
    }

    public void configureEncoder(XMLEncoder encoder)
    {
        // Данный фрагмент необходим для сохранения
        // объектов Point2D.Double
        encoder.setPersistenceDelegate(Point2D.Double.class, new DefaultPersistenceDelegate(
            new String[]
            {
                "x", "y"
            }));
        // Данный фрагмент необходим, потому что списочный массив
        // точек не представляется как свойство.
        encoder.setPersistenceDelegate(DamageReport.class, new DefaultPersistenceDelegate()
        {
            @Override
            protected void initialize(Class<?> type,
                Object oldInstance,
                Object newInstance,
                Encoder out)
            {
                super.initialize(type, oldInstance, newInstance, out);
                DamageReport r = (DamageReport) oldInstance;

                for (Point2D p : points)
                {
                    out.writeStatement(new Statement(oldInstance, "click", new Object[]
                        {
                            p
                        }));
                }
            }
        });
    }
}
