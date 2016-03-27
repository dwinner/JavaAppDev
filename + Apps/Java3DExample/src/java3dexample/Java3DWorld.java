package java3dexample;

import java.awt.*;
import java.util.logging.Logger;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.behaviors.mouse.*;


/**
 * Среда для отображения трехмерной графики, где создается сцена SimpleUniverse.
 * Пользователю предоставляется возможность управлять освещениям, движением
 * объектов и текстурами трехмерной сцены.
 * @author dwinner@inbox.ru
 */
public class Java3DWorld extends Canvas3D {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Java3DWorld.class.getName());
    
    private Appearance appearance;  // Внешний вид 3D-объекта
    private Light ambientLight; // Внешнее освещение сцены
    private Box shape;  // 3D-объект для манипулирования
    private Color3f lightColor; // Цвет для освещения
    private Light directionalLight;
    private Material material;  // Цвет 3D-объектов
    private SimpleUniverse simpleUniverse;  // Среда 3D-сцены
    private TextureLoader textureLoader;    // Текстура 3D-объекта
    
    // Содержит информацию о преобразовании
    private TransformGroup transformGroup;
    private String imageName;   // Имя файла текстуры
    
    // Конструктор Java3DWorld
    public Java3DWorld(String imageFileName) {
        super(SimpleUniverse.getPreferredConfiguration());
        
        imageName = imageFileName;
        
        // Создание SimpleUniverse (трехмерной графической среды)
        simpleUniverse = new SimpleUniverse(this);
        
        // Задание умалчиваемой точки наблюдаемой сцены
        ViewingPlatform viewPlatrorm = simpleUniverse.getViewingPlatform();
        viewPlatrorm.setNominalViewingTransform();
        
        // Создание трехмерной сцены
        BranchGroup branchGroup = createScene();
        
        // Подключение BranchGroup к SimpleUniverse
        simpleUniverse.addBranchGraph(branchGroup);
    }

    // Создание трехмерной сцены
    private BranchGroup createScene() {
        BranchGroup scene = new BranchGroup();
        
        // Инициализация объекта TransformGroup
        transformGroup = new TransformGroup();
        
        // Установка разрешения на запись для TransformGroup
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        // Добавление TransformGroup в BranchGroup
        scene.addChild(transformGroup);
        
        // Создание ограничивающей сферы BoundingSphere
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0f, 0.0f, 0.0f), 100.0);
        
        appearance = new Appearance();  // Создание оформления объекта
        material = new Material();  // Создание материала структуры
        appearance.setMaterial(material);
        
        String rgb = "RGB";
        
        // Загрузка текстуры для объекта сцены
        textureLoader = new TextureLoader(Java3DWorld.class.getResource(imageName), rgb, this);
        
        // Установка битов для использования текстуры
        textureLoader.getTexture().setCapability(Texture.ALLOW_ENABLE_WRITE);
        
        // Исходная структура отображаться не будет
        textureLoader.getTexture().setEnable(false);
        
        // Задание текстуры для объекта
        appearance.setTexture(textureLoader.getTexture());
        
        // Создание геометрии объекта
        Box shapeLocal = new Box(0.3f, 0.3f, 0.3f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, appearance);
        
        // Добавление геометрии в группу TransformGroup
        transformGroup.addChild(shapeLocal);
        
        // Инициализация внешнего освещения AmbientLight
        ambientLight = new AmbientLight();
        ambientLight.setInfluencingBounds(bounds);
        
        // Инициализация направленного освещения
        directionalLight = new DirectionalLight();
        
        lightColor = new Color3f(); // Инициализация цвета освещения
        
        // Установка начального цвета DirectionalLight
        directionalLight.setColor(lightColor);
        
        // Установка битов разрешения на изменение цвета Color и направления Direction
        directionalLight.setCapability(DirectionalLight.ALLOW_DIRECTION_WRITE);        
        directionalLight.setCapability(DirectionalLight.ALLOW_DIRECTION_READ);
        directionalLight.setCapability(DirectionalLight.ALLOW_COLOR_WRITE);
        directionalLight.setCapability(DirectionalLight.ALLOW_COLOR_READ);
        directionalLight.setInfluencingBounds(bounds);
        
        // Добавление узлов освещения в BranchGroup
        scene.addChild(ambientLight);
        scene.addChild(directionalLight);
        
        // Инициализация поведения для вращения
        MouseRotate rotateBehavior = new MouseRotate();
        rotateBehavior.setTransformGroup(transformGroup);
        rotateBehavior.setSchedulingBounds(bounds);
        
        // Инициализация поведения для перемещения
        MouseTranslate translateBehavior = new MouseTranslate();
        translateBehavior.setTransformGroup(transformGroup);
        translateBehavior.setSchedulingBounds(new BoundingBox(new Point3d(-1.0f, -1.0f, -1.0f), new Point3d(1.0f, 1.0f, 1.0f)));
        
        // Инициализация поведения для масштабирования
        MouseZoom scaleBehavior = new MouseZoom();
        scaleBehavior.setTransformGroup(transformGroup);
        scaleBehavior.setSchedulingBounds(bounds);
        
        // Добавление поведений в BranchGroup
        scene.addChild(scaleBehavior);
        scene.addChild(rotateBehavior);
        scene.addChild(translateBehavior);
        
        scene.compile();
        
        return scene;
    }
    
    // Изменение цвета освещения DirectionLight
    public void changeColor(Color color) {
        lightColor.set(color);
        directionalLight.setColor(lightColor);
    }
    
    // Наложение текстуры
    public void updateTexture(boolean textureValue) {
        textureLoader.getTexture().setEnable(textureValue);
    }
    
    // Изменение изображения текстуры
    public void setImageName(String imageFileName) {
        imageName = imageFileName;
    }
    
    // Получение имени файла изображения
    public String getImageName() {
        return imageName;
    }
    
    // Возврат предпочтительных размеров контейнера
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
    
    // Возврат минимального размера контейнера
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    
}