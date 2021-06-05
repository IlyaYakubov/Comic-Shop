package domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Скидка
 */
public class Discount {

    private String name;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private int percent;
    private List<Comic> comics;
}
