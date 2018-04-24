package ru.ivmiit.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "chat")
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    @ManyToMany(cascade = { CascadeType.ALL})
    @JoinTable(
        name = "chat_user",
        joinColumns = { @JoinColumn(name = "chat_id")},
        inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private List<User> members;
}
