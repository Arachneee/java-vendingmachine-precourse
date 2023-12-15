package vendingmachine.domain;

import java.util.List;

@FunctionalInterface
public interface NumberGenerator {

    int generate(List<Integer> input);

}
