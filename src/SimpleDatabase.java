import java.util.HashMap;
import java.util.Map;

public class SimpleDatabase {

    private static final int MAX_SIZE = 1000000;
    private Map<String, String> data;
    private int currentSize;

    public SimpleDatabase() {
        data = new HashMap<>();
        currentSize = 0;
    }

    public boolean put(String key, String value) {
        if (key == null || value == null) {
            System.out.println("Ключ и значение не могут быть null.");
            return false;
        }

        int newValueSize = key.length() + value.length();

        if (currentSize + newValueSize > MAX_SIZE) {
            System.out.println("Недостаточно места в базе данных. Максимальный размер: " + MAX_SIZE + " символов.");
            return false;
        }

        if (data.containsKey(key)) {
            currentSize -= key.length() + data.get(key).length();
        }

        data.put(key, value);
        currentSize += newValueSize;

        System.out.println("Запись добавлена/обновлена.  Ключ: " + key + ", значение: " + value);
        System.out.println("Текущий размер базы данных: " + currentSize);
        return true;
    }

    public String get(String key) {
        return data.get(key);
    }

    public boolean remove(String key) {
        if (data.containsKey(key)) {
            String value = data.get(key);
            currentSize -= key.length() + value.length();
            data.remove(key);
            System.out.println("Запись удалена. Ключ: " + key);
            System.out.println("Текущий размер базы данных: " + currentSize);
            return true;
        } else {
            System.out.println("Ключ не найден: " + key);
            return false;
        }
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getMaxSzie() {
        return MAX_SIZE;
    }

    public static void main(String[] args) {
        SimpleDatabase db = new SimpleDatabase();

        db.put("name", "Andrey");
        db.put("age", "20");
        db.put("city", "Perm");

        System.out.println("Name: " + db.get("name"));
        System.out.println("Age: " + db.get("age"));

        db.remove("age");

        System.out.println("Age after remove: " + db.get("age"));

        StringBuilder longValue = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            longValue.append("Yra");
        }

        db.put("longKey", longValue.toString());

        longValue = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            longValue.append("Vova");
        }
        db.put("veryLongKey", longValue.toString());

        System.out.println("Текущий размер базы данных: " + db.getCurrentSize());
        System.out.println("Максимальный размер базы данных: " + db.getMaxSzie());
    }
}