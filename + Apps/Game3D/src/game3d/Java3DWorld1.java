/*
 * Класс Java3DWorld1 - это трехмерная игра.
 * Цель игры - перевести красный маленький шар в нижнем правом углу в верхний
 * левый угол экрана, не допуская столкновений с "летающими" объектами.
 * Пользователь может изменить количество летающих объектов, чтобы увеличить
 * сложность игры.
 */
package game3d;

import java.awt.*;
import java.util.BitSet;

import java.util.logging.Logger;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import com.sun.j3d.utils.universe.*;

/**
 * @author dwinner@inbox.ru
 */
public class Java3DWorld1 extends Canvas3D {
    
    private static final Logger LOG = Logger.getLogger(Java3DWorld1.class.getName());
    
    // Размеры контейнера
    private static final int CONTAINER_WIDTH = 600;
    private static final int CONTAINER_HEIGHT = 600;
    
    // Константы, задающие количество фигур
    private static final int NUMBER_OF_SHAPES = 20;
    private static final int NUMBER_OF_PRIMITIVES = 4;
    
    // Начальная сцена для отображения
    private static final int DEFAULT_SCENE = 0;
    
    // Константы для анимационного вращения
    private static final int MAX_ROTATION_SPEED = 25000;
    private static final int MIN_ROTATION_SPEED = 20000;
    private static final float MIN_ROTATION_ANGLE = 0.0f;
    private static final float MAX_ROTATION_ANGLE = (float) Math.PI * 8.0f;
    
    // Константы для анимационного смещения
    private static final int MAX_TRANSLATION_SPEED = 7500;
    private static final int MIN_TRANSLATION_SPEED = 2500;
    
    // Максимальное время до начала анимации
    public static final int MAX_PHASE_DELAY = 20000;
    
    // Информация о фигурах
    private static final float MAX_RADIUS = 0.15f;
    private static final float MAX_LENGTH = 0.2f;
    private static final float MAX_SHININESS = 128.0f;
    private static final float SPHERE_RADIUS = 0.15f;
    private static final float BOUNDING_RADIUS = 100.0f;
    private static final long serialVersionUID = 1L;
    
    private Switch shapeSwitch;  // содержит летающие фигуры
    private BoundingSphere bounds;  // границы для узлов и групп
    
    private SimpleUniverse simpleUniverse;  // среда игры
    
    private String imageName;   // Имя файла с изображением текстуры
    
    // Конструктор Java3DWorld1
    public Java3DWorld1(String imageFileName) {
        super(SimpleUniverse.getPreferredConfiguration());
        
        imageName = imageFileName;
        // Создание вселенной SimpleUniverse (трехмерной графической среды)
        simpleUniverse = new SimpleUniverse(this);
        
        // Установка дистанции обзора для сцены
        ViewingPlatform viewPlatform = simpleUniverse.getViewingPlatform();       
        viewPlatform.setNominalViewingTransform();
        
        // Создание сцены
        BranchGroup brachGroup = createScene();
        
        // Присоединение группы BrachGroup к SimpleUniverse
        simpleUniverse.addBranchGraph(brachGroup);
    }

