package vendingmachine.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Menu {

    A("1");

    private static final Map<String, Menu> menus = Arrays.stream(values())
            .collect(Collectors.toMap(menu -> menu.value,
                    menu -> menu));
    private final String value;

    Menu(String value) {
        this.value = value;
    }

    public static Menu from(final String value) {
        return menus.computeIfAbsent(value, key -> {return null;
        });
    }
}
