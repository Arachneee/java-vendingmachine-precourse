package vendingmachine.domain.item;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import vendingmachine.domain.Money;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.VendingMachineException;

public final class ItemParser {

    private static final Pattern ITEMS_PATTERN = Pattern.compile(
            "^\\[[가-힣]+,[0-9]+,[0-9]+\\](?:;\\[[가-힣]+,[0-9]+,[0-9]+\\])*$");
    private static final String ITEMS_DELIMITER = ";";
    private static final String ITEM_DELIMITER = ",";
    private ItemParser() {
    }

    public static List<Item> convertToItems(final String input) {
        validateItems(input);

        return Arrays.stream(input.split(ITEMS_DELIMITER))
                .map(ItemParser::convertToItem)
                .collect(Collectors.toList());
    }

    private static void validateItems(final String itemSource) {
        final Matcher matcher = ITEMS_PATTERN.matcher(itemSource);

        if (matcher.matches()) {
            return;
        }

        throw new VendingMachineException(ErrorMessage.INVALID_ITEM_FORMAT);
    }

    private static Item convertToItem(final String item) {
        final String[] itemSource = item.split(ITEM_DELIMITER);
        final Money money = new Money(itemSource[1]);
        final int count = convertToInt(itemSource[2]);
        return new Item(itemSource[0], money, count);
    }

    private static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw new VendingMachineException(ErrorMessage.NOT_NUMBER_COUNT);
        }
    }
}