    // Создание 3D-сцены
    @SuppressWarnings("FinalMethod")
    public final BranchGroup createScene() {
        BranchGroup sceneBranchGroup = new BranchGroup();
        
        // Создание группы сцен Switch
        Switch sceneSwitch = initializeSwitch(DEFAULT_SCENE);
        
        // Создание группы фигур Switch
        shapeSwitch = initializeSwitch(DEFAULT_SCENE);
        
        // Инициализация группы BranchGroup, которая содержит элементы игровой сцены
        BranchGroup gameBranchGroup = new BranchGroup();
        
        // Добавление shapeSwitch к gameBranchGroup
        gameBranchGroup.addChild(shapeSwitch);
        
        // Добавление gameBranchGroup к sceneSwitch
        sceneSwitch.addChild(gameBranchGroup);
        
        // Добавление sceneSwitch к sceneBranchGroup
        sceneBranchGroup.addChild(sceneSwitch);
        
        // Создание ограничивающей сферы для объектов и поведений
        bounds = new BoundingSphere(new Point3d(0.0f, 0.0f, 0.0f), BOUNDING_RADIUS);
        
        // Создание массива для вращений TransformGroup
        TransformGroup[] spinTransform = createTransformGroupArray(NUMBER_OF_SHAPES);
        
        // Создание массива для смещений TransformGroup
        TransformGroup[] pathTransform = createTransformGroupArray(NUMBER_OF_SHAPES);
        
        // Создание интерполяторов RotationInterpolator
        createRotationInterpolators(spinTransform, NUMBER_OF_SHAPES);
        
        // Создание интерполяторов PositionInterpolator
        createPositionInterpolator(pathTransform, NUMBER_OF_SHAPES);
        
        // Создание объектов Appearance для примитивов
        Appearance[] shapeAppearance = createAppearance(NUMBER_OF_SHAPES);
        
        // Создание фигур
        Primitive[] shapes = createShapes(shapeAppearance, NUMBER_OF_SHAPES);
        
        // Добавление фигур в структуру сцены
        for (int x = 0; x < NUMBER_OF_SHAPES; x++) {
            
            // Добавление фигур в группу spinTransform
            spinTransform[x].addChild(shapes[x]);
            
            // Добавление spinTransform в группу pathTransform
            pathTransform[x].addChild(spinTransform[x]);
            
            // Добавление pathTransform в группу shapeSwitch
            shapeSwitch.addChild(pathTransform[x]);
        }
        
        // Создание освещения сцены
        setLighting(sceneBranchGroup, bounds);
        
        // Создание сцены, отображаемой при проигрыше
        TransformGroup loserTransformGroup = createEndScene("You Lose!");
        
        // Добавление сцены проигрыша в группу sceneSwitch
        sceneSwitch.addChild(loserTransformGroup);
        
        // Создание сцены, отображаемой при выигрыше
        TransformGroup winnerTransformGroup = createEndScene("You Win!");
        
        // Добавление сцены выигрыша в группу sceneSwitch
        sceneSwitch.addChild(winnerTransformGroup);
        
        // Создание ярко-красной фигуры, управляемой игроком
        Appearance flyingAppearance = createAppearance(new Color3f(1.0f, 0.0f, 0.0f));
        
        // Инициализация сферы управляемой фигуры
        Primitive flyingBall = new Sphere(0.03f, Sphere.GENERATE_NORMALS, flyingAppearance);
        
        // Установка битов разрешения обнаружения столкновений, разрешение на чтение и запись
        flyingBall.setCollidable(true);
        flyingBall.setCapability(Node.ENABLE_COLLISION_REPORTING);
        flyingBall.setCapability(Node.ALLOW_BOUNDS_READ);
        flyingBall.setCapability(Node.ALLOW_BOUNDS_WRITE);
        
        // Создание TransformGroup для перемещения фигуры
        TransformGroup startTransform = createTransform(new Vector3f(0.9f, -0.9f, 0.0f));
        
        startTransform.addChild(flyingBall);
        gameBranchGroup.addChild(startTransform);
        // Создание материала Material для внешнего вида Appearance целевой сферы
        Appearance targetAppearance = createAppearance(new Color3f(0.0f, 1.0f, 0.0f));
        
        // Получение текстурированного изображения для целевой сферы
        String rgb = "RGB";
        TextureLoader textureLoader = new TextureLoader(
                Java3DWorld1.class.getResource(imageName),
                rgb,
                this
        );
        textureLoader.getTexture().setEnable(true);
        targetAppearance.setTexture(textureLoader.getTexture());
        
        // Инициализация целевой сферы
        Primitive targetSphere = new Sphere(
                SPHERE_RADIUS, 
                Sphere.GENERATE_TEXTURE_COORDS | Sphere.GENERATE_NORMALS,
                targetAppearance
        );
        
        // Запрет обнаружения столкновений для целевой сферы
        targetSphere.setCollidable(false);
        
        // Создание вектора для целевой точки
        Vector3f target = new Vector3f(-1.0f, 1.0f, -1.0f);
        
        // Создание TransformGroup для смещения положения сферы
        TransformGroup targetTransform = createTransform(target);
        targetTransform.addChild(targetSphere);
        gameBranchGroup.addChild(targetTransform);
        
        // Создание поведения Navigator
        Navigator navigator = new Navigator(startTransform);
        navigator.setSchedulingBounds(bounds);
        
        // Создание поведения Collide
        Collide collider = new Collide(simpleUniverse, flyingBall, sceneSwitch);
        collider.setSchedulingBounds(bounds);
        
        // Создание поведения GoalDetector
        GoalDetector goalDetector = new GoalDetector(
                simpleUniverse,
                startTransform,
                sceneSwitch,
                target,
                SPHERE_RADIUS
        );
        goalDetector.setSchedulingBounds(bounds);
        
        // Добавление поведения Behavior в сцену
        sceneBranchGroup.addChild(goalDetector);
        sceneBranchGroup.addChild(collider);
        sceneBranchGroup.addChild(navigator);
        
        // Создание фона Background для сцены
        Background background = new Background();
        background.setColor(0.65f, 0.75f, 1.0f);
        background.setApplicationBounds(bounds);
        sceneBranchGroup.addChild(background);
        sceneBranchGroup.compile();
        
        return sceneBranchGroup;
    }
    
