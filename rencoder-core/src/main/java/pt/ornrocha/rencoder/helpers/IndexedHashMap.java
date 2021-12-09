/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Public License along with this code. If not, see
 * http://www.gnu.org/licenses/
 * 
 * Created by Paulo Maia
 */
package pt.ornrocha.rencoder.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class IndexedHashMap.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class IndexedHashMap<K, V> extends HashMap<K, V> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The index. */
  private List<K> index;

  /**
   * Instantiates a new indexed hash map.
   */
  public IndexedHashMap() {
    super();
    index = new ArrayList<K>();
  }

  /**
   * Instantiates a new indexed hash map.
   *
   * @param initialCapacity the initial capacity
   */
  public IndexedHashMap(int initialCapacity) {
    super(initialCapacity);
    index = new ArrayList<K>(initialCapacity);
  }

  /**
   * Instantiates a new indexed hash map.
   *
   * @param initialCapacity the initial capacity
   * @param loadFactor the load factor
   */
  public IndexedHashMap(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
    index = new ArrayList<K>(initialCapacity);
  }


  /*
   * (non-Javadoc)
   * 
   * @see java.util.HashMap#clear()
   */
  @Override
  public void clear() {
    super.clear();
    index.clear();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.HashMap#clone()
   */
  @SuppressWarnings("unchecked")
  @Override
  public Object clone() {
    IndexedHashMap<K, V> result = null;

    result = (IndexedHashMap<K, V>) super.clone();
    result.index = new ArrayList<K>();

    return result;
  }


  /*
   * (non-Javadoc)
   * 
   * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
   */
  @Override
  public V put(K key, V value) {
    V old = super.put(key, value);
    index.add(key);
    return old;
  }

  /**
   * Put at.
   *
   * @param idx the idx
   * @param key the key
   * @param value the value
   * @return the v
   */
  public V putAt(int idx, K key, V value) {
    V old = super.put(key, value);
    index.add(idx, key);
    return old;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.HashMap#putAll(java.util.Map)
   */
  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    super.putAll(m);
    for (K key : m.keySet())
      index.add(key);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.HashMap#remove(java.lang.Object)
   */
  @Override
  public V remove(Object key) {
    V result = super.remove(key);
    index.remove(key);
    return result;
  }


  /**
   * Gets the index of.
   *
   * @param key the key
   * @return the index of
   */
  public int getIndexOf(K key) {
    return index.indexOf(key);
  }

  /**
   * Gets the key at.
   *
   * @param ind the ind
   * @return the key at
   */
  public K getKeyAt(int ind) {
    return index.get(ind);
  }

  /**
   * Gets the value at.
   *
   * @param ind the ind
   * @return the value at
   */
  public V getValueAt(int ind) {
    K key = getKeyAt(ind);
    return super.get(key);
  }

  /**
   * Gets the index array.
   *
   * @return the index array
   */
  public List<K> getIndexArray() {
    return index;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.HashMap#size()
   */
  @Override
  public int size() {
    return index.size();
  }


}


