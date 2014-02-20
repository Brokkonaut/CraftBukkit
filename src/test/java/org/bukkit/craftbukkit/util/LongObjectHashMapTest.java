package org.bukkit.craftbukkit.util;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class LongObjectHashMapTest {

    @Test
    public void testPutAndGet() {
        LongObjectHashMap<Integer> map = new LongObjectHashMap<Integer>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        assertThat(map.get(1), is(1)); //cache miss
        assertThat(map.get(1), is(1)); //cache hit
        assertThat(map.get(2), is(2)); //cache miss
        assertThat(map.get(2), is(2)); //cache hit

        map.put(2, 5); //update

        assertThat(map.get(2), is(5)); //cache hit

        assertThat(map.get(4), is(nullValue())); //cache miss
        assertThat(map.get(4), is(nullValue())); //cache hit
    }

    @Test
    public void testRemove() {
        LongObjectHashMap<Integer> map = new LongObjectHashMap<Integer>();
        map.put(1, 1);
        map.put(2, 2);

        assertThat(map.get(1), is(1)); //set cache
        map.remove(1);
        assertThat(map.get(1), is(nullValue())); //cache hit
        map.remove(2);
        assertThat(map.get(2), is(nullValue())); //cache miss
    }

    @Test
    public void testClear() {
        LongObjectHashMap<Integer> map = new LongObjectHashMap<Integer>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        assertThat(map.get(1), is(1)); //set cache

        map.clear();

        assertThat(map.get(1), is(nullValue())); //cache hit
        assertThat(map.get(2), is(nullValue())); //cache miss
    }
}
