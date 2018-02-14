package model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andrey Volinskiy on 13.02.2018.
 */
@Getter
@Setter
public class Order {

    private String name;
    private long id;
    boolean completed;
}
