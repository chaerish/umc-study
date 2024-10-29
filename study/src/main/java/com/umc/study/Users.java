@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 10, nullable = false)
    private String userName;

    @Column
    private LocalDateTime userBirth;

    @Column
    private String userAddress;

    @Column
    private Long userPoints;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @OneToMany(mappedBy = "user")
    private List<FavoriteFood> favoriteFoods;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    // Getters, setters, and constructors
}
