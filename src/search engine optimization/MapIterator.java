package sow;

import java.util.Iterator;


public interface MapIterator<K, V> extends Iterator<K> {

 /**
  * Checks to see if there are more entries still to be iterated.
  *
  * @return <code>true</code> if the iterator has more elements
  */
 boolean hasNext();

 /**
  * Gets the next <em>key</em> from the <code>Map</code>.
  *
  * @return the next key in the iteration
  * @throws java.util.NoSuchElementException if the iteration is finished
  */
 K next();

 //-----------------------------------------------------------------------
 /**
  * Gets the current key, which is the key returned by the last call
  * to <code>next()</code>.
  *
  * @return the current key
  * @throws IllegalStateException if <code>next()</code> has not yet been called
  */
 K getKey();

 /**
  * Gets the current value, which is the value associated with the last key
  * returned by <code>next()</code>.
  *
  * @return the current value
  * @throws IllegalStateException if <code>next()</code> has not yet been called
  */
 V getValue();

 //-----------------------------------------------------------------------
 /**
  * Removes the last returned key from the underlying <code>Map</code> (optional operation).
  * <p>
  * This method can be called once per call to <code>next()</code>.
  *
  * @throws UnsupportedOperationException if remove is not supported by the map
  * @throws IllegalStateException if <code>next()</code> has not yet been called
  * @throws IllegalStateException if <code>remove()</code> has already been called
  *  since the last call to <code>next()</code>
  */
 void remove();

 /**
  * Sets the value associated with the current key (optional operation).
  *
  * @param value  the new value
  * @return the previous value
  * @throws UnsupportedOperationException if setValue is not supported by the map
  * @throws IllegalStateException if <code>next()</code> has not yet been called
  * @throws IllegalStateException if <code>remove()</code> has been called since the
  *  last call to <code>next()</code>
  */
 V setValue(V value);

}
