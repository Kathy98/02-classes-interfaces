package ohm.softa.a02;

import java.util.Iterator;

public class SimpleListImpl implements SimpleList, Iterable<Object> {

    private Element head;
    private int size;

    public SimpleListImpl() {
        this.head = null;
    }

    // 2) Implement Element as static inner class of SimpleListImpl.
    private static class Element {
        private Object item;
        private Element next;

        Element(Object item) {
            this.item = item;
            this.next = null;
        }

        public Object getItem() {
            return item;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    // 3) Implement the Iterator interface as inner class of SimpleListImpl.
    // Ein Iterator ist ein Objekt, das eine bestimmte Datenstruktur sequenziell durchläuft.
    // next: Mit jedem Aufruf seiner Methode next liefert der Iterator jeweils das nächstfolgende Element.
    // hasNext: Mithilfe der Methode hasNext stellt der Iterator fest, ob noch weitere Elemente vorhanden sind.
    /*
    public interface Iterator {
        boolean hasNext();
        Object next();
    }
    */
    private class SimpleIterator implements Iterator<Object> {

        private Element current = head;

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Object next() {
            Object tmp = current.getItem();
            current = current.getNext();
            return tmp;
        }
    }

    // 4) Add the Iterable interface to your SimpleListImpl, and implement the required methods
    /*
    public interface Iterable {
        Iterator iterator();
    }
    */
    @Override
    public Iterator<Object> iterator(){
        return new SimpleIterator();
    }

    /**
     * Add a given object to the back of the list.
     */
    public void add(Object item) {
        Element neu = new Element(item);

        /* special case empty list */
        if (head == null) {
            head = neu;
        }
        else
        {
            /* any other list length */
            Element tmp = head;
            while (tmp.getNext() != null)
                tmp = tmp.getNext();
            tmp.setNext(neu); // tmp.next = neu;
        }
        size++;
    }

    /**
     * @return current size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Generate a new list using the given filter instance.
     * @return a new, filtered list
     */
    public SimpleList filter(SimpleFilter filter) {
        SimpleList newList = new SimpleListImpl();
        for(Object o : this){
            if(filter.include(o)){
                newList.add(o);
            }
        }
        return newList;
    }
}