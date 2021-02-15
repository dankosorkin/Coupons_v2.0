package core.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TestUtility {
	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();

		list.add(2);
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(2);

		System.out.println(list instanceof Collection);

	}

	public static void print(Object obj) {
		if (obj instanceof Collection<?>) {
			Iterator<?> it = ((Collection) obj).iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
	}

}
