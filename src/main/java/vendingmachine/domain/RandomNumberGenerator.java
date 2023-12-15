package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public int generate(final List<Integer> input) {
        return Randoms.pickNumberInList(input);
    }
}
