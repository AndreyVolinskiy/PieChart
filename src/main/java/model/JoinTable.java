package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Andrey Volinskiy on 13.02.2018.
 */
@Getter
@Setter
@ToString
public class JoinTable {

    private Long id;
    private String orderName;
    private Boolean completed;
    private String clientName;
    private String clientPhone;
}