    private Appearance createAppearance(Color3f diffuseColor) {  // Создание объекта Appearance
        Appearance appearance = new Appearance();
        Material material = new Material();
        material.setShininess(MAX_SHININESS);
        material.setDiffuseColor(diffuseColor);
        material.setAmbientColor(0.0f, 0.0f, 0.0f);
        appearance.setMaterial(material);
        
        return appearance;
    }
    
    // Создание группы TransformGroup для размещения объекта на сцене
    private TransformGroup createTransform(Vector3f positionVector) {
        // Инициализация TransformGroup и установка битов разрешений
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Смещение начальной позиции в нижний правый угол
        Transform3D location = new Transform3D();
        location.setTranslation(positionVector);
        transformGroup.setTransform(location);
        
        return transformGroup;
    }

    // Инициализация группы Switch и установка битов разрешений
    private Switch initializeSwitch(int sceneNumber) {
        Switch switchGroup = new Switch(sceneNumber);
        switchGroup.setCollidable(true);
        switchGroup.setCapability(Switch.ALLOW_SWITCH_WRITE);
        switchGroup.setCapability(Switch.ALLOW_SWITCH_READ);
        switchGroup.setCapability(Switch.ALLOW_CHILDREN_WRITE);
        switchGroup.setCapability(Switch.ALLOW_CHILDREN_READ);
        switchGroup.setCapability(Group.ENABLE_COLLISION_REPORTING);
        
        return switchGroup;
    }

    private TransformGroup[] createTransformGroupArray(int size) {
        TransformGroup[] transformGroup = new TransformGroup[size];
        // Установка разрешений на чтение и запись для TransformGroup,
        // а также разрешение на уведомление о столкновениях
        for (int i = 0; i < size; i++) {
            // Создание групп TransformGroup
            transformGroup[i] = new TransformGroup();
            
            // Разрешение уведомлений о столкновениях
            transformGroup[i].setCapability(Group.ENABLE_COLLISION_REPORTING);
            
            // Установка разрешения на запись
            transformGroup[i].setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            
            // Установка разрешения на чтение
            transformGroup[i].setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        }
        
        return transformGroup;
    }

    @SuppressWarnings("deprecation")    // Создание интерполяторов RotationInterpolator для сцены
    private void createRotationInterpolators(TransformGroup[] transformGroup, int size) {
        // Объявление структуры для создания интерполяторов
        Alpha[] alphaSpin = new Alpha[size];
        
        Transform3D[] spinAxis = new Transform3D[size];
        
        RotationInterpolator[] spinner = new RotationInterpolator[size];
        
        // Создание интерполятора для каждой из фигур
        for (int x = 0; x < size; x++) {
            // Инициализация объекта Alpha
            alphaSpin[x] = new Alpha();
            
            // Установка случайного времени увеличения для Alpha
            alphaSpin[x].setIncreasingAlphaDuration(MIN_ROTATION_SPEED + ((int) (Math.random() * MAX_ROTATION_SPEED)));
            
            // Инициализация интерполятора с помощью соответствующих объектов Alpha и TransformGroup
            spinner[x] = new RotationInterpolator(alphaSpin[x], transformGroup[x]);
            
            spinAxis[x] = new Transform3D();
            
            // Установка случайного вращения вдоль оси X
            spinAxis[x].rotX((float) (Math.PI * (Math.random() * 2)));
            spinner[x].setAxisOfRotation(spinAxis[x]);
            
            // Установка минимального и максимального углов вращения
            spinner[x].setMinimumAngle(MIN_ROTATION_ANGLE);
            spinner[x].setMaximumAngle(MAX_ROTATION_ANGLE);
            
            spinner[x].setSchedulingBounds(bounds);
            // Добавление интерполятора в соответствующую группу TransformGroup
            transformGroup[x].addChild(spinner[x]);
        }
    }

