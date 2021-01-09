# Task6SberTech
1) Имплементировать интерфейс в классе CalculatorImpl (Расчет факториала числа)
2) Вывести на консоль все методы класса, включая все родительские методы (включая приватные)
3) Вывести все геттеры класса
4) Проверить что все String константы имеют значение = их имени (public static final String MONDAY = "MONDAY")
5) Реализовать кэширующий прокси
6) Создать свою аннотацию Metric. Реализовать proxy класс PerformanceProxy, который в случае если метод аннотирован Metric будет выводить на консоль время выполнения метода.
7) Реализовать следующий класс по документации
public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {... }
}


