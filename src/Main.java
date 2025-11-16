import functions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ МЕТОДОВ TABULATED FUNCTION ===\n");

        // Создаем тестовые данные для функции x^2 - x + 41
        System.out.println("1. СОЗДАНИЕ ТЕСТОВЫХ ФУНКЦИЙ (x^2 - x + 41)");

        // Создаем точки для функции x^2 - x + 41
        FunctionPoint[] points = new FunctionPoint[5];
        for (int i = 0; i < 5; i++) {
            double x = i * 2.0; // x = 0, 2, 4, 6, 8
            double y = x * x - x + 41; // y = x^2 - x + 41
            points[i] = new FunctionPoint(x, y);
        }

        // Создаем функции разных типов
        ArrayTabulatedFunction arrayFunc = new ArrayTabulatedFunction(points);
        LinkedListTabulatedFunction linkedListFunc = new LinkedListTabulatedFunction(points);

        System.out.println("Созданы функции с точками:");
        for (int i = 0; i < points.length; i++) {
            System.out.printf("  (%.1f; %.1f)", points[i].getX(), points[i].getY());
        }
        System.out.println("\n");

        // 1. Тестирование toString()
        System.out.println("2. ТЕСТИРОВАНИЕ toString()");
        System.out.println("ArrayTabulatedFunction: " + arrayFunc.toString());
        System.out.println("LinkedListTabulatedFunction: " + linkedListFunc.toString());
        System.out.println();

        // 2. Тестирование equals()
        System.out.println("3. ТЕСТИРОВАНИЕ equals()");

        // Создаем идентичные функции
        ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(points);
        LinkedListTabulatedFunction linkedListFunc2 = new LinkedListTabulatedFunction(points);

        // Создаем отличающуюся функцию (изменена одна точка)
        FunctionPoint[] differentPoints = points.clone();
        differentPoints[2] = new FunctionPoint(4.0, 50.0); // Изменяем y в точке x=4

        ArrayTabulatedFunction differentArrayFunc = new ArrayTabulatedFunction(differentPoints);
        LinkedListTabulatedFunction differentLinkedListFunc = new LinkedListTabulatedFunction(differentPoints);

        System.out.println("arrayFunc.equals(arrayFunc2): " + arrayFunc.equals(arrayFunc2));
        System.out.println("linkedListFunc.equals(linkedListFunc2): " + linkedListFunc.equals(linkedListFunc2));
        System.out.println("arrayFunc.equals(linkedListFunc): " + arrayFunc.equals(linkedListFunc));
        System.out.println("arrayFunc.equals(differentArrayFunc): " + arrayFunc.equals(differentArrayFunc));
        System.out.println("linkedListFunc.equals(differentLinkedListFunc): " + linkedListFunc.equals(differentLinkedListFunc));
        System.out.println("arrayFunc.equals(null): " + arrayFunc.equals(null));
        System.out.println("arrayFunc.equals(\"строка\"): " + arrayFunc.equals("строка"));
        System.out.println();

        // 3. Тестирование hashCode()
        System.out.println("4. ТЕСТИРОВАНИЕ hashCode()");
        System.out.println("arrayFunc.hashCode(): " + arrayFunc.hashCode());
        System.out.println("arrayFunc2.hashCode(): " + arrayFunc2.hashCode());
        System.out.println("linkedListFunc.hashCode(): " + linkedListFunc.hashCode());
        System.out.println("linkedListFunc2.hashCode(): " + linkedListFunc2.hashCode());
        System.out.println("differentArrayFunc.hashCode(): " + differentArrayFunc.hashCode());
        System.out.println("differentLinkedListFunc.hashCode(): " + differentLinkedListFunc.hashCode());

        // Проверка согласованности equals() и hashCode()
        System.out.println("\nПроверка согласованности equals() и hashCode():");
        System.out.println("arrayFunc.equals(arrayFunc2) && arrayFunc.hashCode() == arrayFunc2.hashCode(): " +
                (arrayFunc.equals(arrayFunc2) && arrayFunc.hashCode() == arrayFunc2.hashCode()));
        System.out.println("linkedListFunc.equals(linkedListFunc2) && linkedListFunc.hashCode() == linkedListFunc2.hashCode(): " +
                (linkedListFunc.equals(linkedListFunc2) && linkedListFunc.hashCode() == linkedListFunc2.hashCode()));
        System.out.println();

        // Тестирование изменения объекта и хэш-кода
        System.out.println("5. ТЕСТИРОВАНИЕ ИЗМЕНЕНИЯ ХЭШ-КОДА ПРИ ИЗМЕНЕНИИ ОБЪЕКТА");
        System.out.println("Исходный hashCode arrayFunc: " + arrayFunc.hashCode());

        try {
            // Изменяем одну координату на несколько тысячных
            double originalY = arrayFunc.getPointY(2);
            arrayFunc.setPointY(2, originalY + 0.001);
            System.out.println("hashCode после изменения Y на 0.001: " + arrayFunc.hashCode());

            // Возвращаем обратно
            arrayFunc.setPointY(2, originalY);
            System.out.println("hashCode после возврата исходного значения: " + arrayFunc.hashCode());
        } catch (Exception e) {
            System.out.println("Ошибка при изменении точки: " + e.getMessage());
        }
        System.out.println();

        // 4. Тестирование clone()
        System.out.println("6. ТЕСТИРОВАНИЕ clone() И ГЛУБОКОГО КЛОНИРОВАНИЯ");

        // Клонируем функции
        ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) arrayFunc.clone();
        LinkedListTabulatedFunction linkedListClone = (LinkedListTabulatedFunction) linkedListFunc.clone();

        System.out.println("Исходная arrayFunc: " + arrayFunc.toString());
        System.out.println("Клон arrayClone: " + arrayClone.toString());
        System.out.println("Исходная linkedListFunc: " + linkedListFunc.toString());
        System.out.println("Клон linkedListClone: " + linkedListClone.toString());

        System.out.println("\narrayFunc.equals(arrayClone): " + arrayFunc.equals(arrayClone));
        System.out.println("linkedListFunc.equals(linkedListClone): " + linkedListFunc.equals(linkedListClone));

        // Проверка глубокого клонирования - изменяем исходные объекты
        System.out.println("\n7. ПРОВЕРКА ГЛУБОКОГО КЛОНИРОВАНИЯ");

        try {
            // Изменяем исходные функции
            arrayFunc.setPointY(1, 100.0);
            linkedListFunc.setPointY(1, 200.0);

            System.out.println("После изменения исходных функций:");
            System.out.println("Исходная arrayFunc: " + arrayFunc.toString());
            System.out.println("Клон arrayClone: " + arrayClone.toString());
            System.out.println("Исходная linkedListFunc: " + linkedListFunc.toString());
            System.out.println("Клон linkedListClone: " + linkedListClone.toString());

            System.out.println("\narrayFunc.equals(arrayClone) после изменений: " + arrayFunc.equals(arrayClone));
            System.out.println("linkedListFunc.equals(linkedListClone) после изменений: " + linkedListFunc.equals(linkedListClone));

            // Проверяем, что клоны не изменились
            boolean arrayCloneUnchanged = true;
            boolean linkedListCloneUnchanged = true;

            for (int i = 0; i < arrayClone.getPointsCount(); i++) {
                if (arrayClone.getPointY(i) != arrayClone.getPoint(i).getY()) {
                    arrayCloneUnchanged = false;
                    break;
                }
            }

            for (int i = 0; i < linkedListClone.getPointsCount(); i++) {
                if (linkedListClone.getPointY(i) != linkedListClone.getPoint(i).getY()) {
                    linkedListCloneUnchanged = false;
                    break;
                }
            }

            System.out.println("\nКлон arrayClone не изменился: " + arrayCloneUnchanged);
            System.out.println("Клон linkedListClone не изменился: " + linkedListCloneUnchanged);

        } catch (Exception e) {
            System.out.println("Ошибка при изменении точек: " + e.getMessage());
        }

        // Дополнительные тесты
        System.out.println("\n8. ДОПОЛНИТЕЛЬНЫЕ ТЕСТЫ");

        // Тест с разным количеством точек
        FunctionPoint[] fewerPoints = new FunctionPoint[3];
        for (int i = 0; i < 3; i++) {
            fewerPoints[i] = points[i];
        }
        ArrayTabulatedFunction fewerPointsFunc = new ArrayTabulatedFunction(fewerPoints);

        System.out.println("arrayFunc.equals(fewerPointsFunc) [разное количество точек]: " + arrayFunc.equals(fewerPointsFunc));
        System.out.println("arrayFunc.hashCode() == fewerPointsFunc.hashCode(): " + (arrayFunc.hashCode() == fewerPointsFunc.hashCode()));

        // Тест клонирования FunctionPoint
        FunctionPoint originalPoint = new FunctionPoint(10.0, 20.0);
        FunctionPoint clonedPoint = (FunctionPoint) originalPoint.clone();
        originalPoint.setX(15.0);
        System.out.println("Глубокое клонирование FunctionPoint: original.x=" + originalPoint.getX() + ", clone.x=" + clonedPoint.getX());
    }
}