public class MyClass {
    public static void main(String args[]) {
PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
pq.add(4);
pq.add(1);
pq.add(3);
pq.add(2);
pq.add(4);
while( !pq.isEmpty() )
    System.out.print( pq.remove() );
    }
}