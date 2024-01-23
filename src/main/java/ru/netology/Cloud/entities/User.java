package ru.netology.Cloud.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Role> roles = List.of();

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "username")
//    private String username;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "email")
//    private String email;
//
//    @ManyToMany
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Collection<Role> roles;
//
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    private List<File> files = new ArrayList<>();

    public void addFileToUser(File file) {
        file.setUser(this);
        files.add(file);
    }
}