    // Создание интерполяторов PositionInterpolator
    private void createPositionInterpolator(TransformGroup[] transformGroup, int size) {
        // Создание структур для интерполяторов
        Alpha[] alphaPath = new Alpha[size];
        PositionInterpolator[] mover = new PositionInterpolator[size];
        Transform3D[] pathAxis = new Transform3D[size];
        // Создание интерполятора PositionInterpolator для каждой из фигур
        for (int x = 0; x < size; x++) {
            
            // Инициализация объекта Alpha
            alphaPath[x] = new Alpha();
            // Установка режима уменьшения и увеличения при интерполяции
            alphaPath[x].setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
            
            // Установка случайной задержки
            alphaPath[x].setPhaseDelayDuration((int) (Math.random() * MAX_PHASE_DELAY));
            
            // Задание случайной скорости смещения
            int speed = MIN_TRANSLATION_SPEED + (int) (Math.random() * MAX_TRANSLATION_SPEED);
            
            // Установка длительностей увеличения и уменьшения
            alphaPath[x].setIncreasingAlphaDuration(speed);
            alphaPath[x].setDecreasingAlphaDuration(speed);
            
            // Задание случайной оси
            pathAxis[x] = new Transform3D();
            pathAxis[x].rotX((float) (Math.PI * (Math.random() * 2)));
            pathAxis[x].rotY((float) (Math.PI * (Math.random() * 2)));
            pathAxis[x].rotZ((float) (Math.PI * (Math.random() * 2)));
            
            // Инициализация PositionInterpolator
            mover[x] = new PositionInterpolator(alphaPath[x], transformGroup[x], pathAxis[x], 1.0f, -1.0f);
            mover[x].setSchedulingBounds(bounds);
            // Добавление интерполятора в соответствующую группу TransformGroup
            transformGroup[x].addChild(mover[x]);
        }
    }

    // Создание массива внешнего облика и материалов для примитивов
    private Appearance[] createAppearance(int size) {
        // Создание объектов Appearance для каждой из фигур
        Appearance[] appearance = new Appearance[size];
        
        Material[] material = new Material[size];
        
        // Установка свойств внешнего вида и материала для каждой из фигур
        for (int i = 0; i < size; i++) {
            appearance[i] = new Appearance();
            material[i] = new Material();
            
            // Установка внешнего материала
            material[i].setAmbientColor(new Color3f(0.0f, 0.0f, 0.0f));
            
            // Установка диффузного отражения для материала
            material[i].setDiffuseColor(
                    new Color3f(
                            (float) Math.random(),
                            (float) Math.random() * 0.5f,
                            (float) Math.random()
                    )
            );
            // Задание материала Material для объекта Appearance
            appearance[i].setMaterial(material[i]);
        }
        
        return appearance;
    }

    // Создание фигур
    private Primitive[] createShapes(Appearance[] appearance, int size) {
        Primitive[] shapes = new Primitive[size];
        
        // Цикл для получения случайного индекса
        for (int x = 0; x < size; x++) {
            
            // Генерирование случайного индекса для фигуры
            int index = (int) (Math.random() * NUMBER_OF_PRIMITIVES);
            
            // Создание фигуры на основе случайного индекса
            switch (index) {
                case 0: // Создание параллелепипеда Box
                    shapes[x] = new Box(
                            (float) Math.random() * MAX_LENGTH,
                            (float) Math.random() * MAX_LENGTH,
                            (float) Math.random() * MAX_LENGTH,
                            Box.GENERATE_NORMALS,
                            appearance[x]
                    );
                    break;
                case 1: // Создание конуса
                    shapes[x] = new Cone(
                            (float) Math.random() * MAX_RADIUS,
                            (float) Math.random() * MAX_LENGTH,
                            Cone.GENERATE_NORMALS,
                            appearance[x]
                    );
                    break;
                case 2: // Создание цилиндра Cylinder
                    shapes[x] = new Cylinder(
                            (float) Math.random() * MAX_RADIUS,
                            (float) Math.random() * MAX_LENGTH,
                            Cylinder.GENERATE_NORMALS,
                            appearance[x]
                    );
                    break;
                case 3: // Создание сферы Sphere
                    shapes[x] = new Sphere(
                            (float) Math.random() * MAX_RADIUS,
                            Sphere.GENERATE_NORMALS,
                            appearance[x]
                    );
                    break;
            }
            
            // Установка битов, чтобы разрешить столкновения и операции чтения/записи
            shapes[x].setCapability(Node.ENABLE_COLLISION_REPORTING);
            shapes[x].setCapability(Node.ALLOW_BOUNDS_READ);
            shapes[x].setCapability(Node.ALLOW_BOUNDS_WRITE);
            shapes[x].setCollidable(true);
        }
        
        return shapes;
    }

