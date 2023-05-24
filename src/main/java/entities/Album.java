package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Album {
    private Long id;
    private Long userId;
    private String title;
}
