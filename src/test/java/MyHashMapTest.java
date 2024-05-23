import org.example.map.MyHashMap;
import org.junit.Assert;
import org.junit.Test;

public class MyHashMapTest {
    public static final String INVALID_SIZE = "Invalid hashmap size";
    public static final String INVALID_VALUE = "Invalid value";
    public static final int NUMBER_0 = 0;
    public static final int NUMBER_1 = 1;
    public static final int NUMBER_500 = 500;
    public static final int NUMBER_501 = 501;
    public static final int NUMBER_1000 = 1000;
    public static final String SOMETHING = "Something";
    public static final String VALUE = "Value";

    @Test()
    public void testGetSize() {
        MyHashMap<String, String> map = new MyHashMap<>();
        Assert.assertEquals(INVALID_SIZE, map.getSize(), NUMBER_0);
        map.put(SOMETHING, VALUE);
        Assert.assertEquals(INVALID_SIZE, map.getSize(), NUMBER_1);
        map.put(SOMETHING, VALUE);
        map.put(SOMETHING, VALUE);
        Assert.assertEquals(INVALID_SIZE, map.getSize(), NUMBER_1);
        map.remove(SOMETHING);
        Assert.assertEquals(INVALID_SIZE, map.getSize(), NUMBER_0);
        map.remove(SOMETHING);
        map.remove(SOMETHING);
        Assert.assertEquals(INVALID_SIZE, map.getSize(), NUMBER_0);
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        Assert.assertEquals(INVALID_SIZE, map.getSize(), NUMBER_1000);
        for (int i = 0; i < NUMBER_500; i++) {
            map.remove(SOMETHING + i);
        }
        Assert.assertEquals(INVALID_SIZE, map.getSize(), NUMBER_500);
        map.put(null, VALUE);
        Assert.assertEquals(INVALID_SIZE, map.getSize(), NUMBER_501);
        map.remove(null);
        Assert.assertEquals(INVALID_SIZE, map.getSize(), NUMBER_500);
    }

    @Test
    public void testIsEmpty() {
        MyHashMap<String, String> map = new MyHashMap<>();
        Assert.assertTrue(map.isEmpty());
        map.put(SOMETHING, VALUE);
        Assert.assertFalse(map.isEmpty());
        map.remove(SOMETHING);
        Assert.assertTrue(map.isEmpty());
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        Assert.assertFalse(map.isEmpty());
        map.clear();
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void testGetAndPutString() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put(SOMETHING, VALUE);
        Assert.assertEquals(INVALID_VALUE, map.get(SOMETHING), VALUE);
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        Assert.assertEquals(INVALID_VALUE, map.get(SOMETHING + NUMBER_500), VALUE + NUMBER_500);
        map.put(SOMETHING + NUMBER_500, VALUE);
        Assert.assertEquals(INVALID_VALUE, map.get(SOMETHING + NUMBER_500), VALUE);
        map.put(null, VALUE);
        Assert.assertEquals(INVALID_VALUE, map.get(null), VALUE);
        map.put(null, null);
        Assert.assertNull(INVALID_VALUE, map.get(null));
    }

    @Test
    public void testGetAndPutInteger() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(NUMBER_0, VALUE);
        Assert.assertEquals(INVALID_VALUE, map.get(NUMBER_0), VALUE);
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(i, VALUE + i);
        }
        Assert.assertEquals(INVALID_VALUE, map.get(NUMBER_500), VALUE + NUMBER_500);
        map.put(NUMBER_500, VALUE);
        Assert.assertEquals(INVALID_VALUE, map.get(NUMBER_500), VALUE);
        map.put(null, VALUE);
        Assert.assertEquals(INVALID_VALUE, map.get(null), VALUE);
        map.put(null, null);
        Assert.assertNull(INVALID_VALUE, map.get(null));
    }

    @Test
    public void testClear() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.clear();
        Assert.assertTrue(map.isEmpty());
        map.put(NUMBER_0, VALUE);
        Assert.assertFalse(map.isEmpty());
        map.clear();
        Assert.assertTrue(map.isEmpty());
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(i, VALUE + i);
        }
        Assert.assertFalse(map.isEmpty());
        map.clear();
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void testContainsKeyString() {
        MyHashMap<String, String> map = new MyHashMap<>();
        Assert.assertFalse(map.containsKey(SOMETHING));
        map.put(SOMETHING, VALUE);
        Assert.assertTrue(map.containsKey(SOMETHING));
        map.remove(SOMETHING);
        Assert.assertFalse(map.containsKey(SOMETHING));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertTrue(map.containsKey(SOMETHING + i));
        }
        map.clear();
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertFalse(map.containsKey(SOMETHING + i));
        }
    }

    @Test
    public void testContainsKeyInteger() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        Assert.assertFalse(map.containsKey(NUMBER_0));
        map.put(NUMBER_0, VALUE);
        Assert.assertTrue(map.containsKey(NUMBER_0));
        map.remove(NUMBER_0);
        Assert.assertFalse(map.containsKey(NUMBER_0));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(i, VALUE + i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertTrue(map.containsKey(i));
        }
        map.clear();
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertFalse(map.containsKey(i));
        }
    }

    @Test
    public void testContainsValueString() {
        MyHashMap<String, String> map = new MyHashMap<>();
        Assert.assertFalse(map.containsValue(VALUE));
        map.put(SOMETHING, VALUE);
        Assert.assertTrue(map.containsValue(VALUE));
        map.remove(SOMETHING);
        Assert.assertFalse(map.containsValue(VALUE));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertTrue(map.containsValue(VALUE + i));
        }
        map.clear();
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertFalse(map.containsValue(VALUE + i));
        }
    }

    @Test
    public void testContainsValueInteger() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        Assert.assertFalse(map.containsValue(NUMBER_0));
        map.put(SOMETHING, NUMBER_0);
        Assert.assertTrue(map.containsValue(NUMBER_0));
        map.remove(SOMETHING);
        Assert.assertFalse(map.containsValue(NUMBER_0));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertTrue(map.containsValue(i));
        }
        map.clear();
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertFalse(map.containsValue(i));
        }
    }

    @Test
    public void testRemove() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            if (i % 2 == 0) {
                map.remove(SOMETHING + i);
            }
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            if (i % 2 == 0) {
                Assert.assertFalse(map.containsKey(SOMETHING + i));
            } else {
                Assert.assertTrue(map.containsKey(SOMETHING + i));
            }
        }
        Assert.assertEquals(map.getSize(), NUMBER_500);
    }
}