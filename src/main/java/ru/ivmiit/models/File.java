package ru.ivmiit.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //название загружаемого файла
    private String name;

    //для хранения
    private String storageFileName;

    //урл для загрузки
    private String url;

    private String type;
}