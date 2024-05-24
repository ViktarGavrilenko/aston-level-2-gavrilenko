import org.example.map.MyHashMap;
import org.junit.Assert;
import org.junit.Test;

public class MyHashMapTest {
    public static final String INVALID_SIZE = "Invalid hashmap size";
    public static final String INVALID_VALUE = "Invalid value";
    public static final String HASHMAP_IS_NOT_EMPTY = "Hashmap is not empty";
    public static final String HASHMAP_IS_EMPTY = "Hashmap is empty";
    public static final String HASHMAP_CONTAINS_KEY = "Hashmap contains key";
    public static final String HASHMAP_DOSE_NOT_CONTAINS_KEY = "Hashmap does not contains key";
    public static final String HASHMAP_CONTAINS_VALUE = "Hashmap contains value";
    public static final String HASHMAP_DOSE_NOT_CONTAINS_VALUE = "Hashmap does not contains value";
    public static final int NUMBER_0 = 0;
    public static final int NUMBER_1 = 1;
    public static final int NUMBER_500 = 500;
    public static final int NUMBER_501 = 501;
    public static final int NUMBER_1000 = 1000;
    public static final String SOMETHING = "Something";
    public static final String VALUE = "Value";

    @Test()
    public void testSize() {
        MyHashMap<String, String> map = new MyHashMap<>();
        Assert.assertEquals(INVALID_SIZE, NUMBER_0, map.size());
        map.put(SOMETHING, VALUE);
        Assert.assertEquals(INVALID_SIZE, NUMBER_1, map.size());
        map.put(SOMETHING, VALUE);
        map.put(SOMETHING, VALUE);
        Assert.assertEquals(INVALID_SIZE, NUMBER_1, map.size());
        map.remove(SOMETHING);
        Assert.assertEquals(INVALID_SIZE, NUMBER_0, map.size());
        map.remove(SOMETHING);
        map.remove(SOMETHING);
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertEquals(INVALID_SIZE, i, map.size());
            map.put(SOMETHING + i, VALUE + i);
        }
        Assert.assertEquals(INVALID_SIZE, NUMBER_1000, map.size());
        for (int i = 0; i < NUMBER_500; i++) {
            Assert.assertEquals(INVALID_SIZE, NUMBER_1000 - i, map.size());
            map.remove(SOMETHING + i);
        }
        Assert.assertEquals(INVALID_SIZE, NUMBER_500, map.size());
        map.put(null, VALUE);
        Assert.assertEquals(INVALID_SIZE, NUMBER_501, map.size());
        map.remove(null);
        Assert.assertEquals(INVALID_SIZE, NUMBER_500, map.size());
    }

    @Test
    public void testIsEmpty() {
        MyHashMap<String, String> map = new MyHashMap<>();
        Assert.assertTrue(HASHMAP_IS_NOT_EMPTY, map.isEmpty());
        map.put(SOMETHING, VALUE);
        Assert.assertFalse(HASHMAP_IS_EMPTY, map.isEmpty());
        map.remove(SOMETHING);
        Assert.assertTrue(HASHMAP_IS_NOT_EMPTY, map.isEmpty());
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        Assert.assertFalse(HASHMAP_IS_EMPTY, map.isEmpty());
        map.clear();
        Assert.assertTrue(HASHMAP_IS_NOT_EMPTY, map.isEmpty());
    }

    @Test
    public void testGetAndPutString() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put(SOMETHING, VALUE);
        Assert.assertEquals(INVALID_VALUE, VALUE, map.get(SOMETHING));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        Assert.assertEquals(INVALID_VALUE, VALUE + NUMBER_500, map.get(SOMETHING + NUMBER_500));
        map.put(SOMETHING + NUMBER_500, VALUE);
        Assert.assertEquals(INVALID_VALUE, VALUE, map.get(SOMETHING + NUMBER_500));
        map.put(null, VALUE);
        Assert.assertEquals(INVALID_VALUE, VALUE, map.get(null));
        map.put(null, null);
        Assert.assertNull(INVALID_VALUE, map.get(null));
    }

    @Test
    public void testGetAndPutInteger() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(NUMBER_0, VALUE);
        Assert.assertEquals(INVALID_VALUE, VALUE, map.get(NUMBER_0));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(i, VALUE + i);
        }
        Assert.assertEquals(INVALID_VALUE, VALUE + NUMBER_500, map.get(NUMBER_500));
        map.put(NUMBER_500, VALUE);
        Assert.assertEquals(INVALID_VALUE, VALUE, map.get(NUMBER_500));
        map.put(null, VALUE);
        Assert.assertEquals(INVALID_VALUE, VALUE, map.get(null));
        map.put(null, null);
        Assert.assertNull(INVALID_VALUE, map.get(null));
    }

    @Test
    public void testClear() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.clear();
        Assert.assertTrue(HASHMAP_IS_NOT_EMPTY, map.isEmpty());
        map.put(NUMBER_0, VALUE);
        Assert.assertFalse(HASHMAP_IS_EMPTY, map.isEmpty());
        map.clear();
        Assert.assertTrue(HASHMAP_IS_NOT_EMPTY, map.isEmpty());
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(i, VALUE + i);
        }
        Assert.assertFalse(HASHMAP_IS_EMPTY, map.isEmpty());
        map.clear();
        Assert.assertTrue(HASHMAP_IS_NOT_EMPTY, map.isEmpty());
    }

    @Test
    public void testContainsKeyString() {
        MyHashMap<String, String> map = new MyHashMap<>();
        Assert.assertFalse(HASHMAP_CONTAINS_KEY, map.containsKey(SOMETHING));
        map.put(SOMETHING, VALUE);
        Assert.assertTrue(HASHMAP_DOSE_NOT_CONTAINS_KEY, map.containsKey(SOMETHING));
        map.remove(SOMETHING);
        Assert.assertFalse(HASHMAP_CONTAINS_KEY, map.containsKey(SOMETHING));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertTrue(HASHMAP_DOSE_NOT_CONTAINS_KEY, map.containsKey(SOMETHING + i));
        }
        map.clear();
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertFalse(HASHMAP_CONTAINS_KEY, map.containsKey(SOMETHING + i));
        }
    }

    @Test
    public void testContainsKeyInteger() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        Assert.assertFalse(HASHMAP_CONTAINS_KEY, map.containsKey(NUMBER_0));
        map.put(NUMBER_0, VALUE);
        Assert.assertTrue(HASHMAP_DOSE_NOT_CONTAINS_KEY, map.containsKey(NUMBER_0));
        map.remove(NUMBER_0);
        Assert.assertFalse(HASHMAP_CONTAINS_KEY, map.containsKey(NUMBER_0));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(i, VALUE + i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertTrue(HASHMAP_DOSE_NOT_CONTAINS_KEY, map.containsKey(i));
        }
        map.clear();
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertFalse(HASHMAP_CONTAINS_KEY, map.containsKey(i));
        }
    }

    @Test
    public void testContainsValueString() {
        MyHashMap<String, String> map = new MyHashMap<>();
        Assert.assertFalse(HASHMAP_CONTAINS_VALUE, map.containsValue(VALUE));
        map.put(SOMETHING, VALUE);
        Assert.assertTrue(HASHMAP_DOSE_NOT_CONTAINS_VALUE, map.containsValue(VALUE));
        map.remove(SOMETHING);
        Assert.assertFalse(HASHMAP_CONTAINS_VALUE, map.containsValue(VALUE));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertTrue(HASHMAP_DOSE_NOT_CONTAINS_VALUE, map.containsValue(VALUE + i));
        }
        map.clear();
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertFalse(HASHMAP_CONTAINS_VALUE, map.containsValue(VALUE + i));
        }
    }

    @Test
    public void testContainsValueInteger() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        Assert.assertFalse(HASHMAP_CONTAINS_VALUE, map.containsValue(NUMBER_0));
        map.put(SOMETHING, NUMBER_0);
        Assert.assertTrue(HASHMAP_DOSE_NOT_CONTAINS_VALUE, map.containsValue(NUMBER_0));
        map.remove(SOMETHING);
        Assert.assertFalse(HASHMAP_CONTAINS_VALUE, map.containsValue(NUMBER_0));
        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertTrue(HASHMAP_DOSE_NOT_CONTAINS_VALUE, map.containsValue(i));
        }
        map.clear();
        for (int i = 0; i < NUMBER_1000; i++) {
            Assert.assertFalse(HASHMAP_CONTAINS_VALUE, map.containsValue(i));
        }
    }

    @Test
    public void testRemove() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put(SOMETHING, null);
        Assert.assertNull(INVALID_VALUE, map.remove(SOMETHING));
        map.put(SOMETHING, VALUE);
        Assert.assertEquals(INVALID_VALUE, VALUE, map.remove(SOMETHING));
        map.put(null, null);
        Assert.assertNull(INVALID_VALUE, map.remove(null));
        map.put(null, VALUE);
        Assert.assertEquals(INVALID_VALUE, VALUE, map.remove(null));

        for (int i = 0; i < NUMBER_1000; i++) {
            map.put(SOMETHING + i, VALUE + i);
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            if (i % 2 == 0) {
                Assert.assertEquals(INVALID_VALUE, VALUE + i, map.remove(SOMETHING + i));
            }
        }
        for (int i = 0; i < NUMBER_1000; i++) {
            if (i % 2 == 0) {
                Assert.assertFalse(HASHMAP_CONTAINS_KEY, map.containsKey(SOMETHING + i));
            } else {
                Assert.assertTrue(HASHMAP_DOSE_NOT_CONTAINS_KEY, map.containsKey(SOMETHING + i));
            }
        }
        Assert.assertEquals(INVALID_SIZE, NUMBER_500, map.size());

        for (int i = 0; i < NUMBER_1000; i++) {
            if (i % 2 == 0) {
                Assert.assertNull(map.remove(SOMETHING + i));
            } else {
                Assert.assertEquals(INVALID_VALUE, VALUE + i, map.remove(SOMETHING + i));
            }
        }
        Assert.assertEquals(INVALID_SIZE, NUMBER_0, map.size());
    }
}