    // Инициализация освещения
    private void setLighting(BranchGroup scene, BoundingSphere bounds) {
        // Инициализация внешнего освещения
        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setInfluencingBounds(bounds);
        
        // Инициализация направленного освещения
        DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setColor(new Color3f(1.0f, 1.0f, 1.0f));
        directionalLight.setInfluencingBounds(bounds);
        
        // Добавление освещения к сцене
        scene.addChild(ambientLight);
        scene.addChild(directionalLight);
    }
    
    // Обновление сцены путем воспроизведения различных фигур в shapeSwitch
    public void switchScene(int numberChildren, int size) {
        // Создание нового объекта BitSet размеров
        BitSet bitSet = new BitSet(size);
        
        // Установка значений BitSet
        for (int i = 0; i < numberChildren; i++) {
            bitSet.set(i);
        }
        
        // Указание отображать объекты с помощью маски Mask
        shapeSwitch.setWhichChild(Switch.CHILD_MASK);
        shapeSwitch.setChildMask(bitSet);
    }

    // Создание заключительной сцены при выигрыше или проигрыше игрока
    @SuppressWarnings("deprecation")
    private TransformGroup createEndScene(String text) {
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        // Запрет обнаружения столкновений для сцен
        transformGroup.setCollidable(false);
        
        // Создание объекта Alpha
        Alpha alpha = new Alpha();
        alpha.setIncreasingAlphaDuration(MAX_ROTATION_SPEED);
        
        // Создание интерполятора RotationInterpolator для сцен
        RotationInterpolator rotation = new RotationInterpolator(alpha, transformGroup);
        
        // Задание оси вращения
        Transform3D axis = new Transform3D();
        axis.rotY((float) (Math.PI / 2));
        rotation.setAxisOfRotation(axis);
        
        // Задание мимнимального и максимального углов вращения
        rotation.setMinimumAngle(0.0f);
        rotation.setMaximumAngle((float) (Math.PI * 8.0));
        
        rotation.setSchedulingBounds(bounds);
        transformGroup.addChild(rotation);
        
        // Создание геометрии сцены
        Appearance appearance = new Appearance();
        Material material = new Material();
        appearance.setMaterial(material);
        
        // Задание рассеянного отражения для материала
        material.setDiffuseColor(new Color3f(0.0f, 0.8f, 1.0f));
        
        // Создание объекта Font3D
        Font3D font3d = new Font3D(new Font("Helvetica", Font.ITALIC, 1), new FontExtrusion());
        
        // Создание объекта Text3D из объекта Font3D
        Text3D text3d = new Text3D(font3d, text, new Point3f(-2.0f, 0.0f, 0.0f));
        
        // Создание объекта Shape3D из объекта Text3D
        Shape3D textShape = new Shape3D(text3d);
        textShape.setAppearance(appearance);
        // Запрет обнаружения столкновений
        textShape.setCollidable(false);
        
        transformGroup.addChild(textShape);
        
        return transformGroup;
    }
    
    @Override   // Возврат предпочтительных размеров контейнера
    public Dimension getPreferredSize() {
        return new Dimension(CONTAINER_WIDTH, CONTAINER_HEIGHT);
    }
    
    @Override
    public Dimension getMinimumSize() { // Возврат минимального размера контейнера
        return getPreferredSize();
    }
    
}